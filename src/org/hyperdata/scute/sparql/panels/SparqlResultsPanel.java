/**
 * 
 */
package org.hyperdata.scute.sparql.panels;

import java.awt.BorderLayout;
import java.awt.event.FocusListener;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFactory;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.sparql.resultset.SPARQLResult;

import org.hyperdata.scute.rdf.RdfUtils;
import org.hyperdata.scute.sparql.SparqlContainer;
import org.hyperdata.scute.sparql.SparqlEvent;
import org.hyperdata.scute.sparql.SparqlListener;
import org.hyperdata.scute.sparql.table.TableResultsPane;
import org.hyperdata.scute.syntax.ScuteEditorKit;
import org.hyperdata.scute.system.Log;

/**
 * @author danny
 * 
 */
public class SparqlResultsPanel extends JPanel implements SparqlListener {

	private TextResultsPanel textPane;
	private XMLResultsPanel xmlPane;
	// private BetterJTable table = new BetterJTable();
	private TableResultsPane tablePane;
	private HTTPPanel httpPane;

	public SparqlResultsPanel(FocusListener focusListener) {
		super(new BorderLayout());

		textPane = new TextResultsPanel();
		textPane.addFocusListener(focusListener);
		textPane.setEditorKit(new ScuteEditorKit("Turtle")); // for CONSTRUCTed
																// results

		xmlPane = new XMLResultsPanel();
		xmlPane.addFocusListener(focusListener);
		xmlPane.setEditorKit(new ScuteEditorKit("XML"));

		tablePane = new TableResultsPane();
		tablePane.addFocusListener(focusListener);

		httpPane = new HTTPPanel();
		httpPane.addFocusListener(focusListener);

		JTabbedPane tabs = new JTabbedPane(SwingConstants.BOTTOM);
		tabs.addTab("Text", new JScrollPane(textPane));
		tabs.addTab("Table", tablePane); // it has its own scroll
		tabs.addTab("XML", new JScrollPane(xmlPane));
		tabs.addTab("HTTP", new JScrollPane(httpPane));

		add(tabs, BorderLayout.CENTER);
	}

	public void populate(String resultString) {
		try {
			xmlPane.setText(resultString);
		} catch (Error error) {
			System.err.println("SparqlResultsPanel: " + error.getMessage());
			// ignore - probably Interrupted attempt to acquire write lock
		}
	}

	/**
	 * @param resultSet
	 */
	private void populate(ResultSet results) {
		ResultSet resultSet = ResultSetFactory.copyResults(results);

		tablePane.setResults(resultSet);
		textPane.setText(ResultSetFormatter.asText(resultSet));
		xmlPane.setText(ResultSetFormatter.asXMLString(resultSet));
	}

	public void populate(SPARQLResult result) {

		String resultString = "...";
		if (result.isBoolean()) {
			resultString = Boolean.toString(result.getBooleanResult());
			textPane.setText(resultString);
		}
		if (result.isModel()) {
			resultString = RdfUtils.modelToString(result.getModel());
			textPane.setText(resultString); // will do for now
		}
		if (result.isResultSet()) {
			populate(result.getResultSet());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.hyperdata.scute.sparql.SparqlListener#sparqlEvent(org.hyperdata.scute
	 * .sparql.SparqlEvent)
	 */
	@Override
	public void sparqlEvent(SparqlEvent sparqlEvent) {
		SparqlContainer sparqlContainer = (SparqlContainer) sparqlEvent
				.getSource();
		if (sparqlContainer.getResultsText() == null) {
			clearViews();
		}
		populate(sparqlContainer.getResultsText());
		populate(sparqlContainer.getResultSet());
		populateHTTPPane(sparqlContainer.getHTTPText());
	}

	/**
	 * 
	 */
	private void clearViews() {
		tablePane.clear();
		httpPane.setText("");
		xmlPane.setText("");
		textPane.setText("");
	}

	/**
	 * @param httpText
	 */
	private void populateHTTPPane(String httpText) {
		httpPane.setText(httpText);
	}

	/**
	 * @return
	 */
	public JEditorPane getXmlPane() {
		return xmlPane;
	}
}
