package com.Uatti.entity;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2018-11-03
 * Time: 上午 8:50
 * Created with IntelliJ IDEA.
 */
public class Note {
    private int nId;
    private String user;
    private String createTime;
    private String noteTitle;
    private String noteContent;
    private int star;

    public Note(int nId, String user, String createTime, String noteTitle, String noteContent, int star) {
        this.nId = nId;
        this.user = user;
        this.createTime = createTime;
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        this.star = star;
    }

    public int getnId() {
        return nId;
    }

    public void setnId(int nId) {
        this.nId = nId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "Note{" +
                "nId=" + nId +
                ", user='" + user + '\'' +
                ", createTime='" + createTime + '\'' +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteContent='" + noteContent + '\'' +
                ", star=" + star +
                '}';
    }
}
