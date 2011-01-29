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
package org.hyperdata.resources.general;

import javax.swing.ImageIcon;

/**
 * The Class GeneralIcons.
 */
public class GeneralIcons {
	
	/** The Constant checkUnknownIcon. */
	public static final ImageIcon checkUnknownIcon;
	
	/** The Constant cloneIcon. */
	public static final ImageIcon cloneIcon;
	
	/** The Constant closeIcon. */
	public static final ImageIcon closeIcon;
	
	/** The Constant copyIcon. */
	public static final ImageIcon copyIcon;
	
	/** The Constant cutIcon. */
	public static final ImageIcon cutIcon;
	
	/** The Constant deleteIcon. */
	public static final ImageIcon deleteIcon;
	
	/** The Constant editIcon. */
	public static final ImageIcon editIcon;
	
	/** The Constant exportIcon. */
	public static final ImageIcon exportIcon;
	
	/** The Constant helpIcon. */
	public static final ImageIcon helpIcon;
	
	/** The Constant importIcon. */
	public static final ImageIcon importIcon;
	
	/** The Constant lineIcon. */
	public static final ImageIcon lineIcon;
	
	/** The Constant newIcon. */
	public static final ImageIcon newIcon;
	
	/** The Constant openIcon. */
	public static final ImageIcon openIcon;

	/** The Constant pasteIcon. */
	public static final ImageIcon pasteIcon;
	
	/** The Constant printIcon. */
	public static final ImageIcon printIcon;

	/** The Constant saveAsIcon. */
	public static final ImageIcon saveAsIcon;
	
	/** The Constant saveIcon. */
	public static final ImageIcon saveIcon;

	static {
		final ClassLoader loader = GeneralIcons.class.getClassLoader();

		/*
		 * System.out.println("loader = " + loader); System.out.println(
		 * "loader.getResource(org/hyperdata/resources/general/New16.gif) = " +
		 * loader.getResource( "org/hyperdata/resources/general/New16.gif"));
		 * System.out.println( "loader.getResource(GeneralIcons.class) = " +
		 * loader.getResource(
		 * "org/hyperdata/resources/general/GeneralIcons.class"));
		 */
		newIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/general/New16.gif"));
		openIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/general/Open16.gif"));
		saveIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/general/Save16.gif"));
		saveAsIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/general/SaveAs16.gif"));

		cutIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/general/Cut16.gif"));
		pasteIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/general/Paste16.gif"));
		copyIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/general/Copy16.gif"));
		deleteIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/general/Delete16.gif"));

		helpIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/general/Help16.gif"));

		editIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/general/Edit16.gif"));

		exportIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/general/Export16.gif"));

		importIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/general/Import16.gif"));

		closeIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/general/Close16.gif"));

		cloneIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/general/Clone16.gif"));

		printIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/general/Print16.gif"));

		lineIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/graph/line16.gif"));

		checkUnknownIcon = new ImageIcon(
				loader
						.getResource("org/hyperdata/resources/general/check-unknown.gif"));
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		new GeneralIcons();
	}
}
