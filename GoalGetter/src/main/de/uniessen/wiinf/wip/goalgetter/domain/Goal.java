/*
 * Goal.java
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
 * $Id: Goal.java,v 1.1 2004/07/03 20:17:08 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.domain;

import com.jgoodies.binding.beans.Model;

/**
 * 
 * Describes goals and provides bound bean properties
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public class Goal extends Model {

    public static final String PROPERTYNAME_DESCRIPTION = "description";

    public static final String PROPERTYNAME_IDENTIFIER = "identifier";

    public static final String PROPERTYNAME_UNIT = "unit";

    public static final String PROPERTYNAME_INTENSITY = "intensity";

    public static final String PROPERTYNAME_OPERATOR = "operator";

    private String description;

    private String identifier;

    private String unit;

    private String intensity;

    private String operator;

    // Instance Creation ******************************************************

    /**
     * Constructs a <code>Goal</code> with the given identifier.
     * 
     * @param identifier
     *            the initial name
     */
    public Goal(String identifier) {
        this.identifier = identifier;
    }

    /**
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @return identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * 
     * @return unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 
     * @return intensity
     */
    public String getIntensity() {
        return intensity;
    }

    /**
     * sets the description
     * 
     * @param newDescription
     *            description to set
     */
    public void setDescription(String newDescription) {
        String oldDescription = getDescription();
        description = newDescription;
        firePropertyChange(PROPERTYNAME_DESCRIPTION, oldDescription,
                newDescription);
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
     * sets the unit
     * 
     * @param newUnit
     *            the unit to set
     */
    public void setUnit(String newUnit) {
        String oldUnit = getUnit();
        unit = newUnit;
        firePropertyChange(PROPERTYNAME_UNIT, oldUnit, newUnit);
    }

    /**
     * sets the intensity
     * 
     * @param newIntensity
     *            the intensity to set
     */
    public void setIntensity(String newIntensity) {
        String oldIntensity = getIntensity();
        intensity = newIntensity;
        firePropertyChange(PROPERTYNAME_INTENSITY, oldIntensity, newIntensity);
    }

    /**
     * sets the operator
     * 
     * @param newoperator
     *            the operator to set
     */
    public void setOperator(String newOperator) {
        String oldOperator = getOperator();
        this.operator = newOperator;
        firePropertyChange(PROPERTYNAME_OPERATOR, oldOperator, newOperator);
    }

    /**
     * 
     * @return
     */
    public String getOperator() {
        return operator;
    }

    //  Misc *******************************************************************

    /**
     * Returns a string representation for this goal. Currently it prints the
     * class info and identifier.
     * 
     * @return a string representation for this goal
     */
    public String toString() {
        return super.toString() + ':' + getIdentifier();
    }

}