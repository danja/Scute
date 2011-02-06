/**
 * 
 */
package org.hyperdata.scute.swing.status;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * @author danny
 * 
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
	 * 
	 */
	private void runTask() {
		Runnable task = getStatusTask();
		Thread t = new Thread(task);
		t.start();
	}

	public void setStatusTask(Runnable task) {
		putValue("StatusTask", task);
	}

	/**
	 * @return
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
	 * @param statusPane
	 */
	public void addStatusChangeListener(StatusChangeListener listener) { // pass it on
		getStatusTask().addStatusListener(listener);
	}


}
