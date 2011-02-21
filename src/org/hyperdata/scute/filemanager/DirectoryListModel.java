/**
 * 
 */
package org.hyperdata.scute.filemanager;

import java.io.File;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.UIManager;

/**
 * @author danny
 *
 */
public class DirectoryListModel extends AbstractListModel {

    protected File directory;
   // protected String[] children;
    protected List<File> files;
    protected int rowCount;
    protected Object dirIcon;
    protected Object fileIcon;
    
	public DirectoryListModel(File dir){
		super();
        setDirectory(dir);
        dirIcon = UIManager.get("DirectoryPane.directoryIcon");
        fileIcon = UIManager.get("DirectoryPane.fileIcon");
	}
	
    /**
     * Sets the directory.
     *
     * @param dir the new directory
     */
    public void setDirectory(File dir) {
        if (dir != null) {
            directory = dir;
         //   children =   FileComparator.getSortedChildrenNames(dir);
            files = FileComparator.getSortedChildren(dir);
          //  children = dir.list();
            rowCount = files.size();
        }
        else {
            directory = null;
            files = null;
            rowCount = 0;
        }
        fireContentsChanged(this, 0, rowCount);
    }
	
	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	@Override
	public Object getElementAt(int i) {
		return files.get(i);
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getSize()
	 */
	@Override
	public int getSize() {
		return rowCount;
	}

}
