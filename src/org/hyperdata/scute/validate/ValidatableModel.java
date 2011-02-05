/**
 * 
 */
package org.hyperdata.scute.validate;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * @author danny
 * 
 * Wrapper around a Jena Model with a method to validate the Model
 * 
 * (the method will usually be called automatically by a org.hyperdata.scute.validate.Validator)
 *
 */
public class ValidatableModel implements Validatable {

	private Model model;

	public ValidatableModel(Model model){
		this.model = model;
	}
	
	/* (non-Javadoc)
	 * @see org.hyperdata.scute.validate.Validatable#validate()
	 */
	@Override
	public StatusEvent validate() throws InterruptedException {
		StatusEvent event = new StatusEvent();
		// FIXME implement Model validation
		return event;
	}

}
