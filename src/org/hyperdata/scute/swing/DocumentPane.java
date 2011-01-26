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
	/**
	 * 
	 */
	private static final long serialVersionUID = 7870299563995282592L;
	public static final String HTML = "text/html";
	public static final String TEXT = "text/plain";

	private final JEditorPane editorPane;
	private String filename = null;

	private boolean loaded = false;

	private boolean saved = false;

	public DocumentPane() {
		this(new JEditorPane());
		// editorPane = new JEditorPane();
	}

	public DocumentPane(JEditorPane editorPane) {
		super(editorPane);
		this.editorPane = editorPane;
		editorPane.addKeyListener(this);
	}

	@Override
	public void addKeyListener(KeyListener listener) {
		editorPane.addKeyListener(listener);
	}

	/** Return the name of the file loaded into the editor pane. */

	public String getFilename() {
		return (filename);
	}

	public String getText() {
		return editorPane.getText();
	}

	public boolean isEditable() {
		return editorPane.isEditable();
	}

	/**
	 * Return true if a document is loaded into the editor page, either through
	 * <code>setPage</code> or <code>setText</code>.
	 */

	public boolean isLoaded() {
		return (loaded);
	}

	public boolean isSaved() {
		return saved;
	}

	public void keyPressed(KeyEvent e) {
		saved = false;
	}

	public void keyReleased(KeyEvent e) {
		// not needed
	}

	public void keyTyped(KeyEvent e) {
		// not needed
	}

	/**
	 * Load a file into the editor pane.
	 * 
	 * Note that the setPage method of JEditorPane checks the URL of the
	 * currently loaded page against the URL of the new page to laod. If the two
	 * URLs are the same, then the page is <b>not</b> reloaded.
	 */

	public void loadFile(String filename) {
		try {
			final File file = new File(filename);
			setPage(file.toURL());
			editorPane.setCaretPosition(0); // scroll to top
			repaint(); // //
		} catch (final IOException exception) {
			System.err.println("Unable to load file: " + filename);
			exception.printStackTrace();
		}
	}

	public void saveFile() {
		saveFile(filename);
	}

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

	public void setEditable(boolean editable) {
		editorPane.setEditable(editable);
	}

	/** Set the filename of the document. */

	public void setFilename(String filename) {
		this.filename = filename;
	}

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

	public void setSaved(boolean b) {
		saved = b;
	}

	/**
	 * Set the text in the document page, replace the exiting document.
	 */

	public void setText(String text) {
		editorPane.setText(text);
		setFilename("");
		loaded = true;
	}

}