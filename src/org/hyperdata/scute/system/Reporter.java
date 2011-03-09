/**
 * 
 */
package org.hyperdata.scute.system;

import javax.swing.JOptionPane;

import org.jdesktop.swingx.error.ErrorInfo;
import org.jdesktop.swingx.error.ErrorReporter;

/**
 * @author danny
 *
 */
public class Reporter implements ErrorReporter {

	/* (non-Javadoc)
	 * @see org.jdesktop.swingx.error.ErrorReporter#reportError(org.jdesktop.swingx.error.ErrorInfo)
	 */
	@Override
	public void reportError(ErrorInfo info) throws NullPointerException {
		JOptionPane.showMessageDialog(null, "Not yet implemented: "+info);	
	}

}
