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

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.ViewFactory;

/**
 * The Class HighlighterEditorKit.
 */
public class HighlighterEditorKit extends StyledEditorKit {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6049505706362842082L;
	
	/** The default factory. */
	HighlighterFactory defaultFactory = new HighlighterFactory();

	/**
	 * Instantiates a new highlighter editor kit.
	 * 
	 * @param syntax
	 *            the syntax
	 */
	public HighlighterEditorKit(String syntax) {
		super();
		defaultFactory.setSyntax(syntax);
	}

	/* (non-Javadoc)
	 * @see javax.swing.text.StyledEditorKit#getInputAttributes()
	 */
	@Override
	public MutableAttributeSet getInputAttributes() {
		final MutableAttributeSet attrs = super.getInputAttributes();
		// mAttrs.removeAttribute(SyntaxHighlighterRunner.LINE_BREAK_ATTRIBUTE_NAME);
		return attrs;
	}

	/* (non-Javadoc)
	 * @see javax.swing.text.StyledEditorKit#getViewFactory()
	 */
	@Override
	public ViewFactory getViewFactory() {
		return defaultFactory;
	}
}
