/**
 * 
 */
package org.hyperdata.scute.demos.temp;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JTree;
import javax.swing.tree.TreePath;


import org.hyperdata.scute.filemanager.FileReference;

/**
 * @author danny
 *
 */
public class FileTreeMouseListener extends MouseAdapter {

	
	private JTree tree;
	private FileReference target;
	
	public void attach(JTree tree, FileReference target){
		this.tree = tree;
		tree.addMouseListener(this);
		this.target = target;
	}

	public void mouseClicked(MouseEvent e) {
        int x = (int) e.getPoint().getX();
        int y = (int) e.getPoint().getY();
        TreePath treePath = tree.getPathForLocation(x, y);
System.out.println(treePath);
if(treePath == null) return;
File file = (File)treePath.getLastPathComponent();
if(file.isDirectory()) return;

    }
}
