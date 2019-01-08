package com.Uatti.test;

import javax.swing.*;
import java.awt.*;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2018-11-03
 * Time: 上午 9:56
 * Created with IntelliJ IDEA.
 */
public class JPanelTest extends JFrame {
    public JPanelTest(){
        super("面板练习");
        Container c = this.getContentPane();
        c.setLayout(new FlowLayout());
        JPanel jp = new JPanel();

        jp.add(new JLabel("加上边框"));

        jp.setBorder(BorderFactory.createTitledBorder("标题"));
        jp.add(new JTextField("name",5));
        jp.setBackground(Color.GRAY);

        JSlider js = new JSlider(SwingConstants.HORIZONTAL,1,100,40);

        c.add(jp);
        c.add(js);
        this.setBounds(100,200,300,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new JPanelTest();
    }
}
