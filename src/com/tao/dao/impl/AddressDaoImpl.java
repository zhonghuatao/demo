package com.tao.dao.impl;

import com.tao.dao.AddressDao;
import com.tao.entity.Address;
import com.tao.utils.C3P0DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddressDaoImpl implements AddressDao {

    @Override
    public int insertAddr(Address address) {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = C3P0DBUtils.getConnection();
        Object[] object={address.getU_id(),address.getA_name(),address.getA_phone(),address.getA_detail(),address.getA_state()};
        int update = 0;
        try {
            C3P0DBUtils.beginTransAction();
            update = queryRunner.update(connection, "insert into address( u_id, a_name, a_phone, a_detail, a_state)values(?,?,?,?,?)", object);
            C3P0DBUtils.commitTransAction();
            return update;
        } catch (SQLException e) {
            C3P0DBUtils.rollBackTransAction();
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public List<Address> selectAddr(int uid) {
        QueryRunner queryRunner = new QueryRunner(C3P0DBUtils.getDataSource());
        try {
            List<Address> query = queryRunner.query("select * from address where u_id=?",new BeanListHandler<Address>(Address.class),uid);
            if (query!=null){
                return query;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteAddr(int aidInt) {
        QueryRunner queryRunner = new QueryRunner();
        try {
            Connection connection = C3P0DBUtils.getConnection();
            C3P0DBUtils.beginTransAction();
            int update = queryRunner.update(connection, "delete from address where  a_id =?", aidInt);
            C3P0DBUtils.commitTransAction();
            return update;
        } catch (SQLException e) {
            C3P0DBUtils.rollBackTransAction();
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean defaultAddr(int u_id, int aidInt) {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = C3P0DBUtils.getConnection();
        try {
            C3P0DBUtils.beginTransAction();
            int update = queryRunner.update(connection, "update address set a_state=0 where u_id=?", u_id);
            int update2 = queryRunner.update(connection, "update address set a_state=1 where u_id=? and a_id=?", u_id,aidInt);
            C3P0DBUtils.commitTransAction();
            if (update>0&&update2>0){
                return true;
            }
        } catch (SQLException e) {
            C3P0DBUtils.rollBackTransAction();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int updateAddr(Address address) {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = C3P0DBUtils.getConnection();
        try {
            C3P0DBUtils.beginTransAction();
            Object[] objects={address.getA_name(),address.getA_phone(),address.getA_detail(),address.getA_id()};
            int update = queryRunner.update(connection, "update address set a_name=?,a_phone=? ,a_detail=? where a_id=?",objects);
            C3P0DBUtils.commitTransAction();
            return update;
        } catch (SQLException e) {
            C3P0DBUtils.rollBackTransAction();
            e.printStackTrace();
        }
        return 0;
    }
}
