/**
 * 
 */
package org.hyperdata.scute.validate;

import java.awt.Color;
import java.io.StringReader;
import java.io.IOException;

import org.openjena.atlas.io.IndentedLineBuffer;
import org.openjena.atlas.io.IndentedWriter;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.Syntax;
import com.hp.hpl.jena.sparql.ARQException;
import com.hp.hpl.jena.sparql.algebra.Algebra;
import com.hp.hpl.jena.sparql.algebra.Op;
import com.hp.hpl.jena.sparql.serializer.SerializationContext;

import javax.swing.text.*;

import com.hp.hpl.jena.rdf.arp.ARP;

import org.hyperdata.scute.status.StatusEvent;
import org.hyperdata.scute.status.StatusMonitor;
import org.hyperdata.scute.system.Log;

/**
 * The Class ValidatableRDFXMLDocument.
 * 
 * @author danny
 * 
 *         Wrapper around a Document with a method to validate contained SPARQL
 *         syntax
 * 
 *         (the method will usually be called automatically by a
 *         org.hyperdata.scute.validate.Validator)
 */
public class ValidatableSparqlDocument implements Validatable {

	private Document document;
	private String text;

	/**
	 * Instantiates a new validatable rdfxml document.
	 * 
	 * @param document
	 *            the document
	 */
	public ValidatableSparqlDocument(Document document) {
		this.document = document;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperdata.scute.validate.Validatable#validate()
	 */
	@Override
	public StatusEvent validate() throws InterruptedException {
		StatusEvent statusEvent = null;
		try {
			text = document.getText(0, document.getLength());
		} catch (BadLocationException exception) { // unlucky!
			return new StatusEvent(StatusMonitor.RED, exception.getMessage());
		}
		statusEvent = parseString(text);
		return statusEvent;
	}

	/**
	 * Parses the string.
	 * 
	 * @param text
	 *            the text
	 * @return the status event
	 */
	public StatusEvent parseString(String queryString) {
		Query query = null;
		StatusEvent statusEvent = null;
		try {
			query = QueryFactory.create(queryString);
			statusEvent = new StatusEvent(StatusMonitor.GREEN, "");
		} catch (Exception exception) {
			String message = exception.getMessage();
			statusEvent = new StatusEvent(StatusMonitor.RED,
					exception.getMessage(), true);
			if (statusEvent.getLine() > -1) {
				// highlightError(statusEvent); // doesn't work - because of
				// doc-level styling..?
			}
		}
		return statusEvent;
	}

	/**
	 * @param statusEvent
	 */
	private void highlightError(StatusEvent statusEvent) {

		int location = DocUtils.getLocation(text, statusEvent.getLine(),
				statusEvent.getColumn());
		// ((StyledDocument)document).putProperty("flag", location); couldn't
		// get that to work
		System.out.println("LOCATION=" + location);
		MutableAttributeSet attributes = new SimpleAttributeSet(); // or this...
		StyleConstants.setBackground(attributes, Color.red);
		((StyledDocument) document).setCharacterAttributes(location, 1,
				attributes, false);//
	}

}
