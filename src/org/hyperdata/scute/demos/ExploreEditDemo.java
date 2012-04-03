/**
 * 
 */
package org.hyperdata.scute.demos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;

import org.jdesktop.swingx.JXMultiSplitPane;
import org.jdesktop.swingx.MultiSplitLayout;
import org.jdesktop.swingx.MultiSplitLayout.Divider;
import org.jdesktop.swingx.MultiSplitLayout.Leaf;
import org.jdesktop.swingx.MultiSplitLayout.Split;

import org.hyperdata.scute.demos.temp.SourceEditor;
import org.hyperdata.scute.demos.temp.FileTreeSelectionListener;
import org.hyperdata.scute.filemanager.FileExplorerCard;
import org.hyperdata.scute.toolbars.file.FileUI;
import org.hyperdata.scute.toolbars.file.IO;

/**
 * @author danny
 * 
 */
public class ExploreEditDemo {

	private JPanel outerPanel;
	private FileExplorerCard fileExplorerPane; // left-hand panel
	
	private JPanel editorPanel; // right-hand panel
	private JToolBar fileToolBar;
	private JScrollPane editorScrollPane;
	private SourceEditor editor;
	
	private Leaf left;
	private Leaf right;
	
	private IO io;
	private FileUI fileUI;

	public ExploreEditDemo() {
		initLayout();
		initFileExplorer();
		initEditor();
		initToolBar();
		
		outerPanel.add(fileExplorerPane, "left");
		
		editorPanel = new JPanel(new BorderLayout());
		editorPanel.add(editor.getScrollPane(), BorderLayout.CENTER);
		editorPanel.add(fileToolBar, BorderLayout.NORTH);
		outerPanel.add(editorPanel, "right");
		centerSplit();
		
		FileTreeSelectionListener treeMouseListener = new FileTreeSelectionListener();
		treeMouseListener.attach(fileExplorerPane.getTree(), editor);
	}

	private void initEditor() {
		editor = new SourceEditor();
		jsyntaxpane.DefaultSyntaxKit.initKit();
		editor.setContentType("text/sparql");
	}
	
	private void initToolBar(){
		io = new IO(this);
		fileUI = new FileUI(io);
// 		toolsPanel.add(fileUI.getToolBar());
		fileToolBar = new JToolBar();
		fileToolBar.add(fileUI.getToolBar());
	}

	/**
	 * @return
	 */
	private void initFileExplorer() {
		fileExplorerPane = new FileExplorerCard(System.getProperty("user.home"));
		fileExplorerPane.setPreferredSize(new Dimension(150,400));
	}

	public void centerSplit() {
		left.setWeight(0.5);
		right.setWeight(0.5);
	}

	private void initLayout() {
		
		Split outer = new Split();
		outer.setRowLayout(true);

		left = new Leaf("left");
		right = new Leaf("right");

		outer.setChildren(left, new Divider(), right);

		outerPanel = new JXMultiSplitPane();
		MultiSplitLayout layout = new MultiSplitLayout(outer);
		outerPanel.setLayout(layout);
	}

	/**
	 * @return the contentPanel
	 */
	public JPanel getOuterPanel() {
		return this.outerPanel;
	}

	// ///////////////////////////////////////////////////////////////

	public static void main(String[] argv) {
		try {
			NimRODTheme nt = new NimRODTheme("./Scute.theme");
			NimRODLookAndFeel nf = new NimRODLookAndFeel();
			nf.setCurrentTheme(nt);
			UIManager.setLookAndFeel(nf);
		} catch (Exception exception) {
			// ignore
		}
		JFrame frame = new JFrame("File Explorer");

		ExploreEditDemo eed = new ExploreEditDemo();
		frame.getContentPane().add(eed.getOuterPanel());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		// frame.pack();
		
		frame.setVisible(true);
	}
}
