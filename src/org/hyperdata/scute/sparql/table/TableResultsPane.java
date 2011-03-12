/**
 * 
 */
package org.hyperdata.scute.sparql.table;

import java.awt.BorderLayout;
import java.awt.event.FocusListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.hp.hpl.jena.query.ResultSet;

import org.jdesktop.swingx.JXTable;

/**
 * @author danny
 * 
 */
public class TableResultsPane extends JPanel {

	private JXTable table;
	private ResultSetTableModel tableModel; // recreated with every set of interesting results, kept for clearing

	public TableResultsPane() {
		super(new BorderLayout());
		table = new JXTable();
		table.setDragEnabled(true);
		//table.setEditable(true);
		add(new JScrollPane(table), BorderLayout.CENTER);
	}

	public void addFocusListener(FocusListener focusListener) {
		table.addFocusListener(focusListener);
	}

	public void setResults(ResultSet resultSet) {
		tableModel = new ResultSetTableModel(resultSet);
		table.setModel(tableModel);
		table.validate(); // is enough?
	}

	/**
	 * 
	 */
	public void clear() {
		tableModel.clear();
		table.validate(); // is enough?
	}
}
