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

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.hyperdata.scute.system.Log;


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
	public static final ImageIcon previousIcon;
	public static final ImageIcon nextIcon;	
	
	public static final ImageIcon bigImageIcon;
	public static BufferedImage bigImage = null;	
	
//	public static final Image spin0;
//	public static final Image spin1;
//	public static final Image spin2;
//	public static final Image spin3;
//	public static final Image spin4;
//	public static final Image spin5;
	public static Image[] spin = new Image[6];
	public static final Image transparent;
	public static Cursor transparentCursor;
	
	public static Cursor[] spinCube = new Cursor[4];
	public static Image[] cubes = new Image[4];
	
	static {
		final ClassLoader loader = ScuteIcons.class.getClassLoader();

		applicationIcon = new ImageIcon(loader.getResource("org/hyperdata/resources/scute/logo.png"));
		
		rdfIcon = new ImageIcon(loader.getResource("org/hyperdata/resources/scute/rdf-logo-16x16.png"));
		sparqlIcon = new ImageIcon(loader.getResource("org/hyperdata/resources/scute/sparql-16x16.png"));
		runIcon = new ImageIcon(loader.getResource("org/hyperdata/resources/scute/run-16x16.gif"));	
		stopIcon = new ImageIcon(loader.getResource("org/hyperdata/resources/scute/stop-16x16.gif"));
		errorIcon = new ImageIcon(loader.getResource("org/hyperdata/resources/scute/error-16x16.gif"));
		previousIcon = new ImageIcon(loader.getResource("org/hyperdata/resources/scute/previous-16x16.png"));
		nextIcon = new ImageIcon(loader.getResource("org/hyperdata/resources/scute/next-16x16.png"));
		bigImageIcon = new ImageIcon(loader.getResource("org/hyperdata/resources/scute/turtle-zoom.jpg"));	
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();  

		spin[0] = toolkit.getImage(loader.getResource("org/hyperdata/resources/scute/spin0.gif"));
		spin[1] = toolkit.getImage(loader.getResource("org/hyperdata/resources/scute/spin1.gif"));
		spin[2] = toolkit.getImage(loader.getResource("org/hyperdata/resources/scute/spin2.gif"));
		spin[3] = toolkit.getImage(loader.getResource("org/hyperdata/resources/scute/spin3.gif"));
		spin[4] = toolkit.getImage(loader.getResource("org/hyperdata/resources/scute/spin4.gif"));
		spin[5] = toolkit.getImage(loader.getResource("org/hyperdata/resources/scute/spin5.gif"));
		
		transparent = toolkit.getImage(loader.getResource("org/hyperdata/resources/scute/transparent.gif"));
		transparentCursor = toolkit.createCustomCursor(transparent, new Point(0,0), "transparent");
		
		cubes[0] = toolkit.getImage(loader.getResource("org/hyperdata/resources/scute/cube0.gif"));
		spinCube[0] = toolkit.createCustomCursor(cubes[0], new Point(0,0), "cubes0");
		cubes[1] = toolkit.getImage(loader.getResource("org/hyperdata/resources/scute/cube1.gif"));
		spinCube[1] = toolkit.createCustomCursor(cubes[1], new Point(0,0), "cubes1");
		cubes[2] = toolkit.getImage(loader.getResource("org/hyperdata/resources/scute/cube2.gif"));
		spinCube[2] = toolkit.createCustomCursor(cubes[2], new Point(0,0), "cubes2");
		cubes[3] = toolkit.getImage(loader.getResource("org/hyperdata/resources/scute/cube3.gif"));
		spinCube[3] = toolkit.createCustomCursor(cubes[3], new Point(0,0), "cubes3");
		
		try {
		//	System.out.println(loader.getResource("org/hyperdata/resources/scute/turtle-zoom.jpg").getFile());
			bigImage = ImageIO.read(new File(loader.getResource("org/hyperdata/resources/scute/turtle-zoom.jpg").getFile()));
		} catch (IOException exception) {
			Log.exception(exception);
		}

	}

	
}
