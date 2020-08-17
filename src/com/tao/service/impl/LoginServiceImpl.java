package com.tao.service.impl;

import com.tao.dao.impl.UserDaoImpl;
import com.tao.entity.User;
import com.tao.service.LoginService;
import com.tao.utils.MD5Utils;

public class LoginServiceImpl implements LoginService {
    public User login(String name, String password) {

        UserDaoImpl userDao = new UserDaoImpl();
        User user = null;
        user = userDao.select(name);
        if (user != null) {
            String u_password = user.getU_password();
            String pwdMd5 = MD5Utils.md5(password);
            if (pwdMd5.equals(u_password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean checkUserName(String name) {
        UserDaoImpl userDao = new UserDaoImpl();
        User user = null;
        user = userDao.select(name);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public User autoLogin(String name) {
        UserDaoImpl userDao = new UserDaoImpl();
        User select = userDao.select(name);
        return select;
    }

}
