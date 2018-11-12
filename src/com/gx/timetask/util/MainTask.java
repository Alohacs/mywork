package com.gx.timetask.util;
import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled; 
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gx.service.ResultService;
import com.gx.tokenProcessor.util.TokenProcessor;
import com.gx.web.APIexternalAction;
import com.gx.web.OperationAction; 



@Component
public class MainTask{ 
	
	@Autowired
	public APIexternalAction apiexternalAction;
	@Autowired
	public OperationAction operationAction;
	
	public String ct =  "0 0 4 * * ?";
//	
//@Scheduled(cron = "0 */5 * * * ?") 
 public void run() throws Exception { 
//  System.out.println("推送消息来了"); 
  

	  try {
		  
		  operationAction.setDate();
		  operationAction.setDetailNEW();
		  operationAction.getAjaxJsonTest();
		  operationAction.insertReportResult();
		  apiexternalAction.setTokenCode();
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	 
	 
	  
	  
	  
	  
  
  
  
 } 
  
}