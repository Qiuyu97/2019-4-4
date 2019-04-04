<%@page import="com.qiuyu.bean.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	//是否已经登录了，是：直接回主页
	HttpSession se = request.getSession();
	User u = (User)se.getAttribute("user");
	if(u!=null){
		response.sendRedirect("index.jsp");
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</head>
<body>

	<h1>用户登录</h1>

	<form name="f1" method="post">

		用户名: <input name="logid" type="text"
			value="<%		
			Cookie[] arr = request.getCookies();
			for(Cookie c : arr){
				if(c.getName().equals("logid")){
					out.print(c.getValue());
				}
			}		
		%>" />
		<span></span> <br />
		<br /> 密码: <input name="pwd" type="password"
			value="<%	
			for(Cookie c : arr){
				if(c.getName().equals("pwd")){
					out.print(c.getValue());
				}
			}		
		%>" />
		<span></span> <br />
		<br /> <label> <input name="save" type="checkbox" value="1"
			<%	
			for(Cookie c : arr){
				if(c.getName().equals("logid")){
					out.print("checked='checked'");
				}
			}		
		%> />
			记住用户名和密码(Cookie)
		</label> <br />
		<br /> <input name="sub" type="button" value="登录" /> <input
			name="res" type="button" value="重置" />

	</form>


</body>
</html>
<%	
	//必须在最后
	for(Cookie c : arr){
		if(c.getName().equals("logid")){
			out.print("<script>logid=true;pwd=true;</script>");
		}
	}		
%>
