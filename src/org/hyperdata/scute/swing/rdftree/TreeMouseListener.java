package org.hyperdata.scute.swing.rdftree;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

import org.hyperdata.scute.swing.tree.RdfTreePanel;

/**
 * Picks up mouse events on the RDF tree view
 * 
 */
public class TreeMouseListener extends MouseAdapter {
	private JTree tree;
	private final RdfTreePanel treePanel;

	public TreeMouseListener(RdfTreePanel treePanel) {
		tree = treePanel.getTree();
		this.treePanel = treePanel;

		// m_action = treePanel.getAction();
		// m_popup=treePanel.getPopupMenu();

	}

	public JTree getTree() {
		return tree;
	}

	/**
	 * Constructor for TreeMouseListener.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// nothing needed yet
	}

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

	public void setTree(JTree tree) {
		this.tree = tree;
	}

}
