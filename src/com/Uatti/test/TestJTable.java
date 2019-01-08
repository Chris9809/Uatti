package com.Uatti.test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.ViewFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Page Description:
 * User: Yelihu
 * Date: 2018-11-17
 * Time: 上午 10:15
 * Function:
 */
public class TestJTable extends JFrame {
    DefaultTableModel model;
    JTable table;

    public TestJTable() {
        super("用户信息");
        Container c = this.getContentPane();
        c.setLayout(new FlowLayout());
        JButton[] jb = {
                new JButton("添加行"),
                new JButton("添加列"),
                new JButton("删除行"),
                new JButton("删除列"),
        };
        for(int i=0;i<4;i++) { c.add(jb[i]); }

        model = new DefaultTableModel();
        table = new JTable(model);

        table.setPreferredScrollableViewportSize(new Dimension(360,160));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane sPane = new JScrollPane(table);

        c.add(sPane);


        jb[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jb[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jb[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jb[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public void addColumn(){
        int cNum = model.getColumnCount();
        int rNum = model.getRowCount();

        String s = "列"+(cNum+1);

        int c = table.getSelectedColumn();

        System.out.println("当前列号为"+c);

        if(cNum==0||rNum==0) {
            model.addColumn(s);
        }
        c++;
        Vector<String> vs = getColumNames();
        vs.add(c,s);
        Vector data  = model.getDataVector();
        for (int i = 0;i<data.size();i++){
            Vector e = (Vector) data.get(i);
            e.add(c,new String(""));
        }
        model.setDataVector(data,vs);
    }/*end*/

    public void addRow(){
        int cNum = model.getColumnCount();
        if(cNum==0) {
            addColumn();
        }
        int rNum = model.getRowCount();
        int r = getRowCurrent();
        System.out.println("当前行号为"+r);
        model.insertRow(r,(Vector)null);
    }

    public void deleteColum(){
        int cNum = model.getColumnCount();
        if(cNum==0){
            return;
        }
        int c = table.getSelectedColumn();

        if(c<=0){
            c=0;
        }
        System.out.println("当前列号为"+c);
        Vector<String> vs = getColumNames();
        vs.remove(c);
        Vector data = model.getDataVector();
        for (int i = 0;i<data.size();i++){
            Vector e = (Vector) data.get(i);
            e.remove(c);
        }
        model.setDataVector(data,vs);
    }

    public  void deleteRow(){
        int rNum = model.getRowCount();
        if(rNum>0){
            int rEdit = getRowCurrent();
            model.removeRow(rEdit);
        }
    }

    public Vector<String> getColumNames(){
        Vector<String> vs = new Vector<>();
        int cNum = model.getColumnCount();
        for (int i = 0;i<cNum;i++){
            vs.add(model.getColumnName(i));
        }
        return vs;
    }
    public int getRowCurrent(){
        int r = table.getSelectedRow();
        if(r<0){
            r=0;
        }
        return r;
    }
    public static void main(String[] args) {
        new TestJTable();
    }
}
