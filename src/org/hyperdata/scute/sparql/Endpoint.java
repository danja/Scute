/**
 * 
 */
package org.hyperdata.scute.sparql;

import javax.swing.Action;



/**
 * @author danny
 *
 */
public final class Endpoint {
	
	private String label = null;
	private String uri = null;
	private Action action = null;
	
	/**
	 * Standard remote URI endpoint
	 * these all have the same type of Action
	 * 
	 * @param label display label
	 * @param uri endpoint URI
	 * 
	 */
	public Endpoint(String label, String uri){
		this.label = label;
		this.uri = uri;
		this.action = new URIEndpointAction(this);
	}
	
/**
 * Special-case endpoints, e.g. referring to a local model
 * 
	 * @param label display label
	 * @param action Action associated with the endpoint
	 */
	public Endpoint(String label, Action action) {
		this.label = label;
		this.action = action;
	}

public String getLabel() {
		return this.label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getUri() {
		return this.uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Action getAction() {
		return this.action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	
	public String toString(){
		return this.label;
	}

}
