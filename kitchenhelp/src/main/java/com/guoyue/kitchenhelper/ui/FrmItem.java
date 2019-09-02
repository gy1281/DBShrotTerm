package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.domain.menu.MenuInfo;
import com.guoyue.kitchenhelper.domain.user.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class FrmItem extends JFrame implements ActionListener {

    private JPanel toolBar = new JPanel();

    private JButton foodSel = new JButton("购食材");
    private JButton selfMenu = new JButton("成大厨");
    private JButton buyCar = new JButton("购物车");

    private JPanel statusBar = new JPanel();

    JButton changePass = new JButton("修改密码");
    JButton changeUsername = new JButton("修改用户名");

    JScrollPane jScrollPane;

    JLabel label;

    JPanel imgArea;

    List<MenuInfo> menuInfos;

    public FrmItem(){
        this.setVisible(true);
        toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolBar.add(foodSel);
        toolBar.add(selfMenu);
        toolBar.add(buyCar);
        this.getContentPane().add(toolBar, BorderLayout.NORTH);
        this.setSize(400, 600);
        // 屏幕居中显示
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        foodSel.addActionListener(this);
        selfMenu.addActionListener(this);
        buyCar.addActionListener(this);

        imgArea = new JPanel();

        imgArea.setLayout(new BoxLayout(imgArea,  BoxLayout.Y_AXIS));

        jScrollPane = new JScrollPane(imgArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(jScrollPane);

        menuInfos = KitchenHelperUtil.menuManager.findHotestMenu();
        JLabel img1 = new JLabel(new ImageIcon("/Users/guoqs/Desktop/st/kitchenhelp/src/img/hdxr4.png"));
        JLabel img2 = new JLabel(new ImageIcon("/Users/guoqs/Desktop/st/kitchenhelp/src/img/nnbb4.png"));
        JLabel title = new JLabel("今日热门");
        title.setFont(new Font("宋体",Font.BOLD, 16));
        imgArea.add(title);
        imgArea.add(new JLabel(menuInfos.get(0).getMenuName()));
        imgArea.add(img1);
        imgArea.add(new JLabel(menuInfos.get(1).getMenuName()));
        imgArea.add(img2);

        //状态栏
        statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        label=new JLabel("尊敬的用户" + UserInfo.currentLoginUser.getUserName() + "您好!");//修改成   您好！+登陆用户名

        statusBar.add(label);
        statusBar.add(changeUsername);
        statusBar.add(changePass);

        changePass.addActionListener(this);
        changeUsername.addActionListener(this);

        this.getContentPane().add(statusBar,BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.foodSel){
            System.out.println("进入食材选择");
            this.setVisible(false);
            FrmFoodSel foodSel = new FrmFoodSel();
            foodSel.setVisible(true);
        }else if(e.getSource() == this.buyCar){
            System.out.println("进入购物车");
            this.setVisible(false);
            FrmBuyCar frmBuyCar = new FrmBuyCar();
            frmBuyCar.setVisible(true);
        }else if(e.getSource() == this.selfMenu){
            System.out.println("进入菜谱主界面");
            this.setVisible(false);
            FrmMenuMain frmMenuMain = new FrmMenuMain();
            frmMenuMain.setVisible(true);
        }else if(e.getSource() == changePass){
            FrmChangePass frmChangePass = new FrmChangePass();
            frmChangePass.setVisible(true);
        }else if(e.getSource() == changeUsername){
            FrmChangeUsername frmChangeUsername = new FrmChangeUsername();
            label.setText("尊敬的用户" + UserInfo.currentLoginUser.getUserName() + "您好!");
            frmChangeUsername.setVisible(true);
        }
    }
}

