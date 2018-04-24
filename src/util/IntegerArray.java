/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.List;

/**
 *
 * @author pooh
 */
public class IntegerArray {
    
    public static int[] ConvertToArray( String inStr){
    
        String[] parts = inStr.split(",|;|:");
        int[] ints = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            ints[i] = Integer.parseInt(parts[i].trim());
        }

        return ints;
    }
    
    public static String ConvertToString( int[] inData ){
        StringBuilder strBuilder = new StringBuilder();
        boolean start = true;
        for( int i : inData){
            if( !start)strBuilder.append(",");
            strBuilder.append(Integer.toString(i));
            start = false;
        }
        return strBuilder.toString();
    }
    
    public static float[] ConvertToFloatArray( int[] inData){        
        float[] outData = new float[inData.length];
        for( int i = 0; i < inData.length; i++){
            outData[i] = (float)inData[i];
        }
        
        return outData;
    }
    
    public static int[] ConvertFromFloatArray( float[] inData){
        int[] outData = new int[inData.length];
        for( int i = 0; i < inData.length; i++){
            outData[i] = (int)inData[i];
        }
        return outData;
    }
}
