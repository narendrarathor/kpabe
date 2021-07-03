package com.openshift.security;

 

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.crypto.Cipher;

 

public class Client {
	 
	private String[] attrs;
	
	public Client(){}
	
	public Client(String[] attrs){
		this.attrs = attrs;
	}
	
	public String[] getAttrs(){
		return attrs;
	}
	
	public void setAttrs(String[] attrs){
		this.attrs = attrs;
	}
	
	 
	
	public void dec(File in){
		String ciphertextFileName = null; 
		DataInputStream dis = null;
		try {
			ciphertextFileName = in.getCanonicalPath();
			dis = new DataInputStream(new FileInputStream(in));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
