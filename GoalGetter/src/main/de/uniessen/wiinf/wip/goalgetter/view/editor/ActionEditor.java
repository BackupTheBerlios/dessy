/*
 * ActionEditor.java
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
 * $Id: ActionEditor.java,v 1.4 2004/07/18 21:26:39 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.editor;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.jgoodies.binding.adapter.DocumentAdapter;
import com.jgoodies.binding.beans.BeanAdapter;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.uif.util.ResourceUtils;

import de.uniessen.wiinf.wip.goalgetter.domain.Action;
import de.uniessen.wiinf.wip.goalgetter.tool.Resources;

/**
 * An implementation of {@link Editor}that displays instances of class
 * {@link Action}.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.4 $
 *  
 */
public final class ActionEditor extends AbstractEditor {

    // Instance Creation ****************************************************

    private JTextField identifierField;

    private JTextField resultField;

    private JTextField paymentForActionField;

    private JTextField paymentForTradeoffField;

    private JEditorPane descriptionArea;

    /**
     * Constructs a <code>ActionEditor</code>.
     */
    public ActionEditor() {
        super(Resources.ACTION_ICON);
    }

    // Implementing Abstract Superclass Behavior ****************************

    private Action getAction() {
        return (Action) getModel();
    }

    protected String getTitleSuffix() {
        return getAction().getIdentifier();
    }

    public Class getDomainClass() {
        return Action.class;
    }

    // Component Creation and Configuration **********************************

    /**
     * Creates and intializes the UI components.
     */
    private void initComponents() {
        identifierField = new JTextField();
        descriptionArea = new JEditorPane();
        resultField = new JTextField();
        paymentForActionField = new JTextField();
        paymentForTradeoffField = new JTextField();

        beanAdapter = new BeanAdapter(getModel(), true);

        identifierField.setDocument(new DocumentAdapter(beanAdapter
                .getValueModel(Action.PROPERTYNAME_IDENTIFIER)));
        descriptionArea.setDocument(new DocumentAdapter(beanAdapter
                .getValueModel(Action.PROPERTYNAME_DESCRIPTION)));
        resultField.setDocument(new DocumentAdapter(beanAdapter
                .getValueModel(Action.PROPERTYNAME_RESULT)));
        paymentForActionField.setDocument(new DocumentAdapter(beanAdapter
                .getValueModel(Action.PROPERTYNAME_PAYMENTFORACTION)));
        paymentForTradeoffField.setDocument(new DocumentAdapter(beanAdapter
                .getValueModel(Action.PROPERTYNAME_PAYMENTFORTRADEOFF)));
    }

    // Building *************************************************************

    /**
     * Builds the pane. Columns are specified before components are added to the
     * form, rows are added dynamically using the {@link DefaultFormBuilder}.
     * <p>
     * 
     * The builder combines a step that is done again and again: add a label,
     * proceed to the next data column and add a component.
     */
    public void build() {

        initComponents();

        java.awt.Component descriptionPane = new JScrollPane(descriptionArea);
        FormLayout layout = new FormLayout("right:max(40dlu;p), 4dlu, 0:grow");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout,
                ResourceUtils.getBundle(), this);
        builder.setDefaultDialogBorder();
        //  CellConstraints cc = new CellConstraints();

        builder.appendI15dSeparator("actionEditor.action.text");

        builder.appendI15d("actionEditor.identifier.text", identifierField);
        builder.nextLine();
        builder.appendI15d("actionEditor.result.text", resultField);
        builder.nextLine();
        builder.appendI15d("actionEditor.pfa.text", paymentForActionField);
        builder.nextLine();
        builder.appendI15d("actionEditor.pft.text", paymentForTradeoffField);
        builder.nextLine();
        builder.appendI15dSeparator("actionEditor.description.text");
        builder.appendRow(builder.getLineGapSpec());
        builder.appendRow(new RowSpec("fill:50dlu:nogrow"));
        builder.nextLine(2);
        builder.appendI15d("actionEditor.description.text", descriptionPane);
    }

}