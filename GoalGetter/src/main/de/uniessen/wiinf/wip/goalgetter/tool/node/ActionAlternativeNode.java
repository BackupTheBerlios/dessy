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
 * $Id: ActionAlternativeNode.java,v 1.1 2004/07/19 18:22:02 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.tool.node;

import javax.swing.Icon;

import de.uniessen.wiinf.wip.goalgetter.domain.Alternative;
import de.uniessen.wiinf.wip.goalgetter.tool.Resources;

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
public final class ActionAlternativeNode extends AbstractTreeNode {

    // Instance Creation ******************************************************

    /**
     * Constructs a <code>ShaftNode</code> for the given parent and action.
     * 
     * @param parent
     *            this node's parent
     * @param shaft
     *            the associated model, an instance of Action
     */
    public ActionAlternativeNode(NavigationNode parent, Alternative alternative) {
        super(parent, alternative);
    }

    // Implementing the NavigationNode Interface ******************************

    /**
     * Returns this node's name, the identifier of the associated action.
     * 
     * @return this node's name
     */
    public String getName() {
        return getAlternative().getIdentifier();
    }

    // ************************************************************************

    /**
     * Returns this node's icon, ignores the selection. The icons is requested
     * from a global resource repository.
     * 
     * @return this node's icon.
     */
    public Icon getIcon(boolean sel) {
        return Resources.ACTION_ICON;
    }

    /**
     * Returns this node's associated Action instance.
     * 
     * @return this node's associated Action instance.
     * @see NavigationNode#getModel()
     */
    public Alternative getAlternative() {
        return (Alternative) getModel();
    }

}