/**
 * 
 */
package org.hyperdata.scute.swing.status;

import javax.swing.JPanel;
import javax.swing.JTextPane;


/**
 * The Class StatusPane.
 *
 * @author danny
 */
public class StatusPane extends JPanel implements StatusChangeListener {

	private JTextPane textPane;
	
	/**
	 * Instantiates a new status pane.
	 *
	 * @param statusAction the status action
	 */
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
