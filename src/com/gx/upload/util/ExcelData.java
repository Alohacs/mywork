package com.gx.upload.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.gx.po.EducationCustomer;
import com.gx.po.EducationEf;
import com.gx.po.PhoneNUM;
import com.gx.service.EducationCustomerService;
import com.gx.service.EducationYinFuService;
import com.gx.service.PhoneNUMService;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * 解析excel 上传数据
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/ExcelDataAtionController")
public class ExcelData {
    
    private static final Logger log = Logger.getLogger(ExcelData.class);
    
	@Autowired
	public PhoneNUMService phoneNUMService;
	@Autowired
	public EducationCustomerService educationCustomerService;
	@Autowired
	public EducationYinFuService educationYinFuService;
	
	
//  上传文件
  @ResponseBody
  @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
  public void uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
      //@RequestParam(value = "fileinfo", required = false) MultipartFile file
	  //上传文件的路径
	  
	  
	  String listID  = request.getParameter("listID");
	  String assignment_name = request.getParameter("assignment_name");
	  String camp_id = request.getParameter("campaign");
	  
	  
	  
//	  String ff =   request.getParameter("file");
	  
//	  MultipartFile mFile = multipartRequest.getFile(name);
	  
      String path = FileTools.getFileInfo(request, response, file);
      
      try {
		read03Excel(path,listID,assignment_name,camp_id);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
  }
	
	
	/*
	 * 读取文件公共方法
	 */
  
  
	
	/**
	      * 03-97版本的  EXCEL
	      * @param args
	      * @throws Exception
	      */
	 	@ResponseBody 
	 	@RequestMapping("/read03Excel")
	     public void read03Excel(String filePath,String  listID,String assignment_name,String campid) throws Exception
	     {
	         //创建输入流
	         FileInputStream fis = new FileInputStream(new File(filePath));
	         //通过构造函数传参
	         Workbook workbook = new HSSFWorkbook(fis);
	       
	         
	        /* //获取工作表
	         HSSFSheet sheet = workbook.getSheetAt(0);
	         //获取行,行号作为参数传递给getRow方法,第一行从0开始计算
	         HSSFRow row = sheet.getRow(0);
	         //获取单元格,row已经确定了行号,列号作为参数传递给getCell,第一列从0开始计算
	         HSSFCell cell = row.getCell(2);
	         //设置单元格的值,即C1的值(第一行,第三列)
	         String cellValue = cell.getStringCellValue();
	         System.out.println("第一行第三列的值是"+cellValue);*/
	         
	
	//         for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
	        	    Sheet sheet = workbook.getSheetAt(0);//获取sheet
	        	    int count = 0; //总条数  第二个sheet自动归0
	        	    for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
	        	    	if(j>0) {
		        	        Row row = sheet.getRow(j); // 获取行
		        	        // count++; //按行的话
		        	        
		        	        PhoneNUM pn = new PhoneNUM();
		        	        EducationCustomer ec = new EducationCustomer();
		        	        
//		        	        String callid = getMyRandom();
	//	        	        for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {
			        	            // count ++ ; 按单元格的话
		        	        		Cell cell0 = row.getCell(0); //获取单元格
		        	        		cell0.setCellType(CellType.STRING);
			        	            pn.setPhoneNumber(cell0.getStringCellValue());
		        	        		
			        	            Cell cell1 = row.getCell(1); 
		        	        		if(null!=cell1) {
		        	        			cell1.setCellType(CellType.STRING);
		        	        			pn.setCustomerName(cell1.getStringCellValue());
	        	    				}
		        	        		
		        	        		
	//	        	        		Cell cell2 = row.getCell(2); 
	//	        	        		cell2.setCellType(CellType.STRING);
			        	            
		        	        		pn.setListID(listID);
		        	        		pn.setCamp_id(campid);
		        	        		pn.setStatus("0");
//		        	        		pn.setCallID(callid);
		        	        		
		        	        		PhoneNUM newPN = phoneNUMService.findforPhoneNum(pn);
		        	        		if(null==newPN) {
		        	        			
		        	        			phoneNUMService.insert(pn);
		        	        			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		        	        			String nowTime = df.format(new Date());// new Date()为获取当前系统时间				arrayPhone.add(ec.getPhoneNumber());
		        	        			  
		        	       	         if(("AI_YF").equals(campid)) {
		        	       	        	 
		        	       	        	EducationEf detail = new EducationEf();
			        					/*detail.(pn.getPhoneNumber());*/
			        					detail.setList_id(listID);
//			        					detail.setCall_id(pn.getCallID());
			        					detail.setParent_name(pn.getCustomerName());	
			        					detail.setParent_phone(pn.getPhoneNumber());
			        					detail.setCreated_time(nowTime);
			        					detail.setUpdate_time(nowTime);			        							        					
			        					detail.setStatus("0");
			        					
//			        					detail.setAssignment_name(assignment_name);
			        					EducationEf result= educationYinFuService.findforEEF(detail);
			        					if(null==result)
			        						educationYinFuService.insert(detail);
		        	       	        	 
		        	       	         }else if(("AI_MXC").equals(campid)) {
		        	       	        	 
//		        	       	        	EducationEf detail = new EducationEf();
//			        					detail.(pn.getPhoneNumber());
//			        					detail.setList_id(listID);
////			        					detail.setCall_id(pn.getCallID());
//			        					detail.setParent_name(pn.getCustomerName());		        					
//			        					detail.setCreated_time(nowTime);
//			        					detail.setUpdate_time(nowTime);			        							        					
//			        					detail.setStatus("0");
//			        					detail.setAssignment_name(assignment_name);
//			        					EducationCustomer result= educationCustomerService.findforEducationCustomer(detail);
//			        					if(null==result)
//			        					educationCustomerService.insert(detail);
		        	       	        	 
		        	       	         }else {
		        	       	        	 
			        					
			        	        		EducationCustomer detail = new EducationCustomer();
			        					detail.setPhone_number(pn.getPhoneNumber());
			        					detail.setList_id(listID);
//			        					detail.setCall_id(pn.getCallID());
			        					detail.setParent_name(pn.getCustomerName());
			        					
			        					detail.setCreation_time(nowTime);
			        					detail.setUpdate_time(nowTime);
			        					
			        					
			        					detail.setStatus("0");
			        					detail.setAssignment_name(assignment_name);
			        					EducationCustomer result= educationCustomerService.findforEducationCustomer(detail);
			        					if(null==result)
			        					educationCustomerService.insert(detail);
		        	        			 
		        	       	        	 
		        	       	         }

		        	       	         
		        	       	         
		        	        			
		        	        			
		        	        		}else {
		        	        			continue;
		        	        		}
		        	        		
		        	        		
		        	        		
		        	        		
		        	        		
	//	        	        }
	        	    	}
	        	    }
	//         }
	         
	         
	         
	         
	         
	         
	         workbook.close();
	         fis.close();
	         
	         
	         
	         
	         
	     }
	 	
	 	
	 	//sendMess 云之树发送短信接口
	 	@ResponseBody 
	 	@RequestMapping("/sendMessage")
	     public void sendMessage() throws Exception
	     {
	         //创建输入流
	         FileInputStream fis = new FileInputStream(new File("C:\\Users\\dell\\Desktop\\03-sendMess.xls"));
	         //通过构造函数传参
	         Workbook workbook = new HSSFWorkbook(fis);
	        /* //获取工作表
	         HSSFSheet sheet = workbook.getSheetAt(0);
	         //获取行,行号作为参数传递给getRow方法,第一行从0开始计算
	         HSSFRow row = sheet.getRow(0);
	         //获取单元格,row已经确定了行号,列号作为参数传递给getCell,第一列从0开始计算
	         HSSFCell cell = row.getCell(2);
	         //设置单元格的值,即C1的值(第一行,第三列)
	         String cellValue = cell.getStringCellValue();
	         System.out.println("第一行第三列的值是"+cellValue);*/
	         
	         		String numberString = null;
	         
	        	    Sheet sheet = workbook.getSheetAt(0);//获取sheet
	        	    int count = 0; //总条数  第二个sheet自动归0
	        	    for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
	        	    	if(j>0) {
		        	        Row row = sheet.getRow(j); // 获取行
		        	        		Cell cell0 = row.getCell(0); //获取单元格
		        	        		
		        	        		if(null!=cell0)
		        	        		cell0.setCellType(CellType.STRING);
			        	            String phoneNUm = cell0.getStringCellValue();
			        	            if(null==numberString) {
			        	            	numberString=phoneNUm;
			        	            }else {
			        	            	numberString+=","+phoneNUm;
			        	            }
		        	        		
		        	        		
	        	    	}
	        	    }
	         
	         
	         System.out.println(numberString);
	         
	        	    OkHttpClient client = new OkHttpClient();

	        	    MediaType mediaType = MediaType.parse("application/octet-stream");
	        	    RequestBody body = RequestBody.create(mediaType, "{\r\n\t\"Action\": \"sendsms\", "
	        	    		+ "\r\n\t\"UserName\": \"shmc\", "
	        	    		+ "\r\n\t\"Password\": \"7913C822752D1D3F32A9AE67E6D33632\", "
	        	    		+ "\r\n\t\"Mobile\": \""+numberString+"\", "
	        	    		+ "\r\n\t\"Message\": \"5pyJ5oOz6L+H5oKo55qE5a2p5a2Q5Lmf6K645piv5LiL5LiA5L2N5LmU5biD5pav77yM5q+U5bCU55uW6Iyo5ZCX77yf5oOz55+l6YGT5a2p5a2Q55qE5Yib6YCg5oCn5oCd57u055qE6IO95Yqb5pyJ5aSa6auY5ZCX77yf5p2l5YWN6LS55L2T6aqM5oiR5Lus55qE5aaZ5bCP56iL6K+V5ZCs6K++56iL5ZCn77yBaHR0cDovL2NtLmFubGFpLmNvbTo4MDgwL3N0dWR5L214Yw==\", "
	        	    		+ "\r\n\t\"ExtCode\": \"\", \r\n\t\"MsgID\": \"\", \r\n\t\"IsFlash\": 0\r\n\r\n\t\r\n\t\r\n}\r\n");
	        	    Request request = new Request.Builder()
	        	      .url("http://www.yescloudtree.cn:28009")
	        	      .post(body)
	        	      .addHeader("cache-control", "no-cache")
	        	      .addHeader("Postman-Token", "72662cde-f8a1-43a8-b0b4-fd15ec8f4b56")
	        	      .build();

	        	    Response response = client.newCall(request).execute();
	        	    
	        	    
	         
	         
	         workbook.close();
	         fis.close();
	         
	         System.out.println("ok");
	         
	         
	         
	     }
	 	


	@ResponseBody 
	@RequestMapping("/getExcelData")
    public static List<String[]> getExcelData(MultipartFile file) throws IOException{
        checkFile(file);
         //获得Workbook工作薄对象
        Workbook workbook = getWorkBook(file);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<String[]> list = new ArrayList<String[]>();
        if(workbook != null){
            for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
                //获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if(sheet == null){
                    continue; 
                }
                //获得当前sheet的开始行
                int firstRowNum  = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                //循环除了第一行的所有行
                for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    if(row == null){
                        continue;
                    }
                    //获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数
                    int lastCellNum = row.getLastCellNum();
                    String[] cells = new String[row.getLastCellNum()];
                    //循环当前行
                    for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
                    }
                    list.add(cells);
                }
            }
        }
        return list;
    }

    
    /**
     * 检查文件
     * @param file
     * @throws IOException
     */
     public static  void checkFile(MultipartFile file) throws IOException{
         //判断文件是否存在
         if(null == file){
             log.error("文件不存在！");
         }
         //获得文件名
         String fileName = file.getOriginalFilename();
         //判断文件是否是excel文件
         if(!fileName.endsWith("xls") && !fileName.endsWith("xlsx")){
             log.error(fileName + "不是excel文件");
         }
     }
     public static  Workbook getWorkBook(MultipartFile file) {
         //获得文件名
         String fileName = file.getOriginalFilename();
         //创建Workbook工作薄对象，表示整个excel
         Workbook workbook = null;
         try {
             //获取excel文件的io流
             InputStream is = file.getInputStream();
             //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
             if(fileName.endsWith("xls")){
                 //2003
                 workbook = new HSSFWorkbook(is);
             }
//             else if(fileName.endsWith("xlsx")){
//                 //2007 及2007以上
//                 workbook = new XSSFWorkbook(is);
//             }
         } catch (IOException e) {
             log.error(e.getMessage());
         }
         return workbook;
     }
     
     public static String getCellValue(Cell cell){
         String cellValue = "";
         if(cell == null){
             return cellValue;
         }
     //判断数据的类型
         switch (cell.getCellType()){
             case Cell.CELL_TYPE_NUMERIC: //数字
                 cellValue = stringDateProcess(cell);
                 break;
             case Cell.CELL_TYPE_STRING: //字符串
                 cellValue = String.valueOf(cell.getStringCellValue());
                 break;
             case Cell.CELL_TYPE_BOOLEAN: //Boolean
                 cellValue = String.valueOf(cell.getBooleanCellValue());
                 break;
             case Cell.CELL_TYPE_FORMULA: //公式
                 cellValue = String.valueOf(cell.getCellFormula());
                 break;
             case Cell.CELL_TYPE_BLANK: //空值
                 cellValue = "";
                 break;
             case Cell.CELL_TYPE_ERROR: //故障
                 cellValue = "非法字符";
                 break;
             default:
                 cellValue = "未知类型";
                 break;
         }
         return cellValue;
     }


 /**
      * 时间格式处理
      * @return
      * @author Liu Xin Nan
      * @data 2017年11月27日
      */
     public static String stringDateProcess(Cell cell){
         String result = new String();  
         if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式  
             SimpleDateFormat sdf = null;  
             if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {  
                 sdf = new SimpleDateFormat("HH:mm");  
             } else {// 日期  
                 sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
             }  
             Date date = cell.getDateCellValue();  
             result = sdf.format(date);  
         } else if (cell.getCellStyle().getDataFormat() == 58) {  
             // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)  
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
             double value = cell.getNumericCellValue();  
             Date date = org.apache.poi.ss.usermodel.DateUtil  
                     .getJavaDate(value);  
             result = sdf.format(date);  
         } else {  
             double value = cell.getNumericCellValue();  
             CellStyle style = cell.getCellStyle();  
             DecimalFormat format = new DecimalFormat();  
             String temp = style.getDataFormatString();  
             // 单元格设置成常规  
             if (temp.equals("General")) {  
                 format.applyPattern("#");  
             }  
             result = format.format(value);  
         }  
         
         return result;
     }


     /**
      * 03-97版本的  EXCEL
      * @param args
      * @throws Exception
      */
     
     public static void main(String[] args) throws Exception {
         /**
          * 注意这只是07版本以前的做法对应的excel文件的后缀名为.xls
          * 07版本和07版本以后的做法excel文件的后缀名为.xlsx
          */
         //创建新工作簿
         HSSFWorkbook workbook = new HSSFWorkbook();
         //新建工作表
         HSSFSheet sheet = workbook.createSheet("phone");
         //创建行,行号作为参数传递给createRow()方法,第一行从0开始计算
         HSSFRow row = sheet.createRow(0);
         //创建单元格,row已经确定了行号,列号作为参数传递给createCell(),第一列从0开始计算
         HSSFCell cell = row.createCell(2);
         //设置单元格的值,即C1的值(第一行,第三列)
         cell.setCellValue("hello sheet");
         //输出到磁盘中
         FileOutputStream fos = new FileOutputStream(new File("D:\\file\\12.xlsx"));
         workbook.write(fos);
         workbook.close();
         fos.close();
     }
     
     
 	/*
 	 * 编写 5位 混合大小写 和数字的随机数
 	 */
 	public String getMyRandom(){
 		String str = "";
 		Random rand = new Random();
 		for(int i=0;i<5;i++){
 			int num = rand.nextInt(3);
 			switch(num){
 				case 0:
 					char c1 = (char)(rand.nextInt(26)+'a');//生成随机小写字母 
 					str += c1;
 					break;
 				case 1:
 					char c2 = (char)(rand.nextInt(26)+'A');//生成随机大写字母 
 					str += c2;
 					break;
 				case 2:
 					str += rand.nextInt(10);//生成随机数字
 			}
 		}
 		return str;
 	}
  	
     
 	
 	
 	

     
     
     
     /**
     * 07版本的  EXCEL
     * @param args
     * @throws Exception
     */
 	@ResponseBody 
 	@RequestMapping("/write07")
    public void write07() throws Exception
    {
        //创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        //新建工作表
        XSSFSheet sheet = workbook.createSheet("hello");
        //创建行,0表示第一行
        XSSFRow row = sheet.createRow(0);
        //创建单元格行号由row确定,列号作为参数传递给createCell;第一列从0开始计算
        XSSFCell cell = row.createCell(2);
        //给单元格赋值
        cell.setCellValue("hello yeye sheet");
        //创建输出流
        FileOutputStream fos = new FileOutputStream(new File("D:\\file\\hello.xlsx"));
        workbook.write(fos);
        workbook.close();
        fos.close();
    }
 	
 	/**
     * 07版本的  EXCEL
     * @param args
     * @throws Exception
     */
// 	@ResponseBody 
// 	@RequestMapping("/read07Excel")
 	  public void read07Excel(String filePath) throws Exception
 	    {
 	        //创建输入流
 	        FileInputStream fis = new FileInputStream(new File(filePath));
 	        //由输入流得到工作簿
 	        XSSFWorkbook workbook = new XSSFWorkbook(fis);
 	        //得到工作表
 	        XSSFSheet sheet = workbook.getSheet("Sheet1");
 	        
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
 	
 	
 	
   
  /*	@ResponseBody 
  	@RequestMapping("/readEXCEL")
  	  public void readEXCEL(String path) throws Exception
  	    {
    	
        if (path == null || Common.EMPTY.equals(path)) {
        	            return null;
        	      } else {
        	            String postfix = Util.getPostfix(path);
        	            if (!Common.EMPTY.equals(postfix)) {
        	                 if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
        	                    return readXls(path);
        	               } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
        	                    return readXlsx(path);
        	                  }
        	              } else {
        	                  System.out.println(path + Common.NOT_EXCEL_FILE);
        	              }
        	          }
    	
    	
    	
  	    }*/
 	
  	
  	
 	@ResponseBody
  	@RequestMapping(value = "/readEXCEL")
	public Map<String, Object> readEXCEL(@RequestParam("file") MultipartFile file, @RequestParam("path") String filePath) throws Exception
	{
		Map<String, Object> resultMap = new HashMap<String,Object>();
		String path = this.getClass().getResource("/").toString().replace("WEB-INF/classes/", "").replace("file:/", "")+"upload";
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
		String fileName = file.getOriginalFilename();
	//	String typeName = fileName.substring(fileName.lastIndexOf("."));
	//	String typs = ".jpg.png.gif.xls.xlsx.doc.ppt.xml.docx.pptx.pdf";
		String savePath = "error";
	//	if (typs.indexOf(typeName) == -1)
	//	{
	//		resultMap.put("message","文件格式不符！");
	//	}
	//	else
	//	{
			if (filePath.equals(""))
			{
				savePath = sdf.format(new Date()) + "/" + +System.currentTimeMillis() + "_.jpeg";
				filePath = path+"/" + savePath;
			}
			else
			{
				filePath = path + filePath;
			}
			File tempFile = new File(filePath);
			File folder = new File(path + "/" + sdf.format(new Date()));
			if (!folder.exists())
			{
				folder.mkdirs();
			}
			if (!tempFile.exists())
			{
				tempFile.createNewFile();
			}
			file.transferTo(tempFile);
	//	}
			resultMap.put("message",true);
		resultMap.put("path",savePath);
		return resultMap;
	}
  	
  	

}