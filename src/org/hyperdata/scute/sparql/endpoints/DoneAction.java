/**
 * 
 */
package org.hyperdata.scute.sparql.endpoints;

import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * @author danny
 *
 */
public class DoneAction extends AbstractAction {

	private EndpointTableModel endpointTableModel;
	private Window window;

	/**
	 * @param string
	 * @param endpointTableModel
	 */
	public DoneAction(String string, EndpointTableModel endpointTableModel, Window window) {
		super(string);
		this.endpointTableModel = endpointTableModel;
		this.window = window;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		endpointTableModel.saveEndpointsToFile();
		endpointTableModel.fireTableDataChanged();
		window.dispose();
	}
}
