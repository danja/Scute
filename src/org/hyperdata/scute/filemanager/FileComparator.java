/**
 * 
 */
package org.hyperdata.scute.filemanager;

import java.io.File;
import java.util.Comparator;

/**
 * @author danny
 *
 */
public class FileComparator implements Comparator<File> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(File file1, File file2) {
		if(file1.equals(file2)) return 0;
		if(file1.isDirectory() && !file2.isDirectory()){
			return -1;
		}
		if(!file1.isDirectory() && file2.isDirectory()){
			return 1;
		}
		String filename1 = file1.getName(); 
		String filename2 = file2.getName(); 
		return filename1.compareToIgnoreCase(filename2);
	}

}
