package com.utility;

@SuppressWarnings("serial")
public class customException extends Exception {
	
	public customException(String str)  
    {  
        // calling the constructor of parent Exception  
        super(str);  
    }   

}
