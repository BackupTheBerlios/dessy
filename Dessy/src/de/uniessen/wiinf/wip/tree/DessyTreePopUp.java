/*
 * Created on 22.05.2004
 * 
 * TODO To change the template for this generated file go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
package de.uniessen.wiinf.wip.tree;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import de.uniessen.wiinf.wip.chart.DessyChart;
import de.uniessen.wiinf.wip.internationalisation.Internationalization;
/**
 * @author Jonas Sprenger
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class DessyTreePopUp extends JPopupMenu implements ActionListener {
	private JMenuItem delete;
	private JMenuItem rename;
	private JMenuItem showAsTable;
	private JMenuItem newEntrie;
	private JMenuItem analyse;
	private JPopupMenu layout;
	boolean hasParent = false;
	private JFrame parent;
	private DefaultMutableTreeNode node;
	private JTree tree;
	private DessyTree dessyTree;
	public DessyTreePopUp(JFrame p, DessyTree t) {
		parent = p;
		dessyTree = t;
		tree = t.getTree();
		node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		newEntrie = add(new JMenuItem(Internationalization
				.getMessage("newEntrie")));
		rename = add(Internationalization.getMessage("rename"));
		delete = add(Internationalization.getMessage("delete"));
		addSeparator();
		analyse = add(Internationalization.getMessage("Sensibilitätsanalyse"));
		showAsTable = add(new JMenuItem(Internationalization
				.getMessage("showasTable")));
		analyse.addActionListener(this);
		rename.addActionListener(this);
		delete.addActionListener(this);
		showAsTable.addActionListener(this);
		newEntrie.addActionListener(this);
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
		Object nodeInfo = node.getUserObject();
		Entries en = null;
		if (cmd.equals(delete.getActionCommand())) {
			//tree.remove((Component)nodeInfo);
			if (node.isRoot())
				System.out.println("Delete root ?");
			else if (node.isLeaf()) {
				node.removeFromParent();
				tree.updateUI();
			} else {
				//node.removeAllChildren();
				node.removeFromParent();
				tree.updateUI();
				tree.clearSelection();
			}
		} else if (cmd.equals(newEntrie.getActionCommand())) {
			//			 TODO ganz dreckig gehackt
			Entries book = null;
			if (node == null)
				return;
			book = (Entries) nodeInfo;
			//System.out.println(book.name+ " "+parent.isClosed());
			if (dessyTree.parent.isClosed()) {
				try {
					dessyTree.parent.setClosed(false);
					dessyTree.deskpane.add(dessyTree.parent);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			} else {
				dessyTree.parent.setTitle(book.title);
				dessyTree.epanel.setName(book.name);
				dessyTree.epanel.update();
				dessyTree.parent.setVisible(true);
			}
		} else if (cmd.equals(analyse.getActionCommand())) {
			DessyChart dc = new DessyChart(tree);
			JInternalFrame innerFrame1 = new JInternalFrame(Internationalization
					.getMessage("Sensibilitätsanalyse"));
			innerFrame1 = new JInternalFrame(Internationalization
					.getMessage("willkomen"));
			innerFrame1.getContentPane().add(dc.getChartPanel());
			innerFrame1.setLocation(20,20);
			innerFrame1.setSize(300, 300);
			innerFrame1.setVisible(true);
			innerFrame1.setClosable(true);
			innerFrame1.setMaximizable(true);
			innerFrame1.setResizable(true);
			innerFrame1.setIconifiable(true);
			dessyTree.deskpane.add(innerFrame1);
		
			//dessyTree.parent.setVisible(true);
	
		} else if (cmd.equals(rename.getActionCommand())) {
			final RenameDialog rd = new RenameDialog(parent, true);
			//rd.setNewName(vc.getName());
			rd.setNewName(node.toString());
			rd.setLocation(300, 300);
			rd.setVisible(true);
			if (rd.isOK) {
				if (nodeInfo instanceof Entries) {
					en = (Entries) nodeInfo;
					en.setName(rd.getNewName());
				}
				//((DefaultTreeModel)
				// getModel()).nodeChanged(selectedNode);
				rd.dispose();
				parent.repaint();
			}
			//}
		}
	}
}