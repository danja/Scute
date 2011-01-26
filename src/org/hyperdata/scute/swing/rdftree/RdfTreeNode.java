package org.hyperdata.scute.swing.rdftree;

import javax.swing.ImageIcon;
import javax.swing.tree.TreeNode;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;

public interface RdfTreeNode extends TreeNode {

	public static final int LITERAL = 4;
	public static final int MODEL = 1;
	public static final int RESOURCE = 2;
	public static final int ROOT = 0;
	public static final int STATEMENT = 3;

	public void delete();

	public void deleteRequest();

	public ImageIcon getIcon();

	public Model getModel();

	public String getModelFilename();

	public RdfNodeMap getNodeMap();

	public int getNodeType();

	public String getNodeTypeName();

	public Statement getParentStatement();

	public RDFNode getRdfNode();

	public String getRdfValue();

	public boolean isDirty();

	public boolean isValueEditable();

	public void rename(String newName);

	public void renameRequest();

	public void setDirty(boolean dirty);

	public void setModel(Model model);

	public void setModelFilename(String filename);

	public void setRdfValue(String string);

}
