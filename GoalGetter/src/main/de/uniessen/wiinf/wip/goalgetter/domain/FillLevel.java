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
 * $Id: FillLevel.java,v 1.1 2004/08/14 11:11:12 moleman Exp $
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
 * @version $Revision: 1.1 $
 *  
 */
public class FillLevel {

    public static final int EMPTY = 0;

    public static final int HALF = 1;

    public static final int FULL = 2;

    /**
     *  
     */
    private FillLevel() {
        super();
        // TODO Auto-generated constructor stub
    }

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