package com.tao.entity;

public class Cart {
    private int c_id;
    private int u_id;
    private int p_id;
    private double c_count;
    private int c_num;
    private Product product;

    public Cart() {
    }

    public Cart(int u_id, int p_id, double c_count, int c_num, Product product) {
        this.u_id = u_id;
        this.p_id = p_id;
        this.c_count = c_count;
        this.c_num = c_num;
        this.product = product;
    }

    public Cart(int c_id, int u_id, int p_id, double c_count, int c_num, Product product) {
        this.c_id = c_id;
        this.u_id = u_id;
        this.p_id = p_id;
        this.c_count = c_count;
        this.c_num = c_num;
        this.product = product;
    }

    public Cart(int u_id, int p_id, double c_count, int c_num) {
        this.u_id = u_id;
        this.p_id = p_id;
        this.c_count = c_count;
        this.c_num = c_num;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public double getC_count() {
        return c_count;
    }

    public void setC_count(double c_count) {
        this.c_count = c_count;
    }

    public int getC_num() {
        return c_num;
    }

    public void setC_num(int c_num) {
        this.c_num = c_num;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "c_id=" + c_id +
                ", u_id=" + u_id +
                ", p_id=" + p_id +
                ", c_count=" + c_count +
                ", c_num=" + c_num +
                ", product=" + product +
                '}';
    }
}
