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
 * $Id: AlternativeContainerEditor.java,v 1.14 2004/09/08 18:31:34 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.editor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JScrollPane;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.uif.util.ResourceUtils;

import de.uniessen.wiinf.wip.goalgetter.domain.container.AlternativeContainer;
import de.uniessen.wiinf.wip.goalgetter.tool.Resources;
import de.uniessen.wiinf.wip.goalgetter.tool.tablemodel.AlternativeContainerTableModelTransposed;
import de.uniessen.wiinf.wip.goalgetter.view.editor.table.AlternativeContainerEditorPopupAdapter;
import de.uniessen.wiinf.wip.goalgetter.view.editor.table.OverviewTable;

/**
 * AlternativeContainerEditor
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.14 $
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
        overviewTable
                .addMouseListener(new AlternativeContainerEditorPopupAdapter());
        overviewTable.setHighlightColumn(0);
        
        addPropertyChangeListener(AlternativeContainer.PROPERTYNAME_ALTERNATIVES, new AlternativesChangedHandler());
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#getDomainClass()
     */
    public Class getDomainClass() {
        return AlternativeContainer.class;
    }

    /**
     * Convenience function for {@link AlternativeContainerEditor#getModel()}including the correct
     * typecast.
     * 
     * @return AlternativeContainer
     */
    private AlternativeContainer getAlternativeContainer() {
        return (AlternativeContainer) getModel();
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
        AlternativeContainerTableModelTransposed actm = new AlternativeContainerTableModelTransposed(
                getAlternativeContainer().getAlternativesListModel());
        overviewTable.setModel(actm);

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#updateView()
     */
    protected void updateView() {
        AlternativeContainerTableModelTransposed actm = new AlternativeContainerTableModelTransposed(
                getAlternativeContainer().getAlternativesListModel());
        overviewTable.setModel(actm);

    }
    
    /* (non-Javadoc)
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#activate()
     */
    public void activate() {       
        super.activate();
        updateView();
    }
    
    /* (non-Javadoc)
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#deactivate()
     */
    public void deactivate() {       
        super.deactivate();
    }
    
    private class AlternativesChangedHandler implements PropertyChangeListener {

        /* (non-Javadoc)
         * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
         */
        public void propertyChange(PropertyChangeEvent evt) {
           System.out.println("hier höre ich was");
            
        }
        
    }

}