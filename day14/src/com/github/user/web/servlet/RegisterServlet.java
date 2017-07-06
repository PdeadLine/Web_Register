package com.github.user.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.commons.utils.CommonUtils;
import com.github.user.domain.User;
import com.github.user.service.UserException;
import com.github.user.service.UserService;

public class RegisterServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//依赖UserService
		UserService us = new UserService();
		
		//封装表单数据
	/*	User form = new User();
		Map<String,String> m=request.getParameterMap();
		try {
			BeanUtils.populate(form, m);
		} catch (Exception e) {
			e.printStackTrace();
		} */
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		
		/**
		 * 	添加新任务（表单校验）
		 * 	1）创建一个map,用来装载所有错误信息【key为表单字段名称】。
		 *	2）校验之后，map是否为null，非空保存map到request域中。
		 *	3）为空，向下执行
		 */
		Map<String,String> errors=new HashMap<String,String>();
		
		String username =form.getUsername();//获取表单username
		if(username==null ||username.trim().isEmpty()){
			errors.put("username", "用户名不能为空");
		}else if(username.length()<3 || username.length()>15){
			errors.put("username", "用户名长度必须在3~15之间");
		}
		String password =form.getPassword();//获取表单password
		if(password==null ||password.trim().isEmpty()){
			errors.put("password", "密码不能为空");
		}else if(password.length()<3 || password.length()>15){
			errors.put("password", "密码长度必须在3~15之间");
		}
		
		String sessionVerifyCode=(String) request.getSession().getAttribute("session_vcode");
		String verifycode =form.getVerifycode();//获取表单verifycode
		if(verifycode==null ||verifycode.trim().isEmpty()){
			errors.put("verifycode", "验证码不能为空");
		}else if(verifycode.length()!=5){
			errors.put("verifycode", "验证码必须5位");
		}else if(!sessionVerifyCode.equalsIgnoreCase(verifycode)){
			errors.put("verifycode", "验证码错误！");
		}
	
		/**
		 * 判读map是否为空。说明存在错误
		 */
		if(errors !=null && errors.size()>0){
			/**
			 * 1)保存errors到request域中
			 * 2)保存form到request域中
			 * 3)转发到register.jsp
			 */
			request.setAttribute("errors", errors);
			request.setAttribute("user", form);
			request.getRequestDispatcher("/user/register.jsp").forward(request, response);
			return;
		}
		
		
		
		
/*		*//**
		 * 验证码校验
		 *//*
		String sessionVerifyCode=(String) request.getSession().getAttribute("session_vcode");
		if(!sessionVerifyCode.equalsIgnoreCase(form.getVerifycode())){
			request.setAttribute("msg", "验证码错误！");
			request.setAttribute("user", form);
			request.getRequestDispatcher("/user/register.jsp").forward(request, response);
			return;
		}
		*/
		try {
			us.register(form);
			response.getWriter().print("<h1>注册成功</h1><a href='"+request.getContextPath()+"/user/login.jsp'>点击这里登陆</a>");
		} catch (UserException e) {
			//获取异常信息，保存到request域中，转发给register.jsp
			request.setAttribute("msg", e.getMessage());
			//保存表单数据到request域中，增加回显功能
			request.setAttribute("user", form);
			request.getRequestDispatcher("/user/register.jsp").forward(request, response);
		}
		
	}

}
