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
 * $Id: RootNode.java,v 1.9 2004/09/08 18:31:34 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.tool.node;

import java.util.Iterator;

import javax.swing.Icon;

import de.uniessen.wiinf.wip.goalgetter.domain.Alternative;
import de.uniessen.wiinf.wip.goalgetter.domain.Goal;
import de.uniessen.wiinf.wip.goalgetter.domain.Project;
import de.uniessen.wiinf.wip.goalgetter.tool.ActionContainerByAlternative;
import de.uniessen.wiinf.wip.goalgetter.tool.PresentationSettings;

/**
 * 
 * Describes the root node in the GoalGetter navigation tree.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.9 $
 *  
 */
public final class RootNode extends AbstractTreeNode {

    private PresentationSettings presentationSettings = null;

    // Instance Creation ******************************************************

    /**
     * Constructs a root node for the specified project.
     * 
     * @param project
     *            the associated project
     * @param presentationSettings
     *            the PresentationSettings (containing tree construction rules)
     */
    public RootNode(Project project, PresentationSettings presentationSettings) {
        super(null, project);
        this.presentationSettings = presentationSettings;
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
        Iterator i;
        add(new DescriptionNode(this, project.getDescription()));

        GoalContainerNode masterGoalNode = new GoalContainerNode(this, project
                .getGoalContainer());
        for (i = project.getGoals().iterator(); i.hasNext();) {
            Goal goal = (Goal) i.next();
            GoalNode node = new GoalNode(masterGoalNode, goal);
            masterGoalNode.add(node);
        }
        add(masterGoalNode);

        AlternativeContainerNode masterAlternativeNode = new AlternativeContainerNode(
                this, project.getAlternativeContainer());

        i = project.getAlternatives().iterator();

        while (i.hasNext()) {
            Alternative alternative = (Alternative) i.next();
            AlternativeNode node = new AlternativeNode(masterAlternativeNode,
                    alternative);
            masterAlternativeNode.add(node);

        }

        add(masterAlternativeNode);

        ActionContainerNode masterActionNode;
        //TODO decide whether action display types should be kept.
        //        if (false && presentationSettings != null
        //                && presentationSettings.getActionPresentationMode() ==
        // ActionPresentationMode.GOAL) {
        //
        //            masterActionNode = new ActionContainerNode(this, project
        //                    .getActionContainer());
        //            for ( i = project.getGoalContainer().getGoals().iterator(); i
        //                    .hasNext();) {
        //                Goal goal = (Goal) i.next();
        //                ActionContainerByGoal acbg = new
        // ActionContainerByGoal(project.getActionContainer(),goal);
        //                ActionGoalNode node = new ActionGoalNode(masterActionNode, acbg);
        //                masterActionNode.add(node);
        //            }
        //        } else {
        
        masterActionNode = new ActionContainerNode(this, project
                .getActionContainer());

        i = project.getAlternativeContainer().getAlternatives().iterator();

        while (i.hasNext()) {

            Alternative alternative = (Alternative) i.next();
            ActionContainerByAlternative acba = new
            ActionContainerByAlternative(project.getActionContainer(),alternative);
            ActionAlternativeNode node = new ActionAlternativeNode(
                    masterActionNode, acba);
            masterActionNode.add(node);
        }
        // }

        add(masterActionNode);

    }

    // ************************************************************************

    /**
     * Returns this node's associated Goal Project. Convenience function for
     * {@link NavigationNode#getModel()}with the respective typecast
     * 
     * @return this node's associated Project instance.
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