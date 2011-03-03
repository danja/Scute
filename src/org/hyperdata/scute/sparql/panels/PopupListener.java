/**
 * 
 */
package org.hyperdata.scute.sparql.panels;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

/**
 * @author danny
 * 
 */
public class PopupListener extends MouseAdapter {

	private JPopupMenu popup;

	public PopupListener(JPopupMenu popup) {
		this.popup = popup;
	}

	@Override
	public void mousePressed(MouseEvent event) { // SwingUtilities.isLeftMouseButton(e)
		if ((event.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) { // right
																							// mouse
																							// click
			popup.show(event.getComponent(), event.getX(), event.getY());
		}
	}
}
