package com.Uatti.controller;

import com.Uatti.entity.Action;
import com.Uatti.entity.Blog;
import com.Uatti.entity.Report;
import com.Uatti.entity.User;

import javax.swing.*;
import java.awt.*;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2018-11-17
 * Time: 下午 3:35
 * Function:
 */
public class ListFont extends JList<Blog> {
    public ListFont(DefaultListModel<Blog> listModel, Font f){
        super(listModel);
        this.setFont(f);
    }
}
class ListFontAction extends JList<Action> {
    public ListFontAction(DefaultListModel<Action> listModel){
        super(listModel);
        this.setFont(new java.awt.Font("微软雅黑", 0, 12));
    }
}
class ListFontReport extends  JList<Report> {
    public ListFontReport(DefaultListModel<Report> dataModel) {
        super(dataModel);
        this.setFont(new java.awt.Font("微软雅黑", 0, 12));
    }
}