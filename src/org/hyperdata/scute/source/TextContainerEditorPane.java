/**
 * 
 */
package org.hyperdata.scute.source;

import java.awt.event.FocusEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JEditorPane;
import javax.swing.event.ChangeListener;

import org.hyperdata.scute.main.Config;

/**
 * A JEditorPane with a few additions for managing the text contentS
 * 
 * @author danny
 */
public abstract class TextContainerEditorPane extends JEditorPane implements TextContainer, 
ChangeListener {

	private String syntax;
	
	/**
	 * @param syntax
	 */
	public TextContainerEditorPane(String syntax) {
		this.syntax = syntax;
		addFocusListener(this);
	}
	
	public String getSyntax() {
		return syntax;
	}

	/**
	 * 
	 */
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
