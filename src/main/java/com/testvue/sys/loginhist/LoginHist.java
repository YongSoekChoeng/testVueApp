package com.testvue.sys.loginhist;

/**
 * <pre>
 * 로그인 이력 한 줄(TBSY_LOGIN_HIST + 계정, 직원 이름).
 * </pre>
 */
public class LoginHist {

	private String loginDtm;
	private String mbId;
	private String loginId;
	private String empNm;
	private String loginIp;

	public String getLoginDtm() {
		return loginDtm;
	}

	public void setLoginDtm(String loginDtm) {
		this.loginDtm = loginDtm;
	}

	public String getMbId() {
		return mbId;
	}

	public void setMbId(String mbId) {
		this.mbId = mbId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getEmpNm() {
		return empNm;
	}

	public void setEmpNm(String empNm) {
		this.empNm = empNm;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
}
