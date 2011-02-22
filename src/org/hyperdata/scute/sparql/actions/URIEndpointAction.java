/**
 * 
 */
package org.hyperdata.scute.sparql.actions;

import org.hyperdata.scute.sparql.Endpoint;

/**
 * @author danny
 *
 */
public class URIEndpointAction extends EndpointAction {

	private String uri;
	private String label;

	/**
	 * @param endpoint
	 */
	public URIEndpointAction(Endpoint endpoint) {
		this.uri = endpoint.getUri();
		this.label = endpoint.getLabel();
	}

}
