package com.Uatti.controller;
import com.Uatti.Service.BlogService;
import com.Uatti.Service.imp.BlogServiceImp;
import com.Uatti.entity.Blog;
import com.Uatti.entity.User;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.undo.*;

/**HTML文件文档编辑器*/
public class Write extends JFrame implements ActionListener
{
    /** 声明一个网页文档对象变量*/
    private HTMLDocument document;
    /** 创建一个文本编辑板*/
    private JTextField textTitle;
    private JTextPane textPane = new JTextPane();
    private File currentFile;
    /** 侦听在当前文档上的编辑器 */
    protected UndoableEditListener undoHandler = new UndoHandler();
    /** 添加撤消管理器 */
    protected UndoManager undo = new UndoManager();
    /** 添加撤消侦听器*/
    private UndoAction undoAction = new UndoAction();
    /** 添加恢复侦听器*/
    private RedoAction redoAction = new RedoAction();
    /** 添加加粗侦听器*/
    private Action boldAction = new StyledEditorKit.BoldAction();
    /** 添加加下划线侦听器*/
    private Action underlineAction = new StyledEditorKit.UnderlineAction();
    /** 添加倾斜侦听器*/
    private Action italicAction = new StyledEditorKit.ItalicAction();
    private Action insertBreakAction = new DefaultEditorKit.InsertBreakAction();
    private BlogService blogService;
    /**当前用户名*/
    private User currentUser;
    private Blog blog;
    /*编辑模式区分开始创作和二次编辑*/
    private boolean isFirstEdit;
    

    /** 构造方法*/
    public Write(User currentUser)
    {
        /** 设置主窗体标题*/
        super("有态度博客,记录你的Idea");
        this.isFirstEdit = true;
        //创建HTML解析器
        HTMLEditorKit editorKit = new HTMLEditorKit();

        /*创建默认文档指向网页引用document*/
        document = (HTMLDocument)editorKit.createDefaultDocument();
        //设置风格样式
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        this.currentUser = currentUser;

        //调用初始化方法
        init();
        this.setVisible(true);

    }
    /** 构造方法*/
    public Write(User currentUser,Blog blog)
    {
        /** 设置主窗体标题*/
        super(blog.getTitle());
        this.isFirstEdit = false;
        //创建HTML解析器
        HTMLEditorKit editorKit = new HTMLEditorKit();
        this.blog = blog;
        /*创建默认文档指向网页引用document*/
        document = (HTMLDocument)editorKit.createDefaultDocument();
        //设置风格样式
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        this.currentUser = currentUser;

        //调用初始化方法
        init();
        textTitle.setText(blog.getTitle());
        textPane.setText(blog.getContent());
        this.setVisible(true);
    }
    /**初始化各组件的方法*/
    public void init()
    {
        //调用自定义继承WindowListener的侦听器FrameListener，给主窗体添加WindowListener
        addWindowListener(new FrameListener());

        JMenuBar menuBar = new JMenuBar();
        getContentPane().add(menuBar, BorderLayout.NORTH);
        JMenu fileMenu = new JMenu("文件");  //文件
        JMenu helpMenu = new JMenu("帮助");  //帮助

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        JMenuItem newItem = new JMenuItem("新建");  //新建
        JMenuItem openItem = new JMenuItem("打开" );    //打开
        JMenuItem saveItem = new JMenuItem("保存" );    //保存
        JMenuItem saveAsItem = new JMenuItem("另存为");         //另存
        JMenuItem exitItem = new JMenuItem("退出");    //退出

        newItem.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        saveAsItem.addActionListener(this);
        exitItem.addActionListener(this);

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.add(exitItem);

        //给菜单项添加侦听器
        JMenuItem clearItem = new JMenuItem("清空");       //清除
        JMenuItem selectAllItem = new JMenuItem("清空所有");     //全选
        JMenuItem insertBreaKItem = new JMenuItem(insertBreakAction);

        insertBreaKItem.setText("清空一行");

        clearItem.addActionListener(this);
        selectAllItem.addActionListener(this);

        /*******************************************/
        //帮助项目添加菜单按钮
        JMenuItem helpItem = new JMenuItem("帮助");
        JMenuItem shortcutsItem = new JMenuItem("快捷键");
        JMenuItem aboutItem = new JMenuItem("关于作者");
        //添加菜单项目到菜单
        helpMenu.add(shortcutsItem);
        helpMenu.add(aboutItem);
        helpMenu.add(helpItem);
        //设置监听
        aboutItem.addActionListener(this);
        helpItem.addActionListener(this);
        shortcutsItem.addActionListener(this);

        /*菜单栏下方按钮组*/
        JPanel editorControlPanel = new JPanel();
        editorControlPanel.setLayout(new FlowLayout());

        /*保存文章到本地*/
        JButton saveBtn = new JButton("保存");
        saveBtn.setIcon(new ImageIcon("src/com/Uatti/res/icon/保存.png"));
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(textTitle.getText());
                System.out.println(textPane.getText());
            }
        });

        /*保存（上传数据库按钮）*/
        JButton submitBtn = new JButton("上传");
        submitBtn.setIcon(new ImageIcon("src/com/Uatti/res/icon/上传.png"));
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = textTitle.getText();
                String content = textPane.getText();
                try {
                    submitDocument(title,content);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        /*字体大小选择下拉框*/
        JLabel fontLabel = new JLabel("字体");
        JComboBox fontCb = new JComboBox();
        fontCb.setPreferredSize(new Dimension(100, 35));
        fontCb.setModel(new DefaultComboBoxModel(new String[] {"微软雅黑","黑体","宋体"}));
        fontCb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Object selectItem = e.getItem();
                //字体设置
                switch (selectItem.toString())
                {
                    case "黑体":
                        fontCb.setAction(new StyledEditorKit.FontFamilyAction("黑体", "黑体"));break;
                    case "微软雅黑":
                        fontCb.setAction(new StyledEditorKit.FontFamilyAction("微软雅黑", "微软雅黑"));break;
                    case "宋体":
                        fontCb.setAction(new StyledEditorKit.FontFamilyAction("宋体", "宋体"));break;
                }
            }
        });

        /*字体大小选择下拉框*/
        JLabel fontSizeLabel = new JLabel("字号");
        JComboBox fontSizeCb = new JComboBox();
        fontSizeCb.setPreferredSize(new Dimension(70, 35));
        fontSizeCb.setModel(new DefaultComboBoxModel(new String[] {"6","8","10","12","14","16","20","24","32","36","48","72"}));
        fontSizeCb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Object sel = e.getItem();
                fontSizeCb.setAction(new StyledEditorKit.FontSizeAction(sel
                        .toString(), Integer.parseInt(sel.toString())));
            }
        });

        /*字体颜色选择下拉框*/
        JLabel fontColorLabel = new JLabel("颜色");
        JComboBox fontColorCb = new JComboBox();
        fontColorCb.setPreferredSize(new Dimension(100, 35));
        fontColorCb.setModel(new DefaultComboBoxModel(new String[]
                 {"黑色","灰色","红色","橘黄","明黄","绿色","深蓝","浅蓝","粉色","紫色"}));
        fontColorCb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Object selectItem = e.getItem();
                System.out.println(selectItem.toString());
                //字体设置
                switch (selectItem.toString())
                {
                    case "黑色": fontColorCb.setAction(new StyledEditorKit.ForegroundAction("黑色",Color.black));break;
                    case "灰色": fontColorCb.setAction(new StyledEditorKit.ForegroundAction("灰色",Color.gray));break;
                    case "红色": fontColorCb.setAction(new StyledEditorKit.ForegroundAction("红色",Color.red));break;
                    case "橘黄": fontColorCb.setAction(new StyledEditorKit.ForegroundAction("橘红",Color.orange));break;
                    case "明黄": fontColorCb.setAction(new StyledEditorKit.ForegroundAction("明黄",Color.yellow));break;
                    case "绿色": fontColorCb.setAction(new StyledEditorKit.ForegroundAction("绿色",Color.GREEN));break;
                    case "深蓝": fontColorCb.setAction(new StyledEditorKit.ForegroundAction("深蓝",Color.blue));break;
                    case "浅蓝": fontColorCb.setAction(new StyledEditorKit.ForegroundAction("深蓝",Color.cyan));break;
                    case "粉色": fontColorCb.setAction(new StyledEditorKit.ForegroundAction("粉色",Color.pink));break;
                    case "紫色": fontColorCb.setAction(new StyledEditorKit.ForegroundAction("紫色",Color.magenta));break;

                }
            }
        });


        /*其余功能按钮*/
        JButton boldButton = new JButton(boldAction);    //创建“加粗”按钮，添加加粗侦听
        boldButton.setIcon(new ImageIcon("src/com/Uatti/res/icon/加粗.png"));
        JButton underlineButton = new JButton(underlineAction); //创建“下划线”按钮，添加下划线侦听
        underlineButton.setIcon(new ImageIcon("src/com/Uatti/res/icon/下划线.png"));
        JButton italicButton = new JButton(italicAction);   //创建“斜体”按钮，添加斜体侦听
        italicButton.setIcon(new ImageIcon("src/com/Uatti/res/icon/倾斜.png"));
        JButton strikeThroughButton = new JButton(new Write.StrikeThroughAction());//下划线按钮
        strikeThroughButton.setIcon(new ImageIcon("src/com/Uatti/res/icon/删除线.png"));
        JButton leftAlignButton = new JButton(new StyledEditorKit.AlignmentAction("Left Align",StyleConstants.ALIGN_LEFT));
        leftAlignButton.setIcon(new ImageIcon("src/com/Uatti/res/icon/左对齐.png"));
        JButton centerButton = new JButton(new StyledEditorKit.AlignmentAction("Center",StyleConstants.ALIGN_CENTER));
        centerButton.setIcon(new ImageIcon("src/com/Uatti/res/icon/中对齐.png"));
        JButton rightAlignButton = new JButton(new StyledEditorKit.AlignmentAction ("Right Align",StyleConstants.ALIGN_RIGHT));
        rightAlignButton.setIcon(new ImageIcon("src/com/Uatti/res/icon/右对齐.png"));
        JButton unDoButton = new JButton(undoAction);
        JButton reDoButton = new JButton(redoAction);
        JButton backButton = new JButton("返回");
        backButton.setFont(new Font("微软雅黑",0 ,14));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(Write.this, "是否返回主界面？") == JOptionPane.YES_OPTION){
                    Write.this.dispose();
                    try {
                        new MainGUI(currentUser);
                    } catch (FontFormatException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        //按钮文字设置
        boldButton.setText("");
        underlineButton.setText("");
        italicButton.setText("");
        strikeThroughButton.setText("");
        leftAlignButton.setText("");
        centerButton.setText("");
        rightAlignButton.setText("");
        unDoButton.setText("?");
        reDoButton.setText("?");

        //创建文章标题输入框
        textTitle = new JTextField();
        textTitle.setPreferredSize(new Dimension(1100,45));

        //按钮加入pane
        editorControlPanel.add(saveBtn);
        editorControlPanel.add(submitBtn);
        editorControlPanel.add(fontLabel);
        editorControlPanel.add(fontCb);
        editorControlPanel.add(fontSizeLabel);
        editorControlPanel.add(fontSizeCb);
        editorControlPanel.add(fontColorLabel);
        editorControlPanel.add(fontColorCb);
        editorControlPanel.add(boldButton);
        editorControlPanel.add(underlineButton);
        editorControlPanel.add(italicButton);
        editorControlPanel.add(strikeThroughButton);
        editorControlPanel.add(unDoButton);
        editorControlPanel.add(reDoButton);

        JPanel alignPanel = new JPanel();
        alignPanel.setLayout(new FlowLayout());
        editorControlPanel.add(leftAlignButton);
        editorControlPanel.add(centerButton);
        editorControlPanel.add(rightAlignButton);
        editorControlPanel.add(unDoButton);
        editorControlPanel.add(reDoButton);
        editorControlPanel.add(backButton);

        JPanel titlePanel = new JPanel();
        textTitle.setLayout(new BorderLayout());
        JLabel jLabel1 = new JLabel("标题");
        titlePanel.add(jLabel1,BorderLayout.WEST);
        titlePanel.add(textTitle,BorderLayout.WEST);

        document.addUndoableEditListener(undoHandler);
        resetUndoManager();

        textPane = new JTextPane(document);
        textPane.setContentType("text/html");
        JScrollPane scrollPane = new JScrollPane(textPane);

        JPanel textAndButtonPanel = new JPanel();

        textAndButtonPanel.setLayout(new BorderLayout());
        textAndButtonPanel.add(scrollPane,BorderLayout.NORTH);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension scrollPaneSize = new Dimension(5*screenSize.width/8,5*screenSize.height/8);
        scrollPane.setPreferredSize(scrollPaneSize);

        //创建工具栏面板，并设置面板布局管理器，添加子面板
        JPanel toolPanel = new JPanel();
        toolPanel.setLayout(new BorderLayout());
        toolPanel.add(editorControlPanel, BorderLayout.WEST);
        toolPanel.add(titlePanel, BorderLayout.SOUTH);

        //向主窗体添加菜单栏
        getContentPane().add(menuBar, BorderLayout.NORTH);
        //向主窗体添加工具栏
        getContentPane().add(toolPanel, BorderLayout.CENTER);
        //向主窗体添加滚动面板
        getContentPane().add(textAndButtonPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        startNewDocument();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        String actionCommand = ae.getActionCommand();
        if (actionCommand.compareTo("新建") == 0)
        {
            startNewDocument();
        }
        else if (actionCommand.compareTo("打开") == 0)
        {
            openDocument();
        }
        else if (actionCommand.compareTo("保存") == 0)
        {
            saveDocument();
        }
        else if (actionCommand.compareTo("另存为") == 0)
        {
            saveDocumentAs();
        }
        else if (actionCommand.compareTo("退出") == 0)
        {
            exit();
        }
        else if (actionCommand.compareTo("清空") == 0)
        {
            clear();
        }
        else if (actionCommand.compareTo("清空所有") == 0)
        {
            selectAll();
        }
        else if (actionCommand.compareTo("帮助") == 0)
        {
            help();
        }
        else if (actionCommand.compareTo("快捷键") == 0)
        {
            showShortcuts();
        }
        else if (actionCommand.compareTo("关于作者") == 0)
        {
            aboutYelihu();
        }
    }

    public void submitDocument(String title,String content) throws Exception {
        blogService  = new BlogServiceImp();
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String time = dateFormat.format(date);

        boolean statue;
        if(textPane.getText().equals("")||textPane.getText()==null||
                textTitle.getText().equals("")||textTitle.getText()==null){
            JOptionPane.showConfirmDialog(this, "还有内容没有填写呢！");
            return;
        }
        if(this.isFirstEdit==true){
            Blog blog = new Blog(currentUser.getUserName(),time,title,content);
            statue = blogService.insertBlog(blog);
        }else{
            Blog blog = new Blog(this.blog.getbId(),currentUser.getUserName(),time,title,content);
            statue = blogService.updateBlog(blog);
        }
        System.out.println(statue);
        if(statue){
            if(JOptionPane.showConfirmDialog(this, "上传成功！是否继续创作？") == JOptionPane.YES_OPTION){
                this.dispose();
                new Write(currentUser);
            }else{
                this.dispose();
                new MainGUI(currentUser);
            }
        }
    }
    public void startNewDocument()
    {
        Document oldDoc = textPane.getDocument();
        if(oldDoc != null){
            oldDoc.removeUndoableEditListener(undoHandler);

        }
        HTMLEditorKit editorKit = new HTMLEditorKit();
        document = (HTMLDocument)editorKit.createDefaultDocument();
        textPane.setDocument(document);
        currentFile = null;
        setTitle("开始你的创作");
        textPane.getDocument().addUndoableEditListener(undoHandler);
        resetUndoManager();
    }

    public void openDocument()
    {
        try
        {
            File current = new File(".");
            JFileChooser chooser = new JFileChooser(current);
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

            //使用过滤器，获取所有html和htm格式的文件
            chooser.setFileFilter(new HTMLFileFilter());

            int approval = chooser.showSaveDialog(this);

            if (approval == JFileChooser.APPROVE_OPTION)
            {
                currentFile = chooser.getSelectedFile();
                setTitle(currentFile.getName());
                FileReader fr = new FileReader(currentFile);
                Document oldDoc = textPane.getDocument();
                if(oldDoc != null){
                    oldDoc.removeUndoableEditListener(undoHandler);
                }
                HTMLEditorKit editorKit = new HTMLEditorKit();
                document = (HTMLDocument)editorKit.createDefaultDocument();
                editorKit.read(fr,document,0);
                document.addUndoableEditListener(undoHandler);
                textPane.setDocument(document);
                resetUndoManager();
            }
        }
        catch(BadLocationException ble)
        {
            System.err.println("BadLocationException: " + ble.getMessage());
        }
        catch(FileNotFoundException fnfe)
        {
            System.err.println("FileNotFoundException: " + fnfe.getMessage());
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    public void saveDocument()
    {
        if (currentFile != null)
        {
            try
            {
                //FileWriter对象传入当前文件
                FileWriter fw = new FileWriter(currentFile);
                //获取pane上面的全部文字
                fw.write(textPane.getText());
                //关闭
                fw.close();
            }
            catch(FileNotFoundException fnfe)
            {
                System.err.println("FileNotFoundException: " + fnfe.getMessage());
            }
            catch(IOException ioe)
            {
                System.err.println("IOException: " + ioe.getMessage());
            }
        }
        else
        {
            //保存文档
            saveDocumentAs();
        }
    }

    public void saveDocumentAs()
    {
        try
        {
            //创建新的文件对象
            File current = new File(".");
            //java封装文件选择对象，传入创建的文件
            JFileChooser chooser = new JFileChooser(current);
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.setFileFilter(new HTMLFileFilter());
            int approval = chooser.showSaveDialog(this);
            if (approval == JFileChooser.APPROVE_OPTION)
            {
                File newFile = chooser.getSelectedFile();
                //如果文件已存在
                if (newFile.exists())
                {
                    String message = newFile.getAbsolutePath() + "文件已经存在. /n" + "是否想要覆盖原来文件?";
                    //选择yes
                    if (JOptionPane.showConfirmDialog(this, message) == JOptionPane.YES_OPTION)
                    {
                        currentFile = newFile;
                        setTitle(currentFile.getName());
                        //覆盖写入
                        FileWriter fw = new FileWriter(currentFile);
                        fw.write(textPane.getText());
                        fw.close();
                    }
                }
                else
                {
                    currentFile = new File(newFile.getAbsolutePath());
                    setTitle(currentFile.getName());
                    FileWriter fw = new FileWriter(currentFile);
                    fw.write(textPane.getText());
                    fw.close();
                }
            }
        }
        catch(FileNotFoundException fnfe)
        {
            System.err.println("FileNotFoundException: " + fnfe.getMessage());
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    public void exit()
    {
        String exitMessage = "确定退出吗？";
        if (JOptionPane.showConfirmDialog(this, exitMessage) == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }

    /**调用startNewDocument()方法，清除文本，开始一个新文档*/
    public void clear()
    {
        startNewDocument();
    }

    /**调用JTextPane的全选方法*/
    public void selectAll()
    {
        textPane.selectAll();
    }

    /**利用消息框显示帮助信息*/
    public void help()
    {
        JOptionPane.showMessageDialog(this,"联系作者获取帮助！邮箱：87160265@qq.com");
    }

    /**利用消息框显示快捷键*/
    public void showShortcuts()
    {
        String shortcuts ="E";
        JOptionPane.showMessageDialog(this,shortcuts);
    }

    public void aboutYelihu()
    {
        JOptionPane.showMessageDialog(this,"yelihu/87160265@qq.com/17616154090",
                "hello！",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("quantumhyperspace.gif"));
    }

    /**内部类：自定义继承WindowListener的侦听器FrameListener*/
    class FrameListener extends WindowAdapter
    {
        /**处理点击窗体关闭按钮事件，实现程序的关闭停止*/
        @Override
        public void windowClosing(WindowEvent we)
        {
            exit();
        }
    }


    class StrikeThroughAction extends StyledEditorKit.StyledTextAction
    {
        public StrikeThroughAction()
        {
            super(StyleConstants.StrikeThrough.toString());
        }

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            JEditorPane editor = getEditor(ae);
            if (editor != null)
            {
                StyledEditorKit kit = getStyledEditorKit(editor);
                MutableAttributeSet attr = kit.getInputAttributes();
                boolean strikeThrough = (StyleConstants.isStrikeThrough(attr)) ? false : true;
                SimpleAttributeSet sas = new SimpleAttributeSet();
                StyleConstants.setStrikeThrough(sas, strikeThrough);
                setCharacterAttributes(editor, sas, false);
            }
        }
    }

    /**
     * HTML文件过滤器
     * 获取所有htm和html后缀的文件
     */
    class HTMLFileFilter extends javax.swing.filechooser.FileFilter
    {
        @Override
        public boolean accept(File f)
        {
            return ((f.isDirectory()) ||(f.getName().toLowerCase().indexOf(".htm") > 0));
        }

        @Override
        public String getDescription()
        {
            return "html";
        }
    }

    protected void resetUndoManager()
    {
        undo.discardAllEdits();
        undoAction.update();
        redoAction.update();
    }

    class UndoHandler implements UndoableEditListener
    {
        @Override
        public void undoableEditHappened(UndoableEditEvent e)
        {
            undo.addEdit(e.getEdit());
            undoAction.update();
            redoAction.update();
        }
    }

    class UndoAction extends AbstractAction
    {
        public UndoAction()
        {
            super("?");
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                undo.undo();
            }
            catch (CannotUndoException ex)
            {
                System.out.println("Unable to undo: " + ex);
                ex.printStackTrace();
            }
            update();
            redoAction.update();
        }

        protected void update()
        {
            if(undo.canUndo())
            {
                setEnabled(true);
                putValue(Action.NAME, undo.getUndoPresentationName());
            }
            else
            {
                setEnabled(false);
                putValue(Action.NAME, "?");
            }
        }
    }

    class RedoAction extends AbstractAction
    {

        public RedoAction()
        {
            super("?");
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                undo.redo();
            }
            catch (CannotRedoException ex)
            {
                System.err.println("Unable to redo: " + ex);
                ex.printStackTrace();
            }
            update();
            undoAction.update();
        }

        protected void update()
        {
            if(undo.canRedo())
            {
                setEnabled(true);
                putValue(Action.NAME, undo.getRedoPresentationName());
            }
            else
            {
                setEnabled(false);
                putValue(Action.NAME, "?");
            }
        }
    }
}
