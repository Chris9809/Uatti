package com.Uatti.controller;

import com.Uatti.Service.ActionService;
import com.Uatti.Service.BlogService;
import com.Uatti.Service.UserService;
import com.Uatti.Service.imp.ActionServiceImp;
import com.Uatti.Service.imp.BlogServiceImp;
import com.Uatti.Service.imp.UserServiceImp;
import com.Uatti.entity.Blog;
import com.Uatti.entity.Report;
import com.Uatti.entity.User;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class Read extends javax.swing.JFrame {
    private HTMLDocument document;
    static Blog blog = new Blog();
    static User user;
    static User visiter;
    private UserService userService = new UserServiceImp();
    private BlogService blogService = new BlogServiceImp();
    private ActionService actionService = new ActionServiceImp();
    private DefaultListModel<Blog> listModelBlogs = new DefaultListModel<>();
    private List<Blog> blogs;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonLike;
    private javax.swing.JButton jButtonReport;
    private javax.swing.JButton jButtonUserHome;
    private javax.swing.JLabel jLabelBoard;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelRltBlogs;
    private javax.swing.JLabel jLabelShowEmail;
    private javax.swing.JLabel jLabelShowName;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelShowExp;
    private ListFont jListRltBlogs;
    private javax.swing.JPanel jPanelUserDetail;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextPane jTextPaneContent;
    // End of variables declaration  
    public Read(Blog blog,User visiter) throws Exception {
        Read.visiter = visiter;
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        HTMLEditorKit editorKit = new HTMLEditorKit();
        /*创建默认文档指向网页引用document*/
        document = (HTMLDocument)editorKit.createDefaultDocument();
        BlogService blogService = new BlogServiceImp();
        Read.blog = blog;
        user = userService.queryUserInfo(blog.getUsername());
        initComponents();


    }

    private void initComponents() throws Exception {

        jButtonBack = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();

        jTextPaneContent = new JTextPane();
        jTextPaneContent.setContentType("text/html");
        jLabelTitle = new javax.swing.JLabel();
        jPanelUserDetail = new javax.swing.JPanel();
        jButtonUserHome = new javax.swing.JButton();
        jLabelName = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jLabelShowName = new javax.swing.JLabel();
        jLabelShowEmail = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabelRltBlogs = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jButtonLike = new javax.swing.JButton();
        jButtonReport = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jLabelBoard = new javax.swing.JLabel();
        jLabelShowExp = new javax.swing.JLabel();


        jButtonBack.setText("返回");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });
        /*正文和标题设置，读取传入参数里面的文字*/
        jLabelTitle.setFont(new java.awt.Font("微软雅黑", 1, 18)); // NOI18N
        jLabelTitle.setText(blog.getTitle());
        jTextPaneContent.setFont(new java.awt.Font("微软雅黑", 0, 15)); // NOI18N
        jTextPaneContent.setText(blog.getContent());
        /*不可编辑状态*/
        jTextPaneContent.setEditable(false);
        jTextPaneContent.setBackground(new Color(213, 216, 222));
        jScrollPane1.setViewportView(jTextPaneContent);



        jPanelUserDetail.setFont(new java.awt.Font("微软雅黑", 0, 15)); // NOI18N

        jButtonUserHome.setFont(new java.awt.Font("微软雅黑", 0, 15)); // NOI18N
        jButtonUserHome.setText("Ta的主页");
        jButtonUserHome.setActionCommand("");
        jButtonUserHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonUserHomeActionPerformed(evt);
            }
        });

        jLabelName.setFont(new java.awt.Font("微软雅黑", 0, 15)); // NOI18N
        jLabelName.setText("昵称：");

        jLabelEmail.setFont(new java.awt.Font("微软雅黑", 0, 15)); // NOI18N
        jLabelEmail.setText("邮箱:");

        jLabelShowName.setFont(new java.awt.Font("微软雅黑", 0, 15)); // NOI18N
        jLabelShowName.setText(blog.getUsername());

        jLabelShowEmail.setFont(new java.awt.Font("微软雅黑", 0, 15)); // NOI18N
        jLabelShowEmail.setText(user.getEmail());

        javax.swing.GroupLayout jPanelUserDetailLayout = new javax.swing.GroupLayout(jPanelUserDetail);
        jPanelUserDetail.setLayout(jPanelUserDetailLayout);
        jPanelUserDetailLayout.setHorizontalGroup(
                jPanelUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelUserDetailLayout.createSequentialGroup()
                                .addGroup(jPanelUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelUserDetailLayout.createSequentialGroup()
                                                .addGroup(jPanelUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanelUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabelShowEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabelShowName, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 11, Short.MAX_VALUE))
                        .addComponent(jButtonUserHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelUserDetailLayout.setVerticalGroup(
                jPanelUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelUserDetailLayout.createSequentialGroup()
                                .addContainerGap(37, Short.MAX_VALUE)
                                .addGroup(jPanelUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelShowName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addGroup(jPanelUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelShowEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addComponent(jButtonUserHome, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        blogs = blogService.getAllMyBlog(user.getUserName());
        freshListModel(blogs);
        jListRltBlogs = new ListFont(listModelBlogs,new Font(StyleArgument.FONTNAME,StyleArgument.FONTSTYLE,12));
        jListRltBlogs.setModel(listModelBlogs);
        jListRltBlogs.addMouseListener(new MyMouseAdapter(jListRltBlogs,visiter));
        jScrollPane2.setViewportView(jListRltBlogs);



        jLabelRltBlogs.setFont(new java.awt.Font("微软雅黑", 0, 18)); // NOI18N
        jLabelRltBlogs.setText("热门文章");
        jButtonLike.setFont(new java.awt.Font("微软雅黑", 0, 18)); // NOI18N
        jButtonLike.setText("赞一个");

        if(visiter.getUserName().equals(jLabelShowName.getText())){
            jButtonLike.setEnabled(false);
        }
        jButtonLike.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLikeActionPerformed(evt);
            }
        });

        jButtonReport.setFont(new java.awt.Font("微软雅黑", 0, 10));
        jButtonReport.setText("举报");
        jButtonReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(Read.this,"确定要举报这篇文章吗？")==JOptionPane.YES_OPTION){
                    Report report = new Report(blog.getbId(),user.getUserName(),blog.getTitle());
                    try {
                        blogService.reportBlog(report);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(Read.this,"举报成功，我们将在三个工作日内处理。");
                }else {
                    return;
                }
            }
        });

        jLabelBoard.setFont(new java.awt.Font("微软雅黑 Light", 0, 15));
        jLabelBoard.setText("jLabel1");

        jLabelShowExp.setFont(new java.awt.Font("微软雅黑 Light", 1, 24));
        jLabelShowExp.setText("热度:"+blogService.queryPageView(Read.blog));

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
                                        .addComponent(jLabelBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jButtonReport, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanelUserDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabelRltBlogs, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabelShowExp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonLike, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButtonLike, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(jLabelShowExp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanelUserDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabelRltBlogs, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
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
        Read.this.dispose();
        // TODO add your handling code here:
    }

    private void jButtonUserHomeActionPerformed(java.awt.event.ActionEvent evt) {
        new UserHome(user,visiter);
    }

    private void jButtonLikeActionPerformed(java.awt.event.ActionEvent evt) {
        Long pageView = Long.valueOf(jLabelShowExp.getText().substring(3));
        pageView++;
        jLabelShowExp.setFont(new java.awt.Font("微软雅黑 Light", 1, 24));
        jLabelShowExp.setText("热度:"+pageView);

        try {
            actionService.addActionRecord(visiter,user,blog,"点赞");
            blogService.updatePageView(blog);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jButtonLike.setEnabled(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info :UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Read.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Read.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Read.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Read.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

                 
}
