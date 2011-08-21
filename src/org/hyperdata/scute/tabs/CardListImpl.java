/**
 * 
 */
package org.hyperdata.scute.tabs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.hyperdata.scute.cards.Card;

/**
 * @author danny
 *
 */
public class CardListImpl implements CardList {
	
	private List<Card> cards = new ArrayList<Card>(); // LinkedList might be more natural..?
	private int index = 0;

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.history.CardList#getPrevious()
	 */
	@Override
	public Card previous() {
		if(--index == -1){
			index = cards.size()-1;
		}
		return cards.get(index);
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.history.CardList#getNext()
	 */
	@Override
	public Card next() {
		if(++index == cards.size()){
			index = 0;
		}
		return cards.get(index);
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.history.CardList#add(org.hyperdata.scute.cards.Card)
	 */
	@Override
	public void add(Card card) {
		cards.add(card);
		
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.history.CardList#remove()
	 */
	@Override
	public void remove() {
		cards.remove(index);
	}
}