/*
 * GoalGetter.java
 * Package: de.uniessen.wiinf.wip.goalgetter
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
 * $Id: GoalGetter.java,v 1.2 2004/07/19 18:22:02 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.UIManager;
import javax.swing.plaf.DimensionUIResource;

import com.jgoodies.looks.FontSizeHints;
import com.jgoodies.looks.Options;
import com.jgoodies.uif.action.ActionManager;
import com.jgoodies.uif.application.AbstractMainFrame;
import com.jgoodies.uif.application.Application;
import com.jgoodies.uif.application.Globals;
import com.jgoodies.uif.osx.OSXApplicationMenu;
import com.jgoodies.uif.splash.ImageSplash;
import com.jgoodies.uif.splash.Splash;
import com.jgoodies.uifextras.convenience.DefaultApplicationStarter;

import de.uniessen.wiinf.wip.goalgetter.tool.Actions;
import de.uniessen.wiinf.wip.goalgetter.tool.MainController;
import de.uniessen.wiinf.wip.goalgetter.tool.MainModule;
import de.uniessen.wiinf.wip.goalgetter.tool.help.HelpSets;
import de.uniessen.wiinf.wip.goalgetter.view.MainFrame;

/**
 * 
 * This is the main class of the GoalGetter application.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.2 $
 *  
 */

public final class GoalGetter extends DefaultApplicationStarter {

    private MainModule mainModule;

    private MainController mainController;

    /**
     * Configures the splash to set a brown progress bar.
     */
    protected void configureSplash() {
        super.configureSplash();
        ImageSplash splash = (ImageSplash) Splash.getProvider();
        splash.setProgressBarBounds(60);
        splash.setForeground(new Color(64, 64, 64));
        splash.setBackground(Color.white);
    }

    /**
     * Configures the user interface.
     */
    protected void configureUI() {
        Options.setUseSystemFonts(true);
        Options.setGlobalFontSizeHints(FontSizeHints.MIXED2);
        Options.setDefaultIconSize(new Dimension(18, 18));
        Options.setPopupDropShadowEnabled(true);
        UIManager.put("ToolBar.separatorSize", new DimensionUIResource(6, 18));
        super.configureUI();
    }

    /**
     * Creates and returns the application's main frame.
     */
    protected AbstractMainFrame createMainFrame() {
        return new MainFrame(mainModule);
    }

    /**
     * Initializes the actions
     */
    protected void initializeActions() {
        Actions.initializeFor(mainModule, mainController);
        OSXApplicationMenu.register(ActionManager
                .get(Actions.OPEN_ABOUT_DIALOG_ID), ActionManager
                .get(Actions.OPEN_PREFERENCES_ID), ActionManager
                .get(Actions.EXIT_ID));
    }

    /**
     * Brings up the application, it therefore initializes the main frame,
     * checks the setup process, initializes all actions, then builds the main
     * frame, and finally opens it.
     */
    protected void launchApplication() {
        addMessageHandler();

        // Create the module that provides all high-level models.
        Splash.setNote("Creating Models", 30);
        mainModule = new MainModule();

        // Create the controller that provides the major operations.
        Splash.setNote("Creating Controller", 40);
        mainController = new MainController(mainModule);

        // Initialize all Actions
        Splash.setNote("Initializing Actions...", 50);
        initializeActions();

        HelpSets.registerHelpSets(mainModule.getHelpModule());

        // Create and build the main frame.
        AbstractMainFrame mainFrame = createMainFrame();
        Application.setMainFrame(mainFrame);

        // Set the main frame as default parent for dialogs.
        mainController.setDefaultParentFrame(mainFrame);

        checkSetup();

        Splash.setNote("Building UI...", 60);
        mainFrame.build();

        Splash.setNote("Finishing...", 90);
        mainFrame.open();
        mainController.checkForOpenTipOfTheDayDialog();
    }

    /**
     * Defines a bunch of application wide constants, and launches the boot
     * process for the GoalGetter application.
     */
    public static void main(String[] arguments) {
        OSXApplicationMenu.setAboutName("GoalGetter");
        new GoalGetter()
                .boot(new Globals(
                        "GoalGetter", // Application name
                        "goalgetter", // prefix for logs and prefs
                        "0.1", // version
                        "0.1.3 (July-19-2004)", // full version
                        "A Decision supporting system using the GoalGetter Method by Markus Stallkamp", // description
                        "\u00a9 2004", // copyright
                        "Universität Duisburg-Essen", // vendor
                        "de/uniessen/wiinf/wip/goalgetter/", // resource
                        // properties URL
                        "docs/help/global/Help.hs", // helpset URL
                        "docs/help/tips/index.txt", // tip index path
                        "http://www.wip.uni-essen.de/", // vendor URL
                        "tim.franz@uni-essen.de")); // support eMail
    }

}