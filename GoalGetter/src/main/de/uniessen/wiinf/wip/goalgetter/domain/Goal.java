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
 * $Id: Goal.java,v 1.4 2004/08/14 16:43:35 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.domain;

import com.jgoodies.validation.util.ValidationUtils;

/**
 * 
 * Describes goals and provides bound bean properties
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.4 $
 *  
 */
public class Goal extends AbstractDomain implements Comparable {

    /**
     * Bound Bean Property <code>PROPERTYNAME_DESCRIPTION</code>
     */
    public static final String PROPERTYNAME_DESCRIPTION = "description";//$NON-NLS-1$

    /**
     * Bound Bean Property <code>PROPERTYNAME_IDENTIFIER</code>
     */
    public static final String PROPERTYNAME_NAME = "name";//$NON-NLS-1$

    /**
     * Bound Bean Property <code>PROPERTYNAME_INTENSITY</code>
     */
    public static final String PROPERTYNAME_INTENSITY = "intensity";//$NON-NLS-1$

    private String description;

    private String name;

    private String intensity;

    /**
     * Internal identifier for a Goal instance.
     */
    private String identifier;
    
    private static long counter =0;

    // Instance Creation ******************************************************

    /**
     * Constructs a <code>Goal</code> with the given identifier.
     * 
     * @param name
     *            the initial name
     */
    public Goal(String name) {
        this.name = name;
        this.identifier = generateUID();
    }

    /**
     * generates and answers an unique ID for the Goal. The ids are predictable
     * incrementing, meaning that an object constructed after an existing object
     * has an higher id value in the terms of
     * {@link String#compareTo(java.lang.String)}.
     * <p>
     * The algorithm used to generate the UId may change at any time, so donts
     * rely on the format.
     * 
     * @return UID
     */
    private String generateUID() {
        return this.getClass().getName() + System.currentTimeMillis()
                + counter++;
    }

    /**
     * Answers the description
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Answers the name
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return intensity
     */
    public String getIntensity() {
        return intensity;
    }

    /**
     * Returns the internal identifier
     * 
     * @return identifier
     */
    public String getIdentifier() {
        return identifier;
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
     * @param newName
     *            the identifier to set
     */
    public void setName(String newName) {
        String oldName = getName();
        name = newName;
        firePropertyChange(PROPERTYNAME_NAME, oldName, newName);
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

    //  Misc *******************************************************************

    /**
     * Returns a string representation for this goal. Currently it prints the
     * class info and identifier.
     * 
     * @return a string representation for this goal
     */
    public String toString() {
        return super.toString() + ':' + getName();
    }

    /* (non-Javadoc)
     * @see de.uniessen.wiinf.wip.goalgetter.domain.AbstractDomain#isEmpty()
     */
    protected boolean isEmpty() {
        return ValidationUtils.isBlank(name)
                && ValidationUtils.isBlank(intensity);
    }

    /* (non-Javadoc)
     * @see de.uniessen.wiinf.wip.goalgetter.domain.AbstractDomain#isFilled()
     */
    protected boolean isFilled() {
        return !ValidationUtils.isBlank(name)
                && !ValidationUtils.isBlank(intensity);
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object o) {
        if(o.getClass() != this.getClass()){
            return 0;
        }
        Goal g = (Goal) o;
        return identifier.compareTo(g.getIdentifier());
    }


}