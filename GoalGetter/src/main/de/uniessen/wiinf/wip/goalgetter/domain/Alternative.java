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
 * $Id: Alternative.java,v 1.7 2004/08/16 12:26:21 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.jgoodies.validation.util.ValidationUtils;

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
 * @version $Revision: 1.7 $
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

    /**
     * Bound Bean Property <code>PROPERTYNAME_INTENSITY</code>
     */
    public static final String PROPERTYNAME_INTENSITY = "intensity";//$NON-NLS-1$

    private String description;

    private String identifier;

    private TreeMap intensityMap = new TreeMap();

    /**
     *  
     */
    public Alternative() {
        this(null);
    }

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
        Map oldValue = getIntensityMap();
        intensityMap.put(g, value);
        Map newValue = getIntensityMap();
        firePropertyChange(PROPERTYNAME_INTENSITY, oldValue, newValue);
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
        boolean intensitiesEmpty = true;

        Iterator iterator = intensityMap.values().iterator();
        while (iterator.hasNext()) {
            String element = (String) iterator.next();
            intensitiesEmpty = intensitiesEmpty
                    && ValidationUtils.isBlank(element);
        }

        return ValidationUtils.isBlank(identifier) && intensitiesEmpty;

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.domain.AbstractDomain#isFilled()
     */
    protected boolean isFilled() {
        boolean intensitiesFilled = true;

        Iterator iterator = intensityMap.values().iterator();
        while (iterator.hasNext()) {
            String element = (String) iterator.next();
            intensitiesFilled = intensitiesFilled
                    && !ValidationUtils.isBlank(element);
        }

        return !ValidationUtils.isBlank(identifier) && intensitiesFilled;
    }

    /**
     * @return a collection of the Goals
     */
    public List getGoals() {
        Set set = intensityMap.keySet();
        List l = new ArrayList();
        l.addAll(set);
        return l;

    }

    /**
     * @param intensityMap
     *            The intensityMap to set.
     */
    public void setIntensityMap(TreeMap intensityMap) {
        this.intensityMap = intensityMap;
    }

    /**
     * @return Returns the intensityMap.
     */
    public TreeMap getIntensityMap() {
        return intensityMap;
    }

}