/*
 * ActionContainerEditor.java
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
 * $Id: ActionContainerEditor.java,v 1.5 2004/08/07 09:28:03 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.editor;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif.util.ResourceUtils;

import de.uniessen.wiinf.wip.goalgetter.domain.ActionContainer;
import de.uniessen.wiinf.wip.goalgetter.tool.Resources;

/**
 * GoalContainerEditor
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.5 $
 *  
 */
public class ActionContainerEditor extends AbstractEditor {

    /**
     * Constructs a <code>ActionContainerEditor</code>
     */
    public ActionContainerEditor() {
        super(Resources.ACTION_ICON, "Actions Overview");
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#build()
     */
    protected void build() {
        // TODO Auto-generated method stub
        initComponents();

        FormLayout layout = new FormLayout("right:max(40dlu;p), 4dlu, 0:grow");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout,
                ResourceUtils.getBundle(), this);
        builder.setDefaultDialogBorder();
        //  CellConstraints cc = new CellConstraints();
        builder.appendSeparator("Actions");

    }

    private void initComponents() {
        // empty. no components to initialize
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#getDomainClass()
     */
    public Class getDomainClass() {
        return ActionContainer.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#getTitleSuffix()
     */
    protected String getTitleSuffix() {
        return getActionContainer().getIdentifier();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uniessen.wiinf.wip.goalgetter.view.editor.AbstractEditor#updateModel()
     */
    protected void updateModel() {
        // TODO Auto-generated method stub

    }

    private ActionContainer getActionContainer() {
        return (ActionContainer) getModel();
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