/*
 * MainToolBarBuilder.java
 * Package: de.uniessen.wiinf.wip.goalgetter.view
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
 * $Id: MainToolBarBuilder.java,v 1.4 2004/09/25 14:56:57 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.view;

import javax.swing.AbstractButton;
import javax.swing.JToolBar;

import com.jgoodies.looks.BorderStyle;
import com.jgoodies.looks.HeaderStyle;
import com.jgoodies.looks.Options;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.windows.WindowsLookAndFeel;
import com.jgoodies.uif.action.ActionManager;
import com.jgoodies.uif.action.ToggleAction;
import com.jgoodies.uif.builder.ToolBarBuilder;
import com.jgoodies.uif.component.ToolBarButton;

import de.uniessen.wiinf.wip.goalgetter.model.Actions;

/**
 * 
 * Builds the tool bar
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.4 $
 *  
 */
final class MainToolBarBuilder {

    /**
     * Creates, configures, composes and returns the tool bar.
     * 
     * @return the application's toolbar.
     */
    JToolBar build() {

        // Set a hint so that JGoodies Looks will detect it as being in the
        // header.
        ToolBarBuilder builder = new ToolBarBuilder("ToolBar", HeaderStyle.BOTH); //$NON-NLS-1$

        // Unlike the default, use a separator border.
        builder.getToolBar().putClientProperty(
                WindowsLookAndFeel.BORDER_STYLE_KEY, BorderStyle.SEPARATOR);
        builder.getToolBar().putClientProperty(
                PlasticLookAndFeel.BORDER_STYLE_KEY, BorderStyle.SEPARATOR);

        builder.addGap(2);
        //builder.add(history().createGoBackButton());
        //builder.add(history().createGoNextButton());
        //builder.addGap(LARGE_GAP);
        builder.add(createToolBarButton(Actions.OPEN_PROJECT_ID));
        builder.add(createToolBarButton(Actions.SAVE_ID));
        builder.add(createToolBarButton(Actions.PRINT_ID));
        builder.addLargeGap();
        builder.add(createToolBarButton(Actions.ADD_GOAL_ID));
        builder.add(createToolBarButton(Actions.ADD_ALTERNATIVE_ID));
        builder.addLargeGap();
        builder.addGlue();
        builder.addToggle((ToggleAction)ActionManager.get(Actions.SHOW_HELP_NAVIGATOR_ID));
        builder.add(com.jgoodies.uif.action.ActionManager
                .get(Actions.OPEN_HELP_CONTENTS_ID));
        builder.addGap(2);
        return builder.getToolBar();
    }

    /**
     * Creates and returns a button which is suitable for use in a tool bar.
     * 
     * @param actionID
     *            the action id to crete the button for
     * @return toolbar button
     * @see de.uniessen.wiinf.wip.goalgetter.model.Actions
     */
    private AbstractButton createToolBarButton(String actionID) {
        return new ToolBarButton(ActionManager.get(actionID));
    }

}