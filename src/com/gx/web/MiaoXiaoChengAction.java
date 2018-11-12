package com.gx.web;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gx.po.AiUser;
import com.gx.po.EducationMiaoXiaoCheng;
import com.gx.po.PhoneNUM;
import com.gx.po.ProjectCount;
import com.gx.po.Role;
import com.gx.po.TokenCode;
import com.gx.service.AiUserService;
import com.gx.service.EducationCustomerService;
import com.gx.service.EducationMiaoXiaoChengService;
import com.gx.service.PhoneNUMService;
import com.gx.service.ProjectCountService;
import com.gx.service.RoleService;
import com.gx.service.TokenCodeService;
import com.gx.tokenProcessor.util.TokenProcessor;

import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

@Controller
@RequestMapping("/MiaoXiaoChengAction")
public class MiaoXiaoChengAction {
	
	
	@Autowired
	public EducationMiaoXiaoChengService educationMiaoXiaoChengService;
	
	@Autowired
	public ProjectCountService projectCountService;
	
	
	
	@RequestMapping("/addMiaoXiaoCheng")
	@ResponseBody 
	public void  addMiaoXiaoCheng(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
		
		
		String  child_name = httprequest.getParameter("child_name"); 
		
		String  child_grade = httprequest.getParameter("child_grade"); 
		String  parent_phone = httprequest.getParameter("parent_phone"); 
		String  interest_degree = httprequest.getParameter("interest_degree"); 
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
		
		EducationMiaoXiaoCheng emxc = new EducationMiaoXiaoCheng(null, child_name, child_grade, null, parent_phone, format.format(new Date()), "AI_MXC", null, interest_degree);
		
		EducationMiaoXiaoCheng newemxc = educationMiaoXiaoChengService.findforEMXC(emxc);
		if(null==newemxc) {
			educationMiaoXiaoChengService.insert(emxc);
			
			map.put("code", 1);
			map.put("data", emxc);
			
		}else {
			map.put("code", 0);
			map.put("tips", "该手机号已领取免费课程，如有疑问请联系客服！");
			
			
		}
		
		
		
		
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
		
		
		
		
	}
	
	
	@RequestMapping("/countClick")
	@ResponseBody 
	public void  countClick(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
		
		
//		int inputInt = Integer.valueOf(httprequest.getParameter("inputInt"));  
		
		String campid = httprequest.getParameter("camp_id");
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
		ProjectCount newpc =  projectCountService.findByProjectCount(campid);
		if(newpc==null) {
			
			map.put("code", 0);
		}else{
			
			map.put("code", 1);
			int count = Integer.parseInt(newpc.getCount_num());
			count+= 1;
			newpc.setCount_num(String.valueOf(count));
			projectCountService.updateByPrimaryKey(newpc);
			
			
			map.put("count", count);
		}
		
		
		
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
		
		
	}
	
	
	
	
}
