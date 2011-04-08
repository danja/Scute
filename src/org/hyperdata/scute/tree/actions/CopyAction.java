/*
 * Scute
 * 
 * Homepage: http://hyperdata.org/scute
 * 
 * License : http://www.apache.org/licenses/LICENSE-2.0
 * See also license.txt or http://hyperdata.org/wiki/Scute:License
 * 
 * Danny Ayers 2011
 */
package org.hyperdata.scute.tree.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.hyperdata.scute.tree.RdfTreeCard;

/**
 * The Class CopyAction.
 */
public class CopyAction extends AbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 322403355387969068L;

	/**
	 * Instantiates a new copy action.
	 * 
	 * @param treePanel
	 *            the tree panel
	 */
	public CopyAction(RdfTreeCard treePanel) {
		super("Copy");
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(null, "Not yet Implemented. Sorry.");
	}

}
