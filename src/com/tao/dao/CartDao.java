package com.tao.dao;

import com.tao.entity.Cart;

import java.util.List;

public interface CartDao {
    public int insert(Cart cart);
    public int delete(int uid ,int cid);
    public int  update(Cart cart);
    public Cart select(int uid,int pid);
    public List<Cart> query(int uid);

    public int deleteAll(int uid);

}
