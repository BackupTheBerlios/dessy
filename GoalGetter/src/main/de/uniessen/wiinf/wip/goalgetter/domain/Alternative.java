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
 * $Id: Alternative.java,v 1.1 2004/07/03 20:17:08 moleman Exp $
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
 * @version $Revision: 1.1 $
 *  
 */
public class Alternative extends Model {

    public static final String PROPERTYNAME_DESCRIPTION = "description";

    public static final String PROPERTYNAME_IDENTIFIER = "identifier";

    private String description;

    private String identifier;

    private Map intensities = new TreeMap();

    public Alternative(String identifier) {
        this.identifier = identifier;

    }

    public String getDescription() {
        return description;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setDescription(String newDescription) {
        String oldDescription = getDescription();
        description = newDescription;
        firePropertyChange(PROPERTYNAME_DESCRIPTION, oldDescription,
                newDescription);
    }

    public void setIdentifier(String newIdentifier) {
        String oldIdentifier = getIdentifier();
        identifier = newIdentifier;
        firePropertyChange(PROPERTYNAME_IDENTIFIER, oldIdentifier,
                newIdentifier);
    }

    public String toString() {
        return super.toString() + ':' + getIdentifier();
    }

    public Map getIntensities() {
        return intensities;
    }

    public void putIntensity(Object key, Object value) {
        intensities.put(key, value);
    }

    public void removeIntensity(Object key) {
        intensities.remove(key);
    }

}