package org.hyperdata.scute.editortools.undo;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.undo.CannotRedoException;

import org.hyperdata.scute.source.EditorPane;

/**
 * 
 */

public class RedoAction extends AbstractAction {

	private EditorPane editorPane;

	public RedoAction(EditorPane editorPane) {
		super("Redo");
		this.editorPane = editorPane;
		setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.editorPane.getUndoManager().redo();
		} catch (CannotRedoException ex) {
			System.out.println("Unable to redo: " + ex);
			ex.printStackTrace();
		}
		update();
		this.editorPane.getUndoAction().update();
	}

	public void update() {
		if (this.editorPane.getUndoManager().canRedo()) {
			setEnabled(true);
			putValue(Action.NAME, this.editorPane.getUndoManager().getRedoPresentationName());
		} else {
			setEnabled(false);
			putValue(Action.NAME, "Redo");
		}
	}
}