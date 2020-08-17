package com.tao.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BeanServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{
            String methods = req.getParameter("methods");
            if (methods==null||methods==""){
                resp.sendRedirect("/project/login.jsp");
            }

            Class<? extends BeanServlet> clazz = this.getClass();
            try {
                Method clazzMethod = clazz.getMethod(methods, HttpServletRequest.class, HttpServletResponse.class);
                clazzMethod.invoke(this,req,resp);
            } catch (Exception e) {
                resp.sendRedirect("/project/login.jsp");
                e.printStackTrace();
            }
        }catch (Exception e){

            req.setAttribute("msg","出了点小问题，正在处理中，先去别处逛逛吧");
            req.getRequestDispatcher("/message.jsp").forward(req,resp);
         e.printStackTrace();
        }


    }
}
