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
 * $Id: AlternativeContainer.java,v 1.1 2004/07/03 20:17:08 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jgoodies.binding.beans.Model;

/**
 * 
 * AlternativeContainer
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public class AlternativeContainer extends Model {

    private final List alternatives = new ArrayList();

    private String identifier;

    public static final String PROPERTYNAME_IDENTIFIER = "identifier";

    public static final String PROPERTYNAME_ALTERNATIVES = "alternatives";

    public AlternativeContainer(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List getAlternatives() {
        return Collections.unmodifiableList(alternatives);
    }

    public void setIdentifier(String newIdentifier) {
        String oldIdentifier = getIdentifier();
        identifier = newIdentifier;
        firePropertyChange(PROPERTYNAME_IDENTIFIER, oldIdentifier,
                newIdentifier);
    }

    public void addAlternative(Alternative newAlternative) {
        List oldAlternatives = getAlternatives();
        alternatives.add(newAlternative);
        List newAlternatives = getAlternatives();
        firePropertyChange(PROPERTYNAME_ALTERNATIVES, oldAlternatives,
                newAlternatives);
    }

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