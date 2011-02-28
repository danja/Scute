
package org.hyperdata.scute.graphmanager;

import javax.swing.JLabel;
import org.hyperdata.scute.cards.Card;

/**
 * @author danny
 * 
 * TDB provides a Multiple Reader or Single Writer (MRSW) policy for concurrency access. 
 * 
 * Applications are expected to adhere to this policy - it is not automatically checked.

One gotcha is Java iterators. An iterator that is moving over the database is making read operations and no 
updates to the dataset are possible while an iterator is being used. 

http://openjena.org/wiki/TDB/JavaAPI

 */
public class GraphManagerPanel extends Card {

	
	public GraphManagerPanel(){
		add(new JLabel("Graph Manager - TODO"));
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
