/*
 * ProjectFactory.java
 * Package: de.uniessen.wiinf.wip.goalgetter.domain
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
 * $Id: ProjectFactory.java,v 1.4 2004/10/05 10:11:38 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.domain;

/**
 * 
 * A factory that can create a sample project with a sample description, some
 * goals and alternatives and actions.
 * <p>
 * is currently capable of either returning a full featured sample project or a
 * project stub for quickstart.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.4 $
 *  
 */
public final class ProjectFactory {

    private ProjectFactory() {
        // Overrides default constructor; prevents instantiation.
    }

    /**
     * Creates and returns a sample data model with sample components.
     * 
     * @return a sample data model with sample components.
     */
    public static Project createSample() {
        Project project = new Project("Unnamed Project");
        fillDescription(project.getDescription());

        project.addGoal(createGoal("Availability"));
        project.addGoal(createGoal("Knowledge"));
        project.addGoal(createGoal("Salary"));

        project.addAlternative(createAlternative("Klaus")); //$NON-NLS-1$
        project.addAlternative(createAlternative("Peter")); //$NON-NLS-1$

        return project;
    }

    /**
     * Creates an initially empty project containing just stubs for a goal,
     * alternative (and thus also action). None of the field values are filled.
     * 
     * @return project with stubs
     */
    public static Project createEmpty() {
        Project project = new Project("Unnamed Project");
        project.addGoal(createGoal("Goal 1"));
        project.addAlternative(createAlternative("Alternative 1"));
        project.getDescription().setIdentifier("Project description");
        return project;
    }

    /**
     * Sets sample data in the given description.
     * 
     * @param description
     *            the description to fill
     */
    private static void fillDescription(Description description) {
        description.setIdentifier("Sample Project");
        description.setDescription("Description for the project.");
    }

    /**
     * Creates and returns a sample goal with the specified identifier.
     * 
     * @param identifier
     *            the identifier for the goal
     * @return instantiated Goal
     */
    private static Goal createGoal(String identifier) {
        Goal g = new Goal(identifier);
        g.setIntensity("1000");
        return g;
    }

    /**
     * Creates and returns a sample alternative with the specified identifier.
     * 
     * @param identifier
     *            the identifier for the Alternative
     * @return instantiated Alternative
     */
    private static Alternative createAlternative(String identifier) {
        return new Alternative(identifier);
    }

}