/**
 * 
 */
package org.hyperdata.old;

import javax.help.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;

import org.hyperdata.scute.system.Log;
public class ScuteHelp2 {
	
   public static void main(String args[]) {
      JHelp helpViewer = null;
      try {
         // Get the classloader of this class.
	      ClassLoader cl = ScuteHelp2.class.getClassLoader();
         // Use the findHelpSet method of HelpSet to create a URL referencing the helpset file.
         // Note that in this example the location of the helpset is implied as being in the same
         // directory as the program by specifying "jhelpset.hs" without any directory prefix,
         // this should be adjusted to suit the implementation.
	      
	    //  URL url = HelpSet.findHelpSet(cl, "jhelpset.hs");
	      File file = new File("doc/www/Scute.hs");
			URL url = null;
			try {
				url = file.toURI().toURL();
			} catch (MalformedURLException exception1) {
				exception1.printStackTrace();
			}
	      
         // Create a new JHelp object with a new HelpSet.
         helpViewer = new JHelp(new HelpSet(cl, url));
         // Set the initial entry point in the table of contents.
         helpViewer.setCurrentID("Overview");
	   } catch (Exception exception) {
	      Log.exception(exception);
     	}

      // Create a new frame.
      JFrame frame = new JFrame();
      // Set it's size.
      frame.setSize(500,500);
      // Add the created helpViewer to it.
      frame.getContentPane().add(helpViewer);
      // Set a default close operation.
      frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      // Make the frame visible.
      frame.setVisible(true);
   }
}
