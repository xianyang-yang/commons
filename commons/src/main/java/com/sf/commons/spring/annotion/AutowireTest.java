package com.sf.commons.spring.annotion;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 描述：spring 属性自动注入测试类
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           	   		PERSON          REASON
 *  1    2016年1月8日 下午8:42:09	    204401          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author xianyang.yang
 * @since 1.0
 */
public class AutowireTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:com/sf/commons/spring/annotion/app-config.xml");
		IMyDao service = context.getBean("myService", MyService.class);
		service.save("yang");

		IMyDao service1 = context.getBean("myService", MyService.class);
		service1.save("yang");

		context.close();
	}
}
