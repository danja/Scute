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
	
	public static final ImageIcon validIcon;
	public static final ImageIcon invalidIcon;
	public static final ImageIcon unknownIcon;

	static {
		final ClassLoader loader = IndicatorIcons.class.getClassLoader();

		validIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/indicators/green-button.gif"));
		invalidIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/indicators/red-button.gif"));
		unknownIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/indicators/amber-button.gif"));
	}
}
