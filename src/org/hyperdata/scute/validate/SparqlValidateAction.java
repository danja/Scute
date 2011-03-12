/**
 * 
 */
package org.hyperdata.scute.validate;

import javax.swing.text.Document;

import org.hyperdata.scute.status.StatusAction;
import org.hyperdata.scute.status.StatusEvent;

/**
 * The Class TurtleValidateAction.
 *
 * @author danny
 */
public class SparqlValidateAction extends StatusAction {

	private StatusEvent status;

	private Validator validator;
	/**
	 * Instantiates a new turtle validate action.
	 *
	 * @param turtleDocument the turtle document
	 */
	public SparqlValidateAction(Document sparqlDocument) {
		Validatable validatableSparql = new ValidatableSparqlDocument(sparqlDocument); 
		validator = new Validator(validatableSparql);
		validator.addStatusListener(this);
		setStatusTask(validator); 
	}
	
	public Validator getValidator(){
		return validator;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.swing.status.StatusChangeListener#statusChanged(org.hyperdata.scute.swing.status.StatusEvent)
	 */
	@Override
	public void statusChanged(StatusEvent status) {
		this.status = status;
	}


// putValue(key, value)
}
