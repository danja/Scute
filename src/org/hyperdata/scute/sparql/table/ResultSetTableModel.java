/**
 * 
 */
package org.hyperdata.scute.sparql.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFactory;
import com.hp.hpl.jena.query.ResultSetRewindable;
import com.hp.hpl.jena.rdf.model.RDFNode;

import org.hyperdata.scute.rdf.RdfUtils;


public class ResultSetTableModel extends AbstractTableModel {

	private ResultSetRewindable resultSet;
	private List<String> columnNames;
	private List<QuerySolution> results = new ArrayList<QuerySolution>();

	public ResultSetTableModel(ResultSet resultSetRaw){
		super();
		this.resultSet = ResultSetFactory.copyResults(resultSetRaw); // just in case...
		columnNames = resultSet.getResultVars();
		while(resultSet.hasNext()){
			results.add(resultSet.next());
		}
	}
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int i) {
		// System.out.println("NAME="+columnNames.get(i));
		return columnNames.get(i);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		if(results == null){
			return 0; // on init
		}
		return results.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int row, int column) {
		QuerySolution solution = results.get(row);
		RDFNode node = solution.get(getColumnName(column));
		if(node == null){
			return "";
		}
		return RdfUtils.nodeToDisplayString(node);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int, int)
	 */
//	@Override
//	public void setValueAt(Object arg0, int arg1, int arg2) {
//		// TODO Auto-generated method stub
//		
//	}

}
