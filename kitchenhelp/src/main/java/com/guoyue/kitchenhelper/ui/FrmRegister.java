package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.domain.user.UserInfo;
import com.guoyue.kitchenhelper.util.BaseException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;

public class FrmRegister extends JDialog implements ActionListener, FocusListener {
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("注册");
    private JButton btnCancel = new JButton("取消");

    private JLabel labelUser = new JLabel("用户*：");
    private JLabel inputUser = new JLabel("                     请输入用户名                                               ");
    private JLabel labelPwd = new JLabel("密码*：");
    private JLabel inputPwd = new JLabel("                     请输入密码                                               ");
    private JLabel labelPwd2 = new JLabel("确认密码*：");
    private JLabel inputPwd2 = new JLabel("                     请确认密码                                               ");
    private JLabel confPwd = new JLabel("                     两次输入的密码不一致                                               ");
    private JLabel lableSex = new JLabel("性别：              ");
    private JRadioButton male = new JRadioButton("男         ");
    private JRadioButton female = new JRadioButton("女       ");
    private JLabel labelPhone = new JLabel("手机号码：");
    private JLabel email = new JLabel("邮箱：");
    private JLabel city = new JLabel("所在城市：");
    private JTextField edtUserName = new JTextField(17);
    private JPasswordField edtPwd = new JPasswordField(17);
    private JPasswordField edtPwd2 = new JPasswordField(15);
    private JTextField edtPhone = new JTextField(16);
    private JTextField edtEmail = new JTextField(18);
    private JTextField edtCity = new JTextField(16);

    private ButtonGroup buttonGroup = new ButtonGroup();
    /**
     * 数据库数据域
     */
    private String username = null;
    private String pwd1 = null;
    private String pwd2 = null;
    private String dbSex = null;
    private String dbPhone = null;
    private String dbEmail = null;
    private String dbCity =null;

    public FrmRegister(Dialog f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(this.btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelUser);
        workPane.add(edtUserName);
        workPane.add(inputUser);
        inputUser.setForeground(Color.red);
        inputUser.setVisible(false);
        workPane.add(labelPwd);
        workPane.add(edtPwd);
        workPane.add(inputPwd);
        inputPwd.setForeground(Color.red);
        inputPwd.setVisible(false);
        workPane.add(labelPwd2);
        workPane.add(edtPwd2);
        workPane.add(inputPwd2);
        workPane.add(confPwd);
        workPane.add(lableSex);
        workPane.add(female);
        workPane.add(male);

        male.setActionCommand("男");
        female.setActionCommand("女");

        buttonGroup.add(female);
        buttonGroup.add(male);

        inputPwd2.setForeground(Color.red);
        confPwd.setForeground(Color.red);
        inputPwd2.setVisible(false);
        confPwd.setVisible(false);
        workPane.add(labelPhone);
        workPane.add(edtPhone);
        workPane.add(email);
        workPane.add(edtEmail);
        workPane.add(city);
        workPane.add(edtCity);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(280, 270);
        this.btnCancel.addActionListener(this);
        this.btnOk.addActionListener(this);
        edtUserName.addFocusListener(this);
        edtPwd.addFocusListener(this);
        edtPwd2.addFocusListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btnCancel)
            this.setVisible(false);
        else if(e.getSource()==this.btnOk){
            try{
                dbPhone = labelPhone.getText();
                dbEmail = email.getText();
                dbCity = city.getText();
                dbSex = this.buttonGroup.getSelection().getActionCommand();
                UserInfo user= KitchenHelperUtil.userManager.reg(username,pwd1, dbSex, dbPhone, dbEmail, dbCity);
                this.setVisible(false);
            }catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    public void focusGained(FocusEvent e) {

    }

    public void focusLost(FocusEvent e) {
        username = this.edtUserName.getText();
        pwd1 = new String(this.edtPwd.getPassword());
        pwd2 = new String(this.edtPwd2.getPassword());
        JComponent component = (JComponent)e.getSource();
        if(component != inputUser){
            if(username.equals("")){
                System.out.println(1);
                inputUser.setVisible(true);
            }else{
                System.out.println(2);
                inputUser.setVisible(false);
            }
        }

        if(component != inputPwd && component != edtUserName){
            if(pwd1.equals("")){
                inputPwd.setVisible(true);
            }else{
                inputPwd.setVisible(false);
            }
        }

        if(component != inputPwd2 && component != edtUserName && component != edtPwd){
            if(pwd2.equals("")){
                inputPwd2.setVisible(true);
            }else{
                inputPwd2.setVisible(false);
                if(!pwd1.equals(pwd2)){
                    confPwd.setVisible(true);
                }else{
                    confPwd.setVisible(false);
                }
            }
        }
    }
}

