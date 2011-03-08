package org.hyperdata.scute.editortools.undo;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.undo.CannotUndoException;

import org.hyperdata.scute.source.EditorPane;

/**
 * 
 */

public class UndoAction extends AbstractAction {
	/**
	 * 
	 */
	private EditorPane editorPane;

	public UndoAction(EditorPane editorPane) {
		super("Undo");
		this.editorPane = editorPane;
		setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.editorPane.undoManager.undo();
		} catch (CannotUndoException ex) {
			System.out.println("Unable to undo: " + ex);
			ex.printStackTrace();
		}
		update();
		this.editorPane.redoAction.update();
	}

	public void update() {
		if (this.editorPane.undoManager.canUndo()) {
			setEnabled(true);
			putValue(Action.NAME, this.editorPane.undoManager.getUndoPresentationName());
		} else {
			setEnabled(false);
			putValue(Action.NAME, "Undo");
		}
	}
}