/*
 * AbstractEditor.java
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
 * $Id: AbstractEditor.java,v 1.2 2004/08/07 09:28:03 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.editor;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.jgoodies.binding.beans.BeanAdapter;
import com.jgoodies.forms.builder.DefaultFormBuilder;

/**
 * 
 * The abstract superclass of all <code>Editor</code> implementations. The
 * JGoodies DataBinding Helper classes are used for automatic updates of View
 * and Model.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.2 $
 *  
 */
public abstract class AbstractEditor extends JPanel implements Editor {

    protected final Icon icon;

    protected final String titlePrefix;

    private Object model;

    protected BeanAdapter beanAdapter;

    // Instance Creation ****************************************************

    /**
     * Constructs an <code>AbstractEditor</code> with the specified
     * <code>Icon</code> and title prefix.
     * 
     * @param icon
     *            icon for the editor
     * @param titlePrefix
     *            titlePrefix for the editor
     */
    public AbstractEditor(Icon icon, String titlePrefix) {
        this.icon = icon;
        this.titlePrefix = titlePrefix;
        build();
    }

    /**
     * Constructs an <code>AbstractEditor</code> with the specified icon.
     * 
     * @param icon
     *            icon for the editor
     */
    public AbstractEditor(Icon icon) {
        this(icon, "");
    }

    /**
     * Constructs an <code>AbstractEditor</code> with the specified title
     * prefix.
     * 
     * @param titlePrefix
     *            titlePrefix for the editor
     */
    public AbstractEditor(String titlePrefix) {
        this(null, titlePrefix);
    }

    // Abstract Behavior ****************************************************

    /**
     * Returns the class used to register this instance in the UpdateManager.
     */
    abstract public Class getDomainClass();

    /**
     * Builds the pane. Columns are specified before components are added to the
     * form, rows are added dynamically using the {@link DefaultFormBuilder}.
     * <p>
     * 
     * The builder combines a step that is done again and again: add a label,
     * proceed to the next data column and add a component.
     */
    abstract protected void build();

    /**
     * Writes the view contents to the underlying model.
     */
    //    abstract protected void updateModel();
    /**
     * Reads the view contents from the underlying model.
     */
    //    abstract protected void updateView();
    protected void updateModel() {
        beanAdapter.setBean(getModel());
    }

    protected void updateView() {
        beanAdapter.setBean(getModel());

    }

    /**
     * Returns a suffix for this editor's title.
     * 
     * @return a suffix for this editor's title
     */
    abstract protected String getTitleSuffix();

    // Implementing the Viewer Interface ************************************

    /**
     * Answers this <code>Editor</code>'s<code>Icon</code>.
     */
    public Icon getIcon() {
        return icon;
    }

    /**
     * Answers this <code>Editor</code>'s title.
     */
    public String getTitle() {
        return titlePrefix + ' ' + getTitleSuffix();
    }

    /**
     * Answers this <code>Editor</code>'s<code>JToolBar</code>. The
     * default implementation specifies that no tool bar is used.
     */
    public JToolBar getToolBar() {
        return null;
    }

    /**
     * Activates this viewer.
     */
    public void activate() {
        // Do nothing by default; subclasses may override.
    }

    /**
     * Deactivates this viewer.
     */
    public void deactivate() {
        updateModel();
    }

    /**
     * Returns this editor's underlying model.
     */
    public Object getModel() {
        return model;
    }

    /**
     * Sets a new model. Does nothing if the old and new model are the same. If
     * the model changes, invokes <code>#updateView</code>.
     * 
     * @param newModel
     *            the model to set
     */
    public void setModel(Object newModel) {
        Object oldModel = getModel();
        if (oldModel == newModel) {
            return;
        }
        model = newModel;
        updateView();
    }

}