/*
 * $Id: StatusBarAddon.java 3475 2009-08-28 08:30:47Z kleopatra $
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
package org.hdesktop.swingx.plaf;

import org.hdesktop.swingx.JXStatusBar;
import org.hdesktop.swingx.plaf.windows.WindowsLookAndFeelAddons;
import org.hdesktop.swingx.util.OS;

/**
 * Addon for <code>JXStatusBar</code>.<br>
 *
 */
public class StatusBarAddon extends AbstractComponentAddon {
    
    public StatusBarAddon() {
        super("JXStatusBar");
    }
    
    @Override
    protected void addBasicDefaults(LookAndFeelAddons addon, DefaultsList defaults) {
        super.addBasicDefaults(addon, defaults);
        
        defaults.add(JXStatusBar.uiClassID,
                "org.hdesktop.swingx.plaf.basic.BasicStatusBarUI");
    }
    
    @Override
    protected void addMacDefaults(LookAndFeelAddons addon, DefaultsList defaults) {
        super.addMacDefaults(addon, defaults);
        
        defaults.add(JXStatusBar.uiClassID,
                "org.hdesktop.swingx.plaf.macosx.MacOSXStatusBarUI");
    }
    
    @Override
    protected void addMetalDefaults(LookAndFeelAddons addon, DefaultsList defaults) {
        super.addMetalDefaults(addon, defaults);
        
        defaults.add(JXStatusBar.uiClassID,
                "org.hdesktop.swingx.plaf.metal.MetalStatusBarUI");
    }
    
    @Override
    protected void addWindowsDefaults(LookAndFeelAddons addon, DefaultsList defaults) {
        super.addWindowsDefaults(addon, defaults);
        if (OS.isUsingWindowsVisualStyles()) {
            defaults.add(JXStatusBar.uiClassID,
                    "org.hdesktop.swingx.plaf.windows.WindowsStatusBarUI");
            
            String xpStyle = OS.getWindowsVisualStyle();
            
            if (WindowsLookAndFeelAddons.SILVER_VISUAL_STYLE.equalsIgnoreCase(xpStyle)
                    || WindowsLookAndFeelAddons.VISTA_VISUAL_STYLE.equalsIgnoreCase(xpStyle)) {
                defaults.add("StatusBar.leftImage", "resources/silver-statusbar-left.png");
                defaults.add("StatusBar.middleImage", "resources/silver-statusbar-middle.png");
                defaults.add("StatusBar.rightImage", "resources/silver-statusbar-right.png");
            } else {
                defaults.add("StatusBar.leftImage", "resources/statusbar-left.png");
                defaults.add("StatusBar.middleImage", "resources/statusbar-middle.png");
                defaults.add("StatusBar.rightImage", "resources/statusbar-right.png");
            }
        } else {
            defaults.add(JXStatusBar.uiClassID,
                    "org.hdesktop.swingx.plaf.windows.WindowsClassicStatusBarUI");
        }
    }
}
