/*
 * RootNode.java
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
 * $Id: RootNode.java,v 1.3 2004/07/19 18:22:02 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.tool.node;

import java.util.Iterator;

import javax.swing.Icon;

import de.uniessen.wiinf.wip.goalgetter.domain.Action;
import de.uniessen.wiinf.wip.goalgetter.domain.Alternative;
import de.uniessen.wiinf.wip.goalgetter.domain.Goal;
import de.uniessen.wiinf.wip.goalgetter.domain.Project;

/**
 * 
 * Describes the root node in the GoalGetter navigation tree.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.3 $
 *  
 */
public final class RootNode extends AbstractTreeNode {

    // Instance Creation ******************************************************

    /**
     * Creates a root node for the specified project.
     * 
     * @param project
     *            the associated project
     */
    public RootNode(Project project) {
        super(null, project);
        buildNodesFrom(project);
    }

    // Building the Tree ******************************************************

    /**
     * Builds nodes from the specified <code>Project</code>.
     * 
     * @param project
     *            the project to build the tree from
     */
    private void buildNodesFrom(Project project) {

        add(new DescriptionNode(this, project.getDescription()));

        GoalContainerNode masterGoalNode = new GoalContainerNode(this, project
                .getGoalContainer());
        for (Iterator i = project.getGoals().iterator(); i.hasNext();) {
            Goal goal = (Goal) i.next();
            GoalNode node = new GoalNode(masterGoalNode, goal);
            masterGoalNode.add(node);
        }
        add(masterGoalNode);

        AlternativeContainerNode masterAlternativeNode = new AlternativeContainerNode(
                this, project.getAlternativeContainer());
        for (Iterator i = project.getAlternatives().iterator(); i.hasNext();) {
            Alternative alternative = (Alternative) i.next();
            System.out.println(alternative.getIdentifier());
            AlternativeNode node = new AlternativeNode(masterAlternativeNode,
                    alternative);
            masterAlternativeNode.add(node);
        }
        add(masterAlternativeNode);

        ActionContainerNode masterActionNode = new ActionContainerNode(this,
                project.getActionContainer());

        ActionContainerNode actionByGoalNode = new ActionContainerNode(
                masterActionNode, project.getActionsbyGoalContainer());
        for (Iterator i = project.getGoalContainer().getGoals()
                .iterator(); i.hasNext();) {
            Goal goal= (Goal) i.next();
            ActionGoalNode node = new ActionGoalNode(actionByGoalNode, goal);
            actionByGoalNode.add(node);
        }

        ActionContainerNode actionByAlternativeNode = new ActionContainerNode(
                masterActionNode, project.getActionsbyAlternativeContainer());
        for (Iterator i = project.getAlternativeContainer()
                .getAlternatives().iterator(); i.hasNext();) {
            Alternative alternative = (Alternative) i.next();
            ActionAlternativeNode node = new ActionAlternativeNode(actionByAlternativeNode, alternative);
            actionByAlternativeNode.add(node);
        }

        masterActionNode.add(actionByGoalNode);
        masterActionNode.add(actionByAlternativeNode);

        add(masterActionNode);

    }

    // ************************************************************************

    /**
     * Returns this node's associated Description instance.
     * 
     * @return this node's associated Description instance.
     * @see NavigationNode#getModel()
     */
    public Project getProject() {
        return (Project) getModel();
    }

    /**
     * Returns this node's icon. Since the root node will be hidden by the tree,
     * we can return <code>null</code>.
     * 
     * @return null
     */
    public Icon getIcon(boolean sel) {
        return null;
    }

    /**
     * Returns this node's name. Since the root node will be hidden by the tree,
     * we can return <code>null</code>.
     * 
     * @return null
     */
    public String getName() {
        return null;
    }

    /**
     * Returns a string representation for this node. Currently it prints the
     * class info and node name.
     * 
     * @return a string representation for this node
     */
    public String toString() {
        return super.toString() + ':' + getProject().toString();
    }

}