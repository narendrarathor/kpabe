package com.openshift.security;

public class Calculation {
static  double kpeb;
 static double var1=.18;
 static double var2=.15;
 	
 public static void set_kp(String s)
 {
	 double d=Double.parseDouble(s);
	 kpeb=d;
 }

	public static double get()
	{
		return kpeb*var1;
	}
	public static double get_mod()
	{
		return kpeb*var2;
	}

}
