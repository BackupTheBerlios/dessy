/*
 * FillLevel.java
 * Package: de.uniessen.wiinf.wip.goalgetter.domain
 * Project: GoalGetter
 * 
 * GoalGetter is based on a decision supporting method 
 * developed by Markus Stallkamp (markus.stallkamp@uni-essen.de)
 * 
 * (c) 2004 
 * Jonas Sprenger (jonas.sprenger@gmx.de),
 * Tim Franz (tim.franz@uni-essen.de)
 * 
 * $Id: FillLevel.java,v 1.2 2004/08/14 16:43:35 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.domain;

import javax.swing.Icon;

import de.uniessen.wiinf.wip.goalgetter.tool.Resources;

/**
 * FillLevel
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.2 $
 *  
 */
public class FillLevel {

    /**
     * The empty fill level
     */
    public static final int EMPTY = 0;

    /**
     * The half full fill level
     */
    public static final int HALF = 1;

    /**
     * The full fill level
     */
    public static final int FULL = 2;

    /**
     * Hide constructor and prevent initializing
     */
    private FillLevel() {
        // hidden from public
    }

    /**
     * Answers the icon for the given Fill Level
     * 
     * @param fillLevel
     *            the fill level to get the icon for
     * @return Icon for the fill level
     */
    public static Icon getIcon(int fillLevel) {
        switch (fillLevel) {
        case EMPTY:
            return Resources.COMPLETION_EMPTY_ICON;

        case HALF:
            return Resources.COMPLETION_HALF_ICON;

        case FULL:
            return Resources.COMPLETION_FULL_ICON;

        default:
            return Resources.COMPLETION_EMPTY_ICON;
        }
    }

}