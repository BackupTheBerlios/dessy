/*
 * GoalContainerEditor.java
 * Package: de.uniessen.wiinf.wip.goalgetter.view.editor
 * Project: GoalGetter
 * 
 * GoalGetter is based on a decision supporting method 
 * developed by Markus Stallkamp (markus.stallkamp@uni-essen.de)
 * 
 * (c) 2004 
 * Jonas Sprenger (jonas.sprenger@gmx.de),
 * Tim Franz (tim.franz@uni-essen.de)
 * 
 * ---
 * 
 * This project uses the JGoodies Framework and its Skeleton Pro Application
 * Copyright (c) 2002-2004 JGoodies Karsten Lentzsch. All Rights Reserved.
 * See Readme file for detailed license
 * 
 * $Id: GoalContainerEditor.java,v 1.2 2004/07/12 11:59:37 jsprenger Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.editor;



import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.uniessen.wiinf.wip.goalgetter.domain.GoalContainer;
import de.uniessen.wiinf.wip.goalgetter.overviewTable.OverviewTable;
import de.uniessen.wiinf.wip.goalgetter.overviewTable.OverviewTableEntry;
import de.uniessen.wiinf.wip.goalgetter.tool.Resources;

/**
 * GoalContainerEditor
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.2 $
 *  
 */
public class GoalContainerEditor extends AbstractEditor {

    private JTable overviewTable;
    
    public GoalContainerEditor() {
        super(Resources.GOAL_ICON, "Goals Overview");
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#build()
     */
    protected void build() {
        initComponents();

        FormLayout layout = new FormLayout("right:max(40dlu;p), 4dlu, 160dlu");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.setDefaultDialogBorder();
        //  CellConstraints cc = new CellConstraints();
        builder.appendSeparator("Goals");
        builder.appendRow(builder.getLineGapSpec());
        // builder.appendRow(new RowSpec("fill:200dlu:nogrow"));
        builder.nextLine(2);
        
        java.awt.Component overviewPane = new JScrollPane(overviewTable);
        builder.append(overviewPane, 3);
      
    }

    /**
     *  
     */
    private void initComponents() {
        //TODO table model
        
    	
    	
    	
//    	String rowData[][] = { { "Availability", "=", "may - june" },
//                { "Knowledge", ">=", "Java" }, { "Salary", "<=", "10.000" } };
//        String columnNames[] = { "Goal", "operator", "intensity" };
//        overviewTable = new JTable(rowData, columnNames);
    	
    	         
         List entries = new ArrayList();
          // neue Zeile
         OverviewTableEntry element = new OverviewTableEntry("Goal","Availability");
         element.newElement("Operator","=",java.lang.String.class);
         element.newElement("Intensity","may - june",java.lang.String.class);
         entries.add(element);
          // neue Zeile
         element =  new OverviewTableEntry("Goal","Knowledge");
         element.newElement("Operator",">=",java.lang.String.class);
         element.newElement("Intensity","java",java.lang.String.class); 
         entries.add(element);
         // neue Zeile
         element =  new OverviewTableEntry("Goal","Salary");
         element.newElement("Operator",">=",java.lang.String.class);
         element.newElement("Intensity","12.000",java.lang.String.class);
         entries.add(element);
          // erzeuge neue Tabelle
         overviewTable = new OverviewTable(entries);
          }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#getDomainClass()
     */
    public Class getDomainClass() {
        return GoalContainer.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#getTitleSuffix()
     */
    protected String getTitleSuffix() {
        return getGoalContainer().getIdentifier();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#updateModel()
     */
    protected void updateModel() {
        // TODO Auto-generated method stub

    }

    private GoalContainer getGoalContainer() {
        return (GoalContainer) getModel();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#updateView()
     */
    protected void updateView() {
        // TODO Auto-generated method stub

    }

}