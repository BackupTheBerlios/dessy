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
 * $Id: AlternativeContainerNode.java,v 1.1 2004/07/03 20:17:08 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.tool.node;

import javax.swing.Icon;

import de.uniessen.wiinf.wip.goalgetter.domain.AlternativeContainer;
import de.uniessen.wiinf.wip.goalgetter.tool.Resources;

/**
 * 
 * AlternativeContainerNode
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public final class AlternativeContainerNode extends AbstractTreeNode {

    /**
     * Constructor
     * 
     * @param parent
     * @param goal
     */
    public AlternativeContainerNode(NavigationNode parent,
            AlternativeContainer alternativecontainer) {
        super(parent, alternativecontainer);
    }

    /**
     *  
     */
    public String getName() {
        return getAlternativeContainer().getIdentifier();
    }

    /**
     *  
     */
    public Icon getIcon(boolean sel) {
        return Resources.ALTERNATIVE_ICON;
    }

    /**
     * 
     * @return
     */
    public AlternativeContainer getAlternativeContainer() {
        return (AlternativeContainer) getModel();
    }
}