/**
 * 
 */
package org.hyperdata.scute.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.hyperdata.scute.filemanager.FileExplorerPanel;

/**
 * @author danny
 * 
 */
public class FileExplorerDemo {

	public static void main(String[] argv) {
		JFrame frame = new JFrame("File Explorer");

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		frame.getContentPane().add(
				new FileExplorerPanel(System.getProperty("user.home")));

		frame.setSize(400, 400);
		frame.pack();
		frame.setVisible(true);
	}
}
