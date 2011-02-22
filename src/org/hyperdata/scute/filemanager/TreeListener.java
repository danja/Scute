package org.hyperdata.scute.filemanager;

import java.io.File;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

/**
 * The listener interface for receiving tree events. The class that is
 * interested in processing a tree event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addTreeListener<code> method. When
 * the tree event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see TreeEvent
 */
class TreeListener implements TreeSelectionListener {
	private DirListModel dirListModel;
	private FileReference fileReference;


	/**
	 * Instantiates a new tree listener.
	 * @param fileExplorerPanel 
	 * 
	 */
	public TreeListener(FileReference fileReference, DirListModel model) {
		this.fileReference = fileReference;
		this.dirListModel = model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.
	 * event.TreeSelectionEvent)
	 */
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		File file = (File) e.getPath().getLastPathComponent();
		fileReference.setCurrentFile(file);
		if (file.isDirectory()) {
			dirListModel.setDirectory(file);
		} else {
			dirListModel.setDirectory(null);
		}
	}
}