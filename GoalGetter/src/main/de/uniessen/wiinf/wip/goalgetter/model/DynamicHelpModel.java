/*
 * DynamicHelpModule.java
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
 * $Id: DynamicHelpModel.java,v 1.1 2004/09/25 14:56:57 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.model;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.jgoodies.binding.beans.Model;

import de.uniessen.wiinf.wip.goalgetter.model.help.HelpNode;
import de.uniessen.wiinf.wip.goalgetter.model.help.HelpSets;

/**
 * 
 * Provides bound bean properties for the help tree model, the help page and the
 * help visibility.
 * 
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public final class DynamicHelpModel extends Model {

    // Names of the Bound Bean Properties *************************************

    /**
     * Bound Bean Property <code>PROPERTYNAME_HELP_TREE_MODEL</code>
     */
    public static final String PROPERTYNAME_HELP_TREE_MODEL = "helpTreeModel"; //$NON-NLS-1$

    /**
     * Bound Bean Property <code>PROPERTYNAME_HELP_PAGE</code>
     */
    public static final String PROPERTYNAME_HELP_PAGE = "helpPage"; //$NON-NLS-1$

    /**
     * Bound Bean Property <code>PROPERTYNAME_HELP_VISIBLE</code>
     */
    public static final String PROPERTYNAME_HELP_VISIBLE = "helpVisible"; //$NON-NLS-1$

    /**
     * Bound Bean Property <code>PROPERTYNAME_HELP_NAVIGATOR_VISIBLE</code>
     */
    public static final String PROPERTYNAME_HELP_NAVIGATOR_VISIBLE = "helpNavigatorVisible"; //$NON-NLS-1$

    // Instance Fields ********************************************************

    /**
     * Maps domain classes to help roots.
     */
    private final Map helpRegistry;

    /**
     * Holds the model for the help tree. It changes everytime the selection
     * changes.
     * 
     * @see #getHelpTreeModel()
     * @see #getHelpTreeSelectionModel()
     */
    private TreeModel helpTreeModel;

    /**
     * Refers to the single selection model for the help tree. The selection is
     * reset if the help tree model changes.
     * 
     * @see #getHelpTreeSelectionModel()
     * @see #getHelpTreeModel()
     */
    private final TreeSelectionModel helpTreeSelectionModel;

    /**
     * Holds the URL for the currently selected help page.
     * 
     * @see #getHelpPage()
     */
    private URL helpPage;

    /**
     * Determines whether the dynamic help shall be displayed or hidden.
     * 
     * @see #isHelpVisible()
     * @see #setHelpVisible(boolean)
     */
    private boolean helpVisible;

    /**
     * Determines whether the dynamic help navigator shall be displayed or
     * hidden.
     * 
     * @see #isHelpNavigatorVisible()
     * @see #setHelpNavigatorVisible(boolean)
     */
    private boolean helpNavigatorVisible;

    // Instance Creation ******************************************************

    /**
     * Constructs a <code>DynamicHelpModule</code> with the welcome help set
     * preselected.
     */
    DynamicHelpModel() {
        helpRegistry = new HashMap();

        setHelpSet(HelpSets.WELCOME_HELP_SET);
        helpTreeSelectionModel = new DefaultTreeSelectionModel();
        helpTreeSelectionModel
                .setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        helpTreeSelectionModel
                .addTreeSelectionListener(new HelpTreeSelectionChangeHandler());
        helpNavigatorVisible=true;
    }

    // Managing the Help Tree Model *************************************

    /**
     * Returns the tree model for the help tree.
     * 
     * @return the tree model for the help tree.
     */
    public TreeModel getHelpTreeModel() {
        return helpTreeModel;
    }

    /**
     * Sets a new tree model for the help tree.
     * 
     * @param newModel
     *            the new tree model to set
     */
    private void setHelpTreeModel(TreeModel newModel) {
        TreeModel oldModel = getHelpTreeModel();
        helpTreeModel = newModel;
        firePropertyChange(PROPERTYNAME_HELP_TREE_MODEL, oldModel, newModel);
    }

    private void setHelpSet(TreeNode helpSet) {
        setHelpTreeModel(new DefaultTreeModel(helpSet));
    }

    /**
     * Returns the fixed selection model for the navigation tree.
     * 
     * @return the fixed selection model for the navigation tree.
     */
    public TreeSelectionModel getHelpTreeSelectionModel() {
        return helpTreeSelectionModel;
    }

    // Mapping Domain Classes to Help Sets ************************************

    /**
     * Registers a help tree for a domain class.
     * 
     * @param domainClass
     *            domain class to register help for
     * @param node
     *            node to register help for
     */
    public void registerHelp(Class domainClass, TreeNode node) {
        Object oldValue = helpRegistry.put(domainClass, node);
        if (oldValue != null) {
            Logger logger = Logger
                    .getLogger("de.uniessen.wiinf.wip.goalgetter.tool.DynamicHelpModule"); //$NON-NLS-1$
            logger
                    .warning("Duplicate help registered for class " //$NON-NLS-1$
                            + domainClass);
        }
    }

    /**
     * Returns the <code>HelpNode</code> that has been registered for the
     * given domain object class, <code>null</code> if none.
     * 
     * @param domainClass
     *            the class associated with the editor to lookup
     * @return the editor associated with the given domain class
     */
    private TreeNode lookupHelpSet(Class domainClass) {
        return (TreeNode) helpRegistry.get(domainClass);
    }

    void updateHelpSet(Object selection) {
        TreeNode helpSet = lookupHelpSet(selection.getClass());
        if (helpSet != null) {
            setHelpSet(helpSet);
        }
    }

    // Accessing the Help Page and Help Visibility ****************************

    /**
     * Returns the current help page, a URL. This page will be updated if the
     * selection in the help tree changes.
     * 
     * @return the URL for the currently selected help page.
     */
    public URL getHelpPage() {
        return helpPage;
    }

    /**
     * Sets a URL as new help page. This method is invoked by the
     * HelpTreeSelectionChangeHandler that observes changes in the selection of
     * the help tree.
     * <p>
     * 
     * Sets the help to visible.
     * 
     * @param newHelpPage
     *            the help page to set
     * 
     * @see #getHelpPage()
     */
    private void setHelpPage(URL newHelpPage) {
        URL oldHelpPage = getHelpPage();
        helpPage = newHelpPage;
        if (equals(oldHelpPage, newHelpPage))
            return;

        firePropertyChange(PROPERTYNAME_HELP_PAGE, oldHelpPage, newHelpPage);
        setHelpVisible(true);
    }

    /**
     * Answers whether the dynamic help is visible or not.
     * 
     * @return true if the help viewer is visible, false if hidden.
     */
    public boolean isHelpVisible() {
        return helpVisible;
    }

    /**
     * Answers whether the dynamic help is visible or not.
     * 
     * @return true if the help viewer is visible, false if hidden.
     */
    public boolean isHelpNavigatorVisible() {
        return helpNavigatorVisible;
    }

    /**
     * Sets a new visibility for the help viewer.
     * 
     * @param newVisibility
     *            the help page to set
     * 
     * @see #isHelpVisible()
     */
    public void setHelpVisible(boolean newVisibility) {
        boolean oldVisibility = isHelpVisible();
        helpVisible = newVisibility;
        firePropertyChange(PROPERTYNAME_HELP_VISIBLE, oldVisibility,
                newVisibility);
    }

    /**
     * Sets a new visibility for the help viewer.
     * 
     * @param newVisibility
     *            the help page to set
     * 
     * @see #isHelpNavigatorVisible()
     */
    public void setHelpNavigatorVisible(boolean newVisibility) {        
        boolean oldVisibility = isHelpNavigatorVisible();       
        helpNavigatorVisible = newVisibility;
        firePropertyChange(PROPERTYNAME_HELP_NAVIGATOR_VISIBLE, oldVisibility,
                newVisibility);
    }

    // Event Handling *********************************************************

    // Listens to changes in the help tree node selection
    // and updates the selection.
    private class HelpTreeSelectionChangeHandler implements
            TreeSelectionListener {

        /**
         * The selected node in the help tree has changed. Updates this module's
         * help page.
         * <p>
         * 
         * Does nothing if the new tree selection is <code>null</code>.
         * 
         * @param evt
         *            the event that describes the change
         */
        public void valueChanged(TreeSelectionEvent evt) {
            TreePath path = evt.getPath();
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path
                    .getLastPathComponent();
            HelpNode helpNode = (HelpNode) selectedNode.getUserObject();
            if (null == helpNode || helpNode.isChapter())
                return;

            setHelpPage(helpNode.getURL());
        }

    }

}