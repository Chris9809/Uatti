package com.Uatti.utils;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class MyJComboBox {

    String [] fontsize={"12","14","16","18","20","22","24","26","28"};
    String defaultMessage="请选择或直接输入文字大小";
    public MyJComboBox (){
        JFrame f=new JFrame();
        Container contentpane=f.getContentPane();

        JComboBox combo=new JComboBox<>(fontsize);
        combo.setBorder(BorderFactory.createTitledBorder("请选择您想要的文字大小"));
        combo.setEditable(true);
        ComboBoxEditor editor=combo.getEditor();
        combo.configureEditor(editor, defaultMessage);//初始化显示项目

        contentpane.add(combo);
        f.pack();
        f.show();
        f.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);
            }

        });
    }
    public static void main(String[] args) {
        new MyJComboBox ();
    }
}