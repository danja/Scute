/**
 * 
 */
package org.hyperdata.scute.validate;

import java.io.StringReader;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.hp.hpl.jena.rdf.arp.ARP;

import org.hyperdata.scute.status.StatusEvent;
import org.hyperdata.scute.status.StatusMonitor;
import org.hyperdata.scute.system.Log;

/**
 * The Class ValidatableRDFXMLDocument.
 *
 * @author danny
 * 
 * Wrapper around a Document with a method to validate contained Turtle syntax
 * 
 * (the method will usually be called automatically by a org.hyperdata.scute.validate.Validator)
 */
public class ValidatableRdfXmlDocument implements Validatable {

	private Document document;

	/**
	 * Instantiates a new validatable rdfxml document.
	 *
	 * @param document the document
	 */
	public ValidatableRdfXmlDocument(Document document){
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
				// TODO Ado something useful with this exception
				Log.exception(exception);
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
		StatusEvent statusEvent = new StatusEvent(StatusMonitor.GREEN);
	ARP arp = new ARP();
    
	 // initialisation - uses ARPConfig interface only.
	 
	    arp.getOptions().setStrictErrorMode();
	        
	    /*
	    arp.getHandlers().setErrorHandler(new ErrorHandler(){
	    	public void fatalError(SAXParseException e){
	               // TODO code
	    	}
			public void error(SAXParseException e){
					// TODO code
			}
			public void warning(SAXParseException e){
					// TODO code
			}
	    });
	    */
	    /*
	    arp.getHandlers().setStatementHandler(new StatementHandler(){
	    public void statement(AResource a, AResource b, ALiteral l){
	        	// TODO code
	    }
		public void statement(AResource a, AResource b, AResource l){
		        // TODO code
	    }
	    });
	 
	 */
	 // parsing.
	 
	    try {
	        arp.load(new StringReader(text)); 
	    }
	    catch (Exception e){
	           statusEvent.setStatus(StatusMonitor.RED);
	           statusEvent.setDescription(e.getMessage());
	    }
	    return statusEvent;
	}

}
