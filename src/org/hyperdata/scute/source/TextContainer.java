/**
 * 
 */
package org.hyperdata.scute.source;

import java.awt.event.FocusListener;

import org.hyperdata.scute.autosave.Saveable;

/**
 * The Interface TextContainer.
 * 
 * @author danny
 */
public interface TextContainer extends FocusListener, Saveable {

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

	public void setFilename(String filename);
	
	
	public void load();
}
