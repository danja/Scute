/**
 * 
 */
package org.hyperdata.scute.filemanager;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
	
	public static String[] getSortedChildrenNames(File directory) {
		return getSortedChildrenNames(directory, null);
	}

	/**
	 * Sorts directory children
	 * directories first, then files
	 * alphabetical, ignoring case
	 * 
	 * @param directory
	 * @return an array of children names
	 * 
	 */
	public static String[] getSortedChildrenNames(File directory, FileFilter filter) {
	
		List<File> files = getSortedChildren(directory);
		if(filter != null){
			files = filter(files, filter);
		}
		String[] sorted = new String[files.size()];
		
		for(int i=0;i<files.size();i++){
			sorted[i] = files.get(i).getName();
		}	
		return sorted;
	}
	
	public static List<File> filter(List<File> files, FileFilter filter){
		List<File> filteredFiles = new ArrayList<File>();
		for(int i=0;i<files.size();i++){ // inefficient but who cares..?
			File file = files.get(i);
			if(filter.accept(file)){
				filteredFiles.add(file);
			}
		}
		return filteredFiles;
	}
	
	public static List<File> getSortedChildren(File directory) {
		File[] children = directory.listFiles();
		List<File> files = Arrays.asList(children);
		Collections.sort(files, new FileComparator());
		return files;
	}
}
