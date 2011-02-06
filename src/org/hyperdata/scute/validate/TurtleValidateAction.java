/**
 * 
 */
package org.hyperdata.scute.validate;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.text.Document;

import org.hyperdata.scute.swing.status.StatusAction;
import org.hyperdata.scute.swing.status.StatusChangeListener;
import org.hyperdata.scute.swing.status.StatusEvent;

/**
 * @author danny
 *
 */
public class TurtleValidateAction extends StatusAction {

	private StatusEvent status;

	/**
	 * @param turtleDocument
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
