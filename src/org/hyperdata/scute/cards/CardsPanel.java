package org.hyperdata.scute.cards;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

import org.jdesktop.swingx.JXTitledPanel;

public class CardsPanel extends JPanel {

	private CardLayout layout;
	private EventListenerList listenerList = new EventListenerList();
	private ChangeEvent changeEvent = null;
	private String previousCardName = "Turtle";
	private String currentCardName = "Turtle";

	private Map<String, Card> cards = new HashMap<String, Card>();

	public void addPlain(Card card, String name) {
		super.add(card, name);
		cards.put(name, card);
	}
	
	public void add(Card card, String name) {
		super.add(new JXTitledPanel(name, card), name);
		cards.put(name, card);
	}
	
	public void addScroll(Card card, String name) {
		super.add(new JXTitledPanel(name, card), name);
	//	super.add(new JScrollPane(card), name);
		// System.out.println("Adding CARD = "+name);
		cards.put(name, card);
	}

	public String getCurrentCardName() {
		return currentCardName;
	}

	public String getPreviousCardName() {
		return previousCardName;
	}

	public CardsPanel() {
		super();
		layout = new CardLayout();
		setLayout(layout);
	}
	
	// for debugging
	public void listCards(){
		Iterator<String> iterator = cards.keySet().iterator();
		// System.out.println("---- CARDS ----");
		while(iterator.hasNext()){
			String key = iterator.next();
			// System.out.println(key+" = "+cards.get(key));
		}
		// System.out.println("---------------");
	}

	public Card getCard(String name) {
		return cards.get(name);
	}
	
	public Card getCurrentCard(){
		return getCard(currentCardName);
	}

	public void setCardName(String cardName) {
		previousCardName = currentCardName;
		this.currentCardName = cardName;
		fireStateChanged(); // is enough to update?
	}

	public void addChangeListener(ChangeListener listener) {
		listenerList.add(ChangeListener.class, listener);
	}

	public void removeChangeListener(ChangeListener listener) {
		listenerList.remove(ChangeListener.class, listener);
	}

	// Notify all listeners that have registered interest for
	// notification on this event type. The event instance
	// is lazily created using the parameters passed into
	// the fire method.

	protected void fireStateChanged() {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == ChangeListener.class) {
				// Lazily create the event:
				if (changeEvent == null)
					changeEvent = new ChangeEvent(this);
				((ChangeListener) listeners[i + 1]).stateChanged(changeEvent);
			}
		}
	}

	/**
	 * @param actionEvent
	 */
	public void fireChange(ActionEvent actionEvent) {
		this.currentCardName = actionEvent.getActionCommand();
		fireStateChanged();
	}
}
