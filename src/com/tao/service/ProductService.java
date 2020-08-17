package com.tao.service;

import com.tao.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getProduct(int tid);
    public Product productDetail(int pid);
}
