package com.guoyue.kitchenhelper.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAdmainMain extends JFrame implements ActionListener {


    JPanel toolBar = new JPanel();

    JButton menuManager = new JButton("菜谱管理");
    JButton foodManager = new JButton("食材管理");
    JButton userManager = new JButton("用户管理");

    JButton more = new JButton("其他");

    public FrmAdmainMain()  {

        this.setSize(1200, 700);

        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.setTitle("管理员界面");

        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        toolBar.add(menuManager);
        toolBar.add(foodManager);
        toolBar.add(userManager);
        toolBar.add(more);

        menuManager.addActionListener(this);
        foodManager.addActionListener(this);
        userManager.addActionListener(this);
        more.addActionListener(this);

        this.add(toolBar, BorderLayout.NORTH);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menuManager){
            FrmAdminMenuManager frmAdminMenuManager = new FrmAdminMenuManager();
            frmAdminMenuManager.setVisible(true);
        }else if(e.getSource() == foodManager){
            FrmAdmainFoodManager frmAdmainFoodManager = new FrmAdmainFoodManager();
            frmAdmainFoodManager.setVisible(true);
        }else if(e.getSource() == userManager){
            FrmAdminUserManager frmAdminUserManager = new FrmAdminUserManager();
            frmAdminUserManager.setVisible(true);
        }
    }
}
