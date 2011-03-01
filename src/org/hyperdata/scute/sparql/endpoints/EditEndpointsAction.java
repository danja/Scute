/**
 * 
 */
package org.hyperdata.scute.sparql.endpoints;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
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
	private JDialog endpointEditFrame;
	private Frame frame;

	/**
	 * @param string
	 * @param endpointTableModel 
	 */
	public EditEndpointsAction(String string, EndpointTableModel endpointTableModel, Frame frame) {
		super(string);
		this.frame = frame;
		endpointTable = new SlidyTable(endpointTableModel);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(endpointEditFrame == null){
			createEndpointEditFrame();
		}
		endpointEditFrame.setVisible(true);
	}

	/**
	 * 
	 */
	private void createEndpointEditFrame() {
		endpointEditFrame = new JDialog(frame);
		JPanel endpointEditPanel = new JPanel(new BorderLayout());
		endpointEditPanel.add(endpointTable, BorderLayout.CENTER);
		endpointEditFrame.add(endpointEditPanel);
		endpointEditFrame.pack();
	}
	
	private void createEndpointTable(){
//        table.setAutoCreateRowSorter(true);
//        table.setRowHeight(START_HEIGHT);
//        for(int i=0;i<model.getRowCount();i++) table.setRowHeight(i, END_HEIGHT);
//
//        JScrollPane scroll = new JScrollPane(table);
//        scroll.setComponentPopupMenu(new TablePopupMenu());
//        table.setInheritsPopupMenu(true);
//        add(scroll);
//        add(new JButton(new TestCreateAction("add", null)), BorderLayout.SOUTH);
//        setPreferredSize(new Dimension(320, 240));
	}
}
