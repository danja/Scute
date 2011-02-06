/**
 * 
 */
package org.hyperdata.scute.validate;

import org.hyperdata.scute.swing.status.StatusEvent;

/**
 * @author danny
 *
 * 
 */
public interface Validatable {

	/**
	 * @return
	 * @throws InterruptedException 
	 * @throws Exception 
	 */
	public StatusEvent validate() throws Exception;

}
