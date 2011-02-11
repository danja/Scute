/**
 * 
 */
package org.hyperdata.scute.syspane;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

/**
 * The Class SystemData.
 *
 * @author danja
 * 
 * Check system status and pass to logger
 * 
 * TODO implement
 * 
 * Runtime runtime = Runtime.getRuntime();
 * int freeMemory = (int)(runtime.freeMemory() / 1024);
 * int totalMemory = (int)(runtime.totalMemory() / 1024);
 * int usedMemory = (totalMemory - freeMemory);
 * 
 * System.out.println(System.getProperty("java.class.path"));
 */
public class SystemDataPanel extends JPanel {

	public SystemDataPanel(){
		super();
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setWrapStyleWord(false);
		String text = "";
		
		Runtime runtime = Runtime.getRuntime();
		
		int totalMemory = (int)(runtime.totalMemory() / 1048576);
		text += "\nApplication Total Memory : "+totalMemory+" MB";
		int freeMemory = (int)(runtime.freeMemory() / 1048576);
		text += "\nApplication Used Memory : "+(totalMemory - freeMemory)+" MB";
		text += "\nApplication Free Memory : "+freeMemory+" MB";

		String classpath = System.getProperty("java.class.path");
		classpath = classpath.replace(":", "\n");
		classpath = classpath.replace(";", "\n");
		text += "\n\nClasspath : \n"+classpath;
		
		textArea.setText(text);
		add(textArea);
	}
}
