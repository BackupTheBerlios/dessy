/*
 * ActionPresentationMode.java
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
 * $Id: ActionPresentationMode.java,v 1.1 2004/07/28 16:02:18 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.tool;

/**
 * A typesafe enumaration for the Navigator Action modes.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public final class ActionPresentationMode {
    public static final ActionPresentationMode GOAL = new ActionPresentationMode(
            "Goal");

    public static final ActionPresentationMode ALTERNATIVE = new ActionPresentationMode(
            "Alternative");

    public static final ActionPresentationMode DEFAULT = GOAL;

    /**
     * Holds the mode's name.
     */
    private final String name;

    /**
     * Constructs a <code>ViewMode</code> for the specified name.
     */
    private ActionPresentationMode(String name) {
        this.name = name;
    }

    /**
     * Answers a <code>ViewMode</code> for the specified case insensitive
     * name, <code>DEFAULT</code> if none fits.
     * 
     * @param name
     *            the name to look for
     * @return the associated SortMode or DEFAULT if none fits
     */
    public static ActionPresentationMode valueOf(String name) {
        if (GOAL.name.equalsIgnoreCase(name))
            return GOAL;
        else if (ALTERNATIVE.name.equalsIgnoreCase(name))
            return ALTERNATIVE;
        else
            return DEFAULT;
    }

    public String name() {
        return name;
    }

    public String toString() {
        return "ViewMode: " + name;
    }

}