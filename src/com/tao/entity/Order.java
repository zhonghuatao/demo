package com.tao.entity;

import java.util.Date;

public class Order {
    private String  o_id;
    private int  u_id;
    private int  a_id;
    private double o_count;
    private Date o_time;
    private int o_state;
    private Address address;

    public Order() {
    }

    public Order(String o_id, int u_id, int a_id, double o_count, Date o_time, int o_state, Address address) {
        this.o_id = o_id;
        this.u_id = u_id;
        this.a_id = a_id;
        this.o_count = o_count;
        this.o_time = o_time;
        this.o_state = o_state;
        this.address = address;
    }

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public double getO_count() {
        return o_count;
    }

    public void setO_count(double o_count) {
        this.o_count = o_count;
    }

    public Date getO_time() {
        return o_time;
    }

    public void setO_time(Date o_time) {
        this.o_time = o_time;
    }

    public int getO_state() {
        return o_state;
    }

    public void setO_state(int o_state) {
        this.o_state = o_state;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "o_id='" + o_id + '\'' +
                ", u_id=" + u_id +
                ", a_id=" + a_id +
                ", o_count=" + o_count +
                ", o_time=" + o_time +
                ", o_state=" + o_state +
                ", address=" + address +
                '}';
    }
}
