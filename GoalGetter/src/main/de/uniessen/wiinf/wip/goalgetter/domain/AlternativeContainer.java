/*
 * AlternativeContainer.java
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
 * $Id: AlternativeContainer.java,v 1.2 2004/08/07 09:28:04 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.domain;

import java.util.List;

import com.jgoodies.binding.beans.Model;
import com.jgoodies.binding.list.ArrayListModel;

/**
 * 
 * Holder for the alternatives in the decision model
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.2 $
 *  
 */
public class AlternativeContainer extends Model {

    private final ArrayListModel alternatives = new ArrayListModel();

    private String identifier;

    /**
     * Bound Bean Property <code>PROPERTYNAME_IDENTIFIER</code>
     */
    public static final String PROPERTYNAME_IDENTIFIER = "identifier";//$NON-NLS-1$

    /**
     * Bound Bean Property <code>PROPERTYNAME_ALTERNATIVES</code>
     */
    public static final String PROPERTYNAME_ALTERNATIVES = "alternatives";//$NON-NLS-1$

    /**
     * Constructs an <code>AlternativeContainer</code> with the given
     * identifier.
     * 
     * @param identifier
     *            the initial name
     */
    public AlternativeContainer(String identifier) {
        this.identifier = identifier;
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
     * Returns the list of alternatives
     * 
     * @return alternatives
     */
    public ArrayListModel getAlternatives() {
        return alternatives;
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
     * Adds an Alternative to the list
     * 
     * @param newAlternative
     *            alternative to add
     */
    public void addAlternative(Alternative newAlternative) {
        List oldAlternatives = getAlternatives();
        alternatives.add(newAlternative);
        List newAlternatives = getAlternatives();
        firePropertyChange(PROPERTYNAME_ALTERNATIVES, oldAlternatives,
                newAlternatives);
    }

    /**
     * removes an Alternative from the list
     * 
     * @param alternative
     *            Alternative to remove
     */
    public void removeAlternative(Alternative alternative) {
        List oldAlternatives = getAlternatives();
        alternatives.remove(alternative);
        List newAlternatives = getAlternatives();
        firePropertyChange(PROPERTYNAME_ALTERNATIVES, oldAlternatives,
                newAlternatives);
    }

    public String toString() {
        return super.toString() + ':' + getIdentifier();
    }
}