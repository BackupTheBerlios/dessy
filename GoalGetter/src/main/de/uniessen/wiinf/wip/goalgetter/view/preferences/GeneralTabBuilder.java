/*
 * GeneralTabBuilder.java
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
 * $Id: GeneralTabBuilder.java,v 1.8 2004/09/25 14:56:57 moleman Exp $
 */

package de.uniessen.wiinf.wip.goalgetter.view.preferences;

import javax.swing.JCheckBox;
import javax.swing.JComponent;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.ToggleButtonAdapter;
import com.jgoodies.binding.beans.PropertyAdapter;
import com.jgoodies.binding.value.BufferedValueModel;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.uif.util.ResourceUtils;
import com.jgoodies.uifextras.convenience.TipOfTheDayDialog;

import de.uniessen.wiinf.wip.goalgetter.model.PresentationSettings;

/**
 * Builds the <em>General</em> tab in the preferences dialog.
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.8 $
 *  
 */
public final class GeneralTabBuilder {

	private final PresentationModel settingsModel;

	private JCheckBox showTipsBox;

	private JCheckBox autoExpandBox;

	private JCheckBox autoCollapseBox;

	// Instance Creation ****************************************************

	/**
	 * Constructs the <i>General </i> panel for the preferences dialog.
	 * 
	 * @param settingsModel
	 *            holds the presentation settings and vends models for the bound
	 *            bean properties of the settings object
	 */
	public GeneralTabBuilder(PresentationModel settingsModel) {
		this.settingsModel = settingsModel;
	}

	// Component Creation and Initialization ********************************

	/**
	 * Creates and configures the UI components. Creates three check boxes that
	 * are bound to properties of the <code>TipOfTheDayDialog</code> and the
	 * given <code>PresentationSettings</code>. All models are buffered and
	 * change the underlying model properties only if the
	 * <code>triggerChannel</code> indicates that values shall be commited.
	 * <p>
	 * 
	 * The adapted bean properties will not be observed; therefore we don't need
	 * to invoke <code>PropertyAdapter.release</code>
	 * 
	 * @see PropertyAdapter
	 * @see BufferedValueModel
	 * @see ToggleButtonAdapter
	 */
	private void initComponents() {

		showTipsBox = BasicComponentFactory.createCheckBox(
				new BufferedValueModel(TipOfTheDayDialog.showingTipsModel(),
						settingsModel.getTriggerChannel()), ResourceUtils
						.getString("generalTabBuilder.showtipofday.text"));//$NON-NLS-1$

		autoExpandBox = BasicComponentFactory
				.createCheckBox(
						settingsModel
								.getBufferedModel(PresentationSettings.PROPERTYNAME_EXPAND_SELECTED_PATHS),
						ResourceUtils
								.getString("generalTabBuilder.autoexpand.text"));//$NON-NLS-1$

		autoCollapseBox = BasicComponentFactory
				.createCheckBox(
						settingsModel
								.getBufferedModel(PresentationSettings.PROPERTYNAME_COLLAPSE_DESELECTED_PATHS),
						ResourceUtils
								.getString("generalTabBuilder.autocollapse.text"));//$NON-NLS-1$

	}

	// Building *************************************************************

	/**
	 * Builds and returns the general panel using the previously created
	 * components. Uses a FormLayout to lay out the panel, and a PanelBuilder to
	 * add the UI components.
	 * 
	 * @return the general tab
	 */
	JComponent build() {
	    initComponents();
		FormLayout layout = new FormLayout("7dlu, left:pref, 0:grow",//$NON-NLS-1$
				"pref, 2dlu, pref, 14dlu, " + "pref, 2dlu, pref, 2dlu, pref");//$NON-NLS-1$ //$NON-NLS-2$

		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();
		CellConstraints cc = new CellConstraints();

		builder.addSeparator(ResourceUtils
				.getString("generalTabBuilder.onStartup.text"), cc.xyw( //$NON-NLS-1$
				1, 1, 3));
		builder.add(showTipsBox, cc.xy(2, 3));

		builder.addSeparator(ResourceUtils
				.getString("generalTabBuilder.navigationTree.text"), cc.xyw(1, //$NON-NLS-1$
				5, 3));
		builder.add(autoExpandBox, cc.xy(2, 7));
		builder.add(autoCollapseBox, cc.xy(2, 9));

		return builder.getPanel();
	}

}