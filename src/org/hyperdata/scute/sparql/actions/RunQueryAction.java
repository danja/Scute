/**
 * 
 */
package org.hyperdata.scute.sparql.actions;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

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
import org.hyperdata.scute.status.StatusAction;
import org.hyperdata.scute.status.StatusEvent;
import org.hyperdata.scute.status.StatusMonitor;
import org.hyperdata.scute.system.Log;
import org.hyperdata.scute.validate.Validator;

/**
 * @author danny
 * 
 */
public class RunQueryAction extends StatusAction {

	private SparqlContainer sparqlContainer;
	// private SparqlHttp http;
	private SparqlHttp sparqlHttp;
	private SparqlSourcePanel sourcePanel;
	private Validator validator;
	private Frame frame;

	/**
	 * @param frame 
	 * @param string
	 *            label
	 * @param sourcePanel
	 * @param validator
	 */
	public RunQueryAction(Frame frame, String string, SparqlContainer sparqlContainer,
			SparqlSourcePanel sourcePanel, Validator validator) {
		super(string);
		this.frame = frame;
		this.sparqlContainer = sparqlContainer;
		this.sourcePanel = sourcePanel;
		this.validator = validator;
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
		StatusEvent statusEvent = validator.validate();
		if (statusEvent.getStatus() != StatusMonitor.GREEN) {
			JOptionPane.showMessageDialog(frame, statusEvent.getDescription(), "Syntax Error", JOptionPane.WARNING_MESSAGE);
			return;
		}
		sparqlContainer.setQueryString(sourcePanel.getText());

		if (sparqlContainer.isLocal()) {
			// TODO IMPLEMENTS!!!
			SPARQLResult result = runQuery(sparqlContainer.getQueryString());
		} else {
			if (!sparqlHttp.isRunning()) {
				runRemoteQuery(event);
			} else {
				stop();
			}
		}
	}

	/**
	 * 
	 */
	private void runRemoteQuery(ActionEvent event) {

		System.out.println("ENDPOINT RQA="+sparqlContainer.getEndpoint());
		if (sparqlContainer.getEndpoint().getUri() == null) { // shouldn't get
																// this far...
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
			Log.exception(exception);
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
