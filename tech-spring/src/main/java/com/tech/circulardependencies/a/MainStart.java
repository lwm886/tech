package com.tech.circulardependencies.a;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

import javax.print.attribute.standard.MediaSize;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lw
 * @since 2023-05-10
 */
public class MainStart {
    private static Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    //循环依赖标识
    public static Set<String> singletonsCurrenlyInCreation = new HashSet<>();

    //一级缓存
    public static Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    //二级缓存  为了将成熟Bean和纯净Bean分离，避免读取到不完整的Bean
    public static Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>();

    //三级缓存
    public static Map<String, ObjectFactory> singletonFactories = new ConcurrentHashMap<>();


    //读取Bean定义，Spring内部是根据配置动态扫描注册Bean定义
    public static void loadBeanDefinitions() {
        RootBeanDefinition aBeanDefinition = new RootBeanDefinition(InstanceA.class);
        RootBeanDefinition bBeanDefinition = new RootBeanDefinition(InstanceB.class);
        beanDefinitionMap.put("instanceA", aBeanDefinition);
        beanDefinitionMap.put("instanceB", bBeanDefinition);
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        //加载Bean定义
        loadBeanDefinitions();
        //注册Bean的后置处理器

        //循环创建Bean
        for (String key : beanDefinitionMap.keySet()) {
            //先创建A
            getBean(key);
        }

        InstanceA instanceA = (InstanceA) getBean("instanceA");
        instanceA.say();
    }

    //假设A 使用了Aop @PointCut("execution(* *..InstanceA.*(..))") 要给A创建动态代理
    //获取Bean
    private static Object getBean(String beanName) throws IllegalAccessException, InstantiationException {
        Object singleton = getSingleton(beanName);
        if (singleton != null) {
            return singleton;
        }

        //正在创建
        if (!singletonsCurrenlyInCreation.contains(beanName)) {
            singletonsCurrenlyInCreation.add(beanName);
        }
        //createBean

        //实例化
        RootBeanDefinition beanDefinition = (RootBeanDefinition) beanDefinitionMap.get(beanName);
        Class<?> beanClass = beanDefinition.getBeanClass();
        Object instanceBean = beanClass.newInstance(); //通过无参构造函数

        //创建动态代理 （耦合、BeanPostProcessor） Spring还是希望正常的Bean还是再初始化后创建
        //只在循环依赖的情况下在实例化后创建Proxy  判断当前是不是循环依赖
//        singletonFactories.put(beanName,()->new JdkProxyBeanPostProcessor().getEarlyBeanReference(earlySingletonObjects.get(beanName),beanName));
        singletonFactories.put(beanName, () -> new JdkProxyBeanPostProcessor().getEarlyBeanReference(earlySingletonObjects.get(beanName), beanName));

        //添加到二级缓存
//        earlySingletonObjects.put(beanName,instanceBean);

        //属性赋值
        Field[] declaredFields = beanClass.getDeclaredFields();
        for(Field field:declaredFields){
            Annotation annotation = field.getAnnotation(Autowired.class);
            if(annotation!=null){
                field.setAccessible(true);
                String name = field.getName();
                Object fieldObject = getBean(name);
                field.set(instanceBean,fieldObject);
            }
        }

        //初始化 init-method
        //放在这里创建完了 B里面的A不是Proxy
        //正常情况下会在初始化后创建Proxy

        //由于递归完A还是原实例，所以还要从二级缓存中拿到Proxy
        if(earlySingletonObjects.containsKey(beanName)){
            instanceBean=earlySingletonObjects.get(beanName);
        }

        //添加到一级缓存
        singletonObjects.put(beanName,instanceBean);

//        remove二级缓存 三级缓存
        return instanceBean;
    }

    private static Object getSingleton(String beanName) {
        Object bean = singletonObjects.get(beanName);
        //说明是循环依赖
        if (bean == null && singletonsCurrenlyInCreation.contains(beanName)) {
            bean = earlySingletonObjects.get(beanName);
        }
        //如果二级缓存没有就从三级缓存拿
        if (bean == null) {
            //从三级缓存中拿
            ObjectFactory factory = singletonFactories.get(beanName);
            if(factory!=null){
                bean = factory.getObject(); //拿到动态代理
                earlySingletonObjects.put(beanName,bean);
            }
        }
        return bean;
    }


}
