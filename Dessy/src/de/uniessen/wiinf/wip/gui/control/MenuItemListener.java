/*
 * Created on 22.05.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.uniessen.wiinf.wip.gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.uniessen.wiinf.wip.gui.Dessy;
import de.uniessen.wiinf.wip.internationalisation.Internationalization;

/**
 * @author Tim Franz, Jonas Sprenger
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MenuItemListener implements ActionListener {
	public MenuItemListener(){
		
	}
	/* (this class handels all events fred by menuitems
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		String com = e.getActionCommand();
		if(com.equals(Internationalization.getMessage("beenden"))){
			System.exit(0);
		}
		else if(com.equals(Internationalization.getMessage("öffnen"))){
			Dessy.getMain().addProjektToGui();
		}
		
	}

}
