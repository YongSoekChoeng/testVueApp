package com.testvue.sys.code;

/**
 * <pre>
 * 공통코드 그룹(TBCM_COMMON_CODE_GROUP).
 * </pre>
 */
public class CodeGroup {

	private String grpCmmCd;
	private String grpCmmCdNm;
	private String grpCmmCdDesc;
	private String systemGubun;
	private Integer sortNo;

	public String getGrpCmmCd() {
		return grpCmmCd;
	}

	public void setGrpCmmCd(String grpCmmCd) {
		this.grpCmmCd = grpCmmCd;
	}

	public String getGrpCmmCdNm() {
		return grpCmmCdNm;
	}

	public void setGrpCmmCdNm(String grpCmmCdNm) {
		this.grpCmmCdNm = grpCmmCdNm;
	}

	public String getGrpCmmCdDesc() {
		return grpCmmCdDesc;
	}

	public void setGrpCmmCdDesc(String grpCmmCdDesc) {
		this.grpCmmCdDesc = grpCmmCdDesc;
	}

	public String getSystemGubun() {
		return systemGubun;
	}

	public void setSystemGubun(String systemGubun) {
		this.systemGubun = systemGubun;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
}
