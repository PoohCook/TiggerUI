/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tigger;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.geom.*;

@SuppressWarnings("serial")

/**
 *
 * @author pooh
 */
public class GraphPanel extends JFrame {
    
    
    public GraphPanel(){

        this.setSize(500, 500);

        this.setTitle("Drawing Shapes");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(new DrawStuff(), BorderLayout.CENTER);

        this.setVisible(true);

    }
    
    
    private class DrawStuff extends JComponent{
		
        // Graphics is the base class that allows for drawing on components
        public void paint(Graphics g){

                // Extends graphics so you can draw dimensional shapes and images
               Graphics2D graph2 = (Graphics2D)g;

                // Sets preferences for rendering
                // KEY_ANTIALIASING reduces artifacts on shapes
                // VALUE_ANTIALIAS_ON will clean up the edges
               graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // The Shape interface knows how to draw many different shapes
                /* Arc2D, Arc2D.Double, Arc2D.Float, Area, BasicTextUI.BasicCaret, 
                 * CubicCurve2D, CubicCurve2D.Double, CubicCurve2D.Float, DefaultCaret, 
                 * Ellipse2D, Ellipse2D.Double, Ellipse2D.Float, GeneralPath, Line2D, 
                 * Line2D.Double, Line2D.Float, Path2D, Path2D.Double, Path2D.Float, 
                 * Polygon, QuadCurve2D, QuadCurve2D.Double, QuadCurve2D.Float, 
                 * Rectangle, Rectangle2D, Rectangle2D.Double, Rectangle2D.Float, 
                 * RectangularShape, RoundRectangle2D, RoundRectangle2D.Double, 
                 * RoundRectangle2D.Float
                 */

                // A line that goes from x1, y1 to x2, y2
                Shape line = new Line2D.Float(20, 90, 55, 250);
                
                graph2.setPaint(Color.BLACK);
			
		// Draws a shape based on the preferences that have been set
		graph2.draw(line);

        }
    }
    
    
    
}
