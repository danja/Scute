/**
 * 
 */
package org.hyperdata.scute.editortools;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

import org.jdesktop.swingx.JXErrorPane;

import org.hyperdata.resources.general.GeneralIcons;
import org.hyperdata.scute.editortools.undo.ActionChangedListener;
import org.hyperdata.scute.editortools.undo.UndoHandler;
import org.hyperdata.scute.source.EditorPane;
import org.hyperdata.scute.system.Log;

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
		undoButton.setIcon(GeneralIcons.undoIcon);
		undoAction.addPropertyChangeListener(new ActionChangedListener(undoButton));
		undoButton.setHideActionText(true);
		undoButton.setToolTipText("Undo edit");
		add(undoButton);

		Action redoAction = zoomPane.getRedoAction();
		JButton redoButton = new JButton(redoAction);
		redoAction.addPropertyChangeListener(new ActionChangedListener(redoButton));
		redoButton.setIcon(GeneralIcons.redoIcon);
		redoButton.setHideActionText(true);
		redoButton.setToolTipText("Redo edit");
		add(redoButton);

		Action findAction = new FindAction(frame, findPane);
		JButton findButton = new JButton(findAction);
		findButton.setIcon(GeneralIcons.findIcon);
		findButton.setHideActionText(true);
		findButton.setToolTipText("Find in text..");
		add(findButton);

		ZoomAction zoomInAction = new ZoomAction(zoomPane, "+", 1.1);
		JButton zoomIn = new JButton(zoomInAction);
		zoomIn.setIcon(GeneralIcons.zoomInIcon);
		zoomIn.setHideActionText(true);
		zoomIn.setToolTipText("Zoom in");
		add(zoomIn);

		ZoomAction zoomOutAction = new ZoomAction(zoomPane, "-", 1 / 1.1);
		JButton zoomOut = new JButton(zoomOutAction);
		zoomOut.setIcon(GeneralIcons.zoomOutIcon);
		zoomOut.setHideActionText(true);
		zoomOut.setToolTipText("Zoom out");
		add(zoomOut);
		
//		JButton exceptionButton = new JButton("X");
//		exceptionButton.addActionListener(new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				try {
//					throw new ClassNotFoundException("original message");
//				} catch (Exception exception) {
//					
//					Log.exception(exception);
//				}
//				
//			}});
//		
//		add(exceptionButton);
	}
}
