package com.tao.service;

import com.tao.entity.User;

public interface RegisterService {
    public boolean register(User user);
    public int active(String  name);
    public User selectUser(String  name);
}
