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
package org.hyperdata.scute.toolbars.file;

import java.awt.Frame;

/**
 * The Interface ToolsInterface.
 */
public interface FilesInterface {

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
	public void newModel();

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
