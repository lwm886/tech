package com.tech.transaction.a;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

/**
 * @author lw
 * @since 2023-05-22
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void save() {
        System.out.println("in save");
        ((UserService)AopContext.currentProxy()).query();
    }

    @Override
    public void query() {
        System.out.println("in query");
    }
}
