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
 * $Id: Alternative.java,v 1.2 2004/08/07 09:28:04 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.domain;

import java.util.Map;
import java.util.TreeMap;

import com.jgoodies.binding.beans.Model;

/**
 * 
 * Alternative
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.2 $
 *  
 */
public class Alternative extends Model {

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

    private Map intensities = new TreeMap();

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

    /** Returns the intensities
     * @return intensities
     */
    public Map getIntensities() {
        return intensities;
    }

    /** Puts an intensity  
     * @param key 
     * @param value
     */
    public void putIntensity(Object key, Object value) {
        intensities.put(key, value);
    }

    /** removes an intensity
     * @param key the intensity to remove
     */
    public void removeIntensity(Object key) {
        intensities.remove(key);
    }

}