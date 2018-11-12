package com.gx.web;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import jdk.management.resource.internal.inst.ServerSocketChannelImplRMHooks;
import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.gx.po.BrainPushDTO;
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.druid.sql.visitor.functions.Insert;
//import com.alibaba.fastjson.JSON;
import com.gx.po.Detail;
import com.gx.po.EducationCustomer;
import com.gx.po.HitDTO;
import com.gx.po.InteractionDTO;
import com.gx.po.ReportDate;
import com.gx.po.ReportDetail;
import com.gx.po.ReportResult;
import com.gx.po.Result;
import com.gx.service.DateService;
import com.gx.service.DetailService;
import com.gx.service.EducationCustomerService;
import com.gx.service.ReportDateService;
import com.gx.service.ReportDetailService;
import com.gx.service.ReportResultService;
import com.gx.service.ResultService;
import com.gx.service.impl.ResultServiceImpl;

@Controller
@RequestMapping("/OperationController")
public class OperationAction {

	@Autowired
	public ResultService resultService;
	@Autowired
	public ReportResultService reportResultService;
	@Autowired
	public ReportDetailService reportDetailService;
	@Autowired
	public ReportDateService reportDateService;
	@Autowired
	public DateService dateService;
	@Autowired
	public DetailService detailService;
	@Autowired
	public EducationCustomerService educationCustomerService;
	
	
	
	
	@RequestMapping("/setDetail")
	@ResponseBody 
	public void setDetail() throws Exception {
		
		String _sql ="call proc_api_auto_calldetail_bylist_report('AI_TEST',0,0)";
		Connection conn = getCon();
		Statement stmt = conn.createStatement();
	
		ResultSet rs = stmt.executeQuery(_sql);//选择import java.sql.ResultSet;
		while(rs.next()){//如果对象中有数据，就会循环打印出来
			Detail d = new Detail();
			d.setCampaign_id(rs.getString("Campaign ID"));
			d.setPhone_number(rs.getString("Phone Number"));
			d.setList_id(rs.getString("List ID"));
			String startT = rs.getString("Call Start Time");
			d.setStart_date(startT.substring(0, startT.length()-2));
			//根据 campid，listid，startTime，phonenum 找到对象
			Detail detail = detailService.findBySQLforDetailByCLSP(d);
			if(detail==null){
//				detail = new Detail();
//				d.setCampaignID(rs.getString("Campaign ID"));
//				d.setListidID(rs.getString("List ID"));
//				String startT = rs.getString("Call Start Time");
//				d.setCall_start_time(startT.substring(0, startT.length()-2));
//				d.setPhone_number(rs.getString("Phone Number"));
				String endT = rs.getString("Call End Time");
				d.setEnd_date(endT.substring(0, endT.length()-2));
				d.setStatus(rs.getString("Lead Status"));
				d.setCall_time(rs.getString("Call Time"));
				d.setRing_time(rs.getString("Ring Time"));
				d.setTalk_time(rs.getString("Talk Time"));
				
				detailService.insert(d);
			}
		}
		
	}
	
	
	@RequestMapping("/setNNN")
	@ResponseBody 
	public void setNNN() throws Exception {
		
		
//		18291177
//		18291175
//		18291173
		 Detail result = new Detail();
		 result.setLead_id("18291177");
		
		Detail resultDetail = detailService.findBySQLforDetailByULS(result);
		
//		System.out.println(resultDetail.getStart_date());
		String nn = resultDetail.getStart_date().replace("T", " ");
		System.out.println(nn);
	}
	
	

	@RequestMapping("/setDetailNEW")
	@ResponseBody 
	public void setDetailNEW() throws IOException {
		
		//获取当前时间
		Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取String类型的时间
        String createdate = sdf.format(date);

		
		List<String> newList = getListIDbyReportDate();
		for(int x = 0;x<newList.size();x++) {
			
//			if(x<1) {
			
			OkHttpClient client = new OkHttpClient();

			client.setReadTimeout(100, TimeUnit.SECONDS);  
			
			MediaType mediaType = MediaType.parse("application/octet-stream");
			RequestBody body = RequestBody.create(mediaType, "{\r\n    \"Head\": {\r\n        \"Version\": \"0.0.1\", \r\n        \"CMD\": \"AI008\", \r\n        \"Time\": \""+createdate+"\"\r\n    }, \r\n    \"Body\": {\r\n        \"token\": \"v31Z51S7i5TmCmz\",\r\n        \"user_login\": \"admin\",\r\n        \"user_pass\": \"123456\", \r\n        \"campaign_id\": \"AI_TEST\", \r\n       "
					+ " \"list_id\": \""
					+ newList.get(x)
					+ "\", \r\n        \"action\": \"list_call_result\"\r\n    }\r\n}\r\n");
			Request request = new Request.Builder()
			  .url("http://10.208.133.91/xiaomaiai/query/ListStatusResult")
//					.url("http://10.208.134.21/xiaomaiai/query/ListStatusResult")
			  .post(body)
			  .addHeader("Content-Type", "application/json")
			  .addHeader("Cache-Control", "no-cache")
			  .addHeader("Postman-Token", "3a191d06-bbe5-439c-a0ff-4a6eb7251993")
			  .build();

			Response response = null;
			try {
				response = client.newCall(request).execute();
				String json = response.body().string();
			
			
				
				Map map1 = JSON.parseObject(json);
				
				Map<String,Object> map1_1 = (Map<String,Object>)map1.get("Body");
				
				String resultss = (String)map1_1.get("msg");
						if(!("").equals(resultss)) {
							
							continue;

							
						}
				
				
			
						
						
			
//			String[] totalResult = json.split("\"result\": \"");
//			String newResult =totalResult[1].substring(0,1);
//			
////			if(("0").equals(newResult)) {
////				continue;
////			}
//			
//			String[] totalDResult = json.split("\"listresult\": \\[\\{");
//			
//			
//			String[] detailResult =totalDResult[1].split("\\}\\],");
			
//			String secResult = newResult.substring(0,detailResult.length() - 3);

					List<JSONArray> resultData =  (List<JSONArray>) map1_1.get("listresult");	
					for(Object ja:resultData) {
						String jsonStr = com.alibaba.fastjson.JSONObject.toJSONString(ja);
						Detail orderInfo = JSON.parseObject(jsonStr, Detail.class);
						String ad =null;
						String sd =null;
						String ed =null;
						
						
						if(null!=orderInfo.getAchieve_date())
						ad = orderInfo.getAchieve_date().replace("T", " ");
						if(null!=orderInfo.getStart_date())
						sd = orderInfo.getStart_date().replace("T", " ");
						if(null!=orderInfo.getEnd_date())
						ed = orderInfo.getEnd_date().replace("T", " ");
						
						orderInfo.setAchieve_date(ad);
						orderInfo.setStart_date(sd);
						orderInfo.setEnd_date(ed);
						
						Detail resultDetail = detailService.findBySQLforDetailByULS(orderInfo);
						 if(null == resultDetail) {
							 
							 detailService.insert(orderInfo);
						 }
						
					}
					
					
//			String[] eachData = null;
		/*	 for(Object ja:resultData) {  
				 
					JSONArray jaa = JSONArray.fromObject(ja);
				 
					@SuppressWarnings("rawtypes")
					Iterator iterator = jaa.iterator();
					while(iterator.hasNext()){  
					    Object next = iterator.next();
					    
					    JSONObject jaC = net.sf.json.JSONObject.fromObject(next);
//					    String docId = (String) jaC.get("id");
//					    String docTitle = (String) jaC.get("title");
//					    
					    String sd =  ((String)jaC.get("start_date")).replace("T", " ");
						String ed =  ((String)jaC.get("end_date")).replace("T", " ");
						if(null!=jaC.get("achieve_date")) {
							
							System.out.println("1");
							
							
						}
							String ad = (String)jaC.get("achieve_date");
//						if(null!=jaC.get("achieve_date")) {
//							ad =  ((String)jaC.get("achieve_date")).replace("T", " ");
//						}
					    
					    
						 Detail result =new Detail();
						 result.setCampaign_id((String)jaC.get("campaign_id"));
						 result.setList_id((String)jaC.get("list_id"));
						 result.setLead_id((String)jaC.get("lead_id"));
						 result.setStart_date(sd);
						 result.setEnd_date(ed);
						 result.setPhone_number((String)jaC.get("phone_number"));
						 result.setUniqueid((String)jaC.get("uniqueid"));
						 result.setStatus((String)jaC.get("status"));
//						 result.setAchieve_date(ad);
						 result.setCall_time((String)jaC.get("call_time"));
						 result.setRing_time((String)jaC.get("ring_time"));
						 result.setTalk_time((String)jaC.get("talk_time"));
						 result.setDownload((String)jaC.get("download"));
						 
						 
						 Detail resultDetail = detailService.findBySQLforDetailByULS(result);
						 if(null == resultDetail) {
							 
							 detailService.insert(resultDetail);
						 }
					}*/
					
					
//				 campaign_id;
//				    private String list_id;
//				    private String lead_id;
//				    private String start_date;
//				    private String end_date;
//				    private String phone_number;
//				    private String uniqueid;
//				    private String status;
//				    private String achieve_date;
//				    private String call_time;
//				    private String ring_time;
//				    private String talk_time;
//				    private String download;
				 
//				 Detail result =new Detail();
//				 result.setCampaign_id(jaa.get("campaign_id"));
				 
				 
//				 System.out.println(ja.get);
//				 data.split("\\,");
				 //通过com.alibaba.fastjson.JSON; 将json格式的 String 转化成 Bean对象 
//				 Detail result = JSONObject.parseObject(job.toString(), Detail.class);// jsonString转为java对象
				 

//				 Detail resultDetail = detailService.findBySQLforDetailByULS(ja);
//				 if(null == resultDetail) {
//					String sd =  ja.getStart_date().replace("T", " ");
//					String ed =  ja.getEnd_date().replace("T", " ");
//					String ad =  ja.getAchieve_date().replace("T", " ");
//					
//					ja.setStart_date(sd);
//					ja.setEnd_date(ed);
//					ja.setAchieve_date(ad);
//					detailService.insert(ja);
//				 }
				 
				 
//		        }  
			 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
//			}
//				finally {
//				ResponseBody responseBody = response.body();
//				responseBody.close();
//			}
			
//			 ResponseBody responseBody = response.body();
//				responseBody.close();
			
		}
		
		
		
		
		
	}
	
	
	
	
	
	@RequestMapping("/setDate")
	@ResponseBody 
	public void setDate() throws Exception {
		Connection conn = getCon();
		Statement stmt = conn.createStatement();
		
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		
		 Calendar c = Calendar.getInstance();
	        c.setTime(currentTime);
	        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
	 
	        Date tomorrow = c.getTime();
		
		String createdate = formatter.format(tomorrow);
		String sql = "call proc_api_auto_callresult_sum_bydate_report('2018/04/01','"+createdate+"','AI_TEST')";
		ResultSet rs = stmt.executeQuery(sql);//选择import java.sql.ResultSet;
		while(rs.next()){//如果对象中有数据，就会循环打印出来
//			System.out.println(rs.getString("Date"));
//			System.out.println(rs.getString("List ID"));
//			d.setCampaignID(rs.getString("Campaign Id"));
			
			com.gx.po.Date d = new com.gx.po.Date();
			d.setCampID(rs.getString("Campaign Id"));
			d.setListID(rs.getString("List ID"));
			d.setDate(rs.getString("Date"));
			
			com.gx.po.Date dd = dateService.findBySQLforCLD(d);
			if(dd == null) {
				d.setTotal_calls(rs.getString("Total Calls"));
				d.setAnswers(rs.getString("Answers"));
				d.setAnswer_rate(rs.getString("Answer Rate"));
				d.setNo_answers(rs.getString("No Answers"));
				d.setNo_answer_rate(rs.getString("No Answer Rate"));
				
				dateService.insert(d);
			}
			
		}
	}
	
	
	/*
	 * 33数据库 detail表存到本地
	 */
	@RequestMapping("/getDetailByCLSP")
	@ResponseBody 
	public void  getDetailByCLSP(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
		
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		String starttime  = httprequest.getParameter("startTime");
		String endtime  = httprequest.getParameter("endTime");

		String listID  = httprequest.getParameter("listidID");
		String lead_status  = httprequest.getParameter("lead_status");
		
		String campID = httprequest.getParameter("campID");
		
		
		if(starttime==null||endtime==null) {
			
			map.put("code", 0);
			map.put("tips", "查询结果,选择正确的时间");
			
		}else if(listID == null) {
			
			map.put("code", 0);
			map.put("tips", "查询结果,请选择表ID");
			
		}else{
			
			starttime+=" 00:00:00";
			endtime+=" 23:59:59";
			
			String SQL = " where  call_start_time   > '"+starttime+"' and call_end_time < '"+endtime+"' ";
			if(lead_status == "") {
				SQL +=" and lead_status in ('Answer','No Answer')";
			}else if(lead_status.equals("0")) {
				SQL +=" and lead_status in ('No Answer')";
			}else if(lead_status.equals("1")) {
				SQL +=" and lead_status in ('Answer')";
			}
			SQL +=" and campaign_id ='"+campID+"' order by STR_TO_DATE(call_end_time,'%Y-%m-%d %H:%i:%s') desc";
			
			List<Detail> rdLIST = detailService.findBySQL(SQL);
			if(!rdLIST.isEmpty()){
				map.put("code", 1);
				map.put("data", rdLIST);
			}else {
				map.put("code", 0);
				map.put("tips", "查询结果不存在");
			}
			
		}
		
		
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
		
		
	}
	
	
	
	/*
	 * 33数据库 date表存到本地
	 */
	@RequestMapping("/getDateByCLD")
	@ResponseBody 
	public void  getDateByCLD(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
	
	
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		String starttime  = httprequest.getParameter("startTime");
		String endtime  = httprequest.getParameter("endTime");
		
		String listID  = httprequest.getParameter("listID");
		
		String campID = httprequest.getParameter("campID");
		
		if(starttime==null||endtime==null) {
			
			map.put("code", 0);
			map.put("tips", "查询结果,选择正确的时间");
			
		}else{
			
			String SQL = " where";
				
				if(listID!=null) {
					SQL+=	 " listID ='"+listID+"' and  ";
				}
					
					SQL+=" campID ='"+campID+"' and  STR_TO_DATE(date,'%Y-%m-%d')   between '"+starttime+"' and '"+endtime+"' "
							+" order by STR_TO_DATE(date,'%Y-%m-%d') desc";
			
				
				
			
			List<com.gx.po.Date> rdLIST = dateService.findBySQL(SQL);
			
			for(com.gx.po.Date dd :rdLIST) {
				
				String pn_tt = setPotentialNumberIntoCLD(dd.getDate(), dd.getListID());
				String[] ptNumber = pn_tt.split(";");
				
//				int pn = Integer.parseInt(ptNumber[0]);
//				int tt = Integer.parseInt(ptNumber[1]);
//				
//				float num= (float)pn/tt;   
//				DecimalFormat df = new DecimalFormat("0.00");//格式化小数   
//				String s = df.format(num);//返回的是String类型 
//				
//				double value = Double.valueOf(s.toString());
//				DecimalFormat dfpercent = new DecimalFormat("0.00%");
//				String r = dfpercent.format(value);
//				
//				dd.setPotential_customer(ptNumber[0]);
//				dd.setPotential_customer_rate(r);
				
				double member = Double.parseDouble(ptNumber[0]);
				double denominator = Double.parseDouble(ptNumber[1]);
				
				
				double percent = member / denominator;

				//输出一下，确认你的小数无误
//				System.out.println("小数：" + percent);

				//获取格式化对象
				NumberFormat nt = NumberFormat.getPercentInstance();

				//设置百分数精确度2即保留两位小数
//				nt.setMinimumFractionDigits(2);
				
				//最后格式化并输出
//				System.out.println("百分数：" + nt.format(percent));
				
				String pcr = "";
				if(0 == Integer.parseInt(ptNumber[0])) {
					pcr = "0%";
				}else {
					pcr = nt.format(percent);
				}
				
				dd.setPotential_customer(ptNumber[0]);
				dd.setPotential_customer_rate(pcr);
				
				
				
			}
			
			
			if(!rdLIST.isEmpty()){
				map.put("code", 1);
				map.put("data", rdLIST);
			}else {
				map.put("code", 0);
				map.put("tips", "查询结果不存在");
			}
			
		}
		
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
		
		
	}
	
	
	
	/*
	 * 仪表盘 查询 reportDATE  报表接口
	 */
	@RequestMapping("/getEachTimebyReportDate")
	@ResponseBody 
	public void  getEachTimebyReportDate(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException{  
	
	
		String campID = httprequest.getParameter("campID");
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
        //我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取String类型的时间
        String createdate = sdf.format(date);
		int day = Integer.parseInt(createdate.split("-")[2]);
		String time = createdate.split("-")[0]+"-"+createdate.split("-")[1];
		
		
		String sqlFORdate = 
				"WHERE " + 
				"  campID='"+campID+"' and 	date LIKE '"+time+"%' "+
						"GROUP BY " + 
						"	SUBSTRING(date, 9, 2)";
		
		
		List<ReportDate> rdList = reportDateService.findBySQL(sqlFORdate);
		Map<String, String> mapforD = new HashMap<String, String>();
		List<String> dList = new ArrayList<String>();
		
		if(!rdList.isEmpty()) {
			for(ReportDate rd:rdList) {
				String month = rd.getEachmonth();
				if(month.substring(0,1).equals("0"))
					month= month.substring(1, month.length());
				mapforD.put(month, rd.getCountNUM());
				dList.add(month);
			}
			
			for(int i =1;i<=day;i++) {
				if(!dList.contains(String.valueOf(i))) {
					mapforD.put(String.valueOf(i), "0");
				}
			}
			
			map.put("code", 1);
			
		}else {
			for(int i =1;i<=day;i++) {
					mapforD.put(String.valueOf(i), "0");
			}
			map.put("code", 0);
			
		}
		
		map.put("data",mapforD);
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
	
	}
	
	
	
	/*
	 * 仪表盘 查询 reportDETAIL 报表接口 
	 */
	@RequestMapping("/getEachTimebyReportDetail")
	@ResponseBody 
	public void  getEachTimebyReportDetail(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException{  
		
		
		String campID = httprequest.getParameter("campID");
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
//		String time = "2018-05-25";
		
		   //我要获取当前的日期
        Date date = new Date();
//        设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        获取String类型的时间
        String time = sdf.format(date);
		
		//查询结果为  每天，每小时的条数统计，查询条件为 当天日期！
		String sqlFORtoday = 
				"WHERE " + " campaign_id ='"+campID+"' and "+
				"	end_date LIKE '"+time+"%' "+
						"GROUP BY " + 
						"	SUBSTRING(end_date, 12, 2)";
		//查询结果为 每天，每小时的应答条数统计
		String sqlFORtodayAnswer = 
				"WHERE " + " campaign_id ='"+campID+"' and "+
				"	end_date LIKE '"+time+"%'  and status= 'ANSWER' " +
						"GROUP BY " + 
						"	SUBSTRING(end_date, 12, 2)";
		 
		
		List<ReportDetail> rdLISTforToday = reportDetailService.findBySQLforEachTime(sqlFORtoday);
		List<ReportDetail> rdLISTforTodayAnswer = reportDetailService.findBySQLforEachTime(sqlFORtodayAnswer);
		Map<String, String> mapforT = new HashMap<String, String>();
		Map<String, String> mapforTA = new HashMap<String, String>();
		List<String> tList = new ArrayList<String>();
		List<String> taList = new ArrayList<String>();

		
		if(!rdLISTforToday.isEmpty()) {
			for(ReportDetail rdT:rdLISTforToday) {
				/*mapforT.put(rdT.getEachtime(), rdT.getEachcount());
				tList.add(rdT.getEachtime());
				
				*/
				String month = rdT.getEachtime();
				if(month.substring(0,1).equals("0"))
					month= month.substring(1, month.length());
				mapforT.put(month,  rdT.getEachcount());
				tList.add(month);
				
				
			}
			
			for(int i= 0;i<24;i++) {
				
				if(!tList.contains(String.valueOf(i))) {
					mapforT.put(String.valueOf(i), "0");
				}
			}			
			
			
			map.put("code", 1);
		}else {
			map.put("code", 0);
			for(int i= 0;i<24;i++) {
				
					mapforT.put(String.valueOf(i), "0");
			}	
			
			
//			map.put("tips","日外呼统计查询结果为空");
		}
		map.put("TodayData",mapforT);
		
		if(!rdLISTforTodayAnswer.isEmpty()) {
			for(ReportDetail rdTA:rdLISTforTodayAnswer) {
				mapforTA.put(rdTA.getEachtime(), rdTA.getEachcount());
				taList.add(rdTA.getEachtime());
			}
			
			for(int x= 0;x<24;x++) {
				
				if(!taList.contains(String.valueOf(x))) {
					mapforTA.put(String.valueOf(x), "0");
				}
				
			}
			
			
			
			map.put("codeforAnswer", 1);
		}else {
			map.put("codeforAnswer", 0);
			for(int i= 0;i<24;i++) {
				
					mapforTA.put(String.valueOf(i), "0");
				
			}
			
//			map.put("tipsforAnswer","日应答统计查询结果为空");
		}
		map.put("TodayDataAnswer",mapforTA);
		
		
//		map.put("TodayDataAnswer",rdLISTforTodayAnswer);
		
		
//		System.out.println(rdLIST);
		
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
	}
	
	
	/*
	 * 返回 listID 的list
	 */
	@RequestMapping("/getAIRDlistbyReportDate")
	@ResponseBody 
	public void  getAIRDlistbyReportDate(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException{  
	
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
		String campID = httprequest.getParameter("campID");
		
		String SQL = "where campID ='"+campID+"' GROUP BY listID";
		List<com.gx.po.Date> rdLIST = dateService.findBySQL(SQL);
		List<String> idList = new ArrayList<String>();
		for(com.gx.po.Date dd :rdLIST) {
			idList.add(dd.getListID());
		}
		
			map.put("code", 1);
			map.put("data", idList);
		
			jsonObject = jsonObject.fromObject(map);
			httpresponse.setCharacterEncoding("UTF-8"); 
			httpresponse.setContentType("text/html;charset=utf-8");
			httpresponse.setHeader("Access-Control-Allow-Origin","*");
			httpresponse.getWriter().print(jsonObject.toString()); 
			
	}
	
	public List<String> getListIDbyReportDate(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//      获取String类型的时间
		String time = sdf.format(new Date());
		
		String SQL = " where date='"+time+"' GROUP BY listID  order by date desc";
		List<com.gx.po.Date> rdLIST = dateService.findBySQL(SQL);
		List<String> idList = new ArrayList<String>();
		for(com.gx.po.Date dd :rdLIST) {
			idList.add(dd.getListID());
		}
		return idList;
		
	}
	
	

//	/*
//	 * 任务汇总报表接口 
//	 */
//	@RequestMapping("/getAIRDbyReportDate")
//	@ResponseBody 
//	public void  getAIRDbyReportDate(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException{  
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		JSONObject jsonObject = null;
//		String starttime  = httprequest.getParameter("startTime");
//		String endtime  = httprequest.getParameter("endTime");
//		
//		String listID  = httprequest.getParameter("listID");
//		
//		if(starttime==null||endtime==null) {
//			
//			map.put("code", 0);
//			map.put("tips", "查询结果,选择正确的时间");
//			
//		}else{
//			
//			String SQL = " where";
//				
//				if(listID!=null) {
//					SQL+=	 " listID ='"+listID+"' and  ";
//				}
//					
//					SQL+=" STR_TO_DATE(date,'%Y-%m-%d')   between '"+starttime+"' and '"+endtime+"' "
//							+" order by STR_TO_DATE(date,'%Y-%m-%d') desc";
//			
//				
//				
//			
//			List<com.gx.po.Date> rdLIST = dateService.findBySQL(SQL);
//			
//			if(!rdLIST.isEmpty()){
//				map.put("code", 1);
//				map.put("data", rdLIST);
//			}else {
//				map.put("code", 0);
//				map.put("tips", "查询结果不存在");
//			}
//			
//		}
//		
//		jsonObject = jsonObject.fromObject(map);
//		httpresponse.setCharacterEncoding("UTF-8"); 
//		httpresponse.setContentType("text/html;charset=utf-8");
//		httpresponse.setHeader("Access-Control-Allow-Origin","*");
//		httpresponse.getWriter().print(jsonObject.toString()); 
//		
//		
//		
//	}
	
	
	/*
	 * 任务明细报表报表接口
	 */
//	@RequestMapping("/getAIRDbyReportDetail")
//	@ResponseBody 
//	public void  getAIRDbyReportDetail(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException{  
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		JSONObject jsonObject = null;
//		String starttime  = httprequest.getParameter("startTime");
//		String endtime  = httprequest.getParameter("endTime");
//
//		String listID  = httprequest.getParameter("listidID");
//		String lead_status  = httprequest.getParameter("lead_status");
//		
//		
//		if(starttime==null||endtime==null) {
//			
//			map.put("code", 0);
//			map.put("tips", "查询结果,选择正确的时间");
//			
//		}else if(listID == null) {
//			
//			map.put("code", 0);
//			map.put("tips", "查询结果,请选择表ID");
//			
//		}else{
//			
//			starttime+=" 00:00:00";
//			endtime+=" 23:59:59";
//			
//			String SQL = " where  call_start_time   > '"+starttime+"' and call_end_time < '"+endtime+"' ";
//			if(lead_status == "") {
//				SQL +=" and lead_status in ('Answer','No Answer')";
//			}else if(lead_status.equals("0")) {
//				SQL +=" and lead_status in ('No Answer')";
//			}else if(lead_status.equals("1")) {
//				SQL +=" and lead_status in ('Answer')";
//			}
//			SQL +=" order by STR_TO_DATE(call_end_time,'%Y-%m-%d %H:%i:%s') desc";
//			
//			List<Detail> rdLIST = detailService.findBySQL(SQL);
//			if(!rdLIST.isEmpty()){
//				map.put("code", 1);
//				map.put("data", rdLIST);
//			}else {
//				map.put("code", 0);
//				map.put("tips", "查询结果不存在");
//			}
//			
//		}
//		
//		
//		jsonObject = jsonObject.fromObject(map);
//		httpresponse.setCharacterEncoding("UTF-8"); 
//		httpresponse.setContentType("text/html;charset=utf-8");
//		httpresponse.setHeader("Access-Control-Allow-Origin","*");
//		httpresponse.getWriter().print(jsonObject.toString()); 
//		
//		
//	}
	
	
	
	
	
	/*
	 * 前台通过接口，传递时间和关键字 post,查询 ai_result表
	 * 获取查询数据，并且返回列表
	 */
	@RequestMapping("/getAIResultbySearch")
	@ResponseBody 
	public void  getAIResultbySearch(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException{  
		
		//获取前段json 传来的 DATA
		String starttime  = httprequest.getParameter("startTime");
		String endtime  = httprequest.getParameter("endTime");
		String keyword  = httprequest.getParameter("keyword");
		
		String SQL = " where  STR_TO_DATE(createdTime,'%Y-%m-%d %H:%i:%s')   between '" + starttime+" 00:00:00' and '"+endtime+" 23:59:59'";
		if(keyword!=null) {
			SQL +=" and answer LIKE '%"+keyword+"%' or question like '%"+keyword+"%'";
		}
		
		SQL +=" order by STR_TO_DATE(createdTime,'%Y-%m-%d %H:%i:%s') desc";
		
		
				
		//根据sql语句查找到 符合条件的数据
		List<Result> resultList = resultService.findBySQL(SQL);
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		if(!resultList.isEmpty()){
			map.put("code", 1);
			map.put("data", resultList);
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
	
	
	private final String[] yesLine = {"是","时","市","事","湿","室","式","史","始","拾","使","饰","失","诗","试","世","十","师","石","实","士","氏","视","食","屎","似","施","识","仕","势","示","狮","释","适","侍","矢","逝","誓","匙","嗜","谥","蚀","四","死","司","思","伺","嗣","饲","肆","撕","寺","丝","斯","祀","西","系","洗","细","希","喜","戏","吸","溪","熙","锡","席","习","息","夕","兮","熙","曦","稀","溪","袭","术","书","数","熟","树","输","叔","舒","属","是的","时的","市的","事的","湿的","室的","式的","史的","始的","拾的","使的","饰的","失的","诗的","试的","世的","十的","师的","石的","实的","士的","氏的","视的","食的","屎的","似的","施的","识的","仕的","势的","示的","狮的","释的","适的","侍的","矢的","逝的","誓的","匙的","嗜的","谥的","蚀的","四的","死的","司的","思的","伺的","嗣的","饲的","肆的","撕的","寺的","丝的","斯的","祀的","西的","系的","洗的","细的","希的","喜的","戏的","吸的","溪的","熙的","锡的","席的","习的","息的","夕的","兮的","熙的","曦的","稀的","溪的","袭的","术的","书的","数的","熟的","树的","输的","叔的","舒的","属的","是地","时地","市地","事地","湿地","室地","式地","史地","始地","拾地","使地","饰地","失地","诗地","试地","世地","十地","师地","石地","实地","士地","氏地","视地","食地","屎地","似地","施地","识地","仕地","势地","示地","狮地","释地","适地","侍地","矢地","逝地","誓地","匙地","嗜地","谥地","蚀地","四地","死地","司地","思地","伺地","嗣地","饲地","肆地","撕地","寺地","丝地","斯地","祀地","西地","系地","洗地","细地","希地","喜地","戏地","吸地","溪地","熙地","锡地","席地","习地","息地","夕地","兮地","熙地","曦地","稀地","溪地","袭地","术地","书地","数地","熟地","树地","输地","叔地","舒地","属地","是啊","时啊","市啊","事啊","湿啊","室啊","式啊","史啊","始啊","拾啊","使啊","饰啊","失啊","诗啊","试啊","世啊","十啊","师啊","石啊","实啊","士啊","氏啊","视啊","食啊","屎啊","似啊","施啊","识啊","仕啊","势啊","示啊","狮啊","释啊","适啊","侍啊","矢啊","逝啊","誓啊","匙啊","嗜啊","谥啊","蚀啊","四啊","死啊","司啊","思啊","伺啊","嗣啊","饲啊","肆啊","撕啊","寺啊","丝啊","斯啊","祀啊","西啊","系啊","洗啊","细啊","希啊","喜啊","戏啊","吸啊","溪啊","熙啊","锡啊","席啊","习啊","息啊","夕啊","兮啊","熙啊","曦啊","稀啊","溪啊","袭啊","术啊","书啊","数啊","熟啊","树啊","输啊","叔啊","舒啊","属啊","是我","时我","市我","事我","湿我","室我","式我","史我","始我","拾我","使我","饰我","失我","诗我","试我","世我","十我","师我","石我","实我","士我","氏我","视我","食我","屎我","似我","施我","识我","仕我","势我","示我","狮我","释我","适我","侍我","矢我","逝我","誓我","匙我","嗜我","谥我","蚀我","四我","死我","司我","思我","伺我","嗣我","饲我","肆我","撕我","寺我","丝我","斯我","祀我","西我","系我","洗我","细我","希我","喜我","戏我","吸我","溪我","熙我","锡我","席我","习我","息我","夕我","兮我","熙我","曦我","稀我","溪我","袭我","术我","书我","数我","熟我","树我","输我","叔我","舒我","属我","我是","我时","我市","我事","我湿","我室","我式","我史","我始","我拾","我使","我饰","我失","我诗","我试","我世","我十","我师","我石","我实","我士","我氏","我视","我食","我屎","我似","我施","我识","我仕","我势","我示","我狮","我释","我适","我侍","我矢","我逝","我誓","我匙","我嗜","我谥","我蚀","我四","我死","我司","我思","我伺","我嗣","我饲","我肆","我撕","我寺","我丝","我斯","我祀","我西","我系","我洗","我细","我希","我喜","我戏","我吸","我溪","我熙","我锡","我席","我习","我息","我夕","我兮","我熙","我曦","我稀","我溪","我袭","我术","我书","我数","我熟","我树","我输","我叔","我舒","我属","握是","握时","握市","握事","握湿","握室","握式","握史","握始","握拾","握使","握饰","握失","握诗","握试","握世","握十","握师","握石","握实","握士","握氏","握视","握食","握屎","握似","握施","握识","握仕","握势","握示","握狮","握释","握适","握侍","握矢","握逝","握誓","握匙","握嗜","握谥","握蚀","握四","握死","握司","握思","握伺","握嗣","握饲","握肆","握撕","握寺","握丝","握斯","握祀","握西","握系","握洗","握细","握希","握喜","握戏","握吸","握溪","握熙","握锡","握席","握习","握息","握夕","握兮","握熙","握曦","握稀","握溪","握袭","握术","握书","握数","握熟","握树","握输","握叔","握舒","握属","是哦","时哦","市哦","事哦","湿哦","室哦","式哦","史哦","始哦","拾哦","使哦","饰哦","失哦","诗哦","试哦","世哦","十哦","师哦","石哦","实哦","士哦","氏哦","视哦","食哦","屎哦","似哦","施哦","识哦","仕哦","势哦","示哦","狮哦","释哦","适哦","侍哦","矢哦","逝哦","誓哦","匙哦","嗜哦","谥哦","蚀哦","四哦","死哦","司哦","思哦","伺哦","嗣哦","饲哦","肆哦","撕哦","寺哦","丝哦","斯哦","祀哦","西哦","系哦","洗哦","细哦","希哦","喜哦","戏哦","吸哦","溪哦","熙哦","锡哦","席哦","习哦","息哦","夕哦","兮哦","熙哦","曦哦","稀哦","溪哦","袭哦","术哦","书哦","数哦","熟哦","树哦","输哦","叔哦","舒哦","属哦","是呀","时呀","市呀","事呀","湿呀","室呀","式呀","史呀","始呀","拾呀","使呀","饰呀","失呀","诗呀","试呀","世呀","十呀","师呀","石呀","实呀","士呀","氏呀","视呀","食呀","屎呀","似呀","施呀","识呀","仕呀","势呀","示呀","狮呀","释呀","适呀","侍呀","矢呀","逝呀","誓呀","匙呀","嗜呀","谥呀","蚀呀","四呀","死呀","司呀","思呀","伺呀","嗣呀","饲呀","肆呀","撕呀","寺呀","丝呀","斯呀","祀呀","西呀","系呀","洗呀","细呀","希呀","喜呀","戏呀","吸呀","溪呀","熙呀","锡呀","席呀","习呀","息呀","夕呀","兮呀","熙呀","曦呀","稀呀","溪呀","袭呀","术呀","书呀","数呀","熟呀","树呀","输呀","叔呀","舒呀","属呀","是吖","时吖","市吖","事吖","湿吖","室吖","式吖","史吖","始吖","拾吖","使吖","饰吖","失吖","诗吖","试吖","世吖","十吖","师吖","石吖","实吖","士吖","氏吖","视吖","食吖","屎吖","似吖","施吖","识吖","仕吖","势吖","示吖","狮吖","释吖","适吖","侍吖","矢吖","逝吖","誓吖","匙吖","嗜吖","谥吖","蚀吖","四吖","死吖","司吖","思吖","伺吖","嗣吖","饲吖","肆吖","撕吖","寺吖","丝吖","斯吖","祀吖","西吖","系吖","洗吖","细吖","希吖","喜吖","戏吖","吸吖","溪吖","熙吖","锡吖","席吖","习吖","息吖","夕吖","兮吖","熙吖","曦吖","稀吖","溪吖","袭吖","术吖","书吖","数吖","熟吖","树吖","输吖","叔吖","舒吖","属吖","是哎","时哎","市哎","事哎","湿哎","室哎","式哎","史哎","始哎","拾哎","使哎","饰哎","失哎","诗哎","试哎","世哎","十哎","师哎","石哎","实哎","士哎","氏哎","视哎","食哎","屎哎","似哎","施哎","识哎","仕哎","势哎","示哎","狮哎","释哎","适哎","侍哎","矢哎","逝哎","誓哎","匙哎","嗜哎","谥哎","蚀哎","四哎","死哎","司哎","思哎","伺哎","嗣哎","饲哎","肆哎","撕哎","寺哎","丝哎","斯哎","祀哎","西哎","系哎","洗哎","细哎","希哎","喜哎","戏哎","吸哎","溪哎","熙哎","锡哎","席哎","习哎","息哎","夕哎","兮哎","熙哎","曦哎","稀哎","溪哎","袭哎","术哎","书哎","数哎","熟哎","树哎","输哎","叔哎","舒哎","属哎","对","队","堆","怼","兑","埻","度","读","对的","队的","堆的","怼的","兑的","埻的","度的","读的","对地","队地","堆地","怼地","兑地","埻地","度地","读地","对得","队得","堆得","怼得","兑得","埻得","度得","读得","对啊","队啊","堆啊","怼啊","兑啊","埻啊","度啊","读啊","对呀","队呀","堆呀","怼呀","兑呀","埻呀","度呀","读呀","对达","队达","堆达","怼达","兑达","埻达","度达","读达","对哦","队哦","堆哦","怼哦","兑哦","埻哦","度哦","读哦","对喔","队喔","堆喔","怼喔","兑喔","埻喔","度喔","读喔","嗯","呃","恩","正确","正缺","曾雀","没错","美错","请讲","听讲","清剿","请教","请假","请柬","奇想","黔江","金奖","清讲","金强","心想","千家","晴天","请说","请多","没错","美错","么错"};
	private final String[] noLine = {"不","布","补","步","部","簿","吥","捕","卜","部","埠","怖","哺","堡","普","浦","噗","铺","谱","朴","扑","葡","付","服","福","府","不用","布用","补用","步用","部用","簿用","吥用","捕用","卜用","部用","埠用","怖用","哺用","堡用","普用","浦用","噗用","铺用","谱用","朴用","扑用","葡用","付用","服用","福用","府用","不行","布行","补行","步行","部行","簿行","吥行","捕行","卜行","部行","埠行","怖行","哺行","堡行","普行","浦行","噗行","铺行","谱行","朴行","扑行","葡行","付行","服行","福行","府行","不对","布对","补对","步对","部对","簿对","吥对","捕对","卜对","部对","埠对","怖对","哺对","堡对","普对","浦对","噗对","铺对","谱对","朴对","扑对","葡对","付对","服对","福对","府对","不要","布要","补要","步要","部要","簿要","吥要","捕要","卜要","部要","埠要","怖要","哺要","堡要","普要","浦要","噗要","铺要","谱要","朴要","扑要","葡要","付要","服要","福要","府要","不需要","布需要","补需要","步需要","部需要","簿需要","吥需要","捕需要","卜需要","部需要","埠需要","怖需要","哺需要","堡需要","普需要","浦需要","噗需要","铺需要","谱需要","朴需要","扑需要","葡需要","付需要","服需要","福需要","府需要","不方便","布方便","补方便","步方便","部方便","簿方便","吥方便","捕方便","卜方便","部方便","埠方便","怖方便","哺方便","堡方便","普方便","浦方便","噗方便","铺方便","谱方便","朴方便","扑方便","葡方便","付方便","服方便","福方便","府方便"};
	
	
	/*
	 * 接口作用：
	 * 获取第三方接口数据存储 到ai_result
	 * 获取到detail 表数据，并将两个表的数据进行 组合 ，存储到 reportresult表中 
	 */
	@RequestMapping("/insertReportResult")
	@ResponseBody 
	public void  insertReportResult()throws ServletException, IOException, ParseException{  
		
//		getAjaxJsonTest();
		/*SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = new Date();
		String dateString = dft.format(beginDate);*/
		
		/*Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		Date endDate = dft.parse(dft.format(date.getTime()));
		*/
//		String todaySQL="where startTime like '"+dft.format(endDate)+"%'";
//		reportResultService.deleteByDATE(todaySQL);
//		
//		
//		
//		
//		String sqlfordetail = " where STATUS = 'Answer' and start_date like '"+dateString+"%'";
//		List<Detail> detailList = detailService.findBySQL(sqlfordetail);
		
		//更换场景代码
		/*
		String todaySQL="where startTime like '"+dateString+"%'";
		reportResultService.deleteByDATE(todaySQL);
		
		
		
//		String dateString = dft.format(endDate);
		
		String sqlfordetail = " where start_date like '"+dateString+"%'";
		List<Detail> detailList = detailService.findBySQL(sqlfordetail);
		
		
		for(Detail detail :detailList) {
			String resultID = null;
			ReportResult rr = new ReportResult();
			
			String sqlForAI = "where createdTime like '"+(detail.getStart_date()).split(" ")[0]+"%' and fromUser like '%"
					+detail.getPhone_number()+"%' order  by fieldName ";
			
			List<Result> resultList = resultService.findBySQL(sqlForAI);
			if(!resultList.isEmpty()) {
				for(int x = 0;x<resultList.size();x++) {
					if(resultID==null) {
						resultID = (resultList.get(x)).getId();
					}else {
						resultID += ";"+(resultList.get(x)).getId();
					}
					
					if(x==0) {
						rr.setClientLevel("无意向客户");
					}
					
					
					if(x==1) {
						if( (resultList.get(x)).getQuestion().contains("没有")|| (resultList.get(x)).getQuestion().contains("不")) {
							rr.setClientLevel("无意向客户");
						}else if((resultList.get(x)).getQuestion().contains("好的")|| (resultList.get(x)).getQuestion().contains("有")|| (resultList.get(x)).getQuestion().contains("可以")|| (resultList.get(x)).getQuestion().contains("嗯")|| (resultList.get(x)).getQuestion().contains("是的")) {
							rr.setClientLevel("潜在客户");
						}else {
							rr.setClientLevel("一般客户");
						}
						
					}
					
					if(x==2) {
						if(rr.getClientLevel().equals("一般客户")){
							if( (resultList.get(x)).getQuestion().contains("没有")|| (resultList.get(x)).getQuestion().contains("不")) {
								rr.setClientLevel("无意向客户");
							}else if((resultList.get(x)).getQuestion().contains("好的")|| (resultList.get(x)).getQuestion().contains("有")|| (resultList.get(x)).getQuestion().contains("可以")|| (resultList.get(x)).getQuestion().contains("嗯")|| (resultList.get(x)).getQuestion().contains("是的")) {
								rr.setClientLevel("潜在客户");
							}else {
								rr.setClientLevel("一般客户");
							}
						}
					}
					
					
				}
			}
//			System.out.println(resultID);
			
			rr.setListID(detail.getList_id());
			rr.setStatus(detail.getStatus());
			rr.setPhoneNumber(detail.getPhone_number());
			rr.setStartTime(detail.getStart_date());
			rr.setCallTime(detail.getCall_time());
			rr.setRingTime(detail.getRing_time());
			rr.setTalkTime(detail.getTalk_time());
			rr.setRecordDownload(detail.getDownload());
			rr.setAiText(resultID);
			reportResultService.insert(rr);
	}
		
		
		*/
		/*
		 * 1.根据电话和存储时间 在 ai_result表中，找到对应List
		 * 2.根据不同的 回答，建立用户信息，存储到crm中，并且对客户级别进行 识别
		 * 3.根据时间和电话建立新的  ai_report_result 数据。  aiText取得 ai_result的id；
		 * 
		 */
		
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = new Date();
		String dateString = dft.format(beginDate);
//		String dateString = "2018-09-28";
		
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		Date endDate = dft.parse(dft.format(date.getTime()));
		
		String todaySQL="where startTime like '"+dateString+"%'";
		reportResultService.deleteByDATE(todaySQL);
		
//		String sql =" ORDER BY createdTime , fieldName DESC";
//		List<Result> resultList = resultService.findBySQL(sql);
		
		String sqlfordetail = " where STATUS = 'ANSWER' and start_date like '"+dateString+"%'";
		List<Detail> detailList = detailService.findBySQL(sqlfordetail);
		for(Detail detail :detailList) {

			
			String resultID = null;
			ReportResult rr = new ReportResult();
			
			String sqlForAI = "where createdTime like '"+(detail.getStart_date()).split(" ")[0]+"%' and fromUser like '%"
					+detail.getPhone_number()+"%' order  by  createdTime ,fieldName";
			/*
			 * 查询当天，电话下的所有对话的  result文本数据
			 */
			List<Result> resultListnew = resultService.findBySQL(sqlForAI);
			
			if(resultListnew.size()>0) {
				for(int x = 0;x<resultListnew.size();x++) {
					String Word = (resultListnew.get(x)).getQuestion();
					if(resultID==null) {
						resultID = (resultListnew.get(x)).getId();
					}else {
						resultID += ";"+(resultListnew.get(x)).getId();
					}
					String SQL = "where creation_time like '"+(resultListnew.get(x)).getCreatedTime().split(" ")[0]+"%' and phone_number='"+(resultListnew.get(x)).getFromUser()+
							"' and list_id = '"+detail.getList_id()+"'" ;
					List<EducationCustomer> ecList = educationCustomerService.findBySQLforEducationCustomer(SQL);
					if(ecList.size()>0) {
					EducationCustomer ec = ecList.get(0);
							
					/*if(x==0) {
						rr.setClientLevel("无意向客户");
					}*/
					
					/*if(resultListnew.get(x).getAnswer().contains("李     ")) {
						if(-1!= Arrays.binarySearch(yesLine,Word)) {
							rr.setClientLevel("无意向客户");
						}else if(-1!= Arrays.binarySearch(noLine,Word)) {
							rr.setClientLevel("潜在客户");
						}else {
							rr.setClientLevel("一般客户");
						}
						
					}
					
					if(resultListnew.get(x).getAnswer().contains("分享")) {
						if(-1!= Arrays.binarySearch(yesLine,Word)) {
							rr.setClientLevel("无意向客户");
						}else if(-1!= Arrays.binarySearch(noLine,Word)) {
							rr.setClientLevel("潜在客户");
						}else {
							rr.setClientLevel("一般客户");
						}
						
					}*/
					
//					if(resultListnew.get(x).getAnswer().contains("兴趣")||resultListnew.get(x).getAnswer().contains("工作")) {
//						
//						ec.setType_parameter(resultListnew.get(x).getQuestion());
//						
//					}
//					
//					if(resultListnew.get(x).getAnswer().contains("外贸")||resultListnew.get(x).getAnswer().contains("销售")||resultListnew.get(x).getAnswer().contains("旅游")||resultListnew.get(x).getAnswer().contains("社交")) {
//						
//						ec.setScene_recognition(resultListnew.get(x).getQuestion());
//						
//					}
					

					if(resultListnew.get(x).getAnswer().contains("接听我们工作人员的电话")) {
						
						ec.setConvenient_time(resultListnew.get(x).getQuestion());
						
					}
					
					
				/*	if(12==resultListnew.get(x).getFieldName()) {
//						if(-1!= Arrays.binarySearch(yesLine,Word)) {
//							rr.setClientLevel("无意向客户");
//							ec.setInterest_degree("无意向客户");
//						}else if(-1!= Arrays.binarySearch(noLine,Word)) {
							rr.setClientLevel("潜在客户");
							ec.setInterest_degree("潜在客户");
//						}else {
//							rr.setClientLevel("一般客户");
//							ec.setInterest_degree("一般客户");
//						}
						
					}
					
					if(resultListnew.get(x).getAnswer().contains("词汇")) {
						
						ec.setBiggest_problem(resultListnew.get(x).getQuestion());
						
					}*/
					
					educationCustomerService.updateByPrimaryKeyEducationCustomer(ec);
					
					}
					
					
				}
				
			System.out.println(resultID);
			rr.setListID(detail.getList_id());
			rr.setStatus(detail.getStatus());
			rr.setPhoneNumber(detail.getPhone_number());
			rr.setStartTime(detail.getStart_date());
			rr.setCallTime(detail.getCall_time());
			rr.setRingTime(detail.getRing_time());
			rr.setTalkTime(detail.getTalk_time());
			rr.setRecordDownload(detail.getDownload());
			rr.setAiText(resultID);
			reportResultService.insert(rr);
			
			}
			
			
		}
		
		
	}
	
	
	
	/*
	 * 调用方法  将汇总表中的各条数据中 加入潜在客户和 潜在客户率
	 */
	public String setPotentialNumberIntoCLD(String startdate,String listID) throws Exception{
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         Date sDate = sdf.parse(startdate);
         System.out.println("String类型转Date类型 "+sDate);//要实现日期+1 需要String转成Date类型
        
         Format f = new SimpleDateFormat("yyyy-MM-dd");
         System.out.println("Date结束日期:" + f.format(sDate)); 
        
         Calendar c = Calendar.getInstance();  
         c.setTime(sDate);  
         c.add(Calendar.DAY_OF_MONTH, 1);  
         
         sDate = c.getTime();
         String enddate = f.format(sDate);
         
         
         String sql = "";
 		List<ReportResult> rrList = null;
 		if(startdate!=null&&enddate!=null) {
 			sql = " where startTime BETWEEN '"+startdate+"' and '"+enddate+"'";
 			if(listID!=null) {
 				sql += " and listID = '"+listID+"' ";
 			}
 			
 			rrList = reportResultService.findBySQL(sql);
 		}
         
 		int PotentialNumber = 0;
        for(ReportResult rr :rrList) {
        	
        	if(("潜在客户").equals(rr.getClientLevel())) {
        		PotentialNumber += 1;
        	}
        	
        }
 		
        String PN_TT = String.valueOf(PotentialNumber)+";"+rrList.size();
 		
		return PN_TT;
		
	}
	
	
	
	/*
	 * 前台调用
	 * 返回 json
	 */
	@RequestMapping("/getReportResult")
	@ResponseBody 
	public void  getReportResult(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, ParseException{  
		
		
		String starttime  = httprequest.getParameter("startTime");
		String endtime  = httprequest.getParameter("endTime");

		String listID  = httprequest.getParameter("listidID");
		String status  = httprequest.getParameter("status");
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		
		String sql = "";
		List<ReportResult> rrList = null;
		if(starttime!=null&&endtime!=null) {
			sql = " where startTime BETWEEN '"+starttime+"  00:00:00' and '"+endtime+" 23:59:59'";
			if(listID!=null) {
				sql += " and listID = '"+listID+"' ";
			}
			if(status!=null) {
				sql+= " and `status` = '"+status+"'";
			}
			sql+= "  order by startTime desc";
			rrList = reportResultService.findBySQL(sql);
		}else {
			rrList = reportResultService.findAll();
		}
		
		for(ReportResult rr:rrList) { 
			if(null!=rr.getAiText()&&!("").equals(rr.getAiText())) {
				String[] aiList = rr.getAiText().split(";");
				Map<String, String> mapForQA = new HashMap<String, String>();
				for(int x = 0;x<aiList.length;x++) {
					Result result= resultService.selectByPrimaryKey(aiList[x]);
					mapForQA.put("Queation"+x, result.getAnswer());
					mapForQA.put("Answer"+x, result.getQuestion());
					mapForQA.put("Time"+x, result.getCreatedTime());
					
				}
				rr.setMapforQA(mapForQA);
			}
		}
		
		
		map.put("data", rrList);
		
		
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
	}
	
	
//	
//	 @Scheduled(cron = "0/20 * * * * ?") 
//	 public void run() throws Exception { 
////	  System.out.println("推送消息来了");
//	  
//	  try {
//		  
//		setDate();
//		setDetail();
//		getAjaxJsonTest();
//	} catch (ServletException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	  
//	 }
	
	
	
	/*
	 * 接口作用：通过 模拟postman发送的浏览器请求；以json的格式传值。并且获取返回
	 * 解析json并且遍历
	 * 遍历完成后通过com.alibaba.fastjson.JSON;将其组装成 bean对象
	 * 对象存储
	 */
	@RequestMapping("/getAjaxJsonTest")
	@ResponseBody 
	public void  getAjaxJsonTest() {  
//		String endtime  = request.getParameter("endtime");
//	      Map<String, Object> map = new HashMap<String, Object>();
//	       map.put("issure", "ture");
//		
//		System.out.println(endtime);
//		try {
//			doPost("http://localhost:8080/ssmtest2/loginController/getFormdata2.do", map);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		 	Date today = new Date();  
//	        System.out.println("今天是:" + df.format(today));  
	   
	        Calendar c = Calendar.getInstance();  
	        c.setTime(today);  
	        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天  
	   
	        Date tomorrow = c.getTime();  
		
		
		OkHttpClient client = new OkHttpClient();
		client.setReadTimeout(100, TimeUnit.SECONDS); 
		MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
		RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"startTime\"\r\n\r\n2017-04-01 00:00:00\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"endTime\"\r\n\r\n"+df.format(tomorrow)+"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
		Request request = new Request.Builder()
		  .url("http://10.208.134.10:8080/CSRBroker/dialogue")
		  .post(body)
		  .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "e9864309-fbb1-4fe0-bc90-0d34fd0468a7")
		  .build();

		Response response  = null;
		
//获取返回的json数据
		try {
			response = client.newCall(request).execute();
			String json;
			json = response.body().string();
		
		
		
		
		String[] totalResult = json.split("\"result\":");
		String newResult =totalResult[1].substring(2, totalResult[1].length());
		String secResult = newResult.substring(0,newResult.length() - 3);
		String[] eachData = secResult.split("\\}\\,\\{");
		 for(int data = 0; data < eachData.length; data++) {  
	           
//			 System.out.println("{"+eachData[data]+"}");
//			 data.split("\\,");
			 //通过com.alibaba.fastjson.JSON; 将json格式的 String 转化成 Bean对象 
			 Result result = JSON.parseObject("{"+eachData[data]+"}", Result.class);// jsonString转为java对象
			 
//			 if(("alxx_13661699323_98907454031942").equals(result.getFromUser())) {
//				 System.out.println("11231123");
//			 }
			 
//			 String SQL = " where id='" + result.getId()+"'";
			 
//			 if(resultService==null) {
//				 
//				 resultService  = new ResultServiceImpl();
//				 
//			 }
				 Result resultdata = resultService.selectByPrimaryKey(result.getId());
				 
				 if(resultdata==null) {
					 int i= resultService.insert(result);
//				 System.out.println(i);
				 }
				 
			 
			 
//			 result。getId();
	        }  
		 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		Map<String,Object> map = new HashMap<String,Object>();
//
//		
//		 JSONArray objs = new JSONArray(response.body().string());
//		 JSONObject obj = objs.getJSONObject(objs.length()-1);
//		 long lTime = obj.getLong("timePoint");
		
//		System.out.println(json);
		
//		String aa = response1.
	}
	
	
//	private Entity getEntity(String resp){
//        JSONObject jsonObj = (JSONObject) JSON.parse(resp);
//        if (jsonObj.getJSONArray("data") != null) {
//            jsonObj = jsonObj.getJSONArray("data").getJSONObject(0);
//        }
//        jsonObj.remove("******");
//        Entity entity= JSONObject.toJavaObject(jsonObj,Entity.class);
//        return entity;
//    }
	
	
	
	@RequestMapping("/getAjaxJson")
	@ResponseBody 
	public void  getAjaxJson(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{  
//		JSONArray  j = new JSONArray ();  
		System.out.println("eee");
		
//	        String url = "http://10.208.134.10:8080/CSRBroker/dialogue";//调用sendPost方法时传入的外网接口地址
			String url = "http://127.0.0.1:8080/ssmtest2/loginController/getFormdata2.do";
//			String url = "http://10.205.105.183:8080/lee/php";
	        
	        Map<String, String> params = new HashMap<String, String>();//调用sendPost方法时传入外网接口地址携带的参数  
	        params.put("url","http://10.208.134.10:8080/CSRBroker/dialogue");  
	        params.put("data","startTime:2017-04-01 00:00:00,endTime:2018-06-06 00:00:00");  
	        Map<String, Object> map = new HashMap<String, Object>();
	        try {  
	            String orgInfo = sendPost(url,params);  
	            JSONObject obj = JSONObject.fromObject(orgInfo);    
	            //获取返回的json中提取相应的 data.
	            Object result = obj.get("result");
	            
	            
	            Map<String,Object> m = new HashMap<String, Object>();  
	            m.put("issure", obj.get("issure"));//从接口的数据中取出state值并传入json中  
	            m.put("data", obj.get("data"));  
	            
	            map.put("isSuccess", true);
	            map.put("data", m);
	            map.put("message", "查询成功");
	            JSONObject jsonObject = JSONObject.fromObject(map);
	            response.getWriter().print(jsonObject.toString());    
//	            j.setSuccess(true);  
//	            j.setAttributes(m);  
//	            j.setMsg("查询成功");  
	        } catch (Exception e) {  
//	            j.setSuccess(false);  
//	            j.setMsg("查询失败");  
	        	
	        	Map<String,Object> m = new HashMap<String, Object>();  
	        	map.put("isSuccess", false);
	            map.put("message", "查询失败");
	            JSONObject jsonObject = JSONObject.fromObject(map);
	            
	            response.getWriter().print(jsonObject.toString());    
	        }  

	    }  
	
	
	  public String sendPost(String url, Map<String, String> params)throws IOException{
		    OutputStreamWriter out = null;
		    BufferedReader reader = null;
		    String response="";
		    try {
		      URL httpUrl = null; //HTTP URL类 用这个类来创建连接
		      //创建URL
		      httpUrl = new URL(url);
		      //建立连接
		      HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
		      conn.setRequestMethod("POST");
		      conn.setRequestProperty("Content-Type", "application/json");
		      conn.setRequestProperty("connection", "keep-alive");
		      conn.setUseCaches(false);//设置不要缓存
		      conn.setInstanceFollowRedirects(true);
		      conn.setDoOutput(true);
		      conn.setDoInput(true);
		      conn.connect();
		      //POST请求
		      out = new OutputStreamWriter(
		          conn.getOutputStream());
		      // 发送请求参数              
	            if (params != null) {  
	                  StringBuilder param = new StringBuilder();   
	                  for (Map.Entry<String, String> entry : params.entrySet()) {  
	                      if(param.length()>0){  
	                          param.append("&");  
	                      }                 
	                      param.append(entry.getKey());  
	                      param.append("=");  
	                      param.append(entry.getValue());                       
	                      System.out.println(entry.getKey()+":"+entry.getValue());  
	                  }  
	                  System.out.println("param:"+param.toString());  
	                  out.write(param.toString());  
	            }  
	            
		      out.flush();
//		      //解决跨域名问题
//		      response1.setHeader("Access-Control-Allow-Origin","*");
		      //读取响应
		      reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		      String lines;
		      while ((lines = reader.readLine()) != null) {
		        lines = new String(lines.getBytes(), "utf-8");
		        response+=lines;
		      }
		      reader.close();
		      // 断开连接
		      conn.disconnect();
		      System.out.println("断开连接");
		    } catch (Exception e) {
		    System.out.println("发送 POST 请求出现异常！"+e);
		    e.printStackTrace();
		    }
		    //使用finally块来关闭输出流、输入流
		    finally{
		    try{
		      if(out!=null){
		        out.close();
		      }
		      if(reader!=null){
		        reader.close();
		      }
		    }
		    catch(IOException ex){
		      ex.printStackTrace();
		    }
		  }
		 
		    return response;
		  }
	
	 /** 
     * 向指定 URL 发送POST方法的请求      
     * @param url 发送请求的 URL     
     * @param params 请求的参数集合      
     * @return 远程资源的响应结果 
     */  
//    private String sendPost(String url, Map<String, String> params) {  
//        OutputStreamWriter out = null;  
//        BufferedReader in = null;          
//        StringBuilder result = new StringBuilder();   
//        try {  
//            URL realUrl = new URL(url);  
//            HttpURLConnection conn =(HttpURLConnection) realUrl.openConnection();  
//            // 发送POST请求必须设置如下两行  
//            conn.setDoOutput(true);  
//            conn.setDoInput(true);  
//            // POST方法  
//            conn.setRequestMethod("POST");  
//            // 设置通用的请求属性  
//            conn.setRequestProperty("accept", "*/*");  
//            conn.setRequestProperty("connection", "Keep-Alive");  
//            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
//            conn.connect();  
//            // 获取URLConnection对象对应的输出流  
//            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");  
//            // 发送请求参数              
//            if (params != null) {  
//                  StringBuilder param = new StringBuilder();   
//                  for (Map.Entry<String, String> entry : params.entrySet()) {  
//                      if(param.length()>0){  
//                          param.append("&");  
//                      }                 
//                      param.append(entry.getKey());  
//                      param.append("=");  
//                      param.append(entry.getValue());                       
//                      System.out.println(entry.getKey()+":"+entry.getValue());  
//                  }  
//                  System.out.println("param:"+param.toString());  
//                  out.write(param.toString());  
//            }  
//            // flush输出流的缓冲  
//            out.flush();  
//            // 定义BufferedReader输入流来读取URL的响应  
//            in = new BufferedReader( new InputStreamReader(conn.getInputStream(), "UTF-8"));  
//            String line;  
//            while ((line = in.readLine()) != null) {  
//                result.append(line);  
//            }  
//        } catch (Exception e) {              
//            e.printStackTrace();  
//        }  
//        //使用finally块来关闭输出流、输入流  
//        finally{  
//            try{  
//                if(out!=null){  
//                    out.close();  
//                }  
//                if(in!=null){  
//                    in.close();  
//                }  
//            }  
//            catch(IOException ex){  
//                ex.printStackTrace();  
//            }  
//        }  
//        return result.toString();  
//    }  
	  
	  
	  public static String doPost(String url, Map params){  
	         
	       BufferedReader in = null;    
	       try {    
	           // 定义HttpClient    
	           HttpClient client = new DefaultHttpClient();    
	           // 实例化HTTP方法    
	           HttpPost request = new HttpPost();    
	           request.setURI(new URI(url));  
	             
	           //设置参数  
	           List<NameValuePair> nvps = new ArrayList<NameValuePair>();   
	           for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {  
	               String name = (String) iter.next();  
	               String value = String.valueOf(params.get(name)); 
	               //打印
	               System.out.println(name);
	               System.out.println(value);
	               nvps.add(new BasicNameValuePair(name, value));  
	                 
	               //System.out.println(name +"-"+value);  
	           }  
	           request.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));  
	             
	           HttpResponse response = client.execute(request);    
	           int code = response.getStatusLine().getStatusCode();  
	           if(code == 200){    //请求成功  
	               in = new BufferedReader(new InputStreamReader(response.getEntity()    
	                       .getContent(),"utf-8"));  
	               StringBuffer sb = new StringBuffer("");    
	               String line = "";    
	               String NL = System.getProperty("line.separator");    
	               while ((line = in.readLine()) != null) {    
	                   sb.append(line + NL);    
	               }  
	                 
	               in.close();    
	                 
	               return sb.toString();  
	           }  
	           else{   //  
	               System.out.println("状态码：" + code);  
	               return null;  
	           }  
	       }  
	       catch(Exception e){  
	           e.printStackTrace();  
	             
	           return null;  
	       }  
	   }  
	  
	  
	  
	  	/*
		 * 获取新的数据库连接 
		 * 旧  不用
		 */
	   public static Connection getCon() {
	        //数据库连接名称
	        String username="root";
	        //数据库连接密码
	        String password="anlaigz";
	        String driver="com.mysql.jdbc.Driver";
	        //其中test为数据库名称
	        String url="jdbc:mysql://10.201.164.33:3306/vicidial?useUnicode\\=true&characterEncoding\\=utf-8&zeroDateTimeBehavior\\=convertToNull";
	        Connection conn=null;
	        try{
	            Class.forName(driver);
	            conn=(Connection) DriverManager.getConnection(url,username,password);
//	            System.out.println("111");
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        return conn;
	    }
	  
	   
	   
	   
	   
	   
	   
	 

	   public void getSelect() { 
	   // 获取到连接
	           try {
	           Connection conn = getCon();
				 Statement stmt = conn.createStatement();
				//String _sql ="call proc_api_auto_calldetail_bylist_report('AI_TEST','[ALL]','0')";
				ResultSet rs = stmt.executeQuery("call proc_api_auto_calldetail_bylist_report('AI_TEST',0,0)");//选择import java.sql.ResultSet;
				while(rs.next()){//如果对象中有数据，就会循环打印出来
					System.out.println(rs.getString("List ID"));
				}
	           } catch (Exception e) {
	           }
	       }
	   
	   
	   
	   //科大讯飞推送结果接口
		@RequestMapping("/getALL")
		@ResponseBody 
		public String getALL(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException{  

//			 httprequest.setCharacterEncoding("UTF-8");
			BufferedReader streamReader = new BufferedReader( new InputStreamReader(httprequest.getInputStream(), "UTF-8"));  
			String input = null;
			StringBuffer requestBody = new StringBuffer();
			while((input = streamReader.readLine()) != null) {
				requestBody.append(input);
				}
			
			//获取json数据
			String json =  requestBody.toString();
			System.out.println(json);
			//json 转 HashMap
			HashMap parseMap = JSON.parseObject(json,HashMap.class);
			
			//获得标识
			String userData = (String) parseMap.get("userData");
			String listid = userData.split("_")[1];
			 
			 //获得主叫号码
			 String caller = (String) parseMap.get("caller");
			 
			 
			 List<JSONArray> resultData =  (List<JSONArray>) parseMap.get("interactions");	
			
			 EducationCustomer ec = new EducationCustomer();
			 ec.setPhone_number(caller);
			 ec.setList_id(listid);
			 EducationCustomer newEC = educationCustomerService.findforEducationCustomer(ec);
			 
			 
			 for(Object singleOrder : resultData){
				 //生成一个 32位的 随机数
				 UUID uuid = UUID.randomUUID();
				 String random = uuid.toString().replace("-", "");

				 
				 String jsonStr = com.alibaba.fastjson.JSONObject.toJSONString(singleOrder);
				 InteractionDTO orderInfo = JSON.parseObject(jsonStr, InteractionDTO.class);
//				 System.out.println(orderInfo.getInput());
				 if(("customer").equals(orderInfo.getRole())) {
			     
				 	 Result resultdata = new Result();
				 
				     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 	 Date today = new Date();  				 
					 resultdata.setAnswer(orderInfo.getOutput());
					 resultdata.setQuestion(orderInfo.getInput());
					 resultdata.setId(random);
					 resultdata.setCreatedTime(df.format(today));
					 resultdata.setFromUser(caller);
					 resultdata.setFieldName(orderInfo.getSeq());
					 
					 int i= resultService.insert(resultdata);
					 
//					 String sql = "where phone_number ='"+userData+"' and list_id ='180928004'";
//					 EducationCustomer ec = new EducationCustomer();
//					 ec.setPhone_number(userData);
//					 ec.setList_id("180928004");
//					 EducationCustomer newEC = educationCustomerService.findforEducationCustomer(ec);
					 
					 
					 if(!("").equals(orderInfo.getTagName())&&null!=orderInfo.getTagName()) {
						 if(("A").equals(orderInfo.getTagName())) {
							 newEC.setInterest_degree("潜在客户");
						 }else if(("B").equals(orderInfo.getTagName())) {
							 newEC.setInterest_degree("一般客户");
						 }else if(("C").equals(orderInfo.getTagName())) {
							 newEC.setInterest_degree("无意向客户");
						 }/*else {
							 newEC.setInterest_degree("无意向客户");
						 }*/
					 
					 }
					 
					 educationCustomerService.updateByPrimaryKeyEducationCustomer(newEC);
					 
					 
//				 System.out.println(i);
				 }
			 
			 }
			
			
			return null;
		}
	   
	   
		
		
		
		
		//PD detail结果推送
		@RequestMapping("/getAIDETAILforCM")
		@ResponseBody 
		public void getAIDETAILforCM(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException{  
		
			
			Map<String, Object> map = new HashMap<String, Object>();
			JSONObject jsonObject = null;
			
			BufferedReader streamReader = new BufferedReader( new InputStreamReader(httprequest.getInputStream(), "UTF-8"));  
			String input = null;
			StringBuffer requestBody = new StringBuffer();
			while((input = streamReader.readLine()) != null) {
				requestBody.append(input);
				}
			
			//获取json数据
			String json =  requestBody.toString();
			System.out.println(json);
		
			
			map.put("code", 1);
			map.put("status", "succ");
			
			jsonObject = jsonObject.fromObject(map);
			httpresponse.setCharacterEncoding("UTF-8"); 
			httpresponse.setContentType("text/html;charset=utf-8");
			httpresponse.setHeader("Access-Control-Allow-Origin","*");
			httpresponse.getWriter().print(jsonObject.toString()); 
		}
		
		
	   
	   /*
	    * 测试方法
	    */
	   public static void main(String[] args) {
	
//		   try {
//			   Connection conn = getCon();
//			   CallableStatement cstmt = conn.prepareCall("{call proc_api_auto_calldetail_bylist_report(?,?,?)}");
//			   
//			   cstmt.setString(1, "AI_TEST");
//			   cstmt.setString(2,"[ALL]"); 
//			   cstmt.setString(3, "0");
//			   
//			   
//			   cstmt.execute();
//	           System.out.println("执行结果是:"+cstmt.getString(1));
//			   
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	        
		   
		   //3.通过数据库的连接操作数据库，实现增删改查
					try {
						Connection conn = getCon();
						Statement stmt = conn.createStatement();
						String _sql ="call proc_api_auto_calldetail_bylist_report('AI_TEST',0,0)";
						 
						Date currentTime = new Date();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
						String createdate = formatter.format(currentTime);
						String sql = "call proc_api_auto_callresult_sum_bydate_report('2018/04/01','"+createdate+"','AI_TEST')";
						 
						ResultSet rs = stmt.executeQuery(_sql);//选择import java.sql.ResultSet;
						while(rs.next()){//如果对象中有数据，就会循环打印出来
							System.out.println(rs.getString("Campaign ID"));
							System.out.println(rs.getString("Phone Number"));
							Detail d = new Detail();
							d.setCampaign_id(rs.getString("Campaign ID"));
							d.setPhone_number(rs.getString("Phone Number"));
							
							Detail detail = null;
//							detail = detailService
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		   
		   
	        
	        
	   }
	   
		
	  

	   
	  
	
}
