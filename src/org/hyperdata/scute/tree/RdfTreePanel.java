package org.hyperdata.scute.tree;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
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

/**
 * @author danny
 * 
 *         created : 05-Nov-2002
 * 
 *         D.Ayers 2002
 */
public class RdfTreePanel extends JPanel implements TreeSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4397360330476275899L;

	protected Action action;

	protected TreePath clickedPath;
	private EditMenu editMenu;

	protected JPopupMenu popupMenu;
	// private String selectedUrl;

	private JTree tree;
	private RdfTreeModel treeModel;

	public RdfTreePanel(Model model) {
		super(new BorderLayout());
		// addFocusListener(focusListener);
		loadModel(model);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		init();
	}

	public Action getAction() {
		return getEditMenu().getTreeAction();
	}

	protected TreeCellRenderer getCellRenderer() {
		return new RdfTreeCellRenderer();
	}

	public TreePath getClickedPath() {
		return clickedPath;
	}

	public EditMenu getEditMenu() {
		return editMenu;
	}

	public JPopupMenu getPopupMenu() {
		return popupMenu;
	}

	public JTree getTree() {
		return tree;
	}

	//
	public RdfTreeModel getTreeModel() {
		return treeModel;
	}

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

	public void initMenu() {
		editMenu = new EditMenu(this);
		popupMenu = editMenu.getPopupMenu();
		tree.add(popupMenu);
	}

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

	@Override
	public void setSize(Dimension size) {
		setPreferredSize(size);
		tree.setSize(size);
	}

	public void setClickedPath(TreePath path) {
		clickedPath = path;
	}

	// Listen for when the selection changes.
	public void valueChanged(TreeSelectionEvent e) {
		final Object object = tree.getLastSelectedPathComponent();

		if ((object == null) || !(object instanceof ResourceNode))
			return;
		// final ResourceNode node = (ResourceNode) object;
		// selectedUrl = node.getResource().getURI();
		// tree.scrollPathToVisible(path);
		tree.revalidate();
	}

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
