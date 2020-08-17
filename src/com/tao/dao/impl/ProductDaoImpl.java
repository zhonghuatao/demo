package com.tao.dao.impl;

import com.tao.dao.ProductDao;
import com.tao.entity.Product;
import com.tao.entity.Type;
import com.tao.utils.C3P0DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> query() {
        QueryRunner queryRunner = new QueryRunner(C3P0DBUtils.getDataSource());
        try {
            List<Product> query = queryRunner.query("select * from product join type on product.t_id=type.t_id", new BeanListHandler<Product>(Product.class));
            if (query != null) {
                return query;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> select(int tid) {
        QueryRunner queryRunner = new QueryRunner(C3P0DBUtils.getDataSource());
        try {
            List<Product> query = queryRunner.query("select p_id, t_id, p_name, p_time, p_image, p_price, p_state, p_info from product where t_id=?", new BeanListHandler<Product>(Product.class), tid);
            if (query!=null){
                return query;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product selectProductDetail(int p_id) {
        Connection connection = C3P0DBUtils.getConnection();
        PreparedStatement ps=null;
        ResultSet resultSet=null;
        try {
            String sql= "select p_id, product.t_id, p_name, p_time, p_image, p_price, p_state, p_info, t_name from product join type on product.t_id=type.t_id where product.p_id=?" ;
            ps = connection.prepareStatement(sql);
            ps.setObject(1,p_id);
            resultSet= ps.executeQuery();
            Product product=null;
            while(resultSet.next()){
                int pId = (int) resultSet.getObject("p_id");
                int t_id = (int) resultSet.getObject("t_id");
                String p_name= (String) resultSet.getObject("p_name");
                Date p_time = (Date) resultSet.getObject("p_time");
                String p_image = (String) resultSet.getObject("p_image");
                BigDecimal p_price = (BigDecimal) resultSet.getObject("p_price");
                double price = Double.parseDouble(String.valueOf(p_price));
                int p_state = (int) resultSet.getObject("p_state");
                String p_info = (String) resultSet.getObject("p_info");
                String t_name = (String) resultSet.getObject("t_name");
                Type type = new Type(t_id,t_name,null);
                product = new Product(type,t_id,pId,p_name,p_time,p_image,price,p_state,p_info);
            }
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            C3P0DBUtils.closeAll(resultSet,ps,connection);
        }
        return null;
    }
}
