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
 * $Id: SensitivityAnalysisDialog.java,v 1.6 2004/08/22 11:52:04 jsprenger Exp $
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

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.Borders;
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
 * @version $Revision: 1.6 $
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
	JPanel spinnerPanel;
	
	Integer stepSize;
	
	SensitivityAnalysisDialog dialog;
	
	JSpinner stepSizeTextField;
	
	JSpinner spin1;
	boolean flag = true; 
	
/**
	 * Constructs a <code>PreferencesDialog</code>.
	 * 
	 * @param owner
	 *            this dialog's parent frame
	 */
	public SensitivityAnalysisDialog(Frame owner, List elements, String nameX,
			String nameY) {
		super(owner, "Sensitivitätsanalyse",false);
		
		this.setResizable(true);
		this.resize(new Dimension(600,800));
		super.resize(new Dimension(600,800));
		super.setResizable(true);
		
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
		sortElements();
		
		chart = new SensitivityAnalysisChart(elements, nameX, nameY);
	
		FormLayout layout = new FormLayout(
		"right:max(500dlu;p)");  
		
		JPanel panel = new JPanel();
		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
				ResourceUtils.getBundle(), panel);
		builder.setDefaultDialogBorder();
		
		builder.append(buildViewPanel());
		builder.nextLine();
		builder.appendSeparator();
		builder.nextLine();
		builder.append(buildbuttonview());
		builder.nextLine();
		builder.appendSeparator();
		builder.nextLine();
		builder.append(buildbuttonExit());
		p2 = builder.getPanel();
		return p2;
	}
	/**
	 * @return
	 */
	private Component buildViewPanel() {
		
		FormLayout layout = new FormLayout(
		"right:pref,10dlu,350dlu");//  
		JPanel panel = new JPanel();
		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
				ResourceUtils.getBundle(), panel);
		testPanel=chart.getChartPanel();
		testPanel.setPreferredSize(new Dimension(250, 400));
		JScrollPane pane = (JScrollPane) buildEditFields();
		pane.setPreferredSize(new Dimension(250,400));
		pane.setBorder(Borders.EMPTY_BORDER);
		builder.append(pane);
		builder.append(testPanel);
		builder.nextLine();
		builder.appendSeparator();
		builder.nextLine();
		return builder.getPanel();
	}
	/**
	 * @return
	 */
	private Component buildbuttonview() {
		JPanel panel = new JPanel();
		
		FormLayout layout = new FormLayout(
		"right:pref,10dlu, 40dlu, 5dlu, 40dlu,5dlu,60dlu");//                                   

		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
				ResourceUtils.getBundle(), panel);
		//		 auswahl für bestealternative
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
		//builder.append("Beste Alternative: " + bestAlternative);
		//		 die combo box
		String[] views = {"2DStackedBarChart", "3DStackedBarChart","2DBarChart","3DBarChart"};
		JComboBox box = new JComboBox(views);
		box.setPreferredSize(new Dimension(100, 20));
		box.addActionListener(new BoxAction());
		builder.append(box);
		
		JCheckBox cbox = new JCheckBox();
		cbox.setLabel("Gitter");
		cbox.setSelected(true);
		cbox.setPreferredSize(new Dimension(50, 20));
		cbox.addActionListener(new CheckBoxAction());
		builder.append(cbox);
		
		SpinnerNumberModel model1 = new SpinnerNumberModel(stepSize,
				   new Integer(1), null, stepSize);
		stepSizeTextField = new JSpinner(model1);
		stepSizeTextField.setPreferredSize(new Dimension(100, 20));
		stepSizeTextField.addChangeListener(new TextFieldAction());
		builder.append("Schrittgröße",stepSizeTextField);

		 return builder.getPanel();
	}
	/**
	 * @return
	 */
	private Component buildbuttonExit() {
		JPanel panel = new JPanel();
		
		FormLayout layout = new FormLayout(
		"right:max(120dlu;d),10dlu,71dlu");//                                   

		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
				ResourceUtils.getBundle(), panel);
				
		JButton btn = new JButton("Aktualisieren");
		btn.addActionListener(new UpdateAction());
		btn.setPreferredSize(new Dimension(100, 20));
		builder.append(btn);
		
		JButton closeBtn = new JButton("Close");
		closeBtn.setPreferredSize(new Dimension(80, 20));
		closeBtn.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				dialog.dispose();				
			}
		});
		builder.append(closeBtn);
		return builder.getPanel();
	}
	
	/**
	 * @return
	 */
private JComponent buildEditFields() {
		
	FormLayout layout = new FormLayout(
				"right:pref,5dlu, 50dlu, 5dlu, 25dlu,5dlu,default");//                                   
	spinnerPanel = new JPanel();
		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
				ResourceUtils.getBundle(), spinnerPanel);
		// setzen der Textfelder mit aktuellen und orginal werten
		SensitivityElements se;
		sortElements();
		Iterator it = elements.iterator();
		
		while (it.hasNext()) {
			se = (SensitivityElements) it.next();
			Map values = se.getValues();
			Map orginals = se.getOrginalValues();
			//builder.append(se.getName());
			builder.appendSeparator(se.getName());
			builder.nextLine();
			Set keys = values.keySet();
			Iterator i = keys.iterator();
			while (i.hasNext()) {
				Object tmp = i.next();
			
				int value = Integer.parseInt(values.get(tmp).toString());
				stepSize = new Integer(1);
				SpinnerNumberModel model = new SpinnerNumberModel(new Integer(values.get(tmp).toString()),
										   null, null, stepSize);
				spin1 = new JSpinner(model);
				spin1.setPreferredSize(new Dimension(70,20));
				//spin1.setName(String.valueOf(elements.indexOf(se)) + "," + tmp.toString());
				spin1.setName(tmp.toString());
				spin1.addChangeListener(new SpinnerAction(se));
				builder.append(tmp.toString()+"("+orginals.get(tmp)
						.toString()+")",spin1);
					
//				builder.append(tmp.toString()+"("+orginals.get(tmp)
//						.toString()+")", tf);
				builder.nextLine();
			}
		}
		// preferred size muss immer größer ein als die von der scrollpane
		spinnerPanel.setPreferredSize(new Dimension(120, spinnerPanel.countComponents() * 10));
		JScrollPane pane = new JScrollPane();
		pane.setViewportView(spinnerPanel);
		pane.setPreferredSize(new Dimension(200, 400));
		
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
	
	private void sortElements(){
		Iterator i = elements.iterator();
		Map entries;
		Set keys;
		Iterator it;
		int currentvalue = 0,lastvalue=0;
		
		while (i.hasNext()) {
		SensitivityElements se = (SensitivityElements)i.next();
	
		entries = se.getValues();	
		keys = entries.keySet();
		it = keys.iterator();
		String e;
		while(it.hasNext()){
		e = (String)entries.get(it.next());
		currentvalue += Integer.parseInt(e);
				}
		if(currentvalue < lastvalue || lastvalue == 0){
			
			//System.out.println("Schleife   ");
			lastvalue = currentvalue;
			currentvalue = 0;
			elements.remove(se);
			elements.add(0,se);
			i = elements.iterator();
					
			}
		else{
		lastvalue = currentvalue;
		currentvalue=0;
	  }
	}
			
	}
	class SpinnerAction implements ChangeListener {
		
		SensitivityElements element;
		public SpinnerAction(SensitivityElements en){
		this.element = en;
		}

		public void stateChanged(ChangeEvent e) {
			if(flag){
			JSpinner sp;
				sp = (JSpinner) e.getSource();
				String spName = sp.getName();		
				((Map)element.getValues()).put(spName,sp.getValue().toString());			
				sortElements();
				try {
					Thread t = new Thread(new Runnable(){
				public void run() {
				chart.updateValues(elements);
				}
				});
				t.start();
				} catch (RuntimeException e1) {
					e1.printStackTrace();
				}
		  }
		}		
	}
	class TextFieldAction implements ChangeListener {
		
	public void stateChanged(ChangeEvent e) {
			JSpinner jtx = (JSpinner) e.getSource();
			SpinnerNumberModel model;
			
			stepSize = (Integer)jtx.getValue();
						
			Component[] c = spinnerPanel.getComponents();
			flag = false;
			for(int i=0;i<c.length;i++){
			if(c[i] instanceof JSpinner){
			model = (SpinnerNumberModel)((JSpinner)c[i]).getModel();
			model.setStepSize(stepSize);
			}
		}
			flag = true;
	}
}
	class UpdateAction implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			Component[] c = spinnerPanel.getComponents();
			SpinnerNumberModel model;
			
			stepSize = (Integer)stepSizeTextField.getValue();
			
			for(int i=0;i<c.length;i++){
			if(c[i] instanceof JSpinner){
			model = (SpinnerNumberModel)((JSpinner)c[i]).getModel();
			model.setStepSize(stepSize);
				}
			}
			sortElements();
			Thread t = new Thread(new Runnable(){
			public void run() {
			chart.updateValues(elements);
			}
			});
			t.start();
			
						
		}
	}
	class BoxAction implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
					
			sortElements();
			JComboBox box = (JComboBox) e.getSource();
			String newStatus = box.getSelectedItem().toString();
			if(! newStatus.equals(chart.getStatus())){
			chart.setStatus(newStatus);
			Thread t = new Thread(new Runnable(){
			public void run() {
			chart.setChartchanged(true);
			chart.updateValues(elements);
			}
			});
			t.start();
			}
		}
	}
	class CheckBoxAction implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			JCheckBox box = (JCheckBox) e.getSource();	
			chart.setGitterFlag(box.isSelected());
			Thread t = new Thread(new Runnable(){
			public void run() {
			chart.updateValues(elements);
			}
			});
			t.start();
			}
		}
}