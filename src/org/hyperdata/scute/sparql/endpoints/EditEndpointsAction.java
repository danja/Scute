/**
 * 
 */
package org.hyperdata.scute.sparql.endpoints;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


/**
 * @author danny
 *
 */
public class EditEndpointsAction extends AbstractAction {

	private JTable endpointTable = null;
	private JDialog dialog;
	private Frame frame;
	private EndpointTableModel endpointTableModel;

	/**
	 * @param string
	 * @param endpointTableModel 
	 */
	public EditEndpointsAction(String string, EndpointTableModel endpointTableModel, Frame frame) {
		super(string);
		this.frame = frame;
		this.endpointTableModel = endpointTableModel;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(dialog == null){
			createEndpointEditFrame();
		}
		dialog.setVisible(true);
	}

	/**
	 * 
	 */
	private void createEndpointEditFrame() {
		dialog = new JDialog(frame, "Endpoints");
		// endpointTable = new SlidyTable(endpointTableModel);
		
		JPanel endpointEditPanel = new EndpointsEditPanel(dialog, endpointTableModel);
		
		dialog.add(endpointEditPanel);
		dialog.pack();
		
		dialog.setLocationRelativeTo(frame);
	}
}
