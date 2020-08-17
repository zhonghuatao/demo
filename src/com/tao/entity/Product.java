package com.tao.entity;

import java.util.Date;

public class Product {
    private Type type;
    private int t_id;
    private int p_id;
    private String p_name;
    private Date p_time;
    private String p_image;
    private double p_price;
    private int p_state;
    private String p_info;

    public Product() {
    }
    public Product(Type type, int t_id, int p_id, String p_name, Date p_time, String p_image, double p_price, int p_state, String p_info) {
        this.type = type;
        this.t_id = t_id;
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_time = p_time;
        this.p_image = p_image;
        this.p_price = p_price;
        this.p_state = p_state;
        this.p_info = p_info;
    }

    public Product(int t_id, int p_id, String p_name, Date p_time, String p_image, double p_price, int p_state, String p_info) {
        this.t_id = t_id;
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_time = p_time;
        this.p_image = p_image;
        this.p_price = p_price;
        this.p_state = p_state;
        this.p_info = p_info;
    }

    public Product(int p_id, String p_name, Date p_time, String p_image, double p_price, int p_state, String p_info) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_time = p_time;
        this.p_image = p_image;
        this.p_price = p_price;
        this.p_state = p_state;
        this.p_info = p_info;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public Date getP_time() {
        return p_time;
    }

    public void setP_time(Date p_time) {
        this.p_time = p_time;
    }

    public String getP_image() {
        return p_image;
    }

    public void setP_image(String p_image) {
        this.p_image = p_image;
    }

    public double getP_price() {
        return p_price;
    }

    public void setP_price(double p_price) {
        this.p_price = p_price;
    }

    public int getP_state() {
        return p_state;
    }

    public void setP_state(int p_state) {
        this.p_state = p_state;
    }

    public String getP_info() {
        return p_info;
    }

    public void setP_info(String p_info) {
        this.p_info = p_info;
    }

    @Override
    public String toString() {
        return "Product{" +
                "type=" + type +
                ", t_id=" + t_id +
                ", p_id=" + p_id +
                ", p_name='" + p_name + '\'' +
                ", p_time=" + p_time +
                ", p_image='" + p_image + '\'' +
                ", p_price=" + p_price +
                ", p_state=" + p_state +
                ", p_info='" + p_info + '\'' +
                '}';
    }
}
