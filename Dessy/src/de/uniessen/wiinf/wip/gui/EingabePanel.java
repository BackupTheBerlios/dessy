/*
 * Created on 22.05.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.uniessen.wiinf.wip.gui;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif_lite.panel.SimpleInternalFrame;

/**
 * @author Jonas Sprenger
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class EingabePanel extends JPanel {
	JTextField jtxf;
	JLabel label;
	String name;
	public EingabePanel(String n) {
		name = n;
		initComponents();
		
		
		this.add(buildPanel());
		//this.add(sif);
	}
	
	/**
	 *  
	 */
	public void initComponents() {
		jtxf = new JTextField();

	}

	public JComponent buildPanel() {
		// Separating the component initialization and configuration
		// from the layout code makes both parts easier to read.
		initComponents();
		FormLayout layout = new FormLayout("right:max(40dlu;p), 4dlu, 80dlu"); // add
																			   // rows
																			   // dynamically

		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		builder.setDefaultDialogBorder();
		builder.appendTitle("Wurst");
		//builder.nextLine();

		builder.append(this.name + "aaa", jtxf);
		builder.appendSeparator("Segment");
		builder.nextLine();

		builder.append(this.name + "aaa", jtxf);

		// The builder holds the layout container that we now return.
		return builder.getPanel();
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 *  
	 */
	public void update() {
		//	label.setText(name);
		jtxf.setText("");
		this.updateUI();

	}
}