package com.example.spring.boot.practise;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class PractiseApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(PractiseApplication.class, args);
	}
	
	
	@Bean
	public SessionLocaleResolver localResolver() {
		SessionLocaleResolver sessionLocResol = new SessionLocaleResolver();
		sessionLocResol.setDefaultLocale(Locale.ENGLISH);
		return sessionLocResol;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localChangIntercep = new LocaleChangeInterceptor();
		localChangIntercep.setParamName("lang");
		return localChangIntercep;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
}
