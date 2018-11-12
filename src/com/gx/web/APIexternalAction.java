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
import com.gx.po.PhoneNUM;
import com.gx.po.Role;
import com.gx.po.TokenCode;
import com.gx.service.AiUserService;
import com.gx.service.PhoneNUMService;
import com.gx.service.RoleService;
import com.gx.service.TokenCodeService;
import com.gx.tokenProcessor.util.TokenProcessor;

import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

@Component
@Controller
@RequestMapping("/APIexternalAction")
public class APIexternalAction {
	
	@Autowired
	public PhoneNUMService phoneNUMService;
	@Autowired
    public TokenCodeService tokenCodeService;

	
//	public TokenProcessor tp = new TokenProcessor();

	
	@RequestMapping("/putPhoneNUMbyListID")
	@ResponseBody 
	public void  putPhoneNUMbyListID(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
		TokenCode tc = new TokenCode();
		
		//通过接受 token 和 listID，验证获取phoneNUM，中的数据，并且更改status 状态
		String listID = httprequest.getParameter("listID");
//		String listID = "00001";
		//用于显示查询几条数据，并且返回
		String count = httprequest.getParameter("count");
//		String count = "2";
		String tokencode = httprequest.getParameter("tokencode");
		tc.setTokenCode(tokencode);
		tc = tokenCodeService.findByTokenCode(tc);
		if(tc==null) {
			map.put("code", 1001);
			map.put("tips", "身份验证牌错误");
		}else {
			
			
			long currentTime = System.currentTimeMillis();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	        Date date = new Date(currentTime);
	        String sysDATE = formatter.format(date);
	        if(sysDATE.equals(tc.getSystemTime().split(" ")[0])) {
			
				String sql = "where listID = '"+listID+"' and status = 0 limit 0,"+count;
				
				List<PhoneNUM> pnList = phoneNUMService.findBySQLforPhoneNUM(sql);
				if(!pnList.isEmpty()) {
				
					List<PhoneNUM> newPN = new ArrayList<PhoneNUM>();
					for(PhoneNUM pn :pnList ) {
						pn.setStatus("1");
						phoneNUMService.updateByPrimaryKeyforPhoneNUM(pn);
						pn.setStatus(null);
						newPN.add(pn);
					}
					
					
					map.put("code", 1);
					map.put("data", newPN);
					map.put("count", newPN.size());
				
		        }else {
		        	map.put("code", 1002);
					map.put("tips", "身份验证牌过期");
		        }
				
				
			}else {
				map.put("code", 3002);
				map.put("tips", "找不到对应的listID");
			}
		}
	
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
	}
	
	
//	@RequestMapping("/setTokenCode")
//	@ResponseBody 
	public void  setTokenCode()throws ServletException, IOException, Exception{  
		
		TokenCode tc = new TokenCode();
        String value = System.currentTimeMillis()+new Random().nextInt()+"";
//    	String value ="1531018665970";
//        System.out.println(value); 
        tc.setRandomNUM(value);
        
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(currentTime);
//        System.out.println(formatter.format(date));
        tc.setSystemTime(formatter.format(date));

        //获取数据指纹，指纹是唯一的
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] b = md.digest(value.getBytes());//产生数据的指纹
            //Base64编码
            BASE64Encoder be = new BASE64Encoder();
            be.encode(b);
//            System.out.println(be.encode(b)); 
            tc.setTokenCode(be.encode(b));
            tokenCodeService.insert(tc);
//            return be.encode(b);//制定一个编码
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
		
	}
	
	
	private final String[] key_word_list = {"试听课","平台","靠谱","师资","补习","没时间","辅导"};
	
	
	@RequestMapping("/textRobotAPI")
	@ResponseBody 
	public void  textRobotAPI(HttpServletRequest httprequest, HttpServletResponse httpresponse)throws ServletException, IOException, Exception{  
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = null;
	
		String question = httprequest.getParameter("question");
		
		for(String  ss:key_word_list) {
			
			
			
		}
		
		
		
		
		
		
		jsonObject = jsonObject.fromObject(map);
		httpresponse.setCharacterEncoding("UTF-8"); 
		httpresponse.setContentType("text/html;charset=utf-8");
		httpresponse.setHeader("Access-Control-Allow-Origin","*");
		httpresponse.getWriter().print(jsonObject.toString()); 
	}
	
}
