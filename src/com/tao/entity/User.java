package com.tao.entity;

public class User {
    private int u_id;
    private String u_name;
    private String u_password;
    private String u_email;
    private String u_sex;
    private int u_status;//热度指数
    private String u_code;//邮箱激活码
    private int u_role;//0用户 1管理员

    public User() {
    }

    public User(String u_name, String u_password, String u_email, String u_sex) {
        this.u_name = u_name;
        this.u_password = u_password;
        this.u_email = u_email;
        this.u_sex = u_sex;
    }

    public User(String u_name, String u_password, String u_email, String u_sex, int u_status, String u_code, int u_role) {
        this.u_name = u_name;
        this.u_password = u_password;
        this.u_email = u_email;
        this.u_sex = u_sex;
        this.u_status = u_status;
        this.u_code = u_code;
        this.u_role = u_role;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getU_sex() {
        return u_sex;
    }

    public void setU_sex(String u_sex) {
        this.u_sex = u_sex;
    }

    public int getU_status() {
        return u_status;
    }

    public void setU_status(int u_status) {
        this.u_status = u_status;
    }

    public String getU_code() {
        return u_code;
    }

    public void setU_code(String u_code) {
        this.u_code = u_code;
    }

    public int getU_role() {
        return u_role;
    }

    public void setU_role(int u_role) {
        this.u_role = u_role;
    }

    @Override
    public String toString() {
        return "User{" +
                "u_id=" + u_id +
                ", u_name='" + u_name + '\'' +
                ", u_password='" + u_password + '\'' +
                ", u_email='" + u_email + '\'' +
                ", u_sex='" + u_sex + '\'' +
                ", u_status=" + u_status +
                ", u_code='" + u_code + '\'' +
                ", u_role=" + u_role +
                '}';
    }
}
