/**
 * 
 */
package org.hyperdata.scute.validate;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.event.ChangeListener;

import org.hyperdata.resources.indicators.IndicatorIcons;

/**
 * @author danja
 *
 */
public class Validator extends StatusMonitor {

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
	public void validate() throws InterruptedException{ 

		// ask the Validatable to validate itself
		StatusEvent event = validatable.validate();	
		
		// broadcast results to listeners
		stateChanged(event);
	}
}
