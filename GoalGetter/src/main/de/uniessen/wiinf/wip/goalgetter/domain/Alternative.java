/*
 * Alternative.java
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
 * $Id: Alternative.java,v 1.4 2004/08/14 16:43:35 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.domain;

import java.util.Collection;
import java.util.TreeMap;

/**
 * 
 * This class models an alternative in the GoalGetter decision process.
 * <p>
 * An Alternative consists of an identifier, an optional description and a
 * Collection of intensities.
 * <p>
 * The intensities are the alternative-specific value of a Goal.
 * <p>
 * <b>Example </b>: We have a Goal named "availability" and an Alternative
 * called Peter. Peter is available from may to june. So the intensity of the
 * alternative "Peter" would be "may - june".
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.4 $
 *  
 */
public class Alternative extends AbstractDomain {

    /**
     * Bound Bean Property <code>PROPERTYNAME_DESCRIPTION</code>
     */
    public static final String PROPERTYNAME_DESCRIPTION = "description";//$NON-NLS-1$

    /**
     * Bound Bean Property <code>PROPERTYNAME_IDENTIFIER</code>
     */
    public static final String PROPERTYNAME_IDENTIFIER = "identifier";//$NON-NLS-1$

    private String description;

    private String identifier;

    private TreeMap intensityMap = new TreeMap();

    /**
     * Constructs an <code>Alternative</code> with the given identifier.
     * 
     * @param identifier
     *            the initial name
     */
    public Alternative(String identifier) {
        this.identifier = identifier;

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
     * Answers the identifier
     * 
     * @return identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the description
     * 
     * @param newDescription
     *            the new description to set
     */
    public void setDescription(String newDescription) {
        String oldDescription = getDescription();
        description = newDescription;
        firePropertyChange(PROPERTYNAME_DESCRIPTION, oldDescription,
                newDescription);
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

    public String toString() {
        return super.toString() + ':' + getIdentifier();
    }

    /**
     * Returns the intensities
     * 
     * @return intensities
     */
    public TreeMap getIntensities() {
        return intensityMap;
    }

    /**
     * Returns the intensity for the given Goal
     * 
     * @param g
     *            the Goal to fetch the intensity for
     * @return Intensity for the given Goal
     */

    public String getIntensity(Goal g) {
        return (String) intensityMap.get(g);
    }

    /**
     * Puts an intensity
     * 
     * @param g
     *            Goal that the intensity belongs to
     * @param value
     *            the intensity value to set
     */
    public void putIntensity(Goal g, String value) {
        intensityMap.put(g, value);
    }

    /**
     * removes an intensity
     * 
     * @param goal
     *            the Goal whose intensity is to be removed
     */
    public void removeIntensity(Goal goal) {
        removeIntensity(goal);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.domain.AbstractDomain#isEmpty()
     */
    protected boolean isEmpty() {
        // TODO Auto-generated method stub
        return true;
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

    /**
     * @return a collection of the Goals
     */
    public Collection getGoals() {
        return intensityMap.keySet();
    }

}