package com.tao.dao;

import com.tao.entity.User;

public interface UserDao {
    public int insert(User user);
    public int delete(String  name);
    public int update(String name);
    public User select(String name);
    public int updateStatus(String name);
}
