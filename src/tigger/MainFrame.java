/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tigger;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.DynamicTimeSeriesCollection;

public class MainFrame extends JFrame {
      
   
    JFrame frame;
    JPanel buttonPanel, fieldsPanel;
    JLabel filterSizeLbl, inputLbl, outputLbl;
    JTextField filterSizeFld, inputFld, outputFld;
    JButton setFilterSizeBtn, sendBtn, closeBtn, graphBtn;
    
    MafDeviceAdapter mafDev;
    
    TimeSeriesChart graphPanel = null;

    MainFrame() {
        
        this.setSize(500, 500);
         
        buttonPanel = new JPanel();
        fieldsPanel = new JPanel();
        filterSizeLbl = new JLabel("Filter Size");
        inputLbl = new JLabel("Input");
        outputLbl = new JLabel("Outpt");
        filterSizeFld = new JTextField("");
        inputFld = new JTextField("");
        outputFld = new JTextField("");
        setFilterSizeBtn = new JButton("Set Filter Size");
        sendBtn = new JButton("Send");
        closeBtn = new JButton("Close");
        graphBtn = new JButton("Graph");
        
        setFilterSizeBtn.addActionListener(new OpenBtnListener(this));
        sendBtn.addActionListener(new SendBtnListener(this));
        closeBtn.addActionListener(new CloseBtnListener(this));
        graphBtn.addActionListener(new GraphBtnListener(this));
        
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.setLayout(new FlowLayout());

        filterSizeFld.setText("5");
        inputFld.setText("200, 100, 100, 50, 10,10,50,100,200, 100, 100, 50, 17, 18, 19, 20");
        
        fieldsPanel.add(filterSizeLbl);
        fieldsPanel.add(filterSizeFld);
        fieldsPanel.add(inputLbl);
        fieldsPanel.add(inputFld);
        fieldsPanel.add(outputLbl);
        fieldsPanel.add(outputFld);
         
        buttonPanel.add(setFilterSizeBtn);
        buttonPanel.add(sendBtn);
        buttonPanel.add(closeBtn);
        buttonPanel.add(graphBtn);
        
        this.add(fieldsPanel, BorderLayout.PAGE_START);
        this.add(buttonPanel, BorderLayout.PAGE_END);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.pack();
        this.setVisible(true);
        
        mafDev = new MafDeviceAdapter();
        
    }
    
    class GraphBtnListener implements ActionListener{
        
        MainFrame parent;
        
        public GraphBtnListener (MainFrame parent){
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            graphPanel = new TimeSeriesChart("Moving Average Filter Demo");
            graphPanel.launchMe();
            
        }
        
    }
     
    class OpenBtnListener implements ActionListener{
        
        MainFrame parent;
        
        public OpenBtnListener (MainFrame parent){
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            MafIoCtlAdapterJNI jni = new MafIoCtlAdapterJNI();
            int result = jni.SetBufferSize(Integer.parseInt(parent.filterSizeFld.getText()));
            
            if( graphPanel != null) graphPanel.refreshData();
            
        }
        
    }
    
   class SendBtnListener implements ActionListener{
        
        MainFrame parent;
        
        public SendBtnListener (MainFrame parent){
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            parent.mafDev.Write(parent.inputFld.getText());
            
            parent.outputFld.setText(parent.mafDev.Read(200));
            
        }
        
    }
   
   class CloseBtnListener implements ActionListener{
        
        MainFrame parent;
        
        public CloseBtnListener (MainFrame parent){
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            parent.dispatchEvent(new WindowEvent(parent, WindowEvent.WINDOW_CLOSING));
        }
        
    }
   
    
}
