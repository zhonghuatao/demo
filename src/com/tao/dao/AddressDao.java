package com.tao.dao;

import com.tao.entity.Address;

import java.util.List;

public interface AddressDao {
    public int insertAddr(Address address);
    public List<Address> selectAddr(int  uid );

    public int  deleteAddr(int aidInt);

    public boolean  defaultAddr(int u_id, int aidInt);

    public int  updateAddr(Address address);
}
