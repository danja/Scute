/**
 * 
 */
package org.hyperdata.scute.sparql.endpoints;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 * @author danny
 *
 */
public class EndpointAction extends AbstractAction {

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Eggs are not supposed to be green.");
	}
}
