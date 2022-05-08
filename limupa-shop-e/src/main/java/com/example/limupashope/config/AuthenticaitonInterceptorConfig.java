package com.example.limupashope.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.limupashope.interceptor.AdminAuthenticationInterceptor;

@Configuration
public class AuthenticaitonInterceptorConfig implements WebMvcConfigurer{

	@Autowired
	private AdminAuthenticationInterceptor adminAuthenticationInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//Khi truy cap vao link có chứa '/admin/**' thi se goi intercepter	
		registry.addInterceptor(adminAuthenticationInterceptor)
			.addPathPatterns("/admin/**");
	}
}
