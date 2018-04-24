/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tigger;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.awt.Mutex;

/**
 *
 * @author pooh
 */
public class MafDeviceAdapter {
  
    
    
    private File file;
    private FileWriter fw;  
    private FileReader fr;
    
    Mutex mutex = new Mutex();
    
    
    public MafDeviceAdapter() {
        
        
        mutex.lock(); 
        try {
            file = new File("/dev/MAFilter");
            fw = new FileWriter(file);
            fr = new FileReader(file); 
            
        }catch (IOException ex) {
                Logger.getLogger(MafDeviceAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            mutex.unlock();
        }


       
       
    }
    
    
     
    public void Write(String sendData){
        mutex.lock();
        try {
           
            fw.write(sendData);
            fw.flush();

        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            mutex.unlock();
        }
        
    }
       
    public String Read( int size ){
        mutex.lock();
        try {
            
            char [] a = new char[size];
            int result = fr.read(a, 0, size);

            return new String(a);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            mutex.unlock();
        }
        
        return "";
    }

}
