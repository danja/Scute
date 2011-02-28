/**
 * 
 */
package org.hyperdata.scute.swing.status;

import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 * The Class StatusPane.
 *
 * @author danny
 */
public class StatusPane extends JPanel implements StatusChangeListener {

	private JTextArea textArea;
	
	/**
	 * Instantiates a new status pane.
	 *
	 * @param statusAction the status action
	 */
	public StatusPane(StatusAction statusAction){
		super();
		statusAction.addStatusChangeListener(this);
		textArea = new JTextArea();
		textArea.setEditable(false);
		add(textArea);
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.swing.status.StatusChangeListener#statusChanged(org.hyperdata.scute.swing.status.StatusEvent)
	 */
	@Override
	public void statusChanged(StatusEvent status) {
		textArea.setText(status.getDescription());
	}

}
