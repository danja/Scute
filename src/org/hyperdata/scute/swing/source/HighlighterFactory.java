package org.hyperdata.scute.swing.source;

import javax.swing.text.Element;
import javax.swing.text.LabelView;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

class HighlighterFactory implements ViewFactory {

	private String syntax;

	public View create(Element elem) {
		// System.out.println("ELEMENT= "+elem.getName()+elem);
		final String kind = elem.getName();
		if (kind != null) {
			if (syntax.equals("Turtle"))
				return new TurtleView(elem);
			if (syntax.equals("XML"))
				return new XmlView(elem);
		}

		// default to text display
		return new LabelView(elem);
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}
}
