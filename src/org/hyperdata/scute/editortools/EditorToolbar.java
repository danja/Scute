/**
 * 
 */
package org.hyperdata.scute.editortools;

import java.awt.Frame;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

import org.hyperdata.scute.editortools.undo.ActionChangedListener;
import org.hyperdata.scute.editortools.undo.UndoHandler;
import org.hyperdata.scute.source.EditorPane;

/**
 * @author danny
 * 
 */
public class EditorToolbar extends JPanel {

	/**
	 * Listener for the edits on the current document.
	 */
	public UndoableEditListener undoHandler;

	/** UndoManager that we add edits to. */
	public UndoManager undoManager = new UndoManager();

	public EditorToolbar(Frame frame, JEditorPane findPane, EditorPane zoomPane) {

		undoHandler = new UndoHandler(zoomPane);
		zoomPane.getDocument().addUndoableEditListener(undoHandler);

		Action undoAction = zoomPane.getUndoAction();
		JButton undoButton = new JButton(undoAction);
		undoAction.addPropertyChangeListener(new ActionChangedListener(undoButton));
		add(undoButton);

		Action redoAction = zoomPane.getRedoAction();
	
		JButton redoButton = new JButton(redoAction);
		redoAction.addPropertyChangeListener(new ActionChangedListener(redoButton));
		add(redoButton);

		Action findAction = new FindAction(frame, findPane);
		JButton findButton = new JButton(findAction);
		add(findButton);

		ZoomAction zoomInAction = new ZoomAction(zoomPane, "+", 1.1);
		JButton zoomIn = new JButton(zoomInAction);
		add(zoomIn);

		ZoomAction zoomOutAction = new ZoomAction(zoomPane, "-", 1 / 1.1);
		JButton zoomOut = new JButton(zoomOutAction);
		add(zoomOut);
	}
}
