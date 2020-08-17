package com.tao.controller;

import com.tao.entity.Address;
import com.tao.entity.Cart;
import com.tao.entity.User;
import com.tao.service.impl.CartServiceImpl;
import com.tao.service.impl.InfoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class CartController extends BeanServlet {
    public void addCart(HttpServletRequest req, HttpServletResponse resp) {
        String pid = req.getParameter("pid");
        int pIn = Integer.parseInt(pid);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int uid = user.getU_id();
        CartServiceImpl cartService = new CartServiceImpl();
        int i = cartService.insertCart(uid, pIn);
        if (i>0){
            try {
                resp.sendRedirect(req.getContextPath()+"/cartSuccess.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void getCart(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            int uid = user.getU_id();
            System.out.println(uid);
            CartServiceImpl cartService = new CartServiceImpl();
            List<Cart> carts = cartService.query(uid);
            if (!carts.isEmpty()) {
                req.setAttribute("carts", carts);
                req.getRequestDispatcher("/cart.jsp").forward(req, resp);
            } else {
                req.setAttribute("msg", "购物车没有东西");
                req.getRequestDispatcher("/message.jsp").forward(req, resp);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCartNum(HttpServletRequest req, HttpServletResponse resp) {
        String pid = req.getParameter("pid");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println(pid);
        int uid = user.getU_id();
        if (pid != null) {
            int pidInt = Integer.parseInt(pid);
            CartServiceImpl cartService = new CartServiceImpl();
            int i = cartService.addCartNum(uid, pidInt);
        }

    }

    public void removeCartNum(HttpServletRequest req, HttpServletResponse resp) {
        String pid = req.getParameter("pid");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println(pid);
        int uid = user.getU_id();
        System.out.println(pid);
        System.out.println(uid);
        if (pid != null) {
            int pidInt = Integer.parseInt(pid);
            CartServiceImpl cartService = new CartServiceImpl();
            int i = cartService.removeCartNum(uid, pidInt);
        }
    }

    public void deleteCart(HttpServletRequest req, HttpServletResponse resp) {
        String pid = req.getParameter("pid");
        System.out.println(pid);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println(pid);
        int uid = user.getU_id();
        System.out.println(uid);
        if (pid != null) {
            int pidInt = Integer.parseInt(pid);
            CartServiceImpl cartService = new CartServiceImpl();
            int i = cartService.deleteCart(uid, pidInt);
            if (i > 0) {
                try {
                    resp.sendRedirect(req.getContextPath() + "/cart?methods=getCart");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void removeCartAll(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int uid = user.getU_id();
        System.out.println(uid);
        CartServiceImpl cartService = new CartServiceImpl();
        int i = cartService.deleteCartAll(uid);
        if (i == 0) {
            try {
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void orderPv(HttpServletRequest req, HttpServletResponse resp){
        try {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            int uid = user.getU_id();
            CartServiceImpl cartService = new CartServiceImpl();
            InfoServiceImpl infoService = new InfoServiceImpl();
            List<Address> addresses = infoService.selectAdd(uid);
            List<Cart> carts = cartService.query(uid);
            if (!carts.isEmpty()) {
                req.setAttribute("carts", carts);
                req.setAttribute("address", addresses);
                req.getRequestDispatcher("/order.jsp").forward(req, resp);
            } else {
                req.setAttribute("msg", "购物车没有东西");
                req.getRequestDispatcher("/message.jsp").forward(req, resp);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
