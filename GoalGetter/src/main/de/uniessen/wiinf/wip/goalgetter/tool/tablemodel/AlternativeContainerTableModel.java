/*
 * AlternativeContainerTableModel.java
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
 * $Id: AlternativeContainerTableModel.java,v 1.6 2004/09/08 18:31:34 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.tool.tablemodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;

import de.uniessen.wiinf.wip.goalgetter.domain.Alternative;
import de.uniessen.wiinf.wip.goalgetter.domain.Goal;

/**
 * AlternativeContainerTableModel
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.6 $
 *  
 */
public class AlternativeContainerTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private final ListModel listModel;
    private List columnNames;

    /**
     * Constructs an AlternativeContainerTableModel for the given ListModel
     * 
     * @param listModel
     *            the ListModel to construct the TableModel for
     */
    public AlternativeContainerTableModel(ListModel listModel) {
        this.listModel   = listModel;	    
	    if (listModel == null)
	        throw new NullPointerException("The list model must not be null.");
	    
	    listModel.addListDataListener(createChangeHandler());
	    
        this.columnNames = createColumnNames();
       
    }

    private List createColumnNames() {
        List columnNameList = new ArrayList();
        columnNameList.add(" "); //$NON-NLS-1$
        Alternative alternative = (Alternative) getRow(1);
        List goals = alternative.getGoals();
        Iterator iterator = goals.iterator();
        while (iterator.hasNext()) {
            Goal g = (Goal) iterator.next();
            columnNameList.add(g.getName());
        }

        return columnNameList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex == 0 && columnIndex == 0) {
            return "Zielwert";
        }

        if (rowIndex == 0 && columnIndex > 0) {
            return getGoalAt(columnIndex).getIntensity();
        }

        Alternative alternative = (Alternative) getRow(rowIndex);       

        switch (columnIndex) {
        case 0:
            return alternative.getIdentifier();
        default:
            Goal g = getGoalAt(columnIndex);
            return alternative.getIntensity(g);

        }
    }

    /**
     * Only cells with a rowIndex > 0and a colIndex >0 can be edited. the first
     * row and cell are secondary header cells.
     */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return rowIndex > 0 && columnIndex > 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int, int)
     */
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Alternative alternative = (Alternative) getRow(rowIndex);

        Goal g = getGoalAt(columnIndex);
        alternative.putIntensity(g, (String) aValue);

    }

    /**
     * Returns the name of the column at the given column index. This is used to
     * initialize the table's column header name. Note: this name does not need
     * to be unique; two columns in a table can have the same name.
     * <p>
     * 
     * @param columnIndex
     *            the index of the column
     * @return the name of the column
     * 
     * @see #getColumnCount()
     * @see #getRowCount()
     */
    public String getColumnName(int columnIndex) {
        return (String) columnNames.get(columnIndex);
    }

    /**
     * Returns the number of columns in the model. A <code>JTable</code> uses
     * this method to determine how many columns it should create and display by
     * default.
     * <p>
     * 
     * @return the number of columns in the model
     * 
     * @see #getColumnName(int)
     * @see #getRowCount()
     */
    public int getColumnCount() {
       // System.out.println("colCount: "+columnNames.size());
        return columnNames.size();
    }
    
    public final int getRowCount() {
     //   System.out.println("rowCount: "+listModel.getSize());
        return listModel.getSize();
    }


    public List getHighlightColumns() {
        List l = new ArrayList();
        l.add(new Integer(0));
        l.add(new Integer(1));
        return l;
    }

    private Goal getGoalAt(int i) {
        Alternative alternative = (Alternative) getRow(1);       
        return (Goal) (alternative.getGoals()).get(i - 1);
    }
    
    
    /**
     * Returns the row at the specified row index.
     * 
     * @param index   row index in the underlying list model
     * @return the row at the specified row index. 
     */
    protected final Object getRow(int index) {
        return listModel.getElementAt(index);
    }
    
    
    
    // Event Handling *******************************************************
    
    /**
     * Creates and returns a listener that handles changes 
     * in the underlying list model.
     * 
     * @return the listener that handles changes in the underlying ListModel
     */
    protected ListDataListener createChangeHandler() {
        return new ListDataChangeHandler();
    }
    
    /*
     * Listens to subject changes and fires a contents change event.
     */
    private class ListDataChangeHandler implements ListDataListener {

        /** 
         * Sent after the indices in the index0,index1 
         * interval have been inserted in the data model.
         * The new interval includes both index0 and index1.
         *
         * @param evt  a <code>ListDataEvent</code> encapsulating the
         *    event information
         */
        public void intervalAdded(ListDataEvent evt) {
            columnNames = createColumnNames();
            fireTableRowsInserted(evt.getIndex0(), evt.getIndex1());
            fireTableStructureChanged();
        }
    
        
        /**
         * Sent after the indices in the index0,index1 interval
         * have been removed from the data model.  The interval 
         * includes both index0 and index1.
         *
         * @param evt  a <code>ListDataEvent</code> encapsulating the
         *    event information
         */
        public void intervalRemoved(ListDataEvent evt) {
            columnNames = createColumnNames();
            fireTableRowsDeleted(evt.getIndex0(), evt.getIndex1());
            fireTableStructureChanged();
        }
    
    
        /** 
         * Sent when the contents of the list has changed in a way 
         * that's too complex to characterize with the previous 
         * methods. For example, this is sent when an item has been
         * replaced. Index0 and index1 bracket the change.
         *
         * @param evt  a <code>ListDataEvent</code> encapsulating the
         *    event information
         */
        public void contentsChanged(ListDataEvent evt) {
            columnNames = createColumnNames();
            int firstRow = evt.getIndex0();
            int lastRow = evt.getIndex1();
            fireTableRowsUpdated(firstRow, lastRow);
            fireTableStructureChanged();
        }

    }

}