/**
 * 
 */
package org.hyperdata.scute.cards;

import java.awt.BorderLayout;

import org.hyperdata.scute.filemanager.FileExplorerCard;
import org.hyperdata.scute.graph.GraphCard;
import org.hyperdata.scute.graphmanager.GraphManagerCard;
import org.hyperdata.scute.main.Config;
import org.hyperdata.scute.main.ImageCard;
import org.hyperdata.scute.rdf.Models;
import org.hyperdata.scute.sparql.panels.SparqlCard;
import org.hyperdata.scute.system.panels.SystemCard;
import org.hyperdata.scute.tree.RdfTreeCard;
import org.hyperdata.scute.triples.TriplesCard;

/**
 * @author danny
 * 
 */
public class CardFactory {

	private CardFactory() {
	}

	public static Card createCard(int type) { 
		switch (type) {
		case Card.DEFAULT:
			return createDefaultCard();
		case Card.TURTLE: 
			return createTurtleCard();
		case Card.RDFXML:
			return createRDFXMLCard();
		case Card.TRIPLES:
			return createTriplesCard();
		case Card.GRAPH:
			return createGraphCard();
		case Card.TREE:
			return createTreeCard();
		case Card.SPARQL:
			return createSPARQLCard();
		case Card.FILE_EXPLORER:
			return createFileExplorerCard();
		case Card.GRAPH_MANAGER:
			return createGraphManagerCard();
		case Card.IMAGE:
			return createImageCard();
		case Card.SETTINGS:
			return createSettingsCard();
		default:
			return null;
		}
	}

	/**
	 * @return
	 */
	private static Card createSPARQLCard() {
		return new SparqlCard();
	}

	/**
	 * @return
	 */
	private static Card createTriplesCard() {
		return new TriplesCard(Models.workingModel);
	}

	/**
	 * @return
	 */
	private static Card createSettingsCard() {
		return new SystemCard();
	}

	/**
	 * @return
	 */
	private static Card createImageCard() {
		// TODO Auto-generated method stub
		return new ImageCard();
	}

	/**
	 * @return
	 */
	private static Card createGraphManagerCard() {
		return new GraphManagerCard();
	}

	/**
	 * @return
	 */
	private static Card createTreeCard() {
		return new RdfTreeCard(Models.workingModel);
	}

	/**
	 * @return
	 */
	private static Card createGraphCard() {
		return new GraphCard(Models.workingModel);
	}

	/**
	 * @return
	 */ 
	private static Card createFileExplorerCard() {
		return new FileExplorerCard(Config.DATA_DIR);
	}

	/**
	 * @return
	 */
	private static Card createDefaultCard() {
		return new Card();
	}

	/**
	 * @return
	 */
	private static Card createRDFXMLCard() {
		return new Card(new BorderLayout());
	}

	/**
	 * @return
	 */
	private static Card createTurtleCard() {
		return new Card(new BorderLayout());
	}
}