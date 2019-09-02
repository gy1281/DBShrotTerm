package com.guoyue.kitchenhelper.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;

public class FrmMain extends JFrame implements ActionListener {
    private FrmLogin dlgLogin;

    public FrmMain(){
        dlgLogin=new FrmLogin(this,"登陆",true);
        dlgLogin.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

    }
}
