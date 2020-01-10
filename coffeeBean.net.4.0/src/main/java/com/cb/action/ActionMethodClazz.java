package com.cb.action;

import java.lang.reflect.Method;

public class ActionMethodClazz {
	private Object actionBean;
	private Method actionMethod;
	private Class<?> pbClazz;
	
	public ActionMethodClazz(Object actionBean, Method actionMethod, Class<?> pbClazz) {
		this.actionBean = actionBean;
		this.actionMethod = actionMethod;
		this.pbClazz = pbClazz;
	}
	
	public Object getActionBean() {
		return actionBean;
	}
	public void setActionBean(Object actionBean) {
		this.actionBean = actionBean;
	}
	public Method getActionMethod() {
		return actionMethod;
	}
	public void setActionMethod(Method actionMethod) {
		this.actionMethod = actionMethod;
	}
	public Class<?> getPbClazz() {
		return pbClazz;
	}
	public void setPbClazz(Class<?> pbClazz) {
		this.pbClazz = pbClazz;
	}
}
