package com.testvue.sys.code;

/**
 * <pre>
 * 공통코드(TBCM_COMMON_CODE).
 * </pre>
 */
public class Code {

	private String grpCmmCd;
	private String cdVl;
	private String cdVlNm;
	private String useYn;
	private Integer sortNo;

	public String getGrpCmmCd() {
		return grpCmmCd;
	}

	public void setGrpCmmCd(String grpCmmCd) {
		this.grpCmmCd = grpCmmCd;
	}

	public String getCdVl() {
		return cdVl;
	}

	public void setCdVl(String cdVl) {
		this.cdVl = cdVl;
	}

	public String getCdVlNm() {
		return cdVlNm;
	}

	public void setCdVlNm(String cdVlNm) {
		this.cdVlNm = cdVlNm;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
}
