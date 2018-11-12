package com.gx.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
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

import com.gx.po.AiUser;
import com.gx.po.Role;
import com.gx.service.AiUserService;
import com.gx.service.RoleService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/AiUserAction")
public class AiUserAction {
	
	@Autowired
	public AiUserService aiUserService;
	@Autowired
	public RoleService roleService;
	
	
	
	@RequestMapping("/addAiUser")
	@ResponseBody 
	public void  addAiUser(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
		String nickName = httprequest.getParameter("nickName");
		String userName = httprequest.getParameter("userName");
		String passWord = httprequest.getParameter("passWord");
		String roleId =httprequest.getParameter("roleId");
		String campID = httprequest.getParameter("campID");
		String status = httprequest.getParameter("status");
		
		
		
//		String nickName = "aaaa";
//		String userName = "asd";
//		String passWord = "asd123";
//		String roleId = "3";
//		String campID = "";
//		String status = "";
		
		AiUser ai = new AiUser();
		ai.setAi_u_nickname(nickName);
		ai.setAi_u_username(userName);
		ai.setAi_u_password(passWord);
		ai.setAi_u_roleID(roleId);
		ai.setAi_u_campainID(campID);
		ai.setAi_status(status);
//		String SQL = " where ai_u_username='asd' and ai_u_password='asd123'";
		String SQL = " where ai_u_username='"+userName+"'";
		List<AiUser> aiUser =aiUserService.findBySQL(SQL);
		if(aiUser.isEmpty()) {
			
			aiUserService.insert(ai);
		}
		
		
		map.put("code", 1);
		map.put("data", ai);
	
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
	}
	
	
	@RequestMapping("/updateAiUser")
	@ResponseBody 
	public void  updateAiUser(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
		
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
		String id = httprequest.getParameter("id");
		String nickName = httprequest.getParameter("nickName");
		String userName = httprequest.getParameter("userName");
		String passWord = httprequest.getParameter("passWord");
		String roleId =httprequest.getParameter("roleId");
		String campID = httprequest.getParameter("campID");
		String status = httprequest.getParameter("status");
		
//		Integer id = 2;
//		String nickName = "bb";
//		String userName = "123";
//		String passWord = "33cccc";
//		String roleId = "2";
//		String campID = "";
//		String status = "";
		
		AiUser ai = new AiUser();
		ai.setAi_u_nickname(nickName);
		ai.setAi_u_username(userName);
		ai.setAi_u_password(passWord);
		ai.setAi_u_roleID(roleId);
		ai.setAi_u_campainID(campID);
		ai.setAi_status(status);
		ai.setAi_u_id(Integer.parseInt(id));
		aiUserService.updateByPrimaryKeyforAiuser(ai);
		
		map.put("code", 1);
		map.put("data", ai);
	
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
		
		
	}
	
	@RequestMapping("/findAllAiUser")
	@ResponseBody 
	public void  findAllAiUser(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
	
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
		String id = httprequest.getParameter("id");
		if(id!=null) {
			AiUser aiUser = aiUserService.selectByPrimaryKey(Integer.parseInt(id));
			map.put("data", aiUser);
		}else {
			
			List<AiUser> aiUser = aiUserService.findAll();
			for(AiUser au:aiUser) {
				String sql =" where id ='"+au.getAi_u_roleID()+"'";
				List<Role> role = roleService.findBySQLforRole(sql);
				if(!role.isEmpty())
				au.setRole(role.get(0));
			}
			
			
			map.put("data", aiUser);
		}
		
		
		map.put("code", 1);
	
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
		
	
	}
	

	@RequestMapping("/addRole")
	@ResponseBody 
	public void  addRole(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
	
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
		String rolename = httprequest.getParameter("rolename");
		String level = httprequest.getParameter("level");
		String status = httprequest.getParameter("status");
		
		
//		String rolename = "2";
//		String level = "";
//		String status = "";
		
		Date creatime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(creatime);
		
		Role role = new Role();
		role.setRoleName(rolename);
		role.setLevel(level);
		role.setStatus(status);
		role.setCreatTime(dateString);
		Role newRole = roleService.findBySQLforRL(role);
		if(newRole==null) {
			roleService.insert(role);
		}
		map.put("code", 1);
		map.put("data", role);
	
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
	
	}
	
	
	@RequestMapping("/findAllRole")
	@ResponseBody 
	public void  findAllRole(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
	
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
		String id = httprequest.getParameter("id");
		String SQL  = "";
		if(id!=null) {
			SQL = "where id ='"+id+"'";
			
		}
		List<Role> roleList = roleService.findBySQLforRole(SQL);
		
		
		map.put("code", 1);
		map.put("data", roleList);
	
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
		
	
	}
	
	
	@RequestMapping("/editRole")
	@ResponseBody 
	public void  editRole(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
	
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
		String id = httprequest.getParameter("id");
		String rolename = httprequest.getParameter("rolename");
		String level = httprequest.getParameter("level");
		String status = httprequest.getParameter("status");
		
		
//		Integer id = 4;
//		String rolename = "2";
//		String level = "";
//		String status = "";

		Role role = new Role();
		role.setRoleName(rolename);
		role.setLevel(level);
		role.setStatus(status);
		role.setId(Integer.parseInt(id));
		
		roleService.updateByPrimaryKeyforRole(role);
		
		
		map.put("code", 1);
		map.put("data", role);
	
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
		
		
		
	}
	
	
	
}
