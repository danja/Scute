/**
 * 
 */
package org.hyperdata.scute.system;

import javax.swing.JOptionPane;

//import org.hdesktop.swingx.error.ErrorInfo;
//import org.hdesktop.swingx.error.ErrorReporter;

import org.jdesktop.swingx.error.*;
import org.jdesktop.swingx.action.*;
import org.jdesktop.swingx.MultiSplitLayout.*;
/**
 * @author danny
 *
 */
public class Reporter implements ErrorReporter {

	/* (non-Javadoc)
	 * @see org.hdesktop.swingx.error.ErrorReporter#reportError(org.hdesktop.swingx.error.ErrorInfo)
	 */
	@Override
	public void reportError(ErrorInfo info) throws NullPointerException {
		JOptionPane.showMessageDialog(null, "Not yet implemented: "+info);	
	}

}
