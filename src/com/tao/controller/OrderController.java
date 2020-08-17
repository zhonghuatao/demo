package com.tao.controller;

import com.tao.entity.Cart;
import com.tao.entity.Item;
import com.tao.entity.Order;
import com.tao.entity.User;
import com.tao.service.impl.CartServiceImpl;
import com.tao.service.impl.OrderServiceImpl;
import com.tao.utils.RandomUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/order")
public class OrderController extends BeanServlet {

    public void orderList(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            OrderServiceImpl orderService = new OrderServiceImpl();
            int u_id = user.getU_id();
            List<Order> orders = orderService.selectOrderList(u_id);
            req.setAttribute("orderList", orders);
            try {
                req.getRequestDispatcher("/orderList.jsp").forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void orderDetail(HttpServletRequest req, HttpServletResponse resp) {
        String o_id = req.getParameter("o_id");
        if (o_id != null) {
            OrderServiceImpl orderService = new OrderServiceImpl();
            Order order = orderService.orderDetail(o_id);
            List<Item> item = orderService.orderItem(o_id);
            if (order!=null){
                req.setAttribute("order",order);
                req.setAttribute("itemList",item);
                try {
                    req.getRequestDispatcher("/orderDetail.jsp").forward(req,resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addOrder(HttpServletRequest req, HttpServletResponse resp){
        String aid = req.getParameter("aid");
        if (aid!=null){
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            if (user != null) {
                int a_id = Integer.parseInt(aid);
                int u_id = user.getU_id();
                String orderId = RandomUtils.createOrderId();
                Date date = new Date();
                System.out.println(date.getTime());
                CartServiceImpl cartService = new CartServiceImpl();
                List<Cart> carts = cartService.query(u_id);//获取购物车数据
                double money=0;
                for (Cart cart : carts) {
                    money+=cart.getC_count();// 计算金额
                }
                Order order = new Order(orderId,u_id,a_id,money,date,1,null);
                OrderServiceImpl orderService = new OrderServiceImpl();
                int i = orderService.addOrder(order);//添加订单
                //添加订单项
                int i1 = orderService.addItem(carts, orderId);
                if (i>0&&i1>0){
                    try {
                        req.setAttribute("order",order);
                        req.getRequestDispatcher("/orderSuccess.jsp").forward(req,resp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
