package com.testvue.sys.code;

import java.util.List;

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
 * 공통코드관리 화면의 API. 그룹 아래 코드는 /code-groups/{그룹코드}/codes 로 다룬다.
 * </pre>
 */
@RestController
@RequestMapping("/api/sys/code-groups")
public class CodeController {

	@Autowired
	private CodeService codeService;

	@GetMapping
	public List<CodeGroup> list(@RequestParam(required = false) String keyword, HttpSession session) {
		return codeService.getCodeGroups(SessionUtil.getLoginUser(session), keyword);
	}

	@PostMapping
	public void add(@RequestBody CodeGroup group, HttpSession session) {
		codeService.addCodeGroup(SessionUtil.getLoginUser(session), group);
	}

	@PutMapping("/{grpCmmCd}")
	public void modify(@PathVariable String grpCmmCd, @RequestBody CodeGroup group, HttpSession session) {
		codeService.modifyCodeGroup(SessionUtil.getLoginUser(session), grpCmmCd, group);
	}

	@DeleteMapping("/{grpCmmCd}")
	public void remove(@PathVariable String grpCmmCd, HttpSession session) {
		codeService.removeCodeGroup(SessionUtil.getLoginUser(session), grpCmmCd);
	}

	@GetMapping("/{grpCmmCd}/codes")
	public List<Code> codes(@PathVariable String grpCmmCd, HttpSession session) {
		return codeService.getCodes(SessionUtil.getLoginUser(session), grpCmmCd, null);
	}

	@PostMapping("/{grpCmmCd}/codes")
	public void addCode(@PathVariable String grpCmmCd, @RequestBody Code code, HttpSession session) {
		codeService.addCode(SessionUtil.getLoginUser(session), grpCmmCd, code);
	}

	@PutMapping("/{grpCmmCd}/codes/{cdVl}")
	public void modifyCode(@PathVariable String grpCmmCd, @PathVariable String cdVl, @RequestBody Code code, HttpSession session) {
		codeService.modifyCode(SessionUtil.getLoginUser(session), grpCmmCd, cdVl, code);
	}

	@DeleteMapping("/{grpCmmCd}/codes/{cdVl}")
	public void removeCode(@PathVariable String grpCmmCd, @PathVariable String cdVl, HttpSession session) {
		codeService.removeCode(SessionUtil.getLoginUser(session), grpCmmCd, cdVl);
	}
}
