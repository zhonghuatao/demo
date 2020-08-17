package com.tao.entity;

public class Item {
    private  int  i_id;
    private  String  o_id;
    private int  p_id;
    private  double i_count;
    private int  i_num;

    private Product product;

    public Item() {
    }

    public Item(int i_id, String o_id, int p_id, double i_count, int i_num, Product product) {
        this.i_id = i_id;
        this.o_id = o_id;
        this.p_id = p_id;
        this.i_count = i_count;
        this.i_num = i_num;
        this.product = product;
    }

    public Item(String o_id, int p_id, double i_count, int i_num, Product product) {
        this.o_id = o_id;
        this.p_id = p_id;
        this.i_count = i_count;
        this.i_num = i_num;
        this.product = product;
    }

    public int getI_id() {
        return i_id;
    }

    public void setI_id(int i_id) {
        this.i_id = i_id;
    }

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public double getI_count() {
        return i_count;
    }

    public void setI_count(double i_count) {
        this.i_count = i_count;
    }

    public int getI_num() {
        return i_num;
    }

    public void setI_num(int i_num) {
        this.i_num = i_num;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Item{" +
                "i_id=" + i_id +
                ", o_id='" + o_id + '\'' +
                ", p_id=" + p_id +
                ", i_count=" + i_count +
                ", i_num=" + i_num +
                ", product=" + product +
                '}';
    }
}
