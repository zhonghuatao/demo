package com.tao.service;

import com.tao.entity.Cart;
import com.tao.entity.Item;
import com.tao.entity.Order;

import java.util.List;

public interface OrderService {
    public List<Order> selectOrderList(int u_id);

    public Order  orderDetail( String oId);

    public List<Item>  orderItem(String oId);

    public int  addOrder(Order order);

    public int  addItem(List<Cart> carts,String oid);
}
