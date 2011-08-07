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
package org.hyperdata.resources.indicators;

import javax.swing.ImageIcon;

/**
 * The Class TreeIcons.
 */
public class IndicatorIcons {
	
	public static final ImageIcon greenIcon;
	public static final ImageIcon redIcon;
	public static final ImageIcon amberIcon;
	public static final ImageIcon blueIcon;

	static {
		final ClassLoader loader = IndicatorIcons.class.getClassLoader();

		greenIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/indicators/green-button-medium.gif"));
		redIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/indicators/red-button-medium.gif"));
		amberIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/indicators/amber-button-medium.gif"));
		blueIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/indicators/blue-button-medium.gif"));
	}
}
