/*
 * GoalEditor.java Package: de.uniessen.wiinf.wip.goalgetter.view.editor
 * Project: GoalGetter
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
 * $Id: GoalEditor.java,v 1.2 2004/07/12 11:59:37 jsprenger Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.editor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import com.jgoodies.binding.adapter.DocumentAdapter;
import com.jgoodies.binding.beans.BeanAdapter;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import de.uniessen.wiinf.wip.goalgetter.domain.Goal;
import de.uniessen.wiinf.wip.goalgetter.overviewTable.OverviewTable;
import de.uniessen.wiinf.wip.goalgetter.overviewTable.OverviewTableEntry;
import de.uniessen.wiinf.wip.goalgetter.tool.Resources;
/**
 * An implementation of {@link Editor}that displays instances of class
 * {@link Goal}.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.2 $
 *  
 */
public class GoalEditor extends AbstractEditor {
	private JTextComponent descriptionArea;
	private JTextComponent identifierField;
	private JTextComponent unitField;
	private JTextComponent operatorField;
	private JTextComponent intensityField;
	JTable overviewTable;
	/**
	 * Constructs a <code>GoalEditor</code>.
	 */
	public GoalEditor() {
		super(Resources.GOAL_ICON);
	}
	//  Implementing Abstract Superclass Behavior ****************************
	private Goal getGoal() {
		return (Goal) getModel();
	}
	protected String getTitleSuffix() {
		return getGoal().getIdentifier();
	}
	public Class getDomainClass() {
		return de.uniessen.wiinf.wip.goalgetter.domain.Goal.class;
	}
	// Component Creation and Configuration **********************************
	/**
	 * Creates and intializes the UI components.
	 */
	private void initComponents() {
		identifierField = new JTextField();
		descriptionArea = new JEditorPane();
		unitField = new JTextField();
		intensityField = new JTextField();
		operatorField = new JTextField();
		beanAdapter = new BeanAdapter(getModel(), true);
		//
		//        identifierField.setDocument(new DocumentAdapter(beanAdapter
		//                .getValueModel(Goal.PROPERTYNAME_IDENTIFIER)));
		//        descriptionArea.setDocument(new DocumentAdapter(beanAdapter
		//                .getValueModel(Goal.PROPERTYNAME_DESCRIPTION)));
		//        unitField.setDocument(new DocumentAdapter(beanAdapter
		//                .getValueModel(Goal.PROPERTYNAME_UNIT)));
		//        intensityField.setDocument(new DocumentAdapter(beanAdapter
		//                .getValueModel(Goal.PROPERTYNAME_INTENSITY)));
		//        operatorField.setDocument(new DocumentAdapter(beanAdapter
		//                .getValueModel(Goal.PROPERTYNAME_OPERATOR)));
		// erzeuge neue Tabelle
		List entries;
		entries = new ArrayList();
		OverviewTableEntry element = new OverviewTableEntry(
				Goal.PROPERTYNAME_IDENTIFIER, beanAdapter
						.getValueModel(Goal.PROPERTYNAME_IDENTIFIER));
		element.newElement("Unit", "June", java.lang.String.class);
		element.newElement("Operator", "java", java.lang.String.class);
		element.newElement("Intensity", "java", java.lang.String.class);
		element.newElement("Description", "......", java.lang.String.class);
		entries.add(element);
		overviewTable = new OverviewTable(entries);
	}
	// Building *************************************************************
	/**
	 * Builds the pane. Columns are specified before components are added to the
	 * form, rows are added dynamically using the {@link DefaultFormBuilder}.
	 * <p>
	 * 
	 * The builder combines a step that is done again and again: add a label,
	 * proceed to the next data column and add a component.
	 */
	protected void build() {
		initComponents();
		//
		//        java.awt.Component descriptionPane = new
		// JScrollPane(descriptionArea);
		//        FormLayout layout = new FormLayout("right:max(40dlu;p), 4dlu,
		// 160dlu");
		//
		//        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
		//        builder.setDefaultDialogBorder();
		//        builder.appendSeparator("Goal");
		//        builder.append("Identifier", identifierField);
		//        builder.nextLine();
		//        builder.append("Unit", unitField);
		//        builder.nextLine();
		//        builder.append("Operator", operatorField);
		//        builder.nextLine();
		//        builder.append("Intensity", intensityField);
		//        builder.nextLine();
		//        builder.appendSeparator("Description");
		//        builder.appendRow(builder.getLineGapSpec());
		//        builder.appendRow(new RowSpec("fill:50dlu:nogrow"));
		//        builder.nextLine(2);
		//        builder.append("", descriptionPane);
		
		FormLayout layout = new FormLayout("right:max(40dlu;p), 4dlu, 160dlu");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
		builder.setDefaultDialogBorder();
		//  CellConstraints cc = new CellConstraints();
		builder.appendSeparator("Goal");
		builder.appendRow(builder.getLineGapSpec());
		// builder.appendRow(new RowSpec("fill:200dlu:nogrow"));
		builder.nextLine(2);
		java.awt.Component overviewPane = new JScrollPane(overviewTable);
		builder.append(overviewPane, 3);
	}
}