package com.tomcat.test;

import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

public class TomcatDemo {
	public static final int PORT = 8080;
	public static final String CONTEXT_PATH = "/demo1";
	public static final String WEBAPP = "src/main/webapp/";
	public static final String SERVLET_NAME = "indexServlet";

	public static void main(String[] args) throws Exception {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(PORT);
		tomcat.getHost().setAutoDeploy(false);
		// 创建上下文
		StandardContext context = new StandardContext();
		context.setPath(CONTEXT_PATH);
		context.addLifecycleListener(new Tomcat.FixContextListener());
		tomcat.getHost().addChild(context);

		// 创建Servlet
		tomcat.addServlet(CONTEXT_PATH, SERVLET_NAME, new IndexServlet());
		// servlet映射
		context.addServletMappingDecoded("/index", SERVLET_NAME);
		tomcat.start();
		// 异步接受请求
		System.out.println("start success!");
		tomcat.getServer().await();
	}
}
