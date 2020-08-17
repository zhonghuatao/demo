package com.tao.service.impl;

import com.tao.dao.impl.ItemDaoImpl;
import com.tao.dao.impl.OrderDaoImpl;
import com.tao.entity.Cart;
import com.tao.entity.Item;
import com.tao.entity.Order;
import com.tao.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {


    @Override
    public List<Order> selectOrderList(int u_id) {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        List<Order> orders = orderDao.selectOrderList(u_id);
        return orders;
    }

    @Override
    public Order orderDetail( String oId) {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        Order order = orderDao.selectOrder(oId);
        return order;
    }

    @Override
    public List<Item>  orderItem(String oId) {
        ItemDaoImpl itemDao = new ItemDaoImpl();
        List<Item>  item = itemDao. orderItem(oId);
        return item;
    }

    @Override
    public int addOrder(Order order) {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        int insert = orderDao.insert(order);
        return insert;
    }

    @Override
    public int addItem(List<Cart> carts ,String oid) {
        ItemDaoImpl itemDao = new ItemDaoImpl();
        int flag=1;
        for (Cart cart : carts) {
            int i = itemDao.addItem(cart, oid);
            if (!(i>0)){
                 flag=i;
            }
        }
        return flag;
    }
}
