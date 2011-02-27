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

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.Document;

import org.hyperdata.resources.scute.ScuteIcons;
import org.hyperdata.scute.autosave.AutoSave;
import org.hyperdata.scute.autosave.AutoSaveAction;
import org.hyperdata.scute.filemanager.FileExplorerPanel;
import org.hyperdata.scute.graph.GraphPanel;
import org.hyperdata.scute.graphmanager.GraphManagerPanel;
import org.hyperdata.scute.rdf.ModelContainer;
import org.hyperdata.scute.rdf.Models;
import org.hyperdata.scute.rdf.RdfUtils;
import org.hyperdata.scute.source.HighlighterEditorKit;
import org.hyperdata.scute.source.RdfSourcePanel;
import org.hyperdata.scute.sparql.SparqlPanel;
import org.hyperdata.scute.swing.FileChooserWrapper;
import org.hyperdata.scute.swing.FileUI;
import org.hyperdata.scute.swing.GeneralApplication;
import org.hyperdata.scute.swing.HelpUI;
import org.hyperdata.scute.swing.OpenDialog;
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
import org.hyperdata.scute.triples.TriplesPanel;
import org.hyperdata.scute.validate.TurtleValidateAction;
import org.hyperdata.scute.window.CardPanel;
import org.hyperdata.scute.window.TaskPanel;

/**
 * The Class Scute.
 */
public class Scute extends ModelContainer implements TreeSelectionListener,
		GeneralApplication, ToolsInterface {

	/** The Constant READ_ONLY_COLOR. */
	public static final Color READ_ONLY_COLOR = (Color) UIManager.getDefaults()
			.get("Button.background");

	/** The Constant READ_WRITE_COLOR. */
	public static final Color READ_WRITE_COLOR = (Color) UIManager
			.getDefaults().get("TextField.background");

	private static final int FRAME_INSET = 75;

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		new Scute();
	}

	/** The frame. */
	private JFrame frame;

	/** The node panel. */
	private NodePanel nodePanel;

	/** The normal cursor. */
	private Cursor normalCursor;

	/** The normal tree cursor. */
	private Cursor normalTreeCursor;

	/** The panel. */
	private final JPanel panel;

	/** The rdfxml panel. */
	private RdfSourcePanel rdfxmlPanel;

	/** The turtle panel. */
	private RdfSourcePanel turtlePanel;

	/** The tabs. */
	// private final JTabbedPane tabs;

	/** The tree panel. */
	private RdfTreePanel treePanel;

	/** The graph panel. */
	private GraphPanel graphPanel = null;

	private SystemPanel systemPanel;

	private SaveDialog saveDialog = null;

	private OpenDialog openDialog;

	private CardPanel cardPanel;

	private FileExplorerPanel fileExplorerPanel;

	private SparqlPanel sparqlPanel;

	private GraphManagerPanel graphManagerPanel;

	public static ScuteHelp scuteHelp;

	private AutoSave autoSave;

	private TriplesPanel triplesPanel;

	private FileUI fileUI;
	private HelpUI helpUI;

	/**
	 * Instantiates a new scute.
	 */
	public Scute() {

		// FIXME restorePreviousState BROKEN - fix!
		// TODO restorePreviousState BROKEN - fix!

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

		autoSave = new AutoSave();
		autoSave.setWorkingModelContainer(this);
		autoSave.setWorkingModelContainer(Config.self);

		Models.workingModel = Models.sampleModel;

		scuteHelp = new ScuteHelp();

		panel = new JPanel(new BorderLayout());

		makeCardPanel();

		final JPanel controlPanel = new JPanel(); // contains JToolBars
		panel.add(controlPanel, BorderLayout.NORTH);
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

		fileUI = new FileUI(this);

		controlPanel.add(fileUI.getToolBar());

		helpUI = new HelpUI(this);

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
		Document turtleDocument = turtlePanel.getDocument();
		StatusAction turtleAction = new TurtleValidateAction(turtleDocument);
		StatusPane validatorPane = new StatusPane(turtleAction);

		// Set up validator button
		StatusButton validatorButton = new StatusButton(turtleAction,
				"Invalid syntax", "Checking syntax...", "Valid syntax");

		statusPanel.add(validatorButton);
		statusPanel.add(validatorPane);

		TaskPanel taskPanel = new TaskPanel(cardPanel);
		panel.add(taskPanel, BorderLayout.WEST);

		/*
		 * FIXME validator, autosave must interrupt/be halted immediately on any
		 * actions only one can run at any given time make singleton?
		 */

		createFrame();

		// fileChooser = new JFileChooser("./data"); is used???

		if (Config.self.getSync() == false) { // previous run wasn't shut down
			// correctly
			System.out.println("RESTORE");
			autoSave.restorePreviousState(this);

			cardPanel.addChangeListener(autoSave);
		}
	}

	/**
	 * 
	 */
	private void createFrame() {
		frame = new JFrame("Scute");

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(FRAME_INSET, FRAME_INSET, screenSize.width - 2
				* FRAME_INSET, screenSize.height - 2 * FRAME_INSET);

		frame.setIconImage(ScuteIcons.applicationIcon.getImage());
		frame.addWindowListener(autoSave);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileUI.getFileMenu());
		menuBar.add(helpUI.getHelpMenu());

		frame.setJMenuBar(menuBar);
		frame.setContentPane(panel);
		// frame.pack();

		frame.setVisible(true);
	}

	/**
	 * 
	 */
	private void makeCardPanel() {
		cardPanel = new CardPanel();
		panel.add(cardPanel, BorderLayout.CENTER);

		makeTurtlePanel();
		makeRdfXmlPanel();
		makeTreePanel();
		makeTriplesPanel();
		makeSparqlPanel();
		makeGraphManagerPanel();
		makeFileExplorerPanel();
		makeLogPanel();
		makeSystemPanel();
	}

	/**
	 * 
	 */
	private void makeSystemPanel() {
		systemPanel = new SystemPanel();
		// systemPanel.addUserActivityListener(autoSave);

		cardPanel.add("System", new JScrollPane(systemPanel));
	}

	/**
	 * 
	 */
	private void makeLogPanel() {
		LogPane log = LogPane.getLogPane();
		JScrollPane logScroll = new JScrollPane(log);
		logScroll.setBorder(BorderFactory.createLoweredBevelBorder());
		LogPane.println("Ok.");
		TitledBorder logBorder = BorderFactory.createTitledBorder("Log");
		logScroll.setBorder(logBorder);
		cardPanel.add("Log", logScroll);
	}

	/**
	 * 
	 */
	private void makeFileExplorerPanel() {
		fileExplorerPanel = new FileExplorerPanel(Config.DATA_DIR);
		cardPanel.add("Files", fileExplorerPanel);
	}

	/**
	 * 
	 */
	private void makeGraphManagerPanel() {
		graphManagerPanel = new GraphManagerPanel();
		cardPanel.add("Graphs", graphManagerPanel);
	}

	/**
	 * 
	 */
	private void makeSparqlPanel() {
		sparqlPanel = new SparqlPanel();

		cardPanel.add("SPARQL", sparqlPanel);
	}

	/**
	 * 
	 */
	private void makeTriplesPanel() {
		triplesPanel = new TriplesPanel(Models.workingModel);
		// triplesPanel.addUserActivityListener(autoSave);
		// TODO create UserActivityListener interface
		// need change listener???
		// TODO ADD SELECTION LISTENER - make shared listener?
		cardPanel.add("Triples", triplesPanel);
	}

	/**
	 * 
	 */
	private void makeTreePanel() {
		treePanel = new RdfTreePanel(Models.workingModel);
		treePanel.addUserActivityListener(autoSave);

		// need change listener???
		cardPanel.add("Tree", treePanel);

		graphPanel = new GraphPanel(Models.workingModel);
		graphPanel.addUserActivityListener(autoSave);
		// need change listener???
		cardPanel.add("Graph", graphPanel);
	}

	/**
	 * 
	 */
	private void makeRdfXmlPanel() {
		rdfxmlPanel = new RdfSourcePanel("RDF/XML");
		rdfxmlPanel.addUserActivityListener(autoSave);
		rdfxmlPanel.loadModel(Models.workingModel);
		rdfxmlPanel.setEditorKit(new HighlighterEditorKit("XML"));
		Document rdfxmlDocument = turtlePanel.getDocument();

		cardPanel.addChangeListener(rdfxmlPanel);
		JPanel rdfxmlCard = new JPanel(new BorderLayout());
		rdfxmlCard.add(new JScrollPane(rdfxmlPanel), BorderLayout.CENTER);
		cardPanel.add("RDF/XML", rdfxmlCard);
	}

	/**
	 * 
	 */
	private void makeTurtlePanel() {
		turtlePanel = new RdfSourcePanel("Turtle");
		turtlePanel.addUserActivityListener(autoSave);
		turtlePanel.setEditorKit(new HighlighterEditorKit("Turtle"));
		turtlePanel.loadModel(Models.workingModel);

		autoSave.setCurrentTextContainer(turtlePanel);

		cardPanel.addChangeListener(turtlePanel);

		JPanel turtleCard = new JPanel(new BorderLayout());
		turtleCard.add(new JScrollPane(turtlePanel), BorderLayout.CENTER);
		cardPanel.add("Turtle", turtleCard);
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
	public void open() {
		if (getModelURI() == null && getModelFilename() == null) { // never been
																	// saved
			JOptionPane.showMessageDialog(frame,
					"Please save current Working Graph");
			saveAs();
		} else {
			save();
		}
		try {
			if (openDialog == null) {
				openDialog = new OpenDialog(frame);
			}
			// saveDialog.setSize(400,200);
			openDialog.pack();
			openDialog.setVisible(true);
		} catch (final Exception exception) {
			System.out.println("Open aborted");
			return;
		}
		String filename = openDialog.getFilename();
		if (filename != null) {
			setModelFilename(filename);

			loadModelFromFile();
		}
		String uri = saveDialog.getURI();
		if (uri != null) {
			setModelURI(uri);
			loadNamedModel();
		}

		logPrintln("Loaded");
		System.out.println("Loaded");

		turtlePanel.loadModel(Models.workingModel);
		rdfxmlPanel.loadModel(Models.workingModel);

		treePanel.loadModel(Models.workingModel);
		treePanel.init();

		graphPanel.loadModel(Models.workingModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperdata.scute.swing.ToolsInterface#saveAsFile()
	 */
	@Override
	public void saveAs() {

		try {
			if (saveDialog == null) {
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
		if (filename != null) {
			setModelFilename(filename);
			saveModelToFile();
		}
		String uri = saveDialog.getURI();
		if (uri != null) {
			setModelURI(uri);
			storeNamedModel();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperdata.scute.swing.ToolsInterface#save()
	 */
	@Override
	public void save() {
		if (getModelURI() == null && getModelFilename() == null) {
			saveAs();
			return;
		}
		if (getModelURI() != null) {
			storeNamedModel();
		}
		if (getModelFilename() != null) {
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

	// is needed?
	public static void setSystemLookFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperdata.scute.swing.ToolsInterface#getFrame()
	 */
	@Override
	public Frame getFrame() {
		return frame;
	}

	/**
	 * @param selectedView
	 */
	public void setSelectedView(String selectedView) {
		cardPanel.setViewName(selectedView);
	}
}
