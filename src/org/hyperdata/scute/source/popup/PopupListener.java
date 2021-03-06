/**
 * 
 */
package org.hyperdata.scute.source.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 * @author danny
 * 
 */
public class PopupListener extends MouseAdapter  {

	private JPopupMenu popup;

	public PopupListener(JPopupMenu popup) {
		this.popup = popup;
	}

	@Override
	public void mousePressed(MouseEvent event) { // SwingUtilities.isLeftMouseButton(e)
		if ((event.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) { // right
																							// mouse
																							// click
			// System.out.println("click "+event.getComponent());
			popup.show(event.getComponent(), event.getX(), event.getY());
		}
	}
	
}
