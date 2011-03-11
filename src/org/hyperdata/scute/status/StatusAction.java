/**
 * 
 */
package org.hyperdata.scute.status;

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

	private Thread thread;

	public StatusAction() {
		super();
	}

	public StatusAction(String label) {
		super(label);
	}

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
		System.out.println("runTask");
		thread = new Thread(getStatusTask());
		thread.start();
	}

	public void stop() {
		if (thread != null) {
			System.out.println("STOP!");
			thread.interrupt();
			thread = null; // drastic, but what else to do?
			getStatusTask().stateChanged(new StatusEvent(StatusMonitor.GREEN)); // reset
		}

	}

	/**
	 * Sets the status task.
	 * 
	 * @param task
	 *            the new status task
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
		System.out.println("XXXXXXXXXXXXXXX getValue(StatusTask)="+object);
		if (object != null) {
			System.out.println("XXXXXXXXXXXXXXX getValue(StatusTask)="+((StatusTask) object).getClass());
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
	 * @param listener
	 *            the listener
	 */
	public void addStatusChangeListener(StatusChangeListener listener) { // pass
																			// it
																			// on
		getStatusTask().addStatusListener(listener);
	}
}
