/*
 * AlternativeContainerNode.java
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
 * $Id: AlternativeContainerNode.java,v 1.4 2004/08/14 16:43:35 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.tool.node;

import javax.swing.Icon;

import com.jgoodies.uif.util.CompoundIcon;

import de.uniessen.wiinf.wip.goalgetter.domain.FillLevel;
import de.uniessen.wiinf.wip.goalgetter.domain.container.AlternativeContainer;
import de.uniessen.wiinf.wip.goalgetter.tool.Resources;

/**
 * 
 * AlternativeContainerNode
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.4 $
 *  
 */
public final class AlternativeContainerNode extends AbstractTreeNode {

    /**
     * Constructor
     * 
     * @param parent
     *            parent node
     * @param alternativecontainer
     *            Container to wich the node belongs
     */
    public AlternativeContainerNode(NavigationNode parent,
            AlternativeContainer alternativecontainer) {
        super(parent, alternativecontainer);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.tool.node.NavigationNode#getName()
     */
    public String getName() {
        return getAlternativeContainer().getIdentifier();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.tool.node.NavigationNode#getIcon(boolean)
     */
    public Icon getIcon(boolean sel) {
        return new CompoundIcon(Resources.ALTERNATIVE_ICON, FillLevel
                .getIcon(getAlternativeContainer().getFillLevel()));
    }

    /**
     * Returns this node's associated AlternativeContainer instance. Convenience
     * function for {@link NavigationNode#getModel()}with the respective
     * typecast
     * 
     * @return this node's associated AlternativeContainer instance.
     * @see NavigationNode#getModel()
     */
    public AlternativeContainer getAlternativeContainer() {
        return (AlternativeContainer) getModel();
    }
}