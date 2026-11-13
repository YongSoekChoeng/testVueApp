package com.testvue.auth;

/**
 * <pre>
 * 메뉴 한 줄. menuUrl 은 화면 경로(vue-router path)다. 상위 메뉴는 "NONE".
 * </pre>
 */
public class Menu {

	private String menuId;
	private String prntMenuId;
	private String menuNm;
	private String menuUrl;
	private int menuLvl;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getPrntMenuId() {
		return prntMenuId;
	}

	public void setPrntMenuId(String prntMenuId) {
		this.prntMenuId = prntMenuId;
	}

	public String getMenuNm() {
		return menuNm;
	}

	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public int getMenuLvl() {
		return menuLvl;
	}

	public void setMenuLvl(int menuLvl) {
		this.menuLvl = menuLvl;
	}
}
