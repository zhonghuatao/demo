package com.tao.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3P0DBUtils {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    private static DataSource pds;
    static {
        try {
            pds = new ComboPooledDataSource("MySql");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {

        Connection connection = threadLocal.get();
        if (connection == null) {
            try {
                connection = pds.getConnection();
                threadLocal.set(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    //开启事务
    public static void beginTransAction() {
        Connection connection = getConnection();
        try {
//            关闭自动提交
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //    回滚事务
    public static void rollBackTransAction() {
        Connection connection = getConnection();
        try {
//            回滚
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, null, connection);
        }
    }


    //提交事务
    public static void commitTransAction() {
        Connection connection = getConnection();
        try {
//            提交
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, null, connection);
        }
    }

    public static void closeAll(ResultSet resultSet, PreparedStatement pst, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (connection != null) {
                connection.close();
                threadLocal.remove();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(){
        return pds;
    }
}
