/**
 * 
 */
package org.hyperdata.scute.toolbars.file;

import java.awt.Frame;

/**
 * @author danny
 *
 */
public interface FilesInterface {

	public void newFile();
	/**
	 * Clone file.
	 */
	public void cloneFile();

	/**
	 * Close file.
	 */
	public void closeFile();

	/**
	 * Exit.
	 */
	public void exit();

	/**
	 * New file.
	 */


	/**
	 * Open file.
	 */
	public void open();

	/**
	 * Save as file.
	 */
	public void saveAs();

	/**
	 * Save file.
	 */
	public void save();

	/**
	 * Check text.
	 */
	// public void checkText();

	/**
	 * @return
	 */
	public Frame getFrame();
}
