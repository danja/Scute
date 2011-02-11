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

/**
 * The Class FileToolUI.
 */
public class FileToolUI implements KeyListener { // implements ActionListener

	/** The clone action. */
 private Action cloneAction;
	
	/** The close action. */
	private Action closeAction;
	
	/** The editor. */
	private final ToolsInterface editor;

	/** The exit action. */
	private Action exitAction;
	
	/** The file menu. */
	private final JMenu fileMenu;
	
	/** The new action. */
	private Action newAction;
	
	/** The open action. */
	private Action openAction;
	
	/** The save action. */
	private Action saveAction;

	/** The save as action. */
	private Action saveAsAction;
	
	/** The tool bar. */
	private final JToolBar toolBar;

	/**
	 * Instantiates a new file tool ui.
	 * 
	 * @param editor
	 *            the editor
	 */
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

	/**
	 * Creates the actions.
	 */
	public void createActions() {
		newAction = new AbstractAction("New", GeneralIcons.newIcon) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7113809796782683172L;

			@Override
			public void actionPerformed(ActionEvent event) {
				editor.newModel();
			}
		};
		openAction = new AbstractAction("Open", GeneralIcons.openIcon) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 8432975088477774203L;

			@Override
			public void actionPerformed(ActionEvent event) {
				editor.openFile();
			}
		};
		saveAction = new AbstractAction("Save", GeneralIcons.saveIcon) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -8207182633883018396L;

			@Override
			public void actionPerformed(ActionEvent event) {
				editor.saveFile();
			}
		};
		saveAsAction = new AbstractAction("Save As...", GeneralIcons.saveAsIcon) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -1264506428886349998L;

			@Override
			public void actionPerformed(ActionEvent event) {
				editor.saveAsFile();
			}
		};
		exitAction = new AbstractAction("Exit") {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3503256080186368323L;

			@Override
			public void actionPerformed(ActionEvent event) {
				editor.exit();
			}
		};

		cloneAction = new AbstractAction("Clone", GeneralIcons.cloneIcon) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 5343268104742764928L;

			@Override
			public void actionPerformed(ActionEvent event) {
				editor.cloneFile();
			}
		};

		closeAction = new AbstractAction("Close", GeneralIcons.closeIcon) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -8024969229356348913L;

			@Override
			public void actionPerformed(ActionEvent event) {
				editor.closeFile();
			}
		};
	}

	/**
	 * Gets the exit action.
	 * 
	 * @return the exit action
	 */
	public Action getExitAction() {
		return exitAction;
	}

	/**
	 * Gets the file menu.
	 * 
	 * @return the file menu
	 */
	public JMenu getFileMenu() {
		return fileMenu;
	}

	/**
	 * Gets the new action.
	 * 
	 * @return the new action
	 */
	public Action getNewAction() {
		return newAction;
	}

	/**
	 * Gets the open action.
	 * 
	 * @return the open action
	 */
	public Action getOpenAction() {
		return openAction;
	}

	/**
	 * Gets the save action.
	 * 
	 * @return the save action
	 */
	public Action getSaveAction() {
		return saveAction;
	}

	/**
	 * Gets the save as action.
	 * 
	 * @return the save as action
	 */
	public Action getSaveAsAction() {
		return saveAsAction;
	}

	/**
	 * Gets the tool bar.
	 * 
	 * @return the tool bar
	 */
	public JToolBar getToolBar() {
		return toolBar;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent event) {

		final int modifiers = event.getModifiers();

		final String mod = KeyEvent.getKeyModifiersText(modifiers);
		if (!mod.equals("Ctrl"))
			return;
		switch (event.getKeyCode()) {
		case KeyEvent.VK_N:
			editor.newModel();
			break;
		case KeyEvent.VK_S:
			editor.saveFile();
			break;
		default:
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent event) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent event) {
	}

	/**
	 * Sets the exit action.
	 * 
	 * @param action
	 *            the new exit action
	 */
	public void setExitAction(Action action) {
		exitAction = action;
	}

}