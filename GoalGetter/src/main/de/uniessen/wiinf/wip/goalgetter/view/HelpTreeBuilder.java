/*
 * HelpTreeBuilder.java
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
 * $Id: HelpTreeBuilder.java,v 1.1 2004/07/03 20:17:08 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.view;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;

import com.jgoodies.uif.component.UIFTree;
import com.jgoodies.uif.util.TreeUtils;

import de.uniessen.wiinf.wip.goalgetter.tool.DynamicHelpModule;
import de.uniessen.wiinf.wip.goalgetter.tool.help.HelpNode;

/**
 * Builds the help navigation panel that displays a tree of help nodes. The help
 * node tree changes with the selection type of the navigation tree.
 * <p>
 * 
 * This panel is embedded in a stripped scrollpane that in turn is contained in
 * a <code>SimpleInternalFrame</code> that in turn is contained in the main
 * page.
 * <p>
 * 
 * Observes the MainModule's selection type to update the displayed HelpSet. The
 * associated HelpSet is requested from the HelpSets.
 * 
 * @author Karsten Lentzsch
 * 
 * @version $Revision: 1.1 $
 *  
 */
final class HelpTreeBuilder {

    /**
     * Refers to the module that holds the tree model and tree selection model.
     */
    private final DynamicHelpModule module;

    /**
     * Refers to the tree that displays the dynamic help nodes.
     * 
     * @see #initComponents()
     */
    private UIFTree tree;

    // Instance Creation ******************************************************

    /**
     * Constructs a <code>HelpNavigator</code> for the given module.
     * 
     * @param helpModule
     *            provides the tree model and tree selection model
     */
    HelpTreeBuilder(DynamicHelpModule helpModule) {
        this.module = helpModule;
        initComponents();

        helpModule.addPropertyChangeListener(
                DynamicHelpModule.PROPERTYNAME_HELP_TREE_MODEL,
                new TreeModelChangeHandler());
    }

    // Component Setup ********************************************************

    /**
     * Builds the <code>ExtTree</code> utilized for this
     * <code>HelpNavigator</code>.
     */
    private void initComponents() {
        tree = new RefreshedTree(module.getHelpTreeModel());
        tree.setSelectionModel(module.getHelpTreeSelectionModel());
        tree.setRootVisible(false);
        tree.setShowsRootHandles(false);
        tree.setCellRenderer(new TreeCellRenderer());
        TreeUtils.expandTopLevel(tree);
    }

    // Building ***************************************************************

    /**
     * Initializes and returns the help navigation tree.
     * 
     * @return the help navigation tree
     */
    JComponent build() {
        initComponents();

        return tree;
    }

    // Event Handling *********************************************************

    /**
     * Sets a new tree model.
     * 
     * @param newModel
     *            the new tree model
     */
    private void updateModel(TreeModel newModel) {
        tree.setModel(newModel);
        TreeUtils.expandTopLevel(tree);
    }

    // Listens to changes in the module's navigation tree model and rebuilds the
    // tree.
    private class TreeModelChangeHandler implements PropertyChangeListener {

        /**
         * The module's help tree model has changed. Update this navigator's
         * tree model and expand the top-level nodes.
         * 
         * @param evt
         *            describes the property change
         */
        public void propertyChange(PropertyChangeEvent evt) {
            TreeModel newModel = (TreeModel) evt.getNewValue();
            updateModel(newModel);
        }
    }

    // Helper Classes *********************************************************

    // A tree cell renderer that uses special HelpNode properties.
    private static class TreeCellRenderer extends DefaultTreeCellRenderer {

        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean sel, boolean expanded, boolean leaf, int row,
                boolean focused) {
            UIFTree extTree = (UIFTree) tree;
            super.getTreeCellRendererComponent(tree, value, selected, expanded,
                    false, row, focused);
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            HelpNode item = (HelpNode) node.getUserObject();
            if (null == item)
                return this;
            this.setForeground(sel ? getTextSelectionColor()
                    : getTextNonSelectionColor());
            selected = sel;
            this.setFont(item.isChapter() ? extTree.getBoldFont() : extTree
                    .getFont());
            this.setIcon(item.getIcon(sel));
            return this;
        }
    }

    // A tree that refreshes the cell renderer when the
    private class RefreshedTree extends UIFTree {

        private RefreshedTree(TreeModel treeModel) {
            super(treeModel);
        }

        /**
         * Updates the UI; updates the selection path and cell renderer.
         */
        public void updateUI() {
            super.updateUI();
            setCellRenderer(new TreeCellRenderer());
        }

    }
}