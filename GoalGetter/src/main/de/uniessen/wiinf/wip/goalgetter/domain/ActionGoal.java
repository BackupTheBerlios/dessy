/*
 * ActionGoal.java
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
 * $Id: ActionGoal.java,v 1.2 2004/08/07 09:28:04 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.domain;

import com.jgoodies.binding.beans.Model;

/**
 * 
 * Action
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.2 $
 *  
 */
public class ActionGoal extends Model {

    public static final String PROPERTYNAME_DESCRIPTION = "description";//$NON-NLS-1$

    public static final String PROPERTYNAME_IDENTIFIER = "identifier";//$NON-NLS-1$

    public static final String PROPERTYNAME_PAYMENTFORACTION = "paymentForAction";//$NON-NLS-1$

    public static final String PROPERTYNAME_PAYMENTFORTRADEOFF = "paymentForTradeoff";//$NON-NLS-1$

    public static final String PROPERTYNAME_RESULT = "result";//$NON-NLS-1$

    private String description;

    private String goalId;

    private String alternativeId;

    private String paymentForAction;

    private String paymentForTradeoff;

    private String result;

    public ActionGoal(String goalId, String alternativeId) {
        this.alternativeId = alternativeId;
        this.goalId = goalId;
    }

    /**
     * @return Returns the paymentForAction.
     */
    public String getPaymentForAction() {
        return paymentForAction;
    }

    /**
     * @param paymentForAction
     *            The paymentForAction to set.
     */
    public void setPaymentForAction(String paymentForAction) {
        String oldPaymentForAction = getPaymentForAction();
        this.paymentForAction = paymentForAction;
        firePropertyChange(PROPERTYNAME_PAYMENTFORACTION, oldPaymentForAction,
                paymentForAction);
    }

    /**
     * @return Returns the paymentForTradeoff.
     */
    public String getPaymentForTradeoff() {
        return paymentForTradeoff;
    }

    /**
     * @param paymentForTradeoff
     *            The paymentForTradeoff to set.
     */
    public void setPaymentForTradeoff(String paymentForTradeoff) {
        String oldPaymentForTradeoff = getPaymentForTradeoff();
        this.paymentForTradeoff = paymentForTradeoff;
        firePropertyChange(PROPERTYNAME_PAYMENTFORTRADEOFF,
                oldPaymentForTradeoff, paymentForTradeoff);
    }

    /**
     * @return Returns the result of the action.
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result
     *            The result to set.
     */
    public void setResult(String result) {
        String oldResult = getResult();
        this.result = result;
        firePropertyChange(PROPERTYNAME_RESULT, oldResult, result);
    }

    public String getDescription() {
        return description;
    }

    public String getIdentifier() {
        return goalId + alternativeId;
    }

    public void setDescription(String newDescription) {
        String oldDescription = getDescription();
        description = newDescription;
        firePropertyChange(PROPERTYNAME_DESCRIPTION, oldDescription,
                newDescription);
    }

    public String toString() {
        return super.toString() + ':' + getIdentifier();
    }

}