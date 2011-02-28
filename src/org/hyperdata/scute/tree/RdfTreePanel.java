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
package org.hyperdata.scute.tree;

import java.awt.BorderLayout;
import java.awt.Cursor;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.border.BevelBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.hp.hpl.jena.rdf.model.Model;

import org.hyperdata.scute.autosave.UserActivityListener;
import org.hyperdata.scute.cards.Card;

/**
 * The Class RdfTreePanel.
 * 
 * @author danny
 * 
 *         created : 05-Nov-2002
 * 
 *         D.Ayers 2002
 */
public class RdfTreePanel extends Card implements TreeSelectionListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4397360330476275899L;

	/** The action. */
	protected Action action;

	/** The clicked path. */
	protected TreePath clickedPath;
	
	/** The edit menu. */
	private EditMenu editMenu;

	/** The popup menu. */
	protected JPopupMenu popupMenu;
	// private String selectedUrl;

	/** The tree. */
	private JTree tree;
	
	/** The tree model. */
	private RdfTreeModel treeModel;

	/**
	 * Instantiates a new rdf tree panel.
	 * 
	 * @param model
	 *            the model
	 */
	public RdfTreePanel(Model model) {
		super(new BorderLayout());
		// addFocusListener(focusListener);
		loadModel(model);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		init();
	}
	
	/**
	 * Adds the user activity listener.
	 *
	 * @param listener the listener
	 */
	public void addUserActivityListener(UserActivityListener listener) {
		// TODO to implement when RdfTreePanel supports editing
	}

	/**
	 * Gets the action.
	 * 
	 * @return the action
	 */
	public Action getAction() {
		return getEditMenu().getTreeAction();
	}

	/**
	 * Gets the cell renderer.
	 * 
	 * @return the cell renderer
	 */
	protected TreeCellRenderer getCellRenderer() {
		return new RdfTreeCellRenderer();
	}

	/**
	 * Gets the clicked path.
	 * 
	 * @return the clicked path
	 */
	public TreePath getClickedPath() {
		return clickedPath;
	}

	/**
	 * Gets the edits the menu.
	 * 
	 * @return the edits the menu
	 */
	public EditMenu getEditMenu() {
		return editMenu;
	}

	/**
	 * Gets the popup menu.
	 * 
	 * @return the popup menu
	 */
	public JPopupMenu getPopupMenu() {
		return popupMenu;
	}

	/**
	 * Gets the tree.
	 * 
	 * @return the tree
	 */
	public JTree getTree() {
		return tree;
	}

	//
	/**
	 * Gets the tree model.
	 * 
	 * @return the tree model
	 */
	public RdfTreeModel getTreeModel() {
		return treeModel;
	}

	/**
	 * Inits the.
	 */
	public void init() {
		// System.out.println("init called");
		treeModel = (RdfTreeModel) tree.getModel();
		add(tree, BorderLayout.CENTER);
		final TreeMouseListener mouseListener = new TreeMouseListener(this);
		tree.addMouseListener(mouseListener);
		tree.addTreeSelectionListener(this);
		initMenu();
		initTreeComponent();
		tree.invalidate();
	}

	/**
	 * Inits the menu.
	 */
	public void initMenu() {
		editMenu = new EditMenu(this);
		popupMenu = editMenu.getPopupMenu();
		tree.add(popupMenu);
	}

	/**
	 * Inits the tree component.
	 */
	protected void initTreeComponent() {
		ToolTipManager.sharedInstance().registerComponent(tree);
		tree.setCellRenderer(getCellRenderer());

		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		// tree.addTreeSelectionListener(this);
		// tree.getPreferredScrollableViewportSize();

		tree.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

		final int count = tree.getRowCount();
		for (int x = 0; x < count; x++) { // was 0
			tree.expandRow(x);
		}
		tree.revalidate();
	}

	/* (non-Javadoc)
	 * @see java.awt.Component#setSize(java.awt.Dimension)
	 */
//	@Override
//	public void setSize(Dimension size) {
//		setPreferredSize(size);
//		tree.setSize(size);
//	}

	/**
	 * Sets the clicked path.
	 * 
	 * @param path
	 *            the new clicked path
	 */
	public void setClickedPath(TreePath path) {
		clickedPath = path;
	}

	// Listen for when the selection changes.
	/* (non-Javadoc)
	 * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
	 */
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		final Object object = tree.getLastSelectedPathComponent();

		if ((object == null) || !(object instanceof ResourceNode))
			return;
		// final ResourceNode node = (ResourceNode) object;
		// selectedUrl = node.getResource().getURI();
		// tree.scrollPathToVisible(path);
		tree.revalidate();
	}

	/**
	 * Load model.
	 * 
	 * @param model
	 *            the model
	 */
	public void loadModel(Model model) {
		RdfNodeMap nodeMap = new RdfNodeMap(model);
		RootNode root = new RootNode(nodeMap);
		root.setModel(model);
		nodeMap.interpret();
		treeModel = new RdfTreeModel(root, model, nodeMap);
		tree = new JTree(treeModel);
		tree.putClientProperty("JTree.lineStyle", "Angled");
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		ToolTipManager.sharedInstance().registerComponent(tree);
		tree.setCellRenderer(new RdfTreeCellRenderer());
	}


}
