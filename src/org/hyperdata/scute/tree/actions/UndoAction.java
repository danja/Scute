package org.hyperdata.scute.tree.actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.undo.CannotUndoException;

import org.hyperdata.scute.source.EditorPane;


public class UndoAction extends AbstractAction {
	
	private EditorPane editorPane;

	public UndoAction(EditorPane editorPane) {
		super("Undo");
		this.editorPane = editorPane;
		setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			this.editorPane.getUndoManager().undo();
		} catch (CannotUndoException ex) {
			System.out.println("Unable to undo: " + ex);
			ex.printStackTrace();
		}
		update();
		this.editorPane.getRedoAction().update();
	}

	public void update() {
		
		if (this.editorPane.getUndoManager().canUndo()) {
		    setEnabled(true);
			putValue(Action.NAME, this.editorPane.getUndoManager().getUndoPresentationName());
		} else {
			setEnabled(false);
			putValue(Action.NAME, "Undo");
		} 
	}
}