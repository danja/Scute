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

package org.hyperdata.scute.triples;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.hp.hpl.jena.rdf.model.Model;

import org.hdesktop.swingx.JXTable;

import org.hyperdata.scute.cards.Card;

/**
 * The Class TriplesPanel.
 *
 * @author danja
 * 
 * A simple 3-column table display of statements in the Model
 * 
 * FIXME implement
 */
public class TriplesCard extends Card  {

	JXTable table; //  = new JXTable();
	private Model model;
	
	/**
	 * @param model
	 */
	public TriplesCard(Model model) {
		this.model = model;
		loadModel();
		add(new JScrollPane(table));
		addFilterPane();
	}

	/**
	 * 
	 */
	private void addFilterPane() {
		JPanel filterPanel = new JPanel();
		
	}

	/**
	 * 
	 */
	private void loadModel() {
		TableModel tableModel = new TripleTableModel(model);
		table = new JXTable(tableModel);
	}
}
