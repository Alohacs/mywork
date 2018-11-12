package com.gx.tokenProcessor.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;


public class MyRandom{
	
	
	public static String getRandomLetter(int len) {
		char[]  array = new char[len];
		char ch;
		int chInt;
		boolean flag;
		int isLowerCase;
		for(int i = 0;i < array.length; i++) {
			flag = true;
			chInt = (int)(Math.random()*26+65);
			ch = (char)chInt;
			for(int j = 0 ;j < i; j++) {
				if(ch == array[j]) {
					i--;
					flag=false;
					break;
				}
			}
			if(flag) {
				isLowerCase = (int)(Math.random()*2);
				if(isLowerCase<1) {
					array[i] = (char)(chInt+32);
				}else {
					array[i] = ch;
				}
			}
		}
		return array.toString();
	}
	
	
	
	public static String getMyRandom(){
		String str = "";
		Random rand = new Random();
		for(int i=0;i<5;i++){
			int num = rand.nextInt(3);
			switch(num){
				case 0:
					char c1 = (char)(rand.nextInt(26)+'a');//生成随机小写字母 
					str += c1;
					break;
				case 1:
					char c2 = (char)(rand.nextInt(26)+'A');//生成随机大写字母 
					str += c2;
					break;
				case 2:
					str += rand.nextInt(10);//生成随机数字
			}
		}
		return str;
	}
	
	public static String getMyRandomNum(){
		
		Random random = new Random();
		String result="";
		for (int i=0;i<6;i++)
		{
			result+=random.nextInt(10);
		}
//		System.out.println(result);
		return result;
	}
	
 	
	
	
	
	
	public static void main(String[] args) {
		
		
		 UUID uuid = UUID.randomUUID();
	        /**
	         * .{6c0222ed-e7f5-4cad-a717-a9abfb372239}
	         */
	        System.out.println(".{" + uuid.toString() + "}");
	        /**
	         * 6c0222ed-e7f5-4cad-a717-a9abfb372239
	         */
	        System.out.println(uuid.toString());
	        /**
	         * 36
	         */
	        System.out.println(uuid.toString().length());
	        /**
	         * 32
	         */
	        System.out.println(uuid.toString().replace("-", "").length());
	        /**
	         * 6c0222ede7f54cada717a9abfb372239
	         */
	        System.out.println(uuid.toString().replace("-", ""));
	}
	
	
	
}
