/*
 * WelcomePanel.java
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
 * $Id: WelcomePanel.java,v 1.5 2004/08/07 09:28:03 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.view.editor;

import java.awt.Component;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif.action.ActionManager;
import com.jgoodies.uif.util.ResourceUtils;
import com.jgoodies.uifextras.panel.GradientBackgroundPanel;
import com.jgoodies.uifextras.util.ActionLabel;

import de.uniessen.wiinf.wip.goalgetter.tool.Actions;
import de.uniessen.wiinf.wip.goalgetter.tool.Resources;

/**
 * 
 * This panel is displayed after a successful application startup to welcome the
 * user. It provides access to actions that are most useful at the application
 * start.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.5 $
 *  
 */
public final class WelcomePanel extends GradientBackgroundPanel implements
        Editor {

    /**
     * Constructs a <code>WelcomePanel</code>.
     */
    public WelcomePanel() {
        super(false);
        add(buildForeground());
    }

    // Implementing the Editor Interface ************************************

    public Icon getIcon() {
        return null;
    }

    public String getTitle() {
        return ResourceUtils.getString("welcomePanel.welcome.text");
    }

    public JToolBar getToolBar() {
        return null;
    }

    public void activate() {
        // Nothing to do in this welcome panel.
    }

    public void deactivate() {
        // Nothing to do in this welcome panel.
    }

    public Class getDomainClass() {
        return null;
    }

    public Object getModel() {
        return null;
    }

    public void setModel(Object m) {
        // Nothing to do in this welcome panel.
    }

    // Building *************************************************************

    /**
     * Builds and answers the foreground.
     * 
     * @return foreground Component
     */
    private JComponent buildForeground() {
        JLabel welcomeLbl = new JLabel(ResourceUtils
                .getString("welcomePanel.welcomeTo.text"));
        JLabel logoLbl = new JLabel(ResourceUtils
                .getIcon(Resources.LOGO_ICON_ID));
        JLabel selectLbl = new JLabel(ResourceUtils
                .getString("welcomePanel.selectOptionsBelow.text"));
        Component newProjectButton = createActionComponent(ActionManager
                .get(Actions.NEW_PROJECT_ID));
        Component openProjectButton = createActionComponent(ActionManager
                .get(Actions.OPEN_PROJECT_ID));

        FormLayout layout = new FormLayout("9dlu, left:pref:grow",
                "b:pref, c:pref, t:pref, 9dlu, pref, 6dlu, pref");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.getPanel().setOpaque(false);
        builder.setBorder(Borders.DLU14_BORDER);

        CellConstraints cc = new CellConstraints();

        builder.add(welcomeLbl, cc.xyw(1, 1, 2));
        builder.add(logoLbl, cc.xyw(1, 2, 2, "left, center"));
        builder.add(selectLbl, cc.xyw(1, 3, 2));
        builder.add(newProjectButton, cc.xy(2, 5));
        builder.add(openProjectButton, cc.xy(2, 7));

        return builder.getPanel();
    }

    /**
     * Creates and answers an action component for the given action
     * 
     * @param a
     *            Action to create the Component for
     * @return Action Component
     */
    private JComponent createActionComponent(Action a) {
        ActionLabel label = new ActionLabel(a);
        label.setIcon(ResourceUtils.getIcon(Resources.ARROW_ICON_ID));
        return label;
    }

}