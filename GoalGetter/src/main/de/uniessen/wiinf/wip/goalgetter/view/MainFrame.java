/*
 * MainFrame.java
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
 * $Id: MainFrame.java,v 1.8 2004/09/09 18:19:18 jsprenger Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view;

import java.awt.Dimension;
import java.util.prefs.Preferences;

import javax.swing.JComponent;
import javax.swing.JMenuBar;

import com.jgoodies.uif.application.AbstractMainFrame;
import com.jgoodies.uif.application.Application;

import de.uniessen.wiinf.wip.goalgetter.tool.MainModule;

/**
 * 
 * The main frame of the GoalGetter application. It creates the menus, menu bar,
 * tool bar and all subpanels.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.8 $
 *  
 */
public final class MainFrame extends AbstractMainFrame {

    private static final long serialVersionUID = 1L;

    private static final Dimension MINIMUM_SIZE = new Dimension(500, 400);

    /**
     * Bound Bean Property <code>PROPERTYNAME_HELP_NAVIGATOR_VISIBLE</code>
     */
    public static final String PROPERTYNAME_HELP_NAVIGATOR_VISIBLE = "helpNavigatorVisible"; //$NON-NLS-1$

    /**
     * Refers to the module that provides all high-level models. Used to build
     * this frame's main page.
     * 
     * @see #buildContentPane()
     */
    private final MainModule module;

    private MainPageBuilder mainPageBuilder;

    // Instance Creation ****************************************************

    /**
     * Constructs an instance of the app's main frame.
     * 
     * @param mainModule
     *            provides bound properties and high-level models
     */
    public MainFrame(MainModule mainModule) {
        super(Application.getDescription().getWindowTitle());
        this.module = mainModule;
    }

    // Building *************************************************************

    /**
     * Builds this <code>MainFrame</code>. Firstly, it executes the
     * superclass behavior, then sets the menu bar, registers help sets, and
     * finally makes the help view invisible.
     */
    public void build() {
        super.build();
        setJMenuBar(buildMenuBar());
    }

    /**
     * Builds this frame's content pane.
     */
    protected JComponent buildContentPane() {
        mainPageBuilder = new MainPageBuilder(module);
        return mainPageBuilder.build();
    }

    /**
     * Builds and returns the menu bar.
     * 
     * @return built menu bar
     */
    private JMenuBar buildMenuBar() {
        return new MainMenuBuilder().build();
    }

    /**
     * Returns the frame's minimum size. It is used by the WindowUtils to resize
     * the window if the user has shrinked the window below this given size.
     * 
     * @return the frame's minimum size
     * @see com.jgoodies.uif.application.AbstractMainFrame#getWindowMinimumSize()
     */
    public Dimension getWindowMinimumSize() {
        return MINIMUM_SIZE;
    }

    // Storing State ********************************************************

    /**
     * Restores the frame's state from the user preferences.
     * <p>
     * 
     * TODO: We should better not invoke the controller's #restoreState here,
     * instead, the controller should listen to a Workbench startup event.
     */
    protected void restoreState() {
        super.restoreState();
        module.restoreState();
        Preferences userPrefs = Application.getUserPreferences();
        mainPageBuilder().restoreFrom(userPrefs);
    }

    /**
     * Stores the frame's state in the user preferences.
     * <p>
     * 
     * TODO: We should better not invoke the controller's #storeState here,
     * instead, the controller should listen to a Workbench shutdown event.
     */
    public void storeState() {
        super.storeState();
        System.out.println("der mainframe speichert den status");
        module.storeState();
        Preferences userPrefs = Application.getUserPreferences();
        mainPageBuilder().storeIn(userPrefs);
    }

    // Accessing Collaborators **********************************************

    /*
     * Returns the main page.
     */
    public MainPageBuilder mainPageBuilder() {
        return mainPageBuilder;
    }

}