package com.testvue.auth;

/**
 * <pre>
 * 로그인할 때 DB에서 읽는 계정 한 줄(비밀번호 해시 포함). 화면으로는 내보내지 않는다.
 * </pre>
 */
public class LoginAccount {

	private String mbId;
	private String mbNm;
	private String empCd;
	private String empNm;
	private String loginId;
	private String userPwd;
	private String acctLockType;

	public String getMbId() {
		return mbId;
	}

	public void setMbId(String mbId) {
		this.mbId = mbId;
	}

	public String getMbNm() {
		return mbNm;
	}

	public void setMbNm(String mbNm) {
		this.mbNm = mbNm;
	}

	public String getEmpCd() {
		return empCd;
	}

	public void setEmpCd(String empCd) {
		this.empCd = empCd;
	}

	public String getEmpNm() {
		return empNm;
	}

	public void setEmpNm(String empNm) {
		this.empNm = empNm;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getAcctLockType() {
		return acctLockType;
	}

	public void setAcctLockType(String acctLockType) {
		this.acctLockType = acctLockType;
	}
}
