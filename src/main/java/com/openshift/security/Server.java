package com.openshift.security;

import java.security.KeyPair;




public class Server {
	private KeyPair pair;
	
	public Server(){
	//	this.pair = CPABEImplWithoutSerialize.setup();
	}
	
	public String getPublicKeyInString(){
	//	return pair.getPKJSONString();
		return "stringvalue";
	}
	
	public String generateSecretKey(String[] attrs){
		//return CPABEImplWithoutSerialize.keygen(attrs, pair.getPK(), pair.getMK());
		
		return "PKstringvalue";
	}
}
