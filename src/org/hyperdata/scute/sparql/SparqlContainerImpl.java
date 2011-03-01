/**
 * 
 */
package org.hyperdata.scute.sparql;

import javax.swing.event.EventListenerList;

import com.hp.hpl.jena.query.Dataset;

import org.hyperdata.scute.sparql.endpoints.Endpoint;

/**
 * @author danny
 *
 */
public class SparqlContainerImpl implements SparqlContainer {

	private String queryString = null;
	private Dataset dataset = null;
	private Endpoint endpoint = null;
	private boolean local = false;
	private String resultsString = null;
	
	private  EventListenerList listenerList = new EventListenerList();
	private  SparqlEvent sparqlEvent = null;

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
}
