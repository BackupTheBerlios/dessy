/*
 * Created on 05.07.2004
 * 
 * TODO To change the template for this generated file go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
package de.uniessen.wiinf.wip.goalgetter.overviewTable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import de.uniessen.wiinf.wip.goalgetter.overviewTable.OverviewTableEntry.TableElement;
/**
 * @author Jonas Sprenger
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class OverviewTable extends JTable {
	/** Stack für alle werte die bereits eingetragen wurden * */
	/**
	 * enthält alle typen der tabelle je spalte als Class
	 */
	Vector typ = new Vector();
	/**
	 * enthält alle namen der tabelle je spalte als String
	 */
	Vector name = new Vector();
	ContextPopup popup;
	public List entries;
	public OverviewTable(List entries) {
		this.entries = entries;
		getTypesAndNames(entries);
		AbstractTableModel model = createModel();
		addMouseMenu(this);
		setEditor();
		setModel(model);
		getTableHeader().setReorderingAllowed(false);
		setAutoResizeMode(3); //AUTO_RESIZE_ALL_COLUMNS
		setRowSelectionAllowed(false);
		setFocusable(false);
		setColumnSelectionAllowed(false);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setName("default name ");
		setShowHorizontalLines(false);
		int gapWidth = 0;
		int gapHeight = getRowHeight() / 4;
		//setIntercellSpacing(new Dimension(gapWidth, gapHeight));
		setRowHeight(getRowHeight() + gapHeight);

	}
	public void setEditor() {
		int tmp = getColumnCount();
		for (int i = 0; i < tmp; i++) {
			//this.getColumn(getColumnName(i)).setCellRenderer(new
			// DefaultTableCellRenderer());
			getColumn(getColumnName(i)).setCellEditor(
					new VisCellEditor(new JTextField()));
		}
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
		} else {
			//	 	 If not shaded, match the table's background
			c.setBackground(getBackground());
		}
		return c;
	}

	public AbstractTableModel createModel() {
		return new AbstractTableModel() {
			public String getColumnName(int col) {
				return name.get(col).toString();
			}
			public int getRowCount() {
				return entries.size();
			}
			public int getColumnCount() {
				return name.size();
			}
			public Object getValueAt(int row, int col) {
				OverviewTableEntry rowElement = (OverviewTableEntry) entries
						.get(row);
				TableElement te = rowElement.getElementByName(this
						.getColumnName(col));
				if (te != null)
					return te.getValue();
				else
					return null;
			}
			public void setValueAt(Object value, int row, int col) {
				try {
					//Object entry = this.getValueAt(row,col);
					OverviewTableEntry tmpgo = null;
					OverviewTableEntry rowElement = (OverviewTableEntry) entries
							.get(row);
					String colName = this.getColumnName(col);
					TableElement te = rowElement.getElementByName(colName);
					if (value != null && rowElement != null) {
						//						wenn eingabe ungültig...
						if (value.getClass().equals(java.lang.Double.class)) {
							try {
								Double.parseDouble(value.toString());
							} catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								//JOptionPane jp = new JOptionPane();
								JOptionPane.showConfirmDialog(new JFrame(),
										"error_during_input", //"Fehler bei
										// Eingabe",
										"MenuItem_WB_Properties",
										JOptionPane.CLOSED_OPTION,
										JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						te.setValue(value);// wert überschreiben
					
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			public boolean isCellEditable(int row, int col) {
				if (col > 0)
					return true;
				else
					return false;
			}
			public Class getColumnClass(int columnIndex) {
				return (Class) typ.get(columnIndex);
			}
		};
	}
	private void getTypesAndNames(List v) {
		OverviewTableEntry go = null;
		for (int i = 0; i < v.size(); i++) {
			go = (OverviewTableEntry) v.get(i);
			Class[] types = go.getColTypes();
			String[] names = go.getColNames();
			for (int j = 0; j < types.length; j++) {
				if (!name.contains(names[j])) {
					typ.add(types[j]);
					name.add(names[j]);
				}
			}
		}
	}
	public void addMouseMenu(final JTable t) {
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				checkPopupMenu(e);
			}

			public void mouseEntered(MouseEvent e) {
				checkPopupMenu(e);
			}

			public void mouseExited(MouseEvent e) {
				checkPopupMenu(e);
			}
			public void mousePressed(MouseEvent e) {
				checkPopupMenu(e);
			}
			public void mouseReleased(MouseEvent e) {
				checkPopupMenu(e);
			}
			/**
			 * @param e
			 */
			private void checkPopupMenu(MouseEvent e) {
				if (e.isPopupTrigger()) {
					//table.setSelectionPath(table.getClosestPathForLocation(e.getX(),
					//	e.getY()));
					//		JPopupMenu.setDefaultLightWeightPopupEnabled(true);
					//TODO: get MainFrame....
					popup = new ContextPopup(t);

					popup.show(e.getComponent(), e.getX() + 2, e.getY() + 5);
				}

			}
		});
	}
}