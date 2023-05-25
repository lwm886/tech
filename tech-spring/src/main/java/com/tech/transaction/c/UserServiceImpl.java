package com.tech.transaction.c;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lw
 * @since 2023-05-25
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void save() {
        System.out.println("in save");
        User a = new User();
        a.setName("a");
        userDao.save(a);
    }

    @Override
    public void query() {
        System.out.println("in query");
        User query = userDao.query(1);
        System.out.println(query);
    }
}
