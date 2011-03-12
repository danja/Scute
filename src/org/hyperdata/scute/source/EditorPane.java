/**
 * 
 */
package org.hyperdata.scute.source;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.io.*;

import javax.swing.event.ChangeListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

import org.hyperdata.scute.syntax.ScalableEditorPane;
import org.hyperdata.scute.system.Log;
import org.hyperdata.scute.toolbars.source.EditorToolbar;
import org.hyperdata.scute.toolbars.source.UndoHandler;
import org.hyperdata.scute.tree.actions.RedoAction;
import org.hyperdata.scute.tree.actions.UndoAction;


/**
 * A JEditorPane with a few additions for managing the text contentS
 * 
 * @author danny
 */
public abstract class EditorPane extends ScalableEditorPane implements TextContainer, ChangeListener {

	private String filename;
	
	/**
	 * Listener for the edits on the current document.
	 */
	private UndoableEditListener undoHandler = new UndoHandler(this);

	/** UndoManager that we add edits to. */
	private UndoManager undoManager = new UndoManager();
	
	private UndoAction undoAction = new UndoAction(this);
	private RedoAction redoAction = new RedoAction(this);
	private EditorToolbar editorToolbar;
	
	/**
	 * @param syntax
	 */
	public EditorPane(String syntax) {
		super.setSyntax(syntax);
		setFont(new Font("Monospaced", Font.PLAIN, 12));
		// setPreferredSize(new Dimension(800,600));
		addFocusListener(this);
		setDragEnabled(true);
		getDocument().putProperty("ZOOM_FACTOR", new Double(2.5));
	}
	
//	public void createToolbar(){
//
//	}
	
	/**
	 * Resets the undo manager.
	 */
	public void resetUndoManager() { 
		getUndoManager().discardAllEdits();
		getUndoAction().update();
		getRedoAction().update();
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
		    Log.exception(exception);
		} 
		setText(text.toString());
	}

	/**
	 * 
	 */
	@Override
	public void save(){
		File file = new File(getFilename());

		// Config.self.getIdentifyingComment(getSyntax()) + 
		byte[] bytes = (getText()).getBytes();
		try {
			OutputStream fos = new FileOutputStream(file);
			fos.write(bytes);
			fos.close();
			// System.out.println("saving TEXT : " + getSyntax()+ " filename = "+getFilename());
		} catch (Exception exception) {
			Log.exception(exception);
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
		// System.out.println("focusLost so SAVE");
		save();
		
	}

	/**
	 * @param undoAction the undoAction to set
	 */
	public void setUndoAction(UndoAction undoAction) {
		this.undoAction = undoAction;
	}

	/**
	 * @return the undoAction
	 */
	public UndoAction getUndoAction() {
		return undoAction;
	}

	/**
	 * @param redoAction the redoAction to set
	 */
	public void setRedoAction(RedoAction redoAction) {
		this.redoAction = redoAction;
	}

	/**
	 * @return the redoAction
	 */
	public RedoAction getRedoAction() {
		return redoAction;
	}

	/**
	 * @param undoManager the undoManager to set
	 */
	public void setUndoManager(UndoManager undoManager) {
		this.undoManager = undoManager;
	}

	/**
	 * @return the undoManager
	 */
	public UndoManager getUndoManager() {
		return undoManager;
	}
	
}
