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
 * $Id: GoalContainer.java,v 1.2 2004/07/18 21:25:28 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.domain;

import java.util.List;

import com.jgoodies.binding.beans.Model;
import com.jgoodies.binding.list.ArrayListModel;

/**
 * 
 * GoalContainer
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.2 $
 *  
 */
public class GoalContainer extends Model {

    private ArrayListModel goals = new ArrayListModel();

    private String identifier;

    public static final String PROPERTYNAME_IDENTIFIER = "identifier";

    public static final String PROPERTYNAME_GOALS = "goals";

    public GoalContainer(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public ArrayListModel getGoals() {
        return goals;
    }

    public void setIdentifier(String newIdentifier) {
        String oldIdentifier = getIdentifier();
        identifier = newIdentifier;
        firePropertyChange(PROPERTYNAME_IDENTIFIER, oldIdentifier,
                newIdentifier);
    }

    public void setGoals(ArrayListModel newGoals) {
        ArrayListModel oldGoals = getGoals();
        goals = newGoals;
        firePropertyChange(PROPERTYNAME_GOALS, oldGoals, newGoals);
    }

    public void addGoal(Goal newGoal) {
        List oldGoals = getGoals();
        goals.add(newGoal);
        List newGoals = getGoals();
        firePropertyChange(PROPERTYNAME_GOALS, oldGoals, newGoals);
    }

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