/*
 * ActionEditor.java
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
 * $Id: ActionByAlternativeEditor.java,v 1.2 2004/09/25 14:56:57 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.editor;

import javax.swing.JScrollPane;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.uif.util.ResourceUtils;

import de.uniessen.wiinf.wip.goalgetter.domain.Action;
import de.uniessen.wiinf.wip.goalgetter.domain.container.ActionContainer;
import de.uniessen.wiinf.wip.goalgetter.model.ActionContainerByAlternative;
import de.uniessen.wiinf.wip.goalgetter.model.Resources;
import de.uniessen.wiinf.wip.goalgetter.model.tablemodel.ActionContainerTableModel;
import de.uniessen.wiinf.wip.goalgetter.view.editor.table.ActionContainerEditorPopupAdapter;
import de.uniessen.wiinf.wip.goalgetter.view.editor.table.OverviewTable;

/**
 * An implementation of {@link Editor}that displays instances of class
 * {@link Action}.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.2 $
 *  
 */
public final class ActionByAlternativeEditor extends AbstractEditor {

    private static final long serialVersionUID = 1L;

    private OverviewTable overviewTable;

    /**
     * Constructs a <code>AlternativeContainerEditor</code>
     */
    public ActionByAlternativeEditor() {
        super(Resources.ACTION_ICON, ResourceUtils
                .getString("actionContainerEditor.actionOverview.text"));//$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#build()
     */
    protected void build() {
        initComponents();

        FormLayout layout = new FormLayout("0:grow:0.9");//$NON-NLS-1$
        DefaultFormBuilder builder = new DefaultFormBuilder(layout,
                ResourceUtils.getBundle(), this);
        builder.setDefaultDialogBorder();
        builder.appendRow(new RowSpec("fill:0dlu:grow")); //$NON-NLS-1$
        java.awt.Component overviewPane = new JScrollPane(overviewTable);
        builder.add(overviewPane);
    }

    /**
     *  
     */
    private void initComponents() {
        overviewTable = new OverviewTable();
        overviewTable.addMouseListener(new ActionContainerEditorPopupAdapter());
        overviewTable.setHighlightColumn(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#getDomainClass()
     */
    public Class getDomainClass() {
        return ActionContainerByAlternative.class;
    }

    /**
     * Convenience function for {@link ActionContainerEditor#getModel()}
     * including the correct typecast.
     * 
     * @return ActionContainer
     */
    private ActionContainerByAlternative getActionContainerbyAlternative() {
        return (ActionContainerByAlternative) getModel();
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
        ActionContainerTableModel actm = new ActionContainerTableModel(
                getActionContainerbyAlternative().getActionByAlternativeListModel());
        overviewTable.setModel(actm);

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#updateView()
     */
    protected void updateView() {
        ActionContainerTableModel actm = new ActionContainerTableModel(
                getActionContainerbyAlternative().getActionByAlternativeListModel());
        overviewTable.setModel(actm);

    }

}