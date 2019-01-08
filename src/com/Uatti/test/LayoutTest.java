package com.Uatti.test;

import javax.swing.*;
import java.awt.*;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2018-11-03
 * Time: 上午 10:30
 * Created with IntelliJ IDEA.
 */
public class LayoutTest  extends JFrame {
    public LayoutTest(){
        Container c = this.getContentPane();
        JPanel palGrid = new JPanel(new GridLayout(2,2,5,5));

        palGrid.add(new JButton("1"));
        palGrid.add(new JButton("2"));
        palGrid.add(new JButton("3"));
        palGrid.add(new JButton("4"));
        c.add(new JButton("center"));
        c.add(palGrid);
        c.add(new JButton("north"),BorderLayout.NORTH);

        this.setBounds(100,200,300,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new LayoutTest();
    }
}
