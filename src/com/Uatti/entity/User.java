package com.Uatti.entity;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2018-11-03
 * Time: 上午 8:59
 * Created with IntelliJ IDEA.
 */
public class User {
    private int uId;
    private String userName;
    private String userPassword;
    private String phone;
    private String email;
    private int authority;

    public User() {
    }

    public User(int uId, String userName, String userPassword, String phone, String email) {
        this.uId = uId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.phone = phone;
        this.email = email;
    }

    public User(String userName, String userPassword, String phone, String email) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.phone = phone;
        this.email = email;
    }

    public User(int uId, String userName, String userPassword, String phone, String email, int authority) {
        this.uId = uId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.phone = phone;
        this.email = email;
        this.authority = authority;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
