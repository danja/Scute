/*
 * Scute
 * 
 * Homepage: http://hyperdata.org/scute
 * 
 * License : http://www.apache.org/licenses/LICENSE-2.0
 * See also license.txt or http://hyperdata.org/wiki/Scute:License
 * 
 * Danny Ayers 2011
 */
package org.hyperdata.scute.demos;

import java.awt.Font;
import java.io.FileInputStream;

import java.io.InputStream;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;

import org.hyperdata.scute.demos.temp.SourceEditor;
import org.hyperdata.scute.main.Config;
import org.hyperdata.scute.syntax.ScuteEditorKit;
import org.hyperdata.scute.system.Log;
import org.hyperdata.scute.source.*;

/**
 */

import jsyntaxpane.*;

public class SourceDemo {

	
	public SourceDemo(String syntax) {
	//	super(syntax);
	//	setFilename("./temp.txt");
	

	}


	
	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		try {
//			UIManager.setLookAndFeel( // is ugly on this machine!!
//			        UIManager.getSystemLookAndFeelClassName());
			// better than default
		//	UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			NimRODTheme nt = new NimRODTheme( "./Scute.theme");
			NimRODLookAndFeel nf = new NimRODLookAndFeel();
			nf.setCurrentTheme( nt);
			UIManager.setLookAndFeel( nf);
		} catch (Exception exception) {
			// ignore
		} 
		
	//	final String filename = "./data/sample2.ttl";
 
		SourceEditor editor = new SourceEditor();
jsyntaxpane.DefaultSyntaxKit.initKit();
editor.setContentType("text/sparql");


		final JFrame frame = new JFrame("Source Demo");
		frame.getContentPane().add(editor.getScrollPane());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	//	frame.pack();
		frame.setSize(500, 500);
		frame.setLocation(100,100);
		frame.setVisible(true);
	}
}
