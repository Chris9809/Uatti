package com.Uatti.controller;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.undo.*;

/**HTML文件文档编辑器*/
public class TabletGUI extends JFrame implements ActionListener
{
    /*网页文档对象变量*/
    private HTMLDocument document;
    /*创建一个文本编辑板*/
    private JTextPane textPane = new JTextPane();
    private boolean debug = false;
    /*声明一个文件对象变量*/
    private File currentFile;
    /*侦听在当前文档上的编辑器 */
    protected UndoableEditListener undoHandler = new UndoHandler();
    /*添加撤消管理器 */
    protected UndoManager undo = new UndoManager();
    /*添加撤消侦听器*/
    private UndoAction undoAction = new UndoAction();
    /*添加恢复侦听器*/
    private RedoAction redoAction = new RedoAction();
    /*添加剪切侦听器*/
    private Action cutAction = new DefaultEditorKit.CutAction();
    /*添加复制侦听器*/
    private Action copyAction = new DefaultEditorKit.CopyAction();
    /*添加粘贴侦听器*/
    private Action pasteAction = new DefaultEditorKit.PasteAction();
    /*添加加粗侦听器*/
    private Action boldAction = new StyledEditorKit.BoldAction();
    /*添加加下划线侦听器*/
    private Action underlineAction = new StyledEditorKit.UnderlineAction();
    /*添加倾斜侦听器*/
    private Action italicAction = new StyledEditorKit.ItalicAction();
    private Action insertBreakAction = new DefaultEditorKit.InsertBreakAction();
    private HTMLEditorKit.InsertHTMLTextAction unorderedListAction = new HTMLEditorKit.InsertHTMLTextAction("Bullets", "<ul><li> </li></ul>",HTML.Tag.P,HTML.Tag.UL);
    private HTMLEditorKit.InsertHTMLTextAction bulletAction = new HTMLEditorKit.InsertHTMLTextAction("Bullets", "<li> </li>",HTML.Tag.UL,HTML.Tag.LI);

    /** 构造方法*/
    public TabletGUI()
    {
        /** 设置主窗体标题*/
        super("有态度博客,记录你的Idea");
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

        //调用初始化方法
        init();
    }
    /**初始化各组件的方法*/
    public void init()
    {
        //调用自定义继承WindowListener的侦听器FrameListener，给主窗体添加WindowListener
        addWindowListener(new FrameListener());
        //给生成全部菜单项目
        JMenuBar menuBar = new JMenuBar();
        getContentPane().add(menuBar, BorderLayout.NORTH);
        JMenu fileMenu = new JMenu("文件");  //文件
        JMenu editMenu = new JMenu("编辑");  //编辑
        JMenu colorMenu = new JMenu("字体颜色");  //颜色
        JMenu fontMenu = new JMenu("字体");  //字体
        JMenu styleMenu = new JMenu("风格");  //样式
        JMenu alignMenu = new JMenu("对齐方式");  //对齐
        JMenu helpMenu = new JMenu("帮助");  //帮助
        //菜单栏添加菜单项目
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(colorMenu);
        menuBar.add(fontMenu);
        menuBar.add(styleMenu);
        menuBar.add(alignMenu);
        menuBar.add(helpMenu);
        /*******************************************/
        //文件菜单项目添加菜单按钮
        JMenuItem newItem = new JMenuItem("新建空白", new ImageIcon("whatsnew-bang.gif"));  //新建
        JMenuItem openItem = new JMenuItem("打开",new ImageIcon("open.gif"));    //打开
        JMenuItem saveItem = new JMenuItem("保存",new ImageIcon("save.gif"));    //保存
        JMenuItem saveAsItem = new JMenuItem("另存为");                                //另存
        JMenuItem exitItem = new JMenuItem("退出",new ImageIcon("exit.gif"));    //退出
        //菜单按钮设置监听事件
        newItem.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        saveAsItem.addActionListener(this);
        exitItem.addActionListener(this);
        //文件菜单项目添加菜单按钮
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.add(exitItem);
        /*******************************************/
        //编辑菜单项目添加菜单按钮
        JMenuItem undoItem = new JMenuItem(undoAction);      //撤消
        JMenuItem redoItem = new JMenuItem(redoAction);      //恢复

        JMenuItem cutItem = new JMenuItem(cutAction);       //剪切
        JMenuItem copyItem = new JMenuItem(copyAction);      //复制
        JMenuItem pasteItem = new JMenuItem(pasteAction);      //粘贴
        JMenuItem clearItem = new JMenuItem("清空");       //清除
        JMenuItem selectAllItem = new JMenuItem("全选");     //全选

        JMenuItem insertBreaKItem = new JMenuItem(insertBreakAction);
        JMenuItem unorderedListItem = new JMenuItem(unorderedListAction);

        JMenuItem bulletItem = new JMenuItem(bulletAction);     //项目符号

        //菜单按钮生成
        cutItem.setText("剪切");
        copyItem.setText("复制");
        pasteItem.setText("粘贴");
        insertBreaKItem.setText("删除一行");
        //添加图片
        cutItem.setIcon(new ImageIcon("cut.gif"));
        copyItem.setIcon(new ImageIcon("copy.gif"));
        pasteItem.setIcon(new ImageIcon("paste.gif"));
        insertBreaKItem.setIcon(new ImageIcon("break.gif"));
        unorderedListItem.setIcon(new ImageIcon("bullets.gif"));
        //菜单按钮添加监听事件
        clearItem.addActionListener(this);
        selectAllItem.addActionListener(this);
        //编辑菜单添加所有编辑按钮
        editMenu.add(undoItem);
        editMenu.add(redoItem);
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.add(clearItem);
        editMenu.add(selectAllItem);
        editMenu.add(insertBreaKItem);
        editMenu.add(unorderedListItem);
        editMenu.add(bulletItem);
        /*******************************************/
        //文件菜单项目添加菜单按钮
        JMenuItem redTextItem = new JMenuItem(new StyledEditorKit.ForegroundAction("红色",Color.red));
        JMenuItem orangeTextItem = new JMenuItem(new StyledEditorKit.ForegroundAction("橘红",Color.orange));
        JMenuItem yellowTextItem = new JMenuItem(new StyledEditorKit.ForegroundAction("明黄",Color.yellow));
        JMenuItem greenTextItem = new JMenuItem(new StyledEditorKit.ForegroundAction("绿色",Color.green));
        JMenuItem blueTextItem = new JMenuItem(new StyledEditorKit.ForegroundAction("深蓝",Color.blue));
        JMenuItem cyanTextItem = new JMenuItem(new StyledEditorKit.ForegroundAction("浅蓝",Color.cyan));
        JMenuItem magentaTextItem = new JMenuItem(new StyledEditorKit.ForegroundAction("紫色",Color.magenta));
        JMenuItem blackTextItem = new JMenuItem(new StyledEditorKit.ForegroundAction("黑色",Color.black));
        //颜色菜单项目添加菜单按钮
        colorMenu.add(blackTextItem);
        colorMenu.add(redTextItem);
        colorMenu.add(orangeTextItem);
        colorMenu.add(yellowTextItem);
        colorMenu.add(greenTextItem);
        colorMenu.add(blueTextItem);
        colorMenu.add(cyanTextItem);
        colorMenu.add(magentaTextItem);
        /*******************************************/
        //字体大小菜单项目添加菜单按钮
        JMenu fontSizeMenu = new JMenu("字体大小");
        fontMenu.add(fontSizeMenu);
        int[] fontSizes = {6, 8,10,12,14, 16, 20,24, 32,36,48,72};
        for (int i = 0; i < fontSizes.length;i++)
        {
//            if (debug)
//                System.out.println(fontSizes[i]);
            JMenuItem nextSizeItem = new JMenuItem(String.valueOf(fontSizes[i]));
            nextSizeItem.setAction(new StyledEditorKit.FontSizeAction(String.valueOf(fontSizes[i]), fontSizes[i]));
            fontSizeMenu.add(nextSizeItem);
        }

        /*******************************************/
        //样式大小菜单项目添加菜单按钮
        JMenuItem boldMenuItem = new JMenuItem(boldAction);
        JMenuItem underlineMenuItem = new JMenuItem(underlineAction);
        JMenuItem italicMenuItem = new JMenuItem(italicAction);

        boldMenuItem.setText("加粗");
        underlineMenuItem.setText("下划线");
        italicMenuItem.setText("斜体");

        /*******************************************/
        //风格菜单项目添加菜单按钮
        styleMenu.add(boldMenuItem);
        styleMenu.add(underlineMenuItem);
        styleMenu.add(italicMenuItem);

        JMenuItem subscriptMenuItem = new JMenuItem(new SubscriptAction());
        JMenuItem superscriptMenuItem = new JMenuItem(new SuperscriptAction());
        JMenuItem strikeThroughMenuItem = new JMenuItem(new StrikeThroughAction());

        subscriptMenuItem.setText("Subscript");
        superscriptMenuItem.setText("Superscript");
        strikeThroughMenuItem.setText("删除线");

        styleMenu.add(subscriptMenuItem);
        styleMenu.add(superscriptMenuItem);
        styleMenu.add(strikeThroughMenuItem);

        JMenuItem leftAlignMenuItem = new JMenuItem(new StyledEditorKit.AlignmentAction("Left Align",StyleConstants.ALIGN_LEFT));
        JMenuItem centerMenuItem = new JMenuItem(new StyledEditorKit.AlignmentAction("Center",StyleConstants.ALIGN_CENTER));
        JMenuItem rightAlignMenuItem = new JMenuItem(new StyledEditorKit.AlignmentAction ("Right Align",StyleConstants.ALIGN_RIGHT));
        /*******************************************/
        //对齐项目添加菜单按钮
        leftAlignMenuItem.setText("左对齐");
        centerMenuItem.setText("居中对齐");
        rightAlignMenuItem.setText("右对齐");

        alignMenu.add(leftAlignMenuItem);
        alignMenu.add(centerMenuItem);
        alignMenu.add(rightAlignMenuItem);
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

        JPanel editorControlPanel = new JPanel();
        editorControlPanel.setLayout(new FlowLayout());
        JComboBox<Integer> fontSize =  new JComboBox<>();
        fontSize.setBounds(199, 117, 35, 22);
        int[] fontSizes4ComboBox = {6, 8,10,12,14, 16, 20,24, 32,36,48,72};
        fontSize.setSelectedItem(fontSizes4ComboBox);
        fontSize.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    String size = (String)fontSize.getSelectedItem();
                    new StyledEditorKit.FontSizeAction(size,Integer.valueOf(size));
                }
            }
        });
        JButton boldButton = new JButton(boldAction);    //创建“加粗”按钮，添加加粗侦听
        JButton underlineButton = new JButton(underlineAction); //创建“下划线”按钮，添加下划线侦听
        JButton italicButton = new JButton(italicAction);   //创建“斜体”按钮，添加斜体侦听
        JButton strikeThroughButton = new JButton(new StrikeThroughAction());//下划线按钮

        JButton leftAlignButton = new JButton(new StyledEditorKit.AlignmentAction("Left Align",StyleConstants.ALIGN_LEFT));
        JButton centerButton = new JButton(new StyledEditorKit.AlignmentAction("Center",StyleConstants.ALIGN_CENTER));
        JButton rightAlignButton = new JButton(new StyledEditorKit.AlignmentAction ("Right Align",StyleConstants.ALIGN_RIGHT));

        boldButton.setText("Ｂ");
        underlineButton.setText("＿");
        italicButton.setText("／");
        strikeThroughButton.setText("删除线");
        leftAlignButton.setText("左对齐");
        centerButton.setText("居中");
        rightAlignButton.setText("右对齐");

        editorControlPanel.add(fontSize);
        editorControlPanel.add(boldButton);
        editorControlPanel.add(underlineButton);
        editorControlPanel.add(italicButton);
        editorControlPanel.add(strikeThroughButton);

        JPanel alignPanel = new JPanel();
        alignPanel.setLayout(new FlowLayout());
        editorControlPanel.add(leftAlignButton);
        editorControlPanel.add(centerButton);
        editorControlPanel.add(rightAlignButton);

        document.addUndoableEditListener(undoHandler);
        resetUndoManager();

        textPane = new JTextPane(document);
        textPane.setContentType("text/html");
        JScrollPane scrollPane = new JScrollPane(textPane);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension scrollPaneSize = new Dimension(5*screenSize.width/8,5*screenSize.height/8);
        scrollPane.setPreferredSize(scrollPaneSize);

        //创建工具栏面板，并设置面板布局管理器，添加子面板
        JPanel toolPanel = new JPanel();
        toolPanel.setLayout(new BorderLayout());
        toolPanel.add(editorControlPanel, BorderLayout.WEST);
//        toolPanel.add(specialPanel, BorderLayout.CENTER);
//        toolPanel.add(alignPanel, BorderLayout.SOUTH);

        //向主窗体添加菜单栏
        getContentPane().add(menuBar, BorderLayout.NORTH);
        //向主窗体添加工具栏
        getContentPane().add(toolPanel, BorderLayout.CENTER);
        //向主窗体添加滚动面板
        getContentPane().add(scrollPane, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        startNewDocument();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        //创建一个类的实例，即创建一个网页编辑器
        TabletGUI editor = new TabletGUI();
    }



    @Override
    public void actionPerformed(ActionEvent ae)
    {
        String actionCommand = ae.getActionCommand();
        if (debug)
        {
            int modifier = ae.getModifiers();
            long when = ae.getWhen();
            String parameter = ae.paramString();
            System.out.println("actionCommand: " + actionCommand);
            System.out.println("modifier: " + modifier);
            System.out.println("when: " + when);
            System.out.println("parameter: " + parameter);
        }
        if (actionCommand.compareTo("New") == 0)
        {
            startNewDocument();
        }
        else if (actionCommand.compareTo("Open") == 0)
        {
            openDocument();
        }
        else if (actionCommand.compareTo("Save") == 0)
        {
            saveDocument();
        }
        else if (actionCommand.compareTo("Save As") == 0)
        {
            saveDocumentAs();
        }
        else if (actionCommand.compareTo("Exit") == 0)
        {
            exit();
        }
        else if (actionCommand.compareTo("Clear") == 0)
        {
            clear();
        }
        else if (actionCommand.compareTo("Select All") == 0)
        {
            selectAll();
        }
        else if (actionCommand.compareTo("帮助") == 0)
        {
            help();
        }
        else if (actionCommand.compareTo("Keyboard Shortcuts") == 0)
        {
            showShortcuts();
        }
        else if (actionCommand.compareTo("About QuantumHyperSpace") == 0)
        {
            aboutQuantumHyperSpace();
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
        setTitle("TabletGUI");
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
                FileWriter fw = new FileWriter(currentFile);
                fw.write(textPane.getText());
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
            saveDocumentAs();
        }
    }

    public void saveDocumentAs()
    {
        try
        {
            File current = new File(".");
            JFileChooser chooser = new JFileChooser(current);
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.setFileFilter(new HTMLFileFilter());
            int approval = chooser.showSaveDialog(this);
            if (approval == JFileChooser.APPROVE_OPTION)
            {
                File newFile = chooser.getSelectedFile();
                if (newFile.exists())
                {
                    String message = newFile.getAbsolutePath() + " already exists. /n" + "Do you want to replace it?";
                    if (JOptionPane.showConfirmDialog(this, message) == JOptionPane.YES_OPTION)
                    {
                        currentFile = newFile;
                        setTitle(currentFile.getName());
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
        String exitMessage = "Are you sure you want to exit?";
        if (JOptionPane.showConfirmDialog(this, exitMessage) == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }

    /**调用startNewDocument()方法，清除当前文本，开始一个新文档*/
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
        JOptionPane.showMessageDialog(this,"DocumentEditor.java/n" + "Author: Charles Bell/n" + "Version: May 25, 2002/n" +
                "http://www.quantumhyperspace.com/n" + "QuantumHyperSpace Programming Services");
    }

    /**利用消息框显示快捷键*/
    public void showShortcuts()
    {
        String shortcuts = "Navigate in | Tab/n" + "Navigate out | Ctrl+Tab/n" + "Navigate out backwards | Shift+Ctrl+Tab/n" +
                "Move up/down a line | Up/Down Arrown/n" + "Move left/right a component or char | Left/Right Arrow/n" +
                "Move up/down one vertical block | PgUp/PgDn/n" + "Move to start/end of line | Home/End/n" +
                "Move to previous/next word | Ctrl+Left/Right Arrow/n" + "Move to start/end of data | Ctrl+Home/End/n" +
                "Move left/right one block | Ctrl+PgUp/PgDn/n" + "Select All | Ctrl+A/n" +
                "Extend selection up one line | Shift+Up Arrow/n" + "Extend selection down one line | Shift+Down Arrow/n" +
                "Extend selection to beginning of line | Shift+Home/n" + "Extend selection to end of line | Shift+End/n" +
                "Extend selection to beginning of data | Ctrl+Shift+Home/n" + "Extend selection to end of data | Ctrl+Shift+End/n" +
                "Extend selection left | Shift+Right Arrow/n" + "Extend selection right | Shift+Right Arrow/n" +
                "Extend selection up one vertical block | Shift+PgUp/n" + "Extend selection down one vertical block | Shift+PgDn/n" +
                "Extend selection left one block | Ctrl+Shift+PgUp/n" + "Extend selection right one block | Ctrl+Shift+PgDn/n" +
                "Extend selection left one word | Ctrl+Shift+Left Arrow/n" + "Extend selection right one word | Ctrl+Shift+Right Arrow/n";
        JOptionPane.showMessageDialog(this,shortcuts);
    }

    public void aboutQuantumHyperSpace()
    {
        JOptionPane.showMessageDialog(this,"QuantumHyperSpace Programming Services/n" + "http://www.quantumhyperspace.com/n" +
                        "email: support@quantumhyperspace.com/n" +  " or /n" + "email: charles@quantumhyperspace.com/n",
                "QuantumHyperSpace",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("quantumhyperspace.gif"));
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

    class SubscriptAction extends StyledEditorKit.StyledTextAction
    {

        public SubscriptAction()
        {
            super(StyleConstants.Subscript.toString());
        }
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            JEditorPane editor = getEditor(ae);
            if (editor != null)
            {
                StyledEditorKit kit = getStyledEditorKit(editor);
                MutableAttributeSet attr = kit.getInputAttributes();
                boolean subscript = (StyleConstants.isSubscript(attr)) ? false : true;
                SimpleAttributeSet sas = new SimpleAttributeSet();
                StyleConstants.setSubscript(sas, subscript);
                setCharacterAttributes(editor, sas, false);
            }
        }
    }

    class SuperscriptAction extends StyledEditorKit.StyledTextAction
    {

        public SuperscriptAction()
        {
            super(StyleConstants.Superscript.toString());
        }
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            JEditorPane editor = getEditor(ae);
            if (editor != null)
            {
                StyledEditorKit kit = getStyledEditorKit(editor);
                MutableAttributeSet attr = kit.getInputAttributes();
                boolean superscript = (StyleConstants.isSuperscript(attr)) ? false : true;
                SimpleAttributeSet sas = new SimpleAttributeSet();
                StyleConstants.setSuperscript(sas, superscript);
                setCharacterAttributes(editor, sas, false);
            }
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
            super("Undo");
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
                putValue(Action.NAME, "Undo");
            }
        }
    }

    class RedoAction extends AbstractAction
    {

        public RedoAction()
        {
            super("Redo");
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
                putValue(Action.NAME, "Redo");
            }
        }
    }
}
