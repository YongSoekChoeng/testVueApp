package com.testvue.sys.code;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.testvue.common.auth.SessionUtil;

/**
 * <pre>
 * 화면의 콤보 상자가 쓰는 코드 목록. 사용 중(USE_YN = 'Y')인 코드만 준다.
 * </pre>
 */
@RestController
public class CommonCodeController {

	@Autowired
	private CodeService codeService;

	@GetMapping("/api/common/codes/{grpCmmCd}")
	public List<Code> codes(@PathVariable String grpCmmCd, HttpSession session) {
		return codeService.getCodes(SessionUtil.getLoginUser(session), grpCmmCd, "Y");
	}
}
