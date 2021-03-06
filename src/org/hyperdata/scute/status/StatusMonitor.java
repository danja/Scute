/**
 * 
 */
package org.hyperdata.scute.status;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;

import org.hyperdata.resources.indicators.IndicatorIcons;

/**
 * The Class StatusMonitor.
 *
 * @author danny
 */
public class StatusMonitor {
	
	// bunch of constants
	public static final int N_STATES = 3;
	
	public static final int RED = 0;
	public static final int AMBER = 1;
	public static final int GREEN = 2;
	
	protected static final String[] DEFAULT_DESCRIPTION = { "Bad", "Unknown",
			"Good" };

	protected static final ImageIcon[] ICON = new ImageIcon[N_STATES];

	public static final int INDETERMINATE_PROGRESS = -101;

	public static final int INACTIVE = -1;
	
	static {
		ICON[0] = IndicatorIcons.redIcon;
		ICON[1] = IndicatorIcons.amberIcon;
		ICON[2] = IndicatorIcons.greenIcon;
	}
	
	private Set<StatusChangeListener> statusChangeListeners = new HashSet<StatusChangeListener>();

	/**
	 * @return
	 */
	public Set<StatusChangeListener> getStatusChangeListeners() {
		return statusChangeListeners;
	}
	
	/**
	 * @param statusChangeListeners
	 */
	public void addStatusChangeListeners(Set<StatusChangeListener> statusChangeListeners) {
		this.statusChangeListeners.addAll(statusChangeListeners);
	}
	
	/**
	 * Adds the status listener.
	 *
	 * @param listener the listener
	 */
	public void addStatusListener(StatusChangeListener listener) {
		statusChangeListeners.add(listener);
	}
	
	/**
	 * State changed.
	 *
	 * @param vEvent the v event
	 */
	public void stateChanged(StatusEvent vEvent){
		Iterator<StatusChangeListener> iterator = statusChangeListeners.iterator();
		while(iterator.hasNext()){
			iterator.next().statusChanged(vEvent);
		}
	}
}
