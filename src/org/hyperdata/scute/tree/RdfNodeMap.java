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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

/**
 * The Class RdfNodeMap.
 */
public class RdfNodeMap {
	
	/** The current model. */
	private Model currentModel;
	
	/** The current model filename. */
	private String currentModelFilename;
	
	/** The literals. */
	private Map<Literal, AbstractRdfTreeNode> literals;
	
	/** The model. */
	private Model model;

	/** The models. */
	private Map<RDFNode, AbstractRdfTreeNode> models;

	/** The resources. */
	private Map<Resource, AbstractRdfTreeNode> resources;
	
	/** The root node. */
	private RootNode rootNode;

	/** The statements. */
	private Map<Statement, AbstractRdfTreeNode> statements;
	
	/** The tree model. */
	private TreeModel treeModel;

	/**
	 * Instantiates a new rdf node map.
	 * 
	 * @param model
	 *            the model
	 */
	public RdfNodeMap(Model model) {
		setTopModel(model);

		initMaps();

		interpret();
	}

	/**
	 * Adds the child model.
	 * 
	 * @param parentStatement
	 *            the parent statement
	 * @param resource
	 *            the resource
	 */
	protected void addChildModel(Statement parentStatement, Resource resource) {
		/*
		 * // System.out.println("GOT A MODEL"); String filename = RdfUtils
		 * .getFirstPropertyValue(resource, FILESYSTEM.local) .toString(); Model
		 * childModel = RdfUtils.load(filename); currentModelFilename =
		 * filename;
		 * 
		 * getResourceMap().put( resource, new ModelNode(this, childModel,
		 * parentStatement, resource)); interpret(childModel);
		 */
	}

	/**
	 * Adds the literal.
	 * 
	 * @param statement
	 *            the statement
	 * @param literal
	 *            the literal
	 */
	public void addLiteral(Statement statement, Literal literal) {
		if (!literals.containsKey(literal)) {
			final LiteralNode literalNode = new LiteralNode(this, statement,
					literal);

			// literalNode.setModel(currentModel);
			// literalNode.setModelFilename(currentModelFilename);

			literals.put(literal, literalNode);
		}
	}

	/**
	 * Adds the resource.
	 * 
	 * @param resource
	 *            the resource
	 */
	public void addResource(Resource resource) {
		addResource(null, resource);
	}

	/**
	 * Adds the resource.
	 * 
	 * @param statement
	 *            the statement
	 * @param resource
	 *            the resource
	 */
	public void addResource(Statement statement, Resource resource) {
		// System.out.println("adding resource");
		try {
			if (!getResourceMap().containsKey(resource)) {
				/*
				 * if (resource.hasProperty(FILESYSTEM.type, FILESYSTEM.Model))
				 * { addChildModel(statement, resource);
				 * 
				 * } else {
				 */
				final ResourceNode resourceNode = new ResourceNode(this,
						statement, resource);

				getResourceMap().put(resource, resourceNode);

				// }
			}
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Adds the statement.
	 * 
	 * @param statement
	 *            the statement
	 */
	public void addStatement(Statement statement) {
		if (!statements.containsKey(statement)) {
			// System.out.println("\nputting st " + statement);
			final StatementNode statementNode = new StatementNode(this,
					statement);

			statements.put(statement, statementNode);

			// System.out.println("get st " + statements.get(statement));
		}
	}

	/**
	 * Creates the root node.
	 */
	public void createRootNode() {
		// System.out.println("CREATING BASE ROOT NODE");
		rootNode = new RootNode(this);
	}

	/**
	 * Gets the current model.
	 * 
	 * @return the current model
	 */
	public Model getCurrentModel() {
		return currentModel;
	}

	/**
	 * Gets the current model filename.
	 * 
	 * @return the current model filename
	 */
	public String getCurrentModelFilename() {
		return currentModelFilename;
	}

	/*
	 * public StatementTreeNode getPropertyNode(Property property) { Object
	 * object = statements.get(property); if (object != null) { return
	 * (StatementTreeNode) object; } return null; }
	 */
	/**
	 * Gets the literal node.
	 * 
	 * @param literal
	 *            the literal
	 * @return the literal node
	 */
	public LiteralNode getLiteralNode(Literal literal) {
		final Object object = literals.get(literal);

		if (object != null)
			return (LiteralNode) object;

		return null;
	}

	/**
	 * Returns the literals.
	 * 
	 * @return HashMap
	 */
	public Map<Literal, AbstractRdfTreeNode> getLiterals() {
		return literals;
	}

	/**
	 * Gets the resource map.
	 * 
	 * @return the resource map
	 */
	public Map<Resource, AbstractRdfTreeNode> getResourceMap() {
		return resources;
	}

	/**
	 * Gets the resource node.
	 * 
	 * @param resource
	 *            the resource
	 * @return the resource node
	 */
	public ResourceNode getResourceNode(Resource resource) {
		final Object object = resources.get(resource);

		if (object != null)
			return (ResourceNode) object;

		return null;
	}

	/**
	 * Gets the resources.
	 * 
	 * @return the resources
	 */
	public Set<Resource> getResources() {
		return resources.keySet();
	}

	/*
	 * public void passModelToNode(RdfNode node) {
	 * System.out.println("CURRENT MODEL = "+currentModel);
	 * node.setModel(currentModel); node.setModelFilename(currentModelFilename);
	 * }
	 */

	/**
	 * Returns the rootNode.
	 * 
	 * @return RootNode
	 */
	public TreeNode getRoot() {
		return rootNode;
	}

	/**
	 * Gets the statement node.
	 * 
	 * @param statement
	 *            the statement
	 * @return the statement node
	 */
	public StatementNode getStatementNode(Statement statement) {
		// System.out.println("S = " + statement);
		final Object object = statements.get(statement);

		// System.out.println("object= " + object);
		if (object != null)
			return (StatementNode) object;

		return null;
	}

	/**
	 * Returns the statements.
	 * 
	 * @return HashMap
	 */
	public Map<Statement, AbstractRdfTreeNode> getStatements() {
		return statements;
	}

	/**
	 * Returns the model.
	 * 
	 * @return Model
	 */
	public Model getTopModel() {
		return model;
	}

	/**
	 * Gets the tree model.
	 * 
	 * @return the tree model
	 */
	public TreeModel getTreeModel() {
		return treeModel;
	}

	/**
	 * Gets the tree node.
	 * 
	 * @param rdfNode
	 *            the rdf node
	 * @return the tree node
	 */
	public TreeNode getTreeNode(RDFNode rdfNode) {
		// if (rdfNode instanceof Statement) {
		// return getStatementNode((Statement) rdfNode);
		// }

		if (rdfNode instanceof Resource)
			return getResourceNode((Resource) rdfNode);

		if (rdfNode instanceof Literal)
			return getLiteralNode((Literal) rdfNode);

		return null;
	}

	/**
	 * Gets the tree node.
	 * 
	 * @param statement
	 *            the statement
	 * @return the tree node
	 */
	public TreeNode getTreeNode(Statement statement) {

		return getStatementNode(statement);
	}

	/**
	 * Inits the maps.
	 */
	private void initMaps() {
		models = new HashMap<RDFNode, AbstractRdfTreeNode>();
		resources = new HashMap<Resource, AbstractRdfTreeNode>();
		statements = new HashMap<Statement, AbstractRdfTreeNode>();
		literals = new HashMap<Literal, AbstractRdfTreeNode>();
	}

	/**
	 * Interpret.
	 */
	public void interpret() {

		currentModel = getTopModel();
		// System.out.println("interpreting" + currentModel);
		interpret(getTopModel());
		// rootNode = new RootNode(this);
		createRootNode();
	}

	/**
	 * Interpret.
	 * 
	 * @param sourceModel
	 *            the source model
	 */
	private void interpret(Model sourceModel) {
		currentModel = sourceModel;
		try {
			final StmtIterator iterator = sourceModel.listStatements();
			Statement statement;
			RDFNode object;

			while (iterator.hasNext()) {
				statement = iterator.next();
				object = statement.getObject();

				addResource(statement, statement.getSubject());
				addStatement(statement);

				if (object instanceof Resource) {
					addResource((Resource) object);
				} else {
					addLiteral(statement, (Literal) object);
				}
			}
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Load model.
	 * 
	 * @param model
	 *            the model
	 */
	public void loadModel(Model model) {
		this.model = model;
		initMaps();
		interpret();

		Iterator<AbstractRdfTreeNode> iterator = models.values().iterator();

		while (iterator.hasNext()) {
			((ModelNode) iterator.next()).setDirty(true);
		}

		iterator = resources.values().iterator();

		while (iterator.hasNext()) {
			((ResourceNode) iterator.next()).setDirty(true);
		}

		iterator = statements.values().iterator();

		while (iterator.hasNext()) {
			((StatementNode) iterator.next()).setDirty(true);
		}

		iterator = literals.values().iterator();

		while (iterator.hasNext()) {
			((LiteralNode) iterator.next()).setDirty(true);
		}
	}

	/**
	 * Removes the literal.
	 * 
	 * @param literal
	 *            the literal
	 */
	public void removeLiteral(Literal literal) {
		final LiteralNode node = (LiteralNode) literals.get(literal);

		// System.out.println("MODEL");
		// RdfUtils.show(node.getModel());
		// System.out.println("-------");

		final Statement parentStatement = node.getParentStatement();

		// System.out.println("parentStatement");
		// RdfUtils.show(parentStatement);
		// System.out.println("BEFORE");
		// RdfUtils.show(node.getModel());

		try {

			node.getModel().remove(parentStatement);

		} catch (final Exception exception) {
			exception.printStackTrace();
		}

		/*
		 * System.out.println("AFTER"); RdfUtils.show(node.getModel());
		 */
		// literals.remove(literal);
		// System.out.println("REMOVED!!!!!" + literals.get(literal));
		// node.removeFromParent();

		loadModel(model);
		((RdfTreeModel) treeModel).loadModel(model);

		// statements.remove(parentStatement);
		/*
		 * MutableTreeNode node = (MutableTreeNode) literals.get(literal);
		 * System.out.println ("parent NODE = "+(MutableTreeNode)
		 * node.getParent());
		 * 
		 * 
		 * MutableTreeNode parent = (MutableTreeNode)node.getParent(); if
		 * (parent != null) { // parent.remove(node); int i =
		 * parent.getIndex(node); System.out.println("i= "+i); MutableTreeNode
		 * child = (MutableTreeNode)parent.getChildAt(i);
		 * System.out.println("child = "+child); parent.remove(i);
		 */

		// node.getParent().children().elementAt(node.getParent().getIndex(node));

	}

	// note model identified by resource in parent
	/**
	 * Removes the model.
	 * 
	 * @param resource
	 *            the resource
	 */
	public void removeModel(Resource resource) {
		// MutableTreeNode node = (MutableTreeNode) models.get(resource);
		// ((MutableTreeNode) node.getParent()).remove(node);

		models.remove(resource);
	}

	/**
	 * Removes the resource.
	 * 
	 * @param resource
	 *            the resource
	 */
	public void removeResource(Resource resource) {
		// MutableTreeNode node = (MutableTreeNode) resources.get(resource);
		// ((MutableTreeNode) node.getParent()).remove(node);
		resources.remove(resource);
	}

	/**
	 * Removes the statement.
	 * 
	 * @param statement
	 *            the statement
	 */
	public void removeStatement(Statement statement) {
		// MutableTreeNode node = (MutableTreeNode) statements.get(statement);
		// ((MutableTreeNode) node.getParent()).remove(node);
		statements.remove(statement);
	}

	/**
	 * Sets the current model.
	 * 
	 * @param model
	 *            the new current model
	 */
	public void setCurrentModel(Model model) {
		currentModel = model;
	}

	/**
	 * Sets the current model filename.
	 * 
	 * @param string
	 *            the new current model filename
	 */
	public void setCurrentModelFilename(String string) {
		currentModelFilename = string;
	}

	/**
	 * Sets the literals.
	 * 
	 * @param literals
	 *            The literals to set
	 */
	public void setLiterals(HashMap<Literal, AbstractRdfTreeNode> literals) {
		this.literals = literals;
	}

	/**
	 * Sets the resources.
	 * 
	 * @param resources
	 *            The resources to set
	 */
	public void setResources(HashMap<Resource, AbstractRdfTreeNode> resources) {
		this.resources = resources;
	}

	/**
	 * Sets the rootNode.
	 * 
	 * @param rootNode
	 *            The rootNode to set
	 */
	public void setRootNode(RootNode rootNode) {
		this.rootNode = rootNode;
	}

	/**
	 * Sets the statements.
	 * 
	 * @param statements
	 *            The statements to set
	 */
	public void setStatements(HashMap<Statement, AbstractRdfTreeNode> statements) {
		this.statements = statements;
	}

	/**
	 * Sets the model.
	 * 
	 * @param model
	 *            The model to set
	 */
	public void setTopModel(Model model) {
		this.model = model;
	}

	/**
	 * Sets the tree model.
	 * 
	 * @param model
	 *            the new tree model
	 */
	public void setTreeModel(TreeModel model) {
		treeModel = model;
	}

}
