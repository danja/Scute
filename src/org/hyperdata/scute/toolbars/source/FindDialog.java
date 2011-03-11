package org.hyperdata.scute.toolbars.source;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.StyledDocument;

import org.hyperdata.scute.system.Log;
import org.hyperdata.scute.toolbars.actions.FindAction;


public class FindDialog extends JDialog {

	private JEditorPane editorPane;
	private int lastMatchPos = -1;
	private boolean replaced = false;

	private JButton closeButton;
	private JButton findButton;
	private JLabel findLabel;
	private JTextField findTextField;
	private JCheckBox caseCheckBox;
	private JButton replaceAllButton;
	private JButton replaceButton;
	private JLabel replaceLabel;
	private JTextField replaceTextField;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JEditorPane pane = new JEditorPane();
		JButton button = new JButton(new FindAction(frame, pane));
		frame.getContentPane().add(button);
		// new FindDialog(frame, true, pane);
		frame.pack();
		frame.setVisible(true);
	}

	public FindDialog(Frame frame, JEditorPane editorPane) {
		super(frame, false); // not modal
		this.editorPane = editorPane;
	// 	init();

		setLocationRelativeTo(frame);
		// editorPane.select(0, 0);
	}

	public void init() {
		setTitle("Find");
		editorPane.select(0, 0);
		// new BoxLayout(this, GridLayout.Y_AXIS);
		setLayout(new GridLayout(4, 1));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		JPanel findPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		findLabel = new JLabel("Find:");
		findPanel.add(findLabel);
		findTextField = new JTextField(20);
		findTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				if (findTextField.getText().length() > 0) {
					findButton.setEnabled(true);
					replaceButton.setEnabled(true);
					replaceAllButton.setEnabled(true);
				} else {
					findButton.setEnabled(false);
					replaceButton.setEnabled(false);
					replaceAllButton.setEnabled(false);
				}
			}
		});
		findPanel.add(findTextField);
		add(findPanel);

		JPanel replacePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		replaceLabel = new JLabel("Replace with:");
		replacePanel.add(replaceLabel);
		replaceTextField = new JTextField(20);
		replacePanel.add(replaceTextField);
		add(replacePanel);

		JPanel matchPanel = new JPanel();
		caseCheckBox = new JCheckBox("Match Case");
		matchPanel.add(caseCheckBox);
		add(matchPanel);

		JPanel buttons = new JPanel();
		findButton = new JButton("Find");
		findButton.setEnabled(false);
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				find();
			}
		});
		buttons.add(findButton);

		replaceButton = new JButton("Replace");
		replaceButton.setEnabled(false);
		replaceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				replace(evt);
			}
		});
		buttons.add(replaceButton);

		replaceAllButton = new JButton("Replace All");
		replaceAllButton.setEnabled(false);
		replaceAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				replaceAll(evt);
			}
		});
		buttons.add(replaceAllButton);

		closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setVisible(false);
				dispose();
			}
		});
		buttons.add(closeButton);
		add(buttons);
		pack();
	}

	private void find() {
		if (!findNext()) {
			JOptionPane.showMessageDialog(this, "Text not found.");
		}
	}

	private boolean findNext() {
		try {
			boolean matchCase = caseCheckBox.isSelected();
			String findWhat = findTextField.getText();
			StyledDocument doc = (StyledDocument) editorPane.getDocument();
			String htmlText = doc.getText(0, doc.getLength());
			if (matchCase) {
				lastMatchPos = htmlText.indexOf(findWhat, lastMatchPos + 1);
			} else {
				lastMatchPos = htmlText.toUpperCase().indexOf(
						findWhat.toUpperCase(), lastMatchPos + 1);
			}

			if (lastMatchPos != -1) {
				editorPane.setCaretPosition(lastMatchPos + findWhat.length());
				editorPane.requestFocus();
				editorPane.select(lastMatchPos,
						lastMatchPos + findWhat.length());
			}

			return lastMatchPos != -1;
		} catch (BadLocationException exception) {
			Log.exception(exception);
		}
		return false;
	}

	private void replaceSelection() {
		try {
			StyledDocument doc = (StyledDocument) editorPane.getDocument();
			String find = findTextField.getText();
			String replacement = replaceTextField.getText();
			AttributeSet attributeSet = null;
			Element element = doc.getCharacterElement(lastMatchPos);
			attributeSet = element.getAttributes();
			doc.remove(lastMatchPos, find.length());
			doc.insertString(lastMatchPos, replacement, attributeSet);
			editorPane.setCaretPosition(lastMatchPos + replacement.length());
			editorPane.requestFocus();
			editorPane
					.select(lastMatchPos, lastMatchPos + replacement.length());
		} catch (BadLocationException exception) {
			Log.exception(exception);
		}
	}

	private void replace(ActionEvent evt) {
		String selectedText = editorPane.getSelectedText();
		if (replaced || selectedText == null || selectedText.length() == 0) {
			replaced = false;
			find();
			return;
		} else if (lastMatchPos > -1) {
			replaceSelection();
			replaced = true;
		} else {
			JOptionPane.showMessageDialog(this, "Text not found.");
		}
	}

	private void replaceAll(ActionEvent evt) {
		int count = 0;
		while (findNext()) {
			replaceSelection();
			count++;
		}
		JOptionPane.showMessageDialog(this, count + " replacements.");
	}
}
