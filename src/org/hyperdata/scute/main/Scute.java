package org.hyperdata.scute.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import org.hyperdata.scute.graph.GraphDiagramPanel;
import org.hyperdata.scute.graph.GraphPanel;
import org.hyperdata.scute.io.AutoSave;
import org.hyperdata.scute.log.LogPane;
import org.hyperdata.scute.rdf.ModelContainer;
import org.hyperdata.scute.rdf.Models;
import org.hyperdata.scute.rdf.RdfUtils;
import org.hyperdata.scute.source.HighlighterEditorKit;
import org.hyperdata.scute.source.SourcePanel;
import org.hyperdata.scute.source.SourceToolUI;
import org.hyperdata.scute.swing.FileChooserWrapper;
import org.hyperdata.scute.swing.FileToolUI;
import org.hyperdata.scute.swing.GeneralApplication;
import org.hyperdata.scute.swing.ToolsInterface;
import org.hyperdata.scute.tree.NodePanel;
import org.hyperdata.scute.tree.RdfTreeNode;
import org.hyperdata.scute.tree.RdfTreePanel;

import com.hp.hpl.jena.rdf.model.Model;

public class Scute implements TreeSelectionListener, GeneralApplication,
		ToolsInterface, ModelContainer {

	public static final Dimension FRAME_SIZE = new Dimension(800, 800);

	public static final Color READ_ONLY_COLOR = (Color) UIManager.getDefaults()
			.get("Button.background");

	public static final Color READ_WRITE_COLOR = (Color) UIManager
			.getDefaults().get("TextField.background");

	public static final Dimension SOURCE_PANEL_SIZE = new Dimension(600, 300);

	public static final Dimension TREE_PANEL_SIZE = new Dimension(600, 300);

	public static void main(String[] args) {
		// WindowKit.setPlastic3DLookAndFeel();
		// WindowKit.setNativeLookAndFeel();
		new Scute();
	}

	private final JFileChooser fileChooser;
	private final JFrame frame;

	private NodePanel nodePanel;

	private Cursor normalCursor;
	private Cursor normalTreeCursor;

	private final JPanel panel;
	private final SourcePanel rdfxmlPanel;
	private final SourcePanel turtlePanel;
	private final JTabbedPane tabs;
	private final RdfTreePanel treePanel;
	private GraphPanel graphPanel = null;


	public Scute() {

		// for bootstrapping/debugging
		// Config.self.setDefaults();
		// Config.self.saveNow();

		AutoSave autosave = new AutoSave();

		if (Config.self.getSync()) { // previous run was shut down correctly
			System.out.println("CLEAN");
			Config.self.setSync(false);
			Config.self.saveNow();
		}
		autosave.initModelSaver(this);
		autosave.initModelSaver(Config.self);

		Models.workingModel = Models.sampleModel;

		panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(FRAME_SIZE);
		tabs = new JTabbedPane(JTabbedPane.BOTTOM);
		panel.add(tabs, BorderLayout.CENTER);

		turtlePanel = new SourcePanel(autosave, "Turtle");
		turtlePanel.setEditorKit(new HighlighterEditorKit("Turtle"));
		turtlePanel.loadModel(Models.workingModel);
		tabs.addChangeListener(turtlePanel);
		tabs.addTab("Turtle", new JScrollPane(turtlePanel));
		// JTabbedPane setBackgroundAt(int index, Color background) 
		
		rdfxmlPanel = new SourcePanel(autosave, "RDF/XML");
		rdfxmlPanel.loadModel(Models.workingModel);
		rdfxmlPanel.setEditorKit(new HighlighterEditorKit("XML"));
		tabs.addChangeListener(rdfxmlPanel);
		tabs.addTab("RDF/XML", new JScrollPane(rdfxmlPanel));
		
		treePanel = new RdfTreePanel(Models.workingModel);
		tabs.addTab("Tree", treePanel); // treePanel has scroll?

		graphPanel = new GraphPanel(Models.workingModel);
		tabs.addTab("Graph", graphPanel); 

		tabs.setSelectedIndex(0);
//		graphPanel.scramble();
//		graphPanel.start();
	//	graphPanel.initialize();

		final JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

		final FileToolUI fileUI = new FileToolUI(this);

		controlPanel.add(fileUI.getToolBar());

		final SourceToolUI sourceUI = new SourceToolUI(this);

		controlPanel.add(sourceUI.getToolBar());

		panel.add(controlPanel, BorderLayout.NORTH);

		initLogPane();

		frame = new JFrame("Scute (0.5 Beta)");
		frame.addWindowListener(autosave);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileUI.getFileMenu());
		menuBar.add(sourceUI.getSourceMenu());

		frame.setJMenuBar(menuBar);
		frame.setContentPane(panel);
		frame.pack();
		// frame.show();
		frame.setVisible(true);
		fileChooser = new JFileChooser("./data");
		if (Config.self.getSync() == false) { // previous run wasn't shut down
			// correctly
			System.out.println("RESTORE");
			autosave.restorePreviousState(this);
			tabs.addChangeListener(autosave); // so previous tab can be
			// restored, has to be here to
			// miss initializing change to
			// tab 0
		}
	}

	public void setSelectedTab(int index) {
		System.out.println("setting tab = " + index);
		tabs.setSelectedIndex(index);
	}

	public int getSelectedTab() {
		return tabs.getSelectedIndex();
	}

	public void cloneFile() {
		throw new RuntimeException("not yet implemented");
	}

	public void closeFile() {
		throw new RuntimeException("not yet implemented");
	}

	public void exit() { // is needed?
		// frame.dispose();
	}

	private void initLogPane() {
		final LogPane log = LogPane.getLogPane();
		final JScrollPane logScroll = new JScrollPane(log);
		logScroll.setBorder(BorderFactory.createLoweredBevelBorder());
		LogPane.println("Ok.");
		tabs.addTab("Log", log);
	}

	public void logPrintErr(String string) {
		LogPane.err(string);
	}

	public void logPrintln(String string) {
		LogPane.println(string);
	}

	public void newFile() {
	}

	public void openFile() {
		final int returnVal = fileChooser.showOpenDialog(frame);
		// Model model = ModelFactory.createDefaultModel();

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			final File file = fileChooser.getSelectedFile();
			LogPane.println("Opening: " + file.getName());

			String syntax = "Turtle";
			if (file.getPath().toLowerCase().endsWith(".rdf")) {
				syntax = "RDF/XML";
			}

			try {
				final InputStream stream = new FileInputStream(file);
				Models.clearWorkingModel();
				Models.workingModel.read(new FileInputStream(file), "", syntax);
				stream.close();
			} catch (final Exception exception) {
				logPrintErr(exception.getMessage());
				exception.printStackTrace();
				System.out.println(exception.getMessage());
			}
		} else {
			LogPane.println("Open command cancelled by user.");
		}
		logPrintln("Loaded");
		System.out.println("Loaded");

		// turtlePanel.setModel(Models.workingModel);
		// rdfxmlPanel.setModel(Models.workingModel);

		turtlePanel.loadModel(Models.workingModel);
		rdfxmlPanel.loadModel(Models.workingModel);

		// tree = populateTree(Models.workingModel);
		treePanel.loadModel(Models.workingModel);
		treePanel.init();

		// ///////////////////////////////
	}

	public void saveAsFile() {

		try {
			FileChooserWrapper.getFileChooser().saveDialog().toString();
		} catch (final Exception exception) {
			System.out.println("Export aborted");
			return;
		}
	}

	@Override
	public void saveFile() {
		// TODO save file (as Export?)
	}

	public void valueChanged(TreeSelectionEvent event) {
		final Object object = treePanel.getTree()
				.getLastSelectedPathComponent();
		System.out.println("value changed");
		if ((object == null)) {
			System.out.println("null object");
			return;
		}
		nodePanel.setRdfTreeNode((RdfTreeNode) object);
	}

	public void waitCursor(boolean wait) {
		if (wait) {
			normalCursor = frame.getCursor();
			normalTreeCursor = treePanel.getCursor();
			frame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			treePanel.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		} else {
			frame.setCursor(normalCursor);
			treePanel.setCursor(normalTreeCursor);
			frame.setCursor(normalCursor);
			treePanel.setCursor(normalTreeCursor);
		}
	}

	@Override
	public Model getModel() {
		return Models.workingModel;
	}

	@Override
	public String getModelFilename() {
		return Config.WORKING_MODEL_FILENAME;
	}

	@Override
	public String getModelName() {
		return "working model";
	}

	public SourcePanel getCurrentSourcePanel() throws Exception {
		JScrollPane scroll = (JScrollPane) tabs.getSelectedComponent();
		// System.out.println("component = "+scroll);
		Object panel = scroll.getViewport().getView();
		if (panel instanceof SourcePanel)
			return (SourcePanel) panel;
		else
			throw new Exception("not a text panel");
	}

	public void setSourceText(String savedText) {
		try {
			getCurrentSourcePanel().setText(savedText);
		} catch (Exception e) {
			// ignore
		}
	}

	@Override
	public void checkText() {
		try {
			String text = getCurrentSourcePanel().getText();
			String syntax = getCurrentSourcePanel().getSyntax();
			RdfUtils.stringToModel(text, Config.baseUri, syntax);
		} catch (Exception e) {
			System.out.println("INVALID");
		}
		System.out.println("VALID");
	}
}
