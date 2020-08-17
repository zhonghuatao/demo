package com.tao.service;

import com.tao.entity.Cart;

import java.util.List;

public interface CartService {
    public List<Cart> query(int uid);
    public int addCartNum(int uid , int pid);
    public int removeCartNum(int uid , int pid);
    public int deleteCart(int uid ,int pid);
    public int  deleteCartAll(int uid);
     public int insertCart(int uid ,int pIn);
}
