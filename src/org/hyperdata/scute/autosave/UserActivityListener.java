/**
 * 
 */
package org.hyperdata.scute.autosave;

import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.util.EventListener;

import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;

/**
 * The listener interface for receiving userActivity events.
 * The class that is interested in processing a userActivity
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addUserActivityListener<code> method. When
 * the userActivity event occurs, that object's appropriate
 * method is invoked.
 *
 * @author danny
 * 
 * combination of listeners for events related to user activity
 */
public interface UserActivityListener extends EventListener, 
WindowFocusListener, WindowListener, WindowStateListener, 
MouseListener, KeyListener, DocumentListener, FocusListener, ChangeListener {

}
