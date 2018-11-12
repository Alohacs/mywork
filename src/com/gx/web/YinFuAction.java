package com.gx.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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

import com.gx.po.EducationEf;
import com.gx.service.EducationYinFuService;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/YinFuAction")
public class YinFuAction {

	
	@Autowired
	public  EducationYinFuService educationYinFuService;
	
	
	@RequestMapping("/addYinFu")
	@ResponseBody 
	public void  addYinFu(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
		
	
		String  stu_name = httprequest.getParameter("stu_name"); 
		String  stu_sex = httprequest.getParameter("stu_sex");
		String  stu_grade = httprequest.getParameter("stu_grade"); 
		String  parent_name = httprequest.getParameter("parent_name");
		String  parent_phone = httprequest.getParameter("parent_phone"); 
		String  list_id = httprequest.getParameter("list_id"); 
		String  lead_id = httprequest.getParameter("lead_id");
		String  call_id = httprequest.getParameter("call_id"); 
		String  city = httprequest.getParameter("city");
		String  status = httprequest.getParameter("status"); 
		String  created_time = httprequest.getParameter("created_time"); 
		String  update_time = httprequest.getParameter("update_time");
		String  q_Other = httprequest.getParameter("q_Other"); 
		String  q_ChildAge = httprequest.getParameter("q_ChildAge");
		String  q_RegistrationActivities = httprequest.getParameter("q_RegistrationActivities"); 
		String  interest_degree = httprequest.getParameter("interest_degree"); 
		String  remark = httprequest.getParameter("remark"); 
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
		
        EducationEf emxc = new EducationEf(null, stu_name, stu_sex, stu_grade, parent_name, parent_phone, list_id, lead_id, call_id, city, status, created_time, update_time, q_Other, q_ChildAge, q_RegistrationActivities, interest_degree, remark);
		EducationEf newemxc = educationYinFuService.findforEEF(emxc);
				
		if(null==newemxc) {
			educationYinFuService.insert(emxc);
			
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
		
		
		int inputInt = Integer.valueOf(httprequest.getParameter("inputInt"));  
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
		inputInt+=1;
		
		map.put("count", inputInt);
		
		
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
		
		
	}
	  /*根据查找信息进行分页*/
		
		@RequestMapping("/SelectYinFu")
		@ResponseBody 
		public void  SelectYinFu(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
			
		
			httprequest.setCharacterEncoding("UTF-8");
			httpresponse.setContentType("text/html;charset=UTF-8");
			String SQL = "";
			int currentPage = 0;
			if(httprequest.getParameter("currentPage") != null){
				currentPage = Integer.valueOf(httprequest.getParameter("currentPage"));
			}
			int pageSize = 0;
			if(httprequest.getParameter("pageSize") != null){
				pageSize = Integer.parseInt(httprequest.getParameter("pageSize"));
			}
			currentPage = currentPage - 1;
			currentPage = currentPage * pageSize;
			List<EducationEf> powerVos = educationYinFuService.SelectYinFu(SQL, currentPage, pageSize);
			JSONObject json = new JSONObject();
			json.put("data", powerVos);
			PrintWriter out = httpresponse.getWriter();
			out.write(json.toString());
			
			
		}
	
		

	
}
