/*
 * Created on 22.05.2004
 * 
 * TODO To change the template for this generated file go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
package de.uniessen.wiinf.wip.tree;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import de.uniessen.wiinf.wip.gui.Dessy;
import de.uniessen.wiinf.wip.gui.EingabePanel;
import de.uniessen.wiinf.wip.internationalisation.Internationalization;
/**
 * @author Jonas Sprenger
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class DessyTree extends JPanel {
	JTree tree;
	EingabePanel epanel;
	JInternalFrame parent;
	JDesktopPane deskpane;
	JFrame mainFrame;
	DessyTreePopUp popup;
	public DessyTree(EingabePanel in, JInternalFrame p) {
		mainFrame = Dessy.getMainFrame();
		parent = p;
		//popup = new DessyTreePopUp(mainFrame);
		deskpane = parent.getDesktopPane();
		this.epanel = in;
		init();
	}
	/**
	 * init method of DessyTree
	 */
	private void init() {
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Das System");
		createNodes(top);
		tree = new JTree(top);
		JScrollPane treeView = new JScrollPane(tree);
		this.add(treeView);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addMouseListener(new MouseAdapter() {
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
		});
		//Listen for when the selection changes.
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				if (node == null)
					return;
				Object nodeInfo = node.getUserObject();
				if (node.isLeaf()) {
					Entries book = (Entries) nodeInfo;
					//System.out.println(book.name+ " "+parent.isClosed());
					if (parent.isClosed()) {
						try {
							parent.setClosed(false);
							deskpane.add(parent);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					parent.setTitle(book.title);
					epanel.setName(book.name);
					epanel.update();
					parent.setVisible(true);
				} else {
				}
			}
		});
	}
	public void checkPopupMenu(MouseEvent event) {
		if (event.isPopupTrigger()) {
			tree.setSelectionPath(tree.getClosestPathForLocation(event.getX(),
					event.getY()));
			//		JPopupMenu.setDefaultLightWeightPopupEnabled(true);
			popup = new DessyTreePopUp(mainFrame, this);
			popup.show(event.getComponent(), event.getX() + 20,
					event.getY() + 15);
		}
	}
	private void createNodes(DefaultMutableTreeNode top) {
		DefaultMutableTreeNode root = null;
		DefaultMutableTreeNode book = null;
		root = new DefaultMutableTreeNode(new Entries(Internationalization
				.getMessage("Ziele"), Internationalization
				.getMessage("zielheader")));
		top.add(root);
		//original Tutorial
		book = new DefaultMutableTreeNode(new Entries("erstes ziel ",
				"Zieleingabe"));
		root.add(book);
		//...add more books for programmers...
		root = new DefaultMutableTreeNode(new Entries(Internationalization
				.getMessage("Alternativen"), Internationalization
				.getMessage("alternativenheader")));
		top.add(root);
		//VM
		book = new DefaultMutableTreeNode(new Entries("Harrald",
				"Alternativen eingeben"));
		root.add(book);
		//Language Spec
		book = new DefaultMutableTreeNode(new Entries("Peter",
				"Alternativen eingeben"));
		root.add(book);
		root = new DefaultMutableTreeNode(new Entries(Internationalization
				.getMessage("Aktionen"), Internationalization
				.getMessage("aktionenheader")));
		top.add(root);
		//VM
		book = new DefaultMutableTreeNode(new Entries("verhandeln",
				"Aktionen eingeben"));
		root.add(book);
		//Language Spec
		book = new DefaultMutableTreeNode(new Entries("weiterbilden",
				"Aktionen eingeben"));
		root.add(book);
		root = new DefaultMutableTreeNode(new Entries(Internationalization
				.getMessage("Sensibilitätsanalyse"), Internationalization
				.getMessage("sensibheader")));
		top.add(root);
		//VM
		book = new DefaultMutableTreeNode(new Entries("Balkendiagram",
				"Analyse "));
		root.add(book);
		book = new DefaultMutableTreeNode(new Entries("Histogram", "Analyse"));
		root.add(book);
	}
	/**
	 * @return Returns the tree.
	 */
	public JTree getTree() {
		return tree;
	}
	/**
	 * @param tree
	 *            The tree to set.
	 */
	public void setTree(JTree tree) {
		this.tree = tree;
	}
}