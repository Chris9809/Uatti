package com.Uatti.entity;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2019-01-07
 * Time: 下午 9:23
 * Function:
 */
public class Report {
    private int rid;
    private int bid;
    private String username;
    private String title;

    public Report() {

    }



    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Report(int bid, String username, String title) {
        this.bid = bid;
        this.username = username;
        this.title = title;
    }

    public Report(int rid, int bid, String username, String title) {
        this.rid = rid;
        this.bid = bid;
        this.username = username;
        this.title = title;
    }


    @Override
    public String toString() {
        return "举报：" +
                "用户名：'" + username + '\'' +
                "的文章：'" + title + '\'';
    }
}
