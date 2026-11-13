package com.testvue.sample.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testvue.common.auth.LoginUser;
import com.testvue.common.exception.BizException;

/**
 * <pre>
 * 샘플게시판. 삭제는 사용여부만 N으로 바꾼다.
 * </pre>
 */
@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;

	public List<Board> getBoards(LoginUser user, String title) {
		return boardMapper.selectBoardList(user.getMbId(), title);
	}

	public Board getBoard(LoginUser user, long seq) {
		Board board = boardMapper.selectBoard(user.getMbId(), seq);
		if (board == null) {
			throw new BizException("게시글이 없습니다: " + seq);
		}
		return board;
	}

	/**
	 * <pre>
	 * 새 글을 쓰고 글 번호를 돌려준다.
	 * </pre>
	 */
	@Transactional
	public Long addBoard(LoginUser user, Board board) {
		checkTitle(board);
		if (board.getWriterNm() == null || board.getWriterNm().length() == 0) {
			board.setWriterNm(user.getEmpNm());
		}
		boardMapper.insertBoard(user.getMbId(), user.getEmpCd(), board);
		return board.getSeq();
	}

	@Transactional
	public void modifyBoard(LoginUser user, long seq, Board board) {
		checkTitle(board);
		board.setSeq(Long.valueOf(seq));
		// 작성자를 비워 보내면 등록할 때처럼 로그인 사용자 이름을 넣는다
		if (board.getWriterNm() == null || board.getWriterNm().length() == 0) {
			board.setWriterNm(user.getEmpNm());
		}
		if (boardMapper.updateBoard(user.getMbId(), user.getEmpCd(), board) == 0) {
			throw new BizException("게시글이 없습니다: " + seq);
		}
	}

	@Transactional
	public void removeBoard(LoginUser user, long seq) {
		boardMapper.deleteBoard(user.getMbId(), user.getEmpCd(), seq);
	}

	private void checkTitle(Board board) {
		if (board.getTitle() == null || board.getTitle().trim().length() == 0) {
			throw new BizException("제목을 입력하세요.");
		}
	}
}
