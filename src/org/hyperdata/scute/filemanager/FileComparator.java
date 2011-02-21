/**
 * 
 */
package org.hyperdata.scute.filemanager;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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

	/**
	 * Sorts directory children
	 * directories first, then files
	 * alphabetical, ignoring case
	 * 
	 * No doubt could be more efficient, but ok for now
	 * @param directory
	 * @return an array of children names
	 * 
	 */
	public static String[] getSortedChildrenNames(File directory) {
	
		List<File> files = getSortedChildren(directory);
		String[] sorted = new String[files.size()];
		
		for(int i=0;i<files.size();i++){
			sorted[i] = files.get(i).getName();
		//	System.out.println(sorted[i]);
		}	
		return sorted;
	}
	
	public static List<File> getSortedChildren(File directory) {
		File[] children = directory.listFiles();
		
		List<File> files = Arrays.asList(children);
	//	Set<File> sorter = new TreeSet<File>(new FileComparator());
	//	sorter.addAll(files);
		Collections.sort(files, new FileComparator());
		return files;
	}
}
