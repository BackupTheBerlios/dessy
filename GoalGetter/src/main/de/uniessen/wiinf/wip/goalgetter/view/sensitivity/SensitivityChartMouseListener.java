/*
 * SensitivityChartMouseListener.java
 * Package: de.uniessen.wiinf.wip.goalgetter.view.sensitivity
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
 * $Id: SensitivityChartMouseListener.java,v 1.2 2004/10/04 21:31:12 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.sensitivity;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.ChartEntity;

/**
 * 
 * Mouse Listener class for the sensitivity chart
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.2 $
 *  
 */
public class SensitivityChartMouseListener implements ChartMouseListener,
		MouseListener, MouseMotionListener {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jfree.chart.ChartMouseListener#chartMouseClicked(org.jfree.chart.ChartMouseEvent)
	 */
	double starty = 0, startx, y = 0;

	SensitivityAnalysisChart chart;

	/**
	 * @param chart
	 */
	public SensitivityChartMouseListener(SensitivityAnalysisChart chart) {

		this.chart = chart;
	}

	public void chartMouseClicked(ChartMouseEvent arg0) {
		System.out.println(arg0);

		System.out.println(arg0.getEntity());
		ChartEntity entity = arg0.getEntity();
		JFreeChart jfc = arg0.getChart();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jfree.chart.ChartMouseListener#chartMouseMoved(org.jfree.chart.ChartMouseEvent)
	 */
	public void chartMouseMoved(ChartMouseEvent arg0) {
		//System.out.println("mousemoved");
	}

	public void mouseExited(java.awt.event.MouseEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent e) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent e) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e) {

		Point p = e.getPoint();
		starty = p.getY();
		startx = p.getX();

		System.out.println("pressed..." + y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent e) {
		Point p = e.getPoint();

		System.out.println("released..." + y);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	public void mouseDragged(MouseEvent e) {
		Point p = e.getPoint();
		y = p.getY();
		if (y < starty)

			System.out.println("dragged " + y);
		System.out.println(e.getSource());
		ChartPanel jfc = (ChartPanel) chart.getChartPanel().getComponentAt(
				(int) startx, (int) starty);
		JFreeChart chart = jfc.getChart();
		//chart.getCategoryPlot().g
		Component c = jfc.getComponentAt((int) startx, (int) starty);
		//System.out.println(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	public void mouseMoved(MouseEvent e) {

	}

}