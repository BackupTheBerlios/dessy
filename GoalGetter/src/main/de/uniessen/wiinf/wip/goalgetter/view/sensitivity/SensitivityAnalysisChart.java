/*
 * SensitivityAnalysisChart.java
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
 * $Id: SensitivityAnalysisChart.java,v 1.1 2004/08/14 11:11:12 moleman Exp $
 */
package de.uniessen.wiinf.wip.goalgetter.view.sensitivity;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.UIManager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardLegend;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.CategoryItemRenderer;
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

    /**
     * Constructs a SensityvityAnalysisChart
     */
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

        JFreeChart jfc = createStackedBarChart(null,
                "Verfügbarkeit", "Kosten ", category, PlotOrientation.VERTICAL,//$NON-NLS-1$  //$NON-NLS-2$ 
                true, false, false);

        CategoryPlot plot = jfc.getCategoryPlot();

        plot.setBackgroundAlpha(0f);

        plot.getDomainAxis().setLabelFont(UIManager.getFont("Table.font"));
        plot.getRangeAxis().setLabelFont(UIManager.getFont("Table.font"));
        plot.getDomainAxis().setTickLabelFont(UIManager.getFont("Table.font"));
        plot.getRangeAxis().setTickLabelFont(UIManager.getFont("Table.font"));
        plot.getRangeAxis().setTickLabelsVisible(true);
        plot.getRangeAxis().setTickMarksVisible(false);
        plot.getDomainAxis().setTickMarksVisible(false);
        plot.setRangeGridlinesVisible(false);
        // plot.getDomainAxis().setAxisLinePaint(Color.darkGray);
        plot.getDomainAxis().setAxisLineVisible(false);
        plot.getRangeAxis().setAxisLineVisible(false);
        plot.setDomainGridlinesVisible(false);
        plot.setOutlinePaint(null);
        jfc.setBorderVisible(false);
        ((StandardLegend) jfc.getLegend()).setItemFont(UIManager
                .getFont("Table.font"));
        ((StandardLegend) jfc.getLegend()).setBackgroundPaint(new Color(0, 0,
                0, 0));
        ((StandardLegend) jfc.getLegend())
                .setOutlinePaint(new Color(0, 0, 0, 0));
        CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setBaseItemLabelFont(UIManager.getFont("Table.font"));
        renderer.setOutlinePaint(Color.darkGray);

        for (int i = 0; i < category.getColumnCount(); i++) {
            Color col = (Color) renderer.getSeriesPaint(i);

            renderer.setSeriesPaint(i, adjustColor(col));
        }

        cp = new ChartPanel(jfc);
        cp.setPreferredSize(new Dimension(400, 300));
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

    protected static final Color OUTER_SHADOW_COLOR = new Color(0, 0, 0, 25);

    protected static final Color INNER_SHADOW_COLOR = new Color(0, 0, 0, 42);

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
}