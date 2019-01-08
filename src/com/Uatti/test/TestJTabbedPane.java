package com.Uatti.test;

import javax.swing.*;
import java.awt.*;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2018-11-06
 * Time: 上午 10:02
 * Created with IntelliJ IDEA.
 */
public class TestJTabbedPane extends JFrame {
    public TestJTabbedPane() {
        super("testbox");
        Container c = this.getContentPane();
        c.setLayout(new BoxLayout(c,BoxLayout.X_AXIS));
        JTabbedPane center = new JTabbedPane(JTabbedPane.LEFT);
        JPanel jp1 = new JPanel();
        jp1.setBackground(Color.gray);
        center.add("111111",jp1);

        JPanel jp2 = new JPanel();
        jp2.setBackground(Color.BLUE);
        center.add("2222",jp2);

        JPanel jp3 = new JPanel();
        jp3.setBackground(Color.red);
        center.add("你好",jp3);

        c.add(center);
        this.setBounds(100,200,300,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new TestJTabbedPane();
    }
}
