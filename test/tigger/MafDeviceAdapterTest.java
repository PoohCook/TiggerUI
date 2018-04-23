/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tigger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pooh
 */
public class MafDeviceAdapterTest {
    
    public MafDeviceAdapterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        MafIoCtlAdapterJNI jni = new MafIoCtlAdapterJNI();
            int result = jni.SetBufferSize(5);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of Write method, of class MafDeviceAdapter.
     */
    @Test
    public void testBasicDataSample() {
        System.out.println("Testing the data supplied by design ");
        String sendData = "10, 10, 50, 100, 100, 200, 100, 1";
        MafDeviceAdapter instance = new MafDeviceAdapter();
        instance.Write(sendData);
        String result = instance.Read(100);
        
        String expected = "10, 10, 23, 43, 54, 92, 110, 100";
        assertEquals("Returned data did not match expected results", expected, result.trim());
        
        
    }

    
    @Test
    public void testBasicExtendedDataSample() {
        System.out.println("Testing the data supplied by design ");
        String sendData = "10, 10, 50, 100, 100, 200, 100, 50, 0, 0, 50, 100, 200, 100, 100, 50, 10, 10";
        MafDeviceAdapter instance = new MafDeviceAdapter();
        instance.Write(sendData);
        String result = instance.Read(100);
        
        String expected = "10, 10, 23, 43, 54, 92, 110, 110, 90, 70, 40, 40, 70, 90, 110, 110, 92, 54";
        assertEquals("Returned data did not match expected results", expected, result.trim());
        
        
    }
    
    
}
