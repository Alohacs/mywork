package com.gx.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gx.po.AiUser;
import com.gx.po.User;
import com.gx.service.AiUserService;
import com.gx.service.IUserService;
import com.gx.vo.AiUserRole;
import com.gx.vo.RowCount;
import com.gx.vo.UserVo;


@Controller
@RequestMapping("/loginController")
public class LoginAction {
	@Autowired
	public IUserService userService;
	@Autowired
	public AiUserService aiUserService;
	
	
//	 private final static  String URL = "http://10.208.134.10:8080/CSRBroker/dialogue";  
	    
	
//	public class testFilter implements Filter { 
//		public void doFilter(ServletRequest request, ServletResponse resp, FilterChain chain) throws IOException, ServletException {HttpServletResponse response = (HttpServletResponse) resp; response.setHeader("Access-Control-Allow-Origin", "*"); //解决跨域访问报错 
//		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE"); 
//		response.setHeader("Access-Control-Max-Age", "3600"); //设置过期时间 
//		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, client_id, uuid, Authorization"); 
//		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 支持HTTP 1.1. 
//		response.setHeader("Pragma", "no-cache"); // 支持HTTP 1.0. response.setHeader("Expires", "0"); 
//		chain.doFilter(req, res); 
//		} 
//		public void init(FilterConfig filterConfig) {} public void destroy() {}}
//	
	
	@RequestMapping("/login")
	@ResponseBody
	public void login(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException 
	{
		
		String username  = request.getParameter("username");
		String password  = request.getParameter("password");
//		String username  = "GD001"; 
//		String password = "123456";
		
		String SQL = " where ai_user.ai_u_username='" + username+"'";
		
//		String SQL = " where ai_user.ai_u_username='GD001'";
//		List<AiUser> list = aiUserService.findBySQL(SQL);
		List<AiUserRole> list = aiUserService.findRoleBySQL(SQL);
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONArray json = new JSONArray();  
		 JSONObject jsonObject = null;
		if(list.size() >= 1){
			AiUserRole guser = list.get(0);
			if(guser.getAi_u_username().equals(username) && guser.getAi_u_password().equals(password)){
				
				if(guser.getAi_status().equals("0")) {
					map.put("code", 0);
					map.put("tips", "账号已禁用");
				}else {
//				request.getSession().setAttribute("isSuccess", "1");
//					List<AiUser> userlist = new ArrayList<AiUser>();
//					userlist.add(guser);
					map.put("code", 1);
					map.put("username", guser.getAi_u_username());
					map.put("nickname", guser.getAi_u_nickname());
					map.put("camp_id", guser.getAi_u_campainID());
					map.put("level", guser.getLevel());
					
					
				}
				
			}else{
				map.put("code", 0);
				map.put("tips", "账号秘密有误");
			}
		}else{
			map.put("code", 0);
			map.put("tips", "该账号不存在");
		}
		
		jsonObject = jsonObject.fromObject(map);
		
		/*
		 *  解决跨域访问代码，被访问时允许所有人进行访问，反之不行

			或者可以指定某个具体域名访问
			response.setHeader("Access-Control-Allow-Origin", "http://baidu.com");
		 */
		response.setCharacterEncoding("UTF-8"); 
		response.setHeader("Access-Control-Allow-Origin","*");
		
	    response.getWriter().print(jsonObject.toString()); 
	}
	
	
	
	
	
	@RequestMapping("/getFormdata")
	@ResponseBody
	 public void   getFormdata(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException {
	 
		
		 List<User> list = new ArrayList<User>();
		 User d1 = new User(9,"测试01", "50");
		 User d2 = new User(99,"测试02", "50");
		 User d3 = new User(999,"测试03", "50");
         //日期转换 在实体对象属性上加@JSONField (format="yyyy-MM-dd")
         list.add(d1);
         list.add(d2);
         list.add(d3);
//         String json = Json.toJSONString(list);
         
         try{  
         JSONArray json = new JSONArray();  
           
         for(User a :list){  
             JSONObject obj = new JSONObject();  
             obj.put("id",a.getUserid());  
             obj.put("name",a.getUsername());  
             obj.put("money",a.getPassword());  
               
             json.add(obj);  
               
           
         }  
           
         response.getWriter().print(json.toString());      
           
         }  
           
         catch(Exception e){  
             e.printStackTrace();  
         }  
         
         
	 }  
	
	
	@RequestMapping("/getFormdata2")
	@ResponseBody
	 public void   getFormdata2(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException {
		String starttime  = request.getParameter("startTime");
		String endtime  = request.getParameter("endTime");
		  
		  
		  
		System.out.println(starttime);
		System.out.println(endtime);
		  
	       Map<String, Object> map = new HashMap<String, Object>();
	       map.put("issure", "ture");
	       JSONObject jsonObject = JSONObject.fromObject(map);
//	       System.out.println(jsonObject);

	       /**
	        * 解析 JavaBean
	        */
	       User person = new User(51,"A001", "Jack");
	       User person2 = new User(52,"A002", "Jac2");
	       
	       jsonObject = jsonObject.fromObject(person); 
//	       jsonObject = jsonObject.fromObject(person2);
//	       System.out.println(jsonObject);

	       /**
	        * 解析嵌套的对象
	        */
	       List<User> list = new ArrayList<User>();
	       list.add(person);
	       list.add(person2);
	       
	       
	       
	       map.put("data", list);
	       
	       jsonObject = jsonObject.fromObject(map);
//	       System.out.println(jsonObject);
		
	       response.getWriter().print(jsonObject.toString()); 
	}
	
	
	
	@RequestMapping("/findAiUserAndRole")
	public String findAiUserAndRole(HttpServletRequest request){
		
		String SQL = "";
		List<AiUserRole> aiList = aiUserService.findRoleBySQL(SQL);
		return SQL;
	}
	
	
	
	@RequestMapping("/findAll")
	public String findAll(HttpServletRequest request){
		List<User> list = userService.findAll();
		request.getSession().setAttribute("users", list);
		String SQL = "";
		List<UserVo> volist = userService.findVoBySQL(SQL);
		request.getSession().setAttribute("powerVos", volist);
		return "redirect:/loginController/main.do";//重定向Controller
	}
	
	@RequestMapping("/main")
	public String main(){
		return "/main";
	}
	
	@RequestMapping("/login2")
	public ModelAndView login2(String username, String password, HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String SQL = " where username='" + username + "' and password='" + password +"'";
		List<User> list = userService.findBySQL(SQL);
		List<User> users = null;
		if(list.size() == 1){
			User guser = list.get(0);
			if(guser.getUsername().equals(username) && guser.getPassword().equals(password)){
				mav = new ModelAndView("/main");
				users = userService.findAll();
			}else{
				mav = new ModelAndView("/login");
			}
		}else{
			mav = new ModelAndView("/login");
		}
		mav.addObject("users", users);
		mav.addObject("username", username);
		return mav;
	}
	
	@RequestMapping("/toAdd")
	public String toAdd(){
		return "/add";
	}
	
	@RequestMapping("/add")
	public String insert(User user, HttpServletRequest request){
		int result = userService.insertSelective(user);
		return findAll(request);
	}
	
	@RequestMapping("/toUpdate")
	public String toUpdate(int userId, HttpServletRequest request){
		User user = userService.selectByPrimaryKey(userId);
		request.getSession().setAttribute("user", user);
		return "/update";
	}
	
	@RequestMapping("/update")
	public String update(User user, HttpServletRequest request){
		int result = userService.updateByPrimaryKeySelective(user);
		return findAll(request);
	}
	
	@RequestMapping("/delete")
	public String delete(int userId, HttpServletRequest request){
		int result = userService.deleteByPrimaryKey(userId);
		return findAll(request);
	}
	
	@RequestMapping("/rowCount")
	public void rowCount(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String SQL = "";
		RowCount rowCount = userService.rowCount(SQL);
		int count = rowCount.getCount();
		JSONObject json = new JSONObject();
		json.put("count", count);
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}
	
	@RequestMapping("/findPageByVoSQL")
	public void findPageByVoSQL(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String SQL = "";
		int currentPage = 0;
		if(request.getParameter("currentPage") != null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int pageSize = 0;
		if(request.getParameter("pageSize") != null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		currentPage = currentPage - 1;
		currentPage = currentPage * pageSize;
		List<UserVo> powerVos = userService.findPageByVoSQL(SQL, currentPage, pageSize);
		JSONObject json = new JSONObject();
		json.put("data", powerVos);
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}
	
}
