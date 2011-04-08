/**
 * 
 */
package org.hyperdata.scute.cards;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import org.hyperdata.scute.autosave.AutoSave;
import org.hyperdata.scute.main.SplitScreen;
import org.hyperdata.scute.source.EditorPane;
import org.hyperdata.scute.source.TextContainer;


/**
 * @author danny
 *
 */
public class Card extends JPanel {

	public static final int DEFAULT = 0;
	public static final int TURTLE = 1;
	public static final int RDFXML = 2;
	public static final int GRAPH = 3;
	public static final int TREE = 4;
	public static final int SPARQL = 5;
	public static final int FILE_EXPLORER = 6;
	public static final int GRAPH_MANAGER = 7;
	public static final int IMAGE = 8;
	public static final int SETTINGS = 9;
	public static final int TRIPLES = 10;
	
	// this is a bit yucky, will do for now
	private TextContainer textContainer = null;
//	private EditorPane editorPane = null;
	private boolean textCard = false;
	
	// JPanel's constructors
//	private Card(){
//		super(new BorderLayout());
//	}
//
//	private  Card(boolean isDoubleBuffered){
//		super(isDoubleBuffered);
//	}
//	
//	private  Card(LayoutManager layout){
//		super(layout);
//	}
//	
//	private  Card(LayoutManager layout, boolean isDoubleBuffered){
//		super(layout, isDoubleBuffered);
//	}
	
	public Card(){
		super(new BorderLayout());
	}

	public  Card(boolean isDoubleBuffered){
		super(isDoubleBuffered);
	}
	
	public  Card(LayoutManager layout){
		super(layout);
	}
	
	public  Card(LayoutManager layout, boolean isDoubleBuffered){
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

	/**
	 * @param string
	 */
	public void setLabel(String string) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param autoSave
	 */
	public void addUserActivityListener(AutoSave autoSave) {
		// TODO to implement when RdfTreeCard etc supports editing
	}
}
