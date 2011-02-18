/**
 * 
 */
package org.hyperdata.scute.sparql;

import javax.swing.JEditorPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.hyperdata.scute.source.TextContainer;

/**
 * @author danny
 *
 */
public class SparqlSourcePanel extends JEditorPane implements TextContainer,
ChangeListener {

	/* (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.source.TextContainer#getSyntax()
	 */
	@Override
	public String getSyntax() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.source.TextContainer#getTextFilename()
	 */
	@Override
	public String getTextFilename() {
		// TODO Auto-generated method stub
		return null;
	}

}
