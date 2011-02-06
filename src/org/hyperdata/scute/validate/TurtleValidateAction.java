/**
 * 
 */
package org.hyperdata.scute.validate;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.text.Document;

/**
 * @author danny
 *
 */
public class TurtleValidateAction extends StatusAction {

	/**
	 * @param turtleDocument
	 */
	public TurtleValidateAction(Document turtleDocument) {
		Validatable validatableTurtle = new ValidatableTurtleDocument(turtleDocument); 
		setStatusTask(new Validator(validatableTurtle)); 
		
	}


// putValue(key, value)
}
