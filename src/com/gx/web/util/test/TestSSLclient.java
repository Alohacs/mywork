package com.gx.web.util.test;

import java.util.HashMap;  
import java.util.Map;

import com.gx.web.test.HttpUtil;
import com.gx.web.util.HttpClientUtil;  

public class TestSSLclient {

    private String url = "http://localhost:8080/ssmtest2/loginController/getFormdata2.do";  
    private String charset = "utf-8";  
    private HttpClientUtil  httpClientUtil = null;  
      
    public TestSSLclient(){  
        httpClientUtil = new HttpClientUtil ();  
    }  
      
    public void test(){  
        String httpOrgCreateTest = url + "httpOrg/create";  
        Map<String,String> createMap = new HashMap<String,String>();  
        createMap.put("startTime","2017-04-01 00:00:00");  
        createMap.put("endTime","2018-06-06 00:00:00");  
        String can = "{'startTime':'2017-04-01 00:00:00','endTime':'2018-06-06 00:00:00'}";
        
        
//        createMap.put("orgkey","****");  
//        createMap.put("orgname","****");  
        String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest,can,charset);  
        System.out.println("result:"+httpOrgCreateTestRtn);  
    }  
      
    public static void main(String[] args){  
    	TestSSLclient main = new TestSSLclient();  
        main.test();  
    }  
}  