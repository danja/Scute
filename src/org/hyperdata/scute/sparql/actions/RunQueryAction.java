/**
 * 
 */
package org.hyperdata.scute.sparql.actions;

import java.awt.event.ActionEvent;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryException;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.sparql.resultset.SPARQLResult;

import org.hyperdata.scute.main.Config;
import org.hyperdata.scute.sparql.SparqlContainer;
import org.hyperdata.scute.sparql.SparqlHttp;
import org.hyperdata.scute.sparql.panels.SparqlSourcePanel;
import org.hyperdata.scute.swing.status.StatusAction;

/**
 * @author danny
 * 
 */
public class RunQueryAction extends StatusAction {

	private SparqlContainer sparqlContainer;
	// private SparqlHttp http;
	private SparqlHttp sparqlHttp;
	private SparqlSourcePanel sourcePanel;

	/**
	 * @param string
	 *            label
	 * @param sourcePanel 
	 */
	public RunQueryAction(String string, SparqlContainer sparqlContainer, SparqlSourcePanel sourcePanel) {
		super(string);
		this.sparqlContainer = sparqlContainer;
		this.sourcePanel = sourcePanel;
		sparqlHttp = new SparqlHttp();
		setStatusTask(sparqlHttp); 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {

		sparqlContainer.setQueryString(sourcePanel.getText());

		if (sparqlContainer.isLocal()) {
			// TODO IMPLEMENTS!!!
			SPARQLResult result = runQuery(sparqlContainer.getQueryString());
		} else {
			if(!sparqlHttp.isRunning()){
			runRemoteQuery(event);
			}else {
				stop();
				
			}
		}
	}

	/**
	 * 
	 */
	private void runRemoteQuery(ActionEvent event) {

		// System.out.println("ENDPOINT RQA="+sparqlContainer.getEndpoint());
		if(sparqlContainer.getEndpoint().getUri() == null){ // shouldn't get this far...
			return;
		}
		sparqlHttp.init(sparqlContainer);
		super.actionPerformed(event);
		// httpRunner = new Thread(http);
		// httpRunner.start();
		// sparqlContainer.setResultsText(resultsString);
	}

	public SPARQLResult runQuery(String queryString) {
		Query query = null;
		try {
			query = QueryFactory.create(sparqlContainer.getQueryString(),
					Config.SPARQL_SYNTAX);
		} catch (QueryException exception) {
			// TODO popup error
			exception.printStackTrace();
		}
		SPARQLResult result = null;
		Dataset dataset = sparqlContainer.getDataset();
		QueryExecution qexec = QueryExecutionFactory.create(query, dataset);

		if (query.isSelectType()) {
			ResultSet rs = qexec.execSelect();
			// rs = ResultSetFactory.copyResults(rs) ;
			result = new SPARQLResult(rs);
		}

		if (query.isConstructType()) {
			Model model = qexec.execConstruct();
			result = new SPARQLResult(model);
		}

		if (query.isDescribeType()) {
			Model model = qexec.execDescribe();
			result = new SPARQLResult(model);
		}

		if (query.isAskType()) {
			boolean b = qexec.execAsk();
			result = new SPARQLResult(b);
		}

		return result;
	}
}
