/*
 * AlternativeNode.java
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
 * $Id: AlternativeNode.java,v 1.1 2004/07/03 20:17:08 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.tool.node;

import javax.swing.Icon;

import de.uniessen.wiinf.wip.goalgetter.domain.Alternative;
import de.uniessen.wiinf.wip.goalgetter.tool.Resources;

/**
 * 
 * AlternativeNode
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public final class AlternativeNode extends AbstractTreeNode {

    // Instance Creation ******************************************************

    /**
     * Constructs a <code>AlternativeNode</code> for the specified parent and
     * alternative.
     * 
     * @param parent
     *            this node's parent
     * @param segment
     *            the associated model, an instance of Alternative
     */
    public AlternativeNode(NavigationNode parent, Alternative alternative) {
        super(parent, alternative);
    }

    // Implementing the NavigationNode Interface ******************************

    /**
     * Returns this node's name, the identifier of the associated alternative.
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
        return Resources.ALTERNATIVE_ICON;
    }

    /**
     * Returns this node's associated Alternative instance.
     * 
     * @return this node's associated Alternative instance.
     * @see NavigationNode#getModel()
     */
    public Alternative getAlternative() {
        return (Alternative) getModel();
    }

}