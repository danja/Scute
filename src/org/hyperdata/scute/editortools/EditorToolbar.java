/**
 * 
 */
package org.hyperdata.scute.editortools;

import java.awt.Frame;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;

/**
 * @author danny
 * 
 */
public class EditorToolbar extends JPanel {

//	private JEditorPane findPane;
//	private JEditorPane zoomPane;

	public EditorToolbar(Frame frame, JEditorPane findPane, JEditorPane zoomPane) {

		Action findAction = new FindAction(frame, findPane);
		JButton findButton = new JButton(findAction);
		add(findButton);

		// move to scute.editortools
		ZoomAction zoomInAction = new ZoomAction(zoomPane, "+", 1.1);
		JButton zoomIn = new JButton(zoomInAction);
		add(zoomIn);

		ZoomAction zoomOutAction = new ZoomAction(zoomPane, "-", 1 / 1.1);
		JButton zoomOut = new JButton(zoomOutAction);
		add(zoomOut);
	}

}
