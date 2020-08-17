package com.tao.dao.impl;

import com.tao.dao.UserDao;
import com.tao.entity.User;
import com.tao.utils.C3P0DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public int insert(User user) {
        QueryRunner queryRunner=new QueryRunner();
        Connection connection = C3P0DBUtils.getConnection();
        try {
            C3P0DBUtils.beginTransAction();
            Object [] objects={user.getU_name(),user.getU_password(),user.getU_email(),user.getU_sex(),user.getU_status(),user.getU_code(),user.getU_role()};
            int update = queryRunner.update(connection, "insert into user( u_name, u_password, u_email, u_sex, u_status, u_code, u_role)value(?,?,?,?,?,?,?)", objects);
            C3P0DBUtils.commitTransAction();
            return update;
        } catch (Exception e) {
            C3P0DBUtils.rollBackTransAction();
            e.printStackTrace();
        } finally {
        }
        return 0;
    }

    @Override
    public int delete(String name) {
        return 0;
    }

    @Override
    public int update(String name) {
        return 0;
    }

    @Override
    public User select(String name){
        QueryRunner queryRunner=new QueryRunner(C3P0DBUtils.getDataSource());
        User query = null;
        try {
            query = queryRunner.query("select * from user where u_name=?", new BeanHandler<User>(User.class),name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public int updateStatus(String name) {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = C3P0DBUtils.getConnection();
        try {
            C3P0DBUtils.beginTransAction();
            int update = queryRunner.update(connection, "update user set u_status=1 where u_name=?",name);
            C3P0DBUtils.commitTransAction();
            return update;
        } catch (SQLException e) {
            C3P0DBUtils.rollBackTransAction();
            e.printStackTrace();
        }
        return 0;
    }

}
