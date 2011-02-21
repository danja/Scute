/**
 * 
 */
package org.hyperdata.scute.main;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;
import org.jdesktop.swingx.JXTitledPanel;
import org.jdesktop.swingx.action.AbstractActionExt;

import org.hyperdata.resources.scute.ScuteIcons;

public class TaskPanel extends JXTitledPanel {

	private JXTaskPaneContainer taskPaneContainer;
	private CardPanel cardPanel;
	private CardLayout layout;

	public TaskPanel(CardPanel cardPanel) {
		super("Activities");
		this.cardPanel = cardPanel;
		this.layout = (CardLayout) cardPanel.getLayout();
		taskPaneContainer = new JXTaskPaneContainer();
		this.setContentContainer(new JScrollPane(taskPaneContainer));
		addTaskPanes();
	}

	private void addTaskPanes() {
		addEditorTaskPane();
		addSparqlTaskPane();
	}

	@SuppressWarnings("deprecation")
	private void addEditorTaskPane() {
		JXTaskPane taskPane = new JXTaskPane();
		taskPane.setIcon(ScuteIcons.rdfIcon);
		taskPane.setTitle("RDF");
		taskPaneContainer.add(taskPane);

		taskPane.add(new ChangeEditorPanelAction("Turtle", "Turtle View"));
		taskPane.add(new ChangeEditorPanelAction("RDF/XML", "RDF/XML View"));
	}

	@SuppressWarnings("deprecation")
	private void addSparqlTaskPane() {
		JXTaskPane taskPane = new JXTaskPane();
		taskPane.setIcon(ScuteIcons.sparqlIcon);
		taskPane.setTitle("SPARQL");
		taskPaneContainer.add(taskPane);

		// taskPane.add(new SparqlAction());
	}

	private final class ChangeEditorPanelAction extends
			AbstractActionExt {
		private String label;

		public ChangeEditorPanelAction(String label, String panelName) {
			setName(panelName);
			this.label = label;
		}

		public void actionPerformed(ActionEvent actionEvent) {
        	System.out.println("action = "+actionEvent);
        	// CardLayout cLay = cardPanel.getLayout();
        	// cLay.show(cardPanel,"panel1Identifier");
        	layout.show(cardPanel, label);
        }
	}

}
