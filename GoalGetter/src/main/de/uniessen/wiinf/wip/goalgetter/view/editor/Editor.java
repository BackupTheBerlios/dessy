/*
 * Editor.java
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
 * $Id: Editor.java,v 1.2 2004/08/07 09:28:03 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.editor;

import javax.swing.Icon;
import javax.swing.JToolBar;

/**
 * This interface describes general editor that have an <code>Icon</code>,
 * title, <code>ToolBar</code>, and can set and return a model.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.2 $
 *  
 */
public interface Editor {

    /**
     * Answers the editor's icon.
     * 
     * @return the editor's icon.
     */
    Icon getIcon();

    /**
     * Answers the editor's title.
     * 
     * @return the editor's title.
     */
    String getTitle();

    /**
     * Answers the editor's tool bar.
     * 
     * @return the editor's tool bar.
     */
    JToolBar getToolBar();

    /**
     * Activates the editor.
     */
    void activate();

    /**
     * Deactivates the editor.
     */
    void deactivate();

    /**
     * Returns the associated domain class used to register this editor with the
     * EditorPanel's registry.
     * 
     * @return associated domain Class
     */
    Class getDomainClass();

    /**
     * Returns this editor's model.
     * 
     * @return the editor's model
     */
    Object getModel();

    /**
     * Sets this editor's model. Called when the edited instance changed.
     * 
     * @param model
     *            the model to set for the editor
     */
    void setModel(Object model);

}