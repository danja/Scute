/*
 * $Id: package-info.java 3713 2010-07-13 14:07:40Z kschaefe $
 *
 * Copyright 2007 Sun Microsystems, Inc., 4150 Network Circle,
 * Santa Clara, California 95054, U.S.A. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 */

/**
 * Contains extensions to the Swing GUI toolkit, including new and enhanced
 * components that provide functionality commonly required by rich,
 * data-centric client applications. Many of these features will eventually
 * be incorporated into the Swing toolkit, although API compatibility will
 * not be guaranteed.
 * <p>
 * 
 * <h2>New or Enhanced Functionality</h2>
 * 
 * <h3>Auto-completion for TextFields and ComboBoxes</h3>
 * 
 * <h3>Enhanced Rendering Support for Collection Components</h3>
 * 
 * <h3>Built-In Search Support for Collection Components and JXEditorPane</h3>
 * 
 * <h3>Filtering/Sorting for Tables and Lists</h3>
 * 
 * <b>NOTE:</b> Currently on the move to core (JDK 6+) sorting/filtering. Trunk
 * has all SwingX specials removed, JXTable sort api back again. JXList still
 * disabled.
 * 
 * <h3>Login/Authentication Framework</h3>
 * 
 * <h3>Painter-enabled components</h3>
 * 
 * <p>
 * Components that use painters for background rendering alter the functionality
 * of how {@link java.awt.Component#setBackground(java.awt.Color)} works.  
 * Setting the background color of a painter-enabled component effectively sets 
 * the background painter to paint the requested color.
 * <p>
 * Look and Feel implementors should note that setting a 
 * {@link java.swing.plaf.UIResource} to {@code setBackground} will cause a 
 * {@code Painter} {@code UIResource} to be installed.  This means that 
 * implementors should set the background before setting the painter as the last 
 * one set wins. 
 * 
 * <h2>New and Enhanced components</h2>
 * 
 * <h3>Buttons and Labels</h3>
 * <ul>
 * <li> {@link org.hdesktop.swingx.JXButton}
 * <li> {@link org.hdesktop.swingx.JXHyperlink Hyperlink}
 * <li> {@link org.hdesktop.swingx.JXLabel}
 * <li> {@link org.hdesktop.swingx.JXBusyLabel}
 * <li> {@link org.hdesktop.swingx.JXRadioGroup}
 * </ul>
 * 
 * 
 * <h3>Collection Components</h3>
 * 
 * These are sortable/filterable (with the exception of hierarchical
 * components) with consistent and uniform SwingX rendering, highlighting,
 * searching and rollover support.
 * <ul>
 * <li> {@link org.hdesktop.swingx.JXTable Table} uses the enhanced
 * {@link org.hdesktop.swingx.JXTableHeader TableHeader}
 * <li> {@link org.hdesktop.swingx.JXList List} - rollover and sort/filter
 * functionality is disabled by default
 * <li> {@link org.hdesktop.swingx.JXTree Tree}
 * <li> {@link org.hdesktop.swingx.JXTreeTable TreeTable} - a new
 * hierarchical component with support of tabular node properties
 * </ul>
 * 
 * <h3>Top-level Windows, General and Special Purpose Containers</h3>
 * <ul>
 * <li>Enhanced {@link org.hdesktop.swingx.JXFrame Frame} using an extended
 * {@link org.hdesktop.swingx.JXRootPane RootPane RootPane} to support a
 * {@link org.hdesktop.swingx.JXStatusBar StatusBar}
 * <li> {@link org.hdesktop.swingx.JXDialog Dialog}
 * <li> {@link org.hdesktop.swingx.JXPanel Panel}
 * <li> {@link org.hdesktop.swingx.JXErrorPane ErrorPane}
 * <li> {@link org.hdesktop.swingx.JXLoginPane LoginPane}
 * 
 * <li>Search components: {@link org.hdesktop.swingx.JXFindBar FindBar} used
 * for incremental search (similar to FireFox),
 * {@link org.hdesktop.swingx.JXFindPanel FindPanel} used in a find dialog,
 * and {@link org.hdesktop.swingx.JXSearchPanel SearchPanel} used for what
 * was it?
 * <li>Nested SplitPane {@link org.hdesktop.swingx.JXMultiSplitPane
 * MultiSplitPane}
 * <li>Vertical collapsing/expansion functionality is provided by a
 * {@link org.hdesktop.swingx.JXCollapsiblePane CollapsiblePane}. A special
 * purpose collapsible is the {@link org.hdesktop.swingx.JXTaskPane
 * TaskPane} which typically is used to group buttons/hyperlinks which
 * perform related tasks. A special
 * {@link org.hdesktop.swingx.JXTaskPaneContainer TaskPaneContainer} is
 * responsible for the layout of several TaskPanes.
 * <li>Easily configurable {@link org.hdesktop.swingx.JXTipOfTheDay
 * TipOfTheDay}
 * <li> {@link org.hdesktop.swingx.JXTitledPanel TitledPanel}
 * 
 * </ul>
 * 
 * <h3>Miscellaneous Components</h3>
 * 
 * <ul>
 * <li>New calendar components: the {@link org.hdesktop.swingx.JXDatePicker
 * DatePicker} allows to select a single Date and a
 * {@link org.hdesktop.swingx.JXMonthView MonthView} showing the overview of
 * one or more months.
 * 
 * <li> {@link org.hdesktop.swingx.JXHeader Header}
 * <li> {@link org.hdesktop.swingx.JXTitledSeparator TitledSeparator}
 * 
 * <li> {@link org.hdesktop.swingx.JXColorSelectionButton}
 * <li> {@link org.hdesktop.swingx.JXEditorPane}
 * <li> {@link org.hdesktop.swingx.JXGradientChooser}
 * <li> {@link org.hdesktop.swingx.JXGraph}
 * <li>Image containers {@link org.hdesktop.swingx.JXImageView ImageView}
 * and {@link org.hdesktop.swingx.JXImagePanel ImagePanel} (PENDING JW:
 * merge/remove one?)
 * <li> {@link org.hdesktop.swingx.JXMultiThumbSlider MultiThumbSlider}
 * 
 * </ul>
 * 
 * <h2>External Information Sources</h2>
 * 
 * <a href="http://wiki.java.net/bin/view/Javadesktop/SwingX">SwingX Twiki</a>
 * <a href="http://wiki.java.net/bin/view/Javadesktop/SwingXChanges">Change History</a>
 * <a href="http://forums.java.net/jive/forum.jspa?forumID=73">SwingLabs User and 
 * Developer Discussion Forum</a>
 */
package org.hdesktop.swingx;

