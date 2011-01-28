package org.hyperdata.scute.tree.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.hyperdata.scute.tree.RdfTreeNode;
import org.hyperdata.scute.tree.RdfTreePanel;

public class RenameAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5693309851417366965L;
	private final RdfTreePanel treePanel;

	public RenameAction(RdfTreePanel treePanel) {
		super("Rename");
		this.treePanel = treePanel;
	}

	public void actionPerformed(ActionEvent arg0) {
		((RdfTreeNode) treePanel.getClickedPath().getLastPathComponent())
				.renameRequest();
		treePanel.getTree().repaint();
	}

}
