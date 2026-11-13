package com.testvue.common.auth;

import java.io.Serializable;

/**
 * <pre>
 * 로그인한 사용자. 세션에 넣어 두고 요청마다 꺼내 쓴다.
 * </pre>
 */
public class LoginUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private String mbId;
	private String mbNm;
	private String empCd;
	private String empNm;
	private String loginId;

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
}
