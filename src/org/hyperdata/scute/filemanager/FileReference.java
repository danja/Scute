/**
 * 
 */
package org.hyperdata.scute.filemanager;

import java.io.File;

/**
 * @author danny
 *
 */
public interface FileReference {
public void setCurrentFile(File file);
public File getCurrentFile();
}
