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
 * $Id: ActionPresentationMode.java,v 1.3 2004/08/14 11:11:12 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.tool;

/**
 * A typesafe enumaration for the Navigator Action modes. An action mode
 * describes the grouping rule to display the actions in the navigator.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.3 $
 *  
 */
public final class ActionPresentationMode {
    /**
     * presentation mode for a grouping by
     * {@link de.uniessen.wiinf.wip.goalgetter.domain.Goal}
     */
    public static final ActionPresentationMode GOAL = new ActionPresentationMode(
            "Goal");//$NON-NLS-1$

    /**
     * presentation mode for a grouping by
     * {@link de.uniessen.wiinf.wip.goalgetter.domain.Alternative}
     */
    public static final ActionPresentationMode ALTERNATIVE = new ActionPresentationMode(
            "Alternative");//$NON-NLS-1$

    /**
     * The default Presentation Mode, returned by {@link #valueOf(String)}if no
     * other emode matches the String.
     */
    public static final ActionPresentationMode DEFAULT = GOAL;

    /**
     * Holds the mode's name.
     */
    private final String name;

    /**
     * Constructs a <code>ViewMode</code> for the specified name.
     * 
     * @param name
     *            name of the presentation mode to construct
     */
    private ActionPresentationMode(String name) {
        this.name = name;
    }

    /**
     * Answers a <code>PresentationMode</code> for the specified case
     * insensitive name, <code>DEFAULT</code> if none fits.
     * 
     * @param name
     *            the name to look for
     * @return the associated PresentationMode or DEFAULT if none fits
     */
    public static ActionPresentationMode valueOf(String name) {
        if (GOAL.name.equalsIgnoreCase(name))
            return GOAL;
        else if (ALTERNATIVE.name.equalsIgnoreCase(name))
            return ALTERNATIVE;
        else
            return DEFAULT;
    }

    /**
     * Returns the name of the presentationMode
     * 
     * @return name
     */
    public String name() {
        return name;
    }

    public String toString() {
        return "ViewMode: " + name; //$NON-NLS-1$
    }

}