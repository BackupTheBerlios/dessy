/*
 * ActionContainerNode.java
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
 * $Id: ActionContainerNode.java,v 1.1 2004/09/25 14:56:57 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.model.node;

import javax.swing.Icon;

import de.uniessen.wiinf.wip.goalgetter.domain.container.ActionContainer;
import de.uniessen.wiinf.wip.goalgetter.model.Resources;

/**
 * 
 * ActionContainerNode
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
/**
 * ActionContainerNode
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public final class ActionContainerNode extends AbstractTreeNode {

    /**
     * Constructor
     * 
     * @param parent
     * @param actioncontainer
     */
    public ActionContainerNode(NavigationNode parent,
            ActionContainer actioncontainer) {
        super(parent, actioncontainer);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.tool.node.NavigationNode#getName()
     */
    public String getName() {
        return getActionContainer().getIdentifier();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.tool.node.NavigationNode#getIcon(boolean)
     */
    public Icon getIcon(boolean sel) {
        return Resources.ACTION_ICON;
    }

    /**
     * Returns this node's associated ActionContainer instance. Convenience
     * function for {@link NavigationNode#getModel()}with the respective
     * typecast
     * 
     * @return action container
     * @see AbstractTreeNode#getModel()
     */
    public ActionContainer getActionContainer() {
        return (ActionContainer) getModel();
    }
}