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
	public void newFile();

	/**
	 * Open file.
	 */
	public void openFile();

	/**
	 * Save as file.
	 */
	public void saveAsFile();

	/**
	 * Save file.
	 */
	public void saveFile();

	/**
	 * Check text.
	 */
	public void checkText();
}
