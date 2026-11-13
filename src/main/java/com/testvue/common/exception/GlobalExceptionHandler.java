package com.testvue.common.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * <pre>
 * 오류를 {"message": "..."} 모양으로 바꿔 돌려준다.
 * 업무 오류와 입력 오류는 400, 없는 주소는 404, 나머지는 500.
 * </pre>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(BizException.class)
	public ResponseEntity<Map<String, Object>> handleBiz(BizException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body(e.getMessage()));
	}

	/**
	 * <pre>
	 * @Valid 검사에 걸린 경우. 첫 번째 걸린 항목 이름을 알려준다.
	 * </pre>
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleInvalid(MethodArgumentNotValidException e) {
		String message = "입력값을 확인해 주세요.";
		FieldError field = e.getBindingResult().getFieldError();
		if (field != null) {
			message = "입력값을 확인해 주세요: " + field.getField();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body(message));
	}

	/**
	 * <pre>
	 * 요청 본문(JSON)을 읽지 못한 경우.
	 * </pre>
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Map<String, Object>> handleUnreadable(HttpMessageNotReadableException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body("요청 형식이 올바르지 않습니다."));
	}

	/**
	 * <pre>
	 * 없는 주소나 파일을 요청한 경우.
	 * </pre>
	 */
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<Map<String, Object>> handleNotFound(NoResourceFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body("찾을 수 없는 주소입니다."));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleOther(Exception e) {
		LOG.error("처리하지 못한 오류", e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body("서버 오류가 났습니다."));
	}

	private Map<String, Object> body(String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		return map;
	}
}
