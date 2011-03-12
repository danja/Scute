/**
 * 
 */
package org.hyperdata.scute.main;

import java.awt.Font;

import javax.swing.event.ChangeEvent;

import org.hyperdata.scute.source.EditorPane;

/**
 * @author danny
 *
 */
public class ScratchPad extends EditorPane {

	/**
	 * @param syntax
	 */
	public ScratchPad(String syntax) {
		super(syntax);
		setFilename(Config.SCRATCH_FILENAME);
		setFont(new Font("Monospaced", Font.PLAIN, 10)); // bit smaller
	}

	/* (non-Javadoc) 
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
