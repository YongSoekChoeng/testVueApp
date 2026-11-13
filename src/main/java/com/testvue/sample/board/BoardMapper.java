package com.testvue.sample.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <pre>
 * 샘플게시판. SQL은 mapper/sample/BoardMapper.xml
 * </pre>
 */
@Mapper
public interface BoardMapper {

	List<Board> selectBoardList(@Param("mbId") String mbId, @Param("title") String title);

	Board selectBoard(@Param("mbId") String mbId, @Param("seq") long seq);

	int insertBoard(@Param("mbId") String mbId, @Param("userId") String userId, @Param("b") Board board);

	int updateBoard(@Param("mbId") String mbId, @Param("userId") String userId, @Param("b") Board board);

	int deleteBoard(@Param("mbId") String mbId, @Param("userId") String userId, @Param("seq") long seq);
}
