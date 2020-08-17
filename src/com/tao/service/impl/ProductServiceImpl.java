package com.tao.service.impl;

import com.tao.dao.impl.ProductDaoImpl;
import com.tao.entity.Product;
import com.tao.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> getProduct(int tid) {
        ProductDaoImpl productDao = new ProductDaoImpl();
        List<Product> select = productDao.select(tid);
        if (select!=null){
           return select;
        }
        return null;
    }

    public Product productDetail(int pid){
        ProductDaoImpl productDao = new ProductDaoImpl();
        Product product = productDao.selectProductDetail(pid);
         if(product!=null){
             return product;
         }
        return null;
    }
}
