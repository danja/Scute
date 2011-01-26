package org.hyperdata.resources.tree;

import javax.swing.ImageIcon;

public class TreeIcons {
	public static final ImageIcon literalIcon;
	public static final ImageIcon modelIcon;
	public static final ImageIcon propertyIcon;
	public static final ImageIcon resourceIcon;
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
