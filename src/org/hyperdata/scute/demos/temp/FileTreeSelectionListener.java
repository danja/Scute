/**
 * 
 */
package org.hyperdata.scute.demos.temp;

import java.io.File;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import org.hyperdata.scute.filemanager.FileReference;

/**
 * @author danny
 * 
 */
public class FileTreeSelectionListener implements TreeSelectionListener {

	private JTree tree;
	private FileReference target;

	public void attach(JTree tree, FileReference target) {
		this.tree = tree;
		tree.addTreeSelectionListener(this);
		this.target = target;
	}

	public void valueChanged(TreeSelectionEvent e) {

		File file = (File) tree.getLastSelectedPathComponent();

		if (file == null)
			return;
		
		target.setCurrentFile(file);
	}
}
