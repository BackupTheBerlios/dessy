/*
 * IntegerEditor.java
 * Package: de.uniessen.wiinf.wip.goalgetter.view.editor.table
 * Project: GoalGetter
 * 
 * GoalGetter is based on a decision supporting method 
 * developed by Markus Stallkamp (markus.stallkamp@uni-essen.de)
 * 
 * (c) 2004 
 * Jonas Sprenger (jonas.sprenger@gmx.de),
 * Tim Franz (tim.franz@uni-essen.de)
 * 
 * $Id: IntegerEditor.java,v 1.1 2004/09/27 08:46:39 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.view.editor.table;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 * Implements a cell editor that uses a formatted text field to edit Integer
 * values.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 */
public class IntegerEditor extends DefaultCellEditor {
    JFormattedTextField ftf;

    NumberFormat integerFormat;

    private Integer minimum, maximum;

    private boolean DEBUG = false;

    /**
     * Constructs a cell editor for Integer values in the given bounds
     * 
     * @param min
     *            minimum value allowed
     * @param max
     *            maximum value allowed
     */
    public IntegerEditor(int min, int max) {
        super(new JFormattedTextField());
        ftf = (JFormattedTextField) getComponent();
        minimum = new Integer(min);
        maximum = new Integer(max);

        //Set up the editor for the integer cells.
        integerFormat = NumberFormat.getIntegerInstance();
        NumberFormatter intFormatter = new NumberFormatter(integerFormat);
        intFormatter.setFormat(integerFormat);
        intFormatter.setMinimum(minimum);
        intFormatter.setMaximum(maximum);

        ftf.setFormatterFactory(new DefaultFormatterFactory(intFormatter));
        ftf.setValue(minimum);
        ftf.setHorizontalAlignment(SwingConstants.TRAILING);
        ftf.setFocusLostBehavior(JFormattedTextField.PERSIST);

        //React when the user presses Enter while the editor is
        //active. (Tab is handled as specified by
        //JFormattedTextField's focusLostBehavior property.)
        ftf.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                "check"); //$NON-NLS-1$
        ftf.getActionMap().put("check", new AbstractAction() { //$NON-NLS-1$
            public void actionPerformed(ActionEvent e) {
                if (!ftf.isEditValid()) { //The text is invalid.
                    if (userSaysRevert()) { //reverted
                        ftf.postActionEvent(); //inform the editor
                    }
                } else
                    try { //The text is valid,
                        ftf.commitEdit(); //so use it.
                        ftf.postActionEvent(); //stop editing
                    } catch (java.text.ParseException exc) {
                        // empty
                    }
            }
        });
    }

    /*
     * Override to invoke setValue on the formatted text field.
     * 
     * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing.JTable,
     *      java.lang.Object, boolean, int, int)
     */
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        JFormattedTextField ftf = (JFormattedTextField) super
                .getTableCellEditorComponent(table, value, isSelected, row,
                        column);
        ftf.setValue(value);
        return ftf;
    }

    /*
     * Override to ensure that the value remains an Integer.
     * 
     * @see javax.swing.CellEditor#getCellEditorValue()
     */
    public Object getCellEditorValue() {
        JFormattedTextField lftf = (JFormattedTextField) getComponent();
        Object o = lftf.getValue();
        if (o instanceof Integer) {
            return o;
        } else if (o instanceof Number) {
            return new Integer(((Number) o).intValue());
        } else {
            if (DEBUG) {
                System.out.println("getCellEditorValue: o isn't a Number"); //$NON-NLS-1$
            }
            try {
                return integerFormat.parseObject(o.toString());
            } catch (ParseException exc) {
                System.err.println("getCellEditorValue: can't parse o: " + o); //$NON-NLS-1$
                return null;
            }
        }
    }

    /*
     * Override to check whether the edit is valid, setting the value if it is
     * and complaining if it isn't. If it's OK for the editor to go away, we
     * need to invoke the superclass's version of this method so that everything
     * gets cleaned up.
     * 
     * @see javax.swing.CellEditor#stopCellEditing()
     */
    public boolean stopCellEditing() {
        JFormattedTextField lftf = (JFormattedTextField) getComponent();
        if (lftf.isEditValid()) {
            try {
                lftf.commitEdit();
            } catch (java.text.ParseException exc) {
                // empty
            }

        } else { //text is invalid
            if (!userSaysRevert()) { //user wants to edit
                return false; //don't let the editor go away
            }
        }
        return super.stopCellEditing();
    }

    /*
     * Lets the user know that the text they entered is bad. Returns true if the
     * user elects to revert to the last good value. Otherwise, returns false,
     * indicating that the user wants to continue editing.
     */
    protected boolean userSaysRevert() {
        Toolkit.getDefaultToolkit().beep();
        ftf.selectAll();
        Object[] options = { "Edit", "Revert" }; //$NON-NLS-1$ //$NON-NLS-2$
        int answer = JOptionPane.showOptionDialog(SwingUtilities
                .getWindowAncestor(ftf),
                "The value must be an integer between " + minimum + " and "
                        + maximum + ".\n" + "You can either continue editing "
                        + "or revert to the last valid value.",
                "Invalid Text Entered", JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE, null, options, options[1]);

        if (answer == 1) { //Revert!
            ftf.setValue(ftf.getValue());
            return true;
        }
        return false;
    }
}
