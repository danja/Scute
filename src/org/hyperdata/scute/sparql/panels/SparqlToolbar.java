/**
 * 
 */
package org.hyperdata.scute.sparql.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.hyperdata.scute.sparql.Endpoint;
import org.hyperdata.scute.sparql.EndpointListModel;
import org.hyperdata.scute.sparql.SparqlContainer;
import org.hyperdata.scute.sparql.actions.AddEndpointAction;
import org.hyperdata.scute.sparql.actions.RunQueryAction;
import org.hyperdata.scute.sparql.actions.StopQueryAction;

/**
 * @author danny
 * 
 */
public class SparqlToolbar extends JPanel implements ActionListener {

	private JComboBox comboBox;
	private JTextField uriField;
	private SparqlContainer queryContainer;
	private SparqlSourcePanel sourcePanel;

	public SparqlToolbar(SparqlContainer queryContainer, SparqlSourcePanel sourcePanel) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		this.queryContainer = queryContainer;
this.sourcePanel = sourcePanel;
		JButton run = new JButton();
		
		// using sourcepanel here a bit messy, but will do for now
		run.setAction(new RunQueryAction("Run Query", queryContainer, sourcePanel));
		add(run);
		add(Box.createHorizontalStrut(10));

		JButton stop = new JButton();
		stop.setAction(new StopQueryAction("Stop"));
		add(stop);

		add(Box.createHorizontalStrut(10));
		add(new JSeparator(SwingConstants.VERTICAL));
		add(Box.createHorizontalStrut(20));

		add(new JLabel("Endpoint:"));
		add(Box.createHorizontalStrut(10));

		comboBox = new JComboBox(new EndpointListModel());
		comboBox.setSelectedIndex(0);
		comboBox.addActionListener(this);
		add(comboBox);

		uriField = new JTextField(20);
		uriField.setText("----");
		add(uriField);

		add(Box.createHorizontalStrut(10));
		JButton add = new JButton();
		add.setAction(new AddEndpointAction("Add"));
		add(add);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// String selected = (String)comboBox.getSelectedItem();
		Endpoint endpoint = (Endpoint) comboBox.getSelectedItem();
		String uri = endpoint.getUri();
		if (uri != null) {
			uriField.setText(uri);
		} else {
			uriField.setText("---");
		}
		System.out.println("ENDPOINT ST="+endpoint);
		queryContainer.setEndpoint(endpoint);
	}
}
