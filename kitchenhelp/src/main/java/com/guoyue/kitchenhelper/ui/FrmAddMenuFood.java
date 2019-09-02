package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAddMenuFood extends JFrame implements ActionListener {

    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();

    private JButton btnConf = new JButton("确认");
    private JButton btnCanc = new JButton("取消");

    private JLabel labelFoodName = new JLabel("食材名称：");
    private JLabel labelFoodNumber = new JLabel("食材数目：");

    private JTextField edtFoodName = new JTextField(20);
    private JTextField edtFoodNumber = new JTextField(20);


    public FrmAddMenuFood(){
        this.setSize(320,200);

        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

        toolBar.add(btnConf);
        toolBar.add(btnCanc);

        this.add(toolBar, BorderLayout.SOUTH);

        workPane.add(labelFoodName);
        workPane.add(edtFoodName);

        workPane.add(labelFoodNumber);
        workPane.add(edtFoodNumber);

        this.add(workPane, BorderLayout.CENTER);

        btnCanc.addActionListener(this);
        btnConf.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConf){
            try{
                KitchenHelperUtil.menuManager.saveMenuFood(edtFoodName.getText(), FrmMyMenu.curMenu.getMenuId(), Integer.parseInt(edtFoodNumber.getText()));
            }catch(Exception e1){
                throw new RuntimeException();
            }
            this.setVisible(false);
        }else if (e.getSource() == btnCanc){
            this.setVisible(false);
        }
    }
}
