/**
 * 
 */
package org.hyperdata.scute.source;

import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.hyperdata.scute.main.Config;

/**
 * The Interface TextContainer.
 * 
 * @author danny
 */
public interface TextContainer extends FocusListener {

	/**
	 * Gets the syntax.
	 * 
	 * @return the syntax
	 */
	public String getSyntax();

	/**
	 * Gets the text.
	 * 
	 * @return the text
	 */
	public String getText();


	/**
	 * @return
	 */
	public String getFilename();

	/**
	 * 
	 */
	public void save();
}
