/*
 * ActionContainerByGoalTableModel.java
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
 * $Id: ActionContainerTableModel.java,v 1.2 2004/09/27 08:46:39 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.model.tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;

import com.jgoodies.binding.adapter.TableAdapter;

import de.uniessen.wiinf.wip.goalgetter.domain.Action;
import de.uniessen.wiinf.wip.goalgetter.domain.Goal;

/**
 * AlternativeContainerTableModel
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.2 $
 *  
 */
public class ActionContainerTableModel extends TableAdapter {

    private static final long serialVersionUID = 1L;

    private static final String[] columnNames = { "Zielname", "Aktion",
            "Ergebnis", "Aktionszahlung", "Trade-Off-Zahlung" };

    /**
     * Constructs an AlternativeContainerTableModel for the given ListModel
     * 
     * @param listModel
     *            the ListModel to construct the TableModel for
     */
    public ActionContainerTableModel(ListModel listModel) {
        super(listModel, columnNames);

    }
    
    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        Action action = (Action) getRow(rowIndex);
        Object out;
        switch (columnIndex) {
        case 0:
            out = action.getGoal().getName();
            break;
        case 1:
            out = action.getName();
            break;
        case 2:
            out = action.getResult();
            break;
        case 3:
            out = action.getPaymentForAction();
            break;
        case 4:
            out = action.getPaymentForTradeoff();
            break;
        default:
            out = null;
            break;
        }
        if (out == null) {
            out = "";
        }
        return out;
    }

    /**
     * Only cells with a rowIndex > 0and a colIndex >0 can be edited. the first
     * row and cell are secondary header cells.
     */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex > 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int, int)
     */
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Action action = (Action) getRow(rowIndex);
        switch (columnIndex) {
        case 0:
            break;
        case 1:
            action.setName((String) aValue);
            break;
        case 2:
            action.setResult((String) aValue);
            break;
        case 3:
            action.setPaymentForAction((Integer) aValue);
            break;
        case 4:
            action.setPaymentForTradeoff((Integer) aValue);
            break;
        default:
            break;
        }
    }

    public List getHighlightColumns() {
        List l = new ArrayList();
        l.add(new Integer(0));
        l.add(new Integer(1));
        return l;
    }

}