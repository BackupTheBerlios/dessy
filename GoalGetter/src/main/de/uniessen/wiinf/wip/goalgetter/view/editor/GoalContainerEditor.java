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
 * $Id: GoalContainerEditor.java,v 1.13 2004/08/16 12:51:40 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.editor;

import javax.swing.JScrollPane;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif.util.ResourceUtils;

import de.uniessen.wiinf.wip.goalgetter.domain.container.GoalContainer;
import de.uniessen.wiinf.wip.goalgetter.tool.Resources;
import de.uniessen.wiinf.wip.goalgetter.tool.tablemodel.GoalContainerTableModel;
import de.uniessen.wiinf.wip.goalgetter.view.editor.table.GoalContainerEditorPopupAdapter;
import de.uniessen.wiinf.wip.goalgetter.view.editor.table.OverviewTable;

/**
 * GoalContainerEditor
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.13 $
 *  
 */
public class GoalContainerEditor extends AbstractEditor {

    private OverviewTable overviewTable;

    /**
     * Constructs a GoalContainerEditor
     */
    public GoalContainerEditor() {
        super(Resources.GOAL_ICON, ResourceUtils
                .getString("goalContainerEditor.goalsOverview.text"));//$NON-NLS-1$
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
        //   builder.appendRow(builder.getLineGapSpec());
        // builder.appendRow(new RowSpec("fill:200dlu:nogrow"));
        //builder.nextLine();

        JScrollPane overviewPane = new JScrollPane(overviewTable);
        builder.append(overviewPane);

    }

    /**
     *  
     */
    private void initComponents() {
        overviewTable = new OverviewTable();
        overviewTable.addMouseListener(new GoalContainerEditorPopupAdapter());

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
        return ""; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#updateModel()
     */
    protected void updateModel() {
        GoalContainerTableModel gctm = new GoalContainerTableModel(
                getGoalContainer().getGoals());
        overviewTable.setModel(gctm);

    }

    /**
     * Convenience function for {@link #getModel()}including the correct
     * typecast.
     * 
     * @return GoalContainer
     */
    private GoalContainer getGoalContainer() {
        return (GoalContainer) getModel();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#updateView()
     */
    protected void updateView() {
        GoalContainerTableModel gctm = new GoalContainerTableModel(
                getGoalContainer().getGoals());
        overviewTable.setModel(gctm);

    }

}