/*
 * Action.java
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
 * $Id: Action.java,v 1.3 2004/08/14 11:11:12 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.domain;

/**
 * 
 * An action in the GoalGetter method is specified through its
 * {@link de.uniessen.wiinf.wip.goalgetter.domain.Goal}and
 * {@link de.uniessen.wiinf.wip.goalgetter.domain.Alternative}. Each
 * combination of this two items requires an action, if the alternative's
 * intensity for the Goal does not satisfy the Goals requirements
 * {@link de.uniessen.wiinf.wip.goalgetter.domain.Goal#getIntensity()}.
 * <p>
 * To fulfill the requirements, an action can be taken. An action leads to a
 * result.
 * <p>
 * During an action, two payments are considered:
 * <ul>
 * <li>the payment for action <br>
 * this it the payment required to perform the action</li>
 * <li>the payment for tradeoff <br>
 * this is the payment of the value added after the action wich is beyond the
 * requirements So this measures, how much better than the requirement the
 * alternative is, after the action is performed</li>
 * </ul>
 * 
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.3 $
 *  
 */
public class Action extends AbstractDomain {

    private static final long serialVersionUID = 1L;

    /**
     * Property constant for the description
     */
    public static final String PROPERTYNAME_DESCRIPTION = "description";//$NON-NLS-1$

    /**
     * Property constant for the identifier
     */
    public static final String PROPERTYNAME_IDENTIFIER = "identifier";//$NON-NLS-1$

    /**
     * Property constant for the payment for action
     */
    public static final String PROPERTYNAME_PAYMENTFORACTION = "paymentForAction";//$NON-NLS-1$

    /**
     * Property constant for the payment for tradeoff
     */
    public static final String PROPERTYNAME_PAYMENTFORTRADEOFF = "paymentForTradeoff";//$NON-NLS-1$

    /**
     * Property constant for the result
     */
    public static final String PROPERTYNAME_RESULT = "result";//$NON-NLS-1$

    private String description;

    private String goalId;

    private String alternativeId;

    private String paymentForAction;

    private String paymentForTradeoff;

    private String result;

    /**
     * Constructor. it constructs an action for the given goal and alternative
     * 
     * @param goalId
     *            the goal to construct the action for
     * @param alternativeId
     *            the alternative to construct the action for
     */
    public Action(String goalId, String alternativeId) {
        this.alternativeId = alternativeId;
        this.goalId = goalId;
    }

    /**
     * Accessor for paymentForAction
     * 
     * @return Returns the payment for action.
     */
    public String getPaymentForAction() {
        return paymentForAction;
    }

    /**
     * Accessor for paymentForAction
     * 
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
     * Accessor for paymentForTradeoff
     * 
     * @return Returns the payment for tradeoff.
     */
    public String getPaymentForTradeoff() {
        return paymentForTradeoff;
    }

    /**
     * Accessor for paymentForTradeoff
     * 
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
     * Accessor for result
     * 
     * @return Returns the result of the action.
     */
    public String getResult() {
        return result;
    }

    /**
     * Accessor for result
     * 
     * @param result
     *            The result to set.
     */
    public void setResult(String result) {
        String oldResult = getResult();
        this.result = result;
        firePropertyChange(PROPERTYNAME_RESULT, oldResult, result);
    }

    /**
     * Accessor for description
     * 
     * @return Returns the action's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Accessor for identifier
     * 
     * @return Returns the action's identifier
     */
    public String getIdentifier() {
        return goalId + alternativeId;
    }

    /**
     * Accessor for description
     * 
     * @param newDescription
     *            The description to set.
     */
    public void setDescription(String newDescription) {
        String oldDescription = getDescription();
        description = newDescription;
        firePropertyChange(PROPERTYNAME_DESCRIPTION, oldDescription,
                newDescription);
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