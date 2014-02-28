package com.gikdew.facebookhacker;

import java.util.Random;

public class randomString{
	
	static final String AB = "abcdefghijklmnopqrstuvwxyz$·-:|0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Random rnd = new Random();
	
	public String getRandomString(int len) 
	{
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}
}
