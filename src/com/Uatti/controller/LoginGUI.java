package com.Uatti.controller;

import com.Uatti.Service.UserService;
import com.Uatti.Service.imp.UserServiceImp;
import com.Uatti.entity.User;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2018-11-03
 * Time: 涓婂崍 10:55
 * Created with IntelliJ IDEA.
 */

public class LoginGUI extends JFrame {
    UserService userService;
    JTextField userName = new JTextField(13);
    JPasswordField userPassword = new JPasswordField(13);
    JButton btnLogin = new JButton("立即登陆");
    JButton btnRegist = new JButton("点击注册");

    public LoginGUI(){
        super("UattiBlog v1.0_Login");
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        Container c = this.getContentPane();
        userName.setFont(new Font("微软雅黑", Font.BOLD, 18));
        userPassword.setFont(new Font("微软雅黑", Font.BOLD, 18));
        c.add(new JLabel(new ImageIcon("src/com/Uatti/res/icon/bg_login.jpg")),BorderLayout.NORTH);
        JPanel jpc = new JPanel(new FlowLayout());

        Box boxLeft = Box.createVerticalBox();
        JLabel jLabel1 = new JLabel("用户名：");
        JLabel jLabel2 = new JLabel("密  码：");
        jLabel1.setFont(new Font("微软雅黑", Font.BOLD, 16));
        jLabel2.setFont(new Font("微软雅黑", Font.BOLD, 16));

        boxLeft.add(Box.createVerticalStrut(10));
        boxLeft.add(jLabel1);
        boxLeft.add(Box.createVerticalStrut(10));
        boxLeft.add(jLabel2);

        Box boxRight = Box.createVerticalBox();
        boxRight.add(userName);

        boxLeft.add(Box.createVerticalStrut(25));
        boxRight.add(userPassword);

        Box boxCenter = Box.createHorizontalBox();
        boxCenter.add(Box.createHorizontalStrut(35));
        boxCenter.add(boxLeft);
        boxCenter.add(boxRight);
        boxCenter.add(Box.createHorizontalStrut(50));

        //涓嬮儴鐧诲綍娉ㄥ唽鎸夐挳
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnLogin.setFont(new Font("微软雅黑", Font.BOLD, 16));
        btnRegist.setFont(new Font("微软雅黑", Font.BOLD, 16));
        jp.add(btnLogin);
        jp.add(btnRegist);
        c.add(boxCenter);
        c.add(jp,BorderLayout.SOUTH);

        //浜嬩欢鐩戝惉娉ㄥ唽
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text1 = userName.getText();
                String text2 = String.valueOf(userPassword.getPassword());

                if(text1==null||text1.equals(""))
                {
                    JOptionPane.showMessageDialog(LoginGUI.this,"璇疯緭鍏ョ敤鎴峰悕");
                }else if(text1.length()>=15){
                    JOptionPane.showMessageDialog(LoginGUI.this,"鐢ㄦ埛鍚嶈繃闀匡紝璇疯緭鍏�15浣嶄互鍐呯殑鐢ㄦ埛鍚�");
                }else if(text2.length()>=15){
                    JOptionPane.showMessageDialog(LoginGUI.this,"瀵嗙爜杩囬暱锛岃杈撳叆15浣嶄互鍐呯殑瀵嗙爜");
                }else if(text2==null||text2.equals("")){
                    JOptionPane.showMessageDialog(LoginGUI.this,"璇疯緭鍏ュ瘑鐮�");
                }else{

                    userService = new UserServiceImp();
                    User user = null;
                    try {
                        user = userService.checkUserPwd(text1,text2);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    if(user!=null){
                        LoginGUI.this.dispose();
                        try {
                            if(userName.getText().equals("1")){
                                new ManagerGUI(new User(2,"1","1","17616154090","110@qq.com")).setVisible(true);
                            }else {
                                new MainGUI(user);
                            }
                        } catch (FontFormatException e1) {
                            e1.printStackTrace();
                        }

                    }else{
                        JOptionPane.showMessageDialog(LoginGUI.this,"鐧婚檰澶辫触锛佽妫�鏌ョ敤鎴峰悕鍜屽瘑鐮併��");
                    }
                }
            }


        });
        btnRegist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterGUI();
            }
        });

        this.setResizable(false);
        this.setBounds(500,200,500,375);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
     new LoginGUI();
    }

}
