package com.tech.ioc.n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author lw
 * @since 2023-05-09
 */
//value为普通Bean,导入普通Bean到Bean定义容器
//@Import(value = {Car.class,Tank.class})

//value为实现了ImportSelector的接口，会将接口返回的Bean导入到Bean定义容器，接口可以获取标记了Import注解的类的全部信息，可以根据这些信息动态导入Bean定义。还有一个延迟的ImportSelector，这个和普通的ImportSelector比较像，不同的是等其他配置都注册完Bean后再注册Bean定义。
//@Import(value = ServiceImportSelector.class)
//@Import(value = {ServiceDefreImportSelector.class,ServiceImportSelector.class})

//value为实现了ImportBeanDefinitionRegistrar的接口,会将接口返回的Bean导入到Bean定义容器，接口可以获取标记了Import注解的类的信息和BeanDefinitionRegistry,可以动态自定义的导入Bean定义，比如给Bean定义添加属性如设置属性值 scope等。
@Import(value = MyImportBeanDefinitionRegitrar.class)
@Configuration
public class CarConfig {

    @Bean
    public Plane plane(){
        Plane plane = new Plane();
        plane.setName("@bean 飞机");
        return plane;
    }
}
