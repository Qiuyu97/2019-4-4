<%@ page import="com.qiuyu.bean.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</head>
<body>

	<h1>我的网站</h1>

	<!-- 用户没有的时候，显示：登录 / 注册 ， 登录后，显示： 欢迎您：*****， 安全退出-->

	<%
		//获取session，如果session有，说明登录了，如果没有就显示连接导航
		//获取session对象
		HttpSession se = request.getSession();
		//提取指定名称的参数内容
		User u = (User)se.getAttribute("user");
		//判断
		if(u==null){
			out.println("<a href='login.jsp'>登录</a>");
			out.println("<a href='register.jsp'>立即注册</a>");
		}
		else{
			out.println("欢迎您：" + u.getLogid());
			out.println("<input id='tc' type='button' value='立即退出' />");
		}
	%>



</body>
</html>
