package com.hk.board.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginIntercepter implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("mdto")==null) { // 로그인 x
			System.out.println("로그인 필요함ㅋ");
//			request.setAttribute("msg", "로그인이 필요할걸");
			response.sendRedirect("/user/login");
		}
		
		return true;
	}
	
	
}


















