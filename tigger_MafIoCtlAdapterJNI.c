/* 
 * File:   MafAdapterJNI.c
 * Author: pooh
 *
 * Created on April 23, 2018, 6:12 PM
 */

#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include<fcntl.h>
#include<sys/ioctl.h>


#include "tigger_MafIoCtlAdapterJNI.h"

JNIEXPORT jint JNICALL Java_tigger_MafIoCtlAdapterJNI_ioctl(JNIEnv *env, jobject thisObj, jint cmd, jint arg){
    
   // Open the device with read/write access
   int fd = open("/dev/MAFilter", O_RDWR);             
   if (fd >= 0){
      return ioctl(fd, cmd, arg);
   }
   
    
    
   return -1;
}

