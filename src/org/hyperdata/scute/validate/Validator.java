/**
 * 
 */
package org.hyperdata.scute.validate;

import org.hyperdata.scute.status.StatusEvent;
import org.hyperdata.scute.status.StatusMonitor;
import org.hyperdata.scute.status.StatusTask;
import org.hyperdata.scute.system.Log;

/**
 * The Class Validator.
 *
 * @author danja
 */
public class Validator extends StatusTask {
	
	private Validatable validatable;
	
	/**
	 * Instantiates a new validator.
	 *
	 * @param validatable the validatable
	 */
	public Validator(Validatable validatable) { 
		this.validatable = validatable;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// ask the Validatable to validate itself
		StatusEvent starting = new StatusEvent(StatusMonitor.AMBER);
		stateChanged(starting);
		
		StatusEvent event;
		try {
			event = validatable.validate();
			// broadcast results to listeners
			stateChanged(event);
		} catch (Exception exception) {
			Log.exception(exception);
			stateChanged(new StatusEvent(StatusMonitor.RED, exception.getMessage()));
		}
	}
}
