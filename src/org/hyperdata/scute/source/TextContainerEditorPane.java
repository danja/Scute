/**
 * 
 */
package org.hyperdata.scute.source;

import java.awt.event.FocusEvent;
import java.io.*;

import javax.swing.event.ChangeListener;

import org.hyperdata.scute.main.Config;
import org.hyperdata.scute.syntax.ScalableEditorPane;


/**
 * A JEditorPane with a few additions for managing the text contentS
 * 
 * @author danny
 */
public abstract class TextContainerEditorPane extends ScalableEditorPane implements TextContainer, 
ChangeListener {

	// private String syntax;
	private String filename;
	
//	public void paint(Graphics g){
//		((Graphics2D)g).scale(2,2);
//		super.paint(g);
//	}
	
	/**
	 * @param syntax
	 */
	public TextContainerEditorPane(String syntax) {
		super.setSyntax(syntax);
		// this.syntax = syntax;
		addFocusListener(this);
		getDocument().putProperty("ZOOM_FACTOR", new Double(2.5));
		
	}
	/////////////////////////////////////////////////////////////////////////
	
//	private HashMap<Object, Action> actions= new HashMap<Object, Action>();
//	
//	public HashMap<Object, Action> createActionTable(ScuteEditorKit editorKit) {
//		System.out.println("TextContainerEditor.createActionTable");
//        Action[] actionsArray = editorKit.getActions();
//        for (int i = 0; i < actionsArray.length; i++) {
//            Action a = actionsArray[i];
//            actions.put(a.getValue(Action.NAME), a);
//            System.out.println(a.getValue(Action.NAME));
//        }
//	return actions;
//    }
//	
//	public Action getActionByName(String name) {
//		System.out.println(actions.get(name));
//	    return actions.get(name);
//	}

	//////////////////////////////////////
	
	
	
	
	
	
	
	/////////////////////////////////////////////////////////

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hyperdata.scute.source.TextContainer#getTextFilename()
	 */
	@Override
	public String getFilename() {
		return filename;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.source.TextContainer#setFilename(java.lang.String)
	 */
	@Override
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	/* (non-Javadoc)
	 * @see org.hyperdata.scute.source.TextContainer#load()
	 */
	@Override
	public void load() {
		InputStream in = null;
		File file = new File(getFilename());
		System.out.println("FILENAME="+getFilename());
		// StringBuffer text = new StringBuffer();
		String text ="";
		Writer writer = new StringWriter();
		try {
		    in = new FileInputStream(file);
		    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	//	    String line = null;
		    
//		    while ((line = reader.readLine()) != null) {
//		        text.append(line);
//		    }
		   int i;
		    while((i = reader.read()) != -1){
		    	writer.write(i);
		    }
		    in.close();
		    text = writer.toString();
		} catch (IOException exception) {
		    exception.printStackTrace();
		} 
		setText(text.toString());
	}

	/**
	 * 
	 */
	@Override
	public void save(){
		File file = new File(getFilename());

		byte[] bytes = (Config.self
				.getIdentifyingComment(getSyntax()) + getText()).getBytes();
		try {
			OutputStream fos = new FileOutputStream(file);
			fos.write(bytes);
			fos.close();
			System.out.println("saving TEXT : " + getSyntax()+ " filename = "+getFilename());
		} catch (IOException e) {
			// TODO popup warning
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusLost(FocusEvent arg0) {
		System.out.println("focusLost so SAVE");
		save();
		
	}
	
}
