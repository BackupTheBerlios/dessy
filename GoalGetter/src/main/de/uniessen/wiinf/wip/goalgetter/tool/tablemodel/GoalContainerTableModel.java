/*
 * GoalContainerTableModel.java
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
 * $Id: GoalContainerTableModel.java,v 1.3 2004/08/14 11:11:12 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.tool.tablemodel;

import javax.swing.ListModel;

import com.jgoodies.binding.adapter.TableAdapter;
import com.jgoodies.uif.util.ResourceUtils;

import de.uniessen.wiinf.wip.goalgetter.domain.Goal;

/**
 * GoalContainerTableModel
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.3 $
 *  
 */
public class GoalContainerTableModel extends TableAdapter {

    private static final String[] COLUMN_NAMES = {
            ResourceUtils.getString("goalEditor.identifier.text"), //$NON-NLS-1$
            ResourceUtils.getString("goalEditor.unit.text"), //$NON-NLS-1$
            ResourceUtils.getString("goalEditor.intensity.text") }; //$NON-NLS-1$

    /**
     * @param listModel
     */
    public GoalContainerTableModel(ListModel listModel) {
        super(listModel, COLUMN_NAMES);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        Goal goal = (Goal) getRow(rowIndex);
        switch (columnIndex) {
        case 0:
            return goal.getName();
        case 1:
            return goal.getUnit();
        case 2:
            return goal.getIntensity();
        default:
            return null;
        }
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Goal goal = (Goal) getRow(rowIndex);
        switch (columnIndex) {
        case 0:
            goal.setName((String) aValue);
            break;
        case 1:
            goal.setUnit((String) aValue);
            break;
        case 2:
            goal.setIntensity((String) aValue);
            break;
        }
    }

}