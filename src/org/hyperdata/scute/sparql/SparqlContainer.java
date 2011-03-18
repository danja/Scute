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
package org.hyperdata.scute.sparql;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.sparql.resultset.SPARQLResult;

import org.hyperdata.scute.sparql.endpoints.Endpoint;


/**
 * The Interface SparqlContainer.
 * 
 *  * changed it to include a Query object - may cause inconsistencies
 */
public interface SparqlContainer {
	
	/**
	 * Sets the query.
	 *
	 * @param query the new query
	 */
	public void setQuery(Query query);
	
	/**
	 * Gets the query.
	 *
	 * @return the query
	 */
	public Query getQuery();
	
	/**
	 * Gets the query string.
	 *
	 * @return the query string
	 */
	public String getQueryString();

	/**
	 * Gets the dataset.
	 *
	 * @return the dataset
	 */
	public Dataset getDataset();

	/**
	 * Gets the endpoint.
	 *
	 * @return the endpoint
	 */
	public Endpoint getEndpoint();
	
	/**
	 * Sets the endpoint.
	 *
	 * @param endpoint the new endpoint
	 */
	public void setEndpoint(Endpoint endpoint);
	
	/**
	 * Checks if is local.
	 *
	 * @return true, if is local
	 */
	public boolean isLocal();

	/**
	 * Sets the results text.
	 *
	 * @param resultsString the new results text
	 */
	public void setResultsText(String resultsString);
	
	/**
	 * Gets the results text.
	 *
	 * @return the results text
	 */
	public String getResultsText();
	
	/**
	 * Adds the sparql listener.
	 *
	 * @param sparqlListener the sparql listener
	 */
	public void addSparqlListener(SparqlListener sparqlListener);
	
	/**
	 * Fire sparql event.
	 */
	public void fireSparqlEvent();

	/**
	 * Sets the query string.
	 *
	 * @param text the new query string
	 */
	public void setQueryString(String text);

	/**
	 * Gets the result set.
	 *
	 * @return the result set
	 */
	public ResultSet getResultSet();

	/**
	 * Sets the hTTP text.
	 *
	 * @param string the new hTTP text
	 */
	public void setHTTPText(String string);

	/**
	 * Gets the hTTP text.
	 *
	 * @return the hTTP text
	 */
	public String getHTTPText();
	/**
	 * @return
	 */
	// public SPARQLResult getResults();
}
