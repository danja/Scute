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

import org.hyperdata.scute.triples.BetterJTable;


/**
 * @author danny
 *
 */
public class TableResultsPane extends JPanel {

	private BetterJTable table;
	
	public TableResultsPane(){
		super(new BorderLayout());
		table = new BetterJTable();
		table.setDragEnabled(true);
		add(new JScrollPane(table),BorderLayout.CENTER);
	}
	
	public void addFocusListener(FocusListener focusListener){
		table.addFocusListener(focusListener);
	}
	
	public void setResults(ResultSet resultSet){
		TableModel tableModel = new ResultSetTableModel(resultSet);
		table.setModel(tableModel);
table.repaint();
	}
}
