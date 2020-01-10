package com.cb.lisener;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.cb.action.ActionMethodClazz;
import com.cb.action.MethodClazz;
import com.cb.msg.Action;
import com.cb.msg.PbAction;
import com.cb.util.AssertUtil;
import com.cb.util.ContextUtil;
import com.cb.util.PackageUtil;

import io.netty.channel.Channel;

public abstract class AbsLisener {
	public Map<Short, MethodClazz> cbActionMap = new ConcurrentHashMap<Short, MethodClazz>();
	public Map<Short, ActionMethodClazz> pbActionMap = new ConcurrentHashMap<Short, ActionMethodClazz>();
	
	protected abstract String getCbActionPath();
	
	protected abstract String getPbActionPath();
	
	public abstract short getLoginMsgcd();
	
	public abstract void channelInactive(Channel channel);
	
	public void scanCbAction() {
		String actionPath = getCbActionPath();
		Set<Class<?>> clazzSet = PackageUtil.getClasses(actionPath);
		for (Class<?> clazz : clazzSet) {
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				Action[] actions = method.getAnnotationsByType(Action.class);
				for (Action action : actions) {
					if (method.isAnnotationPresent(Action.class)) {
						Object instance = ContextUtil.getBean(clazz);
						AssertUtil.asErrorTrue(!cbActionMap.containsKey(action.value()), String.format("Action=%s重复", action.value()));
						AssertUtil.asErrorTrue(!pbActionMap.containsKey(action.value()), String.format("Action=%s重复", action.value()));
						cbActionMap.put(action.value(), new MethodClazz(method, instance));
					}
				}
			}
		}
    }
	
	public void scanPbAction() {
		String actionPath = getPbActionPath();
		Set<Class<?>> clazzSet = PackageUtil.getClasses(actionPath);
		for (Class<?> clazz : clazzSet) {
			Method[] methods = clazz.getMethods();
			for (Method actionMethod : methods) {
				PbAction[] actions = actionMethod.getAnnotationsByType(PbAction.class);
				for (PbAction action : actions) {
					if (actionMethod.isAnnotationPresent(PbAction.class)) {
						Object actionClassInstance = ContextUtil.getBean(clazz);
						AssertUtil.asErrorTrue(!cbActionMap.containsKey(action.value()), String.format("Action=%s重复", action.value()));
						AssertUtil.asErrorTrue(!pbActionMap.containsKey(action.value()), String.format("Action=%s重复", action.value()));
						// 获取到参数类型
						Type type = actionMethod.getGenericParameterTypes()[0];
						// 转换为参数化类型
						ParameterizedType parameterizedType = (ParameterizedType)type;
						// 获取到实际的ProtoBuf反射类型
				        Type actualType = parameterizedType.getActualTypeArguments()[0];
				        // 将实际的Type转为Class
				        Class<?> actualClazz = (Class<?>)actualType;
						ActionMethodClazz actionMethodClazz = new ActionMethodClazz(actionClassInstance, actionMethod, actualClazz);
						pbActionMap.put(action.value(), actionMethodClazz);
					}
				}
			}
		}
	}
}
