package com.testvue.sys.loginhist;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <pre>
 * 로그인 이력. SQL은 mapper/sys/LoginHistMapper.xml
 * </pre>
 */
@Mapper
public interface LoginHistMapper {

	List<LoginHist> selectLoginHistList(@Param("mbId") String mbId, @Param("fromYmd") String fromYmd, @Param("toYmd") String toYmd,
			@Param("loginId") String loginId);
}
