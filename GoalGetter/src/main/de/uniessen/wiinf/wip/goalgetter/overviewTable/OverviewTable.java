/*
 * OverviewTable.java
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
 * ---
 * 
 * This project uses the JGoodies Framework and its Skeleton Pro Application
 * Copyright (c) 2002-2004 JGoodies Karsten Lentzsch. All Rights Reserved.
 * See Readme file for detailed license
 * 
 * $Id: OverviewTable.java,v 1.8 2004/08/14 11:11:11 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.overviewTable;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

import com.jgoodies.uifextras.util.ExtTable;

/**
 * OverviewTable
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.8 $
 *  
 */
public class OverviewTable extends ExtTable {

    private int highlightColumn = -1;

    /**
     * Constructs an <code>ExtTable</code>.
     */
    public OverviewTable() {
        configureTable();
    }

    /**
     * Sets a column as the highlight column. This column is displayed with a
     * different background than the other columns
     * 
     * @param i
     *            column number
     */
    public void setHighlightColumn(int i) {
        highlightColumn = i;
    }

    /**
     * Answers the highlight column
     * 
     * @return column index of highlighted column or -1 if no column is
     *         highlighted
     */
    public int getHighlightColumn() {
        return highlightColumn;
    }

    /**
     * Constructs an <code>ExtTable</code> for the specified row data and
     * column names.
     * 
     * @param columnNames
     * @param rowData
     */
    public OverviewTable(Object[][] rowData, Object[] columnNames) {
        super(rowData, columnNames);
        configureTable();
    }

    protected void configureTable() {
        super.configureTable();
        getTableHeader().setReorderingAllowed(false);
        setAutoResizeMode(AUTO_RESIZE_LAST_COLUMN);
        setRowSelectionAllowed(true);
        setFocusable(true);
        setColumnSelectionAllowed(true);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setShowHorizontalLines(false);
        int gapHeight = getRowHeight() / 4;
        setRowHeight(getRowHeight() + gapHeight);
        //setDefaultEditor (Object.class, new GenericEditor());
        setEnabled(true);
    }

    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex,
            int vColIndex) {
        Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
        if (vColIndex != getHighlightColumn()) {

            if (rowIndex % 2 == 1 && !isCellSelected(rowIndex, vColIndex)) {

                Color color = UIManager.getColor("List.selectionBackground");//$NON-NLS-1$

                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                Color bgcolor = new Color(r, g, b, 34);

                c.setBackground(bgcolor);
                c.setForeground(Color.black);
            } else {
                //	 	 If not shaded, match the table's background
                c.setBackground(getBackground());
                c.setForeground(Color.black);
            }
        } else {

            Color color = UIManager.getColor("List.selectionBackground");//$NON-NLS-1$

            int r = color.getRed();
            int g = color.getGreen();
            int b = color.getBlue();

            Color bgcolor = new Color(r, g, b, 64);

            c.setBackground(bgcolor);
            c.setForeground(Color.black);

        }

        if (isCellSelected(rowIndex, vColIndex)) {
            c.setBackground(UIManager.getColor("List.selectionBackground"));//$NON-NLS-1$
            c.setForeground(UIManager.getColor("List.selectionForeground"));//$NON-NLS-1$
        }
        return c;
    }

}