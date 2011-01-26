package org.hyperdata.scute.swing.tree.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.hyperdata.scute.swing.tree.RdfTreePanel;

public class CopyAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 322403355387969068L;

	public CopyAction(RdfTreePanel treePanel) {
		super("Copy");
	}

	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(null, "Not yet Implemented. Sorry.");
	}

}
