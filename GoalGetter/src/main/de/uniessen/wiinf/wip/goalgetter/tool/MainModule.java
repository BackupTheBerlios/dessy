/*
 * MainModule.java
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
 * $Id: MainModule.java,v 1.1 2004/07/03 20:17:08 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.tool;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.prefs.Preferences;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.jgoodies.binding.beans.Model;
import com.jgoodies.uif.application.Application;
import com.jgoodies.uifextras.convenience.SetupManager;

import de.uniessen.wiinf.wip.goalgetter.domain.Alternative;
import de.uniessen.wiinf.wip.goalgetter.domain.Goal;
import de.uniessen.wiinf.wip.goalgetter.domain.Project;
import de.uniessen.wiinf.wip.goalgetter.tool.node.NavigationNode;
import de.uniessen.wiinf.wip.goalgetter.tool.node.RootNode;

/**
 * Provides bound bean properties for the project, navigation tree, navigation
 * tree selection, selection type. Refers to the presentation settings and a
 * submodule for the dynamic help.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public final class MainModule extends Model {

    // Names of the Bound Bean Properties *************************************

    public static final String PROPERTYNAME_PROJECT = "project";

    public static final String PROPERTYNAME_SELECTION = "selection";

    public static final String PROPERTYNAME_SELECTION_TYPE = "selectionType";

    public static final String PROPERTYNAME_NAVIGATION_TREE_MODEL = "navigationTreeModel";

    // Instance Fields ********************************************************

    /**
     * Holds the current project.
     * 
     * @see #getProject()
     * @see #setProject(Project)
     */
    private Project project;

    /**
     * Holds the current selection, an instance of Model.
     * 
     * @see #getSelection()
     * @see #getSelectionType()
     */
    private Object selection;

    /**
     * Holds the node that refers to the current selection. Used to report a
     * potential change to its hosting tree model when the selection changes.
     * This is because the node's label may have changed as part of the editor
     * change.
     * <p>
     * 
     * This is a poor workaround that is based on the weak assumumption that
     * this class's tree selection listener is the first to know about the node
     * change.
     * <p>
     * 
     * TODO: Let all nodes observe the relevant domain data that is rendered in
     * the tree view to report a node change.
     */
    private NavigationNode selectedNode;

    /**
     * Holds the class of the current selection.
     * 
     * @see #getSelection()
     * @see #getSelectionType()
     */
    private Class selectionType;

    /**
     * Holds the model for the navigation tree. It changes everytime this
     * module's project changes.
     * 
     * @see #getNavigationTreeModel()
     * @see #getNavigationTreeSelectionModel()
     */
    private TreeModel navigationTreeModel;

    /**
     * Refers to the single selection model for the navigation tree. The
     * selection is reset if the navigation tree model changes.
     * 
     * @see #getNavigationTreeSelectionModel()
     * @see #getNavigationTreeModel()
     */
    private final TreeSelectionModel navigationTreeSelectionModel;

    /**
     * Refers to the submodule that provides bound properties around the dynamic
     * help tree..
     * 
     * @see #getHelpModule()
     */
    private final DynamicHelpModule helpModule;

    /**
     * Refers to the submodule for the UI-related settings.
     * 
     * @see #getPresentationSettings()
     */
    private final PresentationSettings presentationSettings;

    // Instance Creation ******************************************************

    /**
     * Constructs a <code>MainModule</code> that has no project set, no
     * selection and no tree model.
     */
    public MainModule() {
        setNavigationTreeModel(createNavigationTreeModel(new Project("Empty")));
        navigationTreeSelectionModel = new DefaultTreeSelectionModel();
        navigationTreeSelectionModel
                .setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        navigationTreeSelectionModel
                .addTreeSelectionListener(new NavigationTreeSelectionChangeHandler());

        helpModule = new DynamicHelpModule();
        presentationSettings = new PresentationSettings();
        restoreState();

    }

    // Managing Projects ******************************************************

    /**
     * Returns the current project.
     * 
     * @return the current project.
     */
    public Project getProject() {
        return project;
    }

    /**
     * Sets a new project.
     * 
     * @param newProject
     *            the project to set
     * 
     * @throws NullPointerException
     *             if the new project is null
     */
    public void setProject(Project newProject) {
        if (newProject == null)
            throw new NullPointerException("The project must not be null.");

        Project oldProject = getProject();
        project = newProject;
        project.addPropertyChangeListener(Goal.PROPERTYNAME_IDENTIFIER,
                new GoalIdentifierChangeHandler());
        firePropertyChange(PROPERTYNAME_PROJECT, oldProject, newProject);
        setNavigationTreeModel(createNavigationTreeModel(newProject));

    }

    /**
     * Checks and answers if a project is loaded.
     * 
     * @return true if a project is loaded.
     */
    public boolean hasProject() {
        return getProject() != null;
    }

    /**
     * Checks and answers whether the project's file path is valid.
     * 
     * @return true if the project's file path is valid.
     */
    public boolean isProjectFilePathValid() {
        return false;
    }

    // managing decisions *****************************************************

    public void addGoal() {
        Goal g = new Goal("Supergoal");
        project.addGoal(g);
        setNavigationTreeModel(createNavigationTreeModel(project));

    }

    public void addAlternative() {
        Alternative a = new Alternative("SuperAlternative");
        project.addAlternative(a);

        setNavigationTreeModel(createNavigationTreeModel(project));

    }

    // Accessing the Selection and Selection Type *****************************

    /**
     * Returns the current selection, a domain object. This selection will be
     * updated if the selection in the navigation tree changes. During the
     * transition from one tree selection to another this value may hold the old
     * or new selection.
     * 
     * @return the domain object selected in the navigation tree.
     * 
     * @see #getSelectionType()
     */
    public Object getSelection() {
        return selection;
    }

    /**
     * Sets a new domain object as selection. This method is invoked by the
     * NavigationTreeSelectionChangeHandler that observes changes in the
     * selection of the navigation tree.
     * 
     * @param newSelection
     *            the selection to set
     * 
     * @see #getSelection()
     */
    private void setSelection(Object newSelection) {
        Object oldSelection = getSelection();
        selection = newSelection;
        setSelectionType(newSelection.getClass());
        firePropertyChange(PROPERTYNAME_SELECTION, oldSelection, newSelection);
    }

    /**
     * Sets the selection and reports the previously selected node as changed.
     * <p>
     * 
     * <strong>Note: </strong> This code requires a special order. It assumes
     * that domain changes happen when the selection changes.
     * <p>
     * 
     * TODO: Remove this code and let the nodes listen to domain properties.
     * 
     * @param newSelectedNode
     *            the new node selection
     */
    private void setSelectedNode(NavigationNode newSelectedNode) {
        NavigationNode oldSelectedNode = selectedNode;
        selectedNode = newSelectedNode;
        setSelection(newSelectedNode.getModel());
        ((DefaultTreeModel) getNavigationTreeModel())
                .nodeChanged(oldSelectedNode);
    }

    /**
     * Returns the class of the selected domain object.
     * 
     * @return the class of the selected domain object.
     * 
     * @see #getSelection()
     */
    public Class getSelectionType() {
        return selectionType;
    }

    /**
     * Sets a new selection type. This method is invoked in
     * <code>#setSelection</code> with the class of the new selection.
     * 
     * @param newSelectionType
     *            the selection type to set
     * 
     * @see #getSelectionType()
     */
    private void setSelectionType(Class newSelectionType) {
        Class oldSelectionType = getSelectionType();
        selectionType = newSelectionType;
        if (equals(oldSelectionType, newSelectionType))
            return;

        firePropertyChange(PROPERTYNAME_SELECTION_TYPE, oldSelectionType,
                newSelectionType);
        getHelpModule().updateHelpSet(getSelection());
    }

    // Managing the Navigation Tree Model *************************************

    /**
     * Returns the tree model for the navigation tree.
     * 
     * @return the tree model for the navigation tree.
     */
    public TreeModel getNavigationTreeModel() {
        return navigationTreeModel;
    }

    /**
     * Sets a new tree model for the navigation tree.
     * <p>
     * 
     * TODO: The module shall set the tree selection to the first top-level
     * node, the project description. Currently this is done in a view, see
     * <code>Navigator#updateModel(TreeModel)</code>.
     * 
     * @param newModel
     *            the new tree model to set
     */
    private void setNavigationTreeModel(TreeModel newModel) {
        TreeModel oldModel = getNavigationTreeModel();
        navigationTreeModel = newModel;
        firePropertyChange(PROPERTYNAME_NAVIGATION_TREE_MODEL, oldModel,
                newModel);
    }

    /**
     * Returns the fixed selection model for the navigation tree.
     * 
     * @return the fixed selection model for the navigation tree.
     */
    public TreeSelectionModel getNavigationTreeSelectionModel() {
        return navigationTreeSelectionModel;
    }

    /**
     * Creates and returns a tree model for the given project. Constructs a
     * <code>RootNode</code> with the project and returns a
     * <code>DefaultTreeModel</code> with this root.
     * 
     * @param aProject
     *            the project to create the tree for
     * @return a TreeModel for the given project
     */
    private TreeModel createNavigationTreeModel(Project aProject) {
        return new DefaultTreeModel(new RootNode(aProject));
    }

    // Exposing Submodules ****************************************************

    /**
     * Returns the submodule that provides bound properties for the help tree
     * model, selection, help page and help visibility.
     * 
     * @return the submodule for the dynamic help
     */
    public DynamicHelpModule getHelpModule() {
        return helpModule;
    }

    /**
     * Returns the UI related settings.
     * 
     * @return the UI related settings
     */
    public PresentationSettings getPresentationSettings() {
        return presentationSettings;
    }

    // Storing State ********************************************************

    /**
     * Restores the application state from the user preferences.
     */
    public void restoreState() {
        getPresentationSettings().restoreFrom(Application.getUserPreferences());
    }

    /**
     * Stores the application state to the user preferences.
     */
    public void storeState() {
        Preferences prefs = Application.getUserPreferences();
        getPresentationSettings().storeIn(prefs);
        SetupManager.incrementUsageCounter();
    }

    // Event Handling *********************************************************

    // Listens to changes in the navigation tree node selection
    // and updates the selection.
    private class NavigationTreeSelectionChangeHandler implements
            TreeSelectionListener {

        /**
         * The selected node in the navidation tree has changed. Updates this
         * module's selected domain object and in turn the selection type.
         * <p>
         * 
         * Does nothing if the new tree selection is <code>null</code>.
         * 
         * @param evt
         *            the event that describes the change
         */
        public void valueChanged(TreeSelectionEvent evt) {
            TreePath path = evt.getPath();
            NavigationNode aSelectedNode = (NavigationNode) path
                    .getLastPathComponent();
            if (aSelectedNode != null)
                setSelectedNode(aSelectedNode);
        }

    }

    private class GoalIdentifierChangeHandler implements PropertyChangeListener {

        /*
         * (non-Javadoc)
         * 
         * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
         */
        public void propertyChange(PropertyChangeEvent evt) {
            String propertyName = evt.getPropertyName();
            if (Goal.PROPERTYNAME_IDENTIFIER.equals(propertyName)) {
                System.out.println((String) evt.getOldValue()
                        + (String) evt.getNewValue());
            }

        }

    }

    /**
     *  
     */
    public void removeSelectedItem() {
        if (getSelectionType() == Goal.class) {
            System.out.println("removed goal");
            project.removeGoal((Goal) getSelection());

        }
        if (getSelectionType() == Alternative.class) {
            System.out.println("removed alternative");
            project.removeAlternative((Alternative) getSelection());
        }

    }

}