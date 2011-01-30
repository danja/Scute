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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

/**
 * Wraps up a JEditorPane with scrolling and utility I/O methods.
 */

public class DocumentPane extends JScrollPane implements KeyListener {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7870299563995282592L;
	
	/** The Constant HTML. */
	public static final String HTML = "text/html";
	
	/** The Constant TEXT. */
	public static final String TEXT = "text/plain";

	/** The editor pane. */
	private final JEditorPane editorPane;
	
	/** The filename. */
	private String filename = null;

	/** The loaded. */
	private boolean loaded = false;

	/** The saved. */
	private boolean saved = false;

	/**
	 * Instantiates a new document pane.
	 */
	public DocumentPane() {
		this(new JEditorPane());
		// editorPane = new JEditorPane();
	}

	/**
	 * Instantiates a new document pane.
	 * 
	 * @param editorPane
	 *            the editor pane
	 */
	public DocumentPane(JEditorPane editorPane) {
		super(editorPane);
		this.editorPane = editorPane;
		editorPane.addKeyListener(this);
	}

	/* (non-Javadoc)
	 * @see java.awt.Component#addKeyListener(java.awt.event.KeyListener)
	 */
	@Override
	public void addKeyListener(KeyListener listener) {
		editorPane.addKeyListener(listener);
	}

	/**
	 * Return the name of the file loaded into the editor pane.
	 * 
	 * @return the filename
	 */

	public String getFilename() {
		return (filename);
	}

	/**
	 * Gets the text.
	 * 
	 * @return the text
	 */
	public String getText() {
		return editorPane.getText();
	}

	/**
	 * Checks if is editable.
	 * 
	 * @return true, if is editable
	 */
	public boolean isEditable() {
		return editorPane.isEditable();
	}

	/**
	 * Return true if a document is loaded into the editor page, either through
	 * <code>setPage</code> or <code>setText</code>.
	 * 
	 * @return true, if is loaded
	 */

	public boolean isLoaded() {
		return (loaded);
	}

	/**
	 * Checks if is saved.
	 * 
	 * @return true, if is saved
	 */
	public boolean isSaved() {
		return saved;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		saved = false;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// not needed
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// not needed
	}

	/**
	 * Load a file into the editor pane.
	 * 
	 * Note that the setPage method of JEditorPane checks the URL of the
	 * currently loaded page against the URL of the new page to laod. If the two
	 * URLs are the same, then the page is <b>not</b> reloaded.
	 * 
	 * @param filename
	 *            the filename
	 */

	public void loadFile(String filename) {
		try {
			final File file = new File(filename);
			setPage(file.toURI().toURL()); // needed to escape chars
			editorPane.setCaretPosition(0); // scroll to top
			repaint(); // //
		} catch (final IOException exception) {
			System.err.println("Unable to load file: " + filename);
			exception.printStackTrace();
		}
	}

	/**
	 * Save file.
	 */
	public void saveFile() {
		saveFile(filename);
	}

	/**
	 * Save file.
	 * 
	 * @param filename
	 *            the filename
	 */
	public void saveFile(String filename) {
		if (filename != this.filename) {
			this.filename = filename;
			saved = false;
		}
		if (saved)
			return;
		try {
			final File file = new File(filename);
			final FileWriter writer = new FileWriter(file);
			writer.write(editorPane.getText());
			writer.close();
			setFilename(file.getName());
			setSaved(true);
		} catch (final IOException exception) {

			System.err.println("Unable to save file: " + filename);
			exception.printStackTrace();
		}
	}

	/**
	 * Sets the editable.
	 * 
	 * @param editable
	 *            the new editable
	 */
	public void setEditable(boolean editable) {
		editorPane.setEditable(editable);
	}

	/**
	 * Set the filename of the document.
	 * 
	 * @param filename
	 *            the new filename
	 */

	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * Sets the page.
	 * 
	 * @param url
	 *            the new page
	 */
	public void setPage(URL url) {
		loaded = false;
		try {
			editorPane.setPage(url);
			final File file = new File(editorPane.getPage().toString());
			setFilename(file.getName());
			loaded = true;
		} catch (final IOException exception) {
			System.err.println("Unable to set page: " + url);
			exception.printStackTrace();
		}
	}

	/**
	 * Sets the saved.
	 * 
	 * @param b
	 *            the new saved
	 */
	public void setSaved(boolean b) {
		saved = b;
	}

	/**
	 * Set the text in the document page, replace the exiting document.
	 * 
	 * @param text
	 *            the new text
	 */

	public void setText(String text) {
		editorPane.setText(text);
		setFilename("");
		loaded = true;
	}

}