package com.github.user.web.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.commons.utils.VerifyCode;

public class VerifyCodeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VerifyCode vc = new VerifyCode();
		BufferedImage image = vc.getImage();
		request.getSession().setAttribute("session_vcode", vc.getText());
		VerifyCode.outPut(image, response.getOutputStream());
	}

}
