/*
 * Project.java
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
 * $Id: Project.java,v 1.5 2004/08/15 15:13:34 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.domain;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.jgoodies.binding.beans.Model;

import de.uniessen.wiinf.wip.goalgetter.domain.container.ActionContainer;
import de.uniessen.wiinf.wip.goalgetter.domain.container.AlternativeContainer;
import de.uniessen.wiinf.wip.goalgetter.domain.container.GoalContainer;

/**
 * 
 * References all relevant project data
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.5 $
 *  
 */
public final class Project extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * Refers to <code>Description</code> object that describes this project.
     */
    private Description description;

    /**
     * Holds a list of goals
     */
    private GoalContainer goalContainer;

    /**
     * Holds a list of alternatives
     */
    private AlternativeContainer alternativeContainer;

    /**
     * Holds a list of actions
     */
    private ActionContainer actionContainerByGoal;

    private ActionContainer actionContainerByAlternative;

    /**
     * The file used to save this project to.
     */
    private File file;

    // Instance Creation ******************************************************

    public Project() {
        this("New Project");
    }

    /**
     * Constructs a <code>Project</code> with the given name and and empty
     * decision component lists.
     * 
     * @param name
     *            the project's name
     */
    public Project(String name) {
        this.description = new Description(name);
        this.goalContainer = new GoalContainer("Goals");
        this.alternativeContainer = new AlternativeContainer("Alternatives");
        this.actionContainerByGoal = new ActionContainer("Actions by Goal");
        this.actionContainerByAlternative = new ActionContainer(
                "Actions by Alternative");
    }

    // The public API *********************************************************

    /**
     * Answers the Project's name
     * 
     * @return name
     */
    public String getName() {
        return getDescription().getIdentifier();
    }

    /**
     * Answers the Project's description
     * 
     * @return description
     */
    public Description getDescription() {
        return description;
    }

    // Managing Goals ********************************************************

    /**
     * Returns an unmodifiable list of this project's goals.
     * 
     * @return an unmodifiable list of this project's goals.
     */
    public List getGoals() {
        return Collections.unmodifiableList(goalContainer.getGoals());
    }

    /**
     * Adds the given goal to this project's goals.
     * 
     * @param goal
     *            the goal to add
     */
    public void addGoal(Goal goal) {
        goalContainer.addGoal(goal);
        Iterator iterator = alternativeContainer.getAlternatives().iterator();
        while (iterator.hasNext()) {
            Alternative a = (Alternative) iterator.next();
            a.putIntensity(goal, goal.getIntensity());
        }

    }

    /**
     * Removes the given goal from this project's goals.
     * 
     * @param goal
     *            the goal to remove
     */
    public void removeGoal(Goal goal) {
        goalContainer.removeGoal(goal.getIdentifier());

        Iterator iterator = alternativeContainer.getAlternatives().iterator();
        while (iterator.hasNext()) {
            Alternative a = (Alternative) iterator.next();
            a.removeIntensity(goal);
        }

    }

    /**
     * Returns the Project's GoalContainer instance
     * 
     * @return GoalContainer
     */
    public GoalContainer getGoalContainer() {
        return goalContainer;
    }

    // Managing Alternatives
    // ********************************************************

    /**
     * Returns an unmodifiable list of this project's alternatives.
     * 
     * @return an unmodifiable list of this project's alternatives.
     */
    public List getAlternatives() {
        return Collections.unmodifiableList(alternativeContainer
                .getAlternatives());
    }

    /**
     * Adds the given alternative to this project's alternatives.
     * 
     * @param alternative
     *            the alternative to add
     */
    public void addAlternative(Alternative alternative) {

        Iterator iterator = goalContainer.getGoals().iterator();
        while (iterator.hasNext()) {
            Goal g = (Goal) iterator.next();
            alternative.putIntensity(g, "");
            Action ac = new Action(g, alternative);
            actionContainerByGoal.addAction(ac);
            actionContainerByAlternative.addAction(ac);
        }
        alternativeContainer.addAlternative(alternative);

    }

    /**
     * Removes the given alternative from this project's alternatives.
     * 
     * @param alternative
     *            the alternative to remove
     */
    public void removeAlternative(Alternative alternative) {
        alternativeContainer.removeAlternative(alternative);
    }

    /**
     * Returns the Project's AlternativeContainer instance
     * 
     * @return AlternativeContainer
     */
    public AlternativeContainer getAlternativeContainer() {
        return alternativeContainer;
    }

    /**
     * @param alternativeContainer
     *            The alternativeContainer to set.
     */
    public void setAlternativeContainer(
            AlternativeContainer alternativeContainer) {
        this.alternativeContainer = alternativeContainer;
    }

    /**
     * @param goalContainer
     *            The goalContainer to set.
     */
    public void setGoalContainer(GoalContainer goalContainer) {
        this.goalContainer = goalContainer;
    }

    //  Managing Actions ********************************************************

    /**
     * Returns an unmodifiable list of this project's actions.
     * 
     * @return an unmodifiable list of this project's actions.
     */
    public List getActionsbyGoal() {
        return Collections.unmodifiableList(actionContainerByGoal.getActions());
    }

    public List getActionsbyAlternative() {
        return Collections.unmodifiableList(actionContainerByAlternative
                .getActions());
    }

    public ActionContainer getActionsbyAlternativeContainer() {
        return actionContainerByAlternative;
    }

    public ActionContainer getActionsbyGoalContainer() {
        return actionContainerByGoal;
    }

    //    /**
    //     * Adds the given alternative to this project's alternatives.
    //     *
    //     * @param alternative
    //     * the alternative to add
    //     */
    //    public void addAction(Action action) {
    //        actionContainer.addAction(action);
    //    }

    //    /**
    //     * Removes the given alternative from this project's alternatives.
    //     *
    //     * @param goal
    //     * the alternative to remove
    //     */
    //    public void removeAlternative(Action action) {
    //        actionContainer.removeAction(action);
    //    }

    /**
     * Returns the Project's ActionContainer instance
     * 
     * @return ActionContainer
     */
    public ActionContainer getActionContainer() {
        return new ActionContainer("Actions");
    }

    // Reading and Saving *****************************************************

    /**
     * Reads, creates and returns a Project from the given <code>File</code>.
     * 
     * @param f
     *            File to read from
     * @return project
     */
    public static Project readFrom(File f) {
        // TODO: make filename selection
        XMLDecoder dec;
        try {
            dec = new XMLDecoder(new BufferedInputStream(new FileInputStream(
                    f)));
            Object importObject = dec.readObject();
            dec.close();

            Project model = (Project) importObject;
            model.setFile(f);
            return model;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ProjectFactory.createSample();
    }

    /**
     * Saves this project to its associated <code>File</code>.
     */
    public void save() {
        if (!hasFile()) {
            throw new IllegalStateException("Can't save project without file.");
        }
        saveAs(file);
    }

    /**
     * Saves this project to the given <code>File</code>.
     * 
     * @param aFile
     *            the file to save to
     */
    public void saveAs(File aFile) {
        // TODO: make filename selection
        setFile(aFile);
        System.out.println(file);
        XMLEncoder enc;
        try {
            enc = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(
                    file)));
            enc.writeObject(this);
            enc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Checks and answers whether this project has an associated file as backing
     * store.
     * 
     * @return true if this project has an associated file
     */
    public boolean hasFile() {
        return file != null;
    }

    /**
     * Returns the name of this project's associated filename, or
     * &quote;Unsaved&quote; if it has no file associated.
     * 
     * @return this project's filename or a default if it has no file
     */
    public String getFileName() {
        return hasFile() ? file.getName() : "Unsaved";
    }

    /**
     * Sets a file as this project' backing store.
     * 
     * @param aFile
     *            the new associated file
     */
    private void setFile(File aFile) {
        this.file = aFile;
    }

    // Misc *******************************************************************

    /**
     * Returns a string representation for this project. Currently it prints the
     * class info and file name.
     * 
     * @return a string representation for this project
     */
    public String toString() {
        return super.toString() + ':' + getFileName();
    }

}