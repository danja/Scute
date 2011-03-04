/*
 * Scute
 * 
 * Homepage: http://hyperdata.org/scute
 * 
 * License : http://www.apache.org/licenses/LICENSE-2.0
 * See also license.txt or http://hyperdata.org/wiki/Scute:License
 * 
 * Danny Ayers 2011
 */

package org.hyperdata.scute.swing.status;

import javax.swing.ImageIcon;
import javax.swing.text.BadLocationException;


/**
 * The Class StatusEvent.
 *
 * @author danja
 * 
 * a StatusEvent has a status
 */
public class StatusEvent {

	private int status = 0;
	private String description = "";
	
	/**
	 * Instantiates a new status event.
	 *
	 * @param status initial status
	 */
	public StatusEvent(int status) {
		this.status = status;
	}

	/**
	 * @param red
	 * @param message
	 */
	public StatusEvent(int status, String message) {
		this(status);
		setDescription(message);
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public int getStatus() {
		return this.status;
	}

	/**
	 * Gets the status string.
	 *
	 * @return the status string
	 */
	public String getStatusString() {
		return StatusMonitor.DEFAULT_DESCRIPTION[this.status];
	}
	
	/**
	 * Gets the icon.
	 *
	 * @return the icon
	 */
	public ImageIcon getIcon(){
		return StatusMonitor.ICON[this.status];
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}
}
