package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAddFood extends JFrame implements ActionListener {

    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();

    private JButton btnConf = new JButton("确认");
    private JButton btnCanc = new JButton("取消");

    private JLabel labFoodName = new JLabel("食材名称：");
    private JLabel labFoodDesc = new JLabel("食材描述：");
    private JLabel labFoodPrice = new JLabel("食材价格：");
    private JLabel labFoodNumber = new JLabel("食材数量：");

    private JTextField edtFoodName = new JTextField(20);
    private JTextField edtFoodDesc = new JTextField(20);
    private JTextField edtFoodPrice = new JTextField(20);
    private JTextField edtFoodNumber = new JTextField(20);

    public FrmAddFood(){
        this.setSize(320,200);

        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

        toolBar.add(btnConf);
        toolBar.add(btnCanc);

        this.add(toolBar, BorderLayout.SOUTH);

        workPane.add(labFoodName);
        workPane.add(edtFoodName);

        workPane.add(labFoodDesc);
        workPane.add(edtFoodDesc);

        workPane.add(labFoodPrice);
        workPane.add(edtFoodPrice);

        workPane.add(labFoodNumber);
        workPane.add(edtFoodNumber);

        this.add(workPane, BorderLayout.CENTER);

        btnCanc.addActionListener(this);
        btnConf.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnCanc){
            this.setVisible(false);
        }else if(e.getSource() == btnConf){
            KitchenHelperUtil.adminManager.addFood(edtFoodName.getText(), Float.parseFloat(edtFoodPrice.getText()), Integer.parseInt(edtFoodNumber.getText()), edtFoodDesc.getText(), FrmAdmainFoodManager.curType.getTypeId());
            this.setVisible(false);
        }
    }
}
