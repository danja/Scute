/*
 * $Id: LinuxLookAndFeelAddons.java 3637 2010-03-31 15:52:07Z kschaefe $
 *
 * Copyright 2004 Sun Microsystems, Inc., 4150 Network Circle,
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
 */
package org.hdesktop.swingx.plaf.linux;

import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.UIResource;

import org.hdesktop.swingx.plaf.UIManagerExt;
import org.hdesktop.swingx.plaf.basic.BasicLookAndFeelAddons;

public class LinuxLookAndFeelAddons extends BasicLookAndFeelAddons {
    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        super.initialize();
        
        //Issue 1297: added border to ensure non-null insets
        Border b = UIManagerExt.getSafeBorder("Table.focusSelectedCellHighlightBorder", BorderFactory.createEmptyBorder());
        
        if (b instanceof UIResource) {
            UIManager.put("Table.focusSelectedCellHighlightBorder", new BorderUIResource(b));
        }
    }
}
