/*
 * DescriptionEditor.java
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
 * $Id: DescriptionEditor.java,v 1.1 2004/07/03 20:17:08 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.view.editor;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import de.uniessen.wiinf.wip.goalgetter.domain.Description;
import de.uniessen.wiinf.wip.goalgetter.tool.Resources;

/**
 * 
 * DescriptionEditor
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public final class DescriptionEditor extends AbstractEditor {

    private JTextComponent descriptionArea;

    private JTextComponent identifierField;

    public DescriptionEditor() {
        super(Resources.DESCRIPTION_ICON, "General Decision Data");
    }

    public void build() {
        initComponents();

        java.awt.Component descriptionPane = new JScrollPane(descriptionArea);
        FormLayout layout = new FormLayout("right:max(40dlu;p), 4dlu, 160dlu");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout, this);
        builder.setDefaultDialogBorder();
        // CellConstraints cc = new CellConstraints();

        builder.append("Identifier", identifierField);
        builder.appendSeparator("Description");
        builder.appendRow(builder.getLineGapSpec());
        builder.appendRow(new RowSpec("fill:50dlu:nogrow"));
        builder.nextLine(2);
        builder.append("", descriptionPane);
    }

    private Description getDescription() {
        return (Description) getModel();
    }

    public Class getDomainClass() {
        return de.uniessen.wiinf.wip.goalgetter.domain.Description.class;
    }

    public String getTitle() {
        return titlePrefix;
    }

    protected String getTitleSuffix() {
        return getDescription().getIdentifier();
    }

    private void initComponents() {
        identifierField = new JTextField();
        descriptionArea = new JEditorPane();

    }

    protected void updateModel() {
        Description description = getDescription();
        description.setIdentifier(identifierField.getText());
        description.setDescription(descriptionArea.getText());

    }

    protected void updateView() {
        Description description = getDescription();
        identifierField.setText(description.getIdentifier());
        descriptionArea.setText(description.getDescription());
    }

}