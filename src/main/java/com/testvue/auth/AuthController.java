package com.testvue.auth;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testvue.common.auth.LoginUser;
import com.testvue.common.auth.SessionUtil;

/**
 * <pre>
 * 로그인/로그아웃, 내 정보, 메뉴.
 * </pre>
 */
@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/auth/login")
	public LoginUser login(@Valid @RequestBody LoginRequest request, HttpServletRequest httpRequest) {
		LoginUser user = authService.login(request, httpRequest.getRemoteAddr());
		// 로그인할 때마다 세션을 새로 만든다(세션 고정 방지)
		HttpSession old = httpRequest.getSession(false);
		if (old != null) {
			old.invalidate();
		}
		SessionUtil.setLoginUser(httpRequest.getSession(true), user);
		return user;
	}

	@PostMapping("/auth/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}

	@GetMapping("/auth/me")
	public LoginUser me(HttpSession session) {
		return SessionUtil.getLoginUser(session);
	}

	@GetMapping("/menus")
	public List<Menu> menus(HttpSession session) {
		return authService.getMenus(SessionUtil.getLoginUser(session).getMbId());
	}
}
