/**
 * 
 */
package org.hyperdata.scute.terminal;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import org.hdesktop.swingx.*;
import org.hdesktop.swingx.MultiSplitLayout.Leaf;
import org.hdesktop.swingx.MultiSplitLayout.*;

/**
 * @author danny
 *
 */
public class JEdwards {

	private Leaf middle;
	private Leaf topLeft;
	private Leaf bottomLeft;
	private Leaf topRight;
	private Leaf bottomRight;

	public JEdwards(){
		JXMultiSplitPane msp = initPanes();
		initTerminals(msp);
		
	    JFrame f = new JFrame("A JFrame");
	    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    f.setSize(500, 500);
	    f.setLocation(100,100);
	    f.getContentPane().add(msp);
	    f.setVisible(true);
	}
	
	public void initTerminals(JXMultiSplitPane msp){
		Terminal terminal1 = new Terminal();
		Terminal terminal2 = new Terminal();
		Terminal terminal3 = new Terminal();
		
//		terminal1.init();
//		terminal2.init();
//		terminal3.init();
		
		msp.add(new JButton("topLeft"), "topLeft");
		msp.add(terminal1.getConsole(), "bottomLeft");
		msp.add(terminal2.getConsole(), "topRight");
		msp.add(terminal3.getConsole(), "bottomRight");
		

		
//		terminal1.start();
//		terminal2.start();
//		terminal3.start();
	}
	
	  public static void main(String[] args) {
		    
new JEdwards();
		    
		  }
	  
	public JXMultiSplitPane initPanes(){
		Split outer = new Split();
		outer.setRowLayout(true);

		Split left = new Split();
		left.setRowLayout(false);
		left.setWeight(0.2);
		
		middle = new Leaf("middle");
		middle.setWeight(0.6);
		
		Split right = new Split();
		right.setRowLayout(false);
		right.setWeight(0.2);
		
		outer.setChildren(left, new Divider(), middle, new Divider(), right);
		
		topLeft = new Leaf("topLeft");
		bottomLeft = new Leaf("bottomLeft");
		topLeft.setWeight(0.5);
		bottomLeft.setWeight(0.5);
		
		topRight = new Leaf("topRight");
		bottomRight = new Leaf("bottomRight");
		topRight.setWeight(0.5);
		bottomRight.setWeight(0.5);
	
		left.setChildren(topLeft, new Divider(), bottomLeft);

		right.setChildren(topRight, new Divider(), bottomRight);
		
		// Once the layout is done, the code is easy
		JXMultiSplitPane msp = new JXMultiSplitPane();
		MultiSplitLayout layout = new MultiSplitLayout(outer);
		msp.setLayout(layout);

		msp.add(new JButton("middle"), "middle");
		return msp;
	}
}
