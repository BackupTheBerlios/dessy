/*
 * Copyright (c) 2003, 2004 JGoodies Karsten Lentzsch. All Rights Reserved.
 *
 * This software is the proprietary information of JGoodies Karsten Lentzsch.  
 * Use is subject to license terms.
 *
 */

package com.jgoodies.binding.extras;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.Document;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import com.jgoodies.binding.adapter.DocumentAdapter;
import com.jgoodies.binding.adapter.ToggleButtonAdapter;
import com.jgoodies.binding.beans.PropertyConnector;
import com.jgoodies.binding.formatter.EmptyFormatter;
import com.jgoodies.binding.value.BufferedValueModel;
import com.jgoodies.binding.value.StringConverter;
import com.jgoodies.binding.value.Trigger;
import com.jgoodies.binding.value.ValueModel;

/**
 * Consists only of static methods that vend frequently used Swing text
 * components that are bound to an underlying ValueModel.
 * <p>
 * 
 * <strong>Note: </strong> This class is not yet part of the binary Binding
 * library; it comes with the Binding distributions as an extra. <strong>The API
 * is work in progress and may change without notice; this class may even be
 * completely removed from future distributions. </strong> If you want to use
 * this class, you may consider copying it into your codebase.
 * <p>
 * 
 * TODO: Consider moving this class to the UIF in UIF version 1.4.
 * 
 * @author Karsten Lentzsch
 * @version $Revision: 1.1 $
 */
public final class ComponentFactory {

    /**
     * Holds a Trigger that is shared by BufferedValueModels that commit on
     * focus change.
     */
    private static final Trigger FOCUS_LOST_TRIGGER = new Trigger();

    private static final FocusLostHandler FOCUS_LOST_HANLDER = new FocusLostHandler();

    private ComponentFactory() {
        // Suppresses default constructor, ensuring non-instantiability.
    }

    // Factory Methods ********************************************************

    /**
     * Creates and return a check box with the resources' localized text bound
     * to the given model. The model is converted to the required
     * <code>ToggleButton</code> model using a
     * <code>ToggleButtonAdapter</code>.
     * 
     * @param text
     *            the check boxes' text label
     * @param model
     *            the Boolean value model
     * @return a check box with the resources' text bound to the given model
     */
    public static JCheckBox createCheckBox(String text, ValueModel model) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setModel(new ToggleButtonAdapter(model));
        return checkBox;
    }

    /**
     * Creates and returns a <code>JTextField</code> with the content bound to
     * the given ValueModel. The model is updated on every character typed.
     * 
     * @param valueModel
     *            the model that provides the value
     * @return a text field that is bound to the given value model
     * @throws NullPointerException
     *             if the model is <code>null</code>
     */
    public static JTextField createTextField(ValueModel valueModel) {
        JTextField textField = new JTextField();
        textField.setDocument(createBoundDocument(valueModel));
        return textField;
    }

    /**
     * Creates and returns a <code>JLabel</code> that is bound to the given
     * ValueModel.
     * 
     * @param valueModel
     *            the model that provides the value
     * @return a text label that is bound to the given value model
     * @throws NullPointerException
     *             if the model is <code>null</code>
     */
    public static JLabel createLabel(ValueModel valueModel) {
        JLabel label = new JLabel();
        PropertyConnector connector = new PropertyConnector(valueModel,
                "value", label, "text");
        connector.updateProperty2();
        return label;
    }

    /**
     * Creates and returns a <code>JLabel</code> that is bound to the given
     * ValueModel that is wrapped by a <code>StringConverter</code>. The
     * conversion to Strings uses the specified Format.
     * 
     * @param valueModel
     *            the model that provides the value
     * @param format
     *            the format used to create the StringConverter
     * @return a text label that is bound to the given value model
     * @throws NullPointerException
     *             if the model is <code>null</code>
     * 
     * @see StringConverter
     */
    public static JLabel createLabel(ValueModel valueModel, Format format) {
        return createLabel(new StringConverter(valueModel, format));
    }

    /**
     * Creates and returns a <code>JTextArea</code> with the content bound to
     * the given ValueModel. The model is updated on every character typed.
     * 
     * @param valueModel
     *            the model that provides the value
     * @return a text area that is bound to the given value model
     * @throws NullPointerException
     *             if the model is <code>null</code>
     */
    public static JTextArea createTextArea(ValueModel valueModel) {
        JTextArea textArea = new JTextArea();
        textArea.setDocument(createBoundDocument(valueModel));
        return textArea;
    }

    /**
     * Creates and returns a text field with the content bound to the given
     * ValueModel. The model is updated on permanent focus lost.
     * 
     * @param valueModel
     *            the model that provides the value
     * @return a text field that triggers a model update on focus lost
     */
    public static JTextField createFocusBufferedField(ValueModel valueModel) {
        JTextField textField = new JTextField();
        textField.setDocument(createBoundDocument(createFocusBuffered(
                valueModel, textField)));
        return textField;
    }

    /**
     * Creates and returns a formatted text field that binds its value to the
     * given model and converts Strings to values using the given {@link Format}.
     * 
     * @param valueModel
     *            the model that provides the value
     * @param format
     *            the <code>Format</code> used to convert values into a text
     *            representation and vice versa via <code>#format</code> and
     *            <code>#parse</code>
     * @return a formatted text field that is bound to the given value model
     * @throws NullPointerException
     *             if the model is <code>null</code>
     */
    public static JFormattedTextField createFormattedTextField(
            ValueModel valueModel, Format format) {
        JFormattedTextField textField = new JFormattedTextField(format);
        bindFormattedTextField(valueModel, textField);
        return textField;
    }

    /**
     * Creates and returns a formatted text field that binds its value to the
     * given model and converts Strings to values using a MaskFormatter that is
     * based on the given mask.
     * 
     * @param valueModel
     *            the model that provides the value
     * @param mask
     *            the mask pattern used to create an instance of
     *            <code>MaskFormatter</code> that in turn converts values to
     *            Strings and vice versa
     * @return a bound formatted text field using a MaskFormatter
     * @throws NullPointerException
     *             if the model is <code>null</code>
     * @throws IllegalArgumentException
     *             if the mask is invalid
     */
    public static JFormattedTextField createFormattedTextField(
            ValueModel valueModel, String mask) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(mask);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid mask '" + mask + "'.");
        }
        JFormattedTextField textField = new JFormattedTextField(formatter);
        bindFormattedTextField(valueModel, textField);
        return textField;
    }

    /**
     * Creates and returns a formatted text field that is bound to the
     * <code>Date</code> value of the given <code>ValueModel</code>.
     * 
     * @param valueModel
     *            the model that holds the value to be edited
     * @return a formatted text field for Date instances that is bound
     * @throws NullPointerException
     *             if the model is <code>null</code>
     */
    public static JFormattedTextField createDateField(ValueModel valueModel) {
        DateFormat shortFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        JFormattedTextField.AbstractFormatter editFormatter = new EmptyFormatter(
                new DateFormatter(shortFormat));

        JFormattedTextField.AbstractFormatter displayFormatter = new EmptyFormatter(
                new DateFormatter());

        DefaultFormatterFactory formatterFactory = new DefaultFormatterFactory(
                editFormatter, displayFormatter);

        JFormattedTextField textField = new JFormattedTextField(
                formatterFactory);
        bindFormattedTextField(valueModel, textField);
        return textField;
    }

    // Integer Fields *********************************************************

    /**
     * Creates and returns a formatted text field that is bound to the
     * <code>Integer</code> value of the given <code>ValueModel</code>.
     * Empty strings are converted to <code>null</code> and vice versa.
     * <p>
     * 
     * The Format used to convert numbers to strings and vice versa is
     * <code>NumberFormat.getIntegerInstance()</code>.
     * 
     * @param valueModel
     *            the model that holds the value to be edited
     * @return a formatted text field for Integer instances that is bound to the
     *         specified valueModel
     * @throws NullPointerException
     *             if the model is <code>null</code>
     */
    public static JFormattedTextField createIntegerField(ValueModel valueModel) {
        return createIntegerField(valueModel,
                NumberFormat.getIntegerInstance(), null);
    }

    /**
     * Creates and returns a formatted text field that is bound to the
     * <code>Integer</code> value of the given <code>ValueModel</code>.
     * Empty strings are converted to the specified empty number.
     * <p>
     * 
     * The Format used to convert numbers to strings and vice versa is
     * <code>NumberFormat.getIntegerInstance()</code>.
     * 
     * @param valueModel
     *            the model that holds the value to be edited
     * @param emptyNumber
     *            an Integer that represents the empty string
     * @return a formatted text field for Integer instances that is bound to the
     *         specified valueModel
     * @throws NullPointerException
     *             if the model is <code>null</code>
     */
    public static JFormattedTextField createIntegerField(ValueModel valueModel,
            int emptyNumber) {
        return createIntegerField(valueModel,
                NumberFormat.getIntegerInstance(), emptyNumber);
    }

    /**
     * Creates and returns a formatted text field that is bound to the
     * <code>Integer</code> value of the given <code>ValueModel</code>.
     * Empty strings are converted to <code>null</code> and vice versa.
     * 
     * @param valueModel
     *            the model that holds the value to be edited
     * @param numberFormat
     *            used to convert numbers to strings and vice versa
     * @return a formatted text field for Integer instances that is bound to the
     *         specified valueModel
     * @throws NullPointerException
     *             if the model is <code>null</code>
     */
    public static JFormattedTextField createIntegerField(ValueModel valueModel,
            NumberFormat numberFormat) {
        return createIntegerField(valueModel, numberFormat, null);
    }

    /**
     * Creates and returns a formatted text field that is bound to the
     * <code>Integer</code> value of the given <code>ValueModel</code>.
     * Empty strings are converted to the specified empty number.
     * 
     * @param valueModel
     *            the model that holds the value to be edited
     * @param numberFormat
     *            used to convert numbers to strings and vice versa
     * @param emptyNumber
     *            an Integer that represents the empty string
     * @return a formatted text field for Integer instances that is bound to the
     *         specified valueModel
     * @throws NullPointerException
     *             if the model is <code>null</code>
     */
    public static JFormattedTextField createIntegerField(ValueModel valueModel,
            NumberFormat numberFormat, int emptyNumber) {
        return createIntegerField(valueModel, numberFormat, new Integer(
                emptyNumber));
    }

    /**
     * Creates and returns a formatted text field that is bound to the
     * <code>Integer</code> value of the given <code>ValueModel</code>.
     * Empty strings are converted to the specified empty number.
     * 
     * @param valueModel
     *            the model that holds the value to be edited
     * @param numberFormat
     *            used to convert numbers to strings and vice versa
     * @param emptyNumber
     *            an Integer that represents the empty string
     * @return a formatted text field for Integer instances that is bound to the
     *         specified valueModel
     * @throws NullPointerException
     *             if the model is <code>null</code>
     */
    public static JFormattedTextField createIntegerField(ValueModel valueModel,
            NumberFormat numberFormat, Integer emptyNumber) {
        NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
        numberFormatter.setValueClass(Integer.class);

        JFormattedTextField textField = new JFormattedTextField(
                new EmptyFormatter(numberFormatter, emptyNumber));
        bindFormattedTextField(valueModel, textField);
        return textField;
    }

    // Helper Code ************************************************************

    /**
     * Binds the specified <code>JFormattedTextField</code> to the given
     * <code>ValueModel</code>.
     * 
     * @param valueModel
     *            the model that provides the value
     * @param textField
     *            the JFormattedTextField that is to be bound
     * @throws NullPointerException
     *             if the valueModel is <code>null</code>
     */
    private static void bindFormattedTextField(ValueModel valueModel,
            JFormattedTextField textField) {
        if (valueModel == null)
            throw new NullPointerException("The value model must not be null.");

        PropertyConnector connector = new PropertyConnector(valueModel,
                "value", textField, "value");
        connector.updateProperty2();
    }

    /**
     * Creates and returns a {@link Document}that is bound to the given
     * ValueModel and updated on every character typed.
     * 
     * @param valueModel
     *            the model that provides the value
     * @return a Document that is bound to the given value model
     * @throws NullPointerException
     *             if the model is <code>null</code>
     */
    private static Document createBoundDocument(ValueModel valueModel) {
        if (valueModel == null)
            throw new NullPointerException("The model must not be null.");
        return new DocumentAdapter(valueModel);
    }

    /**
     * Creates and returns a {@link ValueModel}that wraps the given ValueModel
     * and delays the commit until the given component looses focus permanently.
     * 
     * @param valueModel
     *            the model that provides the value
     * @param component
     *            the component that looses the focus
     * @return a buffering ValueModel that commits on on focus lost
     * @throws NullPointerException
     *             if the model is <code>null</code>
     */
    private static ValueModel createFocusBuffered(ValueModel valueModel,
            Component component) {
        ValueModel model = new BufferedValueModel(valueModel,
                FOCUS_LOST_TRIGGER);
        component.addFocusListener(FOCUS_LOST_HANLDER);
        return model;
    }

    // Triggers a commit event on focus lost.
    private static class FocusLostHandler extends FocusAdapter {
        /**
         * Triggers a commit event if the focus lost is permanent.
         * 
         * @param evt
         *            the focus lost event
         */
        public void focusLost(FocusEvent evt) {
            if (!evt.isTemporary())
                FOCUS_LOST_TRIGGER.triggerCommit();
        }
    }

}