/**
 * 
 */
package org.hyperdata.scute.filemanager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

/**
 * @author danny
 * 
 */
public class ListMouseListener implements MouseListener {

	private FileReference fileReference;
	private JTree tree;

	public ListMouseListener(FileReference fileReference, JTree tree) {
		this.fileReference = fileReference;
		this.tree = tree;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent event) { // is this getting called?
		System.out.println("mouse clicked");
		JList list = (JList) event.getSource();
		File file = (File) list.getSelectedValue();
		
		if(event.getClickCount() == 2) {
			if(file.isDirectory()){
				TreePath path = tree.getAnchorSelectionPath() ;
				TreePath newPath =	path.pathByAddingChild(file);
				tree.setSelectionPath(newPath);
				return;
			}
			System.out.println("setting file");
			fileReference.setCurrentFile(file);
		}
		
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
