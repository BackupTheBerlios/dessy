/*
 * SensitivityAnalysisChart.java
 * Package: de.uniessen.wiinf.wip.goalgetter.view.chart
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
 * $Id: SensitivityAnalysisChart.java,v 1.1 2004/08/07 09:28:04 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.DefaultCategoryDataset;

/**
 * SensitivityAnalysisChart
 * 
 * @author tfranz
 * @author jsprenger
 * 
 * @version $Revision: 1.1 $
 *  
 */
public class SensitivityAnalysisChart extends ChartFactory {
    ChartPanel cp;

    public SensitivityAnalysisChart() {

        init();
    }

    private void init() {
        DefaultCategoryDataset category = new DefaultCategoryDataset();
        category.addValue(500.0, "Peter", "mai"); //$NON-NLS-1$  //$NON-NLS-2$ 
        category.setValue(200.0, "Peter", "april"); //$NON-NLS-1$  //$NON-NLS-2$ 
        category.setValue(700.0, "Peter", "juni"); //$NON-NLS-1$  //$NON-NLS-2$ 
        category.setValue(100.0, "Peter", "juli"); //$NON-NLS-1$  //$NON-NLS-2$ 

        category.addValue(400.0, "Holger", "mai");//$NON-NLS-1$  //$NON-NLS-2$ 
        category.addValue(100.5, "Holger", "april");//$NON-NLS-1$  //$NON-NLS-2$ 
        category.addValue(200, "Holger", "juni");//$NON-NLS-1$  //$NON-NLS-2$ 
        category.addValue(400, "Holger", "juli");//$NON-NLS-1$  //$NON-NLS-2$ 

        JFreeChart jfc = createAreaChart("Sensibilitätsanalyse",//$NON-NLS-1$  
                "Verfügbarkeit", "Kosten ", category, PlotOrientation.VERTICAL,//$NON-NLS-1$  //$NON-NLS-2$ 
                true, false, false);
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