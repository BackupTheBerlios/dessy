/*
 * NavigationPanel.java
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
 * $Id: NavigationPanel.java,v 1.1 2004/08/14 11:11:11 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import com.jgoodies.uif.action.ActionManager;
import com.jgoodies.uif.action.ToggleAction;
import com.jgoodies.uif.builder.ToolBarBuilder;
import com.jgoodies.uif.component.UIFTree;
import com.jgoodies.uif.panel.CardPanel;
import com.jgoodies.uif.panel.SimpleInternalFrame;
import com.jgoodies.uif.util.ResourceUtils;
import com.jgoodies.uif.util.TreeUtils;
import com.jgoodies.uifextras.util.UIFactory;

import de.uniessen.wiinf.wip.goalgetter.tool.Actions;
import de.uniessen.wiinf.wip.goalgetter.tool.MainModule;
import de.uniessen.wiinf.wip.goalgetter.tool.PresentationSettings;
import de.uniessen.wiinf.wip.goalgetter.tool.node.NavigationNode;

/**
 * Builds the navigation panel that is primarily intended to display a tree of
 * <code>NavigationNode</code> instances. Since GoalGetter has no tree data at
 * application startup time, there's an empty white panel that is displayed as
 * long as there's no data. The first time the tree gets a tree model, the empty
 * panel is replaced by the tree. The empty panel and the tree are switched in a
 * CardPanel.
 * <p>
 * 
 * This panel is embedded in a stripped scrollpane that in turn is contained in
 * a <code>SimpleInternalFrame</code> that in turn is contained in the main
 * page.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public class NavigationPanel extends SimpleInternalFrame {

    private static final long serialVersionUID = 1L;

    private static final String NO_TREE_CARD = "NoTree"; //$NON-NLS-1$

    private static final String TREE_CARD = "Tree"; //$NON-NLS-1$

    /**
     * Refers to the module that holds the tree model and tree selection model.
     */
    private final MainModule module;

    /**
     * Refers to the tree that displays the navigation nodes.
     * 
     * @see #initComponents()
     */
    private UIFTree tree;

    /**
     * Holds two cards: a stub that shows "no project" and a second card for the
     * scrollable navigation tree.
     */
    private CardPanel cardPanel;

    /**
     * Constructs an <code>NavigationPanel</code>.
     * 
     * @param mainModule
     *            provides the tree model and tree selection model
     */
    NavigationPanel(MainModule mainModule) {
        super("Navigator");
        this.module = mainModule;
        initComponents();
        setToolBar(buildToolBar());
        setContent(UIFactory.createStrippedScrollPane(buildContent()));
        setSelected(true);

        //   updateCard();
        registerListeners();
    }

    /**
     * Builds and answers the navigator toolbar.
     * 
     * @return navigator toolbar
     */
    private JToolBar buildToolBar() {
        ToolBarBuilder builder = new ToolBarBuilder("Navigator");
        builder.addToggle((ToggleAction) ActionManager
                .get(Actions.NAVIGATOR_ACTIONS_BY_GOAL_ID));
        builder.addToggle((ToggleAction) ActionManager
                .get(Actions.NAVIGATOR_ACTIONS_BY_ALTERNATIVE_ID));
        return builder.getToolBar();
    }

    // Component Setup ********************************************************

    /**
     * Creates, binds and configures the navigation tree used to display the
     * <code>NavigationNodes</code>. Requests the tree model and the tree
     * selection model from the MainModule.
     */
    private void initComponents() {
        tree = new RefreshedTree(module.getNavigationTreeModel());
        tree.setSelectionModel(module.getNavigationTreeSelectionModel());
        tree.putClientProperty("JTree.lineStyle", "None"); //$NON-NLS-1$ //$NON-NLS-2$
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        tree.setCellRenderer(new TreeCellRenderer());
    }

    // Building ***************************************************************

    /**
     * Builds and returns a CardPanel with two cards: one for the navigation
     * tree, another for the empty card.
     * 
     * @return the card panel
     */
    JComponent buildContent() {
        cardPanel = new CardPanel();
        cardPanel.add(buildNoTreeCard(), NO_TREE_CARD);
        cardPanel.add(tree, TREE_CARD);
        return cardPanel;
    }

    /**
     * Builds and returns a stub panel that is used as long as no
     * <code>Project</code> has been set.
     * 
     * @return stub panel
     */
    private JComponent buildNoTreeCard() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(ResourceUtils
                .getString("navigationPanelBuilder.noProjectLoaded.text")), //$NON-NLS-1$
                BorderLayout.NORTH);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setPreferredSize(new Dimension(150, 200));
        panel.setBackground(Color.white);
        return panel;
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
        // Expand top-level nodes
        TreeUtils.expandTopLevel(tree);
        // Preselect the first node, here: project data
        // tree.setSelectionRow(0);
    }

    /**
     * Updates the expansion state of the selected node; honors the auto expand
     * and collapse settings.
     */
    private void updateExpansionState() {
        TreePath pathToRoot = tree.getSelectionPath();
        if (pathToRoot == null)
            return;

        boolean expanded = tree.isExpanded(pathToRoot);

        if (presentationSettings().getCollapseDeselectedPaths())
            TreeUtils.collapseAllSiblings(tree, pathToRoot);

        if (expanded || presentationSettings().getExpandSelectedPaths())
            tree.expandPath(pathToRoot);

        tree.scrollPathToVisible(pathToRoot);
    }

    // Listens to changes in the module's navigation tree model and rebuilds the
    // tree.
    private class TreeModelChangeHandler implements PropertyChangeListener {

        /**
         * The module's navigation tree model has changed. Update this
         * navigator's tree model switch to the tree card and expand the
         * top-level nodes.
         * 
         * @param evt
         *            describes the property change
         */
        public void propertyChange(PropertyChangeEvent evt) {
            TreeModel newModel = (TreeModel) evt.getNewValue();
            updateModel(newModel);
            cardPanel.showCard(TREE_CARD);
        }
    }

    // Updates the expansion state if the tree selection has changed.
    private class SelectionChangeHandler implements PropertyChangeListener {

        /**
         * The module's selection has changed. Updates the expansion state.
         * 
         * @param evt
         *            describes the property change
         */
        public void propertyChange(PropertyChangeEvent evt) {
            updateExpansionState();
        }
    }

    // Updates the expansion state if tree related UI settings have changed.
    private class PresentationSettingsChangeHandler implements
            PropertyChangeListener {

        /**
         * The presentation settings have changed. If the event is about the
         * expansion state, we update the tree's expansion state.
         * 
         * @param evt
         *            describes the property change
         */
        public void propertyChange(PropertyChangeEvent evt) {
            if (PresentationSettings.PROPERTYNAME_EXPAND_SELECTED_PATHS
                    .equals(evt.getPropertyName())) {
                updateExpansionState();
            } else if (PresentationSettings.PROPERTYNAME_COLLAPSE_DESELECTED_PATHS
                    .equals(evt.getPropertyName())) {
                updateExpansionState();
            }
            if (PresentationSettings.PROPERTYNAME_ACTION_PRESENTATION_MODE
                    .equals(evt.getPropertyName())) {
                // dirty workaround for refreshing the tree
                //TODO remove through better method (find out how)
                module.setProject(module.getProject());
            }
        }

    }

    // Helper Classes *********************************************************

    // Renders icons and labels for the NavigationNodes.
    private static class TreeCellRenderer extends DefaultTreeCellRenderer {

        private static final long serialVersionUID = 1L;

        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean sel, boolean expanded, boolean leaf, int row,
                boolean focus) {
            super.getTreeCellRendererComponent(tree, value, selected, expanded,
                    leaf, row, focus);
            NavigationNode node = (NavigationNode) value;
            this.setForeground(sel ? getTextSelectionColor()
                    : getTextNonSelectionColor());
            selected = sel;
            this.setIcon(node.getIcon(sel));
            this.setText(node.getName());
            return this;
        }
    }

    // A tree that refreshes the cell renderer when the
    private class RefreshedTree extends UIFTree {

        private static final long serialVersionUID = 1L;

        private RefreshedTree(TreeModel treeModel) {
            super(treeModel);
        }

        /**
         * Updates the UI; updates the selection path and cell renderer.
         */
        public void updateUI() {
            super.updateUI();
            setSelectionPath(getSelectionPath());
            setCellRenderer(new TreeCellRenderer());

        }

    }

    private void registerListeners() {
        module.addPropertyChangeListener(
                MainModule.PROPERTYNAME_NAVIGATION_TREE_MODEL,
                new TreeModelChangeHandler());
        module.addPropertyChangeListener(MainModule.PROPERTYNAME_SELECTION,
                new SelectionChangeHandler());
        presentationSettings().addPropertyChangeListener(
                new PresentationSettingsChangeHandler());
    }

    // Accessing Collaborators **********************************************

    private PresentationSettings presentationSettings() {
        return module.getPresentationSettings();
    }

}