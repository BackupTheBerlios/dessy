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
 * $Id: Resources.java,v 1.1 2004/07/03 20:17:08 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.tool;

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

    public static final String DESCRIPTION_NAME_ID = "description.name";

    public static final String DESCRIPTION_ICON_ID = "description.icon";

    public static final String GOAL_ICON_ID = "goal.icon";

    public static final String ALTERNATIVE_ICON_ID = "alternative.icon";

    public static final String ACTION_ICON_ID = "action.icon";

    public static final String ARROW_ICON_ID = "arrow.icon";

    public static final String LOGO_ICON_ID = "logo.icon";

    public static final String HELP_CONTENTS_ICON_ID = "helpContents.icon";

    public static final String DEFAULT_PROJECT_NAME_ID = "defaultProject.name";

    // Resources **************************************************************

    public static final String DESCRIPTION_NAME = getString(DESCRIPTION_NAME_ID);

    public static final Icon DESCRIPTION_ICON = getIcon(DESCRIPTION_ICON_ID);

    public static final Icon GOAL_ICON = getIcon(GOAL_ICON_ID);

    public static final Icon ALTERNATIVE_ICON = getIcon(ALTERNATIVE_ICON_ID);

    public static final Icon ACTION_ICON = getIcon(ACTION_ICON_ID);

    // Helper Code ************************************************************

    private static Icon getIcon(String id) {
        return ResourceUtils.getIcon(id);
    }

    private static String getString(String id) {
        return ResourceUtils.getString(id);
    }

}