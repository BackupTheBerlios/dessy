/*
 * AlternativeContainerTableModelTransposed.java
 * Package: de.uniessen.wiinf.wip.goalgetter.overviewTable
 * Project: GoalGetter
 * 
 * GoalGetter is based on a decision supporting method 
 * developed by Markus Stallkamp (markus.stallkamp@uni-essen.de)
 * 
 * (c) 2004 
 * Jonas Sprenger (jonas.sprenger@gmx.de),
 * Tim Franz (tim.franz@uni-essen.de)
 * 
 * $Id: AlternativeContainerTableModelTransposed.java,v 1.1 2004/09/25 14:56:57 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.model.tablemodel;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;

/**
 * A wrapper Model around {@link AlternativeContainerTableModel}to transpose
 * the data 90 degrees.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public class AlternativeContainerTableModelTransposed extends
        AbstractTableModel {

    /**
     * holds the non-transposed table model which does the real work
     */
    private AlternativeContainerTableModel tableModel;

    /**
     * Constructs an AlternativeContainerTableModel for the given ListModel by
     * initializing the real tablemodel
     * 
     * @param listModel
     *            the ListModel to construct the TableModel for
     */
    public AlternativeContainerTableModelTransposed(ListModel listModel) {
      //  listModel.addListDataListener(createChangeHandler());
        tableModel = new AlternativeContainerTableModel(listModel);
       

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return tableModel.getColumnName(rowIndex + 1);
        }
        return tableModel.getValueAt(columnIndex - 1, rowIndex + 1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#isCellEditable(int, int)
     */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return tableModel.isCellEditable(columnIndex - 1, rowIndex + 1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int, int)
     */
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        tableModel.setValueAt(aValue, columnIndex - 1, rowIndex + 1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#getColumnName(int)
     */
    public String getColumnName(int columnIndex) {
        if (columnIndex == 0)
            return " "; //$NON-NLS-1$
        return (String) tableModel.getValueAt(columnIndex - 1, 0);
    }

    /**
     * The column count of the transpose table model is the row count of the
     * original model plus 1 for the former header name which has to be
     * represented now by a normal column.
     */
    public int getColumnCount() {
        return tableModel.getRowCount() + 1;
    }

    /**
     * The row count of the transpose table model is the column count of the
     * original model minus 1 for the former header name
     */
    public int getRowCount() {
        return tableModel.getColumnCount() - 1;
    }
    
   

    

}