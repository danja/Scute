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
package org.hyperdata.scute.toolbars.file;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import org.hyperdata.resources.general.GeneralIcons;

/**
 * The Class FileToolUI.
 */
public class FileUI implements KeyListener { // implements ActionListener

	/** The clone action. */
 private Action cloneAction;
	
	/** The close action. */
	private Action closeAction;
	
	/** The editor. */
	private final FilesInterface editor; // FilesModelInterface

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
	private final JPanel toolBar;

	/**
	 * Instantiates a new file tool ui.
	 * 
	 * @param editor
	 *            the editor
	 */
	public FileUI(FilesInterface editor) { // FilesModelInterface
		this.editor = editor;
		createActions();

		toolBar = new JPanel();

		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		final JButton newButton = new JButton(newAction);
		newButton.setHideActionText(true);
			toolBar.add(newButton);
		// newButton.setBorderPainted(false);
		final JMenuItem newMenuItem = fileMenu.add(newAction);
		newMenuItem.setMnemonic(KeyEvent.VK_N);
		newButton.setToolTipText("Create new file");

		final JButton openButton = new JButton(openAction);
		openButton.setHideActionText(true);
			toolBar.add(openButton);
		final JMenuItem openMenuItem = fileMenu.add(openAction);
		openButton.setToolTipText("Open file");
		openMenuItem.setMnemonic(KeyEvent.VK_O);

		final JButton saveButton = new JButton(saveAction);
		saveButton.setHideActionText(true);
			toolBar.add(saveButton);
		final JMenuItem saveMenuItem = fileMenu.add(saveAction);
		saveButton.setToolTipText("Save file");
		saveMenuItem.setMnemonic(KeyEvent.VK_S);

		final JButton saveAsButton = new JButton(saveAsAction);
		saveAsButton.setHideActionText(true);
		toolBar.add(saveAsButton);
		fileMenu.add(saveAsAction);
		saveAsButton.setToolTipText("Save file as...");

		final JButton cloneButton = new JButton(cloneAction);
		cloneButton.setHideActionText(true);
		toolBar.add(cloneButton);
		fileMenu.add(cloneAction);
		cloneButton.setToolTipText("Clone");

		final JButton closeButton = new JButton(closeAction);
		closeButton.setHideActionText(true);
		toolBar.add(closeButton);
		fileMenu.add(closeAction);
		closeButton.setToolTipText("Close file");

		
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
			//	editor.newModel();
				editor.newFile();
			}
		};
		openAction = new AbstractAction("Open", GeneralIcons.openIcon) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 8432975088477774203L;

			@Override
			public void actionPerformed(ActionEvent event) {
				editor.open();
			}
		};
		saveAction = new AbstractAction("Save", GeneralIcons.saveIcon) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -8207182633883018396L;

			@Override
			public void actionPerformed(ActionEvent event) {
				editor.save();
			}
		};
		saveAsAction = new AbstractAction("Save As...", GeneralIcons.saveAsIcon) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -1264506428886349998L;

			@Override
			public void actionPerformed(ActionEvent event) {
				editor.saveAs();
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
	public JPanel getToolBar() {
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
			editor.newFile();
			break;
		case KeyEvent.VK_S:
			editor.save();
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