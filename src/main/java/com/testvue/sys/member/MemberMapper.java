package com.testvue.sys.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <pre>
 * 회원사. SQL은 mapper/sys/MemberMapper.xml
 * </pre>
 */
@Mapper
public interface MemberMapper {

	List<Member> selectMemberList(@Param("mbNm") String mbNm, @Param("useYn") String useYn);

	int countMember(@Param("mbId") String mbId);

	int insertMember(@Param("userId") String userId, @Param("m") Member member);

	int updateMember(@Param("userId") String userId, @Param("m") Member member);
}
