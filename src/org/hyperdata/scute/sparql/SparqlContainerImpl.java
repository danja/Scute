/**
 * 
 */
package org.hyperdata.scute.sparql;

import javax.swing.event.EventListenerList;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFactory;
import com.hp.hpl.jena.sparql.resultset.SPARQLResult;

import org.hyperdata.scute.sparql.endpoints.Endpoint;
import org.hyperdata.scute.system.Log;

/**
 * @author danny
 *
 * changed it to include a Query object - may cause inconsistencies
 */
public class SparqlContainerImpl implements SparqlContainer {

	private String queryString = null;
	private Dataset dataset = null;
	private Endpoint endpoint = null;
	private boolean local = false;
	private String resultsString = null;
	
	private  EventListenerList listenerList = new EventListenerList();
	private  SparqlEvent sparqlEvent = null;
	private ResultSet resultSet;
	private String httpText;
	private Query query;

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.sparql.SparqlContainer#getQueryString()
	 */
	@Override
	public String getQueryString() {
		return queryString;
	}
	
	/* (non-Javadoc)
	 * @see org.hyperdata.scute.sparql.SparqlContainer#setQueryString(java.lang.String)
	 */
	@Override
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.sparql.SparqlContainer#getDataset()
	 */
	@Override
	public Dataset getDataset() {
		return dataset ;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.sparql.SparqlContainer#getEndpoint()
	 */
	@Override
	public Endpoint getEndpoint() {
		return endpoint;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.sparql.SparqlContainer#setEndpoint(org.hyperdata.scute.sparql.Endpoint)
	 */
	@Override
	public void setEndpoint(Endpoint endpoint) {
		this.endpoint = endpoint;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.sparql.SparqlContainer#isLocal()
	 */
	@Override
	public boolean isLocal() {
		return local;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.sparql.SparqlContainer#setResultsText(java.lang.String)
	 */
	@Override
	public void setResultsText(String resultsString) {
		this.resultsString = resultsString;
		if(isSelect()){
			try{ // spurious
			resultSet = ResultSetFactory.fromXML(resultsString);
			}catch(Exception exception){
				resultsString = null;
				Log.exception(exception);
			}
		}
	}
	
	/**
	 * @return
	 */
	private boolean isSelect() {
		// TODO check what kind of query it is
		return true;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.sparql.SparqlContainer#getResultsText()
	 */
	@Override
	public String getResultsText() {
		return resultsString;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.sparql.SparqlContainer#addSparqlListener(org.hyperdata.scute.sparql.SparqlListener)
	 */
	@Override
	public void addSparqlListener(SparqlListener sparqlListener) {
	     listenerList.add(SparqlListener.class, sparqlListener);
	 }

	     public void removeFooListener(SparqlListener sparqlListener) {
	         listenerList.remove(SparqlListener.class, sparqlListener);
	     }

	 @Override
	public void fireSparqlEvent() {
	     // Guaranteed to return a non-null array
	     Object[] listeners = listenerList.getListenerList();
	     // Process the listeners last to first, notifying
	     // those that are interested in this event
	     for (int i = listeners.length-2; i>=0; i-=2) {
	         if (listeners[i]==SparqlListener.class) {
	             // Lazily create the event:
	             if (sparqlEvent == null)
	            	 sparqlEvent = new SparqlEvent(this);
	             ((SparqlListener)listeners[i+1]).sparqlEvent(sparqlEvent);
	         }
	     }
	 }

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.sparql.SparqlContainer#getResults()
	 */
	@Override
	public ResultSet getResultSet() {
		return resultSet;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.sparql.SparqlContainer#setHTTPText(java.lang.String)
	 */
	@Override
	public void setHTTPText(String httpText) {
		this.httpText = httpText;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.sparql.SparqlContainer#getHTTPText()
	 */
	@Override
	public String getHTTPText() {
		return httpText;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.sparql.SparqlContainer#setQuery(com.hp.hpl.jena.query.Query)
	 */
	@Override
	public void setQuery(Query query) {
		this.query = query;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.sparql.SparqlContainer#getQuery()
	 */
	@Override
	public Query getQuery() {
		return query;
	}
}
