package com.openshift.security;

import java.io.File;

public class Modified_KP_ABE {
	public void modified_kp_abe()
	{
		Server server = new Server();
		Client PKUClient = new Client(new String[]{"PKU", "USer"});
		Client THUClient = new Client(new String[]{"THU", "USer"});
		Client TeacherClient = new Client(new String[]{"PKU", "USer"});
		Client PKUClient1 = new Client(new String[]{"PKU", "USer"});
		Client THUClient1 = new Client(new String[]{"THU", "USer"});
	
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
	public void encrption( byte bs[]) {
		Server server = new Server();
		int primenumber=31;
		boolean flag;
		int len;
		Client PKUClient = new Client(new String[]{"PKU", "User"});
		Client THUClient = new Client(new String[]{"THU", "USer"});
		Client TeacherClient = new Client(new String[]{"PKU", "USer"});
		
		Client PKUClient1 = new Client(new String[]{"PKU", "USer"});
		Client THUClient1 = new Client(new String[]{"THU", "USer"});
		Client TeacherClient1 = new Client(new String[]{"PKU", "USer"});
		//client��server����ȡ��Կ�ַ���
		String PKJSONString = server.getPublicKeyInString();
		 len=bs.length*primenumber;
				 

		//client���Լ���������Ϣ���͸�server,����ȡ˽Կ�ַ���
		String SKJSONString = server.generateSecretKey(PKUClient.getAttrs());
		 
		
		SKJSONString = server.generateSecretKey(THUClient.getAttrs());
		
		
		SKJSONString = server.generateSecretKey(TeacherClient.getAttrs());
		
		for (int i = 0; i < len; i++) {	
			int hashcode=bs.hashCode();	
			int k=i%bs.length;
			if(hashcode==bs[k] &&  i>primenumber) {
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
