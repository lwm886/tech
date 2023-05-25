package com.tech.transaction.c;

/**
 * @author lw
 * @since 2023-05-25
 */
public interface UserDao {
    void save(User user);
    User query(int id);
}

