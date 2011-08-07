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
package org.hyperdata.resources.tree;

import javax.swing.ImageIcon;

/**
 * The Class TreeIcons.
 */
public class TreeIcons {
	
	/** The Constant literalIcon. */
	public static final ImageIcon literalIcon;
	
	/** The Constant modelIcon. */
	public static final ImageIcon modelIcon;
	
	/** The Constant propertyIcon. */
	public static final ImageIcon propertyIcon;
	
	/** The Constant resourceIcon. */
	public static final ImageIcon resourceIcon;
	
	/** The Constant rootIcon. */
	public static final ImageIcon rootIcon;

	static {
		final ClassLoader loader = TreeIcons.class.getClassLoader();

		resourceIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/tree/resource16.gif"));
		propertyIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/tree/property16.gif"));
		literalIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/tree/literal16.gif"));
		modelIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/tree/model16.gif"));
		rootIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/tree/root16.gif"));
	}
}
