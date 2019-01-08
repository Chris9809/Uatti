package com.Uatti.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2018-11-17
 * Time: 上午 8:17
 * Created with IntelliJ IDEA.
 */
public class KeyUse extends JFrame {

    public KeyUse(){
        Container c = this.getContentPane();
        c.setLayout(new FlowLayout());

        JTextArea ja = new JTextArea(10,20);
        JLabel jl = new JLabel();
        jl.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println(e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Pressed!");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("Release!");
            }
        });
        ja.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                System.out.println("FocusGained");
            }

            @Override
            public void focusLost(FocusEvent e) {
                System.out.println("FocusLost");
            }
        });
        c.add(ja);
        c.add(jl);
        this.setBounds(500,200,500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new KeyUse();
    }
}
