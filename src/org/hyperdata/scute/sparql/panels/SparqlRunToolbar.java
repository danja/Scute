/**
 * 
 */
package org.hyperdata.scute.sparql.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import org.hyperdata.resources.scute.ScuteIcons;
import org.hyperdata.scute.sparql.SparqlContainer;
import org.hyperdata.scute.sparql.actions.RunQueryAction;
import org.hyperdata.scute.sparql.endpoints.EditEndpointsAction;
import org.hyperdata.scute.sparql.endpoints.Endpoint;
import org.hyperdata.scute.sparql.endpoints.EndpointListModel;
import org.hyperdata.scute.sparql.endpoints.EndpointTableModel;
import org.hyperdata.scute.status.StatusAction;
import org.hyperdata.scute.status.StatusButton;
import org.hyperdata.scute.status.StatusChangeListener;
import org.hyperdata.scute.status.StatusEvent;
import org.hyperdata.scute.status.StatusMonitor;
import org.hyperdata.scute.validate.SparqlValidateAction;
import org.hyperdata.scute.validate.Validator;

/**
 * @author danny
 * 
 * TODO refactor messy passing of stuff to RunQueryAction
 */
public class SparqlRunToolbar extends JPanel implements ActionListener {

	private JComboBox endpointsBox;
	private JTextField uriField;
	private SparqlContainer queryContainer;
	private SparqlSourcePanel sourcePanel;
	private Frame frame;
	private Validator validator;
	private StatusAction runQueryAction;


	public SparqlRunToolbar(SparqlContainer queryContainer,
			SparqlSourcePanel sourcePanel, Validator validator, Frame frame) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		this.frame = frame;
		this.validator = validator;
		this.queryContainer = queryContainer;
		this.sourcePanel = sourcePanel;

//		//  a bit messy, lots of backrefs, but will do for now
		
		runQueryAction = new RunQueryAction(this);
		// frame, "Run", queryContainer, sourcePanel, validator
		
		// runQueryAction.addStatusChangeListener(this); **************************************************
		
		String[] labels = { "Error", "Stop", "Run" };
		String[] descriptions = { "Error, check log", "Running...", "Run" };
		ImageIcon[] icons = { ScuteIcons.errorIcon, ScuteIcons.stopIcon,
				ScuteIcons.sparqlIcon };
		
		StatusButton runQueryButton = new StatusButton(runQueryAction, labels, descriptions, icons);
		runQueryButton.setStatus(StatusMonitor.GREEN);
		runQueryButton.setHorizontalTextPosition(SwingConstants.LEFT);
		runQueryButton.setHorizontalAlignment(SwingConstants.RIGHT); // doesn't appear to work!
		runQueryButton.setToolTipText("Run query");

		EndpointListModel endpointListModel = new EndpointListModel();
		endpointsBox = new JComboBox(endpointListModel);
		endpointsBox.setSelectedIndex(0);
		endpointsBox.addActionListener(this);
		endpointsBox.setToolTipText("Choose endpoint");
		
		uriField = new JTextField(20);
		uriField.setText("----");
		uriField.setToolTipText("Endpoint URI");

		JButton editButton = new JButton();
		EndpointTableModel endpointTableModel = new EndpointTableModel(endpointListModel);
		editButton.setAction(new EditEndpointsAction("Endpoint:", endpointTableModel, frame));
		editButton.setToolTipText("Add to/remove from  list");
		

		
		add(runQueryButton);
		// add(Box.createHorizontalStrut(5));
		add(new JSeparator(SwingConstants.VERTICAL));
		add(Box.createHorizontalStrut(5));
		//	add(new JLabel("Endpoint:"));
		add(editButton);
		add(Box.createHorizontalStrut(5));
		add(endpointsBox);
		add(uriField);
	}
	
	public void setCursor(Cursor cursor){
		super.setCursor(cursor);
		sourcePanel.setCursor(cursor);
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
		Endpoint endpoint = (Endpoint) endpointsBox.getSelectedItem();
		String uri = endpoint.getUri();
		if (uri != null) {
			uriField.setText(uri);
		} else {
			uriField.setText("---");
		}
		// System.out.println("ENDPOINT ST=" + endpoint);
		queryContainer.setEndpoint(endpoint);
	}

	/**
	 * @return
	 */
	public Validator getValidator() {
		return validator;
	}

	/**
	 * @return
	 */
	public Frame getFrame() {
		return frame;
	}

	/**
	 * 
	 */
	public void initSparqlContainer() {
		queryContainer.setQueryString(sourcePanel.getText());
		Endpoint endpoint = (Endpoint) endpointsBox.getSelectedItem();
		queryContainer.setEndpoint(endpoint);
	}

	/**
	 * @return
	 */
	public SparqlContainer getSparqlContainer() {
		return queryContainer;
	}

	/**
	 * @param sparqlCard
	 */
	public void addStatusChangeListener(StatusChangeListener listener) {
		runQueryAction.addStatusChangeListener(listener);
	}


}
