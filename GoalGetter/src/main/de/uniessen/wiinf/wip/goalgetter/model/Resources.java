/*
 * Resources.java
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
 * $Id: Resources.java,v 1.1 2004/09/25 14:56:57 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.model;

import javax.swing.Icon;

import com.jgoodies.uif.util.ResourceUtils;

/**
 * This class consists of string ids used for the resource bundle lookup, and
 * some preloaded resources.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public final class Resources {

    // IDs ********************************************************************

    /**
     * Comment for <code>DESCRIPTION_NAME_ID</code>
     */
    public static final String DESCRIPTION_NAME_ID = "description.name";//$NON-NLS-1$

    /**
     * Comment for <code>DESCRIPTION_ICON_ID</code>
     */
    public static final String DESCRIPTION_ICON_ID = "description.icon";//$NON-NLS-1$

    /**
     * Comment for <code>GOAL_ICON_ID</code>
     */
    public static final String GOAL_ICON_ID = "goal.icon";//$NON-NLS-1$

    /**
     * Comment for <code>ALTERNATIVE_ICON_ID</code>
     */
    public static final String ALTERNATIVE_ICON_ID = "alternative.icon";//$NON-NLS-1$

    /**
     * Comment for <code>ACTION_ICON_ID</code>
     */
    public static final String ACTION_ICON_ID = "action.icon";//$NON-NLS-1$

    /**
     * Comment for <code>ARROW_ICON_ID</code>
     */
    public static final String ARROW_ICON_ID = "arrow.icon";//$NON-NLS-1$

    /**
     * Comment for <code>LOGO_ICON_ID</code>
     */
    public static final String LOGO_ICON_ID = "logo.icon";//$NON-NLS-1$

    /**
     * Comment for <code>COMPLETION_EMPTY_ICON_ID</code>
     */
    public static final String COMPLETION_EMPTY_ICON_ID = "completion.empty.icon"; //$NON-NLS-1$

    /**
     * Comment for <code>COMPLETION_HALF_ICON_ID</code>
     */
    public static final String COMPLETION_HALF_ICON_ID = "completion.half.icon"; //$NON-NLS-1$

    /**
     * Comment for <code>COMPLETION_FULL_ICON_ID</code>
     */
    public static final String COMPLETION_FULL_ICON_ID = "completion.full.icon"; //$NON-NLS-1$

    /**
     * Comment for <code>HELP_CONTENTS_ICON_ID</code>
     */
    public static final String HELP_CONTENTS_ICON_ID = "helpContents.icon";//$NON-NLS-1$

    /**
     * Comment for <code>DEFAULT_PROJECT_NAME_ID</code>
     */
    public static final String DEFAULT_PROJECT_NAME_ID = "defaultProject.name";//$NON-NLS-1$

    // Resources **************************************************************

    /**
     * Comment for <code>DESCRIPTION_NAME</code>
     */
    public static final String DESCRIPTION_NAME = getString(DESCRIPTION_NAME_ID);

    /**
     * Comment for <code>DESCRIPTION_ICON</code>
     */
    public static final Icon DESCRIPTION_ICON = getIcon(DESCRIPTION_ICON_ID);

    /**
     * Comment for <code>GOAL_ICON</code>
     */
    public static final Icon GOAL_ICON = getIcon(GOAL_ICON_ID);

    /**
     * Comment for <code>ALTERNATIVE_ICON</code>
     */
    public static final Icon ALTERNATIVE_ICON = getIcon(ALTERNATIVE_ICON_ID);

    /**
     * Comment for <code>ACTION_ICON</code>
     */
    public static final Icon ACTION_ICON = getIcon(ACTION_ICON_ID);

    /**
     * Comment for <code>COMPLETION_EMPTY_ICON</code>
     */
    public static final Icon COMPLETION_EMPTY_ICON = getIcon(COMPLETION_EMPTY_ICON_ID);

    /**
     * Comment for <code>COMPLETION_HALF_ICON</code>
     */
    public static final Icon COMPLETION_HALF_ICON = getIcon(COMPLETION_HALF_ICON_ID);

    /**
     * Comment for <code>COMPLETION_FULL_ICON</code>
     */
    public static final Icon COMPLETION_FULL_ICON = getIcon(COMPLETION_FULL_ICON_ID);

    // Helper Code ************************************************************

    private static Icon getIcon(String id) {
        return ResourceUtils.getIcon(id);
    }

    private static String getString(String id) {
        return ResourceUtils.getString(id);
    }

}