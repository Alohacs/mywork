package com.gx.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.gx.po.AiUser;
import com.gx.po.EducationCustomer;
import com.gx.po.Mission;
import com.gx.po.PhoneNUM;
import com.gx.po.Role;
import com.gx.service.AiUserService;
import com.gx.service.EducationCustomerService;
import com.gx.service.MissionService;
import com.gx.service.PhoneNUMService;
import com.gx.service.RoleService;
import com.gx.tokenProcessor.util.MyRandom;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/AiMissionAction")
public class AiMissionAction {
	
	@Autowired
	public MissionService missionService;
//	@Autowired
	public MyRandom myRandom;
	@Autowired
	public PhoneNUMService phoneNUMService;
	@Autowired
	public EducationCustomerService educationCustomerService;
	
	
	
	@RequestMapping("/addAImission")
	@ResponseBody 
	public void  addAImission(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
	
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
//		String excel_name = httprequest.getParameter("excel_name");
//		String created_time = httprequest.getParameter("created_time");
		String assignment_name = httprequest.getParameter("assignment_name");
		
		String start_time = httprequest.getParameter("start_time");
		String shut_time = httprequest.getParameter("shut_time");
		
		String camp_id = httprequest.getParameter("campaign");
		
		String status = httprequest.getParameter("status");	//任务执行状态  1，立即执行       0，稍后执行
		
		String exhalation_time = httprequest.getParameter("exhalation_time");
		
		Mission mission = new Mission();
		

		
		long time = System.currentTimeMillis();			// 这个获取系统时间
		String listName = String.valueOf(time/1000);	//转化格式
		
		String lIisID = getListID(listName,camp_id);  			//通过  CCMS获得 唯一对应的  ListID
		
		
		
//		String listID = myRandom.getMyRandomNum();
//		String sql = "where listID = '"+listID+"'";
//		
//		List<Mission> findMisson = missionService.findBySQLforMission(sql);
//		if(!findMisson.isEmpty()) {
//			listID = myRandom.getMyRandomNum();
////			sql = "where listID = '"+listID+"'";
////			
////			findMisson = missionService.findBySQLforMission(sql);
//			
//		}	
		
			if(null!= lIisID) {
		
				mission.setListNAME(listName);
				mission.setListID(lIisID);
				mission.setAssignment_name(assignment_name);
				mission.setExhalation_time(exhalation_time);
				mission.setStatus(status);
				mission.setIsCompletel("0");
				
				mission.setAi_campID(camp_id);
				
				mission.setStart_time(start_time);
				mission.setShut_time(shut_time);
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				String date = df.format(new Date());
				mission.setCreated_time(date);
				
				missionService.insert(mission);
				
			
			
				
				map.put("code", 1);
				map.put("data", mission);
		
			}else {
				
				map.put("code", 0);
				map.put("tips", "创建List任务失败");
				
			}
		
		
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
	}
	
	
//	@RequestMapping("/getListID")
//	@ResponseBody 
	public String getListID(String listName,String campID) {
		
		OkHttpClient client = new OkHttpClient();
		client.setReadTimeout(100, TimeUnit.SECONDS);  
		MediaType mediaType = MediaType.parse("application/octet-stream");
		RequestBody body = RequestBody.create(mediaType, "{\r\n    \"Head\": {\r\n        \"Version\": \"0.0.1\", \r\n      "
				+ "  \"CMD\": \"AI003\", \r\n        \"Time\": \"2018-08-21 10:51:22\"\r\n    }, \r\n    "
				+ "\"Body\": {\r\n        \"token\": \"v31Z51S7i5TmCmz\",\r\n        \"user_login\": \"admin\",\r\n        \"user_pass\": \"123456\", \r\n        \"campaign_id\": \""+campID+"\", \r\n      "
				+ "  \"list_name\": \""+listName+"\", \r\n     "
				+ "   \"action\": \"add_list\"\r\n    }\r\n}\r\n");
		Request request = new Request.Builder()
		  .url("http://10.208.133.91/xiaomaiai/action/AddListID")
//				.url("http://10.208.134.21/xiaomaiai/action/AddListID")
		  .post(body)
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "fe6e1c05-964e-436b-8ccb-6ca6d00c902e")
		  .build();

		try {
			Response response = client.newCall(request).execute();
			
			String json = response.body().string();
			
			Map map1 = JSON.parseObject(json);
			
			Map<String,Object> map1_1 = (Map<String,Object>)map1.get("Body");
			
			String result = (String)map1_1.get("msg");
					if(("").equals(result)) {
						return String.valueOf(map1_1.get("list_id"));
					}
					
					return null;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
		
	}
	
	
	
	
	@RequestMapping("/updateAImission")
	@ResponseBody 
	public void  updateAImission(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
		String assignment_name = httprequest.getParameter("assignment_name");
		String exhalation_time = httprequest.getParameter("exhalation_time");
		String status = httprequest.getParameter("status");
		String id = httprequest.getParameter("id");
		
		Mission mis = missionService.selectByPrimaryKeyforMission(Integer.parseInt(id));
//		mis.setId(Integer.parseInt(id));
		if(null!=assignment_name)
			mis.setAssignment_name(assignment_name);
		if(null!=exhalation_time)
			mis.setExhalation_time(exhalation_time);
		
		mis.setStatus(status);
		
		missionService.updateByPrimaryKey(mis);
		
		map.put("code", 1);
		map.put("data", mis);
	
	
	
	
	jsonObject = jsonObject.fromObject(map);
	httpresponse.setCharacterEncoding("UTF-8"); 
	httpresponse.setContentType("text/html;charset=utf-8");
	httpresponse.setHeader("Access-Control-Allow-Origin","*");
	httpresponse.getWriter().print(jsonObject.toString()); 
		
		
	}
	
	
	@RequestMapping("/findAImissionByID")
	@ResponseBody 
	public void  findAImissionByID(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
		String id = httprequest.getParameter("id");
		
		Mission mis = missionService.selectByPrimaryKeyforMission(Integer.parseInt(id));
		
		if(mis!=null) {
			
			map.put("code", 1);
			map.put("data", mis);
		}else {
			map.put("code", 0);
			map.put("tips", "没有查询到！");
		}
		
		
	
		
		

		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
			
			
		}
		
	

	@RequestMapping("/findALLmission")
	@ResponseBody 
	public void  findALLmission(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
	
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
		String start_time = httprequest.getParameter("start_time");
		String shut_time = httprequest.getParameter("shut_time");
		
		String status = httprequest.getParameter("status");	
		String keyWord = httprequest.getParameter("keyWord");	
		
		String camp_id = httprequest.getParameter("campaign");
		
		
		
		String sql=" where created_time > '"+start_time+"' and created_time < '"+shut_time+"'";
			
		if(status!=null)
			sql+=" and status = "+status+" ";
		
			if(null!=keyWord) {
				sql+=" and CONCAT(assignment_name,listID,excel_name) LIKE '%"+keyWord+"%'";
			}
			sql += " and ai_campID ='"+camp_id+"' order by created_time desc ";
		
		List<Mission> mList = missionService.findBySQLforMission(sql);

		if(!mList.isEmpty()) {
			
			for(Mission mm :mList) {
				String sqlforPN =" where listID = '"+mm.getListID()+"' ";
				List<PhoneNUM> callPN = phoneNUMService.findBySQLforPhoneNUM(sqlforPN);
				mm.setCallNum(callPN.size());
				sqlforPN += " and status = '0'";
				List<PhoneNUM> uncallPN = phoneNUMService.findBySQLforPhoneNUM(sqlforPN);
				mm.setUnCallNum(uncallPN.size());
				
			}
			
			
			
			
			map.put("code", 1);
			map.put("data", mList);
		}else {
			map.put("code", 0);
			map.put("tips", "没有查询到！");
		}
		
			jsonObject = jsonObject.fromObject(map);
			httpresponse.setCharacterEncoding("UTF-8"); 
			httpresponse.setContentType("text/html;charset=utf-8");
			httpresponse.setHeader("Access-Control-Allow-Origin","*");
			httpresponse.getWriter().print(jsonObject.toString()); 
	
	}
	
	@RequestMapping("/putNumberForOne")
	@ResponseBody 
	public void  putNumberForOne(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
	
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
		String phone_number = httprequest.getParameter("phone_number");
	
		//拿到电话号码对固定的 mission 进行建立 电话表
		 PhoneNUM pn = new PhoneNUM();
	     EducationCustomer ec = new EducationCustomer();
 	     pn.setPhoneNumber(phone_number);
 	            
	     pn.setListID("180928004");
	     pn.setCustomerName("EducationOutBound");
	        		
	     PhoneNUM newPN = phoneNUMService.findforPhoneNum(pn);
	        if(null==newPN) {
	        	pn.setStatus("0");

	        	phoneNUMService.insert(pn);
    			
    			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				String nowTime = df.format(new Date());// new Date()为获取当前系统时间				arrayPhone.add(ec.getPhoneNumber());
				
        		EducationCustomer detail = new EducationCustomer();
				detail.setPhone_number(pn.getPhoneNumber());
				detail.setList_id("180928004");
//				detail.setCall_id(pn.getCallID());
				
				detail.setCreation_time(nowTime);
				detail.setUpdate_time(nowTime);
				
				
				detail.setStatus("0");
				detail.setAssignment_name("EducationOutBound");
				EducationCustomer result= educationCustomerService.findforEducationCustomer(detail);
				if(null==result)
				educationCustomerService.insert(detail);
	        	
				
				map.put("code", 1);
	        }else {
	        	newPN.setStatus("0");
	        	phoneNUMService.updateByPrimaryKeyforPhoneNUM(newPN);
	        	
	        	
	        	
	        	
	        	map.put("code", 1);
	        }
		
		
	
	
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
		
	}
	
	
	@RequestMapping("/putNumberForJR")
	@ResponseBody 
	public void  putNumberForJR(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
	
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
		String phone_number = httprequest.getParameter("phone_number");
		List<String> arrayPhone = new ArrayList<String>();
		
		long time = System.currentTimeMillis();			// 这个获取系统时间
		String listName = String.valueOf(time/1000);
		
		String lIisID = getListID(listName,"AI_JR");  
		
		
		if(null!=phone_number&&!("").equals(phone_number)) {
			
			arrayPhone.add(phone_number);
			
			OkHttpClient client = new OkHttpClient();
			client.setReadTimeout(100, TimeUnit.SECONDS);  
			Response response = null;
			try {
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, "{\n    \"Head\": {\n        \"Version\": \"0.0.1\", \n        \"CMD\": \"AI004\", \n    "
					+ "    \"Time\": \"2018-08-28 10:51:22\"\n    }, \n    \"Body\": {\n        \"token\": \"v31Z51S7i5TmCmz\",\n     "
					+ "   \"user_login\": \"admin\",\n        \"user_pass\": \"123456\", \n      "
					+ "  \"campaign_id\": \"AI_JR\", \n      "
					+ "  \"list_id\": \""+lIisID+"\", \n       "
					+ " \"phone_number\": \""+arrayPhone+"\", \n        \"action\": \"add_lead_id\"\n    }\n}\n");
			Request request = new Request.Builder()
			  .url("http://10.208.133.91/xiaomaiai/action/AddLeadID")
//					.url("http://10.208.134.21/xiaomaiai/action/AddLeadID")		
			  .post(body)
			  .addHeader("Content-Type", "application/json")
			  .addHeader("Cache-Control", "no-cache")
			  .addHeader("Postman-Token", "51c61bd3-cfd3-416f-b5a8-e5b4632d3090")
			  .build();
				 response = client.newCall(request).execute();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			finally {
//			ResponseBody responseBody = response.body();
//				responseBody.close();
//			}
			
			
			OkHttpClient clientsl = new OkHttpClient();
			clientsl.setReadTimeout(100, TimeUnit.SECONDS);  
			Response responsesl = null;
			MediaType mediaTypesl = MediaType.parse("application/json");
			RequestBody bodysl = RequestBody.create(mediaTypesl, "{\r\n    \"Head\": {\r\n        \"Version\": \"0.0.1\", \r\n        \"CMD\": \"AI005\", \r\n   "
					+ "     \"Time\": \"2018-08-28 10:51:22\"\r\n    }, \r\n    \"Body\": {\r\n        \"token\": \"v31Z51S7i5TmCmz\",\r\n      "
					+ "  \"user_login\": \"admin\",\r\n        \"user_pass\": \"123456\", \r\n        "
					+ "\"campaign_id\": \"AI_JR\", \r\n       "
					+ " \"list_id\": \""+lIisID+"\", \r\n        \"action\": \"start_list_id\"\r\n    }\r\n}\r\n");
			Request requestsl = new Request.Builder()
			  .url("http://10.208.133.91/xiaomaiai/action/startListID")
//					.url("http://10.208.134.21/xiaomaiai/action/startListID")
			  .post(bodysl)
			  .addHeader("Content-Type", "application/json")
			  .addHeader("Cache-Control", "no-cache")
			  .addHeader("Postman-Token", "57f5bb88-939e-4574-b702-f35b55c91aab")
			  .build();

			try {
				
				responsesl = clientsl.newCall(requestsl).execute();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			finally {
//				ResponseBody responseBody1 = responsesl.body();
//					responseBody1.close();
//				}
			
			map.put("code", 1);
		}
		
		
		
		

		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
	
	
	}
	
	
	
	@RequestMapping("/putNumberForDC")
	@ResponseBody 
	public void  putNumberForDC(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
	
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
		String phone_number = httprequest.getParameter("phone_number");
		List<String> arrayPhone = new ArrayList<String>();
		
		long time = System.currentTimeMillis();			// 这个获取系统时间
		String listName = String.valueOf(time/1000);
		
		String lIisID = getListID(listName,"AI_DC");  
		
		
		if(null!=phone_number&&!("").equals(phone_number)) {
			
			arrayPhone.add(phone_number);
			
			OkHttpClient client = new OkHttpClient();
			client.setReadTimeout(100, TimeUnit.SECONDS);  
			Response response = null;
			try {
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, "{\n    \"Head\": {\n        \"Version\": \"0.0.1\", \n        \"CMD\": \"AI004\", \n    "
					+ "    \"Time\": \"2018-08-28 10:51:22\"\n    }, \n    \"Body\": {\n        \"token\": \"v31Z51S7i5TmCmz\",\n     "
					+ "   \"user_login\": \"admin\",\n        \"user_pass\": \"123456\", \n      "
					+ "  \"campaign_id\": \"AI_DC\", \n      "
					+ "  \"list_id\": \""+lIisID+"\", \n       "
					+ " \"phone_number\": \""+arrayPhone+"\", \n        \"action\": \"add_lead_id\"\n    }\n}\n");
			Request request = new Request.Builder()
			  .url("http://10.208.133.91/xiaomaiai/action/AddLeadID")
//					.url("http://10.208.134.21/xiaomaiai/action/AddLeadID")		
			  .post(body)
			  .addHeader("Content-Type", "application/json")
			  .addHeader("Cache-Control", "no-cache")
			  .addHeader("Postman-Token", "51c61bd3-cfd3-416f-b5a8-e5b4632d3090")
			  .build();
				 response = client.newCall(request).execute();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			finally {
//			ResponseBody responseBody = response.body();
//				responseBody.close();
//			}
			
			
			OkHttpClient clientsl = new OkHttpClient();
			clientsl.setReadTimeout(100, TimeUnit.SECONDS);  
			Response responsesl = null;
			MediaType mediaTypesl = MediaType.parse("application/json");
			RequestBody bodysl = RequestBody.create(mediaTypesl, "{\r\n    \"Head\": {\r\n        \"Version\": \"0.0.1\", \r\n        \"CMD\": \"AI005\", \r\n   "
					+ "     \"Time\": \"2018-08-28 10:51:22\"\r\n    }, \r\n    \"Body\": {\r\n        \"token\": \"v31Z51S7i5TmCmz\",\r\n      "
					+ "  \"user_login\": \"admin\",\r\n        \"user_pass\": \"123456\", \r\n        "
					+ "\"campaign_id\": \"AI_DC\", \r\n       "
					+ " \"list_id\": \""+lIisID+"\", \r\n        \"action\": \"start_list_id\"\r\n    }\r\n}\r\n");
			Request requestsl = new Request.Builder()
			  .url("http://10.208.133.91/xiaomaiai/action/startListID")
//					.url("http://10.208.134.21/xiaomaiai/action/startListID")
			  .post(bodysl)
			  .addHeader("Content-Type", "application/json")
			  .addHeader("Cache-Control", "no-cache")
			  .addHeader("Postman-Token", "57f5bb88-939e-4574-b702-f35b55c91aab")
			  .build();

			try {
				
				responsesl = clientsl.newCall(requestsl).execute();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			finally {
//				ResponseBody responseBody1 = responsesl.body();
//					responseBody1.close();
//				}
			
			map.put("code", 1);
		}
		
		
		
		

		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
	
	
	}
	
	
	
}
