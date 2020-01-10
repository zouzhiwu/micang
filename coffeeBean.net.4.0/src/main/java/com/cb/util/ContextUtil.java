package com.cb.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cb.enm.ProfileType;

public class ContextUtil {
	
	private static String cxt = "spring/applicationContext.xml";
	private static ApplicationContext beanFactory = new ClassPathXmlApplicationContext(cxt);
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanId) {
		return (T) beanFactory.getBean(beanId);
	}
	
	public static <T> T getBean(Class<T> clazz) {
		return beanFactory.getBean(clazz);
	}
	
	public static ProfileType getProfile() {
		String strProfile = beanFactory.getEnvironment().getProperty("profile");
		return ProfileType.getType(strProfile);
	}
}
