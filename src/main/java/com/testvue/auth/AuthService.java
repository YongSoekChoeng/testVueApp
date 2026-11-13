package com.testvue.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testvue.common.auth.LoginUser;
import com.testvue.common.exception.BizException;
import com.testvue.common.util.PasswordUtil;

/**
 * <pre>
 * 로그인 확인과 메뉴 조회.
 * </pre>
 */
@Service
public class AuthService {

	@Autowired
	private AuthMapper authMapper;

	/**
	 * <pre>
	 * 회사코드 + 아이디 + 비밀번호를 확인하고, 맞으면 로그인 이력을 남긴다.
	 * </pre>
	 */
	@Transactional
	public LoginUser login(LoginRequest request, String loginIp) {
		LoginAccount account = authMapper.selectLoginAccount(request.getMbId(), request.getLoginId());
		if (account == null || !PasswordUtil.sha256(request.getPassword()).equals(account.getUserPwd())) {
			throw new BizException("회사코드, 아이디 또는 비밀번호가 맞지 않습니다.");
		}
		if ("Y".equals(account.getAcctLockType())) {
			throw new BizException("잠긴 계정입니다.");
		}
		authMapper.insertLoginHist(account.getMbId(), account.getEmpCd(), loginIp);

		LoginUser user = new LoginUser();
		user.setMbId(account.getMbId());
		user.setMbNm(account.getMbNm());
		user.setEmpCd(account.getEmpCd());
		user.setEmpNm(account.getEmpNm());
		user.setLoginId(account.getLoginId());
		return user;
	}

	public List<Menu> getMenus(String mbId) {
		return authMapper.selectMenuList(mbId);
	}
}
