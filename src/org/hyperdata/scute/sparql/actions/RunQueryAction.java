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
import org.hyperdata.scute.main.Scute;
import org.hyperdata.scute.sparql.SparqlContainer;
import org.hyperdata.scute.sparql.SparqlHttp;
import org.hyperdata.scute.sparql.panels.SparqlRunToolbar;
import org.hyperdata.scute.sparql.panels.SparqlSourcePanel;
import org.hyperdata.scute.status.DummyTask;
import org.hyperdata.scute.status.StatusAction;
import org.hyperdata.scute.status.StatusEvent;
import org.hyperdata.scute.status.StatusMonitor;
import org.hyperdata.scute.system.Log;
import org.hyperdata.scute.validate.Validator;

/**
 * @author danny
 * 
 *         TODO refactor! implement local
 */
public class RunQueryAction extends StatusAction {

	// private SparqlContainer sparqlContainer;
	private SparqlHttp sparqlHttp;
	// private SparqlSourcePanel sourcePanel;
	// private Validator validator;
	// private Frame frame;
	private SparqlRunToolbar sparqlRunToolbar;

	/**
	 * @param frame
	 * @param string
	 *            label
	 * @param sourcePanel
	 * @param validator
	 */
	// public RunQueryAction(Frame frame, String string, SparqlContainer
	// sparqlContainer,
	// SparqlSourcePanel sourcePanel, Validator validator) {
	// super(string);
	// this.frame = frame;
	// this.sparqlContainer = sparqlContainer;
	// this.sourcePanel = sourcePanel;
	// this.validator = validator;
	// sparqlHttp = new SparqlHttp();
	// setStatusTask(sparqlHttp);
	// }

	/**
	 * @param sparqlRunToolbar
	 */
	public RunQueryAction(SparqlRunToolbar sparqlRunToolbar) {
		super("Run");
		this.sparqlRunToolbar = sparqlRunToolbar;
		setStatusTask(new DummyTask());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		StatusEvent statusEvent = sparqlRunToolbar.getValidator().validate();
		if (statusEvent.getStatus() != StatusMonitor.GREEN) {
			JOptionPane.showMessageDialog(sparqlRunToolbar.getFrame(),
					statusEvent.getDescription(), "Syntax Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		sparqlRunToolbar.initSparqlContainer();
		// sparqlContainer.setQueryString(sourcePanel.getText());

		// sparqlContainer.setEndpoint(sourcePanel.get)

		if (sparqlRunToolbar.getSparqlContainer().isLocal()) {
			runLocalQuery(event);
		} else {
			runRemoteQuery(event);
		}
	}

	/**
	 * 
	 */
	private void runRemoteQuery(ActionEvent event) {
		System.out.println("runRemoteQuery sparqlHttp="+sparqlHttp);
		if (sparqlHttp != null){
		System.out.println("runRemoteQuery sparqlHttp.isRunning()="+sparqlHttp.isRunning());
		}
		if (sparqlHttp != null && sparqlHttp.isRunning()) {
			stop();
			return;
		}
		if (sparqlHttp == null) {
			sparqlHttp = new SparqlHttp();
		}
		setStatusTask(sparqlHttp);
		System.out.println("ENDPOINT RQA="
				+ sparqlRunToolbar.getSparqlContainer().getEndpoint());
		if (sparqlRunToolbar.getSparqlContainer().getEndpoint().getUri() == null) { // shouldn't
																					// get
			// this far...
			return;
		}
		sparqlHttp.init(sparqlRunToolbar.getSparqlContainer());
		// sparqlHttp.addStatusListener(Scute.animatedCursor);
		// sparqlRunToolbar.setCursor(null);
		// setAnimateCursor(true);
		super.actionPerformed(event);
	}

	/**
	 * @param event
	 */
	private void runLocalQuery(ActionEvent event) {
		// TODO shift this to a Runnable
		// TODO check if is running, stop

		// TODO IMPLEMENTS!!!
		// SPARQLResult result =
		// runQuery(sparqlRunToolbar.getSparqlContainer().getQueryString());

		Query query = null;
		try {
			query = QueryFactory.create(sparqlRunToolbar.getSparqlContainer()
					.getQueryString(), Config.SPARQL_SYNTAX);
		} catch (QueryException exception) {
			Log.exception(exception);
		}
		SPARQLResult result = null;
		Dataset dataset = sparqlRunToolbar.getSparqlContainer().getDataset();
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

		// do something with result;
	}
}
