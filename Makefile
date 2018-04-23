
libMafIoCtlAdapter.so: tigger_MafIoCtlAdapterJNI.c
	
	gcc -fPIC -I"${JAVA_HOME}/include" -I"${JAVA_HOME}/include/linux" -shared -o libMafIoCtlAdapter.so tigger_MafIoCtlAdapterJNI.c
	
clean:
	rm *.o
