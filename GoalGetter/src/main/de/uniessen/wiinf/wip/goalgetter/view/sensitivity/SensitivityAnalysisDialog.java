/*
 * SensitivityAnalysisDialog
 * Package: de.uniessen.wiinf.wip.goalgetter.view.sensitivity
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
 * $Id: SensitivityAnalysisDialog.java,v 1.1 2004/08/14 11:11:12 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.view.sensitivity;

import java.awt.Frame;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif.AbstractDialog;
import com.jgoodies.uif.application.ResourceIDs;
import com.jgoodies.uif.util.Resizer;
import com.jgoodies.uif.util.ResourceUtils;
import com.jgoodies.uifextras.panel.HeaderPanel;

/**
 * 
 * Builds the preferences dialog.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public final class SensitivityAnalysisDialog extends AbstractDialog {

    // Instance Creation ******************************************************

    /**
     * Constructs a <code>PreferencesDialog</code>.
     * 
     * @param owner
     *            this dialog's parent frame
     */
    public SensitivityAnalysisDialog(Frame owner) {
        super(owner, "Sensitivitätsanalyse");

    }

    // Building *************************************************************

    /**
     * Builds the UI.
     */
    protected void build() {
        super.build();
    }

    /**
     * Builds and returns the header.
     */
    protected JComponent buildHeader() {
        return new HeaderPanel(
                "Sensitivitätsanalyse",
                "Führen Sie eine Sensitivitätsanalyse durch. Sie werden begeistert sein.",
                ResourceUtils.getIcon(ResourceIDs.PREFERENCES_ICON));
    }

    /**
     * Builds and returns the preference's content pane.
     */
    protected JComponent buildContent() {

        SensitivityAnalysisChart chart = new SensitivityAnalysisChart();

        FormLayout layout = new FormLayout("160dlu:grow");

        JPanel panel = new JPanel();

        DefaultFormBuilder builder = new DefaultFormBuilder(layout,
                ResourceUtils.getBundle(), panel);
        builder.setDefaultDialogBorder();
        builder.append(chart.getChartPanel());
        builder.nextLine();
        builder.appendI15dSeparator("Werte");
        builder.nextLine();
        builder.append(buildEditFields());
        builder.append(buildButtonBarWithClose());
        JPanel p2 = builder.getPanel();
        // p2.setSize(500,400);
        //  p2.setPreferredSize(new Dimension(500,400));
        return p2;

    }

    /**
     * @return
     */
    private JComponent buildEditFields() {
        FormLayout layout = new FormLayout(
                "right:max(40dlu;p), 4dlu, 0:grow, 4dlu,right:max(40dlu;p), 4dlu, 0:grow");

        JPanel panel = new JPanel();

        DefaultFormBuilder builder = new DefaultFormBuilder(layout,
                ResourceUtils.getBundle(), panel);

        builder.appendI15d("Werte", new JTextField(
                "Würstchen für alle, Würstchen für umsonst."));
        builder.appendI15d("Werte", new JTextField(
                "Würstchen für alle, Würstchen für umsonst."));
        builder.nextLine();

        builder.appendI15d("Werte", new JTextField(
                "Würstchen für alle, Würstchen für umsonst."));
        builder.appendI15d("Werte", new JTextField(
                "Würstchen für alle, Würstchen für umsonst."));
        builder.nextLine();

        builder.appendI15d("Werte", new JTextField(
                "Würstchen für alle, Würstchen für umsonst."));
        builder.appendI15d("Werte", new JTextField(
                "Würstchen für alle, Würstchen für umsonst."));
        builder.nextLine();

        return builder.getPanel();

    }

    /**
     * Unlike the default try to get an aspect ratio of 1:1.
     * 
     * @param component
     *            the component to resize
     */
    protected void resizeHook(JComponent component) {
        Resizer.DEFAULT.resizeDialogContent(component);
    }

}