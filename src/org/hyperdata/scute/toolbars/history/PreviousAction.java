/**
 * 
 */
package org.hyperdata.scute.toolbars.history;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * @author danny
 *
 */
public class PreviousAction extends AbstractAction {

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("PREVIOUS!");
	}

}
