/**
 * 
 */
package org.hyperdata.scute.sparql.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.sparql.resultset.SPARQLResult;

import org.hyperdata.scute.rdf.RdfUtils;
import org.hyperdata.scute.sparql.SparqlContainer;
import org.hyperdata.scute.sparql.SparqlEvent;
import org.hyperdata.scute.sparql.SparqlListener;
import org.hyperdata.scute.syntax.ScuteEditorKit;

/**
 * @author danny
 *
 */
public class SparqlResultsPanel extends JPanel implements SparqlListener {
	
	XMLResultsPanel xmlPanel;
	TableResultsPanel tablePanel;
	HTTPPanel httpPanel;

	public SparqlResultsPanel(){
		super(new BorderLayout());
		
		xmlPanel = new XMLResultsPanel ();
		xmlPanel.setEditorKit(new ScuteEditorKit("XML"));
		tablePanel = new TableResultsPanel();
		httpPanel = new HTTPPanel();
		
		JTabbedPane tabs = new JTabbedPane(SwingConstants.BOTTOM);
		tabs.addTab("Text", new JScrollPane(xmlPanel));
		tabs.addTab("Table", new JScrollPane(tablePanel));
		tabs.addTab("HTTP", new JScrollPane(httpPanel));
		
		add(tabs, BorderLayout.CENTER);
	}
	
	public void populate(String resultString){
		xmlPanel.setText(resultString);
	}
	
	public void populate(SPARQLResult result){
		String resultString = "...";
		if(result.isBoolean()){
			resultString = Boolean.toString(result.getBooleanResult());
		}
		if(result.isModel()){
			resultString = RdfUtils.modelToString(result.getModel());
		}
		if(result.isResultSet()){
//			 ResultSetFormatter fmt = new ResultSetFormatter(result.getResultSet(), query) ;
//			    fmt.printAll(System.out) ;
			resultString = ResultSetFormatter.asText(result.getResultSet());
		}
		xmlPanel.setText(resultString);
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.sparql.SparqlListener#sparqlEvent(org.hyperdata.scute.sparql.SparqlEvent)
	 */
	@Override
	public void sparqlEvent(SparqlEvent sparqlEvent) {
		SparqlContainer sparqlContainer = (SparqlContainer)sparqlEvent.getSource();
		
		// needed for other kinds of results..?
		populate(sparqlContainer.getResultsText());
		
	}
}
