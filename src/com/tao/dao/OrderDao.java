package com.tao.dao;

import com.tao.entity.Order;

import java.util.List;

public interface OrderDao {


    public List<Order> selectOrderList(int u_id);

    public Order selectOrder( String oId);

    public int insert(Order order);
}
