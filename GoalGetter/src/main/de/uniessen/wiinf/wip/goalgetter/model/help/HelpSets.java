/*
 * HelpNode.java
 * Package: de.uniessen.wiinf.wip.goalgetter.tool.help
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
 * $Id: HelpSets.java,v 1.1 2004/09/25 14:56:57 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.model.help;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import de.uniessen.wiinf.wip.goalgetter.domain.Action;
import de.uniessen.wiinf.wip.goalgetter.domain.Alternative;
import de.uniessen.wiinf.wip.goalgetter.domain.Description;
import de.uniessen.wiinf.wip.goalgetter.domain.Goal;
import de.uniessen.wiinf.wip.goalgetter.domain.container.ActionContainer;
import de.uniessen.wiinf.wip.goalgetter.domain.container.AlternativeContainer;
import de.uniessen.wiinf.wip.goalgetter.domain.container.GoalContainer;
import de.uniessen.wiinf.wip.goalgetter.model.DynamicHelpModel;

/**
 * 
 * HelpSets
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public final class HelpSets {

    /**
     * Holds the <code>WELCOME_HELP_SET</code>
     */
    public static final TreeNode WELCOME_HELP_SET = createWelcomeHelpSet();

    /**
     * Holds the <code>DECISION_HELP_SET</code>
     */
    public static final TreeNode DECISION_HELP_SET = createDecisionHelpSet();

    /**
     * Holds the <code>GOAL_HELP_SET</code>
     */
    public static final TreeNode GOAL_HELP_SET = createGoalHelpSet();

    /**
     * Holds the <code>ALTERNATIVE_HELP_SET</code>
     */
    public static final TreeNode ALTERNATIVE_HELP_SET = createAlternativeHelpSet();

    /**
     * Holds the <code>ACTION_HELP_SET</code>
     */
    public static final TreeNode ACTION_HELP_SET = createActionHelpSet();

    /**
     * Holds the <code>GOALCONTAINER_HELP_SET</code>
     */
    public static final TreeNode GOALCONTAINER_HELP_SET = createGoalContainerHelpSet();

    /**
     * Holds the <code>ALTERNATIVECONTAINER_HELP_SET</code>
     */
    public static final TreeNode ALTERNATIVECONTAINER_HELP_SET = createAlternativeContainerHelpSet();

    /**
     * Comment for <code>ACTIONCONTAINER_HELP_SET</code>
     */
    public static final TreeNode ACTIONCONTAINER_HELP_SET = createActionContainerHelpSet();

    /**
     * Comment for <code>URL_PREFIX</code>
     */
    private static final String URL_PREFIX = "docs/help/dynamic/";//$NON-NLS-1$

    private HelpSets() {
        // Suppresses default constructor, ensuring non-instantiability.
    }

    /**
     * Registers all help sets with the DynamicHelpModule.
     * 
     * @param helpModule
     *            holds a map from selection types to help sets
     */
    public static void registerHelpSets(DynamicHelpModel helpModule) {
        helpModule.registerHelp(Description.class, DECISION_HELP_SET);
        helpModule.registerHelp(Goal.class, GOAL_HELP_SET);
        helpModule.registerHelp(Alternative.class, ALTERNATIVE_HELP_SET);
        helpModule.registerHelp(Action.class, ACTION_HELP_SET);
        helpModule.registerHelp(GoalContainer.class, GOALCONTAINER_HELP_SET);
        helpModule.registerHelp(AlternativeContainer.class,
                ALTERNATIVECONTAINER_HELP_SET);
        helpModule
                .registerHelp(ActionContainer.class, ACTIONCONTAINER_HELP_SET);
    }

    /**
     * Creates and answers the welcome help set.
     * 
     * @return welcome help set
     */
    private static TreeNode createWelcomeHelpSet() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        DefaultMutableTreeNode chapter;

        chapter = createChapter("Help");
        chapter.add(createTopic("Welcome", "welcome/welcome")); //$NON-NLS-2$
        chapter.add(createTopic("How to use dynamic help", "welcome/help"));//$NON-NLS-2$
        root.add(chapter);

        chapter = createChapter("Getting Started");
        chapter.add(createTopic("Create a new project", "welcome/new"));//$NON-NLS-2$
        chapter.add(createTopic("Open existing project", "welcome/open"));//$NON-NLS-2$
        root.add(chapter);

        return root;
    }

    /**
     * Creates and answers the decision help set.
     * 
     * @return decision help set
     */
    private static TreeNode createDecisionHelpSet() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        DefaultMutableTreeNode chapter;

        chapter = createChapter("Help");
        chapter.add(createTopic("Decision", "decision/project"));//$NON-NLS-2$
        chapter.add(createTopic("Identifier", "decision/identifier"));//$NON-NLS-2$
        chapter.add(createTopic("Description", "decision/description"));//$NON-NLS-2$
        root.add(chapter);

        return root;
    }

    /**
     * Creates and answers the goal help set.
     * 
     * @return goal help set
     */
    private static TreeNode createGoalHelpSet() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        DefaultMutableTreeNode chapter;

        chapter = createChapter("Help");
        chapter.add(createTopic("Goal", "goal/general"));//$NON-NLS-2$
        chapter.add(createTopic("Comments", "goal/comments"));//$NON-NLS-2$
        root.add(chapter);

        return root;
    }

    /**
     * Creates and answers the alternative help set.
     * 
     * @return alternative help set
     */
    private static TreeNode createAlternativeHelpSet() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        DefaultMutableTreeNode chapter;

        chapter = createChapter("Help");
        chapter.add(createTopic("Alternative", "alternative/general"));//$NON-NLS-2$
        root.add(chapter);

        return root;
    }

    /**
     * Creates and answers the action help set.
     * 
     * @return action help set
     */
    private static TreeNode createActionHelpSet() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        DefaultMutableTreeNode chapter;

        chapter = createChapter("Help");
        chapter.add(createTopic("Action", "action/general"));//$NON-NLS-2$
        root.add(chapter);

        return root;
    }

    /**
     * Creates and answers the goalcontainer help set.
     * 
     * @return goalcontainer help set
     */
    private static TreeNode createGoalContainerHelpSet() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        DefaultMutableTreeNode chapter;

        chapter = createChapter("Help");
        chapter.add(createTopic("Overview", "goalcontainer/general"));//$NON-NLS-2$
        root.add(chapter);

        return root;
    }

    /**
     * Creates and answers the alternativecontainer help set.
     * 
     * @return alternativecontainer help set
     */
    private static TreeNode createAlternativeContainerHelpSet() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        DefaultMutableTreeNode chapter;

        chapter = createChapter("Help");
        chapter.add(createTopic("Overview", "alternativecontainer/general"));//$NON-NLS-2$
        root.add(chapter);

        return root;
    }

    /**
     * Creates and answers the actioncontainer help set.
     * 
     * @return actioncontainer help set
     */
    private static TreeNode createActionContainerHelpSet() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        DefaultMutableTreeNode chapter;

        chapter = createChapter("Help");
        chapter.add(createTopic("Overview", "actioncontainer/general"));//$NON-NLS-2$
        root.add(chapter);

        return root;
    }

    /**
     * Creates and returns a topic node.
     * 
     * @param name
     *            name for the topic to create
     * @param path
     *            path for the topic to create
     * @return topic
     */
    private static DefaultMutableTreeNode createTopic(String name, String path) {
        String fullPath = URL_PREFIX + path + ".html";//$NON-NLS-1$
        return new DefaultMutableTreeNode(HelpNode.createTopic(name, fullPath));
    }

    /**
     * Creates and returns a chapter node.
     * 
     * @param name
     *            name for the chapter to create
     * @return chapter
     */
    private static DefaultMutableTreeNode createChapter(String name) {
        return new DefaultMutableTreeNode(HelpNode.createChapter(name));
    }

}