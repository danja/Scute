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
package org.hyperdata.resources.scute;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


/**
 * The Class TreeIcons.
 */
public class ScuteIcons {
	
	/** The Constant applicationIcon. */
	public static final ImageIcon applicationIcon;
	public static final ImageIcon rdfIcon;	
	public static final ImageIcon sparqlIcon;
	public static final ImageIcon runIcon;
	public static final ImageIcon stopIcon;
	public static final ImageIcon errorIcon;
	public static BufferedImage bigImage = null;	

	static {
		final ClassLoader loader = ScuteIcons.class.getClassLoader();
		applicationIcon = new ImageIcon(loader.getResource("org/hyperdata/resources/scute/logo.png"));
		
		rdfIcon = new ImageIcon(loader.getResource("org/hyperdata/resources/scute/rdf-logo-16x16.png"));
		sparqlIcon = new ImageIcon(loader.getResource("org/hyperdata/resources/scute/sparql-16x16.png"));
		runIcon = new ImageIcon(loader.getResource("org/hyperdata/resources/scute/run-16x16.gif"));	
		stopIcon = new ImageIcon(loader.getResource("org/hyperdata/resources/scute/stop-16x16.gif"));
		errorIcon = new ImageIcon(loader.getResource("org/hyperdata/resources/scute/error-16x16.gif"));
		
		try {
			System.out.println(loader.getResource("org/hyperdata/resources/scute/turtle-zoom.jpg").getFile());
			bigImage = ImageIO.read(new File(loader.getResource("org/hyperdata/resources/scute/turtle-zoom.jpg").getFile()));
		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}

	
}
