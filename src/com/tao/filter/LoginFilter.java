package com.tao.filter;

import com.tao.entity.User;
import com.tao.service.impl.LoginServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String servletPath = req.getServletPath();
        if(servletPath.equals("/register")||servletPath.equals("/login")||servletPath.equals("/login.jsp")||servletPath.equals("/index.jsp")||servletPath.equals("/register.jsp")||servletPath.equals("/registerSuccess.jsp")) {
            filterChain.doFilter(req, resp);
        }else{
            Cookie cookie[] = req.getCookies();
            if (cookie != null) {
                for (Cookie cookie1 : cookie) {
                    if (cookie1.getName().equals("user")) {
                        HttpSession session1 = req.getSession();
                        if (cookie1.getValue()!=null){
                            User user = new LoginServiceImpl().autoLogin(cookie1.getValue());
                            session1.setAttribute("user",user);
                        }
                    }
                }
            }
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            if (user!=null) {
                filterChain.doFilter(req, resp);
            }else{
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
