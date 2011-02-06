/**
 * 
 */
package org.hyperdata.scute.validate;

import java.io.Reader;
import java.io.StringReader;

import javax.swing.text.Document;

import org.hyperdata.scute.swing.status.StatusEvent;
import org.hyperdata.scute.swing.status.StatusMonitor;

/**
 * The Class ValidatableTurtleDocument.
 *
 * @author danny
 * 
 * Wrapper around a Document with a method to validate contained Turtle syntax
 * 
 * (the method will usually be called automatically by a org.hyperdata.scute.validate.Validator)
 */
public class ValidatableTurtleDocument implements Validatable {

	private Document document;

	/**
	 * Instantiates a new validatable turtle document.
	 *
	 * @param document the document
	 */
	public ValidatableTurtleDocument(Document document){
		this.document = document;
	}
	
	/* (non-Javadoc)
	 * @see org.hyperdata.scute.validate.Validatable#validate()
	 */
	@Override
	public StatusEvent validate() throws Exception {
		StatusEvent statusEvent = null;
			statusEvent = parseString("", document.getText(0, document.getLength()));
		return statusEvent;
	}

	/**
	 * Parses the string.
	 *
	 * @param baseURI the base uri
	 * @param in the in
	 * @return the status event
	 */
	public StatusEvent parseString(String baseURI, String in)  {
		StatusEvent statusEvent = new StatusEvent(StatusMonitor.GREEN);
		Reader reader = new StringReader(in);
		
			// FileUtils.asUTF8(in);
		try {
			com.hp.hpl.jena.n3.turtle.parser.TurtleParser parser = new com.hp.hpl.jena.n3.turtle.parser.TurtleParser(reader);
			// parser.setEventHandler(new TurtleEventDump()) ;
			TurtleHandler handler = new TurtleHandler(System.out);
			parser.setEventHandler(handler);
			parser.setBaseURI(baseURI);
			parser.parse();
			statusEvent.setDescription(Integer.toString(handler.getCount())+" triples");
		} catch (Throwable e) { // more general than Exception
			System.out.println("GOT EXC "+e.getMessage());
			statusEvent.setStatus(StatusMonitor.RED);
			statusEvent.setDescription(e.getMessage());
		} 
		return statusEvent;
	}
}
