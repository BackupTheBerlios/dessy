/*
 * ActionContainer.java
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
 * $Id: ActionContainer.java,v 1.1 2004/08/14 16:43:35 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.domain.container;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.uniessen.wiinf.wip.goalgetter.domain.AbstractDomain;
import de.uniessen.wiinf.wip.goalgetter.domain.Action;
import de.uniessen.wiinf.wip.goalgetter.domain.Alternative;
import de.uniessen.wiinf.wip.goalgetter.domain.FillLevel;
import de.uniessen.wiinf.wip.goalgetter.domain.Goal;

/**
 * 
 * Holder for the actions in the decision model
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public class ActionContainer extends AbstractDomain {

    private static final long serialVersionUID = 1L;

    private List actions = new ArrayList();

    private String identifier;

    /**
     * Bound Bean Property <code>PROPERTYNAME_IDENTIFIER</code>
     */
    public static final String PROPERTYNAME_IDENTIFIER = "identifier";//$NON-NLS-1$

    /**
     * Bound Bean Property <code>PROPERTYNAME_ACTIONS</code>
     */
    public static final String PROPERTYNAME_ACTIONS = "actions";//$NON-NLS-1$

    /**
     * Constructs an <code>ActionContainer</code> with the given identifier.
     * 
     * @param identifier
     *            the initial name
     */
    public ActionContainer(String identifier) {
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

    /** Answers all Actions
     * @return all Actions
     */
    public List getActions() {
        return actions;
    }

    /**
     * Sets the identifier
     * 
     * @param newIdentifier
     *            the new identifier to set
     */
    public void setIdentifier(String newIdentifier) {
        String oldIdentifier = getIdentifier();
        identifier = newIdentifier;
        firePropertyChange(PROPERTYNAME_IDENTIFIER, oldIdentifier,
                newIdentifier);
    }

    /** Sets a new List of Actions
     * @param newActions the List of Actions to be set instead of the current List
     */
    public void setActions(List newActions) {
        //TODO consider removing this method
        List oldActions = getActions();
        actions = newActions;
        firePropertyChange(PROPERTYNAME_ACTIONS, oldActions, newActions);
    }

    /** Adds an action to the Collection
     * @param newAction
     */
    public void addAction(Action newAction) {
        List oldActions = getActions();
        actions.add(newAction);
        List newActions = getActions();
        firePropertyChange(PROPERTYNAME_ACTIONS, oldActions, newActions);
    }

    /**Removes an Action from the Collection
     * @param action the Action to remove
     */
    public void removeAction(Action action) {
        List oldActions = getActions();
        actions.remove(action);
        List newActions = getActions();
        firePropertyChange(PROPERTYNAME_ACTIONS, oldActions, newActions);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return super.toString() + ':' + getIdentifier();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.domain.AbstractDomain#isEmpty()
     */
    protected boolean isEmpty() {
        Iterator iterator = getActions().iterator();
        int fillLevel = 0;
        while (iterator.hasNext()) {
            Action action = (Action) iterator.next();
            fillLevel += action.getFillLevel();
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
        Iterator iterator = getActions().iterator();
        int fillLevel = 0;
        while (iterator.hasNext()) {
            Action action = (Action) iterator.next();
            fillLevel += action.getFillLevel();
        }
        if (fillLevel == FillLevel.FULL * getActions().size()) {
            return true;
        }

        return false;

    }

    /**
     * Finds and answers all Actions which cover the given Goal
     * 
     * @param g
     *            the goal which must be covered by the Action
     * @return List of Actions covering the Goal
     * @throws IllegalStateException
     *             if the Goal does not match any of the Actions.
     */
    public List getActionsFor(Goal g) {
        List resultList = new ArrayList();
        Iterator iterator = actions.iterator();
        while (iterator.hasNext()) {
            Action action = (Action) iterator.next();
            if (action.getGoal() == g) {
                resultList.add(action);
            }
        }
        if (resultList.size() == 0) {
            throw new IllegalStateException(
                    "For this Goal is no Action available. This should never happen."); //$NON-NLS-1$
        }
        return resultList;
    }

    /**
     * Finds and answers all Actions which cover the given Alternative
     * 
     * @param a
     *            the Alternative which must be covered by the Action
     * @return List of Actions covering the Alternative
     * @throws IllegalStateException
     *             if the Alternative does not match any of the Actions.
     */
    public List getActionsFor(Alternative a) {
        List resultList = new ArrayList();
        Iterator iterator = actions.iterator();
        while (iterator.hasNext()) {
            Action action = (Action) iterator.next();
            if (action.getAlternative() == a) {
                resultList.add(action);
            }
        }
        if (resultList.size() == 0) {
            throw new IllegalStateException(
                    "For this Alternative is no Action available. This should never happen."); //$NON-NLS-1$
        }
        return resultList;
    }

    /**
     * Finds and answers the Actions which covers the given Alternative and Goal
     * 
     * @param g
     *            the Goal which must be covered by the Action
     * @param a
     *            the Alternative which must be covered by the Action
     * @return Action covering the Alternative and Goal
     * @throws IllegalStateException
     *             if the Alternative and Goal does not match any of the
     *             Actions.
     */
    public Action getActionFor(Goal g, Alternative a) {
        Iterator iterator = actions.iterator();
        while (iterator.hasNext()) {
            Action action = (Action) iterator.next();
            if (action.getAlternative() == a && action.getGoal() == g) {
                return action;
            }
        }
        throw new IllegalStateException(
                "For this combination of Goal and Alternative is no Action available. This should never happen."); //$NON-NLS-1$
    }
}