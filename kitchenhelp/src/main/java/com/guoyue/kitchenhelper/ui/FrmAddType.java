package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAddType extends JFrame implements ActionListener {

    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();

    private JButton btnConf = new JButton("确认");
    private JButton btnCanc = new JButton("取消");

    private JLabel labTypeName = new JLabel("类别名称：");
    private JLabel labTypeDesc = new JLabel("类别描述：");

    private JTextField edtTypeName = new JTextField(20);
    private JTextField edtTypeDesc = new JTextField(20);


    public FrmAddType(){
        this.setSize(380,180);

        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

        toolBar.add(btnConf);
        toolBar.add(btnCanc);

        this.add(toolBar, BorderLayout.SOUTH);

        workPane.add(labTypeName);
        workPane.add(edtTypeName);

        workPane.add(labTypeDesc);
        workPane.add(edtTypeDesc);

        this.add(workPane, BorderLayout.CENTER);

        btnCanc.addActionListener(this);
        btnConf.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnCanc){
            this.setVisible(false);
        }else if(e.getSource() == btnConf){
            KitchenHelperUtil.adminManager.addFoodType(edtTypeName.getText(), edtTypeDesc.getText());
            this.setVisible(false);
        }
    }
}
