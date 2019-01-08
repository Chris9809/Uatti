package com.Uatti.controller;

import com.Uatti.Service.ActionService;
import com.Uatti.Service.BlogService;
import com.Uatti.Service.UserService;
import com.Uatti.Service.imp.ActionServiceImp;
import com.Uatti.Service.imp.BlogServiceImp;
import com.Uatti.Service.imp.UserServiceImp;
import com.Uatti.entity.Action;
import com.Uatti.entity.Blog;
import com.Uatti.entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class MySpace extends javax.swing.JFrame {

    private static User user;
    private BlogService blogService = new BlogServiceImp();
    private UserService userService = new UserServiceImp();
    private ActionService actionService = new ActionServiceImp();

    // Variables declaration - do not modify
    private javax.swing.JButton jButtonMdfPassword;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonMdfEmail;
    private javax.swing.JButton jButtonMdfPhone;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelExp;
    private javax.swing.JLabel jLabelPhone;
    private javax.swing.JLabel jLabelShowExp;
    private javax.swing.JLabel jLabelUserName;
    private ListFontAction jListAction;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private DefaultListModel<Action> listModelActions = new DefaultListModel<>();
    private List<Action> actions;

    // End of variables declaration
    /**
     * Creates new form MySpace
     */
    public MySpace(User user) {
        MySpace.user = user;
        try {
            this.actions = actionService.queryAllMyActions(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        freshListModel(actions);
        initActionList();
        initComponents();
        this.setVisible(true);
    }
    private void initActionList(){
        jListAction = new ListFontAction(listModelActions);
        jListAction.setModel(listModelActions);
    }
    private void freshListModel(List<Action> actions){
        listModelActions.removeAllElements();
        for(Action a:actions){
            listModelActions.addElement(a);
        }

    }
    private void initComponents() {
        jButtonMdfPassword = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonBack = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabelUserName = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jLabelPhone = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabelExp = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabelShowExp = new javax.swing.JLabel();
        jButtonMdfPhone = new javax.swing.JButton();
        jButtonMdfEmail = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonBack.setText("返回");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "我的信息", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("微软雅黑", 0, 24))); // NOI18N

        jLabelUserName.setFont(new java.awt.Font("微软雅黑", 1, 24)); // NOI18N
        jLabelUserName.setText(user.getUserName());

        jLabelEmail.setFont(new java.awt.Font("微软雅黑", 1, 24)); // NOI18N
        jLabelEmail.setText(user.getEmail());

        jLabelPhone.setFont(new java.awt.Font("微软雅黑", 1, 24)); // NOI18N
        jLabelPhone.setText(user.getPhone());

        jLabelExp.setFont(new java.awt.Font("微软雅黑", 0, 24)); // NOI18N
        jLabelExp.setText("热力值：");

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabelShowExp.setFont(new java.awt.Font("微软雅黑", 0, 36)); // NOI18N
        try {
            jLabelShowExp.setText(String.valueOf(blogService.sumPageView(user.getUserName())));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jButtonMdfPhone.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        jButtonMdfPhone.setText("设置");
        jButtonMdfPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMdfPhoneActionPerformed(evt);
            }
        });

        jButtonMdfEmail.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        jButtonMdfEmail.setText("设置");
        jButtonMdfEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MySpace.this.dispose();
                new Modify(user.getUserName(),"邮箱",user.getEmail());
            }
        });

        jButtonMdfPassword.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        jButtonMdfPassword.setText("修改密码");
        jButtonMdfPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMdfPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(64, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jSeparator3)
                                                .addComponent(jSeparator2)
                                                .addComponent(jSeparator4)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabelUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jButtonMdfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabelPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jButtonMdfPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonMdfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(30, 30, 30)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelShowExp, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelExp, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabelExp)
                                                .addGap(48, 48, 48)
                                                .addComponent(jLabelShowExp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(43, 43, 43)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabelUserName)
                                                        .addComponent(jButtonMdfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(1, 1, 1)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabelPhone)
                                                                        .addComponent(jButtonMdfPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(5, 5, 5)
                                                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabelEmail))
                                                        .addComponent(jButtonMdfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "动态", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("微软雅黑", 0, 24))); // NOI18N

        jScrollPane1.setViewportView(jListAction);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jSeparator1)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(16, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {
        MySpace.this.dispose();
    }

    private void jButtonMdfPhoneActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        new Modify(user.getUserName(),"电话",user.getPhone());
    }

    private void jButtonMdfPasswordActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        new Modify(user.getUserName(),"密码",user.getUserPassword());
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
            java.util.logging.Logger.getLogger(MySpace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MySpace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MySpace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MySpace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


    }

}
