/**
 * 
 */
package org.hyperdata.scute.filemanager;

import java.io.File;
import java.io.FileFilter;
/**
 * @author danny
 *
 */
public class DefaultFileFilter implements FileFilter {

	/* (non-Javadoc)
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File file) {
		String name = file.getName();
		if(name.startsWith(".")) return false;
		return true;
	}

}
