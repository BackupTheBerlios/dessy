/*
 * AlternativeContainerEditor.java
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
 * $Id: AlternativeContainerEditor.java,v 1.3 2004/07/12 12:38:12 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.editor;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif.util.ResourceUtils;

import de.uniessen.wiinf.wip.goalgetter.domain.AlternativeContainer;
import de.uniessen.wiinf.wip.goalgetter.tool.Resources;

/**
 * AllAlternativesEditor
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.3 $
 *  
 */
public class AlternativeContainerEditor extends AbstractEditor {

    private JTable overviewTable;

    public AlternativeContainerEditor() {
        super(Resources.ALTERNATIVE_ICON, "Alternative Overview");
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#build()
     */
    protected void build() {
        initComponents();

        FormLayout layout = new FormLayout("right:max(40dlu;p), 4dlu, 160dlu");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout,ResourceUtils.getBundle(), this);
        builder.setDefaultDialogBorder();
        //  CellConstraints cc = new CellConstraints();
        builder.appendI15dSeparator("alternativeContainerEditor.alternatives.text");
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

//        String rowData[][] = {
//
//        { "Availability", "April", "Jan-May" },
//                { "Knowledge", "C++", "Pascal" },
//                { "Salary", "11.000", "12.000" }
//
//        };
//        String columnNames[] = { "  ", "Klaus", "Peter" };
//
//        overviewTable = new JTable(rowData, columnNames);
       
        List entries = new ArrayList();
        // neue Zeile
       OverviewTableEntry element = new OverviewTableEntry("Alternative","Klaus");
       element.newElement("Availability"," June",java.lang.String.class);
       element.newElement("Knowledge","java",java.lang.String.class);
       element.newElement("Salary","12.000",java.lang.String.class);
       entries.add(element);
        // neue Zeile
       element =  new OverviewTableEntry("Alternative","Peter");
       element.newElement("Availability","June",java.lang.String.class);
       element.newElement("Knowledge","java",java.lang.String.class);
       element.newElement("Salary","12.000",java.lang.String.class);
       entries.add(element);
       // neue Zeile
       
        // erzeuge neue Tabelle
       overviewTable = new OverviewTable(entries);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#getDomainClass()
     */
    public Class getDomainClass() {
        return AlternativeContainer.class;
    }

    private AlternativeContainer getAlternativeContainer() {
        return (AlternativeContainer) getModel();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#getTitleSuffix()
     */
    protected String getTitleSuffix() {
        return getAlternativeContainer().getIdentifier();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#updateModel()
     */
    protected void updateModel() {
        // TODO Auto-generated method stub

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