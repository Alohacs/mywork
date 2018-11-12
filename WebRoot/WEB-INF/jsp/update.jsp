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
    
    <title>My JSP 'add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  <body>
  	修改：
  	<div>
  		<form action="${ctx}/loginController/update.do" method="post">
  			<input style="display: none" type="text" name="userid" id="userId" value="${user.userid}"/>
  			用户名:<input type="text" name="username" id="username" value="${user.username}"/>
	  		密码:<input type="password" name="password" id="password" value="${user.password}"/>
	  		<input type="submit" value="修改">
  		</form>
  	</div>
  </body>
</html>
