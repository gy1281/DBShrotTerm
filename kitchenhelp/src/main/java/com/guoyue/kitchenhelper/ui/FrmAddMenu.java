package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.domain.user.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmAddMenu extends JFrame implements ActionListener {

    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();

    private JButton btnConf = new JButton("确认");
    private JButton btnCanc = new JButton("取消");

    private JLabel labelMenuName = new JLabel("菜谱名称：");
    private JLabel labelMenuDesc = new JLabel("菜谱标签：");

    private JTextField edtMenuName = new JTextField(20);
    private JTextField edtMenuDesc = new JTextField(20);

    public FrmAddMenu(){
        this.setSize(320,200);

        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

        toolBar.add(btnConf);
        toolBar.add(btnCanc);

        this.add(toolBar, BorderLayout.SOUTH);

        workPane.add(labelMenuName);
        workPane.add(edtMenuName);

        workPane.add(labelMenuDesc);
        workPane.add(edtMenuDesc);

        this.add(workPane, BorderLayout.CENTER);

        btnCanc.addActionListener(this);
        btnConf.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnConf){
            KitchenHelperUtil.menuManager.saveMenu(edtMenuName.getText(), edtMenuDesc.getText(), UserInfo.currentLoginUser);
            this.setVisible(false);
        }else if(e.getSource() == btnCanc){
            this.setVisible(false);
        }
    }
}
