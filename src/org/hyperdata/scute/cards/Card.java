/**
 * 
 */
package org.hyperdata.scute.cards;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import org.hyperdata.scute.source.TextContainerEditorPane;


/**
 * @author danny
 *
 */
public class Card extends JPanel{

	// JPanel's constructors
	public Card(){
		super();
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
}
