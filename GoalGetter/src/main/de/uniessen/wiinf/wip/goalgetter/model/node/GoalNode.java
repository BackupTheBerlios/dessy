/*
 * GoalNode.java
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
 * $Id: GoalNode.java,v 1.1 2004/09/25 14:56:57 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.model.node;

import javax.swing.Icon;

import com.jgoodies.uif.util.CompoundIcon;

import de.uniessen.wiinf.wip.goalgetter.domain.FillLevel;
import de.uniessen.wiinf.wip.goalgetter.domain.Goal;
import de.uniessen.wiinf.wip.goalgetter.model.Resources;

/**
 * 
 * This class represents Goals in the navigation tree.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public final class GoalNode extends AbstractTreeNode {

    // Instance Creation ******************************************************

    /**
     * Constructs a <code>GoalNode</code> for the specified parent and goal.
     * 
     * @param parent
     *            this node's parent
     * @param goal
     *            the associated model, an instance of Goal
     */
    public GoalNode(NavigationNode parent, Goal goal) {
        super(parent, goal);
    }

    // Implementing the NavigationNode Interface ******************************

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.tool.node.NavigationNode#getName()
     */
    public String getName() {
        return getGoal().getName();
    }

    // ************************************************************************

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.tool.node.NavigationNode#getIcon(boolean)
     */
    public Icon getIcon(boolean sel) {
        return new CompoundIcon(Resources.GOAL_ICON, FillLevel
                .getIcon(getGoal().getFillLevel()));
        // return Resources.GOAL_ICON;
    }

    /**
     * Returns this node's associated Goal instance. Convenience function for
     * {@link NavigationNode#getModel()}with the respective typecast
     * 
     * @return this node's associated Goal instance.
     * @see NavigationNode#getModel()
     */
    public Goal getGoal() {
        return (Goal) getModel();
    }

}