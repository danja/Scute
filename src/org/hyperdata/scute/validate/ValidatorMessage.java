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

import javax.swing.ImageIcon;

/**
 * @author danja
 *
 */
public class ValidatorMessage {

	private int status = 0;
	
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLabel() {
		return Validator.LABEL[this.status];
	}
	
	public ImageIcon getIcon(){
		return Validator.ICON[this.status];
	}
}
