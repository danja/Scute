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
package org.hyperdata.scute.main;

/**
 * The Interface GeneralApplication.
 */
public interface GeneralApplication {

	/**
	 * Log print err.
	 * 
	 * @param string
	 *            the string
	 */
	public void logPrintErr(String string);

	/**
	 * Log println.
	 * 
	 * @param string
	 *            the string
	 */
	public void logPrintln(String string);

	/**
	 * Wait cursor.
	 * 
	 * @param wait
	 *            the wait
	 */
	public void waitCursor(boolean wait);
}
