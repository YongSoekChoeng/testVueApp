package com.testvue.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <pre>
 * 화면 주소(/login, /sys/codes …)로 바로 들어오거나 새로고침하면 index.html을 준다.
 * 어느 화면을 그릴지는 브라우저의 vue-router가 정한다.
 * </pre>
 */
@Controller
public class SpaController {

	@GetMapping({ "/login", "/home", "/sys/**", "/sample/**" })
	public String forward() {
		return "forward:/index.html";
	}
}
