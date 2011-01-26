package org.hyperdata.resources.graph;

import javax.swing.ImageIcon;

public class GraphIcons {
	public static final ImageIcon bigredIcon;
	public static final ImageIcon connectOffIcon;
	public static final ImageIcon connectOnIcon;
	public static final ImageIcon copyIcon;
	public static final ImageIcon cutIcon;
	public static final ImageIcon deleteIcon;
	public static final ImageIcon globeIcon;
	public static final ImageIcon groupIcon;
	public static final ImageIcon insertIcon;
	public static final ImageIcon jGraphIcon;
	public static final ImageIcon layoutIcon;
	public static final ImageIcon pasteIcon;
	public static final ImageIcon redoIcon;
	public static final ImageIcon toBackIcon;
	public static final ImageIcon toFrontIcon;
	public static final ImageIcon undoIcon;
	public static final ImageIcon ungroupIcon;
	public static final ImageIcon zoomIcon;
	public static final ImageIcon zoomInIcon;

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
