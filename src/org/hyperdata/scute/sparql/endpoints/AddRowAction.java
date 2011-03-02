/**
 * 
 */
package org.hyperdata.scute.sparql.endpoints;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Icon;

class AddRowAction extends AbstractAction {

	private EndpointTableModel model;
	private SlidyTable table;

	public AddRowAction(String label, Icon icon, SlidyTable table, EndpointTableModel model) {
		super(label, icon);
		this.model = model;
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// System.out.println("CREATE ROW");
		model.addEndpoint(new Endpoint("New name", ""));
		(new javax.swing.Timer(EndpointsEditPanel.DELAY, new ActionListener() {
			int i = table.convertRowIndexToView(model.getRowCount() - 1);
			int h = EndpointsEditPanel.START_HEIGHT;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (h < EndpointsEditPanel.END_HEIGHT) {
					table.setRowHeight(i, h++);
				} else {
					((javax.swing.Timer) e.getSource()).stop();
				}
			}
		})).start();
	}
}