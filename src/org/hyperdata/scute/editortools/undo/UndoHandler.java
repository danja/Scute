package org.hyperdata.scute.editortools.undo;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

import org.hyperdata.scute.source.EditorPane;

/**
 * 
 */

public class UndoHandler implements UndoableEditListener {

	/**
	 * 
	 */
	private EditorPane editorPane;

	/**
	 * @param editorPane
	 */
	public UndoHandler(EditorPane editorPane) {
		this.editorPane = editorPane;
	}

	/**
	 * Messaged when the Document has created an edit, the edit is added to
	 * <code>undo</code>, an instance of UndoManager.
	 */
	public void undoableEditHappened(UndoableEditEvent e) {
		this.editorPane.undoManager.addEdit(e.getEdit());
		this.editorPane.undoAction.update();
		this.editorPane.redoAction.update();
	}
}