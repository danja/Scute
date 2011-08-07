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

import javax.swing.ImageIcon;
import javax.swing.tree.TreeNode;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;

/**
 * The Interface RdfTreeNode.
 */
public interface RdfTreeNode extends TreeNode {

	/** The Constant LITERAL. */
	public static final int LITERAL = 4;
	
	/** The Constant MODEL. */
	public static final int MODEL = 1;
	
	/** The Constant RESOURCE. */
	public static final int RESOURCE = 2;
	
	/** The Constant ROOT. */
	public static final int ROOT = 0;
	
	/** The Constant STATEMENT. */
	public static final int STATEMENT = 3;

	/**
	 * Delete.
	 */
	public void delete();

	/**
	 * Delete request.
	 */
	public void deleteRequest();

	/**
	 * Gets the icon.
	 * 
	 * @return the icon
	 */
	public ImageIcon getIcon();

	/**
	 * Gets the model.
	 * 
	 * @return the model
	 */
	public Model getModel();

	/**
	 * Gets the model filename.
	 * 
	 * @return the model filename
	 */
	public String getModelFilename();

	/**
	 * Gets the node map.
	 * 
	 * @return the node map
	 */
	public RdfNodeMap getNodeMap();

	/**
	 * Gets the node type.
	 * 
	 * @return the node type
	 */
	public int getNodeType();

	/**
	 * Gets the node type name.
	 * 
	 * @return the node type name
	 */
	public String getNodeTypeName();

	/**
	 * Gets the parent statement.
	 * 
	 * @return the parent statement
	 */
	public Statement getParentStatement();

	/**
	 * Gets the rdf node.
	 * 
	 * @return the rdf node
	 */
	public RDFNode getRdfNode();

	/**
	 * Gets the rdf value.
	 * 
	 * @return the rdf value
	 */
	public String getRdfValue();

	/**
	 * Checks if is dirty.
	 * 
	 * @return true, if is dirty
	 */
	public boolean isDirty();

	/**
	 * Checks if is value editable.
	 * 
	 * @return true, if is value editable
	 */
	public boolean isValueEditable();

	/**
	 * Rename.
	 * 
	 * @param newName
	 *            the new name
	 */
	public void rename(String newName);

	/**
	 * Rename request.
	 */
	public void renameRequest();

	/**
	 * Sets the dirty.
	 * 
	 * @param dirty
	 *            the new dirty
	 */
	public void setDirty(boolean dirty);

	/**
	 * Sets the model.
	 * 
	 * @param model
	 *            the new model
	 */
	public void setModel(Model model);

	/**
	 * Sets the model filename.
	 * 
	 * @param filename
	 *            the new model filename
	 */
	public void setModelFilename(String filename);

	/**
	 * Sets the rdf value.
	 * 
	 * @param string
	 *            the new rdf value
	 */
	public void setRdfValue(String string);

}
