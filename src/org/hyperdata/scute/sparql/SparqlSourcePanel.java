/**
 * 
 */
package org.hyperdata.scute.sparql;

import javax.swing.event.ChangeEvent;

import org.hyperdata.scute.main.Config;
import org.hyperdata.scute.source.HighlighterEditorKit;
import org.hyperdata.scute.source.TextContainerEditorPane;

/**
 * @author danny
 *
 */
public class SparqlSourcePanel extends TextContainerEditorPane {

	public SparqlSourcePanel(String string){
		super(string);
		// addUserActivityListener(autoSave);
	}
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
		return "SPARQL";
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.source.TextContainer#getTextFilename()
	 */
	@Override
	public String getFilename() {
		return Config.SPARQL_FILENAME;
	}

}
