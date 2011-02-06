/**
 * 
 */
package org.hyperdata.scute.swing.status;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextPane;


/**
 * @author danny
 *
 */
public class StatusPane extends JPanel implements StatusChangeListener {

	private JTextPane textPane;
	
	public StatusPane(StatusAction statusAction){
		super();
		statusAction.addStatusChangeListener(this);
		textPane = new JTextPane();
		//textPane.setText("qwe");
		add(textPane);
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.swing.status.StatusChangeListener#statusChanged(org.hyperdata.scute.swing.status.StatusEvent)
	 */
	@Override
	public void statusChanged(StatusEvent status) {
		textPane.setText(status.getDescription());
	}

}
