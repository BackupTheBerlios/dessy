/*
 * GoalContainerNode.java
 * Package: de.uniessen.wiinf.wip.goalgetter.tool.node
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
 * $Id: GoalContainerNode.java,v 1.1 2004/07/03 20:17:08 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.tool.node;

import javax.swing.Icon;

import de.uniessen.wiinf.wip.goalgetter.domain.GoalContainer;
import de.uniessen.wiinf.wip.goalgetter.tool.Resources;

/**
 * 
 * GoalContainerNode
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public final class GoalContainerNode extends AbstractTreeNode {

    /**
     * Constructor
     * 
     * @param parent
     * @param goal
     */
    public GoalContainerNode(NavigationNode parent, GoalContainer goalcontainer) {
        super(parent, goalcontainer);
    }

    /**
     *  
     */
    public String getName() {
        return getGoalContainer().getIdentifier();
    }

    /**
     *  
     */
    public Icon getIcon(boolean sel) {
        return Resources.GOAL_ICON;
    }

    /**
     * 
     * @return
     */
    public GoalContainer getGoalContainer() {
        return (GoalContainer) getModel();
    }
}