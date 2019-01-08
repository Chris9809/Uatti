package com.Uatti.controller;

import com.Uatti.Service.BlogService;
import com.Uatti.Service.ReportService;
import com.Uatti.Service.UserService;
import com.Uatti.Service.imp.BlogServiceImp;
import com.Uatti.Service.imp.ReportServiceImp;
import com.Uatti.Service.imp.UserServiceImp;
import com.Uatti.entity.Blog;
import com.Uatti.entity.Report;
import com.Uatti.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ManagerGUI extends javax.swing.JFrame {
    private static User user;
    private static Blog blog;
    private UserService userService = new UserServiceImp();
    private BlogService blogService = new BlogServiceImp();
    private ReportService reportService = new ReportServiceImp();
    private DefaultListModel<Blog> listModelBlogs = new DefaultListModel<>();
    private DefaultListModel<Report> listModelReport = new DefaultListModel<>();
    private List<Blog> blogs;
    private List<Blog> users;
    private List<Report> reports;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonBan;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonQuit;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonUserHome;
    private javax.swing.JButton jButtonUserInfo1;
    private javax.swing.JButton jButtonView;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPwd;
    private javax.swing.JLabel jLabelShowEmail;
    private javax.swing.JLabel jLabelShowName;

    private ListFont jListResult;
    private ListFontReport jList1;

    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelUserDetail;
    private javax.swing.JRadioButton jRadioButtonBlog;
    private javax.swing.JRadioButton jRadioButtonUser;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JToolBar jToolBar1;

    // End of variables declaration

    public ManagerGUI() {
        initComponents();
    }

    public ManagerGUI(User user) {
        ManagerGUI.user = user;
        try {
            this.reports = reportService.queryAllRepoter();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        freshReportListModel(reports);
        initListReport();
        initComponents();
    }

    private void initListReport(){
        jList1 = new ListFontReport(listModelReport);
        jList1.setModel(listModelReport);
    }
    private void freshBlogListModel(List<Blog> blogs){
        listModelBlogs.removeAllElements();
        for(Blog b:blogs){
            listModelBlogs.addElement(b);
        }
    }
    private void freshReportListModel(List<Report> reports){
        listModelReport.removeAllElements();
        for(Report r:reports){
            listModelReport.addElement(r);
        }
    }
    private void initComponents() {
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanelUserDetail = new javax.swing.JPanel();
        jButtonQuit = new javax.swing.JButton();
        jLabelName = new javax.swing.JLabel();
        jLabelPwd = new javax.swing.JLabel();
        jLabelShowName = new javax.swing.JLabel();
        jLabelShowEmail = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonUserInfo1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();

        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();

        jToolBar1 = new javax.swing.JToolBar();
        jButtonDelete = new javax.swing.JButton();
        jButtonView = new javax.swing.JButton();
        jButtonBan = new javax.swing.JButton();
        jButtonUserHome = new javax.swing.JButton();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jRadioButtonUser = new javax.swing.JRadioButton();
        jRadioButtonBlog = new javax.swing.JRadioButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        buttonGroup1.add(jRadioButtonUser);
        buttonGroup1.add(jRadioButtonBlog);
        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonQuit.setFont(new java.awt.Font("微软雅黑", 0, 15)); // NOI18N
        jButtonQuit.setText("注销");
        jButtonQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQuitActionPerformed(evt);
            }
        });

        jLabelName.setText("昵称");

        jLabelPwd.setText("邮箱");

        jLabelShowName.setText("null");

        jLabelShowEmail.setText("null");

        jButtonUserInfo1.setFont(new java.awt.Font("微软雅黑", 0, 15)); // NOI18N
        jButtonUserInfo1.setText("修改个人信息");
        jButtonUserInfo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUserInfo1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelUserDetailLayout = new javax.swing.GroupLayout(jPanelUserDetail);
        jPanelUserDetail.setLayout(jPanelUserDetailLayout);
        jPanelUserDetailLayout.setHorizontalGroup(
                jPanelUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelUserDetailLayout.createSequentialGroup()
                                .addGroup(jPanelUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelPwd, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelShowName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelShowEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelUserDetailLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButtonUserInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonQuit, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanelUserDetailLayout.setVerticalGroup(
                jPanelUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelUserDetailLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanelUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelShowName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addGroup(jPanelUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelShowEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanelUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButtonQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonUserInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(97, 97, 97))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("投诉信息"));

        jList1.setFont(new java.awt.Font("微软雅黑", 0, 15));

        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("结果查询"));


        jScrollPane2.setViewportView(jListResult);

        jToolBar1.setRollover(true);

        jButtonDelete.setFont(new java.awt.Font("微软雅黑", 0, 18)); // NOI18N
        jButtonDelete.setText("删除文章");
        jButtonDelete.setFocusable(false);
        jButtonDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButtonDelete);

        jButtonView.setFont(new java.awt.Font("微软雅黑", 0, 18)); // NOI18N
        jButtonView.setText("查看文章");
        jButtonView.setFocusable(false);
        jButtonView.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonView.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButtonView);

        jButtonBan.setFont(new java.awt.Font("微软雅黑", 0, 18)); // NOI18N
        jButtonBan.setText("封禁用户");
        jButtonBan.setFocusable(false);
        jButtonBan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonBan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButtonBan);

        jButtonUserHome.setFont(new java.awt.Font("微软雅黑", 0, 18)); // NOI18N
        jButtonUserHome.setText("访问主页");
        jButtonUserHome.setFocusable(false);
        jButtonUserHome.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonUserHome.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButtonUserHome);

        jTextFieldSearch.setFont(new java.awt.Font("微软雅黑", 0, 15)); // NOI18N


        jButtonSearch.setFont(new java.awt.Font("微软雅黑", 0, 15)); // NOI18N
        jButtonSearch.setText("搜索");


        jRadioButtonUser.setFont(new java.awt.Font("微软雅黑", 0, 15)); // NOI18N
        jRadioButtonUser.setText("查用户");

        jRadioButtonBlog.setFont(new java.awt.Font("微软雅黑", 0, 15)); // NOI18N
        jRadioButtonBlog.setText("找文章");
        jRadioButtonBlog.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(jRadioButtonBlog.isSelected()){
                    jButtonSearch.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                ManagerGUI.this.blogs = blogService.getAllMyBlog(jTextFieldSearch.getText());
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                            freshBlogListModel(blogs);
                            jListResult = new ListFont(listModelBlogs,new Font(StyleArgument.FONTNAME,StyleArgument.FONTSTYLE,StyleArgument.FONTSIZE));
                            jListResult.setModel(listModelBlogs);
                            jListResult.addMouseListener(new MyMouseAdapter(jListResult,ManagerGUI.user));
                        }
                    });
                }
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButtonSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jRadioButtonBlog)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jRadioButtonUser)))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 126, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jRadioButtonBlog)
                                        .addComponent(jRadioButtonUser))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextFieldSearch)
                                        .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanelUserDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanelUserDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    private void jButtonQuitActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButtonUserInfo1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(ManagerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerGUI().setVisible(true);
            }
        });
    }


}

