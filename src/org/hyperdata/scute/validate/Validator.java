/*
 * Scute
 * 
 * Homepage: http://hyperdata.org/scute
 * 
 * License : http://www.apache.org/licenses/LICENSE-2.0
 * See also license.txt or http://hyperdata.org/wiki/Scute:License
 * 
 * Danny Ayers 2011
 * 
 */

package org.hyperdata.scute.validate;

import javax.swing.ImageIcon;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * @author danja
 *
 */
public class Validator {
	public static final int UNKNOWN = 0;
	public static final int VALID = 1;
	public static final int INVALID = 2;
	protected static final String[] LABEL = {"Unknown Validity", "Valid", "Invalid"};
	
	protected static final ImageIcon[] ICON = new ImageIcon[3];
	static {
		 // unknown
		ICON[0] = null;
		// valid
		ICON[1] = null;
		// invalid
		ICON[2] = null;
	}
	
	public ValidatorMessage validateModel(Model model){
		ValidatorMessage result = new ValidatorMessage();
		return result;
	}
}
