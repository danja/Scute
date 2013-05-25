/**
 * 
 */
package org.hyperdata.scute.demos;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;

import org.jdesktop.swingx.JXMultiSplitPane;
import org.jdesktop.swingx.MultiSplitLayout;
import org.jdesktop.swingx.MultiSplitLayout.Divider;
import org.jdesktop.swingx.MultiSplitLayout.Leaf;
import org.jdesktop.swingx.MultiSplitLayout.Split;

import org.hyperdata.scute.demos.temp.FileTreeSelectionListener;
import org.hyperdata.scute.demos.temp.SourceEditor;
import org.hyperdata.scute.filemanager.FileExplorerCard;
import org.hyperdata.scute.terminal.Terminal;

//import org.hdesktop.swingx.*;
//import org.hdesktop.swingx.MultiSplitLayout.Leaf;
//import org.hdesktop.swingx.MultiSplitLayout.*;

/**
 * @author danny
 * 
 */
public class JEdwards {

	private SourceEditor editorPane;
	private FileExplorerCard fileExplorerPane;
	private JScrollPane editorScrollPane;

	private Leaf middle;
	private Leaf topLeft;
	private Leaf bottomLeft;
	private Leaf topRight;
	private Leaf bottomRight;

	public JEdwards() {
		JXMultiSplitPane msp = initPanes();
		initTerminals(msp);
		initFileExplorer();
		initEditor();

		msp.add(fileExplorerPane, "topLeft");
		msp.add(editorPane.getScrollPane(), "middle");

		FileTreeSelectionListener treeMouseListener = new FileTreeSelectionListener();
		treeMouseListener.attach(fileExplorerPane.getTree(), editorPane);

		JFrame f = new JFrame("JEdwards");
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setSize(500, 500);
		f.setLocation(100, 100);
		f.getContentPane().add(msp);
		f.setVisible(true);
	}

	private void initEditor() {
		editorPane = new SourceEditor();
		jsyntaxpane.DefaultSyntaxKit.initKit();
		editorPane.setContentType("text/sparql");
	//	editorPane.setText("TEXT");
	}

	/**
	 * @return
	 */
	private void initFileExplorer() {
		fileExplorerPane = new FileExplorerCard(System.getProperty("user.home"));
		fileExplorerPane.setPreferredSize(new Dimension(150, 400));
	}

	public void initTerminals(JXMultiSplitPane msp) {
		Terminal terminal1 = new Terminal();
		Terminal terminal2 = new Terminal();
		Terminal terminal3 = new Terminal();

		msp.add(new JButton("topLeft"), "topLeft");
		msp.add(terminal1.getConsole(), "bottomLeft");
		msp.add(terminal2.getConsole(), "topRight");
		msp.add(terminal3.getConsole(), "bottomRight");

		terminal1.start();
		terminal2.start();
		terminal3.start();
	}

	public static void main(String[] args) {
		try {
			NimRODTheme nt = new NimRODTheme("./Scute.theme");
			NimRODLookAndFeel nf = new NimRODLookAndFeel();
			nf.setCurrentTheme(nt);
			UIManager.setLookAndFeel(nf);
		} catch (Exception exception) {
			// ignore
		}
		new JEdwards();

	}

	public JXMultiSplitPane initPanes() {
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
