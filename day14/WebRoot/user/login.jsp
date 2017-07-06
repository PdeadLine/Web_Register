<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   
	  	<h1>登陆</h1>
	  	<p style="color:red;font-width: 900">${msg }</p>
	    <form action="<c:url value='/LoginServlet'/>" method="post">
	    <!--增加回显功能  -->
	    	用    户:<input type="text" name="username" value="${user.username }"/>${errors.username }<br/>
	    	密    码:<input type="text" name="password" value="${user.password }"/>${errors.password }<br/>
	    	
	    	<br/>
	    		<input type="submit" value="登陆"/>
	    	
	    </form>
  	
  </body>
</html>
