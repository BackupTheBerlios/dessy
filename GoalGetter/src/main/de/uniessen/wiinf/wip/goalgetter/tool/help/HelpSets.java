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
 * $Id: HelpSets.java,v 1.1 2004/07/03 20:17:08 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.tool.help;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import de.uniessen.wiinf.wip.goalgetter.domain.Action;
import de.uniessen.wiinf.wip.goalgetter.domain.ActionContainer;
import de.uniessen.wiinf.wip.goalgetter.domain.Alternative;
import de.uniessen.wiinf.wip.goalgetter.domain.AlternativeContainer;
import de.uniessen.wiinf.wip.goalgetter.domain.Description;
import de.uniessen.wiinf.wip.goalgetter.domain.Goal;
import de.uniessen.wiinf.wip.goalgetter.domain.GoalContainer;
import de.uniessen.wiinf.wip.goalgetter.tool.DynamicHelpModule;

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

    public static final TreeNode WELCOME_HELP_SET = createWelcomeHelpSet();

    public static final TreeNode DECISION_HELP_SET = createDecisionHelpSet();

    public static final TreeNode GOAL_HELP_SET = createGoalHelpSet();

    public static final TreeNode ALTERNATIVE_HELP_SET = createAlternativeHelpSet();

    public static final TreeNode ACTION_HELP_SET = createActionHelpSet();

    public static final TreeNode GOALCONTAINER_HELP_SET = createGoalContainerHelpSet();

    public static final TreeNode ALTERNATIVECONTAINER_HELP_SET = createAlternativeContainerHelpSet();

    public static final TreeNode ACTIONCONTAINER_HELP_SET = createActionContainerHelpSet();

    private static final String URL_PREFIX = "docs/help/dynamic/";

    private HelpSets() {
        // Suppresses default constructor, ensuring non-instantiability.
    }

    /**
     * Registers all help sets with the DynamicHelpModule.
     * 
     * @param helpModule
     *            holds a map from selection types to help sets
     */
    public static void registerHelpSets(DynamicHelpModule helpModule) {
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
     */
    private static TreeNode createWelcomeHelpSet() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        DefaultMutableTreeNode chapter;

        chapter = createChapter("Help");
        chapter.add(createTopic("Welcome", "welcome/welcome"));
        chapter.add(createTopic("How to use dynamic help", "welcome/help"));
        root.add(chapter);

        chapter = createChapter("Getting Started");
        chapter.add(createTopic("Create a new project", "welcome/new"));
        chapter.add(createTopic("Open existing project", "welcome/open"));
        root.add(chapter);

        return root;
    }

    /**
     * Creates and answers the decision help set.
     */
    private static TreeNode createDecisionHelpSet() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        DefaultMutableTreeNode chapter;

        chapter = createChapter("Help");
        chapter.add(createTopic("Decision", "decision/project"));
        chapter.add(createTopic("Identifier", "decision/identifier"));
        chapter.add(createTopic("Description", "decision/description"));
        root.add(chapter);

        return root;
    }

    /**
     * Creates and answers the goal help set.
     */
    private static TreeNode createGoalHelpSet() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        DefaultMutableTreeNode chapter;

        chapter = createChapter("Help");
        chapter.add(createTopic("Goal", "goal/general"));
        chapter.add(createTopic("Comments", "goal/comments"));
        root.add(chapter);

        return root;
    }

    /**
     * Creates and answers the alternative help set.
     */
    private static TreeNode createAlternativeHelpSet() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        DefaultMutableTreeNode chapter;

        chapter = createChapter("Help");
        chapter.add(createTopic("Alternative", "alternative/general"));
        root.add(chapter);

        return root;
    }

    /**
     * Creates and answers the action help set.
     */
    private static TreeNode createActionHelpSet() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        DefaultMutableTreeNode chapter;

        chapter = createChapter("Help");
        chapter.add(createTopic("Action", "action/general"));
        root.add(chapter);

        return root;
    }

    /**
     * Creates and answers the goalcontainer help set.
     */
    private static TreeNode createGoalContainerHelpSet() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        DefaultMutableTreeNode chapter;

        chapter = createChapter("Help");
        chapter.add(createTopic("Overview", "goalcontainer/general"));
        root.add(chapter);

        return root;
    }

    /**
     * Creates and answers the alternativecontainer help set.
     */
    private static TreeNode createAlternativeContainerHelpSet() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        DefaultMutableTreeNode chapter;

        chapter = createChapter("Help");
        chapter.add(createTopic("Overview", "alternativecontainer/general"));
        root.add(chapter);

        return root;
    }

    /**
     * Creates and answers the goalcontainer help set.
     */
    private static TreeNode createActionContainerHelpSet() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        DefaultMutableTreeNode chapter;

        chapter = createChapter("Help");
        chapter.add(createTopic("Overview", "actioncontainer/general"));
        root.add(chapter);

        return root;
    }

    /**
     * Creates and returns a topic node.
     */
    private static DefaultMutableTreeNode createTopic(String name, String path) {
        String fullPath = URL_PREFIX + path + ".html";
        return new DefaultMutableTreeNode(HelpNode.createTopic(name, fullPath));
    }

    /**
     * Creates and returns a chapter node.
     */
    private static DefaultMutableTreeNode createChapter(String name) {
        return new DefaultMutableTreeNode(HelpNode.createChapter(name));
    }

}