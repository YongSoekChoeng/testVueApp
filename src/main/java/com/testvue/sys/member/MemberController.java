package com.testvue.sys.member;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
 * 회원사관리 화면의 API.
 * </pre>
 */
@RestController
@RequestMapping("/api/sys/members")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@GetMapping
	public List<Member> list(@RequestParam(required = false) String mbNm, @RequestParam(required = false) String useYn) {
		return memberService.getMembers(mbNm, useYn);
	}

	@PostMapping
	public void add(@RequestBody Member member, HttpSession session) {
		memberService.addMember(SessionUtil.getLoginUser(session), member);
	}

	@PutMapping("/{mbId}")
	public void modify(@PathVariable String mbId, @RequestBody Member member, HttpSession session) {
		memberService.modifyMember(SessionUtil.getLoginUser(session), mbId, member);
	}
}
