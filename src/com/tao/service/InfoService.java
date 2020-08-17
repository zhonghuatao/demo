package com.tao.service;

import com.tao.entity.Address;

import java.util.List;

public interface InfoService {
    public int  insertAddr(Address address);

    public List<Address> selectAdd(int u_id);

    public int deleteAddr( int aidInt);

    public boolean  defaultAddr(int u_id, int aidInt);

    public int  updateAddr(Address address);
}
