package com.tao.dao.impl;

import com.tao.dao.OrderDao;
import com.tao.entity.Address;
import com.tao.entity.Order;
import com.tao.utils.C3P0DBUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDaoImpl implements OrderDao {


    @Override
    public List<Order> selectOrderList(int u_id) {
        Connection connection = C3P0DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            String sql = "select * from orders join address on orders.a_id=address.a_id where orders.u_id=?";
            ps = connection.prepareStatement(sql);
            ps.setObject(1, u_id);
            resultSet = ps.executeQuery();
            List<Order> arrayList = new ArrayList<>();
            while (resultSet.next()) {
                String  o_id = (String) resultSet.getObject("o_id");
                int uid = (int) resultSet.getObject("u_id");
                int a_id = (int) resultSet.getObject("a_id");
                BigDecimal o_count = (BigDecimal) resultSet.getObject("o_count");
                double aDouble = Double.parseDouble(String.valueOf(o_count));
                Date o_time = (Date) resultSet.getObject("o_time");

                int o_state = (int) resultSet.getObject("o_state");


                String a_name = (String) resultSet.getObject("a_name");
                String a_phone = (String) resultSet.getObject("a_phone");
                String a_detail = (String) resultSet.getObject("a_detail");
                int a_state = (int) resultSet.getObject("a_state");

                Address address = new Address(a_id,a_name,a_phone,a_detail);
                Order order = new Order(o_id, u_id, a_id, aDouble, o_time, o_state, address);
                arrayList.add(order);
            }
           return arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            C3P0DBUtils.closeAll(resultSet, ps, connection);
        }
        return null;
    }

    @Override
    public Order selectOrder(String oId) {
        Connection connection = C3P0DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Order order = null;
        try {
            String sql = "select * from orders join address on orders.a_id=address.a_id where orders.o_id=? ";
            ps = connection.prepareStatement(sql);
            ps.setObject(1, oId);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String  o_id = (String) resultSet.getObject("o_id");
                int a_id = (int) resultSet.getObject("a_id");
                int uid = (int) resultSet.getObject("u_id");
                BigDecimal o_count = (BigDecimal) resultSet.getObject("o_count");
                double aDouble = Double.parseDouble(String.valueOf(o_count));
                int o_state = (int) resultSet.getObject("o_state");
                Date o_time = (Date) resultSet.getObject("o_time");
                String a_name = (String) resultSet.getObject("a_name");
                String a_phone = (String) resultSet.getObject("a_phone");
                int a_state = (int) resultSet.getObject("a_state");
                String a_detail = (String) resultSet.getObject("a_detail");
                Address address = new Address(a_id,a_name,a_phone,a_detail);
                order = new Order(o_id, uid, a_id, aDouble, o_time, o_state, address);
            }
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            C3P0DBUtils.closeAll(resultSet, ps, connection);
        }
        return null;
    }

    @Override
    public int insert(Order order) {
        QueryRunner queryRunner=new QueryRunner();
        Connection connection = C3P0DBUtils.getConnection();
        try {
            C3P0DBUtils.beginTransAction();
            Object [] objects={order.getO_id(),order.getU_id(),order.getA_id(),order.getO_count(),order.getO_time(),order.getO_state()};
            int update = queryRunner.update(connection, "insert into orders(o_id, u_id, a_id, o_count, o_time, o_state )value(?,?,?,?,?,?)", objects);
            C3P0DBUtils.commitTransAction();
            return update;
        } catch (Exception e) {
            C3P0DBUtils.rollBackTransAction();
            e.printStackTrace();
        }
        return 0;
    }
}
