/**
 * 
 */
package org.hyperdata.scute.validate;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeListener;

import org.hyperdata.resources.indicators.IndicatorIcons;
import org.hyperdata.scute.swing.status.StatusEvent;
import org.hyperdata.scute.swing.status.StatusMonitor;

/**
 * @author danja
 *
 */
public class Validator extends StatusMonitor implements Runnable {

	// _________________ private members bar ______________________________
	
	
	private Validatable validatable;
	

	
	public Validator(Validatable validatable) { 
		this.validatable = validatable;
	}
	
	/*
	 * Do the validation
	 * 
	 * (non-Javadoc)
	 * @see org.hyperdata.scute.validate.Validator#validate()
	 */
//	public void validate() { 
//		Thread t = new Thread(this);
//t.start();
//	}

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
