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
 * $Id: AlternativeContainerEditor.java,v 1.11 2004/08/15 07:51:42 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.editor;

import javax.swing.JScrollPane;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif.util.ResourceUtils;

import de.uniessen.wiinf.wip.goalgetter.domain.container.AlternativeContainer;
import de.uniessen.wiinf.wip.goalgetter.overviewTable.AlternativeContainerEditorPopupAdapter;
import de.uniessen.wiinf.wip.goalgetter.overviewTable.OverviewTable;
import de.uniessen.wiinf.wip.goalgetter.tool.Resources;
import de.uniessen.wiinf.wip.goalgetter.tool.tablemodel.AlternativeContainerTableModel;

/**
 * AlternativeContainerEditor
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.11 $
 *  
 */
public class AlternativeContainerEditor extends AbstractEditor {

    private static final long serialVersionUID = 1L;

    private OverviewTable overviewTable;

    /**
     * Constructs a <code>AlternativeContainerEditor</code>
     */
    public AlternativeContainerEditor() {
        super(
                Resources.ALTERNATIVE_ICON,
                ResourceUtils
                        .getString("alternativeContainerEditor.alternativeOverview.text"));//$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#build()
     */
    protected void build() {
        initComponents();

        FormLayout layout = new FormLayout(
        "0:grow:0.9");//$NON-NLS-1$
DefaultFormBuilder builder = new DefaultFormBuilder(layout,
        ResourceUtils.getBundle(), this);
builder.setDefaultDialogBorder();
        builder
                .appendI15dSeparator("alternativeContainerEditor.alternatives.text");//$NON-NLS-1$
 
        builder.nextLine();
        java.awt.Component overviewPane = new JScrollPane(overviewTable);
        builder.append(overviewPane, 3);
    }

    /**
     *  
     */
    private void initComponents() {
        overviewTable = new OverviewTable();
        overviewTable
                .addMouseListener(new AlternativeContainerEditorPopupAdapter());
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
        AlternativeContainerTableModel actm = new AlternativeContainerTableModel(
                getAlternativeContainer().getAlternativesListModel());
        overviewTable.setModel(actm);

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#updateView()
     */
    protected void updateView() {
        AlternativeContainerTableModel actm = new AlternativeContainerTableModel(
                getAlternativeContainer().getAlternativesListModel());
        overviewTable.setModel(actm);

    }

}