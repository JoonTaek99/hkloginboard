package com.hk.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginIntercepter())
					.order(1) // 우선순위 설정
					.addPathPatterns("/**")	// 전체 요청에 대해 적용
					.excludePathPatterns("/error", "/board/boardList","board/boardDetail",  "/", "/user/**", "/css/**","/js/**");
	
	}
}
