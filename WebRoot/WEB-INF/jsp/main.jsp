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
	
  </head>
  <body>
        登录用户名：${username}
    <form name="form2" id="form2">
    <table>
    	<tr>
    		<td width="5%" class="td_title">操作</td>
			<td width="5%" class="td_title">操作</td>
			<td width="5%" class="td_title">操作</td>
    		<td>用户名</td>
    		<td>密码</td>
    	</tr>
    	 <c:forEach items="${users}" var="user">
    		<tr>
    			<td align="center" class="td_border">
					<a href='${ctx}/loginController/toAdd.do'>
						新增
					</a>
				</td>
				<td align="center" class="td_border">
					<a href='${ctx}/loginController/toUpdate.do?userId=${user.userid}'>
						修改${user.userid}
					</a>
				</td>
				<td align="center" class="td_border">
					<a href='${ctx}/loginController/delete.do?userId=${user.userid}'>
							X
					</a>
				</td>
		    	<td>${user.username}</td>
		    	<td>${user.password}</td>
	    	</tr>
	      </c:forEach>
    </table>
    <table>
    	<tr>
    		<td>powerid</td>
    		<td>userid</td>
    		<td>username</td>
    		<td>password</td>
    		<td>powername</td>
    	</tr>
    	 <c:forEach items="${powerVos}" var="powerVo">
    		<tr>
    			<td>${powerVo.powerid}</td>
    			<td>${powerVo.userid}</td>
		    	<td>${powerVo.username}</td>
		    	<td>${powerVo.password}</td>
		    	<td>${powerVo.powername}</td>
	    	</tr>
	      </c:forEach>
    </table>
    <table id="pageTable">
    	<thead>
    		<tr>
	    		<th>powerid</th>
	    		<th>userid</th>
	    		<th>username</th>
	    		<th>password</th>
	    		<th>powername</th>
	    	</tr>
    	</thead>
    	<tbody>
    		
    	</tbody>
    </table>
    <div>
    	<a href="javascript:void(0);" onclick="upPage()">上一页</a>
    	<a href="javascript:void(0);" onclick="nextPage()">下一页</a>
    	<label>
    		<span id="nowPage">0</span>/<span id="allPage">0</span>
    	</label>
    	<input type="text" style="width: 50px;" id="gotoPage"/>
    	<a href="javascript:void(0);" onclick="gotoPage()">跳转</a>
    </div>
  <%--   <table>
    	<tr>
    		<td width="5%" class="td_title">操作</td>
			<td width="5%" class="td_title">操作</td>
			<td width="5%" class="td_title">操作</td>
    		<td>用户名</td>
    		<td>密码</td>
    	</tr>
    	<s:iterator value="users" var="user">
    		<s:property value="userId"/>
    		<s:property value="username"/>
    		<s:property value="password"/><br/>
    		<tr>
    			<td align="center" class="td_border">
					<a href='${ctx}/loginAction/toAdd.do'>
						新增
					</a>
				</td>
				<td align="center" class="td_border">
					<a href='${ctx}/loginAction/toUpdate.do?userId=${user.userId}'>
						修改
					</a>
				</td>
				<td align="center" class="td_border">
					<a href='${ctx}/loginAction/deleteById.do?userId=${user.userId}'>
							X
					</a>
				</td>
		    	<td>${user.username}</td>
		    	<td>${user.password}</td>
	    	</tr>
    	</s:iterator>
    </table> --%>
    </form>
    <script src="${ctx}/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript">
    	var rowcount = 0;
    	var currentPage = 1;
    	var pageSize = 2;
    	var allPage = 1;
    	$(document).ready(function(){
		  	rowCount();
		  	$("#nowPage").html(currentPage);
		  	allPage = parseInt(rowcount/pageSize);
		  	if(rowcount%pageSize > 0){
		  		allPage += 1;
		  	}
		  	$("#allPage").html(allPage);
		  	findPageByVoSQL();
		});
    	function upPage(){
    		if(currentPage > 1){
	    		currentPage -= 1;
	    		findPageByVoSQL();
    		}else{
    			console.info("首页");
    		}
    	}
    	function nextPage(){
    		if(currentPage < allPage){
    			currentPage += 1;
    			findPageByVoSQL();
    		}else{
    			console.info("最后一页");
    		}
    	}
    	function gotoPage(){
    		var gotoPage = Number($("#gotoPage").val());
    		if(gotoPage <= allPage && gotoPage >= 1){
    			currentPage = gotoPage;
    			findPageByVoSQL();
    		}else{
    			console.info("超出范围");
    		}
    	}
    	function rowCount(){
    		$.ajax({
			    url:"/ssmtest2/loginController/rowCount.do",
			    type:'POST', //GET
			    async:false,    //或false,是否异步
			    data:{     //传递参数
			    },
			    timeout:5000,    //超时时间
			    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
			    success:function(da,textStatus,jqXHR){
			    	if(da != null){
	    				rowcount = da.count;
	    			}
			    },
			    error:function(xhr,textStatus){
			        console.log('findPageByVoSQL()');
			    }
			});
    	}
    	function findPageByVoSQL(){
    		$.ajax({
			    url:"/ssmtest2/loginController/findPageByVoSQL.do",
			    type:'POST', //GET
			    async:false,    //或false,是否异步
			    data:{     //传递参数
			    	currentPage:currentPage,pageSize:pageSize
			    },
			    timeout:5000,    //超时时间
			    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
			    success:function(da,textStatus,jqXHR){
			    	 if(da != null){
	    				var str = "";
	    				for(var i=0; i<da.data.length; i++){
	    					str += "<tr>" + 
	    						"<td>" + da.data[i].powerid + "</td>" +
	    						"<td>" + da.data[i].userid + "</td>" +
	    						"<td>" + da.data[i].username + "</td>" +
	    						"<td>" + da.data[i].password + "</td>" +
	    						"<td>" + da.data[i].powername + "</td>" +
	    						"</tr>";
	    				}
	    				$("#pageTable tbody").html(str);
	    			}
			    },
			    error:function(xhr,textStatus){
			        console.log('findPageByVoSQL()');
			    }
			});
    	}
    	function add(){
    		window.location.href="${ctx}/loginAction/toAdd.do";
    	}
    	
    	/* function update(aid){
    		if(aid==0){
			   aid = getCheckboxItem();
			   if(aid.indexOf(",")>0){
					alert('您选择了多条数据，请重新选择再进行操作');
			   }else{
			   		console.log(aid);
					location="${ctx}/loginAction/toUpdate.do&aid="+aid;
			   }
			} 
	    }
	    function getCheckboxItem()
		{
			var allSel="";
			if(document.form2.id.value) return document.form2.id.value;
			for(i=0;i<document.form2.id.length;i++)
			{
				if(document.form2.id[i].checked)
				{
					if(allSel=="")
						allSel=document.form2.id[i].value;
					else
						allSel=allSel+","+document.form2.id[i].value;
				}
			}
			return allSel;
		} */
    </script>
  </body>
</html>
