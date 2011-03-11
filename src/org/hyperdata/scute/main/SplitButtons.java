/**
 * 
 */
package org.hyperdata.scute.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXMultiSplitPane;
import org.jdesktop.swingx.MultiSplitLayout.Leaf;

import org.hyperdata.scute.system.Log;

/**
 * @author danny
 * 
 */
public class SplitButtons implements ActionListener {

	private JButton leftButton;
	private JButton rightButton;
	private Leaf leftLeaf;
	private Leaf rightLeaf;
	private Leaf centerLeaf;
	private double leftWeight = 0;
	private double centerWeight = 1;
	private double rightWeight = 0;
	private JXMultiSplitPane multiSplitPane;

	public JButton getLeftButton() {
		return this.leftButton;
	}

	public JButton getRightButton() {
		return this.rightButton;
	}

	/**
	 * @param multiSplitPane
	 * @param leftLeaf
	 * @param centerLeaf
	 * @param rightLeaf
	 */
	public SplitButtons(JXMultiSplitPane multiSplitPane, Leaf leftLeaf,
			Leaf centerLeaf, Leaf rightLeaf) {
		this.multiSplitPane = multiSplitPane;

		this.leftLeaf = leftLeaf;
		this.rightLeaf = rightLeaf;
		this.centerLeaf = centerLeaf;

		leftButton = new JButton(">");
		leftButton.addActionListener(this);
		rightButton = new JButton("<");
		rightButton.addActionListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		JButton source = (JButton) event.getSource();
		rightWeight = rightLeaf.getWeight();
		leftWeight = leftLeaf.getWeight();
		if (source.equals(leftButton)) {
			flipLeft();
		}
		if (source.equals(rightButton)) {
			flipRight();
		}
		System.out.println(source);
		if (source.getText().equals("")) {
			setDefaults();
		}
	}

	/**
	 * 
	 */
	private void flipLeft() {
		System.out.println("flip left");
		if (leftWeight >= 0.2) {
			leftWeight = 0;
			centerWeight = 1 - rightWeight;
			leftButton.setText(">");
			setWeights();
		} else {
			leftWeight = 0.2;
			centerWeight = 0.8 - rightWeight;
			leftButton.setText("<");
			setWeights();
		}
	}

	private void flipRight() {
		if (rightWeight >= 0.2) {
			rightWeight = 0;
			centerWeight = 1 - leftWeight;
			rightButton.setText(">");
			setWeights();
		} else {
			rightWeight = 0.2;
			centerWeight = 0.8 - leftWeight;
			rightButton.setText("<");
			setWeights();
		}
	}

	/**
	 * 
	 */
	private void setWeights() {

		leftLeaf.setWeight(leftWeight);
		centerLeaf.setWeight(centerWeight);
		rightLeaf.setWeight(rightWeight);

		System.out.println(leftWeight + " " + centerWeight + " " + rightWeight);
		multiSplitPane.validate();
		// multiSplitPane.getParent().repaint();
	}

	public void setDefaults() {
		leftButton.setText("<");
		rightButton.setText(">");
		leftWeight = 0.2;
		centerWeight = 0.6;
		rightWeight = 0.2;
		setWeights();
	}
	
	public void setBigCenter() {
		leftWeight = 0;
		centerWeight = 1;
		rightWeight = 0;
		setWeights();
	}
}
