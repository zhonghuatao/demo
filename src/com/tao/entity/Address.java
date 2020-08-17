package com.tao.entity;

public class Address {
    private int a_id;
    private int u_id;
    private String a_name;
    private String a_phone;
    private String a_detail;
    private int a_state;
    private User user;

    public Address() {
    }

    public Address(int a_id, int u_id, String a_name, String a_phone, String a_detail, int a_state, User user) {
        this.a_id = a_id;
        this.u_id = u_id;
        this.a_name = a_name;
        this.a_phone = a_phone;
        this.a_detail = a_detail;
        this.a_state = a_state;
        this.user = user;
    }

    public Address(int u_id, String a_name, String a_phone, String a_detail, int a_state) {
        this.u_id = u_id;
        this.a_name = a_name;
        this.a_phone = a_phone;
        this.a_detail = a_detail;
        this.a_state = a_state;
    }

    public Address(int a_id, String a_name, String a_phone, String a_detail) {
        this.a_id = a_id;
        this.a_name = a_name;
        this.a_phone = a_phone;
        this.a_detail = a_detail;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getA_phone() {
        return a_phone;
    }

    public void setA_phone(String a_phone) {
        this.a_phone = a_phone;
    }

    public String getA_detail() {
        return a_detail;
    }

    public void setA_detail(String a_detail) {
        this.a_detail = a_detail;
    }

    public int getA_state() {
        return a_state;
    }

    public void setA_state(int a_state) {
        this.a_state = a_state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Address{" +
                "a_id=" + a_id +
                ", u_id=" + u_id +
                ", a_name='" + a_name + '\'' +
                ", a_phone='" + a_phone + '\'' +
                ", a_detail='" + a_detail + '\'' +
                ", a_state=" + a_state +
                ", user=" + user +
                '}';
    }
}
