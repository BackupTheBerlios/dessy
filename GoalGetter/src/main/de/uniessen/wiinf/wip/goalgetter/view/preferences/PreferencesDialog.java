/*
 * PreferencesDialog.java
 * Package: de.uniessen.wiinf.wip.goalgetter.view.preferences
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
 * $Id: PreferencesDialog.java,v 1.2 2004/07/18 21:25:28 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.view.preferences;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.jgoodies.uif.AbstractBoundDialog;
import com.jgoodies.uif.application.ResourceIDs;
import com.jgoodies.uif.util.Resizer;
import com.jgoodies.uif.util.ResourceUtils;
import com.jgoodies.uifextras.panels.HeaderPanel;
import com.jgoodies.uifextras.plaf.LookAndFeelPanel;

import de.uniessen.wiinf.wip.goalgetter.tool.PresentationSettings;

/**
 * 
 * Builds the preferences dialog.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.2 $
 *  
 */
public final class PreferencesDialog extends AbstractBoundDialog {

    /**
     * Refers to the settings to be edited.
     */
    private final PresentationSettings settings;

    /**
     * Holds the panel for the look&amp;feel choice and preview.
     */
    private LookAndFeelPanel lafPanel;

    // Instance Creation ******************************************************

    /**
     * Constructs a <code>PreferencesDialog</code>.
     * 
     * @param owner
     *            this dialog's parent frame
     * @param settings
     *            the settings to edit
     */
    public PreferencesDialog(Frame owner, PresentationSettings settings) {
        super(owner);
        this.settings = settings;
    }

    // Building *************************************************************

    /**
     * Builds the UI. In addition to the superclass behavior, we ensure that the
     * selection in the look-and-feel panel is visible.
     */
    protected void build() {
        super.build();
        lafPanel.ensureSelectionsAreVisible();
    }

    /**
     * Builds and returns the preference's header.
     */
    protected JComponent buildHeader() {
        return new HeaderPanel(ResourceUtils.getString("preferencesDialog.preferencesHeader.text"),
                ResourceUtils.getString("preferencesDialog.preferencesDescription.text"),
                ResourceUtils.getIcon(ResourceIDs.PREFERENCES_ICON));
    }

    /**
     * Builds and returns the preference's content pane.
     */
    protected JComponent buildContent() {
        JPanel content = new JPanel(new BorderLayout());
        content.add(buildTabbedPane(), BorderLayout.CENTER);
        content.add(buildButtonBarWithOKCancel(), BorderLayout.SOUTH);
        return content;
    }

    /**
     * Builds and returns the tabbed pane.
     */
    protected JTabbedPane buildTabbedPane() {
        Component generalPanel = new GeneralTabBuilder(settings,
                getTriggerChannel()).build();

        lafPanel = new LookAndFeelPanel(getTriggerChannel());

        JTabbedPane pane = new JTabbedPane();
        pane.addTab(ResourceUtils.getString("preferencesDialog.general.text"), generalPanel);
        pane.addTab(ResourceUtils.getString("preferencesDialog.laf.text"), lafPanel);
        return pane;
    }

    // Misc *****************************************************************

    /**
     * Closes the window.
     */
    protected void doCloseWindow() {
        doCancel();
    }

    /**
     * Unlike the default try to get an aspect ratio of 1:1.
     */
    protected void resizeHook(JComponent component) {
        Resizer.ONE2ONE.resizeDialogContent(component);
    }

}