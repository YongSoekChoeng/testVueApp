package com.testvue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <pre>
 * testVue 서버. 화면(Vue)은 frontend/ 에서 빌드해 static/ 으로 함께 내보내고, 서버는 /api/** REST만 맡는다.
 * </pre>
 */
@SpringBootApplication
public class TestVueApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestVueApplication.class, args);
	}
}
