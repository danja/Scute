/**
 * 
 */
package org.hyperdata.scute.sparql.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.sparql.resultset.SPARQLResult;

import org.hyperdata.scute.rdf.RdfUtils;
import org.hyperdata.scute.sparql.SparqlContainer;
import org.hyperdata.scute.sparql.SparqlEvent;
import org.hyperdata.scute.sparql.SparqlListener;

/**
 * @author danny
 *
 */
public class SparqlResultsPanel extends JPanel implements SparqlListener {
	
	TextResultsPanel textView;
	TableResultsPanel tableView;
	HTTPPanel httpView;

	public SparqlResultsPanel(){
		super(new BorderLayout());
		
		textView = new TextResultsPanel ();
		tableView = new TableResultsPanel();
		httpView = new HTTPPanel();
		
		JTabbedPane tabs = new JTabbedPane(JTabbedPane.BOTTOM);
		tabs.addTab("Text", textView);
		tabs.addTab("Table", tableView);
		tabs.addTab("HTTP", httpView);
		
		add(tabs, BorderLayout.CENTER);
	}
	
	public void populate(String resultString){
		textView.setText(resultString);
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
		textView.setText(resultString);
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
