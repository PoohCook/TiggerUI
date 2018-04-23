/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tigger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pooh
 */
public class MafDeviceAdapter {
  
    private File file = new File("/dev/MAFilter");
      
    public void Write(String sendData){
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(sendData);
            fw.flush();
            fw.close();

        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
    public String Read( int size ){
        try {
            FileReader fr = new FileReader(file);
            
            char [] a = new char[size];
            int result = fr.read(a, 0, size);

            return new String(a);


        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }

}
