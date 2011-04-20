/**
 * 
 */
package org.hyperdata.scute.sparql.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.EventObject;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.text.*;

import org.jdesktop.swingx.JXMultiSplitPane;
import org.jdesktop.swingx.MultiSplitLayout;
import org.jdesktop.swingx.MultiSplitLayout.Divider;
import org.jdesktop.swingx.MultiSplitLayout.Leaf;
import org.jdesktop.swingx.MultiSplitLayout.Split;

import org.hyperdata.resources.scute.ScuteIcons;
import org.hyperdata.scute.cards.Card;
import org.hyperdata.scute.main.FocusMonitor;
import org.hyperdata.scute.source.popup.PopupListener;
import org.hyperdata.scute.source.popup.SourcePopupMenu;
import org.hyperdata.scute.sparql.SparqlContainer;
import org.hyperdata.scute.sparql.SparqlContainerImpl;
import org.hyperdata.scute.sparql.actions.RunQueryAction;
import org.hyperdata.scute.status.StatusAction;
import org.hyperdata.scute.status.StatusButton;
import org.hyperdata.scute.status.StatusEvent;
import org.hyperdata.scute.status.StatusInfoPane;
import org.hyperdata.scute.status.StatusMonitor;
import org.hyperdata.scute.syntax.*;
import org.hyperdata.scute.toolbars.source.EditorToolbar;
import org.hyperdata.scute.toolbars.source.FindAction;
import org.hyperdata.scute.validate.RdfXmlValidateAction;
import org.hyperdata.scute.validate.SparqlValidateAction;

/**
 * @author danny
 * 
 *         TODO make SPARQL SELECT/CONSTRUCT/DESCRIBE?/ASK? template from
 *         current working model
 * 
 */
public class SparqlCard extends Card { // implements SparqlContainer

	private SparqlSourcePanel sourcePanel;
	private SparqlResultsPanel resultsPanel;
	private SparqlContainer sparqlContainer = new SparqlContainerImpl();
	private Frame frame;
	private ScuteEditorKit editorKit;
	private SourcePopupMenu popupMenu;

	public SparqlCard() {
		super(new BorderLayout());

		sourcePanel = new SparqlSourcePanel("SPARQL");
		editorKit = new ScuteEditorKit("SPARQL"); // ///////////////////////
		// editorKit.setSyntax("SPARQL");
		sourcePanel.setEditorKit(editorKit);


		popupMenu = new SourcePopupMenu(sourcePanel);
		PopupListener popupListener = new PopupListener(popupMenu);
		sourcePanel.addMouseListener(popupListener);

		sourcePanel.loadSoon();

		resultsPanel = new SparqlResultsPanel();

		sparqlContainer.addSparqlListener(resultsPanel);

		// there's an awful lot of this...
		Leaf leftLeaf = new Leaf("left"); // actually upper & lower
		leftLeaf.setWeight(0.5);
		Leaf rightLeaf = new Leaf("right");
		rightLeaf.setWeight(0.5);
		
		List children = 
		    Arrays.asList(leftLeaf,
		       new Divider(), 
		       rightLeaf);
		Split splitModel = new Split();
		splitModel.setChildren(children);
		splitModel.setRowLayout(false); // column

		MultiSplitLayout multiSplitLayout = new MultiSplitLayout();
		multiSplitLayout.setLayoutMode(MultiSplitLayout.NO_MIN_SIZE_LAYOUT);
		JXMultiSplitPane multiSplitPane = new JXMultiSplitPane(multiSplitLayout);
		multiSplitPane.getMultiSplitLayout().setModel(splitModel);
		multiSplitPane.add(new JScrollPane(sourcePanel), "left");
		multiSplitPane.add(resultsPanel, "right");
		add(multiSplitPane, BorderLayout.CENTER);

		// need to set up autosave button

		// add time taken status?

		// Set up validator
		Document sparqlDocument = sourcePanel.getDocument();
		SparqlValidateAction sparqlValidateAction = new SparqlValidateAction(sparqlDocument);
		
		// passing sourcepanel here a bit messy, but will do for now
		SparqlRunToolbar runToolbar = new SparqlRunToolbar(sparqlContainer, sourcePanel, sparqlValidateAction.getValidator(), frame);
		add(runToolbar, BorderLayout.NORTH);
		
		SparqlStatusPanel statusPanel = new SparqlStatusPanel(sparqlValidateAction); 
		runToolbar.addStatusChangeListener(statusPanel);
		add(statusPanel, BorderLayout.SOUTH);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		final JFrame frame = new JFrame();
//		SparqlCard sparqlPanel = new SparqlCard(frame, null);
//		frame.setSize(800, 600);
//		frame.add(sparqlPanel);
//		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//		// frame.pack();
//		frame.setVisible(true);
	}

	/**
	 * @param frame2
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
		
	}

	/**
	 * @param focusMonitor
	 */
	public void addFocusMonitor(FocusMonitor focusMonitor) {
		sourcePanel.addFocusListener(focusMonitor);
		resultsPanel.addFocusListener(focusMonitor);
		
	}
}
