package com.tao.entity;

public class Type {
    private int t_id;
    private String t_name;
    private String t_info;

    public Type() {
    }

    public Type(String t_name, String t_info) {
        this.t_name = t_name;
        this.t_info = t_info;
    }

    public Type(int t_id, String t_name, String t_info) {
        this.t_id = t_id;
        this.t_name = t_name;
        this.t_info = t_info;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getT_info() {
        return t_info;
    }

    public void setT_info(String t_info) {
        this.t_info = t_info;
    }

    @Override
    public String toString() {
        return "Type{" +
                "t_id=" + t_id +
                ", t_name='" + t_name + '\'' +
                ", t_info='" + t_info + '\'' +
                '}';
    }
}
