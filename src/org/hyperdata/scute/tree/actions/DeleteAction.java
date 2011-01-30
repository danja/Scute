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
 * The Class DeleteAction.
 */
public class DeleteAction extends AbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4723921004105606994L;
	
	/** The tree panel. */
	private final RdfTreePanel treePanel;

	/**
	 * Instantiates a new delete action.
	 * 
	 * @param treePanel
	 *            the tree panel
	 */
	public DeleteAction(RdfTreePanel treePanel) {
		super("Delete");
		this.treePanel = treePanel;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {

		((RdfTreeNode) treePanel.getClickedPath().getLastPathComponent())
				.deleteRequest();

		treePanel.getTree().repaint();
	}

}
