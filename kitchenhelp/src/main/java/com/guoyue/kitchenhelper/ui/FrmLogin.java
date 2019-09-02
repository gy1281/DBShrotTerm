package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.domain.admin.AdminInfo;
import com.guoyue.kitchenhelper.domain.user.UserInfo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class FrmLogin extends JDialog implements ActionListener {

    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnLogin = new JButton("登陆");
    private JButton btnCancel = new JButton("退出");
    private JButton btnRegister = new JButton("用户注册");
    private JRadioButton btnuser = new JRadioButton("用户");
    private JRadioButton btnadmin = new JRadioButton("管理员");

    private JLabel labelUser = new JLabel("用户：");
    private JLabel labelPwd = new JLabel("密码：");
    private JLabel labelCode = new JLabel("验证码：");
    private JTextField edtUserName = new JTextField(20);
    private JPasswordField edtPwd = new JPasswordField(20);
    private JTextField edtCode = new JTextField(19);
    //设置按钮组
    private ButtonGroup buttonGroup = new ButtonGroup();

    private ValidCode code;

    public FrmLogin(Frame f, String s, boolean b) {
        super(f, s, b);

        code = new ValidCode();

        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(this.btnRegister);
        toolBar.add(btnLogin);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);

        workPane.add(labelUser);
        workPane.add(edtUserName);
        workPane.add(labelPwd);
        workPane.add(edtPwd);
        workPane.add(labelCode);
        workPane.add(edtCode);
        workPane.add(code);

        workPane.add(btnadmin);
        workPane.add(btnuser);

        btnuser.setActionCommand("用户");
        btnadmin.setActionCommand("管理员");

        buttonGroup.add(btnuser);
        buttonGroup.add(btnadmin);

        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(320, 200);

        // 屏幕居中显示
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

        btnLogin.addActionListener(this);
        btnCancel.addActionListener(this);
        btnRegister.addActionListener(this);
        btnuser.addActionListener(this);
        btnadmin.addActionListener(this);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() ==this.btnLogin){
            String username = this.edtUserName.getText();
            String pwd = new String(this.edtPwd.getPassword());
            String code = this.edtCode.getText();
            String vcode = this.code.getCode();
            String role = this.buttonGroup.getSelection().getActionCommand();
            try{
                if(role.equals("用户")){
                    //关闭登录页面
                    UserInfo.currentLoginUser = KitchenHelperUtil.userManager.login(username, pwd, code, vcode);
                    FrmItem dgl = new FrmItem();
                    dgl.setVisible(true);
                }
                else if(role.equals("管理员")){
                    //关闭登录页面
                    AdminInfo.currentLoginAdmin = KitchenHelperUtil.adminManager.login(Integer.parseInt(username), pwd, code, vcode);
                    FrmAdmainMain frmAdmainMain = new FrmAdmainMain();
                    frmAdmainMain.setVisible(true);
                }
                this.setVisible(false);
            }catch(Exception e1){
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }

        } else if (e.getSource() == this.btnCancel) {
            System.exit(0);
        } else if(e.getSource()==this.btnRegister){
            FrmRegister dlg=new FrmRegister(this,"注册",true);
            dlg.setVisible(true);
        }
    }
}
