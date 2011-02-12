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

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * The Class TreeIcons.
 */
public class ScuteIcons {
	
	/** The Constant applicationIcon. */
	public static final Image applicationIcon;
	

	static {
		final ClassLoader loader = ScuteIcons.class.getClassLoader();
		ImageIcon icon = new ImageIcon(loader.getResource("org/hyperdata/resources/scute/logo.png"));
		applicationIcon = icon.getImage();
	}

	
}
