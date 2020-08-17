package com.tao.controller;

import com.google.gson.Gson;
import com.tao.entity.Type;
import com.tao.service.impl.GoodsTypeServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/goodsType")
public class GoodsTypeController extends BeanServlet {

    public  void getGoodsType(HttpServletRequest rep, HttpServletResponse resp){
        try {
            GoodsTypeServiceImpl goodsTypeService = new GoodsTypeServiceImpl();
            List<Type> goodsType = goodsTypeService.getGoodsType();
            if (goodsType!=null){
                Gson gson = new Gson();
                String json = gson.toJson(goodsType);
                PrintWriter writer = resp.getWriter();
                writer.print(json);
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
