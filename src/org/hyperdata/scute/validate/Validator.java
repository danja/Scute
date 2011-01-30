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
public class Validator {

	// bunch of constants
	public static final int N_STATES = 3;
	
	public static final int UNKNOWN = 0;
	public static final int VALID = 1;
	public static final int INVALID = 2;
	
	protected static final String[] LABEL = { "Unknown Validity", "Valid",
			"Invalid" };

	protected static final ImageIcon[] ICON = new ImageIcon[N_STATES];
	static {
		ICON[0] = IndicatorIcons.unknownIcon;
		ICON[1] = IndicatorIcons.validIcon;
		ICON[2] = IndicatorIcons.invalidIcon;
	}
	
	// _________________ private members bar ______________________________
	
	
	private Validatable validatable;
	
	private Set<ValidationListener> validationListeners = new HashSet<ValidationListener>();
	/* (non-Javadoc)
	 * @see org.hyperdata.scute.validate.Validator#addValidationListener(org.hyperdata.scute.validate.ValidationListener)
	 */
	
	public Validator(Validatable validatable) { 
		this.validatable = validatable;
	}
	
	public void addValidationListener(ValidationListener listener) {
		validationListeners.add(listener);
	}
	
	public void stateChanged(ValidationEvent vEvent){
		Iterator<ValidationListener> iterator = validationListeners.iterator();
		while(iterator.hasNext()){
			iterator.next().stateChanged(vEvent);
		}
	}

	/*
	 * Do the validation
	 * 
	 * (non-Javadoc)
	 * @see org.hyperdata.scute.validate.Validator#validate()
	 */
	public void validate() throws InterruptedException{ 

		// ask the Validatable to validate itself
		ValidationEvent event = validatable.validate();	
		
		// broadcast results to listeners
		stateChanged(event);
	}
}
