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
 * $Id: AlternativeContainer.java,v 1.7 2004/09/25 14:56:57 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.domain.container;

import java.util.Iterator;
import java.util.List;

import com.jgoodies.binding.list.ArrayListModel;

import de.uniessen.wiinf.wip.goalgetter.domain.AbstractDomain;
import de.uniessen.wiinf.wip.goalgetter.domain.Alternative;
import de.uniessen.wiinf.wip.goalgetter.domain.FillLevel;

/**
 * 
 * Holds all alternatives in the decision model
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.7 $
 *  
 */
public class AlternativeContainer extends AbstractDomain {

    private ArrayListModel alternatives = new ArrayListModel();
    private ArrayListModel alternativesWithShouldBe=new ArrayListModel();

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
     * Constructs an <code>AlternativeContainer</code> 
     */
    public AlternativeContainer() {
        this(null);
    }

    /**
     * Constructs an <code>AlternativeContainer</code> with the given
     * identifier.
     * 
     * @param identifier
     *            the initial name
     */
    public AlternativeContainer(String identifier) {
        this.identifier = identifier;   
        this.alternativesWithShouldBe.add("");
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
     * Returns the list of alternatives
     * 
     * @return alternatives
     */
    public ArrayListModel getAlternativesListModel() {
        return alternativesWithShouldBe;
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
        alternativesWithShouldBe.add(newAlternative);
        List newAlternatives = getAlternatives();
        firePropertyChange(PROPERTYNAME_ALTERNATIVES, oldAlternatives,
                newAlternatives);
    }

    /**
     * @param newAlternatives
     *            The alternatives to set.
     */
    public void setAlternatives(ArrayListModel newAlternatives) {       
        
        ArrayListModel oldAlternatives = getAlternatives();
        alternatives = newAlternatives;
        alternativesWithShouldBe = new ArrayListModel();
        alternativesWithShouldBe.add("");
        alternativesWithShouldBe.addAll(newAlternatives);
        
        firePropertyChange(PROPERTYNAME_ALTERNATIVES, oldAlternatives, newAlternatives);
    }
    
    /**
     * @param alternativesWithShouldBe The alternativesWithShouldBe to set.
     */
    public void setAlternativesWithShouldBe(
            ArrayListModel alternativesWithShouldBe) {
        this.alternativesWithShouldBe = alternativesWithShouldBe;
    }
    
    /**
     * @return Returns the alternativesWithShouldBe.
     */
    public ArrayListModel getAlternativesWithShouldBe() {
        return alternativesWithShouldBe;
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
        alternativesWithShouldBe.remove(alternative);
        firePropertyChange(PROPERTYNAME_ALTERNATIVES, oldAlternatives,
                newAlternatives);
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
        Iterator iterator = getAlternatives().iterator();
       
        int fillLevel = 0;
        while (iterator.hasNext()) {
            Alternative a = (Alternative) iterator.next();
            fillLevel += a.getFillLevel();
        }
        switch (fillLevel) {
        case FillLevel.EMPTY:
            return true;

        default:
            return false;

        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.domain.AbstractDomain#isFilled()
     */
    protected boolean isFilled() {

        Iterator iterator = getAlternatives().iterator();
       
        int fillLevel = 0;
        while (iterator.hasNext()) {
            Alternative a = (Alternative) iterator.next();
            fillLevel += a.getFillLevel();
        }
        if (fillLevel == FillLevel.FULL * getAlternatives().size()) {
            return true;
        }

        return false;
    }
    
   

}