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
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.text.Document;

//import org.hdesktop.swingx.JXMultiSplitPane;
//import org.hdesktop.swingx.JXTitledPanel;
//import org.hdesktop.swingx.MultiSplitLayout;
//import org.hdesktop.swingx.MultiSplitLayout.Divider;
//import org.hdesktop.swingx.MultiSplitLayout.Leaf;
//import org.hdesktop.swingx.MultiSplitLayout.Split;

import org.jdesktop.swingx.*;
import org.jdesktop.swingx.action.*;
import org.jdesktop.swingx.MultiSplitLayout.*;

import org.hyperdata.resources.scute.ScuteIcons;
import org.hyperdata.scute.autosave.AutoSave;
import org.hyperdata.scute.autosave.AutoSaveAction;
import org.hyperdata.scute.cards.Card;
import org.hyperdata.scute.cards.CardFactory;
import org.hyperdata.scute.cards.CardsPanel;
import org.hyperdata.scute.cards.TaskPanel;
import org.hyperdata.scute.help.HelpUI;
import org.hyperdata.scute.rdf.ModelContainer;
import org.hyperdata.scute.rdf.Models;
import org.hyperdata.scute.source.RdfSourcePanel;
import org.hyperdata.scute.source.popup.PopupListener;
import org.hyperdata.scute.source.popup.SourcePopupMenu;
import org.hyperdata.scute.sparql.panels.SparqlCard;
import org.hyperdata.scute.status.StatusAction;
import org.hyperdata.scute.status.StatusButton;
import org.hyperdata.scute.status.StatusInfoPane;
import org.hyperdata.scute.syntax.ScuteEditorKit;
import org.hyperdata.scute.system.Log;
import org.hyperdata.scute.system.panels.LogPane;
import org.hyperdata.scute.toolbars.file.FileUI;
import org.hyperdata.scute.toolbars.file.IO;
// import org.hyperdata.scute.toolbars.history.HistoryToolbar;
import org.hyperdata.scute.toolbars.source.EditorToolbar;
import org.hyperdata.scute.tree.NodePanel;
import org.hyperdata.scute.tree.RdfTreeCard;
import org.hyperdata.scute.tree.RdfTreeNode;
import org.hyperdata.scute.validate.RdfXmlValidateAction;
import org.hyperdata.scute.validate.TurtleValidateAction;

/**
 * The Class Scute.
 */
public class Scute extends ModelContainer implements TreeSelectionListener, ScuteIF {

	/** The Constant READ_ONLY_COLOR. */
	public static final Color READ_ONLY_COLOR = (Color) UIManager.getDefaults()
			.get("Button.background");

	/** The Constant READ_WRITE_COLOR. */
	public static final Color READ_WRITE_COLOR = (Color) UIManager
			.getDefaults().get("TextField.background");

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		String homeDir = "";
		for (int i = 0; i < args.length; i++){
			if(args[i].equals("--home")){
				homeDir = args[i+1];
				break;
			}
		}
		Config.init(homeDir);
//		System.out.println("config="+Config.self.CONFIG_FILENAME);
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
	private Card treeCard;

	/** The graph panel. */
	private Card graphCard = null;

	private Card settingsCard;

	private CardsPanel cardsPanel;
	
	private JTabbedPane cardsPanelTabs;

	private Card fileExplorerCard;

	private Card sparqlCard;

	private Card graphManagerCard;

	public static ScuteHelp scuteHelp;

	private AutoSave autoSave;

	private Card triplesCard;

	private FileUI fileUI;
	private HelpUI helpUI;

	private IO io;

	private ScratchPad scratchPad;

	private EditorToolbar editorToolbar;

	private FocusMonitor focusMonitor;

	private Leaf leftLeaf;

	private Leaf centerLeaf;

	private Leaf rightLeaf;

	private SplitScreen splitScreen;

	private Card imageCard;

	private JPanel toolsPanel;

	private JPanel statusBar;

	private JScrollPane scratchPane;

	private JXMultiSplitPane multiSplitPane;

	private JMenuBar menuBar;

	private TabbedPaneUI tabUI;

//	private HistoryToolbar historyToolbar;

	/**
	 * Instantiates a new scute.
	 */
	public Scute() {

		frame = new JFrame("Scute");

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

//		Models.workingModel = Models.sampleModel;

		scuteHelp = new ScuteHelp();

		panel = new JPanel(new BorderLayout());

		focusMonitor = new FocusMonitor();
		
		autoSave = new AutoSave();
		
		editorToolbar = new EditorToolbar(frame);
		focusMonitor.setEditorToolbar(editorToolbar);
		
		makeCardsPanel();
		
		makeScratchPad();
		
		// TODO SEE TABS org.hyperdata.scute.tabs
		// CardSetView
		// CardSetModel
		cardsPanelTabs = new JTabbedPane();
		
		cardsPanelTabs.addTab("Tab 1", cardsPanel);
		cardsPanelTabs.addTab("Tab 2", new JPanel());
		
		tabUI = cardsPanelTabs.getUI();
showTabs(false);
		
		makeSplitScreen();

		// effectively presets
		autoSave.setWorkingModelContainer(this);
		autoSave.setWorkingModelContainer(Config.self);
		autoSave.setCurrentTextContainer(turtlePanel);
		autoSave.setScratchTextContainer(scratchPad);
		
		cardsPanel.addChangeListener(autoSave);

		io = new IO(this, cardsPanel);
		fileUI = new FileUI(io);
		
//		historyToolbar = new HistoryToolbar();

		makeToolsPanel();
		
		helpUI = new HelpUI(io);

		
		// FIXME basic Save and Load

		// Set up autosave
		// FIXME merge with AutoSave stuff above


		
		// TODO tweak makeCardsPanel to support list of cards panels
		TaskPanel taskPanel = new TaskPanel(cardsPanel);
	

	multiSplitPane.add(taskPanel, "left");

		
		/*
		 * FIXME validator, autosave must interrupt/be halted immediately on any
		 * actions only one can run at any given time make singleton?
		 */

		makeFrame();
		
		splitScreen.setFullMiddle();
		showTools(false);
		showStatusBar(false);

		if (Config.self.getSync() == false) { // previous run wasn't shut down
			// correctly
			System.out.println("RESTORE");
			autoSave.restorePreviousState(this);

			cardsPanel.addChangeListener(autoSave);
		}
	}

	/**
	 * 
	 */
	private void makeSplitScreen() {
		// Split Pane stuff
		// there's an awful lot of it...
		leftLeaf = new Leaf("left");
		centerLeaf = new Leaf("center");
		rightLeaf = new Leaf("right");
		
		List children = Arrays.asList(leftLeaf, new Divider(), centerLeaf,
				new Divider(), rightLeaf);
		Split splitModel = new Split();
		splitModel.setChildren(children);
		MultiSplitLayout multiSplitLayout = new MultiSplitLayout();
		multiSplitLayout.setLayoutMode(MultiSplitLayout.NO_MIN_SIZE_LAYOUT);
		multiSplitPane = new JXMultiSplitPane(multiSplitLayout);
		multiSplitPane.getMultiSplitLayout().setModel(splitModel);
		multiSplitPane.add(cardsPanelTabs, "center");
		multiSplitPane.add(new JXTitledPanel("Scratch Pad", scratchPane), "right");

		splitScreen = new SplitScreen(multiSplitPane, leftLeaf, centerLeaf,
				rightLeaf);
		panel.add(multiSplitPane, BorderLayout.CENTER);
	}

	/**
	 * 
	 */
	private void makeScratchPad() {
		scratchPad = new ScratchPad("Text");
		scratchPad.setEditorKit(new ScuteEditorKit("SPARQL"));
		scratchPad.setFilename(Config.SCRATCH_FILENAME);
		scratchPad.addFocusListener(focusMonitor);
		scratchPad.loadSoon(); // load saved contents
		
		scratchPane = new JScrollPane(scratchPad,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scratchPane.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));
	}

	/**
	 * 
	 */
	private void makeToolsPanel() {
		toolsPanel = new JPanel(); // contains JToolBars
		panel.add(toolsPanel, BorderLayout.NORTH);
		toolsPanel.setLayout(new BoxLayout(toolsPanel, BoxLayout.X_AXIS));
		// controlPanel.add(splitButtons.getLeftButton()); more trouble than it was worth
	
		toolsPanel.add(fileUI.getToolBar());
	//	toolsPanel.add(historyToolbar); replaced by tabs
		toolsPanel.add(editorToolbar);
		
//		System.out.println("fileUI.getToolBar()="+fileUI.getToolBar());
//		System.out.println("historyToolbar="+historyToolbar);
//		System.out.println("editorToolbar="+editorToolbar);
		// controlPanel.add(splitButtons.getRightButton()); more trouble than it was worth
	}

	private static final int FRAME_X_INSET = 75;
	private static final int FRAME_Y_INSET = 20;
	private static final int IDEAL_WIDTH = 1200;
	private static final int IDEAL_HEIGHT = 600;	
	
	private void makeFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int maxWidth = screenSize.width-2*FRAME_X_INSET;
		int maxHeight = screenSize.height-2*FRAME_Y_INSET;
		int width = maxWidth < IDEAL_WIDTH ? maxWidth : IDEAL_WIDTH;
		int height = maxHeight < IDEAL_HEIGHT ? maxHeight : IDEAL_HEIGHT;
		frame.setBounds(FRAME_X_INSET, FRAME_Y_INSET, width, height);

		frame.setIconImage(ScuteIcons.applicationIcon.getImage());
		frame.addWindowListener(autoSave);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuBar = new JMenuBar();
		menuBar.add(fileUI.getFileMenu());
		menuBar.add(editorToolbar.getMenu());
		menuBar.add(helpUI.getHelpMenu());
menuBar.setVisible(false);
		frame.setJMenuBar(menuBar);

		frame.setContentPane(panel);
		// frame.pack();

		frame.setVisible(true);
	}

	/**
	 * 
	 */
	private void makeCardsPanel() {
		cardsPanel = new CardsPanel();
		makeImageCard();
		makeTurtleCard();
		makeRdfXmlCard();
		makeTreeCard();
		makeGraphCard();
		makeTriplesCard();
		makeSparqlCard();
		makeGraphManagerCard();
		makeFileExplorerPanel();
		makeLogCard();
		makeSystemCard();
		
		cardsPanel.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));
		cardsPanel.add(editorToolbar, BorderLayout.SOUTH);
	}

	/**
	 * 
	 */
	private void makeImageCard() {
		imageCard = CardFactory.createCard(Card.IMAGE);
			((ImageCard)imageCard).setScute(this); 
			//new ImageCard(this);
		
		cardsPanel.addPlain(imageCard, "Image");
	}

	/**
	 * 
	 */
	private void makeSystemCard() {
		settingsCard = CardFactory.createCard(Card.SETTINGS);
			// new SystemCard();
		// systemPanel.addUserActivityListener(autoSave);

		cardsPanel.addScroll(settingsCard, "Settings");
	}

	/**
	 * 
	 */
	private void makeLogCard() {
		LogPane log = LogPane.getLogPane();
		// JScrollPane logScroll = new JScrollPane(log);
		// logScroll.setBorder(BorderFactory.createLoweredBevelBorder());
		LogPane.println("Ok.");
		// TitledBorder logBorder = BorderFactory.createTitledBorder("Log");
		// logScroll.setBorder(logBorder);
		// cardsPanel.add(logScroll, "Log");
		Card logCard = CardFactory.createCard(Card.DEFAULT);
		logCard.add(log);
		cardsPanel.addScroll(logCard, "Log");
	}

	/**
	 * 
	 */
	private void makeFileExplorerPanel() {
		fileExplorerCard = CardFactory.createCard(Card.FILE_EXPLORER);
			// new FileExplorerCard(Config.DATA_DIR);
		fileExplorerCard.addFocusListener(focusMonitor);
		cardsPanel.add(fileExplorerCard, "Files");
	}

	/**
	 * 
	 */
	private void makeGraphManagerCard() {
		graphManagerCard = CardFactory.createCard(Card.GRAPH_MANAGER);
			// new GraphManagerCard();
		graphManagerCard.addFocusListener(focusMonitor);
		cardsPanel.add(graphManagerCard, "Graphs");
	}

	/**
	 * 
	 */
	private void makeSparqlCard() {
		sparqlCard = CardFactory.createCard(Card.SPARQL);
			// new SparqlCard(frame, focusMonitor);
		((SparqlCard)sparqlCard).setFrame(frame);
		((SparqlCard)sparqlCard).addFocusMonitor(focusMonitor);
		
		sparqlCard.setTextCard(true);
		cardsPanel.add(sparqlCard, "SPARQL");
	}

	/**
	 * 
	 */
	private void makeTriplesCard() {
		triplesCard = CardFactory.createCard(Card.TRIPLES);
			//new TriplesCard(Models.workingModel);
		triplesCard.addFocusListener(focusMonitor);
		// triplesPanel.addUserActivityListener(autoSave);
		// TODO create UserActivityListener interface
		// need change listener???
		// TODO ADD SELECTION LISTENER - make shared listener?
		cardsPanel.add(triplesCard, "Triples");
	}

	/**
	 * 
	 */
	private void makeTreeCard() {
		treeCard = CardFactory.createCard(Card.TREE);
			// new RdfTreeCard(Models.workingModel);
		treeCard.addUserActivityListener(autoSave);
		treeCard.addFocusListener(focusMonitor);
		// need change listener???
		cardsPanel.add(treeCard, "Tree");
	}

	private void makeGraphCard() {
		graphCard = CardFactory.createCard(Card.GRAPH);
			// new GraphCard(Models.workingModel);
		graphCard.addUserActivityListener(autoSave);
		graphCard.addFocusListener(focusMonitor);
		// need change listener???
		cardsPanel.add(graphCard, "Graph");
	}

	/**
	 * 
	 */
	private void makeRdfXmlCard() {
		rdfxmlPanel = new RdfSourcePanel("RDF/XML");
		rdfxmlPanel.setFilename(Config.RDFXML_TEMP);
		rdfxmlPanel.addUserActivityListener(autoSave);
		// rdfxmlPanel.loadModel(Models.workingModel);
		rdfxmlPanel.load();
		rdfxmlPanel.setEditorKit(new ScuteEditorKit("XML"));
		rdfxmlPanel.addFocusListener(focusMonitor);

		JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEADING)); // left-aligned
		statusPanel.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED));

		// add to save-on-shutdown list
		autoSave.addSaveable(rdfxmlPanel);
		
		// set up autosave button
		StatusAction autosaveAction = new AutoSaveAction();
		StatusButton autosaveButton = new StatusButton(autosaveAction,
				"Unsaved", "Saving...", "Saved");
		statusPanel.add(autosaveButton);

		// Set up validators
		Document rdfxmlDocument = rdfxmlPanel.getDocument();
		StatusAction rdfxmlAction = new RdfXmlValidateAction(rdfxmlDocument);
		StatusInfoPane validatorPane = new StatusInfoPane(rdfxmlAction);

		// Set up validator button
		StatusButton validatorButton = new StatusButton(rdfxmlAction,
				"Invalid syntax", "Checking syntax...", "Valid syntax");

		statusPanel.add(validatorButton);
		statusPanel.add(validatorPane);

		cardsPanel.addChangeListener(rdfxmlPanel);

		Card rdfxmlCard = CardFactory.createCard(Card.RDFXML);
		rdfxmlCard.setTextCard(true);
		rdfxmlCard.setTextContainer(rdfxmlPanel);
		rdfxmlCard.add(new JScrollPane(rdfxmlPanel), BorderLayout.CENTER);
		rdfxmlCard.add(statusPanel, BorderLayout.SOUTH);

		cardsPanel.add(rdfxmlCard, "RDF/XML");
	}

	/**
	 * 
	 */
	private void makeTurtleCard() {
		turtlePanel = new RdfSourcePanel("Turtle");

		turtlePanel.setFilename(Config.TURTLE_TEMP);
		turtlePanel.addUserActivityListener(autoSave);
		turtlePanel.setEditorKit(new ScuteEditorKit("Turtle"));
		turtlePanel.loadModel(getModel());
		turtlePanel.load();
		turtlePanel.addFocusListener(focusMonitor);
		
		SourcePopupMenu popupMenu = new SourcePopupMenu(turtlePanel);
		PopupListener popupListener = new PopupListener(popupMenu);
		turtlePanel.addMouseListener(popupListener);

		cardsPanel.addChangeListener(turtlePanel);
		// add to save-on-shutdown list
		autoSave.addSaveable(turtlePanel);
		
		statusBar = new JPanel(new FlowLayout(FlowLayout.LEADING)); // left-aligned
		statusBar.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED));
		// set up autosave button
		StatusAction autosaveAction = new AutoSaveAction();
		StatusButton autosaveButton = new StatusButton(autosaveAction,
				"Unsaved", "Saving...", "Saved");
		statusBar.add(autosaveButton);

		// Set up validators
		Document turtleDocument = turtlePanel.getDocument();
		StatusAction turtleAction = new TurtleValidateAction(turtleDocument);
		StatusInfoPane validatorPane = new StatusInfoPane(turtleAction);

		// Set up validator button
		StatusButton validatorButton = new StatusButton(turtleAction,
				"Invalid syntax", "Checking syntax...", "Valid syntax");

		statusBar.add(validatorButton);
		statusBar.add(validatorPane);
		
		
		Card turtleCard = CardFactory.createCard(Card.TURTLE);
		turtleCard.setTextCard(true);
		turtleCard.setTextContainer(turtlePanel);
		turtleCard.add(new JScrollPane(turtlePanel), BorderLayout.CENTER);
		turtleCard.add(statusBar, BorderLayout.SOUTH);

		cardsPanel.add(turtleCard, "Turtle");
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
		final Object object = ((RdfTreeCard)treeCard).getTree()
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
			normalTreeCursor = treeCard.getCursor();
			frame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			treeCard.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		} else {
			frame.setCursor(normalCursor);
			treeCard.setCursor(normalTreeCursor);
			frame.setCursor(normalCursor);
			treeCard.setCursor(normalTreeCursor);
		}
	}

	// is needed?
	public static void setSystemLookFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception exception) {
			Log.exception(exception);
		}
	}

	/**
	 * @param selectedView
	 */
	public void setSelectedCard(String selectedView) {
		cardsPanel.setCurrentCard(selectedView);
	}

	/**
	 * 
	 */
	public void setDefaultSplit() {
		splitScreen.setDefaults();
	}

	/**
	 * @param b
	 */
	public void showTools(boolean b) {
		toolsPanel.setVisible(b);
		menuBar.setVisible(b);
		// 
	}

	/**
	 * @param b
	 */
	public void showStatusBar(boolean b) {
		statusBar.setVisible(b);
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.main.ScuteIF#showTabs(boolean)
	 */
	@Override
	public void showTabs(boolean b) {
		if(b){
			cardsPanelTabs.setUI(tabUI);
		} else {
			cardsPanelTabs.setUI(new BasicTabbedPaneUI() {  
	            @Override  
	            protected int calculateTabAreaHeight(int tabPlacement, int horizRunCount, int maxTabHeight) {  
	                return 0;
	            }  
	        });  
	//	cardsPanelTabs.setUI(new javax.swing.plaf.metal.MetalTabbedPaneUI(){  
		//      protected void paintTabArea(Graphics g,int tabPlacement,int selectedIndex){}  
		  //  });  
	}
	}
}
