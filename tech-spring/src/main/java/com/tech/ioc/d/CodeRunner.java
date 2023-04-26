package com.tech.ioc.d;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *  创建一个AnnotationConfigApplicationContext会发生如下行为：
 *
 *  创建一个BeanFactory，这个BeanFactory扩展了很多接口如BeanDefinitionRegistry
 *
 *  创建一个BeanDefinitionReader,同时：将Spring内部的一些BeanFactory后置处理器和Bean后置处理器（如配置解析类就是一个BeanFactory后置处理器，它可以解析标记了config/import/componentScan注解的类，负责处理配置类）注册到BeanDefinition容器中
 *
 *  创建一个BeanDefinitionScanner，供外部程序通过scan扫描包中的bean转换成Bean注册到bean定义容器中，这个Scan并不是给Spring内部使用的，Spring内部会再单独创建一个Scanner去扫描包
 *
 *  将构造器中指定的配置类注册到Bean定义容器中，带configuration注解的类为Full配置类，不带configuration注解的类如带component注解为Lite配置类。
 *
 *  刷新IOC容器:
 *
 *
 *
 *
 *ADD:
 *  配置类解析类---BeanDefinitionRegistry后置处理器---解析配置类：扫描出配置的全部bean信息，读取这些bean创建Bean定义注册到bean定义容器中，然后在BeanFactory后置处理器中执行：如果配置类标记了configuration注解则对配置类进行cglib处理，保证Bean是单例的，在访问@bean的程序时，先检查单例池，有直接取出，没有创建保证单例。最后添加一个Bean的后置处理器干预Bean创建过程。
 *
 * Autowire解析类---Bean后置处理器
 *
 * 事件监听相关的类 等
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * @author lw
 * @since 2023-04-25
 */
public class CodeRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CarConfig.class);
//       applicationContext.scan("com.tech.ioc.d");
        Car bean = applicationContext.getBean(Car.class);
        System.out.println(bean);


    }
}
