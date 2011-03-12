/**
 * 
 */
package org.hyperdata.scute.main;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JEditorPane;

import org.hyperdata.scute.source.EditorPane;
import org.hyperdata.scute.toolbars.source.EditorToolbar;

/**
 * @author danny
 *
 */
public class FocusMonitor implements FocusListener {

	private EditorToolbar editorToolbar;

	public void setEditorToolbar(EditorToolbar editorToolbar){
		this.editorToolbar = editorToolbar;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusGained(FocusEvent event) {
		Object source = event.getSource();
		if(source instanceof EditorPane){
			editorToolbar.enableAll();
			editorToolbar.setEditorPane((EditorPane)source);
			editorToolbar.setVisible(true);
		} else {
			editorToolbar.setVisible(false);
		}
		// System.out.println("FOCUS="+event.getSource());
	}

	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusLost(FocusEvent event) {
		Object source = event.getSource();
		if(source instanceof EditorPane){
			((EditorPane)source).save();
		}
		editorToolbar.setVisible(false);
	}

}
