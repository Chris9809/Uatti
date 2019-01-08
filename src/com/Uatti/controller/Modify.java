package com.Uatti.controller;

import com.Uatti.Service.UserService;
import com.Uatti.Service.imp.UserServiceImp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Modify extends javax.swing.JFrame {
    private String modifyContent;
    private String username;
    private String oldContent;
    private UserService userService = new UserServiceImp();
    private javax.swing.JPasswordField jPasswordField;
    /**
     * Creates new form Modify
     */
    public Modify(String username,String modifyContent,String oldContent) {
        this.username = username;
        this.modifyContent = modifyContent;
        this.oldContent = oldContent;

        initComponents();
        setCharacterStyle();
        this.setVisible(true);
    }

    public void setCharacterStyle(){

        String borderContent;



        jLabelNew.setFont(new java.awt.Font("微软雅黑", 0, 18));
        jLabelNew.setText("更改为");
        jTextFieldNumber.setFont(new java.awt.Font("微软雅黑", 1, 14));
        jButtonSubmit.setFont(new java.awt.Font("微软雅黑", 0, 24));
        jButtonSubmit.setText("确认修改");

        if(modifyContent.equals("邮箱")){
            borderContent =  "设置新邮箱";
            jLabelOld.setText("原来邮箱为：");
            jPasswordField.setVisible(false);
            jButtonSubmit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int statue = 0;
                    String newContent = jTextFieldNumber.getText();
                    String regEx = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
                    // 编译正则表达式忽略大小写的写法
                    Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(newContent);
                    boolean rs = matcher.find();
                    if(rs==false){
                        JOptionPane.showMessageDialog(Modify.this,"请输入正确格式的邮箱");
                    }else if(newContent.equals("")||newContent==null){
                        JOptionPane.showMessageDialog(Modify.this,"邮箱不能为空");
                    }
                    try {
                         statue = userService.updateEmail(username,newContent);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    if(statue!=0){
                        JOptionPane.showMessageDialog(Modify.this,"修改成功!");
                    }else {
                        JOptionPane.showMessageDialog(Modify.this,"修改失败!请检查");
                    }
                }
            });

        }else if(modifyContent.equals("电话")){
            borderContent =  "设置电话号码";
            String newNumber = oldContent.substring(0,3)+"*****"+oldContent.substring(7,11);
            jLabelOld.setText("原号码："+newNumber);
            jPasswordField.setVisible(false);
            jButtonSubmit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int statue = 0;
                    String newContent = jTextFieldNumber.getText();
                    String regEx = "^1[7|4|6|3|4|5|8][0-9]\\d{8}$";
                    Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(newContent);
                    boolean rs = matcher.find();
                    if(rs==false){
                        JOptionPane.showMessageDialog(Modify.this,"请输入正确格式的手机号码");
                    }else if(newContent.equals("")||newContent==null){
                        JOptionPane.showMessageDialog(Modify.this,"手机号码不能为空");
                    }
                    try {
                        statue = userService.updatePhone(username,newContent);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    if(statue!=0){
                        JOptionPane.showMessageDialog(Modify.this,"修改成功!");
                    }else {
                        JOptionPane.showMessageDialog(Modify.this,"修改失败!请检查");
                    }
                }
            });
        }else {
            borderContent =  "修改密码";
            jTextFieldNumber.setVisible(false);
            jLabelOld.setText("");
            jButtonSubmit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int statue = 0;
                    String newPassword = String.valueOf(jPasswordField.getPassword());
                    String regEx = "^[A-Za-z]+[A-Za-z0-9]{7,15}$";
//                    String regEx = "^[\w]{8,15}$";
                    // 编译正则表达式忽略大小写的写法
                    Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(newPassword);
                    boolean rs = matcher.find();
                    if(rs==false){
                        JOptionPane.showMessageDialog(Modify.this,"请输入8-15位字母和数字，开头字母的密码");
                        return;
                    }else{
                        try {
                            statue = userService.updatePassword(username,newPassword);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                    if(statue!=0){
                        JOptionPane.showMessageDialog(Modify.this,"修改成功!");
                    }else {
                        JOptionPane.showMessageDialog(Modify.this,"修改失败!请检查");
                    }
                }
            });

        }

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, borderContent,
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("微软雅黑", 1, 24)));
        jLabelOld.setFont(new java.awt.Font("微软雅黑", 0, 18));


    }
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelOld = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelNew = new javax.swing.JLabel();
        jTextFieldNumber = new javax.swing.JTextField();
        jButtonSubmit = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jButtonBack = new javax.swing.JButton();
        jPasswordField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "修改电话", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("微软雅黑", 1, 24))); // NOI18N



        jPasswordField.setFont(new java.awt.Font("微软雅黑", 0, 24));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldNumber)
                        .addComponent(jButtonSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabelNew, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jPasswordField)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabelNew, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jTextFieldNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addComponent(jButtonSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelOld, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabelOld, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(39, Short.MAX_VALUE))
        );

        jButtonBack.setText("返回");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jSeparator2)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {
        Modify.this.dispose();
        try {
            new MySpace(userService.queryUserInfo(username));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Modify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonSubmit;
    private javax.swing.JLabel jLabelNew;
    private javax.swing.JLabel jLabelOld;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextFieldNumber;
    // End of variables declaration
}
