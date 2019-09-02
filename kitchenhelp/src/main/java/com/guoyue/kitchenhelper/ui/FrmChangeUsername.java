package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.domain.user.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmChangeUsername extends JFrame implements ActionListener {

    JPanel toolBar = new JPanel();

    JButton btConf = new JButton("确认");
    JButton btExit = new JButton("取消");

    JLabel labNewUsername = new JLabel("新用户名");

    JTextField pfNewUsername = new JTextField(20);


    JPanel workPane;


    public FrmChangeUsername(){
        this.setVisible(true);
        this.setSize(400,150);

        // 屏幕居中显示
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

        toolBar.add(btConf);
        toolBar.add(btExit);

        btConf.addActionListener(this);
        btExit.addActionListener(this);

        this.add(toolBar, BorderLayout.SOUTH);

        workPane = new JPanel();

        workPane.add(labNewUsername);
        workPane.add(pfNewUsername);

        this.add(workPane, BorderLayout.NORTH);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btConf){
            try{
                KitchenHelperUtil.userManager.changeusername(UserInfo.currentLoginUser, pfNewUsername.getText());
                this.setVisible(false);
            }catch (Exception e1){
                throw new RuntimeException();
            }
        }else if(e.getSource() == btExit){
            this.setVisible(false);
        }
    }
}
