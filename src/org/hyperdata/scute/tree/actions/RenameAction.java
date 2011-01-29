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

import org.hyperdata.scute.tree.RdfTreeNode;
import org.hyperdata.scute.tree.RdfTreePanel;

/**
 * The Class RenameAction.
 */
public class RenameAction extends AbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5693309851417366965L;
	
	/** The tree panel. */
	private final RdfTreePanel treePanel;

	/**
	 * Instantiates a new rename action.
	 * 
	 * @param treePanel
	 *            the tree panel
	 */
	public RenameAction(RdfTreePanel treePanel) {
		super("Rename");
		this.treePanel = treePanel;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		((RdfTreeNode) treePanel.getClickedPath().getLastPathComponent())
				.renameRequest();
		treePanel.getTree().repaint();
	}

}
