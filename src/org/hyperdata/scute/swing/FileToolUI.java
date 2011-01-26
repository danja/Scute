package org.hyperdata.scute.swing;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import org.hyperdata.resources.general.GeneralIcons;

public class FileToolUI implements KeyListener { // implements ActionListener

	private Action cloneAction;
	private Action closeAction;
	private final ToolsInterface editor;

	private Action exitAction;
	private final JMenu fileMenu;
	private Action newAction;
	private Action openAction;
	private Action saveAction;

	private Action saveAsAction;
	private final JToolBar toolBar;

	public FileToolUI(ToolsInterface editor) {
		this.editor = editor;
		createActions();

		toolBar = new JToolBar();
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		final JButton newButton = toolBar.add(newAction);
		final JMenuItem newMenuItem = fileMenu.add(newAction);
		newMenuItem.setMnemonic(KeyEvent.VK_N);
		newButton.setToolTipText("Create new file");

		final JButton openButton = toolBar.add(openAction);
		final JMenuItem openMenuItem = fileMenu.add(openAction);
		openButton.setToolTipText("Open file");
		openMenuItem.setMnemonic(KeyEvent.VK_O);

		final JButton saveButton = toolBar.add(saveAction);
		final JMenuItem saveMenuItem = fileMenu.add(saveAction);
		saveButton.setToolTipText("Save file");
		saveMenuItem.setMnemonic(KeyEvent.VK_S);

		final JButton saveAsButton = toolBar.add(saveAsAction);
		fileMenu.add(saveAsAction);
		saveAsButton.setToolTipText("Save file as...");

		toolBar.add(cloneAction);
		fileMenu.add(cloneAction);
		saveAsButton.setToolTipText("Clone");

		toolBar.add(closeAction);
		fileMenu.add(closeAction);
		saveAsButton.setToolTipText("Close file");

		fileMenu.add(exitAction);
	}

	public void createActions() {
		newAction = new AbstractAction("New", GeneralIcons.newIcon) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7113809796782683172L;

			public void actionPerformed(ActionEvent event) {
				editor.newFile();
			}
		};
		openAction = new AbstractAction("Open", GeneralIcons.openIcon) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 8432975088477774203L;

			public void actionPerformed(ActionEvent event) {
				editor.openFile();
			}
		};
		saveAction = new AbstractAction("Save", GeneralIcons.saveIcon) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -8207182633883018396L;

			public void actionPerformed(ActionEvent event) {
				editor.saveFile();
			}
		};
		saveAsAction = new AbstractAction("Save As...", GeneralIcons.saveAsIcon) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -1264506428886349998L;

			public void actionPerformed(ActionEvent event) {
				editor.saveAsFile();
			}
		};
		exitAction = new AbstractAction("Exit") {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3503256080186368323L;

			public void actionPerformed(ActionEvent event) {
				editor.exit();
			}
		};

		cloneAction = new AbstractAction("Clone", GeneralIcons.cloneIcon) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 5343268104742764928L;

			public void actionPerformed(ActionEvent event) {
				editor.cloneFile();
			}
		};

		closeAction = new AbstractAction("Close", GeneralIcons.closeIcon) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -8024969229356348913L;

			public void actionPerformed(ActionEvent event) {
				editor.closeFile();
			}
		};
	}

	public Action getExitAction() {
		return exitAction;
	}

	public JMenu getFileMenu() {
		return fileMenu;
	}

	public Action getNewAction() {
		return newAction;
	}

	public Action getOpenAction() {
		return openAction;
	}

	public Action getSaveAction() {
		return saveAction;
	}

	public Action getSaveAsAction() {
		return saveAsAction;
	}

	public JToolBar getToolBar() {
		return toolBar;
	}

	public void keyPressed(KeyEvent event) {

		final int modifiers = event.getModifiers();

		final String mod = KeyEvent.getKeyModifiersText(modifiers);
		if (!mod.equals("Ctrl"))
			return;
		switch (event.getKeyCode()) {
		case KeyEvent.VK_N:
			editor.newFile();
			break;
		case KeyEvent.VK_S:
			editor.saveFile();
			break;
		default:
		}
	}

	public void keyReleased(KeyEvent event) {
	}

	public void keyTyped(KeyEvent event) {
	}

	public void setExitAction(Action action) {
		exitAction = action;
	}

}