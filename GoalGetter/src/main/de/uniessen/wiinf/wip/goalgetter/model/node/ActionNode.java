/*
 * ActionNode.java
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
 * $Id: ActionNode.java,v 1.1 2004/09/25 14:56:57 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.model.node;

import javax.swing.Icon;

import de.uniessen.wiinf.wip.goalgetter.model.ActionContainerByAlternative;
import de.uniessen.wiinf.wip.goalgetter.model.Resources;

/**
 * 
 * ActionNode
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public final class ActionNode extends AbstractTreeNode {

    // Instance Creation ******************************************************

    /**
     * Constructs a <code>ShaftNode</code> for the given parent and action.
     * 
     * @param parent
     *            this node's parent
     * @param actionContainer
     *            the associated model, an instance of ActionContainer
     */
    public ActionNode(NavigationNode parent, ActionContainerByAlternative actionContainer) {
        super(parent, actionContainer);
    }

    // Implementing the NavigationNode Interface ******************************

    /**
     * Returns this node's name, the identifier of the associated action.
     * 
     * @return this node's name
     */
    public String getName() {
        return getAlternatives().getName();
    }

    // ************************************************************************

    /**
     * Returns this node's icon, ignores the selection. The icons is requested
     * from a global resource repository.
     * 
     * @return this node's icon.
     */
    public Icon getIcon(boolean sel) {
//        return new CompoundIcon(Resources.ACTION_ICON, FillLevel
//                .getIcon(getAlternatives().getFillLevel()));
        return Resources.ACTION_ICON;
    }

    
    /**
     * Returns this node's associated Action instance. Convenience function for
     * {@link NavigationNode#getModel()}with the respective typecast
     * 
     * @return this node's associated Action instance.
     * @see NavigationNode#getModel()
     */
    public ActionContainerByAlternative getAlternatives() {
        return (ActionContainerByAlternative) getModel();
    }

}