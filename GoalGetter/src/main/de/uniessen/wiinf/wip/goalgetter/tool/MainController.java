/*
 * MainController.java
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
 * ---
 * 
 * This project uses the JGoodies Framework and its Skeleton Pro Application
 * Copyright (c) 2002-2004 JGoodies Karsten Lentzsch. All Rights Reserved.
 * See Readme file for detailed license
 * 
 * $Id: MainController.java,v 1.3 2004/07/12 12:37:57 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.tool;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.jgoodies.uif.application.AbstractMainFrame;
import com.jgoodies.uif.application.Application;
import com.jgoodies.uifextras.convenience.DefaultAboutDialog;
import com.jgoodies.uifextras.convenience.SetupManager;
import com.jgoodies.uifextras.convenience.TipOfTheDayDialog;

import de.uniessen.wiinf.wip.goalgetter.domain.Project;
import de.uniessen.wiinf.wip.goalgetter.domain.ProjectFactory;
import de.uniessen.wiinf.wip.goalgetter.view.preferences.PreferencesDialog;

/**
 * Provides all application-level behavior. Most of the methods in this class
 * will be invoked by <code>AbstractActions</code> as defined in the
 * <code>Actions</code> class.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.3 $
 *  
 */
public final class MainController {
    
  

    /**
     * Refers to the module that provides all high-level models. Used to modify
     * the project and access the domain object tree.
     * 
     * @see #getMainModule()
     */
    private final MainModule mainModule;

    /**
     * Holds a frame that is used as default parent frame for message dialogs.
     * 
     * @see #getDefaultParentFrame()
     * @see #setDefaultParentFrame(Frame)
     */
    private Frame defaultParentFrame;

    // Instance Creation ******************************************************

    /**
     * Constructs the <code>MainController</code> for the given main module.
     * Many methods require that the default parent frame is set once it is
     * available.
     * 
     * @param mainModule
     *            provides bound properties and high-level models
     * 
     * @see #setDefaultParentFrame(Frame)
     */
    public MainController(MainModule mainModule) {
        this.mainModule = mainModule;
    }

    // Action Behavior ******************************************************

    /**
     * Creates a new project and sets it in the main module. Uses a sample
     * project for demoing purposes.
     */
    void newProject() {
        getMainModule().setProject(ProjectFactory.createSample());
    }

    /**
     * Opens a file chooser, loads a project from a file, and sets it in the
     * main module. Uses a sample project in this demo.
     */
    void openProject() {
        FileDialog dialog = new FileDialog(getDefaultParentFrame(),
                "Open project file");
        dialog.show();
        if (dialog.getFile() == null)
            return;

        System.out.println("You've choosen to open this file: "
                + dialog.getFile());
        getMainModule()
                .setProject(Project.readFrom(new File(dialog.getFile())));
    }

    /**
     * Saves the project to the current file.
     */
    void save() {
        showMessage("Save performed.");
    }

    /**
     * Asks the user for a file name and saves the project to that file.
     */
    void saveAs() {
        showMessage("Save as performed.");
    }

    /**
     * Prints a project summary.
     */
    void print() {
        showMessage("Print performed.");
    }

    /**
     * Opens a print dialog, then prints a project summary to the choosen
     * printer.
     */
    void openPrintDialog() {
        showMessage("Open print dialog performed.");
    }

    /**
     * Opens the preferences dialog.
     */
    void openPreferencesDialog() {
        new PreferencesDialog(getDefaultParentFrame(), getMainModule()
                .getPresentationSettings()).open();
    }

    /**
     * Invokes the application shutdown mechanism. Currently it uses the poor
     * assumption that the default parent frame is an instance of
     * <code>AbstractMainFrame</code>.
     * <p>
     * 
     * TODO: Rewrite once the shutdown mechanism uses bound properties in the
     * Workbench, not the AbstractMainFrame.
     *  
     */
    void aboutToExitApplication() {
        ((AbstractMainFrame) getDefaultParentFrame()).aboutToExitApplication();
    }

    /**
     * Adds a goal as child under the selected node.
     */
    void addGoal() {
        mainModule.addGoal();

        showMessage("Add goal performed.");
    }

    /**
     * Adds a alternative as child under the selected node.
     */
    void addAlternative() {
        mainModule.addAlternative();
        showMessage("Add alternative performed.");
    }

    /**
     * Adds a action as child under the selected node.
     */
    void addAction() {
        // mainModule().addActionNode();
        showMessage("Add action performed.");
    }

    /**
     * Deletes the selected item.
     */
    void deleteItem() {
        mainModule.removeSelectedItem();
        showMessage("Item removed.");
    }

    /**
     * Adds a segment as child under the selected node.
     */
    void showSensitivityAnalysis() {
        // mainModule().addActionNode();
        showMessage("Show Sensitivity Analysis performed.");
    }

    /**
     * Adds a segment as child under the selected node.
     */
    void showReport() {
        // mainModule().addActionNode();
        showMessage("Show Report performed.");
    }

    /**
     * Checks if we shall show a tip of the day: asks the TipOfTheDayDialog
     * whether it is enabled, and the SetupManager, if we are not running for
     * the first time. We don't want to disturb the user the first time, where
     * we already have opened some extra panels from the setup process.
     * <p>
     * Opens the tip of the day dialog in the event dispatch thread.
     */
    public void checkForOpenTipOfTheDayDialog() {
        if ((SetupManager.usageCount() > 1)
                && (TipOfTheDayDialog.isShowingTips())) {
            SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    openTipOfTheDayDialog();
                }
            });
        }
    }

    /**
     * Opens the tip-of-the-day dialog.
     */
    void openTipOfTheDayDialog() {
        String tipIndexPath = Application.getGlobals().getTipIndexPath();
        new TipOfTheDayDialog(getDefaultParentFrame(), tipIndexPath).open();
    }

    /**
     * Opens the about dialog.
     */
    void openAboutDialog() {
        new DefaultAboutDialog(getDefaultParentFrame()).open();
    }

    // Help View ToolBar Actions --------------------------------------------

    /**
     * Closes the view for the dynamic help.
     */
    void closeDynamicHelp() {
        getMainModule().getHelpModule().setHelpVisible(false);
    }
    
    /**
     * Closes the view for the dynamic help.
     */
    void closeDynamicHelpNavigator() {
        closeDynamicHelp();
        showMessage("help navigator closed");
    }

    // Helper Code **********************************************************

    /**
     * Show a message.
     */
    private void showMessage(String what) {
        JOptionPane.showMessageDialog(getDefaultParentFrame(), what);
    }

    // Accessing Collaborators **********************************************

    private MainModule getMainModule() {
        return mainModule;
    }

    private Frame getDefaultParentFrame() {
        return defaultParentFrame;
    }

    /**
     * Sets a frame as the default parent for this controller's dialogs.
     * 
     * @param defaultParentFrame
     *            the default parent
     */
    public void setDefaultParentFrame(Frame defaultParentFrame) {
        this.defaultParentFrame = defaultParentFrame;
    }

}