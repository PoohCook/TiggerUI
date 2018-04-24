/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

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
public class IntegerArrayTest {
    
    public IntegerArrayTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of ConvertToString method, of class IntegerArray.
     */
    @Test
    public void testConvertToString() {
        
        int[] data = new int[]{0,1,2,3,4,5,6,7,8,9};
        String result = IntegerArray.ConvertToString(data);
        
        String expected = "0,1,2,3,4,5,6,7,8,9";
        assertEquals("Returned data did not match expected results", expected, result.trim());
        
    }
    
    /**
     * Test of ConvertToArray method, of class IntegerArray.
     */
    @Test
    public void testConvertToArray() {
        
        
        String dataStr = "0,1,2,3,4,5,6,7,8,9";
        int[] result = IntegerArray.ConvertToArray(dataStr);
        
        
        int[] expected = new int[]{0,1,2,3,4,5,6,7,8,9};
        
        assertArrayEquals("Returned data did not match expected results", expected, result);
        
    }
    
    /**
     * Test of ConvertToArray method, of class IntegerArray.
     */
    @Test
    public void testConvertToArrayWithSemiColons() {
        
        
        String dataStr = "0;1;2;3;4;5;6;7;8;9";
        int[] result = IntegerArray.ConvertToArray(dataStr);
        
        
        int[] expected = new int[]{0,1,2,3,4,5,6,7,8,9};
        
        assertArrayEquals("Returned data did not match expected results", expected, result);
        
    }
    
    /**
     * Test of ConvertToArray method, of class IntegerArray.
     */
    @Test
    public void testConvertToArrayVariousInconsitent() {
        
        
        String dataStr = "0,1, 2; 3: 4;5;6,7,8,9";
        int[] result = IntegerArray.ConvertToArray(dataStr);
        
        
        int[] expected = new int[]{0,1,2,3,4,5,6,7,8,9};
        
        assertArrayEquals("Returned data did not match expected results", expected, result);
        
    }
    
    
    /**
     * Test of ConvertToFloatArray method, of class IntegerArray.
     */
    @Test
    public void testConvertToFloatArrayVariousInconsitent() {
        
        
        int[] inData = new int[]{0,1,2,3,4,5,6,7,8,9};
        float[] result = IntegerArray.ConvertToFloatArray(inData);
        
        
        float[] expected = new float[]{0,1,2,3,4,5,6,7,8,9};
        
        assertArrayEquals("Returned data did not match expected results", expected, result, 0);
        
    }
    
   /**
     * Test of ConvertFromFloatArray method, of class IntegerArray.
     */
    @Test
    public void testConvertFromFloatArrayVariousInconsitent() {
        
        
        float[] inData = new float[]{0,1,2,3,4,5.5f,6,7,8,9.45f};
        int[] result = IntegerArray.ConvertFromFloatArray(inData);
        
        
        int[] expected = new int[]{0,1,2,3,4,5,6,7,8,9};
        
        assertArrayEquals("Returned data did not match expected results", expected, result);
        
    }     
    
    
    
}
