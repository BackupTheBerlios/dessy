package de.uniessen.wiinf.wip.gui;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import de.uniessen.wiinf.wip.internationalisation.Internationalization;
import de.uniessen.wiinf.wip.tree.DessyTree;
/**
 * @author Tim Franz, Jonas Sprenger
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class Dessy {
	/** the main Frame */
	static JFrame mainFrame;
	JMenuBar menuBar;
	/** the tree Panel */
	
	JSplitPane splitPane1;
	JSplitPane splitPane2;
	JSplitPane splitMain;
	/** desktop and frame for the first projekt*/
	JDesktopPane desktopPane1;
	JInternalFrame innerFrame1;
	JPanel treePanel1;
	EingabePanel innerPanel1;
	/** desktop and fram for the second projekt*/
	JDesktopPane desktopPane2;
	JInternalFrame innerFrame2;
	JPanel treePanel2;
	EingabePanel innerPanel2;
	
	private static Dessy dessy;
	public void init() {
		dessy = this;
		mainFrame = new JFrame(Internationalization.getMessage("mainName"));
		
		// menu Bar
		menuBar = new DessyMenuBar();
		mainFrame.setJMenuBar(menuBar);
		
		//		 inner frames
		desktopPane1 = new JDesktopPane();
		innerFrame1 = new JInternalFrame(Internationalization
				.getMessage("willkomen"));		
		innerPanel1 = new EingabePanel(" ");
		innerFrame1.getContentPane().add(innerPanel1);
		innerFrame1.setSize(300, 300);
		innerFrame1.setVisible(false);
		innerFrame1.setClosable(true);
		innerFrame1.setMaximizable(true);
		innerFrame1.setResizable(true);
		innerFrame1.setIconifiable(true);
		desktopPane1.add(innerFrame1);
		
		//  the tree
		treePanel1 = new DessyTree(innerPanel1, innerFrame1);
		splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePanel1,
				desktopPane1);
		splitPane1.setOneTouchExpandable(true);
		mainFrame.getContentPane().add(splitPane1);
		
		// main Frame settings ..
		mainFrame.setSize(600, 600);
		mainFrame.setLocation(300, 200);
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		mainFrame.addWindowListener(l);
		mainFrame.setVisible(true);
		mainFrame.show();
	}
	public static void main(String[] args) {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (Exception e) {
//		}
		Dessy d = new Dessy();
		d.init();
	}
	/**
	 * @return
	 */
	public static Dessy getMain() {
		// TODO Auto-generated method stub
		return dessy;
	}
	/**
	 *  
	 */
	public void addProjektToGui() {
		if (splitMain == null) {
			
			desktopPane2 = new JDesktopPane();
			
			innerFrame2 = new JInternalFrame(Internationalization
					.getMessage("willkomen"));
			innerPanel2 = new EingabePanel(" ");
			innerFrame2.getContentPane().add(innerPanel2);
			innerFrame2.setSize(300, 300);
			innerFrame2.setVisible(false);
			innerFrame2.setClosable(true);
			innerFrame2.setMaximizable(true);
			innerFrame2.setResizable(true);
			innerFrame2.setIconifiable(true);
			desktopPane2.add(innerFrame2);
			DessyTree treePanel2 = new DessyTree(innerPanel2, innerFrame2);
			
			mainFrame.remove(splitPane1);
			splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,treePanel2,desktopPane2);
			splitPane2.setOneTouchExpandable(true);
			splitMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,splitPane1,splitPane2);
			splitMain.setOneTouchExpandable(true);
			splitMain.setResizeWeight(0.5);
			mainFrame.getContentPane().add(splitMain);
			mainFrame.setVisible(true);
			//mainFrame.getContentPane().repaint();
			
		} else {
			JOptionPane.showConfirmDialog(mainFrame, Internationalization
					.getMessage("projekt_zu_viel"), Internationalization
					.getMessage("projekt_schliessen"),
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
		}
	}
	/**
	 * @return
	 */
	public static JFrame getMainFrame() {
		// TODO Auto-generated method stub
		return mainFrame;
	}
}