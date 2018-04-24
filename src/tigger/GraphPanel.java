/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tigger;


import java.awt.*;
import java.awt.geom.*;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


@SuppressWarnings("serial")

/**
 *
 * @author pooh
 */
public class GraphPanel extends ChartPanel {
    
    
    public GraphPanel(DefaultCategoryDataset dataset){

        super( ChartFactory.createLineChart(
         "Pooh Title",
         "Years","Number of Schools",
         dataset,
         PlotOrientation.VERTICAL,
         true,true,false));
         
      
      this.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      

    }
    
    
    
    
    
    
    
    
    
    
    
}
