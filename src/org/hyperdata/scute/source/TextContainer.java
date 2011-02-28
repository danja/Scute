/**
 * 
 */
package org.hyperdata.scute.source;

import java.awt.event.FocusListener;

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
