package com.qiuyu.servlet;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qiuyu.bean.User;
import com.qiuyu.factory.ConnectionFactory;
import com.qiuyu.util.Comm;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//get请求
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//乱码问题
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//要提示：只能js跳转页面
		response.getWriter().print("<script>alert('非法操作！');window.location.href='login.jsp';</cript>");
		
		//没有提示，直接跳转
		//response.sendRedirect("login.jsp");
	}

	//post请求
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//乱码问题
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//第一步：应该从请求中获取表单提交的数据
		String logid = request.getParameter("logid");
		String pwd = request.getParameter("pwd");
		
		//第二步：处理业务逻辑，密码加密
		pwd = Comm.toMD5(pwd);
				
		//第三步：查询数据库，
		ResultSet rs = ConnectionFactory.execDQL("select * from `user` where `userName`='"+logid+"' and `userPass`='"+pwd+"';");
		
		//第四步：看返回结果，
		try {
			if(rs.next()) {
				//有
				//获取数据，封装对象
				User u = new User();
				u.setId(rs.getInt("userId"));
				u.setLogid(logid);
				u.setPwd("******");
				u.setCreatetime(rs.getDate("createTime"));
				
				//保存到session中，编程登录状态
				HttpSession session = request.getSession();
				session.setAttribute("user", u);
				
				//=========================================
				
				// 多选盒子：checkbox，之后选中打钩，才会提交
				if(request.getParameter("save")!=null) {
					//记住的，记录cookie
					Cookie ck1 = new Cookie("logid",logid);
					ck1.setMaxAge(60*60*24*7);
					ck1.setPath("/");
					response.addCookie(ck1);
					
					Cookie ck2 = new Cookie("pwd",request.getParameter("pwd"));
					ck2.setMaxAge(60*60*24*7);
					ck2.setPath("/");
					response.addCookie(ck2);
					
				}
				else {
					//不要记住，清除cookie
					//服务器删不掉，把值清除，时效过期
					Cookie[] arr = request.getCookies();
					for(Cookie c : arr) {						
						if(c.getName().equals("logid") || c.getName().equals("pwd")) {
							//清空值
							c.setValue(null);
							//过期
							c.setMaxAge(0);
							c.setPath("/");
							//放回去
							response.addCookie(c);
							
							System.out.println("aaa");
						}						
					}
				}
				
				
				
				//==========================================
				
				//返回主页
				response.sendRedirect("index.jsp");
				
			}
			else {
				//没有
				response.getWriter().print("<script>alert('用户名或密码错误！');window.location.href='login.jsp';</cript>");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
