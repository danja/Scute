package org.hyperdata.scute.sparql;

import com.hp.hpl.jena.query.Dataset;


public interface SparqlContainer {
	
	public String getQueryString();

	public Dataset getDataset();

	public Endpoint getEndpoint();
	
	public void setEndpoint(Endpoint endpoint);
	
	/**
	 * @return
	 */
	public boolean isLocal();

	/**
	 * @param resultsString
	 */
	public void setResultsText(String resultsString);
	
	public String getResultsText();
	
	public void addSparqlListener(SparqlListener sparqlListener);
	
	public void fireSparqlEvent();

	/**
	 * @param text
	 */
	public void setQueryString(String text);
}
