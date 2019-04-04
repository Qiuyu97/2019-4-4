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

<!-- <script src="js/jsAjax.js" type="text/javascript"></script> -->

<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jqueryAjax.js" type="text/javascript"></script>


</head>
<body>
	<h1>用户注册</h1>

	<form name="f1" method="post">






		<!-- 
		用户名_js:
		<input id="logid" name="logid" type="text" />
		<span id="sp_logid"></span>
		<br /><br />		
		 -->


		用户名_jquery: <input name="logid" type="text" /> <span></span> <br />
		<br /> 密码: <input name="pwd" type="password" /> <span></span> <br />
		<br /> 确认密码: <input name="rpwd" type="password" /> <span></span> <br />
		<br /> 验证码: <input name="yzm" type="text" /> <img /> <span></span>
		<br />
		<br /> <label> <input name="xy" type="checkbox" value="1" />
			网站用户注册协议
		</label> <br />
		<br /> <input name="sub" type="button" value="确定注册" /> <input
			name="res" type="button" value="重置" />

	</form>

</body>
</html>
