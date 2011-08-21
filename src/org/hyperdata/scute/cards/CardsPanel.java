package org.hyperdata.scute.cards;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

import org.hdesktop.swingx.JXPanel;
import org.hdesktop.swingx.JXTitledPanel;

public class CardsPanel extends JPanel implements MouseListener {

	private CardLayout layout;
	private EventListenerList listenerList = new EventListenerList();
	private ChangeEvent changeEvent = null;
	private String previousCardType = "Turtle";
	private String currentCardType = "Turtle";

	private Map<String, Card> cards = new HashMap<String, Card>();

	public void addPlain(Card card, String type) {
		super.add(card, type);
		cards.put(type, card);
	}
	
	public void add(Card card, String type) {
//		JXTitledPanel titledPanel = new JXTitledPanel(type, card);	
//		titledPanel.addMouseListener(this);
//		super.add(titledPanel, type);
		card.addMouseListener(this);
		super.add(card, type);
		
		cards.put(type, card);
	}
	
	public void addScroll(Card card, String type) {
		super.add(new JXTitledPanel(type, card), type);
	//	super.add(new JScrollPane(card), name);
		// System.out.println("Adding CARD = "+name);
		cards.put(type, card);
	}

	public String getCurrentCardType() {
		return currentCardType;
	}

	public String getPreviousCardType() {
		return previousCardType;
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

	public Card getCard(String type) {
		return cards.get(type);
	}
	
	public Card getCurrentCard(){
		return getCard(currentCardType);
	}

	public void setCurrentCard(String cardType) {
		previousCardType = currentCardType;
		this.currentCardType = cardType;
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
		this.currentCardType = actionEvent.getActionCommand();
		fireStateChanged();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("CLICK! "+arg0);
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
