/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tigger;

/**
 *
 * @author pooh
 */
public class MafIoCtlAdapterJNI {
  static {
      System.loadLibrary("MafIoCtlAdapter"); // Load native library at runtime
   }
 
   // Declare a native method sayHello() that receives nothing and returns void
   private native int ioctl( int cmd, int arg);
 
   // Test Driver
   public static int SetBufferSize(int size) {
      MafIoCtlAdapterJNI jni = new MafIoCtlAdapterJNI();
      int result = jni.ioctl( 0x8F01, size);  // invoke the native method
      
      System.out.printf("Set Size = %d ret val = %d\n", size, result);
      
      return result;
   }  
}
