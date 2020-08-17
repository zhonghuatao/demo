package com.tao.service;

import com.tao.entity.User;

public interface LoginService {
    public User login(String name, String password);
    public boolean checkUserName(String name);
    public User autoLogin(String name);
}
