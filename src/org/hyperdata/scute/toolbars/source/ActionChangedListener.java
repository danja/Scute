package org.hyperdata.scute.toolbars.source;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JButton;

public class ActionChangedListener implements PropertyChangeListener {
	private JButton abstractButton;

	public ActionChangedListener(JButton abstractButton) {
		this.abstractButton = abstractButton;
	}

	public void propertyChange(PropertyChangeEvent e) {
		String propertyName = e.getPropertyName();
		if (e.getPropertyName().equals(Action.NAME)) {
			String text = (String) e.getNewValue();
			abstractButton.setText(text);
		} else if (propertyName.equals("enabled")) {
			Boolean enabledState = (Boolean) e.getNewValue();
			abstractButton.setEnabled(enabledState.booleanValue());
		}
	}
}