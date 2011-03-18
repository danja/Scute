/**
 * 
 */
package org.hyperdata.scute.sparql.panels;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EtchedBorder;

import org.hyperdata.scute.status.StatusButton;
import org.hyperdata.scute.status.StatusChangeListener;
import org.hyperdata.scute.status.StatusEvent;
import org.hyperdata.scute.status.StatusInfoPane;
import org.hyperdata.scute.status.StatusMonitor;
import org.hyperdata.scute.validate.SparqlValidateAction;

/**
 * @author danny
 *
 */
public class SparqlStatusPanel extends JPanel implements StatusChangeListener {

	private JProgressBar progressBar;
	
	public SparqlStatusPanel(SparqlValidateAction sparqlValidateAction){
		super(new FlowLayout(FlowLayout.LEADING)); // left-aligned
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		StatusInfoPane validatorPane = new StatusInfoPane(sparqlValidateAction);
		StatusButton validatorButton = new StatusButton(sparqlValidateAction);
		
		progressBar = new JProgressBar(0,100);
		progressBar.setVisible(false);
		add(validatorButton);
		add(validatorPane);
		add(progressBar);
	}
	
	/* (non-Javadoc)
	 * @see org.hyperdata.scute.status.StatusChangeListener#statusChanged(org.hyperdata.scute.status.StatusEvent)
	 */
	@Override
	public void statusChanged(StatusEvent statusEvent) {
		
		int progress = statusEvent.getProgress();
		
		if(progress == StatusMonitor.INACTIVE || statusEvent.getStatus() == StatusMonitor.GREEN){
			progress = 0;
			progressBar.setVisible(false);
			return;
		}
			
		progressBar.setVisible(true);
		
		if(progress == StatusMonitor.INDETERMINATE_PROGRESS){
			progressBar.setIndeterminate(true);
		}else {
			progressBar.setIndeterminate(false);
			progressBar.setValue(progress);
		}
		System.out.println("PROGRESS="+progress);
	}
}
