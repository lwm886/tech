package com.tech.transaction.c;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lw
 * @since 2023-05-25
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        jdbcTemplate.execute("insert into sys_dy_user(name) values ("+"'"   +user.getName()+"')");
    }

    @Override
    public User query(int id) {
        return jdbcTemplate.query("select * from sys_dy_user where id = ?", new BeanPropertyRowMapper<>(User.class), id).get(0);
    }
}
