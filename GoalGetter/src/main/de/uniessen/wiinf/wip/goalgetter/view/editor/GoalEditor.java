/*
 * GoalEditor.java
 * Package: de.uniessen.wiinf.wip.goalgetter.view.editor
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
 * $Id: GoalEditor.java,v 1.6 2004/08/14 11:11:11 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.editor;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.jgoodies.binding.adapter.DocumentAdapter;
import com.jgoodies.binding.beans.BeanAdapter;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.uif.util.ResourceUtils;

import de.uniessen.wiinf.wip.goalgetter.domain.Goal;
import de.uniessen.wiinf.wip.goalgetter.tool.Resources;

/**
 * An implementation of {@link Editor}that displays instances of class
 * {@link Goal}.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.6 $
 *  
 */
public class GoalEditor extends AbstractEditor {

    private JTextArea descriptionArea;

    private JTextComponent nameField;

    private JTextComponent unitField;

    private JTextComponent intensityField;

    /**
     * Constructs a <code>GoalEditor</code>.
     */
    public GoalEditor() {
        super(Resources.GOAL_ICON);
    }

    //  Implementing Abstract Superclass Behavior ****************************

    private Goal getGoal() {
        return (Goal) getModel();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#getTitleSuffix()
     */
    protected String getTitleSuffix() {
        return getGoal().getName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.Editor#getDomainClass()
     */
    public Class getDomainClass() {
        return de.uniessen.wiinf.wip.goalgetter.domain.Goal.class;
    }

    // Component Creation and Configuration **********************************

    /**
     * Creates and intializes the UI components.
     */
    private void initComponents() {
        nameField = new JTextField();
        descriptionArea = new JTextArea();
        descriptionArea.setLineWrap(true);
        //descriptionArea.setFont(getCurrentTheme().getControlTextFont());

        unitField = new JTextField();
        intensityField = new JTextField();

        beanAdapter = new BeanAdapter(getModel(), true);

        nameField.setDocument(new DocumentAdapter(beanAdapter
                .getValueModel(Goal.PROPERTYNAME_NAME)));
        descriptionArea.setDocument(new DocumentAdapter(beanAdapter
                .getValueModel(Goal.PROPERTYNAME_DESCRIPTION)));
        unitField.setDocument(new DocumentAdapter(beanAdapter
                .getValueModel(Goal.PROPERTYNAME_UNIT)));
        intensityField.setDocument(new DocumentAdapter(beanAdapter
                .getValueModel(Goal.PROPERTYNAME_INTENSITY)));

    }

    // Building *************************************************************

    protected void build() {
        initComponents();

        java.awt.Component descriptionPane = new JScrollPane(descriptionArea);

        FormLayout layout = new FormLayout("right:max(40dlu;p), 4dlu, 0:grow");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout,
                ResourceUtils.getBundle(), this);
        builder.setDefaultDialogBorder();
        builder.appendI15dSeparator("goalEditor.goal.text");
        builder.appendI15d("goalEditor.identifier.text", nameField);
        builder.nextLine();
        builder.appendI15d("goalEditor.unit.text", unitField);
        builder.nextLine();
        builder.appendI15d("goalEditor.intensity.text", intensityField);
        builder.nextLine();
        builder.appendI15dSeparator("goalEditor.description.text");
        builder.appendRow(builder.getLineGapSpec());
        builder.appendRow(new RowSpec("fill:50dlu:nogrow"));
        builder.nextLine(2);
        builder.append("", descriptionPane);

    }

}