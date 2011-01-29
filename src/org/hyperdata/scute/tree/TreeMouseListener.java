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
package org.hyperdata.scute.tree;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

/**
 * Picks up mouse events on the RDF tree view.
 * 
 * @see TreeMouseEvent
 */
public class TreeMouseListener extends MouseAdapter {
	
	/** The tree. */
	private JTree tree;
	
	/** The tree panel. */
	private final RdfTreePanel treePanel;

	/**
	 * Instantiates a new tree mouse listener.
	 * 
	 * @param treePanel
	 *            the tree panel
	 */
	public TreeMouseListener(RdfTreePanel treePanel) {
		tree = treePanel.getTree();
		this.treePanel = treePanel;

		// m_action = treePanel.getAction();
		// m_popup=treePanel.getPopupMenu();

	}

	/**
	 * Gets the tree.
	 * 
	 * @return the tree
	 */
	public JTree getTree() {
		return tree;
	}

	/**
	 * Constructor for TreeMouseListener.
	 * 
	 * @param e
	 *            the e
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// nothing needed yet
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger()) {
			final int x = e.getX();
			final int y = e.getY();
			System.out.println("x = " + x + " y = " + y);
			final TreePath path = tree.getPathForLocation(x, y);
			if (path != null) {
				if (tree.isExpanded(path)) {
					treePanel.getAction().putValue(Action.NAME, "Collapse");
				} else {
					treePanel.getAction().putValue(Action.NAME, "Expand");
				}
				treePanel.getPopupMenu().show(tree, x, y);
				treePanel.setClickedPath(path);
			}
		}
	}

	/**
	 * Sets the tree.
	 * 
	 * @param tree
	 *            the new tree
	 */
	public void setTree(JTree tree) {
		this.tree = tree;
	}

}
