package com.Uatti.controller;

import com.Uatti.Service.BlogService;
import com.Uatti.Service.imp.BlogServiceImp;
import com.Uatti.entity.Blog;
import com.Uatti.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class MainGUI extends javax.swing.JFrame {

    private static User user;
    private DefaultListModel<Blog> listModelMyBlogs;
    private DefaultListModel<Blog> listModelCmtBlogs;
    private DefaultListModel<Blog> listModelHotBlogs;
    private BlogService blogService = new BlogServiceImp();
    private List<Blog> myBlogs;
    private List<Blog> cmtBlogs;
    private List<Blog> hotlogs;
    private JButton jButtonQuit;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JButton jButtonUserInfo;
    private javax.swing.JButton jButtonWrite;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelCmtBlogs;
    private javax.swing.JLabel jLabelMyBlogs;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPwd;
    private javax.swing.JLabel jLabelShowEmail;
    private javax.swing.JLabel jLabelShowName;
    private ListFont jListHotBlogs;
    private ListFont jListMyBlogs;
    private ListFont  jListCmtBlogs;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelInTabHome;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelMyBlog;
    private javax.swing.JPanel jPanelRcmd;
    private javax.swing.JPanel jPanelUserDetail;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPaneMain;

    public MainGUI(User user) throws FontFormatException {
        MainGUI.user = user;

        try {
            initBlogList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initComponents();

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws FontFormatException {
        /*皮肤*/
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        new MainGUI(user);

    }
    @SuppressWarnings("unchecked")

    public void initBlogList() throws SQLException {
        listModelMyBlogs = new DefaultListModel<Blog>();
        listModelCmtBlogs = new DefaultListModel<Blog>();
        listModelHotBlogs = new DefaultListModel<Blog>();
        /**
         * 查找用户的所有文章
         */
        this.myBlogs = blogService.getAllMyBlog(user.getUserName());

        /**
         * 数据库选取十条
         */
        this.cmtBlogs = blogService.selectCmtBlog(user.getUserName());

        /**
         * 查找除了本用户最多的五条数据
         */
        this.hotlogs = blogService.queryHotBlog(user.getUserName());
        if(myBlogs!=null){
            freshListModel(myBlogs,1);
            jListMyBlogs = new ListFont(listModelMyBlogs,new Font(StyleArgument.FONTNAME,StyleArgument.FONTSTYLE,StyleArgument.FONTSIZE));
            jListMyBlogs.setModel(listModelMyBlogs);
            jListMyBlogs.addMouseListener(new MyMouseAdapter(jListMyBlogs,user));

        }
        
        if(cmtBlogs!=null){
            freshListModel(cmtBlogs,0);
            jListCmtBlogs = new ListFont(listModelCmtBlogs,new Font(StyleArgument.FONTNAME,StyleArgument.FONTSTYLE,StyleArgument.FONTSIZE));
            jListCmtBlogs.setModel(listModelCmtBlogs);
            jListCmtBlogs.addMouseListener(new MyMouseAdapter(jListCmtBlogs,user));

        }
        if(hotlogs!=null){
            freshListModel(hotlogs,-1);
            jListHotBlogs = new ListFont(listModelHotBlogs,new Font(StyleArgument.FONTNAME,StyleArgument.FONTSTYLE,12));
            jListHotBlogs.setModel(listModelHotBlogs);
            jListHotBlogs.addMouseListener(new MyMouseAdapter(jListHotBlogs,user));
        }

    }
    private void freshListModel(List<Blog> blogs,int jListId){
        if(jListId==1){
            listModelMyBlogs.removeAllElements();
            for(Blog b:blogs){
                listModelMyBlogs.addElement(b);
            }
        }if(jListId==0){
            listModelCmtBlogs.removeAllElements();
            for(Blog b:blogs){
                listModelCmtBlogs.addElement(b);
            }
        }else {
            listModelHotBlogs.removeAllElements();
            for(Blog b:blogs){
                listModelHotBlogs.addElement(b);
            }
        }

    }
    private void initComponents() throws FontFormatException {
        jPanelUserDetail = new javax.swing.JPanel();
        jButtonUserInfo = new javax.swing.JButton();
        jLabelName = new javax.swing.JLabel();
        jLabelPwd = new javax.swing.JLabel();
        jLabelShowName = new javax.swing.JLabel();
        jLabelShowEmail = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanelMain = new javax.swing.JPanel();
        jTabbedPaneMain = new javax.swing.JTabbedPane();
        jPanelInTabHome = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jButtonQuit = new JButton();
        jButtonEdit = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabelCmtBlogs = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jPanelMyBlog = new javax.swing.JPanel();
        jLabelMyBlogs = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanelRcmd = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jButtonWrite = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonUserInfo.setText("个人主页");
        jButtonUserInfo.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonUserInfoActionPerformed(evt);
            }
        });

        jLabelName.setText("用户名：");

        jLabelPwd.setText("邮  箱");

        jLabelShowName.setText(user.getUserName());

        jLabelShowEmail.setText(user.getEmail());
        jButtonQuit.setText("注销");
        jButtonQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQuitActionPerformed(evt);
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
                                .addComponent(jButtonUserInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelUserDetailLayout.setVerticalGroup(
                jPanelUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelUserDetailLayout.createSequentialGroup()
                                .addContainerGap(37, Short.MAX_VALUE)
                                .addGroup(jPanelUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelName, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                        .addComponent(jLabelShowName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addGroup(jPanelUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelShowEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonUserInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23))
        );
        jScrollPane3.setViewportView(jListCmtBlogs);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 93, Short.MAX_VALUE)
        );

        jLabelCmtBlogs.setFont(new java.awt.Font("微软雅黑", 0, 18)); // NOI18N
        jLabelCmtBlogs.setText("社区博客");

        javax.swing.GroupLayout jPanelInTabHomeLayout = new javax.swing.GroupLayout(jPanelInTabHome);
        jPanelInTabHome.setLayout(jPanelInTabHomeLayout);
        jPanelInTabHomeLayout.setHorizontalGroup(
                jPanelInTabHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelInTabHomeLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelInTabHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane3)
                                        .addGroup(jPanelInTabHomeLayout.createSequentialGroup()
                                                .addComponent(jLabelCmtBlogs)
                                                .addGap(0, 825, Short.MAX_VALUE))
                                        .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addContainerGap())
        );
        jPanelInTabHomeLayout.setVerticalGroup(
                jPanelInTabHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelInTabHomeLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelCmtBlogs, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE))
        );


        jTabbedPaneMain.addTab("有态度首页", jPanelInTabHome);

        jLabelMyBlogs.setFont(new java.awt.Font("微软雅黑", 0, 18));
        jLabelMyBlogs.setText("我的文章");
        jButtonEdit.setFont(new java.awt.Font("微软雅黑", 0, 12));
        jButtonEdit.setText("编辑");
        jButtonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButtonEditActionPerformed(e);
            }
        });
        jButtonDelete.setFont(new java.awt.Font("微软雅黑", 0, 12));
        jButtonDelete.setText("删除");
        jButtonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButtonDeleteActionPerformed(e);
            }
        });

        jScrollPane2.setViewportView(jListMyBlogs);

        javax.swing.GroupLayout jPanelMyBlogLayout = new javax.swing.GroupLayout(jPanelMyBlog);
        jPanelMyBlog.setLayout(jPanelMyBlogLayout);
        jPanelMyBlogLayout.setHorizontalGroup(
                jPanelMyBlogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMyBlogLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelMyBlogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanelMyBlogLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 901, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelMyBlogLayout.createSequentialGroup()
                                                .addComponent(jLabelMyBlogs)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButtonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26)
                                                .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jSeparator2))
                                .addContainerGap())
        );
        jPanelMyBlogLayout.setVerticalGroup(
                jPanelMyBlogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMyBlogLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelMyBlogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelMyBlogs, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonEdit)
                                        .addComponent(jButtonDelete))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jTabbedPaneMain.addTab("我的BLOG", jPanelMyBlog);

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
                jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPaneMain)
        );
        jPanelMainLayout.setVerticalGroup(
                jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jTabbedPaneMain, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPaneMain.getAccessibleContext().setAccessibleName("首页");
        jScrollPane1.setViewportView(jListHotBlogs);

        jLabel1.setFont(new java.awt.Font("微软雅黑", 0, 18)); // NOI18N
        jLabel1.setText("热门文章");

        javax.swing.GroupLayout jPanelRcmdLayout = new javax.swing.GroupLayout(jPanelRcmd);
        jPanelRcmd.setLayout(jPanelRcmdLayout);
        jPanelRcmdLayout.setHorizontalGroup(
                jPanelRcmdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelRcmdLayout.createSequentialGroup()
                                .addGroup(jPanelRcmdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanelRcmdLayout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanelRcmdLayout.setVerticalGroup(
                jPanelRcmdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRcmdLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButtonWrite.setText("立即创作");
        jButtonWrite.setFont(new Font("微软雅黑", 0, 18));
        jButtonWrite.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWriteActionPerformed(evt);
            }
        });

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanelUserDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanelRcmd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonWrite, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanelUserDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButtonWrite, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanelRcmd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );

        jPanelRcmd.getAccessibleContext().setAccessibleName("");
        jPanelRcmd.getAccessibleContext().setAccessibleDescription("");

        pack();
    }

    private void jButtonQuitActionPerformed(java.awt.event.ActionEvent evt) {
        if(JOptionPane.showConfirmDialog(MainGUI.this, "是否注销？") == JOptionPane.YES_OPTION){
            MainGUI.this.dispose();
            new LoginGUI();
        }
    }
    private void jButtonUserInfoActionPerformed(java.awt.event.ActionEvent evt) {
        new MySpace(user);
    }

    private void jButtonWriteActionPerformed(java.awt.event.ActionEvent evt) {
        MainGUI.this.dispose();
        new Write(user);
    }
    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {
        Blog blog = jListMyBlogs.getSelectedValue();
        if(blog==null){
            JOptionPane.showMessageDialog(MainGUI.this,"请选中想要操作的博客");
        }else {
            MainGUI.this.dispose();
            new Write(user,blog);
        }
    }
    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        Blog blog = jListMyBlogs.getSelectedValue();
        if(blog==null){
            JOptionPane.showMessageDialog(MainGUI.this,"请选中想要操作的博客");
        }else {
            try {
                blogService.deleteBlog(blog);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            List<Blog> bs = null;
            try {
                bs = blogService.getAllMyBlog(user.getUserName());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            freshListModel(bs,1);
            jListMyBlogs.setModel(listModelMyBlogs);
        }
    }



    // End of variables declaration
}
