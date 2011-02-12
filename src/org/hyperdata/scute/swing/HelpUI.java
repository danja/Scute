/**
 * 
 */
package org.hyperdata.scute.swing;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import org.hyperdata.resources.general.GeneralIcons;
import org.hyperdata.scute.main.Config;

/**
 * @author danny
 *
 */
public class HelpUI {
	
	private ToolsInterface editor;
	private JMenu helpMenu;
	private Action aboutAction;

	public HelpUI(ToolsInterface editor) {
		this.editor = editor;
		createActions();

		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);

		final JMenuItem aboutMenuItem = helpMenu.add(aboutAction);
		aboutMenuItem.setMnemonic(KeyEvent.VK_N);
		aboutMenuItem.setToolTipText("About this application...");
		// helpMenu.add(aboutMenuItem);
	}

	/**
	 * 
	 */
	private void createActions() {
		aboutAction = new AbstractAction("About") {

			@Override
			public void actionPerformed(ActionEvent event) {
				
				String aboutString = Config.getAboutString();
				JOptionPane.showMessageDialog(editor.getFrame(), aboutString);
			}
		};
	}

	/**
	 * @return
	 */
	public JMenu getHelpMenu() {
		return helpMenu;
	}
}
