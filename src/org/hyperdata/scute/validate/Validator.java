/**
 * 
 */
package org.hyperdata.scute.validate;

import org.hyperdata.scute.swing.status.StatusEvent;
import org.hyperdata.scute.swing.status.StatusTask;

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
		StatusEvent event;
		try {
			event = validatable.validate();
			// broadcast results to listeners
			stateChanged(event);
		} catch (Exception exception) {
			// TODO make error indicator
			System.out.println("Exception in Validator");
			exception.printStackTrace();
		}
	}
}
