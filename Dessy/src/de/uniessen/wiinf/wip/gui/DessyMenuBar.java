/*
 * Created on 22.05.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.uniessen.wiinf.wip.gui;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import de.uniessen.wiinf.wip.gui.control.MenuItemListener;
import de.uniessen.wiinf.wip.internationalisation.Internationalization;

/**
 * @author Jonas Sprenger
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DessyMenuBar extends JMenuBar {
	
	public DessyMenuBar(){
		init();
	}
	
	private void init(){
//		 menu Bar
	
		ActionListener itemListener = new MenuItemListener();
		JMenu menuDatei = new JMenu((Internationalization.getMessage("datei")));
		JMenuItem item = new JMenuItem(Internationalization
				.getMessage("öffnen"));
		item.addActionListener(itemListener);
		menuDatei.add(item);
		item = new JMenuItem(Internationalization
				.getMessage("speichern"));
		item.addActionListener(itemListener);
		menuDatei.add(item);
		item = new JMenuItem(Internationalization.getMessage("speichern_unter"));
		item.addActionListener(itemListener);
		menuDatei.add(item);
		item = new JMenuItem(Internationalization.getMessage("drucken"));
		item.addActionListener(itemListener);
		menuDatei.add(item);
		item = new JMenuItem(Internationalization.getMessage("beenden"));
		item.addActionListener(itemListener);
		item.addActionListener(itemListener);
		menuDatei.add(item);
		JMenu menuView = new JMenu((Internationalization.getMessage("ansicht")));
		item = new JMenuItem(Internationalization.getMessage("Sensibilitätsanalyse"));
		item.addActionListener(itemListener);
		item.addActionListener(itemListener);
		menuView.add(item);
		
		JMenu menuHilfe = new JMenu("?");
		item = new JMenuItem(Internationalization.getMessage("hilfe"));
		menuHilfe.add(item);
		this.add(menuDatei);
		this.add(menuView);
		this.add(menuHilfe);
	}
}
