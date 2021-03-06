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
 * $Id: MainController.java,v 1.4 2004/10/05 11:00:30 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.model;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.jgoodies.uif.application.AbstractMainFrame;
import com.jgoodies.uif.application.Application;
import com.jgoodies.uifextras.convenience.DefaultAboutDialog;
import com.jgoodies.uifextras.convenience.SetupManager;
import com.jgoodies.uifextras.convenience.TipOfTheDayDialog;
import com.jgoodies.uifextras.printing.PrintManager;

import de.uniessen.wiinf.wip.goalgetter.domain.Action;
import de.uniessen.wiinf.wip.goalgetter.domain.Alternative;
import de.uniessen.wiinf.wip.goalgetter.domain.Constants;
import de.uniessen.wiinf.wip.goalgetter.domain.Project;
import de.uniessen.wiinf.wip.goalgetter.domain.ProjectFactory;
import de.uniessen.wiinf.wip.goalgetter.model.file.GoalGetterFileFilter;
import de.uniessen.wiinf.wip.goalgetter.view.MainFrame;
import de.uniessen.wiinf.wip.goalgetter.view.file.GoalGetterFileView;
import de.uniessen.wiinf.wip.goalgetter.view.preferences.PreferencesDialog;
import de.uniessen.wiinf.wip.goalgetter.view.sensitivity.SensitivityAnalysisDialog;
import de.uniessen.wiinf.wip.goalgetter.view.sensitivity.SensitivityElements;

/**
 * Provides all application-level behavior. Most of the methods in this class
 * will be invoked by <code>AbstractActions</code> as defined in the
 * <code>Actions</code> class.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.4 $
 *  
 */
public final class MainController {

    /**
     * Refers to the module that provides all high-level models. Used to modify
     * the project and access the domain object tree.
     * 
     * @see #getMainModule()
     */
    private final MainModel mainModule;

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
    public MainController(MainModel mainModule) {
        this.mainModule = mainModule;
    }

    // Action Behavior ******************************************************

    /**
     * Creates a new project and sets it in the main module.
     */
    void newProject() {
        getMainModule().setProject(ProjectFactory.createEmpty());
    }

    /**
     * Creates a sample project and sets it in the main module.
     */
    void sampleProject() {
        getMainModule().setProject(ProjectFactory.createSample());
    }

    /**
     * Opens a file chooser, loads a project from a file, and sets it in the
     * main module. Uses a sample project in this demo.
     */
    void openProject() {

        JFileChooser chooser = new JFileChooser();

        GoalGetterFileFilter filter = new GoalGetterFileFilter();
        filter.addExtension(Constants.FILE_EXTENSION);
        filter.setDescription("Goal Getter Decision Project");
        chooser.setFileFilter(filter);

        GoalGetterFileView fileView = new GoalGetterFileView();
        fileView.putIcon(Constants.FILE_EXTENSION, Resources.GOAL_ICON);
        chooser.setFileView(fileView);

        int returnVal = chooser.showOpenDialog(getDefaultParentFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            getMainModule().setProject(
                    Project.readFrom(chooser.getSelectedFile()));
        }
    }

    /**
     * Saves the project to the current file.
     */
    void save() {
        if (getMainModule().getProject().hasFile() == false) {
            saveAs();
        }
        getMainModule().getProject().save();
        //  showMessage("Save performed.");
    }

    /**
     * Asks the user for a file name and saves the project to that file.
     */
    void saveAs() {

        JFileChooser chooser = new JFileChooser();

        GoalGetterFileFilter filter = new GoalGetterFileFilter();
        filter.addExtension(Constants.FILE_EXTENSION);
        filter.setDescription("Goal Getter Decision Project");
        chooser.setFileFilter(filter);

        GoalGetterFileView fileView = new GoalGetterFileView();
        fileView.putIcon(Constants.FILE_EXTENSION, Resources.GOAL_ICON);
        chooser.setFileView(fileView);

        int returnVal = chooser.showSaveDialog(getDefaultParentFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            getMainModule().getProject().saveAs(chooser.getSelectedFile());
        }

    }

    /**
     * Prints a project summary.
     */
    void print() {
        mainModule.print((MainFrame) getDefaultParentFrame());

    }

    /**
     * Opens a print dialog, then prints a project summary to the choosen
     * printer.
     */
    void openPrintDialog() {
        PrintManager.openPageSetupDialog();
        print();
        //showMessage("Open print dialog performed.");
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
        String identifier = JOptionPane.showInputDialog(
                getDefaultParentFrame(), "Please enter the name for the goal");
        if (identifier != null) {
            mainModule.addGoal(identifier);
        }
    }

    /**
     * Adds a alternative as child under the selected node.
     */
    void addAlternative() {
        String identifier = JOptionPane.showInputDialog(
                getDefaultParentFrame(),
                "Please enter the name for the alternative");
        if (identifier != null) {
            mainModule.addAlternative(identifier);
        }
    }

    /**
     * Deletes the selected item.
     */
    void deleteItem() {
        mainModule.removeSelectedItem();
        //  showMessage("Item removed.");

    }

    /**
     * SHows the sensitivity analysis dialog after populating its data with a
     * snapshot of the project's data
     */
    void showSensitivityAnalysis() {
        List col = new ArrayList();
        SensitivityElements e;

        Iterator iterator = getMainModule().getProject().getAlternatives()
                .iterator();

        while (iterator.hasNext()) {
            Alternative anAlternative = (Alternative) iterator.next();

            e = new SensitivityElements(anAlternative.getIdentifier());

            Iterator actionsIterator = getMainModule().getProject()
                    .getActionContainer().getActionsFor(anAlternative)
                    .iterator();
            while (actionsIterator.hasNext()) {
                Action anAction = (Action) actionsIterator.next();
                String name = anAction.getGoal().getName();
                if (name == null) {
                    name = ""; //$NON-NLS-1$
                }
                e.addValues(name, Integer.toString(anAction.paymentAmount()));
            }
            col.add(e);
        }

        new SensitivityAnalysisDialog(getDefaultParentFrame(), col,
                "Alternative", "Zahlung").open();

    }

    /**
     * Adds a segment as child under the selected node.
     */
    void showReport() {
        mainModule.showReport();
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
        String tipIndexPath = Application.getConfiguration().getTipIndexPath();
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
     * Closes the view for the dynamic help navigator.
     */
    void closeDynamicHelpNavigator() {
        getMainModule().getHelpModule().setHelpNavigatorVisible(false);
        getDefaultParentFrame().repaint(); // ensure the correct toggle state
        // for dynamic help menu button is
        // set.
    }

    // Helper Code **********************************************************

    /**
     * Show a message.
     * 
     * @param what
     *            the Message to show
     */
    private void showMessage(String what) {
        JOptionPane.showMessageDialog(getDefaultParentFrame(), what);
    }

    // Accessing Collaborators **********************************************

    /**
     * Answers the main Module
     * 
     * @return main Module
     */
    public MainModel getMainModule() {
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