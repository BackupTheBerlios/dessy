/*
 * SensitivityElements.java
 * Package: de.uniessen.wiinf.wip.goalgetter.view.sensitivity
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
 * $Id: SensitivityElements.java,v 1.5 2004/10/05 10:11:38 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.sensitivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Element Type for sensitivity analysis.
 * 
 * @see de.uniessen.wiinf.wip.goalgetter.view.sensitivity.SensitivityAnalysisChart
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.5 $
 */
public class SensitivityElements {

    /**
     * saves all given values and changes.
     */
    Map dynamicValueMap;

    /**
     * Holds the project's original values. this values cant be changed.
     */
    Map orginalValueMap;

    String name;

    /**
     * Constructor
     * 
     * @param name
     *            name of the sensitivityElement
     */
    public SensitivityElements(String name) {
        super();
        this.name = name;
        dynamicValueMap = new HashMap();
        orginalValueMap = new HashMap();

    }

    /**
     * @param name
     * @param value
     *            add Values to the value Map
     */
    public void addValues(String name, Object value) {
        dynamicValueMap.put(name, value);
        orginalValueMap.put(name, value);
    }

    /**
     * @return Returns the values.
     */
    public Map getValues() {
        return dynamicValueMap;
    }

    /**
     * @return Returns the values.
     */
    public Map getOrginalValues() {
        return orginalValueMap;
    }

    /**
     * sets a Map of values. Previously existing values are replaecd with the
     * new Map's values.
     * 
     * @param values
     *            The values to set.
     */
    public void setValues(Map values) {
        this.dynamicValueMap = values;
    }

    /**
     * Answers the name of the data series
     * 
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets thwe name of the data series
     * 
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Resets the Collection
     *  
     */
    public void reset() {
        dynamicValueMap.clear();
        dynamicValueMap.putAll(orginalValueMap);

    }
}

