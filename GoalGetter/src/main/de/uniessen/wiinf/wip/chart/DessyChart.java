/*
 * Created on 23.05.2004
 * 
 * TODO To change the template for this generated file go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
package de.uniessen.wiinf.wip.chart;

import javax.swing.JTree;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.CategoryDataset;
import org.jfree.data.DefaultCategoryDataset;
/**
 * @author Jonas Sprenger
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class DessyChart extends ChartFactory {
	ChartPanel cp;
	public DessyChart() {
		
		init();
	}
	private void init() {
		DefaultCategoryDataset category = new DefaultCategoryDataset();
		category.addValue(500.0, "Peter", "mai");
		category.setValue(200.0, "Peter", "april");
		category.setValue(700.0, "Peter", "juni");
		category.setValue(100.0, "Peter", "juli");
		
		category.addValue(400.0, "Holger", "mai");
		category.addValue(100.5, "Holger", "april");
		category.addValue(200, "Holger", "juni");
		category.addValue(400, "Holger", "juli");
		
		JFreeChart jfc = createAreaChart("Sensibilitätsanalyse", "Verfügbarkeit", "Kosten ",
				(CategoryDataset) category, PlotOrientation.VERTICAL, true,
				false, false);
				
		cp = new ChartPanel(jfc);
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
}