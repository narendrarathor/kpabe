package com.openshift.security;

import java.io.File;

public class KP_ABE {
	public void kp_abe()
	{
		Server server = new Server();
		Client PKUClient = new Client(new String[]{"PKU", "USer"});
		Client THUClient = new Client(new String[]{"THU", "USer"});
		Client TeacherClient = new Client(new String[]{"PKU", "USer"});
		//client��server����ȡ��Կ�ַ���
		String PKJSONString = server.getPublicKeyInString();
		 

		//client���Լ���������Ϣ���͸�server,����ȡ˽Կ�ַ���
		String SKJSONString = server.generateSecretKey(PKUClient.getAttrs());
		 
		
		SKJSONString = server.generateSecretKey(THUClient.getAttrs());
		
		
		SKJSONString = server.generateSecretKey(TeacherClient.getAttrs());
		
		
		//����
		String outputFileName = "test.cpabe";
		File in = new File("README.md");
		String policy = "Student OR Teacher";
		
		
		//����
		in = new File(outputFileName);
//		THUClient.dec(in);
		TeacherClient.dec(in);
		
		
		
	
	}
	public void encrption( byte[] bs) {
		int primenumber=67;
		boolean flag;
		Server server = new Server();
		Client PKUClient = new Client(new String[]{"PKU", "USer"});
		Client THUClient = new Client(new String[]{"THU", "USer"});
		Client TeacherClient = new Client(new String[]{"PKU", "USer"});
		//client��server����ȡ��Կ�ַ���
		String PKJSONString = server.getPublicKeyInString();
		 

		//client���Լ���������Ϣ���͸�server,����ȡ˽Կ�ַ���
		String SKJSONString = server.generateSecretKey(PKUClient.getAttrs());
		 
		
		SKJSONString = server.generateSecretKey(THUClient.getAttrs());
		
		
		SKJSONString = server.generateSecretKey(TeacherClient.getAttrs());
		
		for (int i = 0; i < bs.length; i++) {	
			int hashcode=bs.hashCode();
			if(hashcode==bs[i] &&  i>primenumber) {
				flag=true;
			}
			
		}
		
		
		//����
		String outputFileName = "test.cpabe";
		File in = new File("README.md");
		String policy = "Student OR Teacher";
		
		
		//����
		in = new File(outputFileName);
//		THUClient.dec(in);
		//TeacherClient.dec(in);
	}
}

