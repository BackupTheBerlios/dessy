/*
 * GeneralTabBuilder.java
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
 * $Id: GeneralTabBuilder.java,v 1.5 2004/08/07 09:28:04 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.view.preferences;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JRadioButton;

import com.jgoodies.binding.adapter.ToggleButtonAdapter;
import com.jgoodies.binding.beans.PropertyAdapter;
import com.jgoodies.binding.value.BufferedValueModel;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif.util.ResourceUtils;
import com.jgoodies.uifextras.convenience.TipOfTheDayDialog;

import de.uniessen.wiinf.wip.goalgetter.tool.PresentationSettings;

/**
 * Builds the <em>General</em> tab in the preferences dialog.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.5 $
 *  
 */
public final class GeneralTabBuilder {

    private JCheckBox showTipsBox;

    private JCheckBox autoExpandBox;

    private JCheckBox autoCollapseBox;
    
    private JRadioButton actionsByGoal;
    
    private JRadioButton actionsByAlternative;

    // Instance Creation ****************************************************

    /**
     * Constructs the <i>General </i> panel for the preferences dialog.
     * 
     * @param settings
     *            the presentation related settings
     * @param triggerChannel
     *            triggers a commit when apply is pressed
     */
    public GeneralTabBuilder(PresentationSettings settings,
            ValueModel triggerChannel) {
        initComponents(settings, triggerChannel);
    }

    // Component Creation and Initialization ********************************

    /**
     * Creates and configures the UI components. Creates three check boxes that
     * are bound to properties of the <code>TipOfTheDayDialog</code> and the
     * given <code>PresentationSettings</code>. All models are buffered and
     * change the underlying model properties only if the
     * <code>triggerChannel</code> indicates that values shall be commited.
     * <p>
     * 
     * The adapted bean properties will not be observed; therefore we don't need
     * to invoke <code>PropertyAdapter.release</code>
     * 
     * @param settings
     *            holds the properties for the tree manipulation
     * @param triggerChannel
     *            indicates a commit or flush of the buffered values
     * @see PropertyAdapter
     * @see BufferedValueModel
     * @see ToggleButtonAdapter
     */
    private void initComponents(PresentationSettings settings,
            ValueModel triggerChannel) {
        showTipsBox = new JCheckBox(ResourceUtils
                .getString("generalTabBuilder.showtipofday.text"));
        showTipsBox.setModel(new ToggleButtonAdapter(new BufferedValueModel(
                TipOfTheDayDialog.showingTipsModel(), triggerChannel)));

        autoExpandBox = new JCheckBox(ResourceUtils
                .getString("generalTabBuilder.autoexpand.text"));
        autoExpandBox
                .setModel(new ToggleButtonAdapter(
                        new BufferedValueModel(
                                new PropertyAdapter(
                                        settings,
                                        PresentationSettings.PROPERTYNAME_EXPAND_SELECTED_PATHS),
                                triggerChannel)));

        autoCollapseBox = new JCheckBox(ResourceUtils
                .getString("generalTabBuilder.autocollapse.text"));
        autoCollapseBox
                .setModel(new ToggleButtonAdapter(
                        new BufferedValueModel(
                                new PropertyAdapter(
                                        settings,
                                        PresentationSettings.PROPERTYNAME_COLLAPSE_DESELECTED_PATHS),
                                triggerChannel)));
    }

    // Building *************************************************************

    /**
     * Builds and returns the general panel using the previously created
     * components. Uses a FormLayout to lay out the panel, and a PanelBuilder to
     * add the UI components.
     * 
     * @return the general tab
     */
    JComponent build() {
        FormLayout layout = new FormLayout("7dlu, left:pref, 0:grow",//$NON-NLS-1$
                "pref, 2dlu, pref, 14dlu, " + "pref, 2dlu, pref, 2dlu, pref");//$NON-NLS-1$ //$NON-NLS-2$

        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();

        builder
                .addSeparator(ResourceUtils
                        .getString("generalTabBuilder.onStartup.text"), cc.xyw(
                        1, 1, 3));
        builder.add(showTipsBox, cc.xy(2, 3));

        builder.addSeparator(ResourceUtils
                .getString("generalTabBuilder.navigationTree.text"), cc.xyw(1,
                5, 3));
        builder.add(autoExpandBox, cc.xy(2, 7));
        builder.add(autoCollapseBox, cc.xy(2, 9));

        return builder.getPanel();
    }

}