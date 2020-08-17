package com.tao.controller;

import com.tao.entity.User;
import com.tao.service.impl.RegisterServiceImpl;
import com.tao.utils.Base64Utils;
import com.tao.utils.EmailUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/reg")
public class RegisterController extends BeanServlet {
    public void register(HttpServletRequest req, HttpServletResponse resp) {

        try {
            Map<String, String[]> parameterMap = req.getParameterMap();
            User user = new User();
            BeanUtils.populate(user, parameterMap);
            RegisterServiceImpl registerService = new RegisterServiceImpl();
            boolean register = registerService.register(user);
            if (register) {
                EmailUtils.sendEmail(user);
                resp.setHeader("REDIRECT", "REDIRECT");//告诉ajax这是重定向
                resp.setHeader("CONTEXTPATH", req.getContextPath() + "/registerSuccess.jsp");//重定向地址
                resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void activeUser(HttpServletRequest req, HttpServletResponse resp) {
        String code = req.getParameter("c");
        String name = req.getParameter("name");
        RegisterServiceImpl registerService = new RegisterServiceImpl();
        User user = registerService.selectUser(name);
        if (user != null && user.getU_status() == 0) {
            if (user.getU_code().equals(Base64Utils.decode(code))) {
                int active = registerService.active(user.getU_name());
                if (active > 0) {
                    try {
                        resp.sendRedirect(req.getContextPath() + "/login.jsp");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("激活出错");
        }
    }
}


