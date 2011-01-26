package org.hyperdata.scute.swing.source;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.ViewFactory;

public class HighlighterEditorKit extends StyledEditorKit {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6049505706362842082L;
	HighlighterFactory defaultFactory = new HighlighterFactory();

	public HighlighterEditorKit(String syntax) {
		super();
		defaultFactory.setSyntax(syntax);
	}

	@Override
	public MutableAttributeSet getInputAttributes() {
		final MutableAttributeSet mAttrs = super.getInputAttributes();
		// mAttrs.removeAttribute(SyntaxHighlighterRunner.LINE_BREAK_ATTRIBUTE_NAME);
		return mAttrs;
	}

	@Override
	public ViewFactory getViewFactory() {
		return defaultFactory;
	}
}
