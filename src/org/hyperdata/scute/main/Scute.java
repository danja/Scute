/*
 * Scute
 * 
 * Homepage: http://hyperdata.org/scute
 * 
 * License : http://www.apache.org/licenses/LICENSE-2.0
 * See also license.txt or http://hyperdata.org/wiki/Scute:License
 * 
 * Danny Ayers 2011
 */
package org.hyperdata.scute.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.Document;

import org.hyperdata.resources.scute.ScuteIcons;
import org.hyperdata.scute.autosave.AutoSave;
import org.hyperdata.scute.autosave.AutoSaveAction;
import org.hyperdata.scute.graph.GraphPanel;
import org.hyperdata.scute.rdf.ModelContainer;
import org.hyperdata.scute.rdf.Models;
import org.hyperdata.scute.rdf.RdfUtils;
import org.hyperdata.scute.source.HighlighterEditorKit;
import org.hyperdata.scute.source.SourcePanel;
import org.hyperdata.scute.swing.FileChooserWrapper;
import org.hyperdata.scute.swing.FileToolUI;
import org.hyperdata.scute.swing.GeneralApplication;
import org.hyperdata.scute.swing.SaveDialog;
import org.hyperdata.scute.swing.ToolsInterface;
import org.hyperdata.scute.swing.status.StatusAction;
import org.hyperdata.scute.swing.status.StatusButton;
import org.hyperdata.scute.swing.status.StatusPane;
import org.hyperdata.scute.syspane.LogPane;
import org.hyperdata.scute.syspane.LookFeelPanel;
import org.hyperdata.scute.syspane.SystemPanel;
import org.hyperdata.scute.tree.NodePanel;
import org.hyperdata.scute.tree.RdfTreeNode;
import org.hyperdata.scute.tree.RdfTreePanel;
import org.hyperdata.scute.validate.TurtleValidateAction;

/**
 * The Class Scute.
 */
public class Scute extends ModelContainer implements TreeSelectionListener,
		GeneralApplication, ToolsInterface {

	/** The Constant FRAME_SIZE. */
	public static final Dimension FRAME_SIZE = new Dimension(800, 800);

	/** The Constant READ_ONLY_COLOR. */
	public static final Color READ_ONLY_COLOR = (Color) UIManager.getDefaults()
			.get("Button.background");

	/** The Constant READ_WRITE_COLOR. */
	public static final Color READ_WRITE_COLOR = (Color) UIManager
			.getDefaults().get("TextField.background");

	/** The Constant SOURCE_PANEL_SIZE. */
	public static final Dimension SOURCE_PANEL_SIZE = new Dimension(600, 300);

	/** The Constant TREE_PANEL_SIZE. */
	public static final Dimension TREE_PANEL_SIZE = new Dimension(600, 300);

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		// WindowKit.setPlastic3DLookAndFeel();
		// WindowKit.setNativeLookAndFeel();
		new Scute();
	}

	/** The file chooser. */
	private final JFileChooser fileChooser;

	/** The frame. */
	private final JFrame frame;

	/** The node panel. */
	private NodePanel nodePanel;

	/** The normal cursor. */
	private Cursor normalCursor;

	/** The normal tree cursor. */
	private Cursor normalTreeCursor;

	/** The panel. */
	private final JPanel panel;

	/** The rdfxml panel. */
	private final SourcePanel rdfxmlPanel;

	/** The turtle panel. */
	private final SourcePanel turtlePanel;

	/** The tabs. */
	private final JTabbedPane tabs;

	/** The tree panel. */
	private final RdfTreePanel treePanel;

	/** The graph panel. */
	private GraphPanel graphPanel = null;

	private SystemPanel systemPanel;

	private SaveDialog saveDialog = null;

	/**
	 * Instantiates a new scute.
	 */
	public Scute() {

		// setSystemLookFeel();
		
		// for bootstrapping/debugging
		// Config.self.setDefaults();
		// Config.self.saveNow();

		if (Config.self.getSync()) { // previous run was shut down correctly
			System.out.println("CLEAN");
			Config.self.setSync(false);
			Config.self.saveNow();
		}
		setModel(Models.workingModel);
		setModelFilename(Config.WORKING_MODEL_FILENAME);
		setModelURI(Config.WORKING_MODEL_URI);

		AutoSave autoSave = new AutoSave();
		autoSave.initModelSaver(this);
		autoSave.initModelSaver(Config.self);

		Models.workingModel = Models.sampleModel;

		panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(FRAME_SIZE);
		tabs = new JTabbedPane(SwingConstants.BOTTOM);
		panel.add(tabs, BorderLayout.CENTER);

		turtlePanel = new SourcePanel("Turtle");
		turtlePanel.addUserActivityListener(autoSave);
		turtlePanel.setEditorKit(new HighlighterEditorKit("Turtle"));
		turtlePanel.loadModel(Models.workingModel);
		Document turtleDocument = turtlePanel.getDocument();
		tabs.addChangeListener(turtlePanel);
		tabs.addTab("Turtle", new JScrollPane(turtlePanel));

		rdfxmlPanel = new SourcePanel("RDF/XML");
		rdfxmlPanel.addUserActivityListener(autoSave);
		rdfxmlPanel.loadModel(Models.workingModel);
		rdfxmlPanel.setEditorKit(new HighlighterEditorKit("XML"));
		Document rdfxmlDocument = turtlePanel.getDocument();
		tabs.addChangeListener(rdfxmlPanel);
		tabs.addTab("RDF/XML", new JScrollPane(rdfxmlPanel));

		treePanel = new RdfTreePanel(Models.workingModel);
		treePanel.addUserActivityListener(autoSave);
		tabs.addTab("Tree", treePanel); // treePanel has scroll?

		graphPanel = new GraphPanel(Models.workingModel);
		graphPanel.addUserActivityListener(autoSave);
		tabs.addTab("Graph", graphPanel);

		systemPanel = new SystemPanel();
		// systemPanel.addUserActivityListener(autoSave);
		tabs.addTab("System", new JScrollPane(systemPanel));

		// tabs.setSelectedIndex(0);

		final JPanel controlPanel = new JPanel(); // contains JToolBars
		panel.add(controlPanel, BorderLayout.NORTH);
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

		final FileToolUI fileUI = new FileToolUI(this);

		controlPanel.add(fileUI.getToolBar());

		// final SourceToolUI sourceUI = new SourceToolUI(this);
		// JToolBar sourceToolbar = sourceUI.getToolBar();
		// controlPanel.add(sourceToolbar); // TODO tidy up toolbars

		JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEADING)); // left-aligned
		statusPanel.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED));
		panel.add(statusPanel, BorderLayout.SOUTH);

		// FIXME basic Save and Load

		// Set up autosave
		// FIXME merge with AutoSave stuff above
		StatusAction autosaveAction = new AutoSaveAction();
		StatusButton autosaveButton = new StatusButton(autosaveAction,
				"Unsaved", "Saving...", "Saved");
		statusPanel.add(autosaveButton);

		// Set up validators
		StatusAction turtleAction = new TurtleValidateAction(turtleDocument);
		StatusPane validatorPane = new StatusPane(turtleAction);

		// Set up validator button
		StatusButton validatorButton = new StatusButton(turtleAction,
				"Invalid syntax", "Checking syntax...", "Valid syntax");

		statusPanel.add(validatorButton);
		statusPanel.add(validatorPane);
		/*
		 * FIXME validator, autosave must interrupt/be halted immediately on any
		 * actions only one can run at any given time make singleton?
		 */

		frame = new JFrame("Scute (0.5 Beta)");
		frame.setIconImage(ScuteIcons.applicationIcon);
		frame.addWindowListener(autoSave);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileUI.getFileMenu());
		// menuBar.add(sourceUI.getSourceMenu());

		frame.setJMenuBar(menuBar);
		frame.setContentPane(panel);
		frame.pack();

		frame.setVisible(true);
		fileChooser = new JFileChooser("./data");
		if (Config.self.getSync() == false) { // previous run wasn't shut down
			// correctly
			System.out.println("RESTORE");
			autoSave.restorePreviousState(this);
			tabs.addChangeListener(autoSave); // so previous tab can be
			// restored, has to be here to
			// miss initializing change to
			// tab 0
		}
	}

	/**
	 * Sets the selected tab.
	 * 
	 * @param index
	 *            the new selected tab
	 */
	public void setSelectedTab(int index) {
		System.out.println("setting tab = " + index);
		tabs.setSelectedIndex(index);
	}

	/**
	 * Gets the selected tab.
	 * 
	 * @return the selected tab
	 */
	public int getSelectedTab() {
		return tabs.getSelectedIndex();
	}

	public String getSelectedTabTitle() {
		return tabs.getTitleAt(getSelectedTab());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperdata.scute.swing.ToolsInterface#cloneFile()
	 */
	@Override
	public void cloneFile() {
		throw new RuntimeException("not yet implemented");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperdata.scute.swing.ToolsInterface#closeFile()
	 */
	@Override
	public void closeFile() {
		throw new RuntimeException("not yet implemented");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperdata.scute.swing.ToolsInterface#exit()
	 */
	@Override
	public void exit() { // is needed?
		// frame.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.hyperdata.scute.swing.GeneralApplication#logPrintErr(java.lang.String
	 * )
	 */
	@Override
	public void logPrintErr(String string) {
		LogPane.err(string);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.hyperdata.scute.swing.GeneralApplication#logPrintln(java.lang.String)
	 */
	@Override
	public void logPrintln(String string) {
		LogPane.println(string);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperdata.scute.swing.ToolsInterface#newFile()
	 */
	@Override
	public void newModel() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperdata.scute.swing.ToolsInterface#openFile()
	 */
	@Override
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperdata.scute.swing.ToolsInterface#saveAsFile()
	 */
	@Override
	public void saveAs() {

		try {
			if(saveDialog == null){
			saveDialog = new SaveDialog(frame);
			}
			// saveDialog.setSize(400,200);
			saveDialog.pack();
			saveDialog.setVisible(true);
		} catch (final Exception exception) {
			System.out.println("Export aborted");
			return;
		}
		String filename = saveDialog.getFilename();
		if(filename != null){
		setModelFilename(filename);
		saveModelToFile();
		}
		String uri = saveDialog.getURI();
		if(uri != null){
		setModelURI(uri);
		storeModel();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperdata.scute.swing.ToolsInterface#save()
	 */
	@Override
	public void save() {
		if(getModelURI() == null && getModelFilename() == null){
			saveAs();
			return;
		}
		if(getModelURI() != null){
			storeModel();
		}
		if(getModelFilename() != null){
			saveModelToFile();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event
	 * .TreeSelectionEvent)
	 */
	@Override
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperdata.scute.swing.GeneralApplication#waitCursor(boolean)
	 */
	@Override
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

	/**
	 * Gets the current source panel.
	 * 
	 * TODO check this is in use
	 * 
	 * @return the current source panel
	 * @throws Exception
	 *             the exception
	 */
	public SourcePanel getCurrentSourcePanel() throws Exception {
		JScrollPane scroll = (JScrollPane) tabs.getSelectedComponent();
		// System.out.println("component = "+scroll);
		Object panel = scroll.getViewport().getView();
		if (panel instanceof SourcePanel)
			return (SourcePanel) panel;
		else
			throw new Exception("not a text panel");
	}

	/**
	 * Sets the source text.
	 * 
	 * @param savedText
	 *            the new source text
	 */
	public void setSourceText(String savedText) {
		try {
			getCurrentSourcePanel().setText(savedText);
		} catch (Exception e) {
			// ignore
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperdata.scute.swing.ToolsInterface#checkText()
	 */
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

	public static void setSystemLookFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
