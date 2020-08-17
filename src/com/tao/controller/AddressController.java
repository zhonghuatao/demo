package com.tao.controller;

import com.tao.entity.Address;
import com.tao.entity.User;
import com.tao.service.impl.InfoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/address")
public class AddressController extends BeanServlet {
    public void addAddr(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String detail = req.getParameter("detail");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            int u_id = user.getU_id();
            Address address = new Address(u_id, name, phone, detail, 0);
            InfoServiceImpl infoService = new InfoServiceImpl();
            int i = infoService.insertAddr(address);
            if (i > 0) {
                try {
                    resp.sendRedirect(req.getContextPath() + "/address?methods=selectAddr");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public void selectAddr(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            int u_id = user.getU_id();
            InfoServiceImpl infoService = new InfoServiceImpl();
            List<Address> addresses = infoService.selectAdd(u_id);
            req.setAttribute("addList", addresses);
            try {
                req.getRequestDispatcher("/self_info.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void deleteAddr(HttpServletRequest req, HttpServletResponse resp) {
        String aid = req.getParameter("aid");
        if (aid != null) {
            int aidInt = Integer.parseInt(aid);
            InfoServiceImpl infoService = new InfoServiceImpl();
            int i = infoService.deleteAddr(aidInt);
            if (i > 0) {
                try {
                    resp.sendRedirect(req.getContextPath() + "/address?methods=selectAddr");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void defaultAddr(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            int u_id = user.getU_id();
            String aid = req.getParameter("aid");
            if (aid != null) {
                int aidInt = Integer.parseInt(aid);
                InfoServiceImpl infoService = new InfoServiceImpl();
                boolean b = infoService.defaultAddr(u_id, aidInt);
                if (b) {
                    try {
                        resp.sendRedirect(req.getContextPath() + "/address?methods=selectAddr");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void updateAddr(HttpServletRequest req, HttpServletResponse resp) {
        String aid = req.getParameter("id");
        if (aid != null) {
            int aidInt = Integer.parseInt(aid);
            String name = req.getParameter("name");
            String phone = req.getParameter("phone");
            String detail = req.getParameter("detail");
            InfoServiceImpl infoService = new InfoServiceImpl();
            Address address = new Address(aidInt, name, phone, detail);
            int i = infoService.updateAddr(address);
            if (i > 0) {
                try {
                    resp.sendRedirect(req.getContextPath() + "/address?methods=selectAddr");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
