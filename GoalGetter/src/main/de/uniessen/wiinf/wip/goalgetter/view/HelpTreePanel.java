/*
 * HelpTreePanel.java
 * Package: de.uniessen.wiinf.wip.goalgetter.view
 * Project: GoalGetter
 * 
 * GoalGetter is based on a decision supporting method 
 * developed by Markus Stallkamp (markus.stallkamp@uni-essen.de)
 * 
 * (c) 2004 
 * Jonas Sprenger (jonas.sprenger@gmx.de),
 * Tim Franz (tim.franz@uni-essen.de)
 * 
 * $Id: HelpTreePanel.java,v 1.4 2004/09/25 14:56:57 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view;

import javax.swing.JToolBar;

import com.jgoodies.uif.action.ActionManager;
import com.jgoodies.uif.builder.ToolBarBuilder;
import com.jgoodies.uif.panel.SimpleInternalFrame;
import com.jgoodies.uif.util.ResourceUtils;
import com.jgoodies.uifextras.util.UIFactory;

import de.uniessen.wiinf.wip.goalgetter.model.Actions;
import de.uniessen.wiinf.wip.goalgetter.model.DynamicHelpModel;

/**
 * HelpTreePanel
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.4 $
 *  
 */
public class HelpTreePanel extends SimpleInternalFrame {

    /**
     *  
     */
    public HelpTreePanel(DynamicHelpModel helpModule) {
        super(
                ResourceUtils
                        .getString("de.uniessen.wiinf.wip.goalgetter.dynamicHelp.label")); //$NON-NLS-1$
        setToolBar(buildToolBar());
        setContent(UIFactory.createStrippedScrollPane(new HelpTreeBuilder(
                helpModule).build()));
    }

    private JToolBar buildToolBar() {
        ToolBarBuilder builder = new ToolBarBuilder("Help Contents");
        builder.add(ActionManager.get(Actions.CLOSE_HELP_NAVIGATOR_ID));
        return builder.getToolBar();
    }

}