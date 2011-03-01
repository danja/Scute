/**
 * 
 */
package org.hyperdata.scute.sparql.endpoints;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.TableModel;


/**
 * @author danny
 * 
 */
public class EndpointsTable extends JPanel {

	private SlidyTable table;
	static final int DELAY = 10;
	static final int START_HEIGHT = 4;
	static final int END_HEIGHT = 24;

	private static final Color evenColor = new Color(250, 250, 250);

	public EndpointsTable(EndpointTableModel endpointTableModel) {

		super(new BorderLayout());

		table = new SlidyTable(endpointTableModel);
		// table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
		table.setRowHeight(START_HEIGHT);
		for (int i = 0; i < endpointTableModel.getRowCount(); i++) {
			table.setRowHeight(i, END_HEIGHT);
		}

		JScrollPane scroll = new JScrollPane(table);
		scroll.setComponentPopupMenu(new TablePopupMenu(table, endpointTableModel));
		table.setInheritsPopupMenu(true);
		add(scroll);
		add(new JButton(new CreateRowAction("add", null, table,
				endpointTableModel)), BorderLayout.SOUTH);
		setPreferredSize(new Dimension(320, 240));
	}
}

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
		(new javax.swing.Timer(EndpointsTable.DELAY, new ActionListener() {
			int h = EndpointsTable.END_HEIGHT;

			@Override
			public void actionPerformed(ActionEvent e) {
				h--;
				if (h > EndpointsTable.START_HEIGHT) {
					for (int i = selection.length - 1; i >= 0; i--) {
						table.setRowHeight(selection[i], h);
					}
				} else {
					((javax.swing.Timer) e.getSource()).stop();
					for (int i = selection.length - 1; i >= 0; i--) {
						model.removeRow(table
								.convertRowIndexToModel(selection[i]));
					}
				}
			}
		})).start();
	}
}

class TablePopupMenu extends JPopupMenu {
	
	private Action deleteAction = null;
	private SlidyTable table;
	private EndpointTableModel model;

	public TablePopupMenu(SlidyTable table, EndpointTableModel model) {
		super();
		this.table = table;
		this.model = model;
		deleteAction = new DeleteRowAction("Delete", null, table, model);
		add(new CreateRowAction("Add", null,  table, model));
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

class CreateRowAction extends AbstractAction {

	private EndpointTableModel model;
	private SlidyTable table;

	public CreateRowAction(String label, Icon icon, SlidyTable table, EndpointTableModel model) {
		super(label, icon);
		this.model = model;
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		testCreateActionPerformed(evt);
	}

	private void testCreateActionPerformed(ActionEvent e) {

		model.addEndpoint(new Endpoint("New name", ""));
		(new javax.swing.Timer(EndpointsTable.DELAY, new ActionListener() {
			int i = table.convertRowIndexToView(model.getRowCount() - 1);
			int h = EndpointsTable.START_HEIGHT;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (h < EndpointsTable.END_HEIGHT) {
					table.setRowHeight(i, h++);
				} else {
					((javax.swing.Timer) e.getSource()).stop();
				}
			}
		})).start();
	}
}