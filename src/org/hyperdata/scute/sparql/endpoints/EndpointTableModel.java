/**
 * 
 */
package org.hyperdata.scute.sparql.endpoints;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * @author danny
 * 
 *         could do with much better integration
 * 
 *         thin wrapper around endpoint list
 * 
 *         all methods of TableModel included for potential use later
 */
public class EndpointTableModel extends DefaultTableModel { // implements
															// TableModelListener

	private EndpointListModel endpointListModel;

	private EndpointTableModel() {
	}

	public EndpointTableModel(EndpointListModel endpointListModel) {
		super();
		this.endpointListModel = endpointListModel;
		for (int i = 0; i < endpointListModel.getSize(); i++) {
			Endpoint endpoint = (Endpoint) endpointListModel.getElementAt(i);
			Object[] row = { endpoint.getLabel(), endpoint.getUri() };
			super.addRow(row);
		}
	}

	public void saveEndpointsToFile() {
		endpointListModel.saveEndpointsToFile();
	}

	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Name";
		case 1:
			return "URI";
		default:
			return ""; // throw exception?
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return 2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		// System.out.println("hereD"+endpointListModel);
		// if(endpointListModel == null){
		// return 0;
		// }
		// return endpointListModel.getSize();
		return super.getRowCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int row, int column) {
		Endpoint endpoint = (Endpoint) endpointListModel.getElementAt(row);
		switch (column) {
		case 0:
			return endpoint.getLabel();
		case 1:
			return endpoint.getUri();
		default:
			return ""; // throw exception?
		}
	}

	@Override
	public void setValueAt(Object value, int row, int column) {
		// data[row][col] = value;
		// System.out.println("setValueAt "+value);
		Endpoint endpoint = (Endpoint) endpointListModel.getElementAt(row);
		switch (column) {
		case 0:
			endpoint.setLabel(value.toString());
			break;
		case 1:
			if (isURI(value.toString())) {
				endpoint.setUri(value.toString());
			}
			break;
		default: // exception?
			break;
		}
		fireTableCellUpdated(row, column);
	}

	/**
	 * @param string
	 * @return
	 */
	private boolean isURI(String string) {
		if (string.startsWith("http://") || string.startsWith("file://")) {
			return true;
		}
		return false;
	}

	/**
	 * @param endpoint
	 */
	public void addEndpoint(Endpoint endpoint) {
		endpointListModel.addEndpoint(endpoint);
		Object[] obj = { endpoint.getLabel(), endpoint.getUri() };
		super.addRow(obj);
	}

	public void removeRow(int row) {
		endpointListModel.removeElementAt(row);
		super.removeRow(row);
	}

	// public void tableChanged(TableModelEvent e) {
	// int row = e.getFirstRow();
	// int column = e.getColumn();
	// TableModel model = (TableModel)e.getSource();
	// String columnName = model.getColumnName(column);
	// Object data = model.getValueAt(row, column);
	// System.out.println("CHENGED");
	// System.out.println(columnName);
	// System.out.println(data);
	//
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return true; // EDITABLE
	}

	// public void insertRow(int row, Object[] rowData){
	// super.insertRow(row, rowData);
	// System.out.println(rowData);
	// System.out.println("INSERT");
	// }

	// the rest pass calls to super

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.table.TableModel#addTableModelListener(javax.swing.event.
	 * TableModelListener)
	 */
	@Override
	public void addTableModelListener(TableModelListener listener) {
		super.addTableModelListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return super.getColumnClass(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.table.TableModel#removeTableModelListener(javax.swing.event
	 * .TableModelListener)
	 */
	@Override
	public void removeTableModelListener(TableModelListener listener) {
		super.removeTableModelListener(listener);
	}

}
