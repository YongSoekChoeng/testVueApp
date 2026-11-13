package com.testvue.auth;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <pre>
 * 로그인, 로그인 이력 기록, 메뉴. SQL은 mapper/auth/AuthMapper.xml
 * </pre>
 */
@Mapper
public interface AuthMapper {

	LoginAccount selectLoginAccount(@Param("mbId") String mbId, @Param("loginId") String loginId);

	int insertLoginHist(@Param("mbId") String mbId, @Param("empCd") String empCd, @Param("loginIp") String loginIp);

	List<Menu> selectMenuList(@Param("mbId") String mbId);
}
