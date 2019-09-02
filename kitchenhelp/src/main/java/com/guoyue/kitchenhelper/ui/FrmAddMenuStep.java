package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.domain.user.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAddMenuStep extends JFrame implements ActionListener {


    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();

    private JButton btnConf = new JButton("确认");
    private JButton btnCanc = new JButton("取消");

    private JLabel labelStepDesc = new JLabel("步骤描述：");

    private JTextField edtStepDesc = new JTextField(20);


    public FrmAddMenuStep(){
        this.setSize(320,200);

        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

        toolBar.add(btnConf);
        toolBar.add(btnCanc);

        this.add(toolBar, BorderLayout.SOUTH);

        workPane.add(labelStepDesc);
        workPane.add(edtStepDesc);

        this.add(workPane, BorderLayout.CENTER);

        btnCanc.addActionListener(this);
        btnConf.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnConf){
            KitchenHelperUtil.menuManager.saveStep(FrmMyMenu.curMenu.getMenuId(), edtStepDesc.getText());
            this.setVisible(false);
        }else if(e.getSource() == btnCanc){
            this.setVisible(false);
        }
    }
}
