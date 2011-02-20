/**
 * 
 */
package org.hyperdata.scute.main;

import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;
import org.jdesktop.swingx.JXTitledPanel;
import org.jdesktop.swingx.action.AbstractActionExt;

import org.hyperdata.resources.scute.ScuteIcons;


public class TaskPanel extends JXTitledPanel {
	
	private JXTaskPaneContainer taskPaneContainer;

	public TaskPanel(){
		super("Activities");
		taskPaneContainer = new JXTaskPaneContainer();
		this.setContentContainer(new JScrollPane(taskPaneContainer));
		addTaskPanes();
	}
	
	private void addTaskPanes() {
		addEditorTaskPane();
		addSparqlTaskPane();
	}
	
	private void addEditorTaskPane() {
		JXTaskPane taskPane = new JXTaskPane();
        taskPane.setIcon(ScuteIcons.rdfIcon);
		taskPane.setTitle("RDF");
		taskPaneContainer.add(taskPane);
		
		taskPane.add(new SomeAction("Some Action"));
	}
	
	private void addSparqlTaskPane() {
		JXTaskPane taskPane = new JXTaskPane();
        taskPane.setIcon(ScuteIcons.sparqlIcon);
		taskPane.setTitle("SPARQL");
		taskPaneContainer.add(taskPane);
		
	//	taskPane.add(new SparqlAction());
	}
	
    private static final class SomeAction extends AbstractActionExt {
        public SomeAction(String name) {
            setName(name);
        }
        public void actionPerformed(ActionEvent actionEvent) {}
    }
	
}
