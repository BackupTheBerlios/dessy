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
 * $Id: ProjectFactory.java,v 1.2 2004/08/07 09:28:04 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.domain;

/**
 * 
 * A factory that can create a sample project with a sample description, some
 * goals and alternatives and actions.
 * <p>
 * This class is only for development purposes and may be removed after
 * completing the load and save mechanism
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.2 $
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

        project.addAlternative(createAlternative("Klaus"));
        project.addAlternative(createAlternative("Peter"));

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
        description.setDescription("Super Problem, super Problem.");
    }

    /**
     * Creates and returns a sample goal with the specified identifier.
     * 
     * @param identifier
     *            the identifier for the goal
     * @return instantiated Goal
     */
    private static Goal createGoal(String identifier) {
        return new Goal(identifier);
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

    /**
     * Creates and returns a sample action with the specified identifier.
     */
    //    private static Action createAction(Goal goal, Alternative alternative) {
    //        return new Action(goal, alternative);
    //    }
}