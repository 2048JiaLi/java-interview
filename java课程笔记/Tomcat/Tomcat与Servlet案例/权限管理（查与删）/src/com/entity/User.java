package com.entity;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/7/15 9:54
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String phone;
    private int access;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", access=" + access +
                '}';
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public User() {
    }

    public User(int id, String username, String password, String phone, int access) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.access = access;
    }

    public User(int id, String username, String password, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }
}
