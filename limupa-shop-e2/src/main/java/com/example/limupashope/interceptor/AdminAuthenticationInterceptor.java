package com.example.limupashope.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminAuthenticationInterceptor implements HandlerInterceptor {

	@Autowired
	private HttpSession session;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("pre handle of request " + request.getRequestURI());
		if (session.getAttribute("username") != null) {
			return true;
		}
		//Lưu giũ URI của trang bạn muốn khi đã đăng nhập thành công
		session.setAttribute("redirect-uri", request.getRequestURI());
		response.sendRedirect("/account/login");
		
		return false;
	}

}
