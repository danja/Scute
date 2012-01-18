/**
 * 
 */
package org.hyperdata.scute.demos.temp;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

import org.hyperdata.scute.filemanager.FileReference;

/**
 * @author danny
 * 
 */
public class SourceEditor extends JEditorPane implements FileReference {

	JScrollPane scrollPane;

	public SourceEditor() {
		super();
		setFont(new Font("Monospaced", Font.PLAIN, 12));
		scrollPane = new JScrollPane(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.hyperdata.scute.filemanager.FileReference#setCurrentFile(java.io.
	 * File)
	 */
	@Override
	public void setCurrentFile(File file) {
		System.out.println("setting file "+file);
		String url = "file:///" + file.getAbsolutePath();
		URL newURL = null;
		try {
			newURL = new URL(url);
		} catch (MalformedURLException exception) {
			exception.printStackTrace();
		}
		URL loadedURL = getPage();
		if (loadedURL != null && loadedURL.sameFile(newURL)) {
			return;
		}
		// maybe display "Loading..."
		 try {
			setPage(url);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperdata.scute.filemanager.FileReference#getCurrentFile()
	 */
	@Override
	public File getCurrentFile() {
		return new File(getPage().getFile());
	}

	/**
	 * @return the scrollPane
	 */
	public JScrollPane getScrollPane() {
		return this.scrollPane;
	}

}
