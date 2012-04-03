/**
 * 
 */
package org.hyperdata.scute.toolbars.file;

import java.awt.Frame;

import javax.swing.JOptionPane;

import org.hyperdata.scute.cards.Card;
import org.hyperdata.scute.cards.CardsPanel;
import org.hyperdata.scute.demos.ExploreEditDemo;
import org.hyperdata.scute.rdf.ModelContainer;
import org.hyperdata.scute.source.TextContainer;
import org.hyperdata.scute.system.panels.LogPane;

/**
 * @author danny
 * 
 */
public class IO implements FilesInterface {

	private Frame frame = null;

	private SaveDialog saveDialog = null;

	private OpenDialog openDialog = null;

	private ModelContainer modelContainer;

	private CardsPanel cardsPanel;

	public IO(ModelContainer modelContainer, CardsPanel cardsPanel) { // don't
																		// like
																		// that
																		// cardsPanel
		// this.frame = frame;
		this.modelContainer = modelContainer;
		this.cardsPanel = cardsPanel;
	}

	/**
	 * @param exploreEditDemo
	 */
	public IO(ExploreEditDemo exploreEditDemo) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void open() {
		if (modelContainer.getModelURI() == null
				&& modelContainer.getModelFilename() == null) { // never been
			// saved
			JOptionPane.showMessageDialog(frame,
					"Please save current Working Graph");
			saveAs();
		} else {
			save();
		}
		try {
			if (openDialog == null) {
				openDialog = new OpenDialog(frame);
			}
			openDialog.pack();
			openDialog.setVisible(true);
		} catch (final Exception exception) {
			System.out.println("Open aborted");
			return;
		}
		String filename = openDialog.getFilename();

		if (filename != null) {

			if (filename.endsWith(".rdf") || filename.endsWith(".xml")) {
				// probably RDF/XML
				cardsPanel.setCurrentCard("RDF/XML");
			} else {
				cardsPanel.setCurrentCard("Turtle");
			}
			// cardsPanel.listCards();
			TextContainer textContainer = cardsPanel.getCurrentCard()
					.getTextContainer();
			// will either be RDF/XML or Turtle
			textContainer.setFilename(filename);
			textContainer.load();
			// setModelFilename(filename);
			// loadModelFromFile();
			LogPane.println("Loaded file " + filename);
		} else {
			String uri = openDialog.getURI();
			if (uri != null) {
				modelContainer.setModelURI(uri);
				modelContainer.loadNamedModel();
				LogPane.println("Loaded model from " + uri);
			}
		}

		System.out.println("Loaded");

		// turtlePanel.loadModel(Models.workingModel);
		// rdfxmlPanel.loadModel(Models.workingModel);
		//
		// treePanel.loadModel(Models.workingModel);
		// treePanel.init();
		//
		// graphPanel.loadModel(Models.workingModel);
	}

	@Override
	public void saveAs() {

		try {
			if (saveDialog == null) {
				saveDialog = new SaveDialog(frame);
			}

			saveDialog.pack();
			saveDialog.setVisible(true);
		} catch (final Exception exception) {
			System.out.println("Export aborted");
			return;
		}
		String filename = saveDialog.getFilename();
		if (filename != null) {
			Card card = cardsPanel.getCurrentCard();
			if (card.isTextCard()) {
				TextContainer textContainer = card.getTextContainer();
				// will be RDF/XML, Turtle or SPARQL
				textContainer.setFilename(filename);
				textContainer.save();
			} else {
				modelContainer.setModelFilename(filename);
				modelContainer.saveModelToFile();
			}

		}
		String uri = saveDialog.getURI();
		if (uri != null) {
			modelContainer.setModelURI(uri);
			modelContainer.storeNamedModel();
		}
	}

	@Override
	public void save() {
		if (modelContainer.getModelURI() == null
				&& modelContainer.getModelFilename() == null) {
			saveAs();
			return;
		}

		if (modelContainer.getModelURI() != null) {
			modelContainer.storeNamedModel();
		}
		if (modelContainer.getModelFilename() != null) {
			modelContainer.saveModelToFile();
		}

		Card card = cardsPanel.getCurrentCard();
		if (card.isTextCard()) {
			if (card.getTextContainer().getFilename() != null) {
				card.getTextContainer().save();
			} else {
				saveAs();
				return;
			}
		}
	}

	@Override
	public void cloneFile() {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public void closeFile() {
		throw new RuntimeException("not yet implemented");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperdata.scute.swing.ToolsInterface#exit()
	 */
	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperdata.scute.swing.ToolsInterface#newModel()
	 */
	@Override
	public void newFile() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperdata.scute.swing.ToolsInterface#getFrame()
	 */
	@Override
	public Frame getFrame() {
		// TODO Auto-generated method stub
		return null;
	}
}
