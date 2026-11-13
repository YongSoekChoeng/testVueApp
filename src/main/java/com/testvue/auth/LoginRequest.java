package com.testvue.auth;

import jakarta.validation.constraints.NotBlank;

/**
 * <pre>
 * 로그인 화면이 보내는 값.
 * </pre>
 */
public class LoginRequest {

	@NotBlank
	private String mbId;
	@NotBlank
	private String loginId;
	@NotBlank
	private String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
