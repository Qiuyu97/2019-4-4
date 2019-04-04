package com.qiuyu.servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qiuyu.bean.User;
import com.qiuyu.factory.ConnectionFactory;
import com.qiuyu.util.Comm;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//乱码问题
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//要提示：只能js跳转页面
		response.getWriter().print("<script>alert('非法操作！');window.location.href='login.jsp';</cript>");
				
	}

	
	
	//表单提交，实现注册
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//乱码问题
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		//第一步：应该从请求中获取表单提交的数据
		String logid = request.getParameter("logid");
		//不要密码，必须拿确认密码
		String pwd = request.getParameter("rpwd");
		
		//第二步：处理业务逻辑，密码加密
		pwd = Comm.toMD5(pwd);
		
		
		//第三步：执行insert		
		if(ConnectionFactory.execDML("insert into `user` values (null,'"+logid+"','"+pwd+"',default);")>0) {
			//绝对不能提取最后一条数据，
			//再按照用户名和密码去执行登录操作
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
					
										
					//返回主页
					response.sendRedirect("index.jsp");
				}
			}
			catch(Exception ex) {
				response.getWriter().print("<script>alert('系统繁忙，请联系管理员！');window.location.href='login.jsp';</cript>");
			}
		}
		else {
			response.getWriter().print("<script>alert('系统繁忙，请稍后再试！');window.location.href='login.jsp';</cript>");
		}
	}

}
