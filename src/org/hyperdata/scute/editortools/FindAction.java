package org.hyperdata.scute.editortools;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JEditorPane;


public class FindAction extends AbstractAction {
	
	private JEditorPane editorPane;
	private Frame frame;

	public FindAction(Frame frame, JEditorPane editorPane) {
		super("Find");
		this.frame = frame;
		this.editorPane = editorPane;
	}

	public void actionPerformed(ActionEvent ae) {
		FindDialog findDialog = new FindDialog(frame, editorPane);
		findDialog.init();
		findDialog.setVisible(true);
	}

}
