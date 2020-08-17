package com.tao.dao;

import com.tao.entity.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> query();
    public List<Product> select(int tid);
    public Product selectProductDetail(int p_id);
}
