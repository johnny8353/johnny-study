package com.zte.msm.system;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/main/resources") // 1 web资源的位置，默认是src/main/webapp
@ContextHierarchy({ @ContextConfiguration(name = "parent", locations = "classpath:spring-config.xml"),
		@ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml") })
public class TestCaseCoverage {
	//
	@Test
	public void coverage() throws ClassNotFoundException {
		Integer serverCount = 0;
		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
		Set<BeanDefinition> beanDefinitions = provider.findCandidateComponents("com/zte/msm");
		for (BeanDefinition beanDefinition : beanDefinitions) {
			Class cl = this.getClass().getClassLoader().loadClass(beanDefinition.getBeanClassName());
			Method[] methods = cl.getDeclaredMethods();
			for (Method method : methods) {
				Annotation[] annotations = method.getAnnotations();
				for (Annotation annotation : annotations) {
					if (annotation.annotationType() == RequestMapping.class) {
						serverCount++ ;
						System.out.println(beanDefinition.getBeanClassName() + "\t"+"類注解方法：" + method.getName());
					}

				}
//				System.out.println(beanDefinition.getBeanClassName() + "\t" + beanDefinition.getResourceDescription()
//						+ "\t" + beanDefinition.getClass());
			}
		}
		
		System.out.println("总共服务数量："+serverCount);
		
	}
}
