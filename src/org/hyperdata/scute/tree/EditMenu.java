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

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenu;

import org.hyperdata.scute.tree.actions.DeleteAction;
import org.hyperdata.scute.tree.actions.RenameAction;

/**
 * The Class EditMenu.
 */
public class EditMenu extends JMenu {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8324902999253358346L;
	
	/** The tree action. */
	private Action treeAction;
	
	/** The tree panel. */
	private final RdfTreePanel treePanel;;

	/**
	 * Instantiates a new edits the menu.
	 * 
	 * @param treePanel
	 *            the tree panel
	 */
	public EditMenu(RdfTreePanel treePanel) {
		super("Edit");
		this.treePanel = treePanel;
		init();
	}

	/**
	 * Gets the tree action.
	 * 
	 * @return the tree action
	 */
	public Action getTreeAction() {
		return treeAction;
	}

	/**
	 * Inits the.
	 */
	private void init() {
		treeAction = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -2988238299851150984L;

			@Override
			public void actionPerformed(ActionEvent e) {

				// move to RdfTreePanel
				if (treePanel.getClickedPath() == null)
					return;
				if (treePanel.getTree().isExpanded(treePanel.getClickedPath())) {
					treePanel.getTree()
							.collapsePath(treePanel.getClickedPath());
				} else {
					treePanel.getTree().expandPath(treePanel.getClickedPath());
				}
			}
		};
		add(treeAction);
		addSeparator();

		/*
		 * Action deleteAction = new AbstractAction("Delete") { public void
		 * actionPerformed(ActionEvent e) {
		 * 
		 * //System.out.println("CLASS= "+
		 * clickedPath.getLastPathComponent().getClass());
		 * 
		 * ((RdfTreeNode) clickedPath.getLastPathComponent()).deleteRequest();
		 * 
		 * tree.repaint(); } };
		 */
		final Action deleteAction = new DeleteAction(treePanel);
		add(deleteAction);

		/*
		 * Action renameAction = new AbstractAction("Rename") { public void
		 * actionPerformed(ActionEvent e) {
		 * 
		 * ((RdfTreeNode) clickedPath.getLastPathComponent()) .renameRequest();
		 * tree.repaint(); } };
		 */
		final Action renameAction = new RenameAction(treePanel);
		add(renameAction);
	}
}
