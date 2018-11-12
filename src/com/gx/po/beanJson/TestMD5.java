package com.gx.po.beanJson;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import sun.misc.BASE64Encoder;

public class TestMD5 {  
public String getMD5(byte[] source){  
    String s=null;  
    //用来将字节转换成16进制表示的字符  
    char[] hexDigits={'0','1','2','3','4','5','6','7','8','9',  
            'a','b','c','d','e','f'};  
    try {  
        MessageDigest md=MessageDigest.getInstance("MD5");  
        md.update(source);  
        //MD5的计算结果是一个128位的长整数，用字节表示为16个字节  
        byte[] tmp=md.digest();  
        //每个字节用16进制表示的话，使用2个字符(高4位一个,低4位一个)，所以表示成16进制需要32个字符  
        char[] str=new char[16*2];  
        int k=0;//转换结果中对应的字符位置  
        for(int i=0;i<16;i++){//对MD5的每一个字节转换成16进制字符  
            byte byte0=tmp[i];  
            str[k++]=hexDigits[byte0>>>4 & 0xf];//对字节高4位进行16进制转换  
            str[k++]=hexDigits[byte0 & 0xf];    //对字节低4位进行16进制转换  
        }  
        s=new String(str);  
    } catch (NoSuchAlgorithmException e) {  
        e.printStackTrace();  
    }  
    return s;  
}  


public static void main(String[] args) {  
	
	
//    TestMD5 md5Util=new TestMD5();  
//    String result=md5Util.getMD5("29da8c37773f48a97fa1862b5a8e5349".getBytes());  
//
	
	String[] noLine = {"不","布","补","步","部","簿","吥","捕","卜","部","埠","怖","哺","堡","普","浦","噗","铺","谱","朴","扑","葡","付","服","福","府","不用","布用","补用","步用","部用","簿用","吥用","捕用","卜用","部用","埠用","怖用","哺用","堡用","普用","浦用","噗用","铺用","谱用","朴用","扑用","葡用","付用","服用","福用","府用","不行","布行","补行","步行","部行","簿行","吥行","捕行","卜行","部行","埠行","怖行","哺行","堡行","普行","浦行","噗行","铺行","谱行","朴行","扑行","葡行","付行","服行","福行","府行","不对","布对","补对","步对","部对","簿对","吥对","捕对","卜对","部对","埠对","怖对","哺对","堡对","普对","浦对","噗对","铺对","谱对","朴对","扑对","葡对","付对","服对","福对","府对","不要","布要","补要","步要","部要","簿要","吥要","捕要","卜要","部要","埠要","怖要","哺要","堡要","普要","浦要","噗要","铺要","谱要","朴要","扑要","葡要","付要","服要","福要","府要","不需要","布需要","补需要","步需要","部需要","簿需要","吥需要","捕需要","卜需要","部需要","埠需要","怖需要","哺需要","堡需要","普需要","浦需要","噗需要","铺需要","谱需要","朴需要","扑需要","葡需要","付需要","服需要","福需要","府需要","不方便","布方便","补方便","步方便","部方便","簿方便","吥方便","捕方便","卜方便","部方便","埠方便","怖方便","哺方便","堡方便","普方便","浦方便","噗方便","铺方便","谱方便","朴方便","扑方便","葡方便","付方便","服方便","福方便","府方便"};
	int index = Arrays.binarySearch(noLine,"no");
	
	
	
	
	System.out.println(index);  
}  


}


