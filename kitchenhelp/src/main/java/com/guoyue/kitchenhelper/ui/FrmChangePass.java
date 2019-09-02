package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.domain.user.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmChangePass extends JFrame implements ActionListener {

    JPanel toolBar = new JPanel();

    JButton btConf = new JButton("确认");
    JButton btExit = new JButton("取消");

    JLabel labOldPass = new JLabel("旧密码");
    JLabel labNewPass = new JLabel("新密码");
    JLabel labConfPass = new JLabel("确认密码");

    JPasswordField pfOldPass = new JPasswordField(20);
    JPasswordField pfNewPass = new JPasswordField(20);
    JPasswordField pfConfPass = new JPasswordField(18);

    JPanel workPane;

    public FrmChangePass(){
        this.setVisible(true);
        this.setSize(320, 200);

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

        workPane.add(labOldPass);
        workPane.add(pfOldPass);
        workPane.add(labNewPass);
        workPane.add(pfNewPass);
        workPane.add(labConfPass);
        workPane.add(pfConfPass);

        this.add(workPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btConf){
            try{
                KitchenHelperUtil.userManager.changepwd(UserInfo.currentLoginUser,new String (pfOldPass.getPassword()),new String (pfNewPass.getPassword()),new String (pfConfPass.getPassword()));
            }catch (Exception e1){
                throw new RuntimeException();
            }
        }else if(e.getSource() == btExit){
            this.setVisible(false);
        }
    }
}
