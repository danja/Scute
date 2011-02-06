/**
 * 
 */
package org.hyperdata.scute.validate;

import java.io.IOException;
import java.io.StringReader;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.hp.hpl.jena.n3.turtle.TurtleParseException;
import com.hp.hpl.jena.rdf.arp.ARP;
import com.hp.hpl.jena.rdf.model.Model;

import org.openjena.riot.ErrorHandler;
import org.xml.sax.SAXParseException;

import org.hyperdata.scute.swing.status.StatusEvent;
import org.hyperdata.scute.swing.status.StatusMonitor;

/**
 * @author danny
 * 
 * Wrapper around a Document with a method to validate contained Turtle syntax
 * 
 * (the method will usually be called automatically by a org.hyperdata.scute.validate.Validator)
 *
 */
public class ValidatableRDFXMLDocument implements Validatable {

	private Document document;

	public ValidatableRDFXMLDocument(Document document){
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
				exception.printStackTrace();
			}
		return statusEvent;
	}

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
