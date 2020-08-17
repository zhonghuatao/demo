package com.tao.controller;

import cn.dsna.util.images.ValidateCode;
import com.tao.entity.User;
import com.tao.service.impl.LoginServiceImpl;
import com.tao.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginController extends BeanServlet {
    //登入servlet
    public void login(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String auto = req.getParameter("auto");
        System.out.println(auto);
        LoginServiceImpl loginService = new LoginServiceImpl();
        User user = loginService.login(username, password);
        try {
            HttpSession session = req.getSession();
            if (user != null && user.getU_status() == 1) {
                if (auto != null && auto.equals("on")) {
                    Cookie cookie1 = new Cookie("user", user.getU_name());
                    cookie1.setMaxAge(14 * 24 * 60 * 60);
                    resp.addCookie(cookie1);
                }
                session.setAttribute("user", user);
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            } else if (user != null && user.getU_status() != 1) {
                req.getRequestDispatcher("registerSuccess.jsp").forward(req, resp);
            } else {
                req.setAttribute("msg", "用户名或密码错误");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void checkUserName(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        LoginServiceImpl loginService = new LoginServiceImpl();
        boolean b = loginService.checkUserName(username);
        try {
            PrintWriter writer = resp.getWriter();
            if (!b) {
                writer.print(Constants.NOT_HAS_USER);
                writer.close();
            } else {
                writer.print(Constants.HAS_USER);
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void code(HttpServletRequest req, HttpServletResponse resp) {
        ValidateCode validateCode = new ValidateCode(100, 30, 4, 20);
        String code = validateCode.getCode();
        HttpSession session = req.getSession();
        session.setAttribute("code", code);
        try {
            validateCode.write(resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkCode(HttpServletRequest req, HttpServletResponse resp) {
        String code = req.getParameter("code");
        HttpSession session = req.getSession();
        String codeSession = (String) session.getAttribute("code");
        if (code != null && codeSession != null) {
            try {
                PrintWriter writer = resp.getWriter();
                if (codeSession.equalsIgnoreCase(code)) {
                    writer.print(Constants.CODE_OK);
                } else {
                    writer.print(Constants.CODE_ERROR);
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void logOut(HttpServletRequest req, HttpServletResponse resp) {
        Cookie cookie1 = new Cookie("user", null);
        cookie1.setMaxAge(0);
        resp.addCookie(cookie1);
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        try {
            resp.sendRedirect(req.getContextPath()+"/login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

