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
 * $Id: AlternativeContainerEditor.java,v 1.1 2004/07/03 20:17:08 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.editor;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import de.uniessen.wiinf.wip.goalgetter.domain.AlternativeContainer;
import de.uniessen.wiinf.wip.goalgetter.tool.Resources;

/**
 * AllAlternativesEditor
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
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
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.setDefaultDialogBorder();
        //  CellConstraints cc = new CellConstraints();
        builder.appendSeparator("Alternatives");
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

        String rowData[][] = {

        { "Availability", "April", "Jan-May" },
                { "Knowledge", "C++", "Pascal" },
                { "Salary", "11.000", "12.000" }

        };
        String columnNames[] = { "  ", "Klaus", "Peter" };

        overviewTable = new JTable(rowData, columnNames);
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