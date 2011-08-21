package org.hyperdata.scute.tabs;

import java.util.List;

import org.hyperdata.scute.cards.Card;

public interface CardList {
	public Card previous() ;
	public Card next();

	/**
	 * add Card to history
	 * 
	 * @param card
	 */
	public void add(Card card);
	
	/**
	 * remove current Card from history
	 */
	public void remove();

}
