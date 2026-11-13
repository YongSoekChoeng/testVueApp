package com.testvue.common.auth;

import jakarta.servlet.http.HttpSession;

/**
 * <pre>
 * 세션에 든 로그인 사용자를 넣고 꺼낸다.
 * </pre>
 */
public final class SessionUtil {

	/** 세션 속성 이름 */
	public static final String LOGIN_USER = "LOGIN_USER";

	private SessionUtil() {
	}

	public static LoginUser getLoginUser(HttpSession session) {
		if (session == null) {
			return null;
		}
		return (LoginUser) session.getAttribute(LOGIN_USER);
	}

	public static void setLoginUser(HttpSession session, LoginUser user) {
		session.setAttribute(LOGIN_USER, user);
	}
}
