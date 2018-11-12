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

import com.gx.po.EducationCustomer;
import com.gx.po.PhoneNUM;
import com.gx.po.ReportResult;
import com.gx.po.Result;
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
@RequestMapping("/CRMAction")
public class CRMAction {

	
	@Autowired
	public ResultService resultService;
	@Autowired
	public EducationCustomerService educationCustomerService;
	@Autowired
	public PhoneNUMService phoneNUMService;
	@Autowired
	public ReportResultService reportResultService;
	
	@RequestMapping("/getPhoneNumList")
	@ResponseBody 
	public List<String> getPhoneNumList(String listID){
		 
		 String ecsql = "where listID ='"+"180919001"+"' and status ='0'";
			List<String> arrayPhone = new ArrayList<String>();
			List<PhoneNUM> pnList = phoneNUMService.findBySQLforPhoneNUM(ecsql);
			for(PhoneNUM ec :pnList) {
//				ec.getPhoneNumber();
				arrayPhone.add(ec.getPhoneNumber());
				ec.setStatus("1");
				phoneNUMService.updateByPrimaryKeyforPhoneNUM(ec);
			}
		 
			OkHttpClient client = new OkHttpClient();

			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, "{\n    \"Head\": {\n        \"Version\": \"0.0.1\", \n        \"CMD\": \"AI004\", \n    "
					+ "    \"Time\": \"2018-08-28 10:51:22\"\n    }, \n    \"Body\": {\n        \"token\": \"v31Z51S7i5TmCmz\",\n     "
					+ "   \"user_login\": \"admin\",\n        \"user_pass\": \"123456\", \n      "
					+ "  \"campaign_id\": \"AI_TEST\", \n      "
					+ "  \"list_id\": \""+"180919001"+"\", \n       "
					+ " \"phone_number\": \""+arrayPhone+"\", \n        \"action\": \"add_lead_id\"\n    }\n}\n");
			Request request = new Request.Builder()
			  .url("http://10.208.133.91/xiaomaiai/action/AddLeadID")
//					.url("http://10.208.134.21/xiaomaiai/action/AddLeadID")
			  .post(body)
			  .addHeader("Content-Type", "application/json")
			  .addHeader("Cache-Control", "no-cache")
			  .addHeader("Postman-Token", "51c61bd3-cfd3-416f-b5a8-e5b4632d3090")
			  .build();

			try {
				Response response = client.newCall(request).execute();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			OkHttpClient clientsl = new OkHttpClient();

			MediaType mediaTypesl = MediaType.parse("application/json");
			RequestBody bodysl = RequestBody.create(mediaTypesl, "{\r\n    \"Head\": {\r\n        \"Version\": \"0.0.1\", \r\n        \"CMD\": \"AI005\", \r\n   "
					+ "     \"Time\": \"2018-08-28 10:51:22\"\r\n    }, \r\n    \"Body\": {\r\n        \"token\": \"v31Z51S7i5TmCmz\",\r\n      "
					+ "  \"user_login\": \"admin\",\r\n        \"user_pass\": \"123456\", \r\n        \"campaign_id\": \"AI_TEST\", \r\n       "
					+ " \"list_id\": \""+"180919001"+"\", \r\n        \"action\": \"start_list_id\"\r\n    }\r\n}\r\n");
			Request requestsl = new Request.Builder()
			  .url("http://10.208.133.91/xiaomaiai/action/startListID")
//					.url("http://10.208.134.21/xiaomaiai/action/startListID")
			  .post(bodysl)
			  .addHeader("Content-Type", "application/json")
			  .addHeader("Cache-Control", "no-cache")
			  .addHeader("Postman-Token", "57f5bb88-939e-4574-b702-f35b55c91aab")
			  .build();

			try {
				
				Response responsesl = clientsl.newCall(requestsl).execute();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		 return arrayPhone;
	 } 
	 
	
	/*
	 * 返回 listID 的list
	 */
	@RequestMapping("/getASSname")
	@ResponseBody 
	public void  getASSname(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException{  
	
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
		
		String SQL = " GROUP BY assignment_name";
		List<EducationCustomer> rdLIST = educationCustomerService.findBySQLforEducationCustomer(SQL);;
		List<String> idList = new ArrayList<String>();
		for(EducationCustomer dd :rdLIST) {
			idList.add(dd.getAssignment_name());
		}
		
			map.put("code", 1);
			map.put("data", idList);
		
			jsonObject = jsonObject.fromObject(map);
			httpresponse.setCharacterEncoding("UTF-8"); 
			httpresponse.setContentType("text/html;charset=utf-8");
			httpresponse.setHeader("Access-Control-Allow-Origin","*");
			httpresponse.getWriter().print(jsonObject.toString()); 
			
	}
	
	
	@RequestMapping("/findALLGuest")
	@ResponseBody 
	public void findALLGuest(HttpServletRequest httprequest, HttpServletResponse httpresponse) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
		String starttime  = httprequest.getParameter("startTime");
		String endtime  = httprequest.getParameter("endTime");

		String Assignment_name  = httprequest.getParameter("Assignment_name");
		String InterestDegree  = httprequest.getParameter("InterestDegree");
		String status  = httprequest.getParameter("status");
		
		String KeyWords  = httprequest.getParameter("KeyWords");
		
		String campID = httprequest.getParameter("campID");
		
		String sql = "where creation_time > '"+starttime+"'\r\n" + 
				"      and  creation_time < '"+endtime+"'\r\n" ;
		
				if(null!=Assignment_name&&!("").equals(Assignment_name)) {
					sql += " and assignment_name = '"+Assignment_name+"' ";
				}
				
				if(null!=InterestDegree&&!("").equals(InterestDegree)) {
					sql += " and interest_degree = '"+InterestDegree+"' ";
				}
				
				if(null!=status&&!("").equals(status)) {
					sql += " and  status = '"+status+"'";
				}
				if(null!=KeyWords&&!("").equals(KeyWords)) {
					sql += " and CONCAT(parent_name,student_name,city,improve_subject) LIKE '%"+KeyWords+"%'";
				}
//				sql += " and camp_id = '"+campID+"' ORDER BY  creation_time";
		
		List<EducationCustomer> ecList = educationCustomerService.findBySQLforEducationCustomer(sql);
		/*for(EducationCustomer ec :ecList) {
			ReportResult rr = new ReportResult();
			rr.setListID(ec.getList_id());
			rr.setPhoneNumber(ec.getPhone_number());
			String sqlforrr = " where listID ='"+ec.getList_id()+"'and phoneNumber = '"+ec.getPhone_number()+"'" ;
			List<ReportResult> rrList = reportResultService.findBySQL(sqlforrr);
			if(rrList.size()>0) {
				
				ec.setInterest_degree(rrList.get(0).getClientLevel());
				educationCustomerService.updateByPrimaryKeyEducationCustomer(ec);
			}
			
			
		}*/
		
		
		
		
		if(!ecList.isEmpty()){
			map.put("code", 1);
			map.put("data", ecList);
		}else {
			map.put("code", 0);
			map.put("tips", "查询结果不存在");
		}
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		
		httpresponse.getWriter().print(jsonObject.toString()); 
		
		
	}
	
	
	
	@RequestMapping("/updateGuest")
	@ResponseBody 
	public void updateGuest(HttpServletRequest httprequest, HttpServletResponse httpresponse) throws Exception {
	
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;

//		Integer id = Integer.parseInt(httprequest.getParameter("id"));
		
		String id  = httprequest.getParameter("id");
		
//		String phoneNum  = httprequest.getParameter("phoneNum");
		String InterestDegree  = httprequest.getParameter("InterestDegree");
		String city  = httprequest.getParameter("city");
		String parentName  = httprequest.getParameter("parentName");
		String stuName  = httprequest.getParameter("stuName");
		String stuSex  = httprequest.getParameter("stuSex");

		String stuGrade  = httprequest.getParameter("stuGrade");
		String improveSub  = httprequest.getParameter("improveSub");
		String subRecord  = httprequest.getParameter("subRecord");
		String ConvenientTime  = httprequest.getParameter("ConvenientTime");
		String InvitationTime  = httprequest.getParameter("InvitationTime");
	
		String remark  = httprequest.getParameter("remark");
	
		String type_parameter  = httprequest.getParameter("type_parameter");
		String scene_recognition  = httprequest.getParameter("scene_recognition");
		String biggest_problem  = httprequest.getParameter("biggest_problem");
		
		
		
		EducationCustomer ec = educationCustomerService.selectByPrimaryKeyforEducationCustomer(Integer.parseInt(id));
		
//		ec.setPhone_number(phoneNum);
		ec.setInterest_degree(InterestDegree);
		ec.setCity(city);
		ec.setParent_name(parentName);
		ec.setStudent_name(stuName);
		ec.setStudent_sex(stuSex);
		ec.setStudent_grade(stuGrade);
		ec.setImprove_subject(improveSub);
		ec.setSubject_record(subRecord);
		ec.setConvenient_time(ConvenientTime);
		ec.setInvitation_time(InvitationTime);

		ec.setRemark(remark);
		
		ec.setType_parameter(type_parameter);
		ec.setScene_recognition(scene_recognition);
		ec.setBiggest_problem(biggest_problem);
		
//		if(null!=biggest_problem&&!("").equals(biggest_problem))
		
		
		

		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		 String nowTime = df.format(new Date());// new Date()为获取当前系统时间
		 ec.setUpdate_time(nowTime);
		
		 String sql = " where phoneNumber = '"+ec.getPhone_number()+"'  and listID ='"+ec.getList_id()+"'" ;
		 List<ReportResult> newrr = reportResultService.findBySQL(sql);
		 ReportResult rr = newrr.get(0);
		 rr.setClientLevel(InterestDegree);
		 reportResultService.updateByPrimaryKey(rr);
		 
		educationCustomerService.updateByPrimaryKeyEducationCustomer(ec);
		
		map.put("code", 1);
		map.put("data", ec);
		
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		
		httpresponse.getWriter().print(jsonObject.toString()); 
		
	}
	
	@RequestMapping("/findByIDGuest")
	@ResponseBody 
	public void findByIDGuest(HttpServletRequest httprequest, HttpServletResponse httpresponse) throws Exception {
	
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;

//		Integer id = Integer.parseInt(httprequest.getParameter("id"));
		String id  = httprequest.getParameter("id");
		
		EducationCustomer ec = educationCustomerService.selectByPrimaryKeyforEducationCustomer(Integer.parseInt(id));
		
		String sql = " where listID='"+ec.getList_id()+"' and phoneNumber='"+ec.getPhone_number()+"'";
		List<ReportResult> newrrList = reportResultService.findBySQL(sql);
		if(newrrList.size()>0) {
			String aiT = newrrList.get(0).getAiText();
			Map<String, String> mapForQA = new HashMap<String, String>();
			if(null!=aiT) {
			String[] aiList = aiT.split(";");
 			
			for(int x = 0;x<aiList.length;x++) {
				Result result= resultService.selectByPrimaryKey(aiList[x]);
				mapForQA.put("Queation"+x, result.getAnswer());
				mapForQA.put("Answer"+x, result.getQuestion());
				mapForQA.put("Time"+x, result.getCreatedTime());
			}
//			rr.setMapforQA(mapForQA);
			
//			List<Result> rList = resultService.
			}
			map.put("mapForQA", mapForQA);
		}
			
		
		map.put("code", 1);
		map.put("data", ec);
		
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		
		httpresponse.getWriter().print(jsonObject.toString()); 
	
	}
	
	
	
	
	
}
