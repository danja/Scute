/**
 * 
 */
package org.hyperdata.scute.sparql.endpoints;

import java.awt.Component;

import javax.swing.Action;
import javax.swing.JPopupMenu;

class TablePopupMenu extends JPopupMenu {
	
	private Action deleteAction = null;
	private SlidyTable table;
	private EndpointTableModel model;

	public TablePopupMenu(SlidyTable table, EndpointTableModel model) {
		super();
		this.table = table;
		this.model = model;
		deleteAction = new DeleteRowAction("Delete", null, table, model);
		add(new AddRowAction("Add", null,  table, model));
		addSeparator();
		add(deleteAction);
	}

	@Override
	public void show(Component c, int x, int y) {
		int[] l = table.getSelectedRows();
		deleteAction.setEnabled(l != null && l.length > 0);
		super.show(c, x, y);
	}
}