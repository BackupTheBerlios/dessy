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
 * $Id: GoalGetter.java,v 1.6 2004/09/08 18:31:34 moleman Exp $
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
import com.jgoodies.uif.application.ApplicationConfiguration;
import com.jgoodies.uif.application.ApplicationDescription;
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
 * @version $Revision: 1.6 $
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
        UIManager.put("ToolBar.separatorSize", new DimensionUIResource(6, 18));//$NON-NLS-1$
        super.configureUI();
    }

    /**
     * Creates and returns the application's main frame.
     * 
     * @return new MainFrame
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
        Splash.setNote("Creating Models", 30);//$NON-NLS-1$
        mainModule = new MainModule();

        // Create the controller that provides the major operations.
        Splash.setNote("Creating Controller", 40);//$NON-NLS-1$
        mainController = new MainController(mainModule);

        // Initialize all Actions
        Splash.setNote("Initializing Actions...", 50);//$NON-NLS-1$
        initializeActions();

        HelpSets.registerHelpSets(mainModule.getHelpModule());

        // Create and build the main frame.
        AbstractMainFrame mainFrame = createMainFrame();
        Application.setMainFrame(mainFrame);

        // Set the main frame as default parent for dialogs.
        mainController.setDefaultParentFrame(mainFrame);

        checkSetup();

        Splash.setNote("Building UI...", 60);//$NON-NLS-1$
        mainFrame.build();

        Splash.setNote("Finishing...", 90);//$NON-NLS-1$
        mainFrame.open();
        mainController.checkForOpenTipOfTheDayDialog();
    }

    /**
     * Defines a bunch of application wide constants, and launches the boot
     * process for the GoalGetter application.
     * 
     * @param arguments
     *            arguments passed from command line
     */
    public static void main(String[] arguments) {
        OSXApplicationMenu.setAboutName("GoalGetter");//$NON-NLS-1$ 
        new GoalGetter()
                .boot(
                        new ApplicationDescription(
                                "GoalGetter", //$NON-NLS-1$ // Application name
                                "GoalGetter", //$NON-NLS-1$ // Application short name
                                "0.1",//$NON-NLS-1$ // version
                                "0.1.5 (August-23-2004)",//$NON-NLS-1$ // full version
                                "A Decision supporting system using the GoalGetter Method by Markus Stallkamp",//$NON-NLS-1$ // description
                                "\u00a9 2004",//$NON-NLS-1$ // copyright
                                "Universität Duisburg-Essen", //$NON-NLS-1$ // vendor
                                "http://www.wip.uni-essen.de/",//$NON-NLS-1$ // vendor URL
                                "tim.franz@uni-essen.de"),//$NON-NLS-1$ // support eMail
                        new ApplicationConfiguration("goalgetter",//$NON-NLS-1$ // prefix for logs and prefs  
                                "de/uniessen/wiinf/wip/goalgetter/",//$NON-NLS-1$ // resource properties URL
                                "docs/help/global/Help.hs",//$NON-NLS-1$ // helpset URL
                                "docs/help/tips/index.txt")//$NON-NLS-1$ // tip index path
                );

    }

}