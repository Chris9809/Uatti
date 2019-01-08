package com.Uatti.entity;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2019-01-07
 * Time: 下午 9:27
 * Function:
 */
public class Action {

    private int aid;
    private String actuser;
    private String pasuser;
    private String time;
    private String obj;
    private String comments;

    public Action() {
    }

    public Action(String actuser, String pasuser, String time, String obj, String comments) {
        this.actuser = actuser;
        this.pasuser = pasuser;
        this.time = time;
        this.obj = obj;
        this.comments = comments;
    }

    public Action(int aid, String actuser, String pasuser, String time, String obj, String comments) {
        this.aid = aid;
        this.actuser = actuser;
        this.pasuser = pasuser;
        this.time = time;
        this.obj = obj;
        this.comments = comments;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getActuser() {
        return actuser;
    }

    public void setActuser(String actuser) {
        this.actuser = actuser;
    }

    public String getPasuser() {
        return pasuser;
    }

    public void setPasuser(String pasuser) {
        this.pasuser = pasuser;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return actuser + " 在 "+time+" 给您的博客 "+obj+" 进行了 "+comments+" 的操作";
    }
}
