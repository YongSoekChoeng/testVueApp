package com.testvue.sys.code;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <pre>
 * 공통코드 그룹과 코드. SQL은 mapper/sys/CodeMapper.xml
 * </pre>
 */
@Mapper
public interface CodeMapper {

	List<CodeGroup> selectCodeGroupList(@Param("mbId") String mbId, @Param("keyword") String keyword);

	int countCodeGroup(@Param("mbId") String mbId, @Param("grpCmmCd") String grpCmmCd);

	int insertCodeGroup(@Param("mbId") String mbId, @Param("userId") String userId, @Param("g") CodeGroup group);

	int updateCodeGroup(@Param("mbId") String mbId, @Param("userId") String userId, @Param("g") CodeGroup group);

	int deleteCodeGroup(@Param("mbId") String mbId, @Param("grpCmmCd") String grpCmmCd);

	List<Code> selectCodeList(@Param("mbId") String mbId, @Param("grpCmmCd") String grpCmmCd, @Param("useYn") String useYn);

	int countCode(@Param("mbId") String mbId, @Param("grpCmmCd") String grpCmmCd, @Param("cdVl") String cdVl);

	int insertCode(@Param("mbId") String mbId, @Param("userId") String userId, @Param("c") Code code);

	int updateCode(@Param("mbId") String mbId, @Param("userId") String userId, @Param("c") Code code);

	int deleteCode(@Param("mbId") String mbId, @Param("grpCmmCd") String grpCmmCd, @Param("cdVl") String cdVl);

	int deleteCodeByGroup(@Param("mbId") String mbId, @Param("grpCmmCd") String grpCmmCd);
}
