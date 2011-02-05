/**
 * 
 */
package org.hyperdata.scute.validate;

/**
 * @author danny
 *
 * 
 */
public interface Validatable {

	/**
	 * @return
	 * @throws InterruptedException 
	 */
	public StatusEvent validate() throws InterruptedException;

}
