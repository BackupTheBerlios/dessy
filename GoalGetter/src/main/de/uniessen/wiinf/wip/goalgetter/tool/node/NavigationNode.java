/*
 * NavigationNode.java
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
 * $Id: NavigationNode.java,v 1.1 2004/07/03 20:17:08 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.tool.node;

import javax.swing.Icon;
import javax.swing.tree.TreeNode;

/**
 * 
 * This interface describes nodes in the navigation tree.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public interface NavigationNode extends TreeNode {

    /**
     * Returns this node's icon for the given selection state.
     * 
     * @return this node's icon for the given selection state
     */
    Icon getIcon(boolean selected);

    /**
     * Returns this node's name.
     * 
     * @return this node's name
     */
    String getName();

    /**
     * Returns this node's model.
     * 
     * @return this node's model
     */
    Object getModel();

}