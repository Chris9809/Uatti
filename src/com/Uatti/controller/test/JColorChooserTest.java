package com.Uatti.controller.test;

import java.awt.*;
import javax.swing.*;

public class JColorChooserTest {
    private JFrame frame = new JFrame("文字展示器");
    // 创建一个标签居中显示文字
    private JLabel label = new JLabel("平凡不平庸", JLabel.CENTER);
    // 选择颜色后不会改变颜色，只有应用后才会
    private JButton choose = new JButton("选择颜色");
    private JButton apply = new JButton("应用颜色");
    // 创建一键换色按钮
    private JButton change = new JButton("一键变色");
    private Color color = Color.BLACK;

    public void init() {
        // 设置展示文字的字体和大小
        label.setFont(new Font("华文行楷", 1, 50));
        frame.add(label);

        JPanel panel = new JPanel();
        // 只有选择颜色后应用按钮才可用
        apply.setEnabled(false);
        // 添加监听器
        choose.addActionListener(e -> {
            try {
                color = JColorChooser.showDialog(frame, "选择字体颜色", color);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            // 应用按钮可用
            apply.setEnabled(true);
        });

        apply.addActionListener(e -> {
            label.setForeground(color);
        });

        change.addActionListener(e -> {
            JColorChooser colorChooser = new JColorChooser();
            JDialog dialog = JColorChooser.createDialog(frame, "请选择颜色", false, colorChooser,
                    e1 -> label.setForeground(colorChooser.getColor()), null);
            dialog.setVisible(true);
        });
        panel.add(choose);
        panel.add(apply);
        panel.add(change);
        frame.add(panel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new JColorChooserTest().init();
    }
}