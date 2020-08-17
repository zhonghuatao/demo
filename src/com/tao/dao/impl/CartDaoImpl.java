package com.tao.dao.impl;

import com.tao.dao.CartDao;
import com.tao.entity.Cart;
import com.tao.entity.Product;
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

public class CartDaoImpl implements CartDao {
    @Override
    public int insert(Cart cart) {
        QueryRunner queryRunner = new QueryRunner();
        try {
            Connection connection = C3P0DBUtils.getConnection();
            C3P0DBUtils.beginTransAction();
            Object [] objects={cart.getU_id(),cart.getP_id(),cart.getC_count(),cart.getC_num()};
            int update = queryRunner.update(connection, "insert into cart( u_id, p_id, c_count, c_num)values(?,?,?,?)", objects);
            C3P0DBUtils.commitTransAction();
            return update;
        } catch (SQLException e) {
            C3P0DBUtils.rollBackTransAction();
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int uid, int pid) {
        QueryRunner queryRunner = new QueryRunner();
        try {
            Connection connection = C3P0DBUtils.getConnection();
            C3P0DBUtils.beginTransAction();
            int update = queryRunner.update(connection, "delete from cart where u_id=? and p_id =?", uid, pid);
            C3P0DBUtils.commitTransAction();
            return update;
        } catch (SQLException e) {
            C3P0DBUtils.rollBackTransAction();
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Cart cart) {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = C3P0DBUtils.getConnection();
        try {
            C3P0DBUtils.beginTransAction();
            int update = queryRunner.update(connection, "update cart set c_count=?,c_num=? where c_id=?", cart.getC_count(), cart.getC_num(), cart.getC_id());
            C3P0DBUtils.commitTransAction();
            return update;
        } catch (SQLException e) {
            C3P0DBUtils.rollBackTransAction();
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Cart select(int uid, int pid) {
        Connection connection = C3P0DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            String sql = "select * from cart join product on product.p_id=cart.p_id where cart.u_id=? having cart.p_id=?";
            ps = connection.prepareStatement(sql);
            ps.setObject(1, uid);
            ps.setObject(2, pid);
            resultSet = ps.executeQuery();
            Cart cart = null;
            while (resultSet.next()) {
                int c_id = (int) resultSet.getObject("c_id");
                int pId = (int) resultSet.getObject("p_id");
                int u_id = (int) resultSet.getObject("u_id");
                BigDecimal c_count = (BigDecimal) resultSet.getObject("c_count");
                int c_num = (int) resultSet.getObject("c_num");
                double c_countD = Double.parseDouble(String.valueOf(c_count));

                int t_id = (int) resultSet.getObject("t_id");
                String p_name = (String) resultSet.getObject("p_name");
                Date p_time = (Date) resultSet.getObject("p_time");
                BigDecimal p_price = (BigDecimal) resultSet.getObject("p_price");
                String p_image = (String) resultSet.getObject("p_image");
                double price = Double.parseDouble(String.valueOf(p_price));
                String p_info = (String) resultSet.getObject("p_info");
                int p_state = (int) resultSet.getObject("p_state");
                Product product = new Product(t_id, pId, p_name, p_time, p_image, price, p_state, p_info);
                cart = new Cart(c_id, u_id, pId, c_countD, c_num, product);
            }
            return cart;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            C3P0DBUtils.closeAll(resultSet, ps, connection);
        }
        return null;
    }

    @Override
    public List<Cart> query(int uid) {
        Connection connection = C3P0DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Cart> carts = new ArrayList<>();
        try {
            String sql = "select * from cart join product on product.p_id=cart.p_id where cart.u_id=? ";
            ps = connection.prepareStatement(sql);
            ps.setObject(1, uid);
            resultSet = ps.executeQuery();
            Cart cart = null;
            while (resultSet.next()) {
                int pId = (int) resultSet.getObject("p_id");
                int c_id = (int) resultSet.getObject("c_id");
                int u_id = (int) resultSet.getObject("u_id");
                int c_num = (int) resultSet.getObject("c_num");
                BigDecimal c_count = (BigDecimal) resultSet.getObject("c_count");
                double c_countD = Double.parseDouble(String.valueOf(c_count));

                int t_id = (int) resultSet.getObject("t_id");
                Date p_time = (Date) resultSet.getObject("p_time");
                String p_name = (String) resultSet.getObject("p_name");
                String p_image = (String) resultSet.getObject("p_image");
                BigDecimal p_price = (BigDecimal) resultSet.getObject("p_price");
                double price = Double.parseDouble(String.valueOf(p_price));
                int p_state = (int) resultSet.getObject("p_state");
                String p_info = (String) resultSet.getObject("p_info");
                Product product = new Product(t_id, pId, p_name, p_time, p_image, price, p_state, p_info);
                cart = new Cart(c_id, u_id, pId, c_countD, c_num, product);
                carts.add(cart);
            }
            return carts;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            C3P0DBUtils.closeAll(resultSet, ps, connection);
        }
        return null;
    }

    @Override
    public int deleteAll(int uid) {
        QueryRunner queryRunner = new QueryRunner();
        try {
            Connection connection = C3P0DBUtils.getConnection();
            C3P0DBUtils.beginTransAction();
            int update = queryRunner.update(connection, "delete from cart where u_id=?", uid);
            C3P0DBUtils.commitTransAction();
            return update;
        } catch (SQLException e) {
            C3P0DBUtils.rollBackTransAction();
            e.printStackTrace();
        }
        return 0;
    }
}
