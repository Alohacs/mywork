package com.gx.tokenProcessor.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gx.po.TokenCode;
import com.gx.service.TokenCodeService;

import sun.misc.BASE64Encoder;

//令牌生产器
public class TokenProcessor {
    public TokenProcessor(){}
    private static TokenProcessor instance = new TokenProcessor();
    
    @Autowired
    public TokenCodeService tokenCodeService;
    
    public static TokenProcessor getInstance(){
        return instance;
    }
    public void generateTokeCode(){
    	TokenCode tc = new TokenCode();
        String value = System.currentTimeMillis()+new Random().nextInt()+"";
//    	String value ="1531018665970";
//        System.out.println(value); 
        tc.setRandomNUM(value);
        
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(currentTime);
//        System.out.println(formatter.format(date));
        tc.setSystemTime(formatter.format(date));

        //获取数据指纹，指纹是唯一的
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] b = md.digest(value.getBytes());//产生数据的指纹
            //Base64编码
            BASE64Encoder be = new BASE64Encoder();
            be.encode(b);
            System.out.println(be.encode(b)); 
            tc.setTokenCode(be.encode(b));
//            tokenCodeService.insert(tc);
//            return be.encode(b);//制定一个编码
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
//        return null;
    }
    
    
    public static void main(String[] args) {
        TokenProcessor processor=new TokenProcessor();
        processor.generateTokeCode();
    }
}