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
public class TurtleValidateAction extends StatusAction {

	private StatusEvent status;

	/**
	 * Instantiates a new turtle validate action.
	 *
	 * @param turtleDocument the turtle document
	 */
	public TurtleValidateAction(Document turtleDocument) {
		Validatable validatableTurtle = new ValidatableTurtleDocument(turtleDocument); 
		Validator validator = new Validator(validatableTurtle);
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
