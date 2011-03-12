/**
 * 
 */
package org.hyperdata.scute.validate;

/**
 * @author danny
 *
 */
public class DocUtils {

	/**
	 * @param text
	 * @param line
	 * @param column
	 * @return
	 */
	public static int getLocation(String text, int lineNumber, int column) { // not tested
		char newline = "\n".charAt(0);
		int i;
		for(i=0;i<text.length();i++){
			char ch = text.charAt(i);
			if(ch == newline){
				if(--lineNumber == 0){
					break;
				}
			}
		}
		return i+column;
	}

}
