package com.gx.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gx.po.Company;
import com.gx.po.EducationCustomer;
import com.gx.po.PhoneNUM;
import com.gx.po.ReportResult;
import com.gx.po.Result;
import com.gx.service.CompanyService;
import com.gx.service.EducationCustomerService;
import com.gx.service.PhoneNUMService;
import com.gx.service.ReportResultService;
import com.gx.service.ResultService;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/CompanyAction")
public class CompanyAction {

	@Autowired
	public CompanyService companyService;
	
	 
	
	
	
	/*
	 * 返回 listID 的list
	 */
	@RequestMapping("/findAllCompany")
	@ResponseBody 
	public void  findAllCompany(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException{  
	
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
		
		String starttime  = httprequest.getParameter("startTime");
		String endtime  = httprequest.getParameter("endTime");
		
		String campany_id  = httprequest.getParameter("campany_id");
		
		if(starttime!=null&&!("").equals(starttime)&&endtime!=null&&!("").equals(endtime)) {
			String sql ="  where created_time BETWEEN '"+starttime+"' and '"+endtime+"' ";
			
			if(campany_id!=null&&!("").equals(campany_id)) {
				
				sql+= " and company_id ='"+campany_id+"' ";
			}
			List<Company> companyList = companyService.findBySQLforCompany(sql);
			
			
			map.put("code", 1);
			map.put("data", companyList);
		}else {
			
			map.put("code", 0);
			map.put("tips", "请传入正确的时间");
			
		}
		
		
		
		
		
		
		
			jsonObject = jsonObject.fromObject(map);
			httpresponse.setCharacterEncoding("UTF-8"); 
			httpresponse.setContentType("text/html;charset=utf-8");
			httpresponse.setHeader("Access-Control-Allow-Origin","*");
			httpresponse.getWriter().print(jsonObject.toString()); 
			
	}
	
	
	@RequestMapping("/findCompanyByID")
	@ResponseBody 
	public void  findCompanyByID(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException{  
	
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
	
	
		
		
		

		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
	}
	
	
	
	
	@RequestMapping("/AddCompany")
	@ResponseBody 
	public void AddCompany(HttpServletRequest httprequest, HttpServletResponse httpresponse) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
		String Campany_id  = httprequest.getParameter("Campany_id");
		
		String Campany_name  = httprequest.getParameter("Campany_name");
		String status  = httprequest.getParameter("status");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String nowTime = df.format(new Date());// new Date()为获取当前系统时间
		
		Company com = new Company();
		com.setCompany_name(Campany_name);
//		com.setStatus(status);
		
		Company comnew = companyService.findforCompany(com);
		if(comnew == null) {
			com.setCompany_id(Campany_id);
			com.setStatus(status);
			com.setCreated_time(nowTime);
			
			companyService.insert(com);
			map.put("code", 1);
//			map.put("data", companyList);
		}else {
			map.put("code", 0);
			map.put("tips", "已有的campany_name");
		}
		
		
		
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		
		httpresponse.getWriter().print(jsonObject.toString()); 
		
		
	}
	
	
	
	@RequestMapping("/updateCompany")
	@ResponseBody 
	public void updateCompany(HttpServletRequest httprequest, HttpServletResponse httpresponse) throws Exception {
	
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;

		Integer id = Integer.parseInt(httprequest.getParameter("id"));
		String Campany_name  = httprequest.getParameter("Campany_name");
		String status  = httprequest.getParameter("status");
		
		
		Company com = companyService.selectByPrimaryKey(id);
//		com.setStatus(status);
		
//		Company comnew = companyService.findforCompany(com);
		if(com != null) {
			com.setStatus(status);
			com.setStatus(Campany_name);
			
			
			companyService.updateByPrimaryKeyforCompany(com);
			map.put("code", 1);
//			map.put("data", companyList);
		}else {
			map.put("code", 0);
			map.put("tips", "未找到");
		}
		
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		
		httpresponse.getWriter().print(jsonObject.toString()); 
		
	}
	
	@RequestMapping("/putAllCompany")
	@ResponseBody 
	public void findByIDGuest(HttpServletRequest httprequest, HttpServletResponse httpresponse) throws Exception {
	
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;

//		Integer id = Integer.parseInt(httprequest.getParameter("id"));
		String ai_campID  = httprequest.getParameter("ai_campID");
		
		
		
		
		
		
		
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		
		httpresponse.getWriter().print(jsonObject.toString()); 
	
	}
	
	
	
	
	
}
