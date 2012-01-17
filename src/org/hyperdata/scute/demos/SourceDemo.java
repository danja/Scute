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

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import org.hyperdata.scute.main.Config;
import org.hyperdata.scute.syntax.ScuteEditorKit;
import org.hyperdata.scute.system.Log;
import org.hyperdata.scute.source.*;

/**
 * The Class SourceDemo.
 * 
 * FIXME finish
 */
public class SourceDemo extends EditorPane {

	public SourceDemo(String syntax) {
		super(syntax);
		setFilename("./temp.txt");
		setFont(new Font("Monospaced", Font.PLAIN, 12)); 
		setEditorKit(new ScuteEditorKit("SPARQL"));
	}

	/* (non-Javadoc) 
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

	//	final String filename = "./data/sample2.ttl";
SourceDemo sd = new SourceDemo("Turtle");

		// TODO show source window!!

		final JFrame frame = new JFrame();
		frame.getContentPane().add(sd);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	//	frame.pack();
		frame.setSize(500, 500);
		frame.setLocation(100,100);
		frame.setVisible(true);
	}
}
