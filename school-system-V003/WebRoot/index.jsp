<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>index</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    
  </head>
  
  <body>
  	<a href="Major___selectMajorInf">登录</a><br>
  	<a href="AdminLogin.jsp">管理员登录</a><br>
 </body>
</html>
