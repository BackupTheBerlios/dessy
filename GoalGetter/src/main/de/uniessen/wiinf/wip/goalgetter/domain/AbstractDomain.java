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
 * $Id: AbstractDomain.java,v 1.1 2004/08/14 11:11:12 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.domain;

import com.jgoodies.binding.beans.Model;

/**
 * AbstractDomain
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public abstract class AbstractDomain extends Model {

    protected abstract boolean isEmpty();

    protected abstract boolean isFilled();

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