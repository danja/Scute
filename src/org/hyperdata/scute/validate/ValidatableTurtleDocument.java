/**
 * 
 */
package org.hyperdata.scute.validate;

import java.io.Reader;
import java.io.StringReader;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.hp.hpl.jena.n3.turtle.Turtle2NTriples;
import com.hp.hpl.jena.n3.turtle.TurtleEventDump;
import com.hp.hpl.jena.n3.turtle.TurtleParseException;
import com.hp.hpl.jena.n3.turtle.parser.ParseException;
import com.hp.hpl.jena.n3.turtle.parser.TokenMgrError;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.shared.JenaException;

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
public class ValidatableTurtleDocument implements Validatable {

	private Document document;

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
