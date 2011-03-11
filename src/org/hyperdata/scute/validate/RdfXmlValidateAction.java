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
public class RdfXmlValidateAction extends StatusAction {

	private StatusEvent status;

	/**
	 * Instantiates a new RDF/XML validate action.
	 *
	 * @param rdfxmlDocument the turtle document
	 */
	public RdfXmlValidateAction(Document rdfxmlDocument) {
		Validatable validatableRdfXml = new ValidatableRdfXmlDocument(rdfxmlDocument); 
		Validator validator = new Validator(validatableRdfXml);
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
