/*
 * HelpView.java
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
 * $Id: HelpView.java,v 1.4 2004/08/07 09:28:03 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view;

import java.awt.Dimension;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import com.jgoodies.uif.action.ActionManager;
import com.jgoodies.uif.builder.ToolBarBuilder;
import com.jgoodies.uif.panel.SimpleInternalFrame;
import com.jgoodies.uif.util.ResourceUtils;
import com.jgoodies.uifextras.util.UIFactory;

import de.uniessen.wiinf.wip.goalgetter.tool.Actions;
import de.uniessen.wiinf.wip.goalgetter.tool.DynamicHelpModule;
import de.uniessen.wiinf.wip.goalgetter.tool.Resources;

/**
 * 
 * Display the contents of the currently selected dynamic help node.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.4 $
 *  
 */
final class HelpView extends SimpleInternalFrame {

    /**
     * Refers to the module that provides a bound property for the help page.
     */
    private final DynamicHelpModule module;

    private JEditorPane editor;

    // Instance Creation ******************************************************

    /**
     * Constructs a <code>HelpView</code> for the given module.
     * 
     * @param helpModule
     *            provides the tree model and tree selection model
     */
    HelpView(DynamicHelpModule helpModule) {
        super(ResourceUtils.getIcon(Resources.HELP_CONTENTS_ICON_ID),
                "Dynamic Help Contents");
        // TODO: I18n
        this.module = helpModule;
        setToolBar(buildToolBar());
        setContent(buildContent());
        setSelected(true);

        module.addPropertyChangeListener(
                DynamicHelpModule.PROPERTYNAME_HELP_PAGE,
                new PageChangeHandler());
    }

    // Building ***************************************************************

    /**
     * Builds and answers the toolbar.
     * 
     * @return built toolbar
     */
    private JToolBar buildToolBar() {
        ToolBarBuilder builder = new ToolBarBuilder("Help Contents");
        builder.add(ActionManager.get(Actions.CLOSE_HELP_VIEW_ID));
        return builder.getToolBar();
    }

    /**
     * Builds and answers the content pane.
     * 
     * @return built content pane
     */
    private JComponent buildContent() {
        editor = new JEditorPane();
        editor.setMargin(new Insets(10, 10, 5, 2));
        editor.setEditable(false);
        editor.setContentType("text/html");
        JScrollPane scrollPane = UIFactory.createStrippedScrollPane(editor);
        scrollPane.setMinimumSize(new Dimension(100, 60));
        scrollPane.setPreferredSize(new Dimension(400, 60));
        return scrollPane;
    }

    // Handling Changes *******************************************************

    /**
     * Sets the page for the specified <code>URL</code>.
     * 
     * @param helpURL
     *            the URL to display in the help window
     */
    private void setPage(URL helpURL) {
        try {
            editor.setPage(helpURL);
        } catch (IOException e) {
            if (helpURL != null)
                Logger.getLogger("HelpView").severe(
                        "Can't show help URL: " + helpURL);
        }
    }

    /**
     * Updates the help page if the module's page has changed.
     * 
     * @author tfranz
     * @author jsprenger
     * 
     * @version $Revision: 1.4 $
     *  
     */
    private class PageChangeHandler implements PropertyChangeListener {

        /**
         * The module's help page has changed. Sets the new page.
         * 
         * @param evt
         *            describes the property change
         */
        public void propertyChange(PropertyChangeEvent evt) {
            setPage((URL) evt.getNewValue());
        }
    }

}