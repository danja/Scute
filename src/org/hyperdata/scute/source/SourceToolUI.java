package org.hyperdata.scute.source;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JToolBar;

import org.hyperdata.resources.general.GeneralIcons;
import org.hyperdata.scute.swing.ToolsInterface;

public class SourceToolUI {

	private final JMenu toolMenu;
	private final JToolBar toolBar;
	private Action checkAction;
	private final ToolsInterface editor;

	public SourceToolUI(ToolsInterface editor) {
		this.editor = editor;
		createActions();

		toolBar = new JToolBar();
		toolMenu = new JMenu("Tools");
		toolMenu.setMnemonic(KeyEvent.VK_T);

		final JButton checkButton = toolBar.add(checkAction);
		checkButton.setToolTipText("Validate Text");
		toolMenu.add(checkAction);
	}

	public void createActions() {

		checkAction = new AbstractAction("Check", GeneralIcons.checkUnknownIcon) {

			private static final long serialVersionUID = -5594526147707171138L;

			public void actionPerformed(ActionEvent event) {
				editor.checkText();
			}
		};
	}

	public JToolBar getToolBar() {
		return toolBar;
	}

	public JMenu getSourceMenu() {
		return toolMenu;
	}
}
