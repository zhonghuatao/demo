package com.tao.controller;

import com.tao.entity.Product;
import com.tao.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/product")
public class ProductController extends BeanServlet {
    public void getProduct(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String tid = req.getParameter("tid");
            int parseInt = 0;
            if (tid != null) {
                parseInt = Integer.parseInt(tid);
            }
            ProductServiceImpl productService = new ProductServiceImpl();
            List<Product> product = productService.getProduct(parseInt);
            if (!product.isEmpty()) {
                req.setAttribute("glist", product);
                req.getRequestDispatcher("/goodsList.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void productDetail(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String p_id = req.getParameter("p_id");
            int parseInt = 0;
            if (p_id != null) {
                parseInt = Integer.parseInt(p_id);
            }
            ProductServiceImpl productService = new ProductServiceImpl();
            Product product = productService.productDetail(parseInt);
            if (product != null) {
                req.setAttribute("goods", product);
                req.getRequestDispatcher("/goodsDetail.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
