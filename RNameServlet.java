package com.qiuyu.servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuyu.factory.ConnectionFactory;

/**
 * Servlet implementation class RNameServlet
 */
@WebServlet("/rname")
public class RNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//当前servlet提供ajax，
	//【每一个ajax操作，都应该提供一个独立的 Servlet】
	// 决定好，ajax提交时get还是post
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print("<script>alert('非法操作！');window.location.href='register.jsp';</cript>");				
	}

	//ajax提交方式为post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取参数
		String logid = request.getParameter("logid");
		
		//判断
		if(logid!=null) {
			
			//第二重防护：数据非法内容过滤
			//数据库，或数组，或枚举都可以，进行内容判断过滤：indexOf()
			
			ResultSet rs = ConnectionFactory.execDQL("select `id` from `user` where `logid`='"+logid+"';");
			
			try {
				if(rs.next()) {
					response.getWriter().print(1);//有了
				}
				else {
					response.getWriter().print(0);//没有
				}
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		else {
			//表示异常问题，没有接收到
			response.getWriter().print(-1);
		}
	}

}
