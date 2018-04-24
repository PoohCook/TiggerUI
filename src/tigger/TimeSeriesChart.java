/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tigger;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Second;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import util.IntegerArray;



public class TimeSeriesChart extends ApplicationFrame {

    private static final String TITLE = "Dynamic Series";
    private static final String START = "Start";
    private static final String STOP = "Stop";
    private static final float MINMAX = 1000;
    private static final int COUNT = 2 * 60;
    private static final int FAST = 100;
    private static final int SLOW = FAST * 5;
    private static final Random random = new Random();
    private Timer timer;
    private MafDeviceAdapter mafDev = new MafDeviceAdapter();
    
    private float[] series1;
    private float[] series2;
    
    private int outputIndx;
    
    private void fillSeries(){
       series1 = gaussianData();
       series2 = filterData(series1); 
       
       outputIndx = 0;
    }
        
    float[] getNewData(){
        if(outputIndx >= COUNT) fillSeries();
        float[] newData = new float[2];
        newData[0] = series1[outputIndx];
        newData[1] = series2[outputIndx];  
        outputIndx++;
        return newData;
    }

    public TimeSeriesChart(final String title) {
        super(title);
        final DynamicTimeSeriesCollection dataset =
            new DynamicTimeSeriesCollection(2, COUNT, new Second());
        dataset.setTimeBase(new Second(0, 0, 0, 1, 1, 2018));
        
        fillSeries();
        
        dataset.addSeries(series1, 0, "Gaussian data");
        dataset.addSeries(series2, 1, "Gaussian data2");
        JFreeChart chart = createChart(dataset);

        final JButton run = new JButton(STOP);
        run.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (STOP.equals(cmd)) {
                    timer.stop();
                    run.setText(START);
                } else {
                    timer.start();
                    run.setText(STOP);
                }
            }
        });

        final JComboBox combo = new JComboBox();
        combo.addItem("Fast");
        combo.addItem("Slow");
        combo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if ("Fast".equals(combo.getSelectedItem())) {
                    timer.setDelay(FAST);
                } else {
                    timer.setDelay(SLOW);
                }
            }
        });

        this.add(new ChartPanel(chart), BorderLayout.CENTER);
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(run);
        btnPanel.add(combo);
        this.add(btnPanel, BorderLayout.SOUTH);
        
        fillSeries();

        timer = new Timer(FAST, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                float[] newData = getNewData();
                dataset.advanceTime();
                dataset.appendData(newData);
            }
        });
    }
    
    private float[] filterData( float[] series ){
        int[] intSeries = IntegerArray.ConvertFromFloatArray(series);
        mafDev.Write(IntegerArray.ConvertToString(intSeries));
        intSeries = IntegerArray.ConvertToArray(mafDev.Read(8000));
        return IntegerArray.ConvertToFloatArray(intSeries);        
    }

    private float randomValue() {
        return (float) (random.nextInt( (int)(MINMAX*9)/10 ));
    }

    private float[] gaussianData() {
        float[] a = new float[COUNT];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomValue();
        }
        return a;
    }

    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
            TITLE, "hh:mm:ss", "milliVolts", dataset, true, true, false);
        final XYPlot plot = result.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        domain.setAutoRange(true);
        ValueAxis range = plot.getRangeAxis();
        range.setRange(-(MINMAX/10), MINMAX);
        return result;
    }

    public void refreshData(){
        fillSeries();
    }
    
    public void start() {
        timer.start();
    }
    
    public void launchMe(){
       this.pack();
       RefineryUtilities.centerFrameOnScreen(this);
       this.setVisible(true);
       this.start(); 
    
    }

    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                TimeSeriesChart demo = new TimeSeriesChart(TITLE);
                demo.launchMe();
            }
        });
    }
}

