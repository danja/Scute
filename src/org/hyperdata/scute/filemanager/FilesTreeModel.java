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
import java.io.FileFilter;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 * The Class FileSystemModel.
 */
public class FilesTreeModel extends DefaultTreeModel {

	private String rootString;

	/**
	 * Instantiates a new file system model.
	 * 
	 * @param startPath
	 *            the start path
	 */
	public FilesTreeModel(String startPath) {
		super(null);
		// setRoot(new File(startPath)); // new DefaultMutableTreeNode(
		rootString = startPath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeModel#getRoot()
	 */
	@Override
	public Object getRoot() {
		return new File(rootString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeModel#getChild(java.lang.Object, int)
	 */
	@Override
	public Object getChild(Object parent, int index) {
		File directory = (File) parent;
		String[] children;
		children = FileComparator.getSortedChildrenNames(directory, filter);
		return new File(directory, children[index]);
	}
	
	private FileFilter filter = new DefaultFileFilter();

	public void setFilter(FileFilter filter){
		this.filter  = filter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeModel#getChildCount(java.lang.Object)
	 */
	@Override
	public int getChildCount(Object parent) {
		File fileItem = (File) parent;
		if (fileItem.isDirectory()) {
			if(filter != null){
			return fileItem.listFiles(filter).length;
			}
			String[] children = fileItem.list();
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
		File[] children;
		if(filter != null){
			children = file.listFiles(filter);
		}else {
		children = file.listFiles();
		}
		for (int i = 0; i < children.length; i++) {
			if (children[i].isDirectory())
				return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.tree.TreeModel#getIndexOfChild(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public int getIndexOfChild(Object parent, Object child) {
		File directory = (File) parent;
		File fileItem = (File) child;
		String[] children = FileComparator.getSortedChildrenNames(directory, filter);
		int result = -1;

		for (int i = 0; i < children.length; ++i) {
			if (fileItem.getName().equals(children[i])) {
				result = i;
				break;
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.tree.TreeModel#valueForPathChanged(javax.swing.tree.TreePath,
	 * java.lang.Object)
	 */
	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
	}

	/**
	 * @param file
	 */
	public void addToPath(File file) {
		//TreePath path = new TreePath(getPathToRoot(file));
		//valueForPathChanged(path, file);
		
	}
}
