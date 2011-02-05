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

package org.hyperdata.scute.validate;

import java.util.EventObject;

import javax.swing.ImageIcon;

/**
 * @author danja
 *
 */
public class StatusEvent {

	private int status = 0;
	private String description = " (no report provided)";
	
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLabel() {
		return StatusMonitor.LABEL[this.status];
	}
	
	public ImageIcon getIcon(){
		return StatusMonitor.ICON[this.status];
	}

	/**
	 * @param string
	 */
	public void setDescription(String description) {
		this.description  = description;
	}
	
	public String getDescription(){
		return description;
	}
	
	
}
