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
 * @author danny
 *
 * combination of listeners for events related to user activity
 */
public interface UserActivityListener extends EventListener, 
WindowFocusListener, WindowListener, WindowStateListener, 
MouseListener, KeyListener, DocumentListener, FocusListener, ChangeListener {

}
