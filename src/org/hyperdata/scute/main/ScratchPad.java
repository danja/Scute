/**
 * 
 */
package org.hyperdata.scute.main;

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
	}

	/* (non-Javadoc) 
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
