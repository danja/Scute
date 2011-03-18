/**
 * 
 */
package org.hyperdata.scute.sparql.endpoints;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;

import javax.swing.*;


/**
 * @author danny
 * 
 */
public class EndpointsEditPanel extends JPanel {

	private JTable table;
	static final int DELAY = 10;
	static final int START_HEIGHT = 4;
	static final int END_HEIGHT = 24;

	private static final Color evenColor = new Color(250, 250, 250);

	public EndpointsEditPanel(Window window, EndpointTableModel endpointTableModel) {

		super(new BorderLayout());
		
		// this.dialog = dialog;

		table = new JTable(endpointTableModel);
		// table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
	//	table.setRowHeight(START_HEIGHT);
//		for (int i = 0; i < endpointTableModel.getRowCount(); i++) {
//			table.setRowHeight(i, END_HEIGHT);
//		}

		JScrollPane scroll = new JScrollPane(table);
		scroll.setComponentPopupMenu(new TablePopupMenu(table, endpointTableModel));
		table.setInheritsPopupMenu(true);
		add(scroll);
		
		JPanel tools = new JPanel();
		
		JButton addButton = new JButton();
		Action addAction = new AddRowAction("Add", null, table, endpointTableModel);
		addButton.setAction(addAction);
		tools.add(addButton);
		
		JButton removeButton = new JButton();
		Action removeAction = new DeleteRowAction("Remove", null, table, endpointTableModel);
		removeButton.setAction(removeAction);
		tools.add(removeButton);
		
		JButton doneButton = new JButton();
		Action doneAction = new DoneAction("Done", endpointTableModel, window);
		doneButton.setAction(doneAction);
		tools.add(doneButton);
		
		add(tools, BorderLayout.SOUTH);
	
		setPreferredSize(new Dimension(400, 300));
	}
}