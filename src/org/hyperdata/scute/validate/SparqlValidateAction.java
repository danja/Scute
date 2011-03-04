/**
 * 
 */
package org.hyperdata.scute.validate;

import javax.swing.text.Document;

import org.hyperdata.scute.swing.status.StatusAction;
import org.hyperdata.scute.swing.status.StatusEvent;

/**
 * The Class TurtleValidateAction.
 *
 * @author danny
 */
public class SparqlValidateAction extends StatusAction {

	private StatusEvent status;

	/**
	 * Instantiates a new turtle validate action.
	 *
	 * @param turtleDocument the turtle document
	 */
	public SparqlValidateAction(Document sparqlDocument) {
		Validatable validatableSparql = new ValidatableSparqlDocument(sparqlDocument); 
		Validator validator = new Validator(validatableSparql);
		validator.addStatusListener(this);
		setStatusTask(validator); 
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
