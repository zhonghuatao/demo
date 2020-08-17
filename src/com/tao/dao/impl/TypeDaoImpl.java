package com.tao.dao.impl;

import com.tao.dao.TypeDao;
import com.tao.entity.Type;
import com.tao.utils.C3P0DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class TypeDaoImpl implements TypeDao {
    @Override
    public List<Type> query() {
        QueryRunner queryRunner = new QueryRunner(C3P0DBUtils.getDataSource());
        try {
            List<Type> query = queryRunner.query("select * from type", new BeanListHandler<Type>(Type.class));
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
