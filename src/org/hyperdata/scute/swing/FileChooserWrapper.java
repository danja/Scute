// doesn't look like it's being used

package org.hyperdata.scute.swing;

import java.io.File;

import javax.swing.JFileChooser;

/**
 * @version 1.0
 * @author
 */
public class FileChooserWrapper {

	public static File dataDirectory = new File("./");
	public static FileChooserWrapper fileChooserWrapper = new FileChooserWrapper();

	public static FileChooserWrapper getFileChooser() {
		return fileChooserWrapper;
	}

	public JFileChooser fileChooser;

	private FileChooserWrapper() {
		// if(Idea.system.dataDirectory != null){
		// dataDirectory = new File(Idea.system.dataDirectory);
		// }else {
		// }
	}

	public JFileChooser getFileChooser(File currentDirectory, int mode) {
		if (fileChooser == null) {
			fileChooser = new JFileChooser(currentDirectory);
		} else {
			fileChooser.setCurrentDirectory(currentDirectory);
		}

		fileChooser.setFileSelectionMode(mode);

		return fileChooser;
	}

	public File openDialog() {
		return openDialog(dataDirectory, JFileChooser.FILES_ONLY);
	}

	public File openDialog(File currentDirectory, int mode) {
		fileChooser = getFileChooser(currentDirectory, mode);

		File file = null;
		final int returnVal = fileChooser.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
		}

		return file;
	}

	public File openDialog(int mode) {
		return openDialog(dataDirectory, mode);
	}

	public File openDialog(String directory) {
		return openDialog(new File(directory), JFileChooser.FILES_ONLY);
	}

	public File saveDialog() {
		return saveDialog(dataDirectory, JFileChooser.FILES_ONLY);
	}

	public File saveDialog(File currentDirectory, int mode) {
		fileChooser = getFileChooser(currentDirectory, mode);

		final int returnVal = fileChooser.showSaveDialog(null);
		File file = null;

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
		}

		return file;
	}

	public File saveDialog(String directory) {
		return saveDialog(new File(directory), JFileChooser.FILES_ONLY);
	}

	public void setDirectory(File directory) {
		dataDirectory = directory;
	}

	public void setDirectory(String directory) {
		setDirectory(new File(directory));
	}

	/*
	 * JFileChooser.FILES_ONLY JFileChooser.DIRECTORIES_ONLY
	 * JFileChooser.FILES_AND_DIRECTORIES
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

	public File showDialog(String buttonText) {
		return showDialog(dataDirectory, buttonText,
				JFileChooser.DIRECTORIES_ONLY);
	}
}
