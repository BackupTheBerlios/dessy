/*
 * PreferencesDialog.java
 * Package: de.uniessen.wiinf.wip.goalgetter.view.preferences
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
 * $Id: PreferencesDialog.java,v 1.8 2004/09/25 10:05:45 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.view.preferences;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.uif.AbstractDialog;
import com.jgoodies.uif.application.ResourceIDs;
import com.jgoodies.uif.util.Resizer;
import com.jgoodies.uif.util.ResourceUtils;
import com.jgoodies.uifextras.laf.LafChoiceModel;
import com.jgoodies.uifextras.laf.LafChoicePanelBuilder;
import com.jgoodies.uifextras.panel.HeaderPanel;

import de.uniessen.wiinf.wip.goalgetter.tool.PresentationSettings;

/**
 * 
 * Builds the preferences dialog.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.8 $
 *  
 */
public final class PreferencesDialog extends AbstractDialog {

	/**
	 * Refers to the model that holds the settings and that vends ValueModels
	 * that adapt the bound bean properties of the settings.
	 */
	private final PresentationModel model;

	/**
	 * Holds the panel for the look&amp;feel choice and preview.
	 */
	private LafChoicePanelBuilder lafPanel;

	// Instance Creation ******************************************************

	/**
	 * Constructs a <code>PreferencesDialog</code>.
	 * 
	 * @param owner
	 *            this dialog's parent frame
	 * @param settings
	 *            the settings to edit
	 */
	public PreferencesDialog(Frame owner, PresentationSettings settings) {
		super(owner);
		this.model = new PresentationModel(settings, getTriggerChannel());
	}

	// Building *************************************************************

	/**
	 * Builds and returns the preference's header.
	 */
	protected JComponent buildHeader() {
		return new HeaderPanel(
				ResourceUtils
						.getString("preferencesDialog.preferencesHeader.text"), //$NON-NLS-1$
				ResourceUtils
						.getString("preferencesDialog.preferencesDescription.text"), //$NON-NLS-1$
				ResourceUtils.getIcon(ResourceIDs.PREFERENCES_ICON));
	}

	/**
	 * Builds and returns the preference's content pane.
	 */
	protected JComponent buildContent() {
		JPanel content = new JPanel(new BorderLayout());
		content.add(buildTabbedPane(), BorderLayout.CENTER);
		content.add(buildButtonBarWithOKCancel(), BorderLayout.SOUTH);
		return content;
	}

	/**
	 * Builds and returns the tabbed pane.
	 * 
	 * @return tabbed Pane
	 */
	protected JTabbedPane buildTabbedPane() {
		lafPanel = new LafChoicePanelBuilder(new LafChoiceModel(
				getTriggerChannel()));

		JTabbedPane pane = new JTabbedPane();
		pane.addTab(ResourceUtils.getString("preferencesDialog.general.text"),//$NON-NLS-1$
				new GeneralTabBuilder(model).build());
		pane.addTab(ResourceUtils.getString("preferencesDialog.laf.text"),//$NON-NLS-1$
				lafPanel.build());
		return pane;
	}

	// Misc *****************************************************************

	/**
	 * Closes the window.
	 */
	protected void doCloseWindow() {
		doCancel();
	}

	/**
	 * Unlike the default try to get an aspect ratio of 1:1.
	 * 
	 * @param component
	 *            the component to resize
	 */
	protected void resizeHook(JComponent component) {
		Resizer.ONE2ONE.resizeDialogContent(component);
	}

}