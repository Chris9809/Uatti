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

import static com.Uatti.controller.Read.blog;

/**
 *
 * @author Administrator
 */
public class UserHome extends javax.swing.JFrame {

    private User user;
    private User visiter;
    private List<Blog> blogs;
    private BlogService blogService = new BlogServiceImp();
    private DefaultListModel<Blog> listModelBlogs = new DefaultListModel<>();
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonHasCome;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelExp;
    private javax.swing.JLabel jLabelPhone;
    private javax.swing.JLabel jLabelShowExp;
    private javax.swing.JLabel jLabelUserName;
    private ListFont jListUsersAllBlogs;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelBlogsList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    // End of variables declaration
    /**
     * Creates new form UserHome
     */
    public UserHome(User user,User visiter) {
        this.user = user;
        this.visiter = visiter;

        try {
            initComponents();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.setVisible(true);
    }

    private void initComponents() throws SQLException {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jButtonBack = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
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
        jButtonHasCome = new javax.swing.JButton();
        jPanelBlogsList = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();


        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonBack.setText("返回");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ta的信息", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("微软雅黑", 0, 24))); // NOI18N

        jLabelUserName.setFont(new java.awt.Font("微软雅黑", 1, 24));
        jLabelUserName.setText(": "+user.getUserName());
        jLabelUserName.setIcon(new ImageIcon("src/com/Uatti/res/icon/我的.png"));

        jLabelPhone.setFont(new java.awt.Font("微软雅黑", 1, 24));
        jLabelPhone.setIcon(new ImageIcon("src/com/Uatti/res/icon/电话.png"));
        jLabelPhone.setText(": "+user.getPhone());

        jLabelEmail.setFont(new java.awt.Font("微软雅黑", 1, 24));
        jLabelEmail.setIcon(new ImageIcon("src/com/Uatti/res/icon/邮件.png"));
        jLabelEmail.setText(": "+user.getEmail());


        jLabelExp.setFont(new java.awt.Font("微软雅黑", 0, 24));
        jLabelExp.setText("热力值：");
        jLabelExp.setIcon(new ImageIcon("src/com/Uatti/res/icon/温度.png"));

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabelShowExp.setFont(new java.awt.Font("微软雅黑", 0, 36));
        try {
            jLabelShowExp.setText(String.valueOf(blogService.sumPageView(user.getUserName())));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        jButtonHasCome.setFont(new java.awt.Font("微软雅黑", 0, 24)); // NOI18N
        jButtonHasCome.setText("  顶Ta一下");
        jButtonHasCome.setIcon(new ImageIcon("src/com/Uatti/res/icon/火箭.png"));
        jButtonHasCome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Long pageView = blog.getPageView();
//                pageView++;
//                jLabelShowExp.setText(String.valueOf(pageView));
//                try {
//                    blogService.updatePageView(user.getUserName());
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//
//                blog.setPageView(pageView);
            }
        });


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabelPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabelUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                                                .addComponent(jSeparator2)
                                                .addComponent(jSeparator4))
                                        .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelShowExp, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelExp, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonHasCome, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabelExp)
                                                .addGap(48, 48, 48)
                                                .addComponent(jLabelShowExp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButtonHasCome, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(43, 43, 43)
                                                .addComponent(jLabelUserName)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(1, 1, 1)
                                                .addComponent(jLabelPhone)
                                                .addGap(9, 9, 9)
                                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabelEmail)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanelBlogsList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "全部BLOG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("微软雅黑", 0, 24))); // NOI18N

        blogs = blogService.getAllMyBlog(user.getUserName());
        freshListModel(blogs);
        jListUsersAllBlogs = new ListFont(listModelBlogs,new Font(StyleArgument.FONTNAME,StyleArgument.FONTSTYLE,18));
        jListUsersAllBlogs.setModel(listModelBlogs);
        jListUsersAllBlogs.addMouseListener(new MyMouseAdapter(jListUsersAllBlogs,visiter));

        jScrollPane2.setViewportView(jListUsersAllBlogs);

        javax.swing.GroupLayout jPanelBlogsListLayout = new javax.swing.GroupLayout(jPanelBlogsList);
        jPanelBlogsList.setLayout(jPanelBlogsListLayout);
        jPanelBlogsListLayout.setHorizontalGroup(
                jPanelBlogsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelBlogsListLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 811, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBlogsListLayout.setVerticalGroup(
                jPanelBlogsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelBlogsListLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanelBlogsList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelBlogsList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void freshListModel(List<Blog> blogs){
        listModelBlogs.removeAllElements();
        for(Blog b:blogs){
            listModelBlogs.addElement(b);
        }

    }

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {
        UserHome.this.dispose();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

}
