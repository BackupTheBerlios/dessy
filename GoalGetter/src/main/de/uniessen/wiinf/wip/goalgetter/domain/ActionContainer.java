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
 * $Id: ActionContainer.java,v 1.1 2004/07/03 20:17:08 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.domain;

import java.util.ArrayList;
import java.util.List;

import com.jgoodies.binding.beans.Model;

/**
 * 
 * ActionContainer
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public class ActionContainer extends Model {

    private List actions = new ArrayList();

    private String identifier;

    public static final String PROPERTYNAME_IDENTIFIER = "identifier";

    public static final String PROPERTYNAME_ACTIONS = "actions";

    public ActionContainer(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List getActions() {
        return actions;
    }

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
}