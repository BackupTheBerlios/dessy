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
 * $Id: MainPageBuilder.java,v 1.11 2004/09/25 14:56:57 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.prefs.Preferences;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.jgoodies.looks.LookUtils;
import com.jgoodies.uif.application.Application;
import com.jgoodies.uif.panel.SimpleInternalFrame;
import com.jgoodies.uif.util.ComponentTreeUtils;
import com.jgoodies.uifextras.util.UIFactory;

import de.uniessen.wiinf.wip.goalgetter.model.DynamicHelpModel;
import de.uniessen.wiinf.wip.goalgetter.model.MainModel;
import de.uniessen.wiinf.wip.goalgetter.view.editor.ActionByAlternativeEditor;
import de.uniessen.wiinf.wip.goalgetter.view.editor.AlternativeContainerEditor;
import de.uniessen.wiinf.wip.goalgetter.view.editor.AlternativeEditor;
import de.uniessen.wiinf.wip.goalgetter.view.editor.DescriptionEditor;
import de.uniessen.wiinf.wip.goalgetter.view.editor.EditorPanel;
import de.uniessen.wiinf.wip.goalgetter.view.editor.GoalContainerEditor;
import de.uniessen.wiinf.wip.goalgetter.view.editor.GoalEditor;
import de.uniessen.wiinf.wip.goalgetter.view.editor.ResultsPanel;
import de.uniessen.wiinf.wip.goalgetter.view.editor.WelcomePanel;

/**
 * Builds the main page of the GoalGetter application: the tool bar, navigator,
 * help navigation, all editors, the dynamic help viewer, and the status bar.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.11 $
 *  
 */

public final class MainPageBuilder {

    private static final Dimension PREFERRED_SIZE = LookUtils.IS_LOW_RESOLUTION ? new Dimension(
            620, 510)
            : new Dimension(760, 570);

    private static final String COPYRIGHT_TEXT = Application.getDescription()
            .getCopyrightAndVendor();

    // Preferences Key
    private static final String MAIN_DIVIDER_LOCATION_KEY = "mainDividerLocation";//$NON-NLS-1$

    private static final String VIEWER_HELP_DIVIDER_LOCATION_KEY = "viewerHelpDividerLocation";//$NON-NLS-1$

    /**
     * Refers to the module that provides all high-level models. Used to build
     * this frame's main page.
     */
    private final MainModel mainModel;

    private JPanel mainPage;

    private SimpleInternalFrame navigator;

    private SimpleInternalFrame helpNavigator;

    private EditorPanel editorPanel;

    private HelpView helpView;

    private JSplitPane mainSplitPane;

    private JSplitPane editorHelpSplitPane;

    private JSplitPane navigatorHelpSplitPane;

    private JLabel statusField;

    private ResultsPanel resultsPanel;

    // Instance Creation ******************************************************

    /**
     * Constructs a MainPageBuilder for the given main module.
     * 
     * @param mainModel
     *            provides high-level models
     */
    MainPageBuilder(MainModel mainModel) {
        this.mainModel = mainModel;

    }

    //  Initialization
    // *************************************************************

    /**
     * Creates, binds and configures the subpanels and components.
     */
    private void initComponents() {
        navigator = new NavigationPanel(mainModel);
        navigator.setSelected(true);
        navigator.setMinimumSize(new Dimension(100, 100));
        navigator.setPreferredSize(new Dimension(160, 200));

        helpNavigator = new HelpTreePanel(mainModel.getHelpModule());
        helpNavigator.setSelected(true);
        helpNavigator.setMinimumSize(new Dimension(100, 100));
        helpNavigator.setPreferredSize(new Dimension(100, 100));

        editorPanel = new EditorPanel(mainModel);
        editorPanel.setMinimumSize(new Dimension(200, 100));
        editorPanel.setPreferredSize(new Dimension(400, 200));

        helpView = new HelpView(mainModel.getHelpModule());
        helpView.setMinimumSize(new Dimension(200, 50));
        helpView.setPreferredSize(new Dimension(300, 100));

        statusField = UIFactory.createPlainLabel(COPYRIGHT_TEXT);
    }

    private void initEventHandling() {
        mainModel.getHelpModule().addPropertyChangeListener(
                DynamicHelpModel.PROPERTYNAME_HELP_VISIBLE,
                new HelpVisibilityChangeHandler());

        mainModel.getHelpModule().addPropertyChangeListener(
                DynamicHelpModel.PROPERTYNAME_HELP_NAVIGATOR_VISIBLE,
                new HelpNavigatorVisibilityChangeHandler());

        mainModel.addPropertyChangeListener(
                MainModel.PROPERTYNAME_RESULTSPANEL_VISIBLE,
                new ResultPanelShowChangeHandler());
    }

    //  Building *************************************************************

    /**
     * Builds this panel with the horizontal <code>JSplitPane</code> in the
     * center and a status bar in the south.
     * 
     * @return main Page
     */
    JComponent build() {
        initComponents();
        initEventHandling();

        mainPage = new RefreshedPanel();
        mainPage.setLayout(new BorderLayout());
        mainPage.add(new MainToolBarBuilder().build(), BorderLayout.NORTH);
        mainPage.add(buildMainSplitPane(), BorderLayout.CENTER);
        mainPage.add(buildStatusBar(), BorderLayout.SOUTH);
        mainPage.setPreferredSize(PREFERRED_SIZE);

        setHelpVisible(false);
        setHelpNavigatorVisible(true);
        return mainPage;
    }

    /**
     * Builds and answers the main <code>JSplitPane</code> that contains the
     * navigation elements on the left, and the view panels on the right.
     * 
     * @return main split pane
     */
    private JComponent buildMainSplitPane() {
        mainSplitPane = UIFactory.createStrippedSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, buildNavigatorHelpPanel(),
                buildEditorHelpPanel(), 0.25);
        mainSplitPane.setBorder(BorderFactory.createEmptyBorder(6, 4, 0, 4));
        return mainSplitPane;
    }

    /**
     * Builds the <code>Navigator</code>, the <code>HelpNavigator</code>
     * and answers them wrapped by a stripped <code>JSplitPane</code>.
     * 
     * @return navigator help panel
     */
    private JComponent buildNavigatorHelpPanel() {
        navigatorHelpSplitPane = UIFactory.createStrippedSplitPane(
                JSplitPane.VERTICAL_SPLIT, navigator, helpNavigator, 0.667);
        return navigatorHelpSplitPane;
    }

    /**
     * Builds and answers the <code>EditorPanel</code>.
     * 
     * @return editor panel
     */
    private EditorPanel buildEditorPanel() {
        WelcomePanel welcomePanel = new WelcomePanel();

        editorPanel.addEditor(welcomePanel);
        editorPanel.addEditor(new DescriptionEditor());
        editorPanel.addEditor(new GoalEditor());
        editorPanel.addEditor(new AlternativeEditor());
        editorPanel.addEditor(new ActionByAlternativeEditor());
        editorPanel.addEditor(new GoalContainerEditor());
        editorPanel.addEditor(new AlternativeContainerEditor());
        //     editorPanel.addEditor(new ActionContainerEditor());
        resultsPanel = new ResultsPanel();

        editorPanel.addEditor(resultsPanel);
        editorPanel.setActiveEditor(welcomePanel);
        return editorPanel;
    }

    private void showResultPanel() {
        // editorPanel.getActiveEditor().deactivate();
        resultsPanel.setModel(mainModel.getProject());
        resultsPanel.activate();
        editorPanel.setActiveEditor(resultsPanel);

    }

    /**
     * Builds the <code>EditorPanel</code>, the <code>HelpView</code> and
     * answers them wrapped by a stripped <code>JSplitPane</code>.
     * 
     * @return editor help panel
     */
    private JComponent buildEditorHelpPanel() {
        editorHelpSplitPane = UIFactory.createStrippedSplitPane(
                JSplitPane.VERTICAL_SPLIT, buildEditorPanel(), helpView, 0.667);
        return editorHelpSplitPane;
    }

    /**
     * Builds and answers the status bar.
     * 
     * @return status bar
     */
    private JPanel buildStatusBar() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(statusField, BorderLayout.WEST);
        statusField.setBorder(BorderFactory.createEmptyBorder(2, 6, 2, 3));
        return panel;
    }

    // Storing and Restoring State ******************************************

    /**
     * Restores the frame's state from the user preferences.
     * 
     * @param userPrefs
     *            the preferences instance to restore the state from
     */
    public void restoreFrom(Preferences userPrefs) {
        int mainDividerLocation = userPrefs.getInt(MAIN_DIVIDER_LOCATION_KEY,
                -1);
        int verticalDividerLocation = userPrefs.getInt(
                VIEWER_HELP_DIVIDER_LOCATION_KEY, -1);

        if ((mainDividerLocation > 100)
                && (mainDividerLocation < mainPage.getWidth() - 50)) {
            mainSplitPane.setDividerLocation(mainDividerLocation);
        }
        if ((verticalDividerLocation > 100)
                && (verticalDividerLocation < mainPage.getHeight() - 50)) {
            editorHelpSplitPane.setDividerLocation(verticalDividerLocation);
        }
    }

    /**
     * Stores the frame's state in the user preferences.
     * 
     * @param userPrefs
     *            the preferences instance to store the state into
     */
    public void storeIn(Preferences userPrefs) {
        int mainDividerLocation = mainSplitPane.getDividerLocation();
        int verticalDividerLocation = editorHelpSplitPane.getDividerLocation();
        userPrefs.putInt(MAIN_DIVIDER_LOCATION_KEY, mainDividerLocation);
        userPrefs.putInt(VIEWER_HELP_DIVIDER_LOCATION_KEY,
                verticalDividerLocation);
    }

    // Updating the Help View Visibility **************************************

    /**
     * Answers if the dynamic help contents panel is visible.
     * 
     * @return <code>true</code> if help is visible, <code>false</code> if
     *         help is not visible
     */
    private boolean isHelpVisible() {
        return editorHelpSplitPane.getTopComponent() != null;
    }

    /**
     * Sets the visiblity of the dynamic help contents panel.
     * 
     * @param b
     *            set to <code>true</code> if help should be set to visible,
     *            <code>false</code> for hiding the help
     */
    private void setHelpVisible(boolean b) {
        if (isHelpVisible() == b) {
            return;
        }
        //help viewer
        if (b) {
            mainSplitPane.setRightComponent(editorHelpSplitPane);
            editorHelpSplitPane.setTopComponent(editorPanel);
        } else {
            editorHelpSplitPane.setTopComponent(null);
            mainSplitPane.setRightComponent(editorPanel);
        }

    }

    private void setHelpNavigatorVisible(boolean b) {
        if (isHelpNavigatorVisible() == b) {
            return;
        }
        //help viewer
        if (b) {
            mainSplitPane.setLeftComponent(navigatorHelpSplitPane);
            navigatorHelpSplitPane.setTopComponent(navigator);
            helpNavigator.updateUI();
        } else {
            setHelpVisible(false);
            navigatorHelpSplitPane.setTopComponent(null);
            mainSplitPane.setLeftComponent(navigator);
        }

    }

    private boolean isHelpNavigatorVisible() {
        return navigatorHelpSplitPane.getTopComponent() != null;
    }

    /**
     * Listens to changes in the module's navigation tree model and rebuilds the
     * tree.
     * 
     * @author tfranz
     * @author jsprenger
     * 
     * @version $Revision: 1.11 $
     *  
     */
    private class HelpVisibilityChangeHandler implements PropertyChangeListener {

        /**
         * The module's help visibility has changed. Show or hide the help
         * viewer.
         * 
         * @param evt
         *            describes the property change
         */
        public void propertyChange(PropertyChangeEvent evt) {
            boolean visible = ((Boolean) evt.getNewValue()).booleanValue();
            setHelpVisible(visible);
        }
    }

    /**
     * Listens to changes in the module's navigation tree model and rebuilds the
     * tree.
     * 
     * @author tfranz
     * @author jsprenger
     * 
     * @version $Revision: 1.11 $
     *  
     */
    private class HelpNavigatorVisibilityChangeHandler implements
            PropertyChangeListener {

        /**
         * The module's help visibility has changed. Show or hide the help
         * viewer.
         * 
         * @param evt
         *            describes the property change
         */
        public void propertyChange(PropertyChangeEvent evt) {
            boolean visible = ((Boolean) evt.getNewValue()).booleanValue();
            setHelpNavigatorVisible(visible);
        }
    }

    private class ResultPanelShowChangeHandler implements
            PropertyChangeListener {

        /**
         * The module's help visibility has changed. Show or hide the help
         * viewer.
         * 
         * @param evt
         *            describes the property change
         */
        public void propertyChange(PropertyChangeEvent evt) {
            showResultPanel();
        }
    }

    // Helper Class ***********************************************************

    private class RefreshedPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        /**
         * In case some panels are invisible, we explicitly update their UIs.
         */
        public void updateUI() {
            super.updateUI();
            if (getComponentCount() == 0)
                return;
            //            if (editorHelpSplitPane == null || isHelpVisible())
            //                return;
            ComponentTreeUtils.updateComponentTreeUI(editorHelpSplitPane);
            // if (navigatorHelpSplitPane == null || isHelpNavigatorVisible())
            ComponentTreeUtils.updateComponentTreeUI(navigatorHelpSplitPane);

        }

    }

    /**
     * @return Returns the resultsPanel.
     */
    public ResultsPanel getResultsPanel() {
        resultsPanel.setModel(mainModel.getProject());
        return resultsPanel;
    }
}