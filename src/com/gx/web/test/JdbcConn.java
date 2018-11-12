package com.gx.web.test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
 
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Application;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gx.poi.utils.XLSX2CSV;
import com.monitorjbl.xlsx.StreamingReader;
import com.mysql.jdbc.PreparedStatement;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import net.sf.json.JSONObject;

  
	class ThreadMethodTest implements Runnable{
	private String number;
	private String province;
	private String city;
	
	public ThreadMethodTest(String number) {
		this.number=number;
	}
	
	public ThreadMethodTest(String province,String city) {
		this.province=province;
		this.city = city;
	}
	
	

    private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/ai_phone_num";
        String username = "root";
        String password = "";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    
    
    
    private static int insert(NumberLibrary numberLibrary) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into ai_number_library_excel (mts,province,catName,telString,areaVid,ispVid,carrier) values (?,?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, numberLibrary.getMts());
            pstmt.setString(2, numberLibrary.getProvince());
            pstmt.setString(3, numberLibrary.getCatName());
            pstmt.setString(4, numberLibrary.getTelString());
            pstmt.setString(5, numberLibrary.getAreaVid());
            pstmt.setString(6, numberLibrary.getIspVid());
            pstmt.setString(7, numberLibrary.getCarrier());
            
            
            
            
            
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
	
    private static void postMando(String telStringNum) {
	    	try {
	        	OkHttpClient client = new OkHttpClient();

	        	MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
	        	RequestBody body = RequestBody.create(mediaType, "tel="+telStringNum);
	        	Request request = new Request.Builder().url("http://tcc.taobao.com/cc/json/mobile_tel_segment.htm").post(body)
	        	  .addHeader("Content-Type", "application/x-www-form-urlencoded")
	        	  .addHeader("Cache-Control", "no-cache")
	        	  .addHeader("Postman-Token", "f15fdc00-21ee-4df6-bcee-31c97ad8cee2")
	        	  .build();

	    			Response response = client.newCall(request).execute();
	    			String jsonOB = response.body().string();

	    			String json = jsonOB.split("= ")[1];
	    			
	    			JSONObject jsonObject=JSONObject.fromObject(json);
	    			NumberLibrary NLibrary=(NumberLibrary)JSONObject.toBean(jsonObject, NumberLibrary.class);
	    			
	    			if(("香港").equals(NLibrary.getProvince())) {
	    				NLibrary.setTelString(telStringNum);
	    				
	    			}
	    			
	        	System.out.println("当前号码"+NLibrary.getTelString());
	    			insert(NLibrary);
	        	} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    }
	
    
    public static List<ExcelLibrary> getExcelList(String pro,String city){
	    	 Connection conn = getConn();
	    	String sql = "  select * FROM ai_excel_library where province =? and city =? ";
	    	PreparedStatement pstmt;
	    	List<ExcelLibrary> elList= new ArrayList<ExcelLibrary>(); 
	    	ResultSet rs = null;
	    	try {
	            pstmt = (PreparedStatement) conn.prepareStatement(sql);
	            pstmt.setString(1, pro);
	            pstmt.setString(2, city);
	            rs = pstmt.executeQuery();
	            
	//            pstmt.close();
	//            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    	
	    	
	    		try {
					while(rs.next()){ 
						Integer id = rs.getInt(1); 
						Integer pref = rs.getInt(2); 
		    			Integer phone = rs.getInt(3); 
		    			
		    			String province = rs.getString(4);
		    			String cityname = rs.getString(5);
		    			String isp = rs.getString(6);
		    			
		    			Integer post_code = rs.getInt(7); 
		    			Integer area_code = rs.getInt(8); 
		    			
		    			String type = rs.getString(9);
		    			
		    			ExcelLibrary el = new ExcelLibrary(id,pref,phone,province,cityname,isp,post_code,area_code,type);
		    			
		    			elList.add(el);
	//	    		int mgr = rs.getInt(4); 
	//	    		Date hiredate = rs.getDate(5); 
	//	    		double sal = rs.getDouble(6); 
	//	    		double comm = rs.getDouble(7); 
	//	    		int deptno = rs.getInt(8); 
	//	    		Emp emp = new Emp(empno,ename1, job, mgr, hiredate, sal, comm, deptno); 
	//	    		list.add(emp); 
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
	
	    	
	    	
	    	return elList;
	    }
   
    
/*	@Override
	public void run() {
		
		String num = "";
		for(int x = 41060 ;x< 100000000;x++) {
    		try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		num = "";
    		if(x<10) {
    			num +="0000000"+Integer.valueOf(x).intValue();
    		}else if(x<100) {
    			num +="000000"+Integer.valueOf(x).intValue();
    		}else if(x<1000) {
    			num +="00000"+Integer.valueOf(x).intValue();
    		}else if(x<10000) {
    			num +="0000"+Integer.valueOf(x).intValue();
    		}else if(x<100000) {
    			num +="000"+Integer.valueOf(x).intValue();
    		}else if(x<1000000) {
    			num +="00"+Integer.valueOf(x).intValue();
    		}else if(x<10000000) {
    			num +="0"+Integer.valueOf(x).intValue();
    		}else if(x<100000000) {
    			num +=Integer.valueOf(x).intValue();
    		}
    		
    		
    		
    		String telStringNum = number+num;
    		postMando(telStringNum);
    	}
		
	}*/
	
    
    @Override
	public void run() {
    	
    	List<ExcelLibrary> elList = getExcelList(province,city);
    	for(ExcelLibrary e:elList) {
    		
    		for(int x = 0;x< 10000 ;x++) {
    			String  numFirst = String.valueOf(e.getPhone());
    			
    			if(x<10) {
    				numFirst +="000"+Integer.valueOf(x).intValue();
        		}else if(x<100) {
        			numFirst +="00"+Integer.valueOf(x).intValue();
        		}else if(x<1000) {
        			numFirst +="0"+Integer.valueOf(x).intValue();
        		}else if(x<10000) {
        			numFirst +=Integer.valueOf(x).intValue();
        		}
    			postMando(numFirst);
    		}
    		
    	}
    	
    	
    }
    
    
    
	}


	@Controller
	@RequestMapping("/JdbcConn")
	public class JdbcConn {  
      
//    public static void main(String[] args) throws Exception {  
    	
		@RequestMapping("/addPN")
		@ResponseBody 
		public void addPN(String listID){
    	
			
	    	new Thread(new ThreadMethodTest("江苏","无锡")).start();
	    	new Thread(new ThreadMethodTest("江苏","南京")).start();
	    	new Thread(new ThreadMethodTest("江苏","常州")).start();
	    	new Thread(new ThreadMethodTest("江苏","扬州")).start();
	    	new Thread(new ThreadMethodTest("江苏","镇江")).start();
	    	new Thread(new ThreadMethodTest("江苏","南通")).start();
	    	new Thread(new ThreadMethodTest("江苏","淮安")).start();
	    	new Thread(new ThreadMethodTest("江苏","徐州")).start();
	    	new Thread(new ThreadMethodTest("江苏","苏州")).start();
	    	
	    	new Thread(new ThreadMethodTest("浙江","杭州")).start();
	    	new Thread(new ThreadMethodTest("浙江","湖州")).start();
	    	new Thread(new ThreadMethodTest("浙江","嘉兴")).start();
	    	
	    	new Thread(new ThreadMethodTest("福建","福州")).start();
	    	new Thread(new ThreadMethodTest("福建","泉州")).start();
	    	
	    	new Thread(new ThreadMethodTest("四川","成都")).start();
	    	new Thread(new ThreadMethodTest("四川","眉山")).start();
	    	new Thread(new ThreadMethodTest("四川","南充")).start();
	    	
	    	new Thread(new ThreadMethodTest("吉林","长春")).start();
	    	
	    	new Thread(new ThreadMethodTest("江西","南昌")).start();
    	
    	
//    	new Thread(new ThreadMethodTest("186")).start();
//		new Thread(new ThreadMethodTest("139")).start();
//		new Thread(new ThreadMethodTest("138")).start();
//		new Thread(new ThreadMethodTest("137")).start();
//    	
//		new Thread(new ThreadMethodTest("135")).start();
//    	
//		new Thread(new ThreadMethodTest("130")).start();
    	
    	/*File xlsxFile = new File("C:\\Users\\dell\\Desktop\\归属地数据库_Excel格式.xlsx");
        if (!xlsxFile.exists()) {
            System.err.println("Not found or not a file: " + xlsxFile.getPath());
            return;
        }
 
        int minColumns = -1;
        if (args.length >= 2)
            minColumns = Integer.parseInt(args[1]);
        
        // The package open is instantaneous, as it should be.
        OPCPackage p = OPCPackage.open(xlsxFile.getPath(), PackageAccess.READ);
        XLSX2CSV xlsx2csv = new XLSX2CSV(p, System.out, minColumns);
        //若想把数据输入文件，可如下调用
        PrintStream ps = new PrintStream(new File("e:/test/a.txt"));
        XLSX2CSV xlsx2csv = new XLSX2CSV(p, ps, minColumns);
        xlsx2csv.process();
        p.close();*/
    	
    	
//    	List<ExcelLibrary> el = getExcelList("江苏", "无锡");
//    	for(ExcelLibrary e :el) {
//    		
//    		System.out.println(e.getPhone());
//    		
//    	}
    	
    }  
    
     /*     
        try {  
            Class.forName("com.mysql.jdbc.Driver");//����������jar����  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        }  
          
        Connection conn = null;   
        try {  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test2", "root", "");//��������  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
          
//        String sql = "select userId,username,password from user";//����SQL���  
        String sql = "INSERT INTO user VALUES ('vava', '333333')";//����SQL���  
        
        
        
          
        try {  
            Statement stmt = conn.createStatement();  
            ResultSet rs = stmt.executeQuery(sql);  
            //cursor  
//            while(rs.next()){  
//                int id = rs.getInt(1);
//                String email = rs.getString(2);  
//                String dob = rs.getString(3);  
//                System.out.println(id+" , "+email+" , "+dob);  
//                  
//            }  
            System.out.println("1");
        } catch (SQLException e) {  
            e.printStackTrace();  
        }finally{  
            if(conn!=null)  
                try {  
                    conn.close();//�ر�����  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }     
        }  
          */
    	
    	/*
    	 * 用postman生成  通过OKhttp包
    	 * 使用post方法
    	 * 传递数据，get返回值
    	 */
    	
    	
    
    	
    	
    	
    	
    	
    	
    	
		
    	
    	
    	
//    	getConn();
//    	NumberLibrary nl = new NumberLibrary();
//    	nl.setMts("1231");
//    	nl.setProvince("1231");
//    	insert(nl);
    	
    	
    public static void read07Excel(String filePath) throws Exception
	    {
	        //创建输入流
	        FileInputStream fis = new FileInputStream(new File(filePath));
	        //由输入流得到工作簿
	        XSSFWorkbook workbook = new XSSFWorkbook(fis);
	        //得到工作表
	        XSSFSheet sheet = workbook.getSheet("phone");
	        
	        /*
	         * for (Iterator iter = l.iterator(); iter.hasNext();) {
	    	     String str = (String)iter.next();
	    	     System.out.println(str);
	    	 }
	         */
	       
	        
	        
	        
	        
	        //得到行,0表示第一行
	        XSSFRow row = sheet.getRow(0);
	        //创建单元格行号由row确定,列号作为参数传递给createCell;第一列从0开始计算
	        XSSFCell cell = row.getCell(2);
	        //给单元格赋值
	        String cellValue = cell.getStringCellValue();
	        System.out.println("C1的值是"+cellValue);
	        int a[][] = new int[10][30];
	        for(int i=0;i<a.length;i++)
	        {
	            System.out.println(i);
	        }
	        workbook.close();
	        fis.close();
	    }
   
    
    public static void postMando(String telStringNum) {
    	try {
        	OkHttpClient client = new OkHttpClient();

        	MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        	RequestBody body = RequestBody.create(mediaType, "tel="+telStringNum);
        	Request request = new Request.Builder().url("http://tcc.taobao.com/cc/json/mobile_tel_segment.htm").post(body)
        	  .addHeader("Content-Type", "application/x-www-form-urlencoded")
        	  .addHeader("Cache-Control", "no-cache")
        	  .addHeader("Postman-Token", "f15fdc00-21ee-4df6-bcee-31c97ad8cee2")
        	  .build();

    			Response response = client.newCall(request).execute();
    			String jsonOB = response.body().string();

    			String json = jsonOB.split("= ")[1];
    			
    			JSONObject jsonObject=JSONObject.fromObject(json);
    			NumberLibrary NLibrary=(NumberLibrary)JSONObject.toBean(jsonObject, NumberLibrary.class);
    			
//        	System.out.println(NLibrary.getTelString());
    			insert(NLibrary);
        	} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    }
    
    
    
    public static List<ExcelLibrary> getExcelList(String pro,String city){
	    	 Connection conn = getConn();
	    	String sql = "  select * FROM ai_excel_library where province =? and city =? ";
	    	PreparedStatement pstmt;
	    	List<ExcelLibrary> elList= new ArrayList<ExcelLibrary>(); 
	    	ResultSet rs = null;
	    	try {
	            pstmt = (PreparedStatement) conn.prepareStatement(sql);
	            pstmt.setString(1, pro);
	            pstmt.setString(2, city);
	            rs = pstmt.executeQuery();
	            
	//            pstmt.close();
	//            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    	
	    	
	    		try {
					while(rs.next()){ 
						Integer id = rs.getInt(1); 
						Integer pref = rs.getInt(2); 
		    			Integer phone = rs.getInt(3); 
		    			
		    			String province = rs.getString(4);
		    			String cityname = rs.getString(5);
		    			String isp = rs.getString(6);
		    			
		    			Integer post_code = rs.getInt(7); 
		    			Integer area_code = rs.getInt(8); 
		    			
		    			String type = rs.getString(9);
		    			
		    			ExcelLibrary el = new ExcelLibrary(id,pref,phone,province,cityname,isp,post_code,area_code,type);
		    			
		    			elList.add(el);
	//	    		int mgr = rs.getInt(4); 
	//	    		Date hiredate = rs.getDate(5); 
	//	    		double sal = rs.getDouble(6); 
	//	    		double comm = rs.getDouble(7); 
	//	    		int deptno = rs.getInt(8); 
	//	    		Emp emp = new Emp(empno,ename1, job, mgr, hiredate, sal, comm, deptno); 
	//	    		list.add(emp); 
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
	
	    	
	    	
	    	return elList;
	    }

	private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://10.205.105.184:3306/ai_phone_num";
        String username = "root";
        String password = "";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    
    
    private static int insert(NumberLibrary numberLibrary) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into ai_Number_Library (mts,province,catName,telString,areaVid,ispVid,carrier) values (?,?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, numberLibrary.getMts());
            pstmt.setString(2, numberLibrary.getProvince());
            pstmt.setString(3, numberLibrary.getCatName());
            pstmt.setString(4, numberLibrary.getTelString());
            pstmt.setString(5, numberLibrary.getAreaVid());
            pstmt.setString(6, numberLibrary.getIspVid());
            pstmt.setString(7, numberLibrary.getCarrier());
            
            
            
            
            
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    
    
    
}  