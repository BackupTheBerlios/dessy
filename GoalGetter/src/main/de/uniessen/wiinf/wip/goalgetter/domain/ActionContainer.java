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
 * $Id: ActionContainer.java,v 1.3 2004/08/14 11:11:12 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Holder for the actions in the decision model
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.3 $
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

    public void setActions(List newActions) {
        List oldActions = getActions();
        actions = newActions;
        firePropertyChange(PROPERTYNAME_ACTIONS, oldActions, newActions);
    }

    public void addAction(Action newAction) {
        List oldActions = getActions();
        actions.add(newAction);
        List newActions = getActions();
        firePropertyChange(PROPERTYNAME_ACTIONS, oldActions, newActions);
    }

    public void removeAction(Action action) {
        List oldActions = getActions();
        actions.remove(action);
        List newActions = getActions();
        firePropertyChange(PROPERTYNAME_ACTIONS, oldActions, newActions);
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
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.domain.AbstractDomain#isFilled()
     */
    protected boolean isFilled() {
        // TODO Auto-generated method stub
        return false;
    }
}