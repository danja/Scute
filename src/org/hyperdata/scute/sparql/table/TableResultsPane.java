/**
 * 
 */
package org.hyperdata.scute.sparql.table;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.FocusListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;

import org.jdesktop.swingx.JXTable;

/**
 * @author danny
 * 
 */
public class TableResultsPane extends JPanel {

	private JTable table;
	private ResultSetTableModel tableModel; // recreated with every set of interesting results, kept for clearing

	public TableResultsPane() {
		super(new BorderLayout());
		table = new JTable();
		table.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		table.setDragEnabled(true);
		
		// TODO fix renderer
		// table.setDefaultRenderer(Object.class, new ResultsTableCellRenderer());

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
