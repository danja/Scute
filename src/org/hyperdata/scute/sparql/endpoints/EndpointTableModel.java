/**
 * 
 */
package org.hyperdata.scute.sparql.endpoints;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.hp.hpl.jena.rdf.model.Model;



/**
 * @author danny
 * 
 * thin wrapper around endpoint list
 */
public class EndpointTableModel extends DefaultTableModel {

	private EndpointListModel endpointListModel;

	private EndpointTableModel(){}
	
	public EndpointTableModel(EndpointListModel endpointListModel){
		// super();
		System.out.println("hereB");
		this.endpointListModel = endpointListModel;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return 2;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		System.out.println("hereD"+endpointListModel);
		if(endpointListModel == null){
			return 0;
		}
		return endpointListModel.getSize();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int row, int column) {
		Endpoint endpoint = (Endpoint) endpointListModel.getElementAt(row);
		switch(column){
		case 0:
			return endpoint.getLabel();
		case 1:
			return endpoint.getUri();
			default:
				return ""; // throw exception?
		}
	}

	/**
	 * @param endpoint
	 */
	public void addEndpoint(Endpoint endpoint) {
		endpointListModel.addEndpoint(endpoint);
	}

}
