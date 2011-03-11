/**
 * 
 */
package org.hyperdata.scute.cards;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import org.hyperdata.scute.main.SplitButtons;
import org.hyperdata.scute.source.EditorPane;
import org.hyperdata.scute.source.TextContainer;


/**
 * @author danny
 *
 */
public class Card extends JPanel {

	// this is a bit yucky, will do for now
	private TextContainer textContainer = null;
//	private EditorPane editorPane = null;
	private boolean textCard = false;

	// JPanel's constructors
	public Card(){
		super(new BorderLayout());
	}

	public Card(boolean isDoubleBuffered){
		super(isDoubleBuffered);
	}
	
	public Card(LayoutManager layout){
		super(layout);
	}
	
	public Card(LayoutManager layout, boolean isDoubleBuffered){
		super(layout, isDoubleBuffered);
	}

	public void setTextCard(boolean textCard) {
		this.textCard = textCard;
	}
	
	/**
	 * this should maybe be in an interface
	 * @return
	 */
	public TextContainer getTextContainer() {
		return textContainer;
	}

	/**
	 * @param rdfxmlPanel
	 */
	public void setTextContainer(TextContainer textContainer) {
		this.textContainer = textContainer;
	}

	/**
	 * @return
	 */
	public boolean isTextCard() {
		return textCard;
	}

}
