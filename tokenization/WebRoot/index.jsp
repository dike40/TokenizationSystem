<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.sun.xml.internal.txw2.Document"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String ID = (String)request.getAttribute("TR_ID");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script  language="javascript" type="text/javascript">
   function showid(){
 		 if( <%=ID%> != null){  
 			 document.getElementById("trid").value = <%=ID%>;
 		 }else{
 		 	 document.getElementById("trid").value = "请输入信息 生成ID！";	
 		 }
 	}
 	
 	</script>
 	
 	
  </head>
 
  <body onload="showid()"> 
    <br>This is Tokenization page. <br>
    
   <form action="mainEntry" method = "post">
   tokendomain:<input type="text" name="domain" id="domain" style="width:138px" /><br/> 
   mode:<input type="text" name="mode" id="mode" style="width:138px" /><br/>
   merchantId:<input type="text" name="merchantId" id="merchantId" style="width:138px" /><br/>
   param 1:<input type="text" name="param1" id="param1" style="width:138px" /><br/>
   param 2:<input type="text" name="param2" id="param2" style="width:138px" /><br/>
   
   <input type="button" value="提交" onclick="this.form.submit()">get your TR ID!
    </form>
    
    your TR ID is:<input type="text" name="trid" id="trid" style="color:red;" readonly/>
    
    
   
  </body>
 
</html>
