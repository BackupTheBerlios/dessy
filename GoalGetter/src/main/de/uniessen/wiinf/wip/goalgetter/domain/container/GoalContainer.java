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
 * $Id: GoalContainer.java,v 1.3 2004/08/16 11:25:22 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.domain.container;

import java.util.Iterator;
import java.util.List;

import com.jgoodies.binding.list.ArrayListModel;

import de.uniessen.wiinf.wip.goalgetter.domain.AbstractDomain;
import de.uniessen.wiinf.wip.goalgetter.domain.FillLevel;
import de.uniessen.wiinf.wip.goalgetter.domain.Goal;

/**
 * 
 * Holder for the Goals in the decision model
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.3 $
 *  
 */
public class GoalContainer extends AbstractDomain {

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
     * Constructs a <code>GoalContainer</code>
     */
    public GoalContainer(){
        this(null);
    }
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
     * @param goal
     *            the Goal to add
     */
    public void addGoal(Goal goal) {
        List oldGoals = getGoals();
        goals.add(goal);
        List newGoals = getGoals();
        firePropertyChange(PROPERTYNAME_GOALS, oldGoals, newGoals);
    }

    /**
     * Removes a Goal
     * 
     * @param goal
     *            the Goal to remove
     */
    public void removeGoal(String goal) {
        List oldGoals = getGoals();
        goals.remove(goal);
        List newGoals = getGoals();
        firePropertyChange(PROPERTYNAME_GOALS, oldGoals, newGoals);
    }

    /**
     * Finds and answers a Goal with the given identifier
     * 
     * @param goalIdentifier
     *            identifier of the Goal to find
     * @return Goal
     * @throws IllegalArgumentException
     *             if goalIdentifier matches no goal in the container
     */
    public Goal goalByIdentifier(String goalIdentifier) {
        Iterator iterator = goals.iterator();
        while (iterator.hasNext()) {
            Goal g = (Goal) iterator.next();
            if (g.getIdentifier().equals(goalIdentifier)) {
                return g;
            }
        }
        throw new IllegalArgumentException("Given identifier matches no Goal"); //$NON-NLS-1$
    }

    public String toString() {
        return super.toString() + ':' + getIdentifier();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.domain.AbstractDomain#isEmpty()
     */
    protected boolean isEmpty() {
        Iterator iterator = getGoals().iterator();
        int fillLevel = 0;
        while (iterator.hasNext()) {
            Goal g = (Goal) iterator.next();
            fillLevel += g.getFillLevel();
        }
        switch (fillLevel) {
        case FillLevel.EMPTY:
            return true;

        default:
            return false;

        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.domain.AbstractDomain#isFilled()
     */
    protected boolean isFilled() {

        Iterator iterator = getGoals().iterator();
        int fillLevel = 0;
        while (iterator.hasNext()) {
            Goal g = (Goal) iterator.next();
            fillLevel += g.getFillLevel();
        }
        if (fillLevel == FillLevel.FULL * getGoals().size()) {
            return true;
        }

        return false;

    }

}