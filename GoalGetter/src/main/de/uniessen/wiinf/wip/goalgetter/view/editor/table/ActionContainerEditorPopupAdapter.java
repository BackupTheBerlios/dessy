/*
 * GoalContainerEditorPopupMenuBuilder.java
 * Package: de.uniessen.wiinf.wip.goalgetter.view.editor.table
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
 * $Id: ActionContainerEditorPopupAdapter.java,v 1.2 2004/09/25 14:56:57 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.editor.table;

import javax.swing.JPopupMenu;

import com.jgoodies.uif.action.ActionManager;
import com.jgoodies.uifextras.util.PopupAdapter;

import de.uniessen.wiinf.wip.goalgetter.model.Actions;

/**
 * Builds the right click popup menu for the GoalContainerEditor
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.2 $
 *  
 */
public final class ActionContainerEditorPopupAdapter extends PopupAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see com.jgoodies.uifextras.util.PopupAdapter#createPopupMenu()
     */
    protected JPopupMenu createPopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(ActionManager.get(Actions.ADD_GOAL_ID));
        popupMenu.add(ActionManager.get(Actions.ADD_ALTERNATIVE_ID));
        popupMenu.add(ActionManager.get(Actions.DELETE_ITEM_ID));
        return popupMenu;
    }

}