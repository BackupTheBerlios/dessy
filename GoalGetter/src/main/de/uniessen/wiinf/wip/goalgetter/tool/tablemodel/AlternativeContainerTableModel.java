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
 * $Id: AlternativeContainerTableModel.java,v 1.1 2004/08/14 11:11:12 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.tool.tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;

import com.jgoodies.binding.adapter.TableAdapter;

import de.uniessen.wiinf.wip.goalgetter.domain.Alternative;

/**
 * AlternativeContainerTableModel
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public class AlternativeContainerTableModel extends TableAdapter {

    private static final long serialVersionUID = 1L;

    private List columnNames;

    /**
     * Constructs an AlternativeContainerTableModel for the given ListModel
     * 
     * @param listModel
     *            the ListModel to construct the TableModel for
     */
    public AlternativeContainerTableModel(ListModel listModel) {
        super(listModel);
        this.columnNames = createColumnNames();
    }

    private List createColumnNames() {
        List columnNameList = new ArrayList();
        columnNameList.add("Zielkriterium");
        columnNameList.add("Zielwert");

        for (int i = 0; i < getRowCount(); i++) {
            Alternative alternative = (Alternative) getRow(i);
            columnNameList.add(alternative.getIdentifier());
        }
        return columnNameList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        Alternative alternative = (Alternative) getRow(rowIndex);

        switch (columnIndex) {
        case 0:
        //   return alternative.getIntensities().keySet();
        //        case 1:
        //            return alternative.get();
        //        case 2:
        //            return alternative.getIntensity();
        default:
            return null;
        }
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Alternative alternative = (Alternative) getRow(rowIndex);
        switch (columnIndex) {
        case 0:
            alternative.setIdentifier((String) aValue);
            break;
        //        case 1:
        //            alternative.setUnit((String) aValue);
        //            break;
        //        case 2:
        //            alternative.setIntensity((String) aValue);
        //            break;
        }
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
        return columnNames.size();
    }

    public List getHighlightColumns() {
        List l = new ArrayList();
        l.add(new Integer(0));
        l.add(new Integer(1));
        return l;
    }

}