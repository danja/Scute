/**
 * 
 */
package org.hyperdata.scute.demos.temp;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import org.hyperdata.scute.filemanager.FileReference;

/**
 * @author danny
 * 
 */
public class SourceEditor extends JEditorPane implements FileReference {

	JScrollPane scrollPane;

	public SourceEditor() {
		super();
		setFont(new Font("Monospaced", Font.PLAIN, 12));
		scrollPane = new JScrollPane(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.hyperdata.scute.filemanager.FileReference#setCurrentFile(java.io.
	 * File)
	 */
	@Override
	public void setCurrentFile(final File file) {
//		System.out.println("setting file "+file);
//		final String url = "file:///" + file.getAbsolutePath();
//		URL newURL = null;
//		try {
//			newURL = new URL(url);
//		} catch (MalformedURLException exception) {
//			exception.printStackTrace();
//		}
//		URL loadedURL = getPage();
//		if (loadedURL != null && loadedURL.sameFile(newURL)) {
//			return;
//		}
//		// maybe display "Loading..."
//		jsyntaxpane.DefaultSyntaxKit.initKit();
//    	setContentType("text/sparql"); // change according to file type
//				 		setPage(url); // SETPAGE BUGGERS UP SYNTAX HIGHLIGHTING

				String text;
				try {
					jsyntaxpane.DefaultSyntaxKit.initKit();
					text = readFileAsString(file);
					setText(text);
					
					setContentType(Mime.getType(file.getName()));
				} catch (IOException exception) {
					// TODO Auto-generated catch block
					exception.printStackTrace();
				}

//		System.out.println("trying URL");
//				// jsyntaxpane.DefaultSyntaxKit.initKit();
//				
//				final String url = "file:///" + file.getAbsolutePath();
//				URL newURL = null;
//				try {
//					newURL = new URL(url);
//				} catch (MalformedURLException exception) {
//					exception.printStackTrace();
//				}
//				URL loadedURL = getPage();
//				if (loadedURL != null && loadedURL.sameFile(newURL)) {
//					return;
//				}
//				try {
//					
//					jsyntaxpane.DefaultSyntaxKit.initKit();
//					setContentType(Mime.getType(file.getName()));
//					setPage(newURL);
//					//System.out.println("SETTING type "+Mime.getType(file.getName()));
//				} catch (IOException exception) {
//					// TODO Auto-generated catch block
//					exception.printStackTrace();
//				}
					
			//	validate();
				
			
//			} catch (IOException exception) {
//				exception.printStackTrace();
//			}
	}
	
	   private static String readFileAsString(File file)
			    throws java.io.IOException{
		  
			        StringBuffer fileData = new StringBuffer(1000);
			        BufferedReader reader = new BufferedReader(
			                new FileReader(file));
			        char[] buf = new char[1024];
			        int numRead=0;
			        while((numRead=reader.read(buf)) != -1){
			            String readData = String.valueOf(buf, 0, numRead);
			            fileData.append(readData);
			            buf = new char[1024];
			        }
			        reader.close();
			        return fileData.toString();
			    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperdata.scute.filemanager.FileReference#getCurrentFile()
	 */
	@Override
	public File getCurrentFile() {
		return new File(getPage().getFile());
	}

	/**
	 * @return the scrollPane
	 */
	public JScrollPane getScrollPane() {
		return this.scrollPane;
	}

}
