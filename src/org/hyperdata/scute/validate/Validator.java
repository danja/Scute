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
	 * @param validatable
	 *            the validatable
	 */
	public Validator(Validatable validatable) {
		this.validatable = validatable;
	}

	public StatusEvent validate() {
		StatusEvent starting = new StatusEvent(StatusMonitor.AMBER);
		stateChanged(starting);
		StatusEvent event = null;
		try {
			// ask the Validatable to validate itself
			event = validatable.validate();
		} catch (Exception exception) {
			// Log.exception(exception);
			event = new StatusEvent(StatusMonitor.RED, exception.getMessage());
			// stateChanged();
		}
		// broadcast results to listeners
		stateChanged(event);
		return event;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		validate();
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.status.Stoppable#stop()
	 */
	@Override
	public void stop() {
		// ignore - should be short-lived task
	}
}
