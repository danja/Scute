/*
 * Scute
 * 
 * Homepage: http://hyperdata.org/scute
 * 
 * License : http://www.apache.org/licenses/LICENSE-2.0
 * See also license.txt or http://hyperdata.org/wiki/Scute:License
 * 
 * Danny Ayers 2011
 */
package org.hyperdata.scute.source;

import javax.swing.text.Element;
import javax.swing.text.LabelView;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

import org.hyperdata.scute.sparql.SparqlView;

/**
 * A factory for creating Highlighter objects.
 */
class HighlighterFactory implements ViewFactory {

	/** The syntax. */
	private String syntax;

	/* (non-Javadoc)
	 * @see javax.swing.text.ViewFactory#create(javax.swing.text.Element)
	 */
	@Override
	public View create(Element elem) {
		// System.out.println("ELEMENT= "+elem.getName()+elem);
		final String kind = elem.getName();
		if (kind != null) {
			if (syntax.equals("Turtle"))
				return new TurtleView(elem);
			
			if (syntax.equals("XML"))
				return new XmlView(elem);
			
			if (syntax.equals("SPARQL"))
				return new SparqlView(elem);
		}

		// default to text display
		return new LabelView(elem);
	}

	/**
	 * Sets the syntax.
	 * 
	 * @param syntax
	 *            the new syntax
	 */
	public void setSyntax(String syntax) {
		this.syntax = syntax;
		// System.out.println("syntax="+syntax);
	}
}
