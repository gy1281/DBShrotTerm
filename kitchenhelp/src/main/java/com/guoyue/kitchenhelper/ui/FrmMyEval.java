package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.domain.user.UserInfo;
import com.guoyue.kitchenhelper.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmMyEval extends JFrame implements ActionListener {

    JPanel workPane = new JPanel();
    JPanel toolBar = new JPanel();

    JButton butSend = new JButton("发送");
    JButton butEmpty = new JButton("清空");

    JTextField menuScore = new JTextField(5);
    JTextArea menuDesc = new JTextArea(20, 25);

    JLabel labScore = new JLabel("评分");

    public FrmMyEval(){
        this.setSize(300, 450);

        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        workPane.add(menuDesc);

        butEmpty.addActionListener(this);
        butSend.addActionListener(this);

        toolBar.add(butSend);
        toolBar.add(butEmpty);
        toolBar.add(labScore);
        toolBar.add(menuScore);

        this.add(toolBar, BorderLayout.SOUTH);
        this.add(workPane, BorderLayout.CENTER);

        menuDesc.setLineWrap(true);

        this.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == butSend){
            try{
                KitchenHelperUtil.menuManager.saveMenuEval(FrmMenuMain.curMenu.getMenuId(), UserInfo.currentLoginUser.getUserId(), menuDesc.getText(), Float.parseFloat(menuScore.getText()));
            }catch(Exception e1){
                throw new RuntimeException();
            }
            menuDesc.setText("");
            menuScore.setText("");
            KitchenHelperUtil.menuManager.updateMenuComScore(FrmMenuMain.curMenu);
        }else if(e.getSource() == butEmpty){
            menuDesc.setText("");
            menuScore.setText("");
        }
    }
}
