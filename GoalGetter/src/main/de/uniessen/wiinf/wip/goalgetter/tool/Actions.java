/*
 * Actions.java
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
 * $Id: Actions.java,v 1.1 2004/07/03 20:17:08 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.tool;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.jgoodies.uif.action.ActionManager;
import com.jgoodies.uifextras.help.HelpBroker;
import com.jgoodies.uifextras.printing.PrintManager;

import de.uniessen.wiinf.wip.goalgetter.domain.ActionContainer;
import de.uniessen.wiinf.wip.goalgetter.domain.AlternativeContainer;
import de.uniessen.wiinf.wip.goalgetter.domain.Description;
import de.uniessen.wiinf.wip.goalgetter.domain.GoalContainer;
import de.uniessen.wiinf.wip.goalgetter.domain.Project;

/**
 * 
 * Provides all UI <code>Actions</code> and their IDs. Therefore it declares
 * static fields for action ids and implementations of the <code>Action</code>
 * interface. These actions are registered to the <code>ActionManager</code>.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */

public final class Actions {

    // Action IDs *************************************************************

    public static final String NEW_PROJECT_ID = "newProject";

    public static final String OPEN_PROJECT_ID = "openProject";

    public static final String SAVE_ID = "save";

    public static final String SAVE_AS_ID = "saveAs";

    public static final String OPEN_PAGE_SETUP_ID = "openPageSetup";

    public static final String PRINT_ID = "print";

    public static final String OPEN_PRINT_DIALOG_ID = "openPrintDialog";

    public static final String OPEN_PREFERENCES_ID = "openPreferences";

    public static final String EXIT_ID = "exit";

    public static final String ADD_GOAL_ID = "addGoal";

    public static final String ADD_ACTION_ID = "addAction";

    public static final String ADD_ALTERNATIVE_ID = "addAlternative";

    public static final String DELETE_ITEM_ID = "deleteItem";

    public static final String SHOW_REPORT_ID = "showReport";

    public static final String SHOW_SENSITIVITYANALYSIS_ID = "showSensitivityAnalysis";

    public static final String OPEN_HELP_CONTENTS_ID = "openHelpContents";

    public static final String OPEN_TIP_OF_THE_DAY_ID = "openTipOfTheDay";

    public static final String OPEN_ABOUT_DIALOG_ID = "openAboutDialog";

    public static final String CLOSE_HELP_VIEW_ID = "helpView.close";

    // Instance Fields ********************************************************

    /**
     * Refers to the controller that is used to forward all action behavior to.
     * 
     * @see #getController()
     */
    private final MainController controller;

    // Instance Creation ******************************************************

    /**
     * Initializes the actions used in this application. Registers all actions
     * with the <code>ActionManager</code> and observes changes in the main
     * module's selection and project to update the enablement of some actions.
     * 
     * @param mainModule
     *            provides bound properties for the selection and project
     * @param controller
     *            used to forward all action behavior
     */
    private Actions(MainModule mainModule, MainController controller) {
        this.controller = controller;
        registerActions();
        mainModule.addPropertyChangeListener(new ModuleChangeHandler());
        updateSelectionActionEnablement(null);
        updateProjectActionEnablement(null);
    }

    /**
     * Initializes the actions used in this application. Registers all actions
     * with the <code>ActionManager</code> and observes changes in the main
     * module's selection and project to update the enablement of some actions.
     * 
     * @param mainModule
     *            provides bound properties for the selection and project
     * @param controller
     *            used to forward all action behavior
     */
    public static void initializeFor(MainModule mainModule,
            MainController controller) {
        new Actions(mainModule, controller);
    }

    /**
     * Registers <code>Action</code> s at the <code>ActionManager</code>
     * using three different styles for demoing purposes, see class comment.
     */
    private void registerActions() {
        registerActionsViaAnonymousClasses();
        registerActionsViaStaticClasses();
        registerActionsViaDispatchingClass();
    }

    //***********************************************************************
    // Style 1: Anonymous AbstractAction Subclasses
    //***********************************************************************

    /**
     * Registers actions in the <code>ActionManager</code> using a bunch of
     * anonymous classes.
     */
    private void registerActionsViaAnonymousClasses() {
        ActionManager.register(NEW_PROJECT_ID, new AbstractAction() {

            public void actionPerformed(ActionEvent event) {
                getController().newProject();
            }
        });
        ActionManager.register(OPEN_PROJECT_ID, new AbstractAction() {

            public void actionPerformed(ActionEvent event) {
                getController().openProject();
            }
        });
        ActionManager.register(SAVE_ID, new AbstractAction() {

            public void actionPerformed(ActionEvent event) {
                getController().save();
            }
        }).setEnabled(false);
        ActionManager.register(SAVE_AS_ID, new AbstractAction() {

            public void actionPerformed(ActionEvent event) {
                getController().saveAs();
            }
        }).setEnabled(false);
        ActionManager.register(OPEN_PAGE_SETUP_ID, new AbstractAction() {

            public void actionPerformed(ActionEvent event) {
                PrintManager.openPageSetupDialog();
            }
        });
        ActionManager.register(PRINT_ID, new AbstractAction() {

            public void actionPerformed(ActionEvent event) {
                getController().print();
            }
        }).setEnabled(false);
        ActionManager.register(OPEN_PRINT_DIALOG_ID, new AbstractAction() {

            public void actionPerformed(ActionEvent event) {
                getController().openPrintDialog();
            }
        }).setEnabled(false);
        ActionManager.register(OPEN_PREFERENCES_ID, new AbstractAction() {

            public void actionPerformed(ActionEvent event) {
                getController().openPreferencesDialog();
            }
        });
        ActionManager.register(EXIT_ID, new AbstractAction() {

            public void actionPerformed(ActionEvent event) {
                getController().aboutToExitApplication();
            }
        });
        ActionManager.register(CLOSE_HELP_VIEW_ID, new AbstractAction() {

            public void actionPerformed(ActionEvent event) {
                getController().closeDynamicHelp();
            }
        });
    }

    //***********************************************************************
    // Style 2: Private Static AbstractAction Subclass
    //***********************************************************************

    private static class OpenHelpContentsAction extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            HelpBroker.openDefault();
        }
    }

    private class OpenTipOfTheDayAction extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            getController().openTipOfTheDayDialog();
        }
    }

    private class OpenAboutDialogAction extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            getController().openAboutDialog();
        }
    }

    /**
     * Register actions in the <code>ActionManager</code> using nested private
     * top-level class(es).
     */
    private void registerActionsViaStaticClasses() {
        ActionManager.register(OPEN_HELP_CONTENTS_ID,
                new OpenHelpContentsAction());
        ActionManager.register(OPEN_TIP_OF_THE_DAY_ID,
                new OpenTipOfTheDayAction());
        ActionManager.register(OPEN_ABOUT_DIALOG_ID,
                new OpenAboutDialogAction());
    }

    //***********************************************************************
    // Style 3: Single Class That Dispatches Over an Action ID
    //***********************************************************************

    /*
     * An implementation of the <code> Action </code> interface that dispatches
     * using an ID.
     */
    private class DispatchingAction extends AbstractAction {

        private final String id;

        /*
         * Constructs a <code> DispatchingAction </code> .
         */
        private DispatchingAction(String id) {
            this.id = id;
        }

        /**
         * Dispatches using the stored id, then delegates to the appropriate
         * method of the application controller, that finally does the real
         * work.
         */
        public void actionPerformed(ActionEvent event) {
            if (id.equals(ADD_GOAL_ID)) {
                getController().addGoal();
            } else if (id.equals(ADD_ALTERNATIVE_ID)) {
                getController().addAlternative();
            } else if (id.equals(ADD_ACTION_ID)) {
                getController().addAction();
            } else if (id.equals(DELETE_ITEM_ID)) {
                getController().deleteItem();
            } else if (id.equals(SHOW_SENSITIVITYANALYSIS_ID)) {
                getController().showSensitivityAnalysis();
            } else if (id.equals(SHOW_REPORT_ID)) {
                getController().showReport();
            } else {
                Logger.getLogger("Actions").warning("Unknown action: " + id);
            }
        }

    }

    /**
     * Register actions in the <code>ActionManager</code> using instances of a
     * dispatching <code>Action</code> implementation.
     */
    private void registerActionsViaDispatchingClass() {
        register(ADD_GOAL_ID);
        register(ADD_ALTERNATIVE_ID);
        register(ADD_ACTION_ID);
        register(DELETE_ITEM_ID);
        register(SHOW_REPORT_ID);
        register(SHOW_SENSITIVITYANALYSIS_ID);
    }

    // Helper Code **********************************************************

    /**
     * Registers a <code>DispatchingAction</code> with the
     * <code>ActionManager</code> using the given id.
     */
    private void register(String id) {
        register(id, true);
    }

    /**
     * Registers a <code>DispatchingAction</code> with the
     * <code>ActionManager</code> using the given id and enablement.
     */
    private void register(String id, boolean enabled) {
        Action action = new DispatchingAction(id);
        ActionManager.register(id, action);
        action.setEnabled(enabled);
    }

    // Updating the Action Enablement *****************************************

    /**
     * Updates the enablement state actions realted to the node selection.
     * 
     * @param selection
     *            the selected domain object
     */
    private void updateSelectionActionEnablement(Object selection) {
        boolean canAddGoal = selection != null;
        boolean canAddAlternative = selection != null;
        boolean canDelete = selection != null
                && !(selection instanceof Description)
                && !(selection instanceof GoalContainer)
                && !(selection instanceof ActionContainer)
                && !(selection instanceof AlternativeContainer)
                && !(selection instanceof de.uniessen.wiinf.wip.goalgetter.domain.Action);

        ActionManager.get(Actions.ADD_GOAL_ID).setEnabled(canAddGoal);
        ActionManager.get(Actions.ADD_ALTERNATIVE_ID).setEnabled(
                canAddAlternative);
        //   ActionManager.get(Actions.ADD_ACTION_ID).setEnabled(canAddComponent);
        ActionManager.get(Actions.DELETE_ITEM_ID).setEnabled(canDelete);
    }

    /**
     * Updates the enablement of actions that are related to the project state.
     * 
     * @param project
     *            the current project
     */
    private void updateProjectActionEnablement(Project project) {
        boolean enabled = project != null;
        ActionManager.get(Actions.SAVE_ID).setEnabled(enabled);
        ActionManager.get(Actions.SAVE_AS_ID).setEnabled(enabled);
        ActionManager.get(Actions.PRINT_ID).setEnabled(enabled);
        ActionManager.get(Actions.OPEN_PRINT_DIALOG_ID).setEnabled(enabled);
        ActionManager.get(Actions.SHOW_REPORT_ID).setEnabled(enabled);
        ActionManager.get(Actions.SHOW_SENSITIVITYANALYSIS_ID).setEnabled(
                enabled);
    }

    // Listens to changes in the navigation selection and update enablements.
    private class ModuleChangeHandler implements PropertyChangeListener {

        /**
         * The selection in the navigation tree has changed. Updates the add and
         * delete actions.
         * 
         * @param evt
         *            describes the property change
         */
        public void propertyChange(PropertyChangeEvent evt) {
            String propertyName = evt.getPropertyName();
            if (MainModule.PROPERTYNAME_SELECTION.equals(propertyName)) {
                updateSelectionActionEnablement(evt.getNewValue());
            } else if (MainModule.PROPERTYNAME_PROJECT.equals(propertyName)) {
                updateProjectActionEnablement((Project) evt.getNewValue());
            }
        }
    }

    // Accessing Collaborators **********************************************

    MainController getController() {
        return controller;
    }

}