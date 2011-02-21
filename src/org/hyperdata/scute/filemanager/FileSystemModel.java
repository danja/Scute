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
package org.hyperdata.scute.filemanager;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 * The Class FileSystemModel.
 */
public class FileSystemModel extends DefaultTreeModel {

	String root;

	/**
	 * Instantiates a new file system model.
	 */
	public FileSystemModel() {
		this(System.getProperty("user.home"));
	}

	/**
	 * Instantiates a new file system model.
	 * 
	 * @param startPath
	 *            the start path
	 */
	public FileSystemModel(String startPath) {
		super(null);
		root = startPath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeModel#getRoot()
	 */
	@Override
	public Object getRoot() {
		return new File(root);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeModel#getChild(java.lang.Object, int)
	 */
	@Override
	public Object getChild(Object parent, int index) {
		File directory = (File) parent;
		String[] children = FileComparator.getSortedChildrenNames(directory);
		return new File(directory, children[index]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeModel#getChildCount(java.lang.Object)
	 */
	@Override
	public int getChildCount(Object parent) {
		File fileSysEntity = (File) parent;
		if (fileSysEntity.isDirectory()) {
			String[] children = fileSysEntity.list();
			return children.length;
		} else {
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeModel#isLeaf(java.lang.Object)
	 */
	@Override
	public boolean isLeaf(Object node) {
		return ((File) node).isFile();
		// return !hasSubdirectories((File) node);
	}

	/**
	 * @param node
	 */
	private boolean hasSubdirectories(File file) {
		if (file.isFile())
			return false; // shouldn't be needed
		File[] children = file.listFiles();
		for (int i = 0; i < children.length; i++) {
			if (children[i].isDirectory())
				return true;
		}
		return false;
	}

	// public void valueForPathChanged( TreePath path, Object newValue ) {
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeModel#getIndexOfChild(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public int getIndexOfChild(Object parent, Object child) {
		File directory = (File) parent;
		File fileSysEntity = (File) child;
		String[] children = FileComparator.getSortedChildrenNames(directory);
		int result = -1;

		for (int i = 0; i < children.length; ++i) {
			if (fileSysEntity.getName().equals(children[i])) {
				result = i;
				break;
			}
		}

		return result;
	}



	/**
	 * @param list
	 * @return
	 */
//	private String[] sortFilenames(String[] list) {
	// directory.list()
//		Arrays.sort(list);
//		return list;
//
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.tree.TreeModel#valueForPathChanged(javax.swing.tree.TreePath,
	 * java.lang.Object)
	 */
	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// TODO Auto-generated method stub

	}
}
