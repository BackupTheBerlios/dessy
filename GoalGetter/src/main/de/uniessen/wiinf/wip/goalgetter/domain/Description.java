/*
 * Description.java
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
 * $Id: Description.java,v 1.2 2004/08/07 09:28:04 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.domain;

import com.jgoodies.binding.beans.Model;

/**
 * 
 * Description
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.2 $
 *  
 */
public final class Description extends Model {

    /**
     * Bound bean property <code>PROPERTYNAME_DESCRIPTION</code>
     */
    public static final String PROPERTYNAME_DESCRIPTION = "description";//$NON-NLS-1$

    /**
     * Bound bean property <code>PROPERTYNAME_IDENTIFIER</code>
     */
    public static final String PROPERTYNAME_IDENTIFIER = "identifier";//$NON-NLS-1$

    private String description;

    private String identifier;

    /**
     * Constructs a <code>Description</code> with the given identifier.
     * 
     * @param identifier
     *            the initial name
     */
    public Description(String identifier) {
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

    public String toString() {
        return super.toString() + ':' + getIdentifier();
    }

}