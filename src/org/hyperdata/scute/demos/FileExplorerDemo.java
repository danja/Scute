/**
 * 
 */
package org.hyperdata.scute.demos;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;

import org.hyperdata.scute.filemanager.FileExplorerCard;

/**
 * @author danny
 * 
 */
public class FileExplorerDemo {

	public static void main(String[] argv) {
		try {
//			UIManager.setLookAndFeel(
//			        UIManager.getSystemLookAndFeelClassName());
		//	UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			
	//		NimRODTheme nt = new NimRODTheme( new URL( "http://personales.ya.com/nimrod/data/Burdeos.theme"));
		
			NimRODTheme nt = new NimRODTheme( "./Snow.theme");
			NimRODLookAndFeel nf = new NimRODLookAndFeel();
			nf.setCurrentTheme( nt);
			UIManager.setLookAndFeel( nf);
			
			// UIManager.setLookAndFeel( new com.nilo.plaf.nimrod.NimRODLookAndFeel());
		} catch (Exception exception) {
			// ignore
			exception.printStackTrace();
		} 
		JFrame frame = new JFrame("File Explorer");

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		frame.getContentPane().add(
				new FileExplorerCard(System.getProperty("user.home")));
frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.pack();
		frame.setVisible(true);
	}
}
