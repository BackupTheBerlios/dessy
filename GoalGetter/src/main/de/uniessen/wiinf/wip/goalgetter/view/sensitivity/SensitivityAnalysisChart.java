/*
 * SensitivityAnalysisChart.java Package:
 * de.uniessen.wiinf.wip.goalgetter.view.sensitivity Project: GoalGetter
 * 
 * GoalGetter is based on a decision supporting method developed by Markus
 * Stallkamp (markus.stallkamp@uni-essen.de)
 * 
 * (c) 2004 Jonas Sprenger (jonas.sprenger@gmx.de), Tim Franz
 * (tim.franz@uni-essen.de)
 * 
 * ---
 * 
 * This project uses the JGoodies Framework and its Skeleton Pro Application
 * Copyright (c) 2002-2004 JGoodies Karsten Lentzsch. All Rights Reserved. See
 * Readme file for detailed license
 * 
 * $Id: SensitivityAnalysisChart.java,v 1.7 2004/09/25 10:05:45 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.sensitivity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 * SensitivityAnalysisChart
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.7 $
 *  
 */
public class SensitivityAnalysisChart extends ChartFactory {
	ChartPanel cp;
	List elements;
	private String nameX;
	private String nameY;
	String status = "2D";
	JFreeChart jfc;
	DefaultCategoryDataset category;
	CategoryPlot plot;
	boolean chartchanged = false;
	protected static final Color OUTER_SHADOW_COLOR = new Color(0, 0, 0, 25);
	protected static final Color INNER_SHADOW_COLOR = new Color(0, 0, 0, 42);
	private boolean gitterFlag;
	
	/**
	 * Constructs a SensityvityAnalysisChart
	 */
	public SensitivityAnalysisChart(List elements, String nameX, String nameY) {
		this.elements = elements;
		this.nameX = nameX;
		this.nameY = nameY;
		this.chartchanged = false;
		this.gitterFlag = true;
		init();
	}
	 void init() {
		category = new DefaultCategoryDataset();
		Iterator it = elements.iterator();
		SensitivityElements se;
		while (it.hasNext()) {
			se = (SensitivityElements) it.next();
			Map values = se.getValues();
			Set keys = values.keySet();
			Iterator i = keys.iterator();
			while (i.hasNext()) {
				Object tmp = i.next();
				int value = Integer.parseInt(values.get(tmp).toString());
				category.addValue(value, tmp.toString(), se.getName());
			}
		}
		jfc = createStackedBarChart(null, nameX, nameY, category,
				PlotOrientation.VERTICAL, //$NON-NLS-1$  //$NON-NLS-2$ 
				true, false, false);
		boolean gitterflag = true;
		
		plot = jfc.getCategoryPlot();
		plot.getDomainAxis().setAxisLineVisible(true);
	    plot.getRangeAxis().setAxisLineVisible(true);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(true);
        jfc.setBorderVisible(true);
	    

	    
//       	plot.setBackgroundAlpha(0f);
//        plot.getDomainAxis().setLabelFont(UIManager.getFont("Table.font"));
//        plot.getRangeAxis().setLabelFont(UIManager.getFont("Table.font"));
//        plot.getDomainAxis().setTickLabelFont(UIManager.getFont("Table.font"));
//        plot.getRangeAxis().setTickLabelFont(UIManager.getFont("Table.font"));
//        plot.getRangeAxis().setTickLabelsVisible(true);
//        plot.getRangeAxis().setTickMarksVisible(gitterflag);
//        plot.getDomainAxis().setTickMarksVisible(gitterflag);

//        plot.getDomainAxis().setAxisLinePaint(Color.darkGray);
//        
//        plot.setDomainGridlinesVisible(true);
//               plot.setOutlinePaint(null);	    	
//		((StandardLegend) jfc.getLegend()).setItemFont(UIManager
//				.getFont("Table.font"));
//		((StandardLegend) jfc.getLegend()).setBackgroundPaint(new Color(0, 0,
//				0, 0));
//		((StandardLegend) jfc.getLegend())
//				.setOutlinePaint(new Color(0, 0, 0, 0));
//		CategoryItemRenderer renderer = plot.getRenderer();
//		renderer.setBaseItemLabelFont(UIManager.getFont("Table.font"));
//		renderer.setOutlinePaint(Color.darkGray);
//		for (int i = 0; i < category.getColumnCount(); i++) {
//			Color col = (Color) renderer.getSeriesPaint(i);
//			renderer.setSeriesPaint(i, adjustColor(col));
//		}
		cp = new ChartPanel(jfc);
//		cp.addChartMouseListener(new SensitivityChartMouseListener(this));
//		cp.addMouseListener(new SensitivityChartMouseListener(this));
//		cp.addMouseMotionListener(new SensitivityChartMouseListener(this));
	}
	private Color adjustColor(Color col) {
		float[] hsbvals = Color.RGBtoHSB(col.getRed(), col.getGreen(), col
				.getBlue(), null);
		col = Color.getHSBColor(hsbvals[0], 0.5f, 0.9f);
		return col;
	}
	/**
	 * @return Returns the cp.
	 */
	public ChartPanel getChartPanel() {
		return cp;
	}
	/**
	 * @param cp
	 *            The cp to set.
	 */
	public void setChartPanel(ChartPanel cp) {
		this.cp = cp;
	}
	/**
	 * renders a shadow. copied from the jgoodies chart library. has no use yet
	 * 
	 * @param g2
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	private void renderShadow(Graphics2D g2, int x, int y, int width, int height) {
		g2.setColor(OUTER_SHADOW_COLOR);
		g2.fillRoundRect(x + 3, y + 3, width, height, 2, 2);
		g2.setColor(INNER_SHADOW_COLOR);
		g2.fillRoundRect(x + 2, y + 2, width, height, 2, 2);
	}
	/**
	 * @param elements2
	 */
public void updateValues(List e) {
	
		elements = e;
	     Iterator it = elements.iterator();
	     category =  new DefaultCategoryDataset();;  
	     SensitivityElements se;
	        while(it.hasNext())
	        {	
	        	se =(SensitivityElements)it.next();
	        	Map values = se.getValues();
	        	Set keys = values.keySet();
	        	Iterator i = keys.iterator();
	        	
	        	while(i.hasNext()){
	        		Object tmp = i.next();
	        		int value;
	        		if(! values.get(tmp).toString().equals("")) {
	        		value = Integer.parseInt(values.get(tmp).toString());
	        		category.addValue(value,
	        				tmp.toString(),
							se.getName());  
	        	   		}
	        		
	        }
	      }
	        
	       if(status.equals("2DStackedBarChart")&& chartchanged == true)  
		    jfc = createStackedBarChart(null,
		                nameX, nameY, category, PlotOrientation.VERTICAL,//$NON-NLS-1$  //$NON-NLS-2$ 
		                true, false, false);
		   else if(status.equals("3DStackedBarChart")&& chartchanged == true)	
		   	jfc = createStackedBarChart3D(null,
		            nameX, nameY, category, PlotOrientation.VERTICAL,//$NON-NLS-1$  //$NON-NLS-2$ 
		            true, false, false);
		   
		   else if(status.equals("2DBarChart")&& chartchanged == true)
		   	jfc = createBarChart(null, 
					nameX,nameY, category, PlotOrientation.VERTICAL, 
					true, false, false); 
		   
		   else if(status.equals("3DBarChart")&& chartchanged == true)		 	
		   	jfc = createBarChart3D(null, 
				nameX,nameY, category, PlotOrientation.VERTICAL, 
				true, false, false);
		  
		 else {
		 	plot = jfc.getCategoryPlot();
	      jfc.getCategoryPlot().setDataset(category);
	      plot.setDomainGridlinesVisible(gitterFlag);
	      plot.setRangeGridlinesVisible(gitterFlag);
	      return;
		 }
	       plot = jfc.getCategoryPlot();
	       plot.setDomainGridlinesVisible(gitterFlag);
		   plot.setRangeGridlinesVisible(gitterFlag);
		   
		   chartchanged=false;
		   
//	       	plot.setBackgroundAlpha(0f);
//	        plot.getDomainAxis().setLabelFont(UIManager.getFont("Table.font"));
//	        plot.getRangeAxis().setLabelFont(UIManager.getFont("Table.font"));
//	        plot.getDomainAxis().setTickLabelFont(UIManager.getFont("Table.font"));
//	        plot.getRangeAxis().setTickLabelFont(UIManager.getFont("Table.font"));
//	        plot.getRangeAxis().setTickLabelsVisible(true);
//	        plot.getRangeAxis().setTickMarksVisible(true);
//	        plot.getDomainAxis().setTickMarksVisible(true);
//	        plot.setRangeGridlinesVisible(gitterFlag);
//	        //plot.getDomainAxis().setAxisLinePaint(Color.darkGray);
//	        plot.getDomainAxis().setAxisLineVisible(true);
//	        plot.getRangeAxis().setAxisLineVisible(true);
//	        

//	        
////	        plot.setOutlinePaint(null);	
//		 
//	        jfc.setBorderVisible(true);
//	        
//	        ((StandardLegend) jfc.getLegend()).setItemFont(UIManager
//	                .getFont("Table.font"));
//	        ((StandardLegend) jfc.getLegend()).setBackgroundPaint(new Color(0, 0,
//	                0, 0));
//	        ((StandardLegend) jfc.getLegend())
//	                .setOutlinePaint(new Color(0, 0, 0, 0));
//	        CategoryItemRenderer renderer = plot.getRenderer();
//	        renderer.setBaseItemLabelFont(UIManager.getFont("Table.font"));
//	        renderer.setOutlinePaint(Color.darkGray);
//
//	        for (int i = 0; i < category.getColumnCount(); i++) {
//	            Color col = (Color) renderer.getSeriesPaint(i);
//
//	            renderer.setSeriesPaint(i, adjustColor(col));
//	        }
	        cp.setChart(jfc);
	      
	    }	/**
			  * @return Returns the status.
			  */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status
	 *            The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @param b
	 */
	public void setGitterFlag(boolean b) {
		this.gitterFlag = b;
		
	}
	/**
	 * @return Returns the chartchanged.
	 */
	public boolean isChartchanged() {
		return chartchanged;
	}
	/**
	 * @param chartchanged The chartchanged to set.
	 */
	public void setChartchanged(boolean chartchanged) {
		this.chartchanged = chartchanged;
	}
		
}