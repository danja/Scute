/**
 * 
 */
package org.hyperdata.scute.swing;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

import java.beans.*; //property change stuff
import java.io.File;
import java.awt.*;
import java.awt.event.*;

/**
 * @author danny
 * 
 */
public class SaveDialog extends JDialog implements ActionListener,
		PropertyChangeListener {
	private String typedText = null;
	private JTextField uriTextField;
	private JTextField filenameTextField;

	private String magicWord;
	private JOptionPane optionPane;

	private String okButtonLabel = "OK";
	private String cancelButtonLabel = "Cancel";
	private Frame frame;

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		SaveDialog saveDialog = new SaveDialog(frame);
		// saveDialog.setSize(400,200);
		saveDialog.pack();
		saveDialog.setVisible(true);
		System.out.println(saveDialog.getValidatedText());
	}

	/**
	 * Returns null if the typed string was invalid; otherwise, returns the
	 * string as the user entered it.
	 */
	public String getValidatedText() {
		return typedText;
	}

	/** Creates the reusable dialog. */
	public SaveDialog(final Frame frame) {
		super(frame, true);
		this.frame = frame;
		// magicWord = aWord.toUpperCase();
		setTitle("Save");

		JPanel filenamePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

		filenameTextField = new JTextField(30);

		JCheckBox fileCheckBox = new JCheckBox(" Save File      "); // alignment
																	// set with
																	// spaces -
																	// hacky!
		fileCheckBox.setSelected(true);
		// fileCheckBox.setHorizontalTextPosition(JCheckBox.LEADING);

		JButton fileButton = new JButton("Select Directory");
		Dimension buttonDimension = fileButton.getPreferredSize();
		final JFileChooser fc = new JFileChooser();

		filenamePanel.add(fileCheckBox);
		filenamePanel.add(filenameTextField);
		filenamePanel.add(fileButton);

		JPanel uriPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

		uriTextField = new JTextField(30);
		JCheckBox uriCheckBox = new JCheckBox(" Store Graph");
		// uriCheckBox.setHorizontalTextPosition(JCheckBox.LEADING);
		JButton uriButton = new JButton("Select Graph");
		uriButton.setPreferredSize(buttonDimension);
		uriPanel.add(uriCheckBox);
		uriPanel.add(uriTextField);
		uriPanel.add(uriButton);

		JPanel defaultGraphPanel = new JPanel(
				new FlowLayout(FlowLayout.LEADING));
		JCheckBox defaultGraphCheckBox = new JCheckBox(
				" Merge into Default Graph");
		defaultGraphPanel.add(defaultGraphCheckBox);

		Object[] array = { "Choose location(s):", filenamePanel, uriPanel,
				defaultGraphPanel };

		// Create an array specifying the number of dialog buttons and their
		// text.
		Object[] options = { okButtonLabel, cancelButtonLabel };

		// Create the JOptionPane.
		optionPane = new JOptionPane(array, JOptionPane.QUESTION_MESSAGE,
				JOptionPane.YES_NO_OPTION, null, options, options[0]);

		// Make this dialog display it.
		setContentPane(optionPane);

		// Handle window closing correctly.
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				/*
				 * Instead of directly closing the window, we're going to change
				 * the JOptionPane's value property.
				 */
				optionPane.setValue(new Integer(JOptionPane.CLOSED_OPTION));
			}
		});

		// Ensure the text field always gets the first focus.
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent ce) {
				uriTextField.requestFocusInWindow();
			}
		});

		// Register an event handler that puts the text into the option pane.
		uriTextField.addActionListener(this);

		// Register an event handler that reacts to option pane state changes.
		optionPane.addPropertyChangeListener(this);

		fileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = fc.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					filenameTextField.setText(file.getAbsolutePath());
					// log.append("Opening: " + file.getName() + "." + newline);
				} else {
					// log.append("Open command cancelled by user." + newline);
				}

			}

		});
	}

	/** This method handles events for the text field. */
	public void actionPerformed(ActionEvent e) {
		optionPane.setValue(okButtonLabel);
	}

	/**
	 * This method reacts to state changes in the option pane.
	 * 
	 * @param e
	 *            the e
	 */
	public void propertyChange(PropertyChangeEvent e) {
		String prop = e.getPropertyName();

		if (isVisible()
				&& (e.getSource() == optionPane)
				&& (JOptionPane.VALUE_PROPERTY.equals(prop) || JOptionPane.INPUT_VALUE_PROPERTY
						.equals(prop))) {
			Object value = optionPane.getValue();

			if (value == JOptionPane.UNINITIALIZED_VALUE) {
				// ignore reset
				return;
			}

			// Reset the JOptionPane's value.
			// If you don't do this, then if the user
			// presses the same button next time, no
			// property change event will be fired.
			optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);

			if (okButtonLabel.equals(value)) {
				typedText = uriTextField.getText();
				// check text
				if (true) {
					clearAndHide();
				} else {
					// text was invalid
					uriTextField.selectAll();
					JOptionPane.showMessageDialog(SaveDialog.this, typedText
							+ "isn't a suitable URI.", "Try again...",
							JOptionPane.ERROR_MESSAGE, null);

					typedText = null;
					uriTextField.requestFocusInWindow();
				}
			} else { // user closed dialog or clicked cancel
				typedText = null;
				clearAndHide();
			}
		}
	}

	/** This method clears the dialog and hides it. */
	public void clearAndHide() {
		uriTextField.setText(null);
		setVisible(false);
	}
}
