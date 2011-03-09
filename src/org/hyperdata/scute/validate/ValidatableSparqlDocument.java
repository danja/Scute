/**
 * 
 */
package org.hyperdata.scute.validate;

import java.io.StringReader;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.hp.hpl.jena.rdf.arp.ARP;
import org.hyperdata.scute.swing.status.StatusEvent;
import org.hyperdata.scute.swing.status.StatusMonitor;
import org.hyperdata.scute.system.Log;

/**
 * The Class ValidatableRDFXMLDocument.
 *
 * @author danny
 * 
 * Wrapper around a Document with a method to validate contained SPARQL syntax
 * 
 * (the method will usually be called automatically by a org.hyperdata.scute.validate.Validator)
 */
public class ValidatableSparqlDocument implements Validatable {

	private Document document;

	/**
	 * Instantiates a new validatable rdfxml document.
	 *
	 * @param document the document
	 */
	public ValidatableSparqlDocument(Document document){
		this.document = document;
	}
	
	/* (non-Javadoc)
	 * @see org.hyperdata.scute.validate.Validatable#validate()
	 */
	@Override
	public StatusEvent validate() throws InterruptedException {
			StatusEvent statusEvent = null;
			try {
				statusEvent = parseString(document.getText(0, document.getLength()));
			} catch (BadLocationException exception) {
				Log.exception(exception);
				statusEvent = new StatusEvent(StatusMonitor.RED, exception.getMessage());
			}
		return statusEvent;
	}

	/**
	 * Parses the string.
	 *
	 * @param text the text
	 * @return the status event
	 */
	public static StatusEvent parseString(String text){
		
	 try {
		Thread.sleep(2000);
	} catch (InterruptedException exception) {
		// TODO Auto-generated catch block
		Log.exception(exception);
	}
	  
//	           statusEvent.setStatus(StatusMonitor.RED);
//	           statusEvent.setDescription("eek");
	           StatusEvent statusEvent = new StatusEvent(StatusMonitor.GREEN, "happy!");
	    return statusEvent;
	}

}
