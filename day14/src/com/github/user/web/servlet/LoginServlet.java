package com.github.user.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.UserException;

import com.commons.utils.CommonUtils;
import com.github.user.domain.User;
import com.github.user.service.UserService;

public class LoginServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8"); //请求编码
		response.setContentType("text/html;charset=utf-8");//响应编码
		
		//依赖UserService
		UserService userService = new UserService();
		
	
		User form =CommonUtils.toBean(request.getParameterMap(), User.class);
		
		try {
			User user =userService.login(form);
			request.getSession().setAttribute("sessionUser", user);
			response.sendRedirect(request.getContextPath()+"/user/welcome.jsp");
		} catch (com.github.user.service.UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("user", form);
			request.getRequestDispatcher("user/login.jsp").forward(request, response);
		}
		
	}

}
