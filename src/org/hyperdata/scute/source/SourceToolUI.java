/*
 * Scute
 * 
 * Homepage: http://hyperdata.org/scute
 * 
 * License : http://www.apache.org/licenses/LICENSE-2.0
 * See also license.txt or http://hyperdata.org/wiki/Scute:License
 * 
 * Danny Ayers 2011
 */
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

/**
 * The Class SourceToolUI.
 */
public class SourceToolUI {

	/** The tool menu. */
	private final JMenu toolMenu;
	
	/** The tool bar. */
	private final JToolBar toolBar;
	
	/** The check action. */
	private Action checkAction;
	
	/** The editor. */
	private final ToolsInterface editor;

	/**
	 * Instantiates a new source tool ui.
	 * 
	 * @param editor
	 *            the editor
	 */
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

	/**
	 * Creates the actions.
	 */
	public void createActions() {

		checkAction = new AbstractAction("Check", GeneralIcons.checkUnknownIcon) {

			private static final long serialVersionUID = -5594526147707171138L;

			public void actionPerformed(ActionEvent event) {
				editor.checkText();
			}
		};
	}

	/**
	 * Gets the tool bar.
	 * 
	 * @return the tool bar
	 */
	public JToolBar getToolBar() {
		return toolBar;
	}

	/**
	 * Gets the source menu.
	 * 
	 * @return the source menu
	 */
	public JMenu getSourceMenu() {
		return toolMenu;
	}
}
