/**
 * 
 */
package org.hyperdata.scute.toolbars.source;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

import org.jdesktop.swingx.JXErrorPane;

import org.hyperdata.resources.general.GeneralIcons;
import org.hyperdata.scute.source.EditorPane;
import org.hyperdata.scute.system.Log;
import org.hyperdata.scute.toolbars.actions.FindAction;
import org.hyperdata.scute.toolbars.actions.ZoomAction;

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

	private JButton undoButton;

	private JButton redoButton;

	private JButton findButton;

	private JButton zoomIn;

	private JButton zoomOut;

	private Frame frame = null;

	private Map<Document, UndoHandler> undoHandlerMap = new HashMap<Document, UndoHandler>();

	private PropertyChangeListener undoActionChangeListener;
	private PropertyChangeListener redoActionChangeListener;

	public EditorToolbar(Frame frame) {
		this.frame = frame;
		init();
	}

	private void init() {
		undoButton = new JButton();
		undoButton.setIcon(GeneralIcons.undoIcon);
		undoButton.setHideActionText(true);
		undoButton.setToolTipText("Undo edit");
		undoActionChangeListener = new ActionChangedListener(undoButton);
		add(undoButton);

		redoButton = new JButton();
		redoButton.setIcon(GeneralIcons.redoIcon);
		redoButton.setHideActionText(true);
		redoButton.setToolTipText("Redo edit");
		redoActionChangeListener = new ActionChangedListener(redoButton);
		add(redoButton);

		findButton = new JButton();
		findButton.setIcon(GeneralIcons.findIcon);
		findButton.setHideActionText(true);
		findButton.setToolTipText("Find in text..");
		add(findButton);

		zoomIn = new JButton();
		zoomIn.setIcon(GeneralIcons.zoomInIcon);
		zoomIn.setHideActionText(true);
		zoomIn.setToolTipText("Zoom in");
		add(zoomIn);

		zoomOut = new JButton();
		zoomOut.setIcon(GeneralIcons.zoomOutIcon);
		zoomOut.setHideActionText(true);
		zoomOut.setToolTipText("Zoom out");
		add(zoomOut);
	}

	private void refreshButtons() {
		undoButton.setIcon(GeneralIcons.undoIcon);
		undoButton.setHideActionText(true);
		undoButton.setToolTipText("Undo edit");

		redoButton.setIcon(GeneralIcons.redoIcon);
		redoButton.setHideActionText(true);
		redoButton.setToolTipText("Redo edit");

		findButton.setIcon(GeneralIcons.findIcon);
		findButton.setHideActionText(true);
		findButton.setToolTipText("Find in text..");

		zoomIn.setIcon(GeneralIcons.zoomInIcon);
		zoomIn.setHideActionText(true);
		zoomIn.setToolTipText("Zoom in");

		zoomOut.setIcon(GeneralIcons.zoomOutIcon);
		zoomOut.setHideActionText(true);
		zoomOut.setToolTipText("Zoom out");
	}

	/**
	 * @param source
	 */
	public void setEditorPane(EditorPane targetPane) {

		Document doc = targetPane.getDocument();
		if (undoHandlerMap.get(doc) == null) {
			undoHandler = new UndoHandler(targetPane);
			doc.addUndoableEditListener(undoHandler);
		}

		Action undoAction = targetPane.getUndoAction();
		undoButton.setAction(undoAction);
		undoAction.addPropertyChangeListener(undoActionChangeListener);

		Action redoAction = targetPane.getRedoAction();
		redoButton.setAction(redoAction);
		redoAction.addPropertyChangeListener(redoActionChangeListener);

		Action findAction = new FindAction(frame, targetPane);
		findButton.setAction(findAction);

		ZoomAction zoomInAction = new ZoomAction(targetPane, "+", 1.1);
		zoomIn.setAction(zoomInAction);

		ZoomAction zoomOutAction = new ZoomAction(targetPane, "-", 1 / 1.1);
		zoomOut.setAction(zoomOutAction);
		refreshButtons(); // a pain, but seems necessary
	}

	public void findOnly() {
		undoButton.setEnabled(false);
		redoButton.setEnabled(false);
		findButton.setEnabled(true);
		zoomIn.setEnabled(false);
		zoomOut.setEnabled(false);
	}

	public void enableAll() {
		undoButton.setEnabled(true);
		redoButton.setEnabled(true);
		findButton.setEnabled(true);
		zoomIn.setEnabled(true);
		zoomOut.setEnabled(true);
	}
}
