/*
 * PresentationSettings.java
 * Package: de.uniessen.wiinf.wip.goalgetter.tool
 * Project: GoalGetter
 * 
 * GoalGetter is based on a decision supporting method 
 * developed by Markus Stallkamp (markus.stallkamp@uni-essen.de)
 * 
 * (c) 2004 
 * Jonas Sprenger (jonas.sprenger@gmx.de),
 * Tim Franz (tim.franz@uni-essen.de)
 * 
 * ---
 * 
 * This project uses the JGoodies Framework and its Skeleton Pro Application
 * Copyright (c) 2002-2004 JGoodies Karsten Lentzsch. All Rights Reserved.
 * See Readme file for detailed license
 * 
 * $Id: PresentationSettings.java,v 1.1 2004/07/03 20:17:08 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.tool;

import java.util.prefs.Preferences;

import com.jgoodies.binding.beans.Model;

/**
 * Provides bound properties for presentation related settings.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public final class PresentationSettings extends Model {

    // Names of the Bound Bean Properties *************************************

    public static final String PROPERTYNAME_EXPAND_SELECTED_PATHS = "expandSelectedPaths";

    public static final String PROPERTYNAME_COLLAPSE_DESELECTED_PATHS = "collapseDeselectedPaths";

    // Default Values *********************************************************

    /**
     * The default value (true) for property 'expandSelectedPaths'.
     */
    private static final boolean DEFAULT_EXPAND_SELECTED_PATHS = true;

    /**
     * The default value (false) for property 'collapseDeselectedPaths'.
     */
    private static final boolean DEFAULT_COLLAPSE_DESELECTED_PATHS = false;

    // Fields *****************************************************************

    /**
     * Describes whether a path shall be expanded on selection.
     */
    private boolean expandSelectedPaths = DEFAULT_EXPAND_SELECTED_PATHS;

    /**
     * Describes whether a path shall be collapsed on deselection.
     */
    private boolean collapseDeselectedPaths = DEFAULT_COLLAPSE_DESELECTED_PATHS;

    // Properties Accessors ***************************************************

    /**
     * Returns if the navigator collapses deselected paths.
     * 
     * @return true if deselected paths will be collapsed
     */
    public boolean getCollapseDeselectedPaths() {
        return collapseDeselectedPaths;
    }

    /**
     * Returns if the navigator expands paths on selection.
     * 
     * @return true if selected paths are expanded, false otherwise
     */
    public boolean getExpandSelectedPaths() {
        return expandSelectedPaths;
    }

    /**
     * Enables or disables the automatic compression of deselected paths.
     * 
     * @param newValue
     *            true to enable the auto collapse, false to disable it
     */
    public void setCollapseDeselectedPaths(boolean newValue) {
        boolean oldValue = getCollapseDeselectedPaths();
        collapseDeselectedPaths = newValue;
        firePropertyChange(PROPERTYNAME_COLLAPSE_DESELECTED_PATHS, oldValue,
                newValue);
    }

    /**
     * Enables or disables the automatic expansion of selected paths.
     * 
     * @param newValue
     *            true to enable the auto expansion, false to disable it
     */
    public void setExpandSelectedPaths(boolean newValue) {
        boolean oldValue = getExpandSelectedPaths();
        expandSelectedPaths = newValue;
        firePropertyChange(PROPERTYNAME_EXPAND_SELECTED_PATHS, oldValue,
                newValue);
    }

    // Property Persistency ***************************************************

    private static final String KEY_EXPAND_SELECTED_PATHS = "state.expandSelectedPaths";

    private static final String KEY_COLLAPSE_DESELECTED_PATHS = "state.collapseDeselectedPaths";

    /**
     * Restores the persistent properties from the specified Preferences.
     * 
     * @param prefs
     *            the Preferences object that holds the property values
     */
    void restoreFrom(Preferences prefs) {
        setExpandSelectedPaths(prefs.getBoolean(KEY_EXPAND_SELECTED_PATHS,
                DEFAULT_EXPAND_SELECTED_PATHS));

        setCollapseDeselectedPaths(prefs.getBoolean(
                KEY_COLLAPSE_DESELECTED_PATHS,
                DEFAULT_COLLAPSE_DESELECTED_PATHS));
    }

    /**
     * Stores the persistent properties in the specified Preferences.
     * 
     * @param prefs
     *            the Preferences object that holds the property values
     */
    void storeIn(Preferences prefs) {
        prefs.putBoolean(KEY_EXPAND_SELECTED_PATHS, getExpandSelectedPaths());

        prefs.putBoolean(KEY_COLLAPSE_DESELECTED_PATHS,
                getCollapseDeselectedPaths());
    }

}