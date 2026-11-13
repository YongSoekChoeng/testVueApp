package com.testvue.sample.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.testvue.common.auth.SessionUtil;

/**
 * <pre>
 * 샘플게시판 화면(목록, 상세, 쓰기)의 API.
 * </pre>
 */
@RestController
@RequestMapping("/api/sample/boards")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping
	public List<Board> list(@RequestParam(required = false) String title, HttpSession session) {
		return boardService.getBoards(SessionUtil.getLoginUser(session), title);
	}

	@GetMapping("/{seq}")
	public Board detail(@PathVariable long seq, HttpSession session) {
		return boardService.getBoard(SessionUtil.getLoginUser(session), seq);
	}

	@PostMapping
	public Map<String, Object> add(@RequestBody Board board, HttpSession session) {
		Long seq = boardService.addBoard(SessionUtil.getLoginUser(session), board);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("seq", seq);
		return result;
	}

	@PutMapping("/{seq}")
	public void modify(@PathVariable long seq, @RequestBody Board board, HttpSession session) {
		boardService.modifyBoard(SessionUtil.getLoginUser(session), seq, board);
	}

	@DeleteMapping("/{seq}")
	public void remove(@PathVariable long seq, HttpSession session) {
		boardService.removeBoard(SessionUtil.getLoginUser(session), seq);
	}
}
