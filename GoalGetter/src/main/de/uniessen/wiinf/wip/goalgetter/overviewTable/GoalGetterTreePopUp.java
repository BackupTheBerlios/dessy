/*
 * Created on 22.05.2004
 * 
 * TODO To change the template for this generated file go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
package de.uniessen.wiinf.wip.goalgetter.overviewTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import com.jgoodies.uif.util.ResourceUtils;

/**
 * @author Jonas Sprenger
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class GoalGetterTreePopUp extends JPopupMenu implements ActionListener {
	private JMenuItem delete;
	private JMenuItem addCol;
	private JMenuItem addRow;
	private JPopupMenu layout;
	boolean hasParent = false;
	private JTable  table;
	
	public GoalGetterTreePopUp( JTable t) {
		table = t;
		//newEntrie = add(new JMenuItem(ResourceUtils.getString("newEntrie")));
		addCol = add(ResourceUtils.getString("addCol"));
		addRow = add(ResourceUtils.getString("addRow"));
		addSeparator();
		delete = add(ResourceUtils.getString("delete"));
		addRow.addActionListener(this);
		delete.addActionListener(this);
		addCol.addActionListener(this);
		
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		//System.out.println(cmd);
		//if (hasParent) {
		
		if (cmd.equals(delete.getActionCommand())) {
			System.out.println("Delete root ?");
			
			}
		if (cmd.equals(addRow.getActionCommand())) {
			System.out.println("add Row ?");
		
			}
		 else if (cmd.equals(addCol.getActionCommand())) {
			System.out.println("add  Column ?");
	
		
		}
	}
}