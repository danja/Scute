/**
 * 
 */
package org.hyperdata.scute.validate;

import org.hyperdata.scute.status.StatusEvent;

/**
 * The Interface Validatable.
 *
 * @author danny
 */
public interface Validatable {

	/**
	 * Validate.
	 *
	 * @return the status event
	 * @throws Exception the exception
	 */
	public StatusEvent validate() throws Exception;

}
