package com.testvue.common.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * <pre>
 * /api/** 요청은 로그인해야 쓸 수 있다. 세션이 없으면 401을 돌려주고, 화면은 로그인 화면으로 보낸다.
 * </pre>
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (SessionUtil.getLoginUser(request.getSession(false)) != null) {
			return true;
		}
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write("{\"message\":\"로그인이 필요합니다.\"}");
		return false;
	}
}
