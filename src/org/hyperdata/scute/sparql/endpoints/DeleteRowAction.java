/**
 * 
 */
package org.hyperdata.scute.sparql.endpoints;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Icon;

class DeleteRowAction extends AbstractAction {
	
	private SlidyTable table;
	private EndpointTableModel model;

	public DeleteRowAction(String label, Icon icon, SlidyTable table,
			EndpointTableModel model) {
		super(label, icon);
		this.table = table;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		deleteActionPerformed(evt);
	}

	public void deleteActionPerformed(ActionEvent evt) {
		final int[] selection = table.getSelectedRows();
		if (selection == null || selection.length <= 0)
			return;
		(new javax.swing.Timer(EndpointsEditPanel.DELAY, new ActionListener() {
			int h = EndpointsEditPanel.END_HEIGHT;

			@Override
			public void actionPerformed(ActionEvent e) {
				h--;
				if (h > EndpointsEditPanel.START_HEIGHT) {
					for (int i = selection.length - 1; i >= 0; i--) {
						table.setRowHeight(selection[i], h);
					}
				} else {
					((javax.swing.Timer) e.getSource()).stop();
					for (int i = selection.length - 1; i >= 0; i--) {
						model.removeRow(table.convertRowIndexToModel(selection[i]));
					}
				}
			}
		})).start();
	}
}