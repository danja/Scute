/**
 * 
 */
package org.hyperdata.scute.sparql;

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
