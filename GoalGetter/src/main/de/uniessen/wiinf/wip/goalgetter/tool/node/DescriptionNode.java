/*
 * DescriptionNode.java
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
 * $Id: DescriptionNode.java,v 1.1 2004/07/03 20:17:08 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.tool.node;

import javax.swing.Icon;

import de.uniessen.wiinf.wip.goalgetter.domain.Description;
import de.uniessen.wiinf.wip.goalgetter.tool.Resources;

/**
 * 
 * This class represents the project description in the navigation tree.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public final class DescriptionNode extends AbstractTreeNode {

    // Instance Creation ******************************************************

    /**
     * Constructs a <code>DescriptionNode</code> for the given parent and
     * description.
     * 
     * @param parent
     *            this node's parent
     * @param description
     *            the associated model, a description instance
     */
    public DescriptionNode(NavigationNode parent, Description description) {
        super(parent, description);
    }

    // Implementing the NavigationNode Interface ******************************

    /**
     * Returns this node's name, a constant requested from the global resource
     * repository.
     * 
     * @return this node's name
     */
    public String getName() {
        return Resources.DESCRIPTION_NAME;
    }

    // ************************************************************************

    /**
     * Returns this node's icon, ignores the selection. The icons is requested
     * from a global resource repository.
     * 
     * @return this description node's icon.
     */
    public Icon getIcon(boolean sel) {
        return Resources.DESCRIPTION_ICON;
    }

    /**
     * Returns this node's associated Description instance.
     * 
     * @return this node's associated Description instance.
     * @see NavigationNode#getModel()
     */
    public Description getDescription() {
        return (Description) getModel();
    }

}