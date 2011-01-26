package org.hyperdata.scute.swing.tree;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenu;

import org.hyperdata.scute.swing.tree.actions.DeleteAction;
import org.hyperdata.scute.swing.tree.actions.RenameAction;

public class EditMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8324902999253358346L;
	private Action treeAction;
	private final RdfTreePanel treePanel;;

	public EditMenu(RdfTreePanel treePanel) {
		super("Edit");
		this.treePanel = treePanel;
		init();
	}

	public Action getTreeAction() {
		return treeAction;
	}

	private void init() {
		treeAction = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -2988238299851150984L;

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
