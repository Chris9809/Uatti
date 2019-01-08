package com.Uatti.entity;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2019-01-04
 * Time: 上午 10:04
 * Function:
 */
public class Blog {
     private int bId;
     private String username;
     private String time;
     private String title;
     private String content;
     private long pageView;
    public Blog() {
    }

    public Blog(int bId, String username, String time, String title, String content) {
        this.bId = bId;
        this.username = username;
        this.time = time;
        this.title = title;
        this.content = content;
    }

    public Blog(int bId, String username, String time, String title, String content, long pageView) {
        this.bId = bId;
        this.username = username;
        this.time = time;
        this.title = title;
        this.content = content;
        this.pageView = pageView;
    }

    public Blog(String username, String time, String title, String content) {
        this.username = username;
        this.time = time;
        this.title = title;
        this.content = content;
    }

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getPageView() {
        return pageView;
    }

    public void setPageView(long pageView) {
        this.pageView = pageView;
    }

    public String showCmtBlog(){
        if(title.length()>=15) {
            title = title.substring(0,14)+"...";
        }else if(title.length()<20){
            for(int i=0;i<15-title.length();i++){
                title = title+" ";
            }
        }
        return title;
    }

    @Override
    public String toString() {
        return  title;
    }

}
