/*
 * GoalContainer.java
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
 * $Id: GoalContainer.java,v 1.4 2004/08/07 09:28:04 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.domain;

import java.util.List;

import com.jgoodies.binding.beans.Model;
import com.jgoodies.binding.list.ArrayListModel;

/**
 * 
 * Holder for the Goals in the decision model
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.4 $
 *  
 */
public class GoalContainer extends Model {

    private ArrayListModel goals = new ArrayListModel();

    private String identifier;

    /**
     * Bound Bean Property <code>PROPERTYNAME_IDENTIFIER</code>
     */
    public static final String PROPERTYNAME_IDENTIFIER = "identifier";//$NON-NLS-1$

    /**
     * Bound Bean Property <code>PROPERTYNAME_GOALS</code>
     */
    public static final String PROPERTYNAME_GOALS = "goals";//$NON-NLS-1$

    /**
     * Constructs a <code>GoalContainer</code> with the given identifier.
     * 
     * @param identifier
     *            the initial name
     */
    public GoalContainer(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Answers the identifier
     * 
     * @return identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Returns the GoalContainer's Goals
     * 
     * @return Goals
     */
    public ArrayListModel getGoals() {
        return goals;
    }

    /**
     * sets the identifier
     * 
     * @param newIdentifier
     *            the identifier to set
     */
    public void setIdentifier(String newIdentifier) {
        String oldIdentifier = getIdentifier();
        identifier = newIdentifier;
        firePropertyChange(PROPERTYNAME_IDENTIFIER, oldIdentifier,
                newIdentifier);
    }

    /**
     * sets the Goals
     * 
     * @param newGoals
     *            the Goals to set
     */
    public void setGoals(ArrayListModel newGoals) {
        ArrayListModel oldGoals = getGoals();
        goals = newGoals;
        firePropertyChange(PROPERTYNAME_GOALS, oldGoals, newGoals);
    }

    /**
     * Adds a Goal
     * 
     * @param newGoal
     *            the Goal to add
     */
    public void addGoal(Goal newGoal) {
        List oldGoals = getGoals();
        goals.add(newGoal);
        List newGoals = getGoals();
        firePropertyChange(PROPERTYNAME_GOALS, oldGoals, newGoals);
    }

    /**
     * Removes a Goal
     * 
     * @param goal
     *            Goal to remove
     */
    public void removeGoal(Goal goal) {
        List oldGoals = getGoals();
        goals.remove(goal);
        List newGoals = getGoals();
        firePropertyChange(PROPERTYNAME_GOALS, oldGoals, newGoals);
    }

    public String toString() {
        return super.toString() + ':' + getIdentifier();
    }

}