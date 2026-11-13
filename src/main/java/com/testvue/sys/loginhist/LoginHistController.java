package com.testvue.sys.loginhist;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.testvue.common.auth.SessionUtil;

/**
 * <pre>
 * 로그인이력 화면의 API. 날짜는 yyyyMMdd.
 * </pre>
 */
@RestController
public class LoginHistController {

	@Autowired
	private LoginHistService loginHistService;

	@GetMapping("/api/sys/login-hists")
	public List<LoginHist> list(@RequestParam(required = false) String fromYmd, @RequestParam(required = false) String toYmd,
			@RequestParam(required = false) String loginId, HttpSession session) {
		return loginHistService.getLoginHists(SessionUtil.getLoginUser(session), fromYmd, toYmd, loginId);
	}
}
