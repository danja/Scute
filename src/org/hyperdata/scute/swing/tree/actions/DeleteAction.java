package org.hyperdata.scute.swing.tree.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.hyperdata.scute.swing.rdftree.RdfTreeNode;
import org.hyperdata.scute.swing.tree.RdfTreePanel;

public class DeleteAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4723921004105606994L;
	private final RdfTreePanel treePanel;

	public DeleteAction(RdfTreePanel treePanel) {
		super("Delete");
		this.treePanel = treePanel;
	}

	public void actionPerformed(ActionEvent arg0) {

		((RdfTreeNode) treePanel.getClickedPath().getLastPathComponent())
				.deleteRequest();

		treePanel.getTree().repaint();
	}

}
