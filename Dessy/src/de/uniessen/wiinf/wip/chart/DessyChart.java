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

import de.uniessen.wiinf.wip.internationalisation.Internationalization;
/**
 * @author Jonas Sprenger
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class DessyChart extends ChartFactory {
	JTree tree;
	ChartPanel cp;
	public DessyChart(JTree tree) {
		this.tree = tree;
		init();
	}
	private void init() {
		DefaultCategoryDataset category = new DefaultCategoryDataset();
		category.addValue(5.0, "Peter", "1");
		category.setValue(2.0, "Peter", "2");
		category.setValue(7.0, "Peter", "3");
		category.setValue(1.0, "Peter", "4");
		
		category.addValue(4.0, "Holger", "1");
		category.addValue(0.5, "Holger", "2");
		category.addValue(2, "Holger", "3");
		category.addValue(4, "Holger", "4");
		
		JFreeChart jfc = createAreaChart(Internationalization
				.getMessage("Sensibilitätsanalyse"), "Nutzen", "Kosten =???",
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