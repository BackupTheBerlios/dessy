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
 * $Id: ProjectFactory.java,v 1.5 2004/10/05 11:00:30 moleman Exp $
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
 * @version $Revision: 1.5 $
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
        
        Goal gAvail=createGoal("Availability");
        gAvail.setIntensity("März - August");
        gAvail.setDescription("Das Projekt hat diese Laufzeit. Wegen des Mythical-Man-Month-Effekts ist es zu vermeiden, die Entwickler während der Laufzeit auszutauschen. Es ist also zwingend, dass die einzustellende Person über den gesamten Zeitraum hinweg verfügbar ist.");
        
        Goal gKnowl=createGoal("Knowledge");
        gKnowl.setIntensity("Java");
        gKnowl.setDescription("Java als plattformunabhängige Sprache ist unumgänglich. Wegen der Nähe zu C# können die Programmierer bei Bedarf schnell umgelernt werden.");
        
        Goal gSalar=createGoal("Salary");
        gSalar.setIntensity("<= 10.000");
        gSalar.setDescription("In Indien kostet ein Entwickler noch weniger. Es ist im Zuge der Standortsicherung dem Entwickler zuzumuten, über die gesamte Laufzeit hinweg nicht mehr als diesen betrag zu kosten.");
        
        project.addGoal(gAvail);
        project.addGoal(gKnowl);
        project.addGoal(gSalar);
        
        Alternative aKlaus=createAlternative("Klaus");
        
        
        Alternative aPeter=createAlternative("Peter");
         

        project.addAlternative(aKlaus);
        project.addAlternative(aPeter); 
        
        aKlaus.putIntensity(gAvail,"Januar - März");
        aKlaus.putIntensity(gKnowl,"C++");
        aKlaus.putIntensity(gSalar,"11.000");
        aKlaus.setDescription("Klaus ist ein eifriger Programmierer mit dem wir schon in früheren Projekten gerne zusammen gearbeitet haben. Obwohl er kein Java kann, kann man ihn wegen seiner objektorientierten C++-Kenntnisse schnell umschulen.");
        
        aPeter.putIntensity(gAvail,"Juli");
        aPeter.putIntensity(gKnowl,"Pascal, Java");
        aPeter.putIntensity(gSalar,"12.000");  
        aPeter.setDescription("Peter kennen wir nicht näher.");
        
        Action acKlausAvail = project.getActionContainer().getActionFor(gAvail,aKlaus);
        Action acPeterAvail= project.getActionContainer().getActionFor(gAvail,aPeter);
        Action acKlausKnowl= project.getActionContainer().getActionFor(gKnowl,aKlaus);
        Action acPeterKnowl= project.getActionContainer().getActionFor(gKnowl,aPeter);
        Action acKlausSalar= project.getActionContainer().getActionFor(gSalar,aKlaus);
        Action acPeterSalar= project.getActionContainer().getActionFor(gSalar,aPeter);
        
        acKlausAvail.setName("verhandeln");
        acKlausAvail.setResult("Januar - September");
        acKlausAvail.setPaymentForAction(new Integer(250));
        
        acPeterAvail.setName("verhandeln");
        acPeterAvail.setResult("März - August");
        acPeterAvail.setPaymentForAction(new Integer(25));
        
        acKlausKnowl.setName("Schulung");
        acKlausKnowl.setResult("C++, Java");
        acKlausKnowl.setPaymentForAction(new Integer(1500));
        
        acPeterKnowl.setName("keine Notwendig");
        acPeterKnowl.setResult("Pascal, Java");
        acPeterKnowl.setPaymentForAction(new Integer(0));
        
        acKlausSalar.setName("Verhandeln");
        acKlausSalar.setResult("9500");
        acKlausSalar.setPaymentForAction(new Integer(0));
        acKlausSalar.setPaymentForTradeoff(new Integer(500));
        
        acPeterSalar.setName("Verhandeln");
        acPeterSalar.setResult("10000");
        acPeterSalar.setPaymentForAction(new Integer(0));   
        
    

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
        description.setIdentifier("Beispielprojekt");
        description.setDescription("Wen stelle ich ein?");
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