package com.Uatti.controller;
import com.Uatti.Service.UserService;
import com.Uatti.Service.imp.UserServiceImp;
import com.Uatti.entity.User;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2018-11-17
 * Time: 下午 1:40
 * Function:
 */
public class RegisterGUI extends JFrame {

    private JTextField userName;
    private JPasswordField userPwd;
    private JPasswordField confirmPwd;
    private JTextField phone;
    private JTextField email;
    private JButton btnDone;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel labelAlert1;
    private JLabel labelAlert2;
    private JLabel labelAlert3;
    private JLabel labelAlert4;
    private JLabel labelAlert5;
    private boolean statue;


    public RegisterGUI(){
        super("有态度blog用户端 v1.0_用户注册");
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        Container c = this.getContentPane();
        c.add(new JLabel(new ImageIcon("src/com/Uatti/res/icon/bg_register.jpg")),BorderLayout.NORTH);


        //创建、设置TextField样式
        setTextField();

        //创建、设置label样式
        setLabelStyle();

        Box boxLeft = Box.createVerticalBox();
        boxLeft.add(Box.createVerticalStrut(20));
        boxLeft.add(jLabel1);
        boxLeft.add(Box.createVerticalStrut(50));
        boxLeft.add(jLabel2);
        boxLeft.add(Box.createVerticalStrut(50));
        boxLeft.add(jLabel3);
        boxLeft.add(Box.createVerticalStrut(50));
        boxLeft.add(jLabel4);
        boxLeft.add(Box.createVerticalStrut(50));
        boxLeft.add(jLabel5);
        boxLeft.add(Box.createVerticalStrut(50));

        Box boxRight = Box.createVerticalBox();
        boxRight.add(Box.createVerticalStrut(30));
        boxRight.add(Box.createVerticalStrut(20));
        boxRight.add(userName);
        labelAlert1 = new JLabel("   ");
        boxRight.add(labelAlert1);
        boxRight.add(Box.createVerticalStrut(20));
        boxRight.add(userPwd);
        labelAlert2 = new JLabel("   ");
        boxRight.add(labelAlert2);
        boxRight.add(Box.createVerticalStrut(20));
        boxRight.add(confirmPwd);
        labelAlert3 = new JLabel("  ");
        boxRight.add(labelAlert3);
        boxRight.add(Box.createVerticalStrut(20));
        boxRight.add(phone);
        labelAlert4 = new JLabel("  ");
        boxRight.add(labelAlert4);
        boxRight.add(Box.createVerticalStrut(20));
        boxRight.add(email);
        labelAlert5 = new JLabel("  ");
        boxRight.add(labelAlert5);
        boxRight.add(Box.createVerticalStrut(65));

        Box boxCenter = Box.createHorizontalBox();
        Box boxMarginLeft = Box.createHorizontalBox();
        boxMarginLeft.add(Box.createHorizontalStrut(25));
        Box boxMarginRight = Box.createHorizontalBox();
        boxMarginRight.add(Box.createHorizontalStrut(25));
        Box boxMarginMid = Box.createHorizontalBox();
        boxMarginMid.add(Box.createHorizontalStrut(15));

        boxCenter.add(boxMarginLeft);
        boxCenter.add(boxLeft);
        boxCenter.add(boxMarginMid);
        boxCenter.add(boxRight);
        boxCenter.add(boxMarginRight);

        boxCenter.add(Box.createVerticalStrut(50));
        c.add(boxCenter);
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.CENTER));
        /**
         * 注册事件
         */
        btnDone = new JButton("立即注册");
        btnDone.setFont(new Font("微软雅黑", Font.BOLD, 16));
        btnDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //是否填写完全
                if(String.valueOf(userName.getText())!=null&&String.valueOf(userPwd.getPassword())!=null&&
                        String.valueOf(phone.getText())!=null&&String.valueOf(email.getText())!=null){
                    statue=true;
                }
                if(statue){
                    UserService userService = new UserServiceImp();
                    User user = new User(String.valueOf(userName.getText()),String.valueOf(userPwd.getPassword()),
                            String.valueOf(phone.getText()),String.valueOf(email.getText()));
                        JOptionPane.showMessageDialog(RegisterGUI.this,"注册成功！确认返回登录");
                    /*写入数据库*/
                    try {
                        userService.insertUser(user);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    /*关闭注册界面回到登录界面*/
                    RegisterGUI.this.dispose();
                    new LoginGUI();
                }else{
                    JOptionPane.showMessageDialog(RegisterGUI.this,"还有信息没有填写哦！");
                }

            }
        });

        jp.add(btnDone);
        c.add(jp,BorderLayout.SOUTH);


        this.setResizable(false);
        this.setBounds(500,200,500,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    //设置label样式
    public void setLabelStyle(){
        jLabel1=new JLabel("用户名");
        jLabel1.setFont(new Font("微软雅黑",Font.BOLD, 16));
        jLabel2=new JLabel("输入密码");
        jLabel2.setFont(new Font("微软雅黑",Font.BOLD, 16));
        jLabel3=new JLabel("再次输入密码");
        jLabel3.setFont(new Font("微软雅黑",Font.BOLD, 16));
        jLabel4=new JLabel("手机号码");
        jLabel4.setFont(new Font("微软雅黑",Font.BOLD, 16));
        jLabel5=new JLabel("邮   箱");
        jLabel5.setFont(new Font("微软雅黑",Font.BOLD, 16));
    }

    /**
     * setTextField
     * 用户名设置验证规则
     */
    public void setTextField(){
        /**
         * 8-15位数字和字母（大写/小写）组合，字母开头
         */
        userName = new JTextField(15);
        userName.setFont(new Font("微软雅黑", Font.BOLD, 16));
        userName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                labelAlert1.setForeground(Color.black);
                labelAlert1.setText("请填写8-15位数字和字母（大写/小写）组合，字母开头");
            }

            @Override
            public void focusLost(FocusEvent e) {
                String str = String.valueOf(userName.getText());
                // 正则表达式规则:8-15位字母和数字，开头字母
                String regEx = "^[A-Za-z][A-Za-z0-9]{7,15}+$";
                // 编译正则表达式忽略大小写的写法
                Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(str);
                boolean rs = matcher.find();
                System.out.println(rs);
                if((str==null)||String.valueOf(userName.getText()).equals("")){
                    labelAlert1.setForeground(Color.red);
                    labelAlert1.setText("用户名不能为空");

                } else if(rs==true) {
                    UserService userService = new UserServiceImp();
                    Boolean isExisted = true;
                    try {
                        isExisted = userService.queryIsExisted(str);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    if (isExisted != true) {
                        labelAlert1.setForeground(Color.GREEN);
                        labelAlert1.setText("恭喜，用户名可用");
                    } else {
                        labelAlert1.setForeground(Color.red);
                        labelAlert1.setText("该用户名被占用");
                    }
                }else{
                    labelAlert1.setForeground(Color.red);
                    labelAlert1.setText("用户名不正确，请按照正确的格式输入");
                }
            }
        });
        /**
         * 密码设置验证规则：
         * ：1 8-15位字母和英文的组合
         */
        userPwd = new JPasswordField(15);
        userPwd.setFont(new Font("微软雅黑", Font.BOLD, 16));
        userPwd.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                String str = String.valueOf(userPwd.getPassword());
                // 正则表达式规则:8-15位字母和数字，开头字母
//                String regEx = "^([A-Z]|[a-z]|[0-9]|){8,15}$";
//                String regEx = "^[A-Za-z0-9]{8,15}$";
                String regEx = "^[A-Za-z]+[A-Za-z0-9]{7,15}$";
                // 编译正则表达式忽略大小写的写法
                Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(str);
                boolean rs = matcher.find();
                if(rs==false){
                    labelAlert2.setForeground(Color.red);
                    labelAlert2.setText("密码设置不正确，8-15位字母和数字,必须包含一个字母");
                }else if(str.equals(String.valueOf(userName.getText()))){
                    labelAlert2.setForeground(Color.red);
                    labelAlert2.setText("密码不能和用户名相同");
                }else{
                    labelAlert2.setForeground(Color.GREEN);
                    labelAlert2.setText("密码设置正确");
                }
            }
        });
        /**
         * 密码确认验证规则
         * ：只能和密码相同
         */
        confirmPwd = new JPasswordField(15);
        confirmPwd.setFont(new Font("微软雅黑", Font.BOLD, 16));
        confirmPwd.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {}
            @Override
            public void focusLost(FocusEvent e) {
                String password1 = String.valueOf(userPwd.getPassword());
                String password2 = String.valueOf(confirmPwd.getPassword());
                if (!password1.equals(password2))
                {
                    labelAlert3.setForeground(Color.red);
                    labelAlert3.setText("确认密码和密码不一致，请检查");
                }else if(password1==null||password2==null||password1.equals("")||password2.equals("")){
                    labelAlert3.setText("   ");
                }else{
                    labelAlert3.setText("   ");
                }
            }
        });
        phone = new JTextField(15);
        phone.setFont(new Font("微软雅黑", Font.BOLD, 16));
        phone.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { }

            @Override
            public void focusLost(FocusEvent e) {
                String str = String.valueOf(phone.getText());
                // 正则表达式规则:8-15位字母和数字，开头字母
                String regEx = "^1[7|4|6|3|4|5|8][0-9]\\d{8}$";
                // 编译正则表达式忽略大小写的写法
                Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(str);
                boolean rs = matcher.find();
                if(rs==false){
                    labelAlert4.setForeground(Color.red);
                    labelAlert4.setText("请输入合法的手机号码");
                }else if(str.equals("")||str==null){
                    labelAlert4.setForeground(Color.red);
                    labelAlert4.setText("手机号码不能为空");
                }else{
                    labelAlert4.setText("   ");
                }
            }
        });
        email = new JTextField(15);
        email.setFont(new Font("微软雅黑", Font.BOLD, 16));
        email.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                String str = String.valueOf(email.getText());
                // 正则表达式规则:
                String regEx = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
                // 编译正则表达式忽略大小写的写法
                Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(str);
                boolean rs = matcher.find();
                if(rs==false){
                    labelAlert5.setForeground(Color.red);
                    labelAlert5.setText("请输入合法的邮箱地址");
                }else if(str.equals("")||str==null){
                    labelAlert5.setForeground(Color.red);
                    labelAlert5.setText("邮箱地址不能为空");
                }else{
                    labelAlert5.setText("   ");
                }
            }
        });
    }

    public static void main(String[] args) {
        new RegisterGUI();
    }
}
