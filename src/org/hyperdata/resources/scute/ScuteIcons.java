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

import javax.swing.ImageIcon;

/**
 * The Class TreeIcons.
 */
public class ScuteIcons {
	
	/** The Constant applicationIcon. */
	public static final ImageIcon applicationIcon;
	public static final ImageIcon rdfIcon;	
	public static final ImageIcon sparqlIcon;	

	static {
		final ClassLoader loader = ScuteIcons.class.getClassLoader();
		applicationIcon = new ImageIcon(loader.getResource("org/hyperdata/resources/scute/logo.png"));
		
		rdfIcon = new ImageIcon(loader.getResource("org/hyperdata/resources/scute/rdf-logo-16x16.png"));
		sparqlIcon = new ImageIcon(loader.getResource("org/hyperdata/resources/scute/sparql-16x16.png"));
	}

	
}
