/**
 * 
 */
package org.hyperdata.scute.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import org.hdesktop.swingx.JXMultiSplitPane;
import org.hdesktop.swingx.MultiSplitLayout.Leaf;

import org.hyperdata.scute.system.Log;

/**
 * @author danny
 * 
 */
public class SplitScreen implements ActionListener {
	
	private static final double LEFT_DEFAULT_WEIGHT = 0.2;
	private static final double MIDDLE_DEFAULT_WEIGHT = 0.5;
	private static final double RIGHT_DEFAULT_WEIGHT = 0.3;
	
	private JButton leftButton;
	private JButton rightButton;
	private Leaf leftLeaf;
	private Leaf rightLeaf;
	private Leaf middleLeaf;
	
	private double leftWeight = 0;
	private double middleWeight = 1;
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
	public SplitScreen(JXMultiSplitPane multiSplitPane, Leaf leftLeaf,
			Leaf centerLeaf, Leaf rightLeaf) {
		this.multiSplitPane = multiSplitPane;

		this.leftLeaf = leftLeaf;
		this.rightLeaf = rightLeaf;
		this.middleLeaf = centerLeaf;

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
		// System.out.println(source);
		if (source.getText().equals("")) {
			setDefaults();
		}
	}

	/**
	 * 
	 */
	private void flipLeft() {
		// System.out.println("flip left");
		if (leftWeight >= LEFT_DEFAULT_WEIGHT) {
			leftWeight = 0;
			middleWeight = 1 - rightWeight;
			leftButton.setText(">");
			setWeights();
		} else {
			leftWeight = LEFT_DEFAULT_WEIGHT;
			middleWeight = MIDDLE_DEFAULT_WEIGHT - rightWeight;
			leftButton.setText("<");
			setWeights();
		}
	}

	private void flipRight() {
		if (rightWeight >= RIGHT_DEFAULT_WEIGHT) {
			rightWeight = 0;
			middleWeight = 1 - leftWeight;
			rightButton.setText(">");
			setWeights();
		} else {
			rightWeight = RIGHT_DEFAULT_WEIGHT;
			middleWeight = MIDDLE_DEFAULT_WEIGHT - leftWeight;
			rightButton.setText("<");
			setWeights();
		}
	}

	/**
	 * 
	 */
	private void setWeights() {
		leftLeaf.setWeight(leftWeight);
		middleLeaf.setWeight(middleWeight);
		rightLeaf.setWeight(rightWeight);

		// System.out.println(leftWeight + " " + middleWeight + " " + rightWeight);
		multiSplitPane.validate();
		// multiSplitPane.getParent().repaint();
	}

	public void setDefaults() {
		leftButton.setText("<");
		rightButton.setText(">");
		leftWeight = LEFT_DEFAULT_WEIGHT;
		middleWeight = MIDDLE_DEFAULT_WEIGHT;
		rightWeight = RIGHT_DEFAULT_WEIGHT;
		setWeights();
	}
	
	public void setFullMiddle() {
		leftWeight = 0;
		middleWeight = 1;
		rightWeight = 0;
		setWeights();
	}
}
