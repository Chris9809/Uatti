package com.Uatti.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2018-11-17
 * Time: 上午 9:53
 * Function: 测试学习popupMenu
 */
public class TestJPopMenu extends JFrame {

    JPopupMenu jPopupMenu = new JPopupMenu();

    public TestJPopMenu() {
        String[] strManu = new String[]{"关于软件(A)"};
        String[][] strManuItem = new String[][]{{"了解我(k)","联系我(C)","打赏作者(R)"},{}};
        char[] strM1 = {'A'};
        char[] strM2 = {'K','C','R'};

        //JMenuBar jMenuBar = new JMenuBar();


        for (int i = 0;i<strManu.length;i++){
            JMenu jMenu =  new JMenu(strManu[i]);
            jMenu.setMnemonic(strM1[i]);
            jPopupMenu.add(jMenu);
            for(int j=0;j<strManuItem[i].length;j++){
                JMenuItem item = new JMenuItem(strManuItem[i][j]);
                item.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if("了解我(k)".equals(e.getActionCommand()))
                        {
                            System.out.println("my name is yelihu!");
                        }else if("联系我(C)".equals(e.getActionCommand())){
                            System.out.println("tel:18814922210");
                        }else if("打赏作者(R)".equals(e.getActionCommand())){
                            System.out.println("AliPay:18814922210");
                        }


                    }
                });
                item.setMnemonic(strM2[j]);
                jMenu.add(item);
                jMenu.addSeparator();
            }
        }
        JTextArea area = new JTextArea(10,20);

        area.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.isPopupTrigger()) {
                    jPopupMenu.show(TestJPopMenu.this,e.getX(),e.getY());
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                mousePressed(e);
            }
        });
        this.getContentPane().add(new JScrollPane(area));
        this.setBounds(500,200,1280,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new TestJPopMenu();
    }
}
