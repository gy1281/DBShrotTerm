package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.domain.menu.MenuFood;
import com.guoyue.kitchenhelper.domain.menu.MenuInfo;
import com.guoyue.kitchenhelper.domain.user.FoodBookform;
import com.guoyue.kitchenhelper.domain.user.UserFood;
import com.guoyue.kitchenhelper.domain.user.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmSendBookForm extends JFrame implements ActionListener {

    JPanel workPane = new JPanel();

    JPanel toolBar = new JPanel();

    JLabel addressLable = new JLabel("配送地址");
    JLabel phoneLable = new JLabel("联系电话");
    JLabel delTimeLable = new JLabel("送达时间");

    JTextField addressText = new JTextField(20);
    JTextField phoneText = new JTextField(20);
    JTextField delTimeText = new JTextField(20);

    JButton confButton = new JButton("确认");
    JButton exitButton = new JButton("取消");

    List<MenuFood> menuFoods ;

    public FrmSendBookForm(){

        try{
            menuFoods = KitchenHelperUtil.menuManager.findMenuFoodByMenuId(FrmMenuMain.curMenu.getMenuId());
        }catch(Exception e){
            throw new RuntimeException();
        }

        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));

        toolBar.add(confButton);
        toolBar.add(exitButton);

        this.getContentPane().add(toolBar, BorderLayout.SOUTH);

        workPane.add(addressLable);
        workPane.add(addressText);

        workPane.add(phoneLable);
        workPane.add(phoneText);

        workPane.add(delTimeLable);
        workPane.add(delTimeText);


        this.getContentPane().add(workPane, BorderLayout.CENTER);

        confButton.addActionListener(this);
        exitButton.addActionListener(this);

        this.setSize(320, 200);

        // 屏幕居中显示
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

        this.add(confButton, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        String address = this.addressText.getText();
        String phone = this.phoneText.getText();
        String delTime = this.delTimeText.getText();
        FoodBookform bookform;
        if(e.getSource() == confButton){
            try{
                bookform = KitchenHelperUtil.bookFormManager.saveBookForm(UserInfo.currentLoginUser.getUserId(), address, delTime, phone);
            }catch(Exception e1){
                throw new RuntimeException();
            }

            KitchenHelperUtil.bookFormManager.saveBookFormInfoFromMenuFood(menuFoods, bookform.getBfId());

            this.setVisible(false);
        } else if (e.getSource() == exitButton){
            this.setVisible(false);
        }
    }
}
