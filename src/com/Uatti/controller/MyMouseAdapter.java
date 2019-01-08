package com.Uatti.controller;

import com.Uatti.controller.ListFont;
import com.Uatti.controller.Read;
import com.Uatti.entity.Blog;
import com.Uatti.entity.User;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2019-01-06
 * Time: 下午 4:47
 * Function:
 */
public class MyMouseAdapter extends MouseAdapter {
    private ListFont listFont;
    private User visiter;

    public MyMouseAdapter(ListFont listFont, User visiter) {
        this.visiter = visiter;
        this.listFont = listFont;
    }
    public MyMouseAdapter(ListFont listFont) {
        this.listFont = listFont;
    }
    @Override
    public void mouseClicked(MouseEvent time) {
        if(time.getClickCount() == 2){
            Blog blog=listFont.getSelectedValue();
            try {
                new Read(blog,visiter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}