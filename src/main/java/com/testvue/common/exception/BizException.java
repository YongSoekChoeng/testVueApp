package com.testvue.common.exception;

/**
 * <pre>
 * 업무 규칙 오류(중복 코드, 틀린 비밀번호 등). 화면에는 message 그대로 보여 준다.
 * </pre>
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BizException(String message) {
		super(message);
	}
}
