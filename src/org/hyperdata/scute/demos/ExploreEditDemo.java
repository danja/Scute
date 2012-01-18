/**
 * 
 */
package org.hyperdata.scute.demos;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
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

import org.hyperdata.scute.demos.temp.SourceEditor;
import org.hyperdata.scute.demos.temp.FileTreeMouseListener;
import org.hyperdata.scute.filemanager.FileExplorerCard;

/**
 * @author danny
 * 
 */
public class ExploreEditDemo {

	JPanel contentPanel;
	private Leaf left;
	private Leaf right;
	private SourceEditor editorPane;
	private FileExplorerCard fileExplorerPane;
	private JScrollPane editorScrollPane;

	public ExploreEditDemo() {
		initLayout();
		initFileExplorer();
		initEditor();

		contentPanel.add(fileExplorerPane, "left");
		contentPanel.add(editorPane.getScrollPane(), "right");
		centerSplit();
		
		FileTreeMouseListener treeMouseListener = new FileTreeMouseListener();
		treeMouseListener.attach(fileExplorerPane.getTree(), editorPane);
	}

	/**
	 * @return
	 */
	private void initEditor() {
		editorPane = new SourceEditor();
//		editorScrollPane = new JScrollPane(editorPane);
//
//		editorPane.setFont(new Font("Monospaced", Font.PLAIN, 12));

		// setEditorKit(new ScuteEditorKit("SPARQL"));
		jsyntaxpane.DefaultSyntaxKit.initKit();
		editorPane.setContentType("text/sparql");
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
		// Dimension d = contentPanel.getSize();
		// editorPane.setPreferredSize(new Dimension(d.width/2, d.height));
		// fileExplorerCard.setPreferredSize(new Dimension(d.width/2,
		// d.height));
	}

	private void initLayout() {
		Split outer = new Split();
		outer.setRowLayout(true);

		left = new Leaf("left");
		right = new Leaf("right");

		outer.setChildren(left, new Divider(), right);

		contentPanel = new JXMultiSplitPane();
		MultiSplitLayout layout = new MultiSplitLayout(outer);
		contentPanel.setLayout(layout);
	}

	/**
	 * @return the contentPanel
	 */
	public JPanel getContentPanel() {
		return this.contentPanel;
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
			exception.printStackTrace();
		}
		JFrame frame = new JFrame("File Explorer");

		ExploreEditDemo eed = new ExploreEditDemo();
		frame.getContentPane().add(eed.getContentPanel());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		// frame.pack();
		
		frame.setVisible(true);
	}
}
