/*
 * MainMenuBuilder.java
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
 * $Id: MainMenuBuilder.java,v 1.6 2004/08/16 11:25:22 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import com.jgoodies.looks.BorderStyle;
import com.jgoodies.looks.HeaderStyle;
import com.jgoodies.looks.Options;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.windows.WindowsLookAndFeel;
import com.jgoodies.uif.action.ActionManager;
import com.jgoodies.uif.action.ToggleAction;
import com.jgoodies.uif.builder.MenuBuilder;
import com.jgoodies.uif.osx.OSXApplicationMenu;
import com.jgoodies.uif.util.ResourceUtils;

import de.uniessen.wiinf.wip.goalgetter.tool.Actions;

/**
 * 
 * Builds the <code>JMenuBar</code> and pull-down menus
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.6 $
 *  
 */
public final class MainMenuBuilder {

    /**
     * Configures, composes, and returns the menu bar.
     * 
     * @return built menubar
     */
    JMenuBar build() {
        JMenuBar menuBar = new JMenuBar();

        // Set a hint so that JGoodies Looks will detect it as being in the
        // header.
        menuBar.putClientProperty(Options.HEADER_STYLE_KEY, HeaderStyle.BOTH);
        // Unlike the default, use a separator border.
        menuBar.putClientProperty(WindowsLookAndFeel.BORDER_STYLE_KEY,
                BorderStyle.SEPARATOR);
        menuBar.putClientProperty(PlasticLookAndFeel.BORDER_STYLE_KEY,
                BorderStyle.SEPARATOR);

        menuBar.add(buildFileMenu());
        menuBar.add(buildNavigatorMenu());
        menuBar.add(buildReportMenu());
        menuBar.add(buildHelpMenu());
        return menuBar;
    }

    /**
     * Builds and returns the File menu.
     * 
     * @return built file menu
     */
    private JMenu buildFileMenu() {
        MenuBuilder builder = new MenuBuilder(ResourceUtils
                .getString("menubar.file.text"), ResourceUtils.getString(//$NON-NLS-1$
                "menubar.file.mnemonic").charAt(0));//$NON-NLS-1$
        builder.add(ActionManager.get(Actions.NEW_PROJECT_ID));
        builder.add(ActionManager.get(Actions.OPEN_PROJECT_ID));
        builder.addSeparator();
        builder.add(ActionManager.get(Actions.SAVE_ID));
        builder.add(ActionManager.get(Actions.SAVE_AS_ID));
        builder.addSeparator();
        builder.add(ActionManager.get(Actions.OPEN_PAGE_SETUP_ID));
        builder.add(ActionManager.get(Actions.OPEN_PRINT_DIALOG_ID));
        if (!OSXApplicationMenu.isRegisteredPreferences()) {
            builder.addSeparator();
            builder.add(ActionManager.get(Actions.OPEN_PREFERENCES_ID));
        }
        if (!OSXApplicationMenu.isRegisteredQuit()) {
            builder.addSeparator();
            builder.add(ActionManager.get(Actions.EXIT_ID));
        }
        return builder.getMenu();
    }

    /**
     * Builds and returns the Navigator menu.
     * 
     * @return built Navigator menu
     */
    private JMenu buildNavigatorMenu() {
        MenuBuilder builder = new MenuBuilder(ResourceUtils
                .getString("menubar.components.text"), ResourceUtils.getString(//$NON-NLS-1$
                "menubar.components.mnemonic").charAt(0));//$NON-NLS-1$
        builder.add(ActionManager.get(Actions.ADD_GOAL_ID));
        builder.add(ActionManager.get(Actions.ADD_ALTERNATIVE_ID));
        //builder.add(ActionManager.get(Actions.ADD_ACTION_ID));
        builder.add(ActionManager.get(Actions.DELETE_ITEM_ID));
        return builder.getMenu();
    }

    /**
     * Builds and returns the Report menu.
     * 
     * @return built report menu
     */
    private JMenu buildReportMenu() {
        MenuBuilder builder = new MenuBuilder(ResourceUtils
                .getString("menubar.reports.text"), ResourceUtils.getString(//$NON-NLS-1$
                "menubar.reports.mnemonic").charAt(0));//$NON-NLS-1$
        builder.add(ActionManager.get(Actions.SHOW_REPORT_ID));
        builder.add(ActionManager.get(Actions.SHOW_SENSITIVITYANALYSIS_ID));
        return builder.getMenu();
    }

    /**
     * Builds and returns the Help menu.
     * 
     * @return built help menu
     */
    private JMenu buildHelpMenu() {
        MenuBuilder builder = new MenuBuilder(ResourceUtils
                .getString("menubar.help.text"), ResourceUtils.getString(//$NON-NLS-1$
                "menubar.help.mnemonic").charAt(0));//$NON-NLS-1$
        builder.addToggle((ToggleAction)ActionManager.get(Actions.SHOW_HELP_NAVIGATOR_ID));
        builder.add(ActionManager.get(Actions.OPEN_HELP_CONTENTS_ID));
        builder.addSeparator();
        builder.add(ActionManager.get(Actions.OPEN_TIP_OF_THE_DAY_ID));
        if (!OSXApplicationMenu.isRegisteredAbout()) {
            builder.addSeparator();
            builder.add(ActionManager.get(Actions.OPEN_ABOUT_DIALOG_ID));
        }
        return builder.getMenu();
    }

}