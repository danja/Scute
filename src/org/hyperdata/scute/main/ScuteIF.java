/**
 * 
 */
package org.hyperdata.scute.main;

/**
 * @author danny
 *
 */
public interface ScuteIF {
	public void setSelectedCard(String selectedView);

	/**
	 * 
	 */
	public void setDefaultSplit();

	/**
	 * @param b
	 */
	public void showTools(boolean b);

	/**
	 * @param b
	 */
	public void showStatusBar(boolean b);
}
