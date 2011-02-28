/**
 * 
 */
package org.hyperdata.scute.sparql.panels;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import org.hyperdata.scute.cards.Card;
import org.hyperdata.scute.sparql.SparqlContainer;
import org.hyperdata.scute.sparql.SparqlContainerImpl;
import org.hyperdata.scute.syntax.HighlighterEditorKit;

/**
 * @author danny
 * 
 * TODO make SPARQL SELECT/CONSTRUCT/DESCRIBE?/ASK? template from current working model
 *
 */
public class SparqlPanel extends Card  { // implements SparqlContainer

	private SparqlSourcePanel sourcePanel;
	private SparqlResultsPanel resultsPanel;
	private SparqlContainer sparqlContainer = new SparqlContainerImpl();
	
	public SparqlPanel(){
		super(new BorderLayout());
		
		sourcePanel = new SparqlSourcePanel("SPARQL");
		sourcePanel.setEditorKit(new HighlighterEditorKit("SPARQL"));
		
		String text = "SELECT ?s ?p ?o WHERE {\n   ?s ?p ?o \n}\nLIMIT 10";
		sourcePanel.setText(text);
		
		resultsPanel = new SparqlResultsPanel();
		sparqlContainer.addSparqlListener(resultsPanel);
		
		System.out.println("QQQQ="+sourcePanel.getText());
		// adding sourcepanel here a bit messy, but will do for now
		SparqlToolbar toolbar = new SparqlToolbar(sparqlContainer, sourcePanel); 
		add(toolbar, BorderLayout.NORTH);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sourcePanel, resultsPanel);
		splitPane.setContinuousLayout(true);
		add(splitPane, BorderLayout.CENTER); 
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SparqlPanel sparqlPanel = new SparqlPanel();
		final JFrame frame = new JFrame();
		frame.setSize(800,600);
		frame.add(sparqlPanel);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	//	frame.pack();
		frame.setVisible(true);
	}

	// delegate to SparqlContainer
	
//	public String getQueryString() {
//		return sparqlContainer.getQueryString();
//	}
//
//	public Dataset getDataset() {
//		return sparqlContainer.getDataset();
//	}
//	
//	public boolean isLocal() {
//		return sparqlContainer.isLocal();
//	}
//
//	public Endpoint getEndpoint() {
//		return sparqlContainer.getEndpoint();
//	}
//
//	public void setEndpoint(Endpoint endpoint) {
//		sparqlContainer.setEndpoint(endpoint);
//	}
//	
//	public void setResultsText(String resultsString) {
//		sparqlContainer.setResultsText(resultsString);
//	}
//	
//
//	public String getResultsText() {
//		return sparqlContainer.getResultsText();
//	}
//
//	public void addSparqlListener(SparqlListener sparqlListener) {
//		sparqlContainer.addSparqlListener(sparqlListener);
//	}
//
//	public void fireSparqlEvent() {
//		sparqlContainer.fireSparqlEvent();
//	}
}
