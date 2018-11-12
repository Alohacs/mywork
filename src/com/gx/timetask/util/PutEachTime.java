package com.gx.timetask.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gx.po.EducationCustomer;
import com.gx.po.EducationEf;
import com.gx.po.Mission;
import com.gx.po.PhoneNUM;
import com.gx.po.ReportResult;
import com.gx.service.EducationCustomerService;
import com.gx.service.EducationYinFuService;
import com.gx.service.MissionService;
import com.gx.service.PhoneNUMService;
import com.gx.service.ReportResultService;
import com.gx.web.OperationAction;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;


@Component
public class PutEachTime {  
	
	@Autowired
	public MissionService missionService;
	@Autowired
	public EducationCustomerService educationCustomerService;
	@Autowired
	public EducationYinFuService educationYinFuService;
	@Autowired
	public PhoneNUMService phoneNUMService;
	@Autowired
	public ReportResultService reportResultService;
	@Autowired
	public OperationAction operationAction;
	
	
	//每次间隔5分钟执行一次

//	 @Scheduled(cron = "0/5 * * * * ?") 
//	 @Scheduled(cron = "0 */3 * * * ?") 
	 public void run() throws Exception { 
//	  System.out.println("推送消息来了"); 
		 
		 
		 
		  operationAction.setDate();
		  operationAction.setDetailNEW();
		  /*
		   * 由于场景更换，所以 CCMS 获取 ai_result表方法，暂时停用
		  ***		operationAction.getAjaxJsonTest();
		   */
//		  operationAction.insertReportResult();
		 
	
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		 String nowTime = df.format(new Date());// new Date()为获取当前系统时间
		 
		 //查询在所在当前时间内 所应该执行的  mission
		 String sql =" where '"+nowTime+"' BETWEEN date(start_time) AND date(shut_time) and isCompletel ='0'";
		 List<Mission> misList = missionService.findBySQLforMission(sql);
		 if(!misList.isEmpty()) {
			 //遍历所有应该执行的 任务，并执行数据传输
			 for(Mission mm:misList) {
				 if(!("EducationOutBound").equals(mm.getAssignment_name())) {
					 
				
				 
					 //获取设定的呼出时间
					String timePeriod =  mm.getExhalation_time();
					if(null!=timePeriod) {
						String am = timePeriod.split("\\|")[0];
						String pm = timePeriod.split("\\|")[1];
						
						//获取上午的2个限时段
						String Ttimeam = am.split("\\~")[0];
						String Etimeam = am.split("\\~")[1];
						Ttimeam = Ttimeam.substring(Ttimeam.length() - 5);
						Etimeam = Etimeam.substring(0,5);
						
						//获取下午的2个限时段
						String Ttimepm = pm.split("\\~")[0];
						String Etimepm = pm.split("\\~")[1];
						Ttimepm = Ttimepm.substring(Ttimepm.length() - 5);
						Etimepm = Etimepm.substring(0,5);
						
						//如果现在的时间在其中任意一个时间段。则执行外部输出
						if(isBelong(Ttimeam,Etimeam)||isBelong(Ttimepm,Etimepm)) {
		//					System.out.println("111");
						
							getPhoneNumList(mm.getListID(),mm.getAi_campID());
							mm.setIsCompletel("1");
							missionService.updateByPrimaryKey(mm);
						}
						
					}
				
				 }
			 }
		 }
		 

	}
	 
	 
	 
	 public void getPhoneNumList(String listID,String  camp_id) throws IOException{
		 
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String date=dateFormat.format(new java.util.Date());

		 String ecsql = " where listID ='"+listID+"' and status ='0'";
			List<String> arrayPhone = new ArrayList<String>();
			List<PhoneNUM> pnList = phoneNUMService.findBySQLforPhoneNUM(ecsql);
			for(PhoneNUM ec :pnList) {
//				ec.getPhoneNumber();
				arrayPhone.add(ec.getPhoneNumber());
				ec.setStatus("1");
				phoneNUMService.updateByPrimaryKeyforPhoneNUM(ec);

				if(("AI_YF").equals(camp_id)) {
					EducationEf ef = new EducationEf();
					ef.setParent_phone(ec.getPhoneNumber());
					ef.setList_id(ec.getListID());
					if(null!=ec.getCustomerName())
						ef.setParent_name(ec.getCustomerName());
					//查找  EF 对象
					EducationEf newef = educationYinFuService.findforEEF(ef);
					if(null!=newef) {
						newef.setStatus("1");
						newef.setUpdate_time(date);
						educationYinFuService.updateByPrimaryKeyEducationEf(newef);
					}
				}else {
					
					EducationCustomer detail = new EducationCustomer();
					detail.setPhone_number(ec.getPhoneNumber());
					if(null!=ec.getCustomerName())
						detail.setParent_name(ec.getCustomerName());
					detail.setList_id(listID);
					
					EducationCustomer result= educationCustomerService.findforEducationCustomer(detail);
					if(null!=result) {
						result.setStatus("1");
						result.setUpdate_time(date);
						educationCustomerService.updateByPrimaryKeyEducationCustomer(result);
					}
				}
			
			}
		 
			if(!arrayPhone.isEmpty()) {
			
				OkHttpClient client = new OkHttpClient();
				client.setReadTimeout(100, TimeUnit.SECONDS);  
				Response response = null;
				try {
				MediaType mediaType = MediaType.parse("application/json");
				RequestBody body = RequestBody.create(mediaType, "{\n    \"Head\": {\n        \"Version\": \"0.0.1\", \n        \"CMD\": \"AI004\", \n    "
						+ "    \"Time\": \"2018-08-28 10:51:22\"\n    }, \n    \"Body\": {\n        \"token\": \"v31Z51S7i5TmCmz\",\n     "
						+ "   \"user_login\": \"admin\",\n        \"user_pass\": \"123456\", \n      "
						+ "  \"campaign_id\": \""+camp_id+"\", \n      "
						+ "  \"list_id\": \""+listID+"\", \n       "
						+ " \"phone_number\": \""+arrayPhone+"\", \n        \"action\": \"add_lead_id\"\n    }\n}\n");
				Request request = new Request.Builder()
				  .url("http://10.208.133.91/xiaomaiai/action/AddLeadID")
//						.url("http://10.208.134.21/xiaomaiai/action/AddLeadID")		
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
				finally {
				ResponseBody responseBody = response.body();
					responseBody.close();
				}
				
				
				OkHttpClient clientsl = new OkHttpClient();
				clientsl.setReadTimeout(100, TimeUnit.SECONDS);  
				Response responsesl = null;
				MediaType mediaTypesl = MediaType.parse("application/json");
				RequestBody bodysl = RequestBody.create(mediaTypesl, "{\r\n    \"Head\": {\r\n        \"Version\": \"0.0.1\", \r\n        \"CMD\": \"AI005\", \r\n   "
						+ "     \"Time\": \"2018-08-28 10:51:22\"\r\n    }, \r\n    \"Body\": {\r\n        \"token\": \"v31Z51S7i5TmCmz\",\r\n      "
						+ "  \"user_login\": \"admin\",\r\n        \"user_pass\": \"123456\", \r\n       "
						+ " \"campaign_id\": \""+camp_id+"\", \r\n       "
						+ " \"list_id\": \""+listID+"\", \r\n        \"action\": \"start_list_id\"\r\n    }\r\n}\r\n");
				Request requestsl = new Request.Builder()
				  .url("http://10.208.133.91/xiaomaiai/action/startListID")
//						.url("http://10.208.134.21/xiaomaiai/action/startListID")
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
				finally {
					ResponseBody responseBody1 = responsesl.body();
						responseBody1.close();
					}
			
			}
			
		 
//		 return arrayPhone;
	 } 
	 


	 
	 public Boolean isBelong(String Ttime,String Etime){
		 
		    SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
		    Date now =null;
		    Date beginTime = null;
		    Date endTime = null;
		    try {
		        now = df.parse(df.format(new Date()));
		        beginTime = df.parse(Ttime);
		        endTime = df.parse(Etime);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    Boolean flag = belongCalendar(now, beginTime, endTime);
		 return flag;
		 
	 }
	 
	 public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
	        Calendar date = Calendar.getInstance();
	        date.setTime(nowTime);

	        Calendar begin = Calendar.getInstance();
	        begin.setTime(beginTime);

	        Calendar end = Calendar.getInstance();
	        end.setTime(endTime);

	        if (date.after(begin) && date.before(end)) {
	            return true;
	        } else {
	            return false;
	        }
	    }
	 
	 

}
