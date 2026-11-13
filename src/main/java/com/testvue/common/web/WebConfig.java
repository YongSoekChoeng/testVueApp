package com.testvue.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.testvue.common.auth.LoginInterceptor;

/**
 * <pre>
 * /api/** 는 로그인 확인을 거친다. 로그인 요청만 뺀다.
 * </pre>
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private LoginInterceptor loginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/api/**")
				.excludePathPatterns("/api/auth/login");
	}
}
