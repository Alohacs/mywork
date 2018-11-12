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

import com.gx.po.EducationHaiFeng;
import com.gx.po.PhoneNUM;
import com.gx.po.ProjectCount;
import com.gx.po.Role;
import com.gx.po.TokenCode;
import com.gx.service.AiUserService;
import com.gx.service.EducationHaiFengService;
import com.gx.service.PhoneNUMService;
import com.gx.service.ProjectCountService;
import com.gx.service.RoleService;
import com.gx.service.TokenCodeService;
import com.gx.tokenProcessor.util.TokenProcessor;

import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

@Controller
@RequestMapping("/HaiFengAction")
public class HaiFengAction {
	
	
	@Autowired
	public EducationHaiFengService educationHaiFengService;
	
	@Autowired
	public ProjectCountService projectCountService;
	
	
	
	@RequestMapping("/addHaiFeng")
	@ResponseBody 
	public void  addHaiFeng(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
		
		//父母姓名 ，电话
		String  parent_name = httprequest.getParameter("parent_name"); 
		String  parent_phone = httprequest.getParameter("parent_phone"); 
		
		//考试种类和考试年份
//		String  test_type = httprequest.getParameter("test_type"); 
//		String  test_year = httprequest.getParameter("test_year"); 
		
		//学生年纪
		String  stu_grade = httprequest.getParameter("stu_grade");
		
		//试听课程
		String  listening_subject = httprequest.getParameter("listening_subject"); 
		
		
		String  interest_degree = httprequest.getParameter("interest_degree"); 
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
		
		EducationHaiFeng emxc = new EducationHaiFeng(null,parent_name,parent_phone,stu_grade,null,listening_subject,format.format(new Date()),"AI_HF",null, interest_degree);
		
		EducationHaiFeng newemxc = educationHaiFengService.findforEHF(emxc);
		if(null==newemxc) {
			educationHaiFengService.insert(emxc);
			
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
