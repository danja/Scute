/**
 * 
 */
package org.hyperdata.scute.filemanager;

import java.io.File;
import java.util.List;

import javax.swing.AbstractListModel;

/**
 * @author danny
 *
 */
public class DirListModel extends AbstractListModel {

    protected File directory;
    protected List<File> files;
    protected int rowCount;

	public DirListModel(File dir){
		super();
        setDirectory(dir);
	}
	
    /**
     * Sets the directory.
     *
     * @param dir the new directory
     */
    public void setDirectory(File dir) {
        if (dir != null) {
            directory = dir;
            files = FileComparator.getSortedChildren(dir);
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
