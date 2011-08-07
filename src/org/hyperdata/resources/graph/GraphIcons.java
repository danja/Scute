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
package org.hyperdata.resources.graph;

import javax.swing.ImageIcon;

/**
 * The Class GraphIcons.
 */
public class GraphIcons {
	
	/** The Constant bigredIcon. */
	public static final ImageIcon bigredIcon;
	
	/** The Constant connectOffIcon. */
	public static final ImageIcon connectOffIcon;
	
	/** The Constant connectOnIcon. */
	public static final ImageIcon connectOnIcon;
	
	/** The Constant copyIcon. */
	public static final ImageIcon copyIcon;
	
	/** The Constant cutIcon. */
	public static final ImageIcon cutIcon;
	
	/** The Constant deleteIcon. */
	public static final ImageIcon deleteIcon;
	
	/** The Constant globeIcon. */
	public static final ImageIcon globeIcon;
	
	/** The Constant groupIcon. */
	public static final ImageIcon groupIcon;
	
	/** The Constant insertIcon. */
	public static final ImageIcon insertIcon;
	
	/** The Constant jGraphIcon. */
	public static final ImageIcon jGraphIcon;
	
	/** The Constant layoutIcon. */
	public static final ImageIcon layoutIcon;
	
	/** The Constant pasteIcon. */
	public static final ImageIcon pasteIcon;
	
	/** The Constant redoIcon. */
	public static final ImageIcon redoIcon;
	
	/** The Constant toBackIcon. */
	public static final ImageIcon toBackIcon;
	
	/** The Constant toFrontIcon. */
	public static final ImageIcon toFrontIcon;
	
	/** The Constant undoIcon. */
	public static final ImageIcon undoIcon;
	
	/** The Constant ungroupIcon. */
	public static final ImageIcon ungroupIcon;
	
	/** The Constant zoomIcon. */
	public static final ImageIcon zoomIcon;
	
	/** The Constant zoomInIcon. */
	public static final ImageIcon zoomInIcon;

	/** The Constant zoomOutIcon. */
	public static final ImageIcon zoomOutIcon;

	static {
		final ClassLoader loader = GraphIcons.class.getClassLoader();

		connectOffIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/graph/connectoff.gif"));
		connectOnIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/graph/connecton.gif"));
		copyIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/graph/copy.gif"));
		cutIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/graph/cut.gif"));
		deleteIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/graph/delete.gif"));
		groupIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/graph/group.gif"));
		insertIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/graph/insert.gif"));
		jGraphIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/graph/jgraph.gif"));
		pasteIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/graph/paste.gif"));
		redoIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/graph/redo.gif"));
		toBackIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/graph/toback.gif"));
		toFrontIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/graph/tofront.gif"));
		undoIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/graph/undo.gif"));
		ungroupIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/graph/ungroup.gif"));
		zoomIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/graph/zoom.gif"));
		zoomInIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/graph/zoomin.gif"));
		zoomOutIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/graph/zoomout.gif"));

		globeIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/graph/Globe16.gif"));

		layoutIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/graph/layout16.gif"));

		bigredIcon = new ImageIcon(loader
				.getResource("org/hyperdata/resources/graph/bigred.gif"));
	}
}
