/*
 * ActionContainerByGoal.java
 * Package: de.uniessen.wiinf.wip.goalgetter.tool
 * Project: GoalGetter
 * 
 * GoalGetter is based on a decision supporting method 
 * developed by Markus Stallkamp (markus.stallkamp@uni-essen.de)
 * 
 * (c) 2004 
 * Jonas Sprenger (jonas.sprenger@gmx.de),
 * Tim Franz (tim.franz@uni-essen.de)
 * 
 * $Id: ActionContainerByAlternative.java,v 1.1 2004/09/25 14:56:57 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.model;

import com.jgoodies.binding.list.ArrayListModel;

import de.uniessen.wiinf.wip.goalgetter.domain.Alternative;
import de.uniessen.wiinf.wip.goalgetter.domain.Goal;
import de.uniessen.wiinf.wip.goalgetter.domain.container.ActionContainer;

/**
 * ActionContainerByGoal 
 *
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *
 */
public class ActionContainerByAlternative {
    
    private  Alternative alternative;
    private  ActionContainer actionContainer;

    /**
     * @param actionContainer
     * @param anAlternative
     * 
     */
    public ActionContainerByAlternative(ActionContainer actionContainer, Alternative anAlternative) {
        super();
        this.actionContainer = actionContainer;
        alternative = anAlternative;
        
    }

    /**
     * @return
     */
    public String getName() {       
        return alternative.getIdentifier();
    }

    /**
     * @return
     */
    public ArrayListModel getActionByAlternativeListModel() { 
       return actionContainer.getActionsFor(alternative); 
    }

  

}
