package com.tech.aop.proxy.cglib1;


/**
 * @author lw
 * @since 2023-05-19
 */
public class UserServiceImpl implements UserService {


    @Override
    public void save() {
        query();
        System.out.println("in save");
    }

    @Override
    public void query() {
        System.out.println("in query");
    }
}
