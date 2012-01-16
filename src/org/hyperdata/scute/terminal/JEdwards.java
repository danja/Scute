/**
 * 
 */
package org.hyperdata.scute.terminal;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import org.hdesktop.swingx.*;
import org.hdesktop.swingx.MultiSplitLayout.*;

/**
 * @author danny
 *
 */
public class JEdwards {

	public JEdwards(){
		JPanel panel = initPanes();
		
	    JFrame f = new JFrame("A JFrame");
	    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    f.setSize(500, 500);
	    f.setLocation(100,100);
	    f.getContentPane().add(panel);
	    f.setVisible(true);
	}
	
	  public static void main(String[] args) {
		    
new JEdwards();
		    
		  }
	  
	public JPanel initPanes(){
		Split outer = new Split();
		outer.setRowLayout(true);

		Split left = new Split();
		left.setRowLayout(false);
		left.setWeight(0.2);
		
		Leaf middle = new Leaf("middle");
		middle.setWeight(0.6);
		
		Split right = new Split();
		right.setRowLayout(false);
		right.setWeight(0.2);
		
		outer.setChildren(left, new Divider(), middle, new Divider(), right);
		
		Leaf topLeft = new Leaf("topLeft");
		Leaf bottomLeft = new Leaf("bottomLeft");
		topLeft.setWeight(0.5);
		bottomLeft.setWeight(0.5);
		
		Leaf topRight = new Leaf("topRight");
		Leaf bottomRight = new Leaf("bottomRight");
		topRight.setWeight(0.5);
		bottomRight.setWeight(0.5);
	
		left.setChildren(topLeft, new Divider(), bottomLeft);

		right.setChildren(topRight, new Divider(), bottomRight);
		
		// Once the layout is done, the code is easy
		JXMultiSplitPane msp = new JXMultiSplitPane();
		MultiSplitLayout layout = new MultiSplitLayout(outer);
		msp.setLayout(layout);
		msp.add(new JButton("topLeft"), "topLeft");
		msp.add(new JButton("bottomLeft"), "bottomLeft");
		msp.add(new JButton("topRight"), "topRight");
		msp.add(new JButton("bottomRight"), "bottomRight");
		msp.add(new JButton("middle"), "middle");
		return msp;
	}
}
