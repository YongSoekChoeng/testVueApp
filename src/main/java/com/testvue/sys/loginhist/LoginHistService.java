package com.testvue.sys.loginhist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testvue.common.auth.LoginUser;

/**
 * <pre>
 * 로그인 이력 조회(최근 500건).
 * </pre>
 */
@Service
public class LoginHistService {

	@Autowired
	private LoginHistMapper loginHistMapper;

	public List<LoginHist> getLoginHists(LoginUser user, String fromYmd, String toYmd, String loginId) {
		return loginHistMapper.selectLoginHistList(user.getMbId(), fromYmd, toYmd, loginId);
	}
}
