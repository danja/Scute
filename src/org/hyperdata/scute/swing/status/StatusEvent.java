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

import java.util.EventObject;

import javax.swing.ImageIcon;


/**
 * @author danja
 *
 * a StatusEvent has a status
 * 
 */
public class StatusEvent {

	private int status = 0;
	private String description = "";
	
	/**
	 * @param status initial status
	 */
	public StatusEvent(int status) {
		this.status = status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return this.status;
	}

	public String getStatusString() {
		return StatusMonitor.DEFAULT_DESCRIPTION[this.status];
	}
	
	public ImageIcon getIcon(){
		return StatusMonitor.ICON[this.status];
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}

}
