/**
 * 
 */
package org.hyperdata.scute.swing.status;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;


/**
 * The Class StatusAction.
 *
 * @author danny
 */
public class StatusAction extends AbstractAction implements
		StatusChangeListener {

	private StatusEvent status = new StatusEvent(StatusMonitor.AMBER); // uncertain
																		// status

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		runTask();
	}

	/**
	 * Run task.
	 */
	private void runTask() {
		Runnable task = getStatusTask();
		Thread t = new Thread(task);
		t.start();
	}

	/**
	 * Sets the status task.
	 *
	 * @param task the new status task
	 */
	public void setStatusTask(Runnable task) {
		putValue("StatusTask", task);
	}

	/**
	 * Gets the status task.
	 *
	 * @return the status task
	 */
	public StatusTask getStatusTask() {
		Object object = getValue("StatusTask");
		if (object != null) {
			return (StatusTask) object;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.hyperdata.scute.swing.status.StatusChangeListener#statusChanged(org
	 * .hyperdata.scute.swing.status.StatusEvent)
	 */
	@Override
	public void statusChanged(StatusEvent status) {
		this.status = status;
	}

	/**
	 * Adds the status change listener.
	 *
	 * @param listener the listener
	 */
	public void addStatusChangeListener(StatusChangeListener listener) { // pass it on
		getStatusTask().addStatusListener(listener);
	}


}
