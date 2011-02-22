/**
 * 
 */
package org.hyperdata.scute.sparql;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

import org.hyperdata.scute.sparql.actions.AddEndpointAction;
import org.hyperdata.scute.sparql.actions.RunQueryAction;
import org.hyperdata.scute.sparql.actions.StopQueryAction;

/**
 * @author danny
 *
 */
public class SparqlToolbar extends JPanel {

	public SparqlToolbar(){
		super();
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

		JButton run = new JButton("Run Query");
		run.setAction(new RunQueryAction());
		add(run);
		add(Box.createHorizontalStrut(10));
		
		JButton stop = new JButton("Stop");
		stop.setAction(new StopQueryAction());
		add(stop);
		
		add(Box.createHorizontalStrut(10));
		add(new JSeparator(SwingConstants.VERTICAL));
		add(Box.createHorizontalStrut(20));

		
		add(new JLabel("Endpoint:"));
		add(Box.createHorizontalStrut(10));
		
		JComboBox comboBox = new JComboBox(new EndpointListModel());
		comboBox.setSelectedIndex(0);
		add(comboBox);
		
		JTextField uriField = new JTextField(20);
		add(uriField);
		
		add(Box.createHorizontalStrut(10));
		JButton add = new JButton("Add");
		add.setAction(new AddEndpointAction());
		add(add);
	}
}
