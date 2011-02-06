/**
 * 
 */
package org.hyperdata.scute.validate;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 * @author danny
 *
 */
public class StatusAction extends AbstractAction {

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Runnable task = getStatusTask();
	Thread t = new Thread(task);
	t.start();
	}

	public void setStatusTask(Runnable task){
		putValue("StatusTask", task);
	}
	
	/**
	 * @return
	 */
	public Runnable getStatusTask() {
		Object object = getValue("StatusTask");
		if(object != null){
			return (Runnable)object;
		}
		return null;
	}


}
