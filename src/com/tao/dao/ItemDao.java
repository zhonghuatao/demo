package com.tao.dao;

import com.tao.entity.Cart;
import com.tao.entity.Item;

import java.util.List;

public interface ItemDao {

    public List<Item> orderItem(String oId);

    public int  addItem(Cart cart, String oid);
}
