/*
 * AbstractDomain.java
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
 * $Id: AbstractDomain.java,v 1.2 2004/08/14 16:43:35 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.domain;

import com.jgoodies.binding.beans.Model;

/**
 * Abstract Superclass for GoalGetter Domain Classes. Contains Helper Methods
 * needed by every subclass. Currently this class can calculate thew fill level
 * of its domain record.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.2 $
 *  
 */
public abstract class AbstractDomain extends Model {

    protected abstract boolean isEmpty();

    protected abstract boolean isFilled();

    /**
     * Calculates the fill level of the domain item. the fill level is defined
     * by the number of required fiels currently filled. If this method is used
     * in as DomainContainer Class, the fill level is defined by the fill levels
     * of the containing elements. Currently, we distinguish three states:
     * empty, helf and full.
     * 
     * @return Fill Level of this item (including sub-items)
     * @see FillLevel
     */
    public int getFillLevel() {
        if (isFilled()) {
            return FillLevel.FULL;
        }
        if (isEmpty()) {
            return FillLevel.EMPTY;
        }
        return FillLevel.HALF;

    }
}