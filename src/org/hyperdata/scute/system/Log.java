/**
 * 
 */
package org.hyperdata.scute.system;

import java.util.logging.Level;

import org.hdesktop.swingx.JXErrorPane;
import org.hdesktop.swingx.error.ErrorInfo;

import org.hyperdata.scute.system.panels.LogPane;

/**
 * @author danny
 * 
 */
public class Log {

	// Log.exception(exception);
	public static void exception(Exception exception) {
		JXErrorPane errorPane = new JXErrorPane();

		ErrorInfo info = new ErrorInfo("Application Error",
				"Application Error", null, "category", exception, Level.ALL,
				null);

		errorPane.setErrorInfo(info);
		errorPane.setErrorReporter(new Reporter());
		JXErrorPane.showDialog(null, errorPane);

		LogPane.err(exception.getMessage());
		Throwable cause = exception.getCause();
		if (cause != null) {
			LogPane.err("Caused by:" + cause.getMessage());
		}
		// Log.exception(exception);
	}

}
