/*
 * Created on 05.07.2004
 * 
 * TODO To change the template for this generated file go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
package de.uniessen.wiinf.wip.goalgetter.overviewTable;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

import com.jgoodies.uifextras.util.ExtTable;
/**
 * @author Jonas Sprenger
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class OverviewTable extends ExtTable {

	
    /**
	 * Constructs an <code>ExtTable</code>.
	 */	
	public OverviewTable() {
		configureTable();
	}

	/**
	 * Constructs an <code>ExtTable</code> for the specified row data and
	 * column names.
	 */	
    public OverviewTable(Object[][] rowData, Object[] columnNames) {
    	super(rowData, columnNames);
    	configureTable();
    }
	
	protected void configureTable(){
	    super.configureTable();
	    getTableHeader().setReorderingAllowed(false);
		setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS); 
		setRowSelectionAllowed(true);
		setFocusable(true);
		setColumnSelectionAllowed(true);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		setShowHorizontalLines(false);
		int gapHeight = getRowHeight() / 4;
		setRowHeight(getRowHeight() + gapHeight);
		//setDefaultEditor  (Object.class, new GenericEditor());		
		setEnabled(true);
	}

	public Component prepareRenderer(TableCellRenderer renderer, int rowIndex,
			int vColIndex) {
		Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
		if (rowIndex % 2 == 1 && !isCellSelected(rowIndex, vColIndex)) {

			Color color = UIManager.getColor("List.selectionBackground");

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
		if(isCellSelected(rowIndex,vColIndex)){
		    c.setBackground(UIManager.getColor("List.selectionBackground"));
		    c.setForeground(UIManager.getColor("List.selectionForeground"));
		}
		return c;
	}

	
	
	
}