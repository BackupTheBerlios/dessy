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
 * $Id: AlternativeNode.java,v 1.3 2004/08/14 11:11:12 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.tool.node;

import javax.swing.Icon;

import com.jgoodies.uif.util.CompoundIcon;

import de.uniessen.wiinf.wip.goalgetter.domain.Alternative;
import de.uniessen.wiinf.wip.goalgetter.domain.FillLevel;
import de.uniessen.wiinf.wip.goalgetter.tool.Resources;

/**
 * 
 * AlternativeNode
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.3 $
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
     * @param alternative
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
        return new CompoundIcon(Resources.ALTERNATIVE_ICON, FillLevel
                .getIcon(getAlternative().getFillLevel()));
    }

    /**
     * Returns this node's associated Alternative instance. Convenience function
     * for {@link NavigationNode#getModel()}with the respective typecast
     * 
     * @return this node's associated Alternative instance.
     * @see NavigationNode#getModel()
     */
    public Alternative getAlternative() {
        return (Alternative) getModel();
    }

}