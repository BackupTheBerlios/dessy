/*
 * SensitivityAnalysisDialog Package:
 * de.uniessen.wiinf.wip.goalgetter.view.sensitivity Project: GoalGetter
 * 
 * GoalGetter is based on a decision supporting method developed by Markus
 * Stallkamp (markus.stallkamp@uni-essen.de)
 * 
 * (c) 2004 Jonas Sprenger (jonas.sprenger@gmx.de), Tim Franz
 * (tim.franz@uni-essen.de)
 * 
 * ---
 * 
 * This project uses the JGoodies Framework and its Skeleton Pro Application
 * Copyright (c) 2002-2004 JGoodies Karsten Lentzsch. All Rights Reserved. See
 * Readme file for detailed license
 * 
 * $Id: SensitivityAnalysisDialog.java,v 1.2 2004/08/14 21:46:51 jsprenger Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.sensitivity;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif.AbstractDialog;
import com.jgoodies.uif.application.ResourceIDs;
import com.jgoodies.uif.util.Resizer;
import com.jgoodies.uif.util.ResourceUtils;
import com.jgoodies.uifextras.panel.HeaderPanel;
/**
 * 
 * Builds the preferences dialog.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.2 $
 *  
 */
public final class SensitivityAnalysisDialog extends AbstractDialog {
	// Instance Creation ******************************************************
	List elements;
	String nameX, nameY;
	SensitivityAnalysisChart chart;
	Frame parent;
	
	JPanel testPanel;
	JPanel p2;
	
	SensitivityAnalysisDialog dialog;
/**
	 * Constructs a <code>PreferencesDialog</code>.
	 * 
	 * @param owner
	 *            this dialog's parent frame
	 */
	public SensitivityAnalysisDialog(Frame owner, List elements, String nameX,
			String nameY) {
		super(owner, "Sensitivitätsanalyse",false);
		this.parent = owner;
		this.elements = elements;
		this.nameX = nameX;
		this.nameY = nameY;
		dialog = this;
	}
	// Building *************************************************************
	/**
	 * Builds the UI.
	 */
	protected void build() {
		super.build();
	}

	/**
	 * Builds and returns the header.
	 */
	protected JComponent buildHeader() {
		return new HeaderPanel(
				"Sensitivitätsanalyse",
				"Führen Sie eine Sensitivitätsanalyse durch. Sie werden begeistert sein.",
				ResourceUtils.getIcon(ResourceIDs.PREFERENCES_ICON));
	}
	/**
	 * Builds and returns the preference's content pane.
	 */
	protected JComponent buildContent() {
		
		chart = new SensitivityAnalysisChart(elements, nameX, nameY);
		
		FormLayout layout = new FormLayout("160dlu:grow");
		JPanel panel = new JPanel();
		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
				ResourceUtils.getBundle(), panel);
		builder.setDefaultDialogBorder();
		testPanel=chart.getChartPanel();
		builder.append(testPanel);
		builder.nextLine();
		builder.appendI15dSeparator("");
		builder.nextLine();
		builder.append(buildEditFields());
		
		builder.append(buildButtonBarWithClose());
		p2 = builder.getPanel();
		return p2;
	}
	/**
	 * @return
	 */
private JComponent buildEditFields() {
		FormLayout layout = new FormLayout(
				"right:max(40dlu;d), 4dlu, 0:grow, 4dlu,right:max(40dlu;d), 4dlu, 0:grow");
		JPanel panel = new JPanel();
		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
				ResourceUtils.getBundle(), panel);
		
		// auswahl für bestealternative
		int first = 0;
		String bestAlternative = "";
		SensitivityElements se;
		Iterator it = elements.iterator();
		while (it.hasNext()) {
			se = (SensitivityElements) it.next();
			Map values = se.getValues();
			Set keys = values.keySet();
			Iterator i = keys.iterator();
			int value = 0;
			while (i.hasNext()) {
				Object tmp = i.next();
				value += Integer.parseInt(values.get(tmp).toString());
			}
			if (value < first || first == 0) {
				bestAlternative = se.getName();
				first = value;
			}
		}
		builder.append("Beste Alternative: " + bestAlternative);
		builder.nextColumn();
		builder.nextColumn();
		//		 die combo box
		String[] views = {"2D", "3D"};
		JComboBox box = new JComboBox(views);
		box.setPreferredSize(new Dimension(70, 20));
		box.addActionListener(new BoxAction());
		builder.append("Ansicht ", box);
		// 		Ende ComboBox
		
		builder.nextLine();
		builder.appendSeparator();
		// setzen der Textfelder mit aktuellen und orginal werten
		it = elements.iterator();
		while (it.hasNext()) {
			se = (SensitivityElements) it.next();
			Map values = se.getValues();
			Map orginals = se.getOrginalValues();
			builder.append(se.getName());
			builder.nextLine();
			Set keys = values.keySet();
			Iterator i = keys.iterator();
			while (i.hasNext()) {
				Object tmp = i.next();
				int value = Integer.parseInt(values.get(tmp).toString());
				
				JTextField tf = new JTextField(values.get(tmp).toString());
				tf.addActionListener(new TextFieldAction());
				tf.setName(se.getName() + "," + tmp.toString());
				
				builder.append(tmp.toString(), tf);
				builder.append("Orginal ", new JLabel(orginals.get(tmp)
						.toString()));
				builder.nextLine();
			}
			builder.appendSeparator();
		}
		// preferred size muss immer größer ein als die von der scrollpane
		panel.setPreferredSize(new Dimension(10, panel.countComponents() * 10));
		JScrollPane pane = new JScrollPane();
		pane.setViewportView(panel);
		pane.setPreferredSize(new Dimension(10, 310));
		return pane;
	}	
	/**
		  * Unlike the default try to get an aspect ratio of 1:1.
		  * 
		  * @param component
		  *            the component to resize
		  */
	protected void resizeHook(JComponent component) {
		Resizer.DEFAULT.resizeDialogContent(component);
	}
	class TextFieldAction implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			JTextField jtx = (JTextField) e.getSource();
			if (!jtx.getText().equals("")) {
				Map map;
				Iterator i = elements.iterator();
				String[] t = jtx.getName().split(",");
				while (i.hasNext()) {
					SensitivityElements element = (SensitivityElements) i
							.next();
					if (element.getName().equals(t[0])) {
						element.getValues().put(t[1], jtx.getText());
						//map.put(t[1],jtx.getText());
					}
				}
				chart.updateValues(elements);
			
			}
		}
	}
	class BoxAction implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			JComboBox box = (JComboBox) e.getSource();
			String newStatus = box.getSelectedItem().toString();
			if(! newStatus.equals(chart.getStatus())){
			chart.setStatus(newStatus);
			chart.updateValues(elements);
			}
		}
	}
}