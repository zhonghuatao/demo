package com.tao.service.impl;

import com.tao.dao.impl.UserDaoImpl;
import com.tao.entity.User;
import com.tao.service.RegisterService;
import com.tao.utils.MD5Utils;
import com.tao.utils.RandomUtils;

public class RegisterServiceImpl implements RegisterService {
    @Override
    public boolean register(User user) {
        UserDaoImpl userDao = new UserDaoImpl();
        String u_password = user.getU_password();
        String md5Pwd = MD5Utils.md5(u_password);
        user.setU_password(md5Pwd);
        user.setU_code(RandomUtils.createActive());
        int insert = userDao.insert(user);
        if (insert==1){
            return true;
        }
        return false;
    }

    @Override
    public int active(String name) {
        UserDaoImpl userDao = new UserDaoImpl();
        int i = userDao.updateStatus(name);
        return i;
    }

    @Override
    public User selectUser(String name) {
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.select(name);
        return user;
    }


}
