package com.tao.service.impl;

import com.tao.dao.impl.CartDaoImpl;
import com.tao.entity.Cart;
import com.tao.entity.Product;
import com.tao.service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    @Override
    public List<Cart> query(int uid) {
        CartDaoImpl cartDao = new CartDaoImpl();
        List<Cart> query = cartDao.query(uid);
        return query;
    }

    public int addCartNum(int uid, int pid) {
        CartDaoImpl cartDao = new CartDaoImpl();
        Cart selectCart = cartDao.select(uid, pid);
        selectCart.setC_count(selectCart.getC_count() + selectCart.getProduct().getP_price());
        selectCart.setC_num(selectCart.getC_num() + 1);
        int update = cartDao.update(selectCart);
        return update;
    }

    @Override
    public int removeCartNum(int uid, int pid) {
        CartDaoImpl cartDao = new CartDaoImpl();
        Cart selectCart = cartDao.select(uid, pid);
        selectCart.setC_count(selectCart.getC_count() - selectCart.getProduct().getP_price());
        selectCart.setC_num(selectCart.getC_num() - 1);
        int update = cartDao.update(selectCart);
        return update;
    }

    @Override
    public int deleteCart(int uid, int pid) {
        CartDaoImpl cartDao = new CartDaoImpl();
        int delete = cartDao.delete(uid, pid);
        return delete;
    }

    @Override
    public int deleteCartAll(int uid) {
        CartDaoImpl cartDao = new CartDaoImpl();
        int i = cartDao.deleteAll(uid);
        return i;
    }

    @Override
    public int insertCart(int uid, int pIn) {
        CartDaoImpl cartDao = new CartDaoImpl();
        Cart select = cartDao.select(uid, pIn);
        if (select!=null){
            int i = addCartNum(uid, pIn);
            return i;
        }else{
            ProductServiceImpl productService = new ProductServiceImpl();
            Product product = productService.productDetail(pIn);
            Cart cart = new Cart(uid,pIn,product.getP_price(),1);
            int insert = cartDao.insert(cart);
            return insert;
        }
    }


}
