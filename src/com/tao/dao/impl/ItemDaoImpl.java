package com.tao.dao.impl;

import com.tao.dao.ItemDao;
import com.tao.entity.Cart;
import com.tao.entity.Item;
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

public class ItemDaoImpl implements ItemDao {

    @Override
    public List<Item> orderItem(String oId) {
        Connection connection = C3P0DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            String sql = "select * from item join product on item.p_id= product.p_id where o_id=?";
            ps = connection.prepareStatement(sql);
            ps.setObject(1, oId);
            resultSet = ps.executeQuery();
            List<Item>  itemList=new ArrayList<>();
            while (resultSet.next()) {
                int   i_id = (int) resultSet.getObject("i_id");
                String o_id = (String) resultSet.getObject("o_id");
                int  p_id = (int) resultSet.getObject("p_id");
                BigDecimal i_count = (BigDecimal) resultSet.getObject("i_count");
                double aDouble = Double.parseDouble(String.valueOf(i_count));
                int i_num = (int) resultSet.getObject("i_num");

                int t_id = (int) resultSet.getObject("t_id");
                int pId = (int) resultSet.getObject("p_id");
                Date p_time = (Date) resultSet.getObject("p_time");
                String p_name= (String) resultSet.getObject("p_name");
                BigDecimal p_price = (BigDecimal) resultSet.getObject("p_price");
                String p_image = (String) resultSet.getObject("p_image");
                double price = Double.parseDouble(String.valueOf(p_price));
                int p_state = (int) resultSet.getObject("p_state");
                String p_info = (String) resultSet.getObject("p_info");
                Product product = new Product(p_id,p_name,p_time,p_image,price,p_state,p_info);
                Item item = new Item(i_id,o_id,p_id,aDouble,i_num,product);
                itemList.add(item);
                System.out.println(item);
            }
            return itemList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            C3P0DBUtils.closeAll(resultSet, ps, connection);
        }
        return null;
    }

    @Override
    public int addItem(Cart cart, String oid) {
        QueryRunner queryRunner=new QueryRunner();
        Connection connection = C3P0DBUtils.getConnection();
        try {
            C3P0DBUtils.beginTransAction();
            Object [] objects={oid,cart.getProduct().getP_id(),cart.getC_count(),cart.getC_num()};
            int update = queryRunner.update(connection, "insert into item( o_id, p_id, i_count, i_num )value(?,?,?,?)", objects);
            C3P0DBUtils.commitTransAction();
            return update;
        } catch (Exception e) {
            C3P0DBUtils.rollBackTransAction();
            e.printStackTrace();
        } finally {
        }
        return 0;
    }
}
