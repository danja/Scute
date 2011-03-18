/**
 * 
 */
package org.hyperdata.scute.status;



/**
 * The listener interface for receiving statusChange events.
 * The class that is interested in processing a statusChange
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addStatusChangeListener<code> method. When
 * the statusChange event occurs, that object's appropriate
 * method is invoked.
 *
 * @author danny
 */
public interface StatusChangeListener {
		
		/**
		 * Status changed.
		 *
		 * @param status the status
		 */
		public void statusChanged(StatusEvent statusEvent);
}
