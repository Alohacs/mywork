<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript">
    $(function() {  
    	$("#login").click(function() {  
    	// 处理表单验证和交给后台处理的逻辑  
    	    var userName = $("#username").val();  
    	    var password = $("#password").val();  
    	    var isNotError = true;  
    	    $.ajax({  
    	        type: "POST",  
    	        url: "${ctx}/loginController/login.do",  
    	        dataType: "json",  
    	        data: {"username":userName,"password":password},  
    	        success: function(data){  
    	            //登录成功  
    	        	console.log(data);
    	              
    	        }  
    	  
    	    });  
    	  
    	});  
    	});  
    
    $(function() {  
    	$("#checkin").click(function() {  
    
/* 	    var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth()+1;
		var day = date.getDate();
		var hour = date.getHours();
		var minute = date.getMinutes();
		var second = date.getSeconds();
		Alert(year+'-'+month+'-'+day+'- '+hour':'+minute+':'+second);
 */
		var d = new Date();


		var YMDHMS = d.getFullYear() + "-" +(d.getMonth()+1) + "-" + d.getDate() + " " + 


		d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
		
		
   	     $.ajax({  
	        type: "POST",  
	        url: "${ctx}/OperationController/getAjaxJsonTest.do",  
	        dataType: "json",  
	        data: {},  
	        success: function(data){  
	            //登录成功  
	        	console.log(data);
	              
	        }  
	  
	    });  
	  
	});  
	});  

    
    
    </script>
	
	
	
  </head>
  <body>
  	<%-- <form action="${ctx}/loginController/login.do" method="post"> --%>
  	
  	
  		用户名:<input type="text" placeholder="请输入账户名"  id="username" value=""/>
  		密码:<input type="password" placeholder="请输入密码" id="password" value=""/>
  	<!-- 	<input type="submit" value="登录"> -->
  	<!-- <input type="button" value="登录" onclick="login()"> -->
  	<button id="login" type="button">登lu</button>
  	
  	<button id="checkin" type="button">拉取历史数据</button>
  	<a href="<c:url value='/up/a.jpg'/>">下载jpg</a>
    <br />
    <a href="<c:url value='/up/cos.jar'/>">下载jar</a>
    <br />
    <a href="<c:url value='/up/db.doc'/>">下载doc</a> 
    <br /> 
    <a href="<c:url value='/up/jingyesi.txt'/>">下载txt</a>  
    <br />
    <a href="<c:url value='/up/buystatus.xlsx'/>">下载excel</a> 

  </body>
</html>
