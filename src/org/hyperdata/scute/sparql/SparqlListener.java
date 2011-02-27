/**
 * 
 */
package org.hyperdata.scute.sparql;

import java.util.EventListener;

/**
 * @author danny
 *
 * marker interface
 */
public interface SparqlListener extends EventListener {

	/**
	 * @param sparqlEvent
	 */
	public void sparqlEvent(SparqlEvent sparqlEvent);

}
