/**
 * 
 */
package org.hyperdata.scute.sparql.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.EventObject;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.text.*;

import org.hyperdata.scute.cards.Card;
import org.hyperdata.scute.sparql.SparqlContainer;
import org.hyperdata.scute.sparql.SparqlContainerImpl;
import org.hyperdata.scute.sparql.actions.RunQueryAction;
import org.hyperdata.scute.swing.status.StatusAction;
import org.hyperdata.scute.swing.status.StatusButton;
import org.hyperdata.scute.syntax.*;

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

	public SparqlCard(Frame frame) {
		super(new BorderLayout());

		sourcePanel = new SparqlSourcePanel("SPARQL");
		editorKit = new ScuteEditorKit("SPARQL"); /////////////////////////
// editorKit.setSyntax("SPARQL");
		sourcePanel.setEditorKit(editorKit);

		String text = "SELECT ?s ?p ?o WHERE {\n   ?s ?p ?o \n}\nLIMIT 10";
		sourcePanel.setText(text);

		resultsPanel = new SparqlResultsPanel();
		sparqlContainer.addSparqlListener(resultsPanel);

		// passing sourcepanel here a bit messy, but will do for now
		SparqlRunToolbar toolbar = new SparqlRunToolbar(sparqlContainer,
				sourcePanel, frame);
		add(toolbar, BorderLayout.NORTH);
		
		JButton zoomIn = new JButton(sourcePanel.getZoomInAction());
		toolbar.add(zoomIn);
		
		JButton zoomOut = new JButton(sourcePanel.getZoomOutAction());
		toolbar.add(zoomOut);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				sourcePanel, resultsPanel);
		splitPane.setContinuousLayout(true);
		add(splitPane, BorderLayout.CENTER); 
		splitPane.setDividerLocation(0.5);

		JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEADING)); // left-aligned
		statusPanel.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED));

		// set up autosave button
		StatusAction runQueryAction = new RunQueryAction(text, sparqlContainer,
				sourcePanel);
		StatusButton runQueryButton = new StatusButton(runQueryAction, "Ready",
				"Running...", "Done");
		statusPanel.add(runQueryButton);

		add(statusPanel, BorderLayout.SOUTH);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final JFrame frame = new JFrame();
		SparqlCard sparqlPanel = new SparqlCard(frame);
		frame.setSize(800, 600);
		frame.add(sparqlPanel);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		// frame.pack();
		frame.setVisible(true);
	}

	// delegate to SparqlContainer

	// public String getQueryString() {
	// return sparqlContainer.getQueryString();
	// }
	//
	// public Dataset getDataset() {
	// return sparqlContainer.getDataset();
	// }
	//
	// public boolean isLocal() {
	// return sparqlContainer.isLocal();
	// }
	//
	// public Endpoint getEndpoint() {
	// return sparqlContainer.getEndpoint();
	// }
	//
	// public void setEndpoint(Endpoint endpoint) {
	// sparqlContainer.setEndpoint(endpoint);
	// }
	//
	// public void setResultsText(String resultsString) {
	// sparqlContainer.setResultsText(resultsString);
	// }
	//
	//
	// public String getResultsText() {
	// return sparqlContainer.getResultsText();
	// }
	//
	// public void addSparqlListener(SparqlListener sparqlListener) {
	// sparqlContainer.addSparqlListener(sparqlListener);
	// }
	//
	// public void fireSparqlEvent() {
	// sparqlContainer.fireSparqlEvent();
	// }
}
