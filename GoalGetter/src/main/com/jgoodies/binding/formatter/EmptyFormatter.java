/*
 * Copyright (c) 2003, 2004 JGoodies Karsten Lentzsch. All Rights Reserved.
 *
 * This software is the proprietary information of JGoodies Karsten Lentzsch.
 * Use is subject to license terms.
 *
 */

package com.jgoodies.binding.formatter;

import java.text.ParseException;

import javax.swing.JFormattedTextField;

/**
 * Wraps a given <code>JFormattedTextField.AbstractFormatter</code> and adds
 * behavior to convert to/from the empty string. Therefore it holds an
 * <em>empty value</em> that is the counterpart of the empty string. Method
 * <code>#valueToString</code> converts the empty value to the empty string.
 * And <code>#stringToValue</code> converts blank strings to the empty value.
 * In all other cases the conversion is delegated to the underlying Formatter.
 * <p>
 * 
 * Often the empty value is <code>null</code>. For example you can wrap a
 * <code>DateFormatter</code> to map the empty string to <code>null</code>
 * and vice versa. As an alternative you can map the empty string to Today; but
 * likely you won't map Today to the empty string. Another example is the
 * mapping of the <code>-1</code> to an empty string using a wrapped
 * <code>NumberFormatter</code>.
 * 
 * <strong>Examples: </strong>
 * 
 * <pre>
 * 
 *  new EmptyFormatter(new NumberFormatter());
 *  new EmptyFormatter(new NumberFormatter(), -1);
 *  new EmptyFormatter(new NumberFormatter(new IntegerFormat(3)), -1);
 *  
 *  new EmptyFormatter(new DateFormatter());
 *  new EmptyFormatter(new DateFormatter(
 *          DateFormat.getDateInstance(DateFormat.SHORT));
 *  
 * </pre>
 * 
 * @author Karsten Lentzsch
 * @version $Revision: 1.1 $
 * 
 * @see javax.swing.text.DateFormatter
 * @see javax.swing.text.NumberFormatter
 * @see java.text.Format
 */
public final class EmptyFormatter extends JFormattedTextField.AbstractFormatter {

    /**
     * Refers to the wrapped Formatter that is used to forward
     * <code>#stringToValue</code> and <code>#valueToString</code>.
     */
    private final JFormattedTextField.AbstractFormatter formatter;

    /**
     * Holds the object that is converted to an empty string and that is the
     * result of converting blank strings to a value.
     * 
     * @see #stringToValue(String)
     * @see #valueToString(Object)
     */
    private final Object emptyValue;

    // Instance Creation ****************************************************

    /**
     * Constructs a <code>EmptyFormatter</code> that wraps the given formatter
     * to convert <code>null</code> to the empty string and vice versa.
     * 
     * @param formatter
     *            the formatter that handles the standard cases
     */
    public EmptyFormatter(JFormattedTextField.AbstractFormatter formatter) {
        this(formatter, null);
    }

    /**
     * Constructs an <code>EmptyFormatter</code> that wraps the given format
     * to convert the given <code>emptyValue</code> to the empty string and
     * vice versa.
     * 
     * @param formatter
     *            the format that handles non- <code>null</code> values
     * @param emptyValue
     *            the representation of the empty string
     */
    public EmptyFormatter(JFormattedTextField.AbstractFormatter formatter,
            Object emptyValue) {
        this.formatter = formatter;
        this.emptyValue = emptyValue;
    }

    // Implementing Abstract Behavior ****************************************

    /**
     * Returns the <code>Object</code> representation of the
     * <code>String</code> <code>text</code>.
     * <p>
     * 
     * Unlike the underlying formatter, this class converts blank strings to the
     * empty value.
     * 
     * @param text
     *            <code>String</code> to convert
     * @return <code>Object</code> representation of text
     * @throws ParseException
     *             if there is an error in the conversion
     */
    public Object stringToValue(String text) throws ParseException {
        return isBlank(text) ? emptyValue : formatter.stringToValue(text);
    }

    /**
     * Returns a String representation of the Object <code>value</code>. This
     * invokes <code>format</code> on the current <code>Format</code>.
     * <p>
     * 
     * Unlike the underlying formatter, this class converts the empty value to
     * the empty string.
     * 
     * @param value
     *            the value to convert
     * @return a String representation of value
     * @throws ParseException
     *             if there is an error in the conversion
     */
    public String valueToString(Object value) throws ParseException {
        return equals(value, emptyValue) ? "" : formatter.valueToString(value);
    }

    // Overriding Superclass Behavior *****************************************

    /**
     * Installs the <code>AbstractFormatter</code> onto a particular
     * <code>JFormattedTextField</code>. Forwards the installation to the
     * underlying formatter.
     * 
     * @param ftf
     *            JFormattedTextField to format for, may be null indicating
     *            uninstall from current JFormattedTextField.
     * 
     * @see javax.swing.JFormattedTextField.AbstractFormatter#install(JFormattedTextField)
     */
    public void install(JFormattedTextField ftf) {
        formatter.install(ftf);
        if (ftf != null) {
            try {
                ftf.setText(valueToString(ftf.getValue()));
                setEditValid(true);
            } catch (ParseException pe) {
                ftf.setText("");
                setEditValid(false);
            }
        }
    }

    /**
     * Uninstalls any state the <code>AbstractFormatter</code> may have
     * installed on the <code>JFormattedTextField</code>. This resets the
     * <code>DocumentFilter</code>,<code>NavigationFilter</code> and
     * additional <code>Action</code> s installed on the
     * <code>JFormattedTextField</code>.
     * 
     * @see javax.swing.JFormattedTextField.AbstractFormatter#uninstall()
     */
    public void uninstall() {
        formatter.uninstall();
    }

    // Helper Code ************************************************************

    /**
     * Checks and answers if the two objects are both <code>null</code> or
     * equal.
     * 
     * @param o1
     *            the first object to compare
     * @param o2
     *            the second object to compare
     * @return boolean true iff both objects are <code>null</code> or equal
     */
    private boolean equals(Object o1, Object o2) {
        return (o1 != null && o2 != null && o1.equals(o2))
                || (o1 == null && o2 == null);
    }

    /**
     * Checks and answers if the given string is whitespace, empty (
     * <code>""</code>) or <code>null</code>.
     * 
     * <pre>
     * 
     *  isBlank(null)     = true
     *  isBlank(&quot;&quot;)       = true
     *  isBlank(&quot; &quot;)      = true
     *  isBlank(&quot;Hi &quot;)    = false
     *  
     * </pre>
     * 
     * @param str
     *            the string to check, may be <code>null</code>
     * @return <code>true</code> if the string is whitespace, empty or
     *         <code>null</code>
     */
    private static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

}