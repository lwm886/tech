package com.tech.aop.proxy.cglib1;

/**使用代理对象像进行函数嵌套调用，执行被调用函数时走代理
 * @author lw
 * @since 2023-05-19
 */
public class CGLibProxyTest {
    public static void main(String[] args) {
        CGLibProxy cgLibProxy = new CGLibProxy(new UserServiceImpl());
        UserService proxy =(UserService) cgLibProxy.getProxy();
        proxy.save();
    }
}
