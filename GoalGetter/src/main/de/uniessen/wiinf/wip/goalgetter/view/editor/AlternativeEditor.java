/*
 * AlternativeEditor.java
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
 * $Id: AlternativeEditor.java,v 1.4 2004/07/18 21:25:28 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.editor;

import java.awt.Component;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.uif.util.ResourceUtils;

import de.uniessen.wiinf.wip.goalgetter.domain.Alternative;
import de.uniessen.wiinf.wip.goalgetter.tool.Resources;

/**
 * An implementation of {@link Editor}that displays instances of class
 * {@link Alternative}.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.4 $
 *  
 */
public final class AlternativeEditor extends AbstractEditor {

    // Instance Creation ****************************************************

    private JTextField identifierField;

    private JEditorPane descriptionArea;

    private JPanel intensitiesPanel;

    /**
     * Constructs a <code>AlternativeEditor</code>.
     */
    public AlternativeEditor() {
        super(Resources.ALTERNATIVE_ICON);
    }

    // Implementing Abstract Superclass Behavior ****************************

    private Alternative getAlternative() {
        return (Alternative) getModel();
    }

    protected String getTitleSuffix() {
        return getAlternative().getIdentifier();
    }

    public Class getDomainClass() {
        return Alternative.class;
    }

    // Storing and Restoring Models *****************************************

    /**
     * Writes the editor contents to the underlying model.
     */
    protected void updateModel() {
        Alternative alternative = getAlternative();

        alternative.setIdentifier(identifierField.getText());
        alternative.setDescription(descriptionArea.getText());

        Component[] components = intensitiesPanel.getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i].getClass() == JTextField.class && components[i].getName()!=null) {
                alternative.putIntensity(components[i].getName(),
                        ((JTextField) components[i]).getText());
            }
        }

    }

    /**
     * Reads the editor contents from the underlying model.
     */
    protected void updateView() {
        Alternative alternative = getAlternative();
        identifierField.setText(alternative.getIdentifier());
        descriptionArea.setText(alternative.getDescription());

        intensitiesPanel.removeAll();

        FormLayout layout = new FormLayout("right:max(40dlu;p), 4dlu, 0:grow:0.7, 4dlu, 0:grow:0.3");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout,ResourceUtils.getBundle(),
                intensitiesPanel);
        builder.setBorder(Borders.EMPTY_BORDER);
        // CellConstraints cc = new CellConstraints();
        
        builder.append("", new JLabel(getAlternative().getIdentifier()),new JLabel("Sollzustand"));
        builder.nextLine();

        Map intensities = getAlternative().getIntensities();
        Iterator iterator = intensities.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            JTextField textfield = new JTextField();
            textfield.setName(key);
            textfield.setText((String) intensities.get(key));
            JTextField shouldBeTextfield = new JTextField();
           // shouldbeTextfield.setName(key);
            shouldBeTextfield.setText("1000");
            shouldBeTextfield.setEditable(false);

            builder.append(key, textfield, shouldBeTextfield);
            builder.nextLine();
        }

    }

    // Component Creation and Configuration **********************************

    /**
     * Creates and intializes the UI components.
     */
    private void initComponents() {
        identifierField = new JTextField();
        descriptionArea = new JEditorPane();
        intensitiesPanel = new JPanel();
    }

    // Building *************************************************************

    /**
     * Builds the pane. Columns are specified before components are added to the
     * form, rows are added dynamically using the {@link DefaultFormBuilder}.
     * <p>
     * 
     * The builder combines a step that is done again and again: add a label,
     * proceed to the next data column and add a component.
     */
    public void build() {

        initComponents();

        java.awt.Component descriptionPane = new JScrollPane(descriptionArea);
        FormLayout layout = new FormLayout("right:max(40dlu;p), 4dlu, 160dlu:grow");

        DefaultFormBuilder builder = new DefaultFormBuilder(layout,ResourceUtils.getBundle(), this);
        builder.setDefaultDialogBorder();

        builder.appendI15dSeparator("alternativeEditor.alternative.text");

        builder.appendI15d("alternativeEditor.identifier.text", identifierField);
        builder.appendRow(builder.getLineGapSpec());
        builder.appendRow(new RowSpec("fill:50dlu:nogrow"));
        builder.nextLine(2);
        builder.appendI15d("alternativeEditor.description.text", descriptionPane);

        builder.appendI15dSeparator("alternativeEditor.intensities.text");

        builder.append(intensitiesPanel, 3);

    }

}