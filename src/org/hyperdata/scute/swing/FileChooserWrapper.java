// doesn't look like it's being used

package org.hyperdata.scute.swing;

import java.io.File;

import javax.swing.JFileChooser;

/**
 * The Class FileChooserWrapper.
 * 
 * @version 1.0
 * @author
 */
public class FileChooserWrapper {

	/** The data directory. */
	public static File dataDirectory = new File("./");
	
	/** The file chooser wrapper. */
	public static FileChooserWrapper fileChooserWrapper = new FileChooserWrapper();

	/**
	 * Gets the file chooser.
	 * 
	 * @return the file chooser
	 */
	public static FileChooserWrapper getFileChooser() {
		return fileChooserWrapper;
	}

	/** The file chooser. */
	public JFileChooser fileChooser;

	/**
	 * Instantiates a new file chooser wrapper.
	 */
	private FileChooserWrapper() {
		// if(Idea.system.dataDirectory != null){
		// dataDirectory = new File(Idea.system.dataDirectory);
		// }else {
		// }
	}

	/**
	 * Gets the file chooser.
	 * 
	 * @param currentDirectory
	 *            the current directory
	 * @param mode
	 *            the mode
	 * @return the file chooser
	 */
	public JFileChooser getFileChooser(File currentDirectory, int mode) {
		if (fileChooser == null) {
			fileChooser = new JFileChooser(currentDirectory);
		} else {
			fileChooser.setCurrentDirectory(currentDirectory);
		}

		fileChooser.setFileSelectionMode(mode);

		return fileChooser;
	}

	/**
	 * Open dialog.
	 * 
	 * @return the file
	 */
	public File openDialog() {
		return openDialog(dataDirectory, JFileChooser.FILES_ONLY);
	}

	/**
	 * Open dialog.
	 * 
	 * @param currentDirectory
	 *            the current directory
	 * @param mode
	 *            the mode
	 * @return the file
	 */
	public File openDialog(File currentDirectory, int mode) {
		fileChooser = getFileChooser(currentDirectory, mode);

		File file = null;
		final int returnVal = fileChooser.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
		}

		return file;
	}

	/**
	 * Open dialog.
	 * 
	 * @param mode
	 *            the mode
	 * @return the file
	 */
	public File openDialog(int mode) {
		return openDialog(dataDirectory, mode);
	}

	/**
	 * Open dialog.
	 * 
	 * @param directory
	 *            the directory
	 * @return the file
	 */
	public File openDialog(String directory) {
		return openDialog(new File(directory), JFileChooser.FILES_ONLY);
	}

	/**
	 * Save dialog.
	 * 
	 * @return the file
	 */
	public File saveDialog() {
		return saveDialog(dataDirectory, JFileChooser.FILES_ONLY);
	}

	/**
	 * Save dialog.
	 * 
	 * @param currentDirectory
	 *            the current directory
	 * @param mode
	 *            the mode
	 * @return the file
	 */
	public File saveDialog(File currentDirectory, int mode) {
		fileChooser = getFileChooser(currentDirectory, mode);

		final int returnVal = fileChooser.showSaveDialog(null);
		File file = null;

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
		}

		return file;
	}

	/**
	 * Save dialog.
	 * 
	 * @param directory
	 *            the directory
	 * @return the file
	 */
	public File saveDialog(String directory) {
		return saveDialog(new File(directory), JFileChooser.FILES_ONLY);
	}

	/**
	 * Sets the directory.
	 * 
	 * @param directory
	 *            the new directory
	 */
	public void setDirectory(File directory) {
		dataDirectory = directory;
	}

	/**
	 * Sets the directory.
	 * 
	 * @param directory
	 *            the new directory
	 */
	public void setDirectory(String directory) {
		setDirectory(new File(directory));
	}

	/*
	 * JFileChooser.FILES_ONLY JFileChooser.DIRECTORIES_ONLY
	 * JFileChooser.FILES_AND_DIRECTORIES
	 */
	/**
	 * Show dialog.
	 * 
	 * @param currentDirectory
	 *            the current directory
	 * @param buttonText
	 *            the button text
	 * @param mode
	 *            the mode
	 * @return the file
	 */
	public File showDialog(File currentDirectory, String buttonText, int mode) {
		fileChooser = getFileChooser(currentDirectory, mode);

		final int returnVal = fileChooser.showDialog(null, buttonText);
		File file = null;

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
		}

		return file;
	}

	/**
	 * Show dialog.
	 * 
	 * @param buttonText
	 *            the button text
	 * @return the file
	 */
	public File showDialog(String buttonText) {
		return showDialog(dataDirectory, buttonText,
				JFileChooser.DIRECTORIES_ONLY);
	}
}
