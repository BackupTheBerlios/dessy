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
 * $Id: AlternativeContainerEditor.java,v 1.8 2004/08/07 09:28:03 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.editor;

import javax.swing.JScrollPane;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif.util.ResourceUtils;

import de.uniessen.wiinf.wip.goalgetter.domain.AlternativeContainer;
import de.uniessen.wiinf.wip.goalgetter.overviewTable.AlternativeContainerEditorPopupAdapter;
import de.uniessen.wiinf.wip.goalgetter.overviewTable.OverviewTable;
import de.uniessen.wiinf.wip.goalgetter.tool.Resources;

/**
 * AlternativeContainerEditor
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.8 $
 *  
 */
public class AlternativeContainerEditor extends AbstractEditor {

    private OverviewTable overviewTable;

    /**
     * Constructs a <code>AlternativeContainerEditor</code>
     */
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

        FormLayout layout = new FormLayout("right:max(40dlu;p), 4dlu, 0:grow");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout,
                ResourceUtils.getBundle(), this);
        builder.setDefaultDialogBorder();
        //  CellConstraints cc = new CellConstraints();
        builder
                .appendI15dSeparator("alternativeContainerEditor.alternatives.text");
       // builder.appendRow(builder.getLineGapSpec());
        // builder.appendRow(new RowSpec("fill:200dlu:nogrow"));
       // builder.nextLine(2);
        builder.nextLine();
        java.awt.Component overviewPane = new JScrollPane(overviewTable);
        builder.append(overviewPane, 3);
    }

    /**
     *  
     */
    private void initComponents() {
        //TODO table model

        String rowData[][] = {

        { "Availability", "Juni","April", "Jan-May" },
                { "Knowledge", "Java","C++", "Pascal" },
                { "Salary", "10.000","11.000", "12.000" }

        };
        String columnNames[] = { "  ","Soll", "Klaus", "Peter" };

        overviewTable = new OverviewTable(rowData, columnNames);
        overviewTable.setHighlightColumn(1);
        overviewTable.addMouseListener(new AlternativeContainerEditorPopupAdapter());
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