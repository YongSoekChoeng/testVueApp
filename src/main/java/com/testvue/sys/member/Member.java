package com.testvue.sys.member;

/**
 * <pre>
 * 회원사(TBSY_MEMBER). 계약일은 yyyyMMdd 글자.
 * </pre>
 */
public class Member {

	private String mbId;
	private String mbNm;
	private String representNm;
	private String telno;
	private String contFrymd;
	private String contToymd;
	private String useYn;
	private String memo;

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

	public String getRepresentNm() {
		return representNm;
	}

	public void setRepresentNm(String representNm) {
		this.representNm = representNm;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public String getContFrymd() {
		return contFrymd;
	}

	public void setContFrymd(String contFrymd) {
		this.contFrymd = contFrymd;
	}

	public String getContToymd() {
		return contToymd;
	}

	public void setContToymd(String contToymd) {
		this.contToymd = contToymd;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
