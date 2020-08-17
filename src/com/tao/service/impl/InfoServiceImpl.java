package com.tao.service.impl;

import com.tao.dao.impl.AddressDaoImpl;
import com.tao.entity.Address;
import com.tao.service.InfoService;

import java.util.List;

public class InfoServiceImpl implements InfoService {
    @Override
    public int insertAddr(Address address) {
        AddressDaoImpl addressDao = new AddressDaoImpl();
        int i = addressDao.insertAddr(address);
        return i;
    }

    @Override
    public List<Address> selectAdd(int u_id) {
        AddressDaoImpl addressDao = new AddressDaoImpl();
        List<Address> addresses = addressDao.selectAddr(u_id);
        return addresses;
    }

    @Override
    public int deleteAddr(int aidInt) {
        AddressDaoImpl addressDao = new AddressDaoImpl();
        int i = addressDao.deleteAddr(aidInt);
        return i;
    }

    @Override
    public boolean defaultAddr(int u_id, int aidInt) {
        AddressDaoImpl addressDao = new AddressDaoImpl();
        boolean b = addressDao.defaultAddr(u_id, aidInt);
        return b;
    }

    @Override
    public int updateAddr(Address address) {
        AddressDaoImpl addressDao = new AddressDaoImpl();
        int i = addressDao.updateAddr(address);
        return i;
    }
}
