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
package org.hyperdata.scute.swing;

import java.awt.Component;
import java.awt.Frame;

/**
 * The Interface ToolsInterface.
 */
public interface ToolsInterface {

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
	public void openFile();

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
	public void checkText();

	/**
	 * @return
	 */
	public Frame getFrame();
}
