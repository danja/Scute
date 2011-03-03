/**
 * 
 */
package org.hyperdata.scute.syntax;

import javax.swing.text.*;

public class ScuteEditorKit extends StyledEditorKit {
	
	private String syntax = "Turtle";

	public ScuteEditorKit(String syntax){
		this.syntax  = syntax;
	}
	
	public ViewFactory getViewFactory() {
		ScuteViewFactory scuteViewFactory = new ScuteViewFactory();
		scuteViewFactory.setSyntax(syntax);
		return scuteViewFactory;
	}

	class ScuteViewFactory implements ViewFactory {

		private String syntax;
		
		public void setSyntax(String syntax) {
			this.syntax = syntax;
		}

		public View create(Element element) {
			String kind = element.getName();
			if (kind != null) {
				if (kind.equals(AbstractDocument.ContentElementName)) {
					return new LabelView(element);
				} else if (kind.equals(AbstractDocument.ParagraphElementName)) {
					return getSyntaxView(element);
				
					// return new ParagraphView(elem);
				} else if (kind.equals(AbstractDocument.SectionElementName)) {
					return getSyntaxView(element);
					
				} else if (kind.equals(StyleConstants.ComponentElementName)) {
					return new ComponentView(element);
				} else if (kind.equals(StyleConstants.IconElementName)) {
					return new IconView(element);
				}
			}

			// default to text display
			return new LabelView(element);
		}
		
		

		/**
		 * @return
		 */
		private View getSyntaxView(Element elem) {
			if (syntax.equals("Turtle")) {
				System.out.println("returning turtleview");
				return new TurtleView(elem);
			}
			if (syntax.equals("XML")) {
				return new XmlView(elem);
			}
			if (syntax.equals("SPARQL")) {
				return new SparqlView(elem);
			}
			return new ParagraphView(elem);
		}



		/**
		 * Sets the syntax.
		 * 
		 * @param syntax
		 *            the new syntax
		 */


	}

	/**
	 * @param string
	 */
	public void setSyntax(String string) {
		this.syntax = syntax;
		
	}
}
