/**
 * 
 */
package org.hyperdata.scute.validate;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

/**
 * @author danny
 *
 */
public class IconSetter implements Runnable, ValidationListener {

	private int status = 0;
	private AbstractButton button;
	
	private IconSetter(){ // hide, shouldn't happen
	}
	
	public IconSetter(AbstractButton target){
		this.button = target;
		button.setIcon(Validator.ICON[0]);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	
	public void setStatus(int status){
		this.status  = status;
	}
	
	@Override
	public void run() {
		button.setIcon(Validator.ICON[status]);
		System.out.println("running iconsetter, status = "+status);
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.validate.ValidationListener#stateChanged(org.hyperdata.scute.validate.ValidationEvent)
	 */
	@Override
	public void stateChanged(ValidationEvent vEvent) {
		setStatus(vEvent.getStatus());
		SwingUtilities.invokeLater(this);
	}

}
