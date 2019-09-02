package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.domain.menu.MenuEval;
import com.guoyue.kitchenhelper.domain.user.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmMenuEval extends JFrame implements ActionListener {

    JPanel workPane;
    JScrollPane jScrollPane;

    JLabel evalUserLab = new JLabel("评价用户" + "\n\n\n\n\n\n");
    JLabel evalInfoLab = new JLabel("评价信息");

    JPanel name = new JPanel();

    List<MenuEval> menuEvals;

    JPanel butPane;

    JButton butMyEval = new JButton("编辑我的评论");

    public FrmMenuEval(){
        this.setVisible(true);

        this.setSize(300,450);

        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        name.add(evalUserLab);
        name.add(evalInfoLab);

        workPane = new JPanel();
        workPane.setLayout(new BoxLayout(workPane,  BoxLayout.Y_AXIS));
        jScrollPane = new JScrollPane(workPane,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(jScrollPane);

        menuEvals = KitchenHelperUtil.menuManager.findMenuEvalByMenuId(FrmMenuMain.curMenu.getMenuId());
        this.reloadEval();

        butPane = new JPanel();
        butPane.add(butMyEval);

        butMyEval.addActionListener(this);

        this.add(butPane, BorderLayout.SOUTH);
    }

    public void reloadEval(){
        for(MenuEval menuEval : menuEvals){
            workPane.add(new MyInfoJPane(menuEval));
            myUpdateUI();
        }
    }


    private void myUpdateUI() {
        SwingUtilities.updateComponentTreeUI(this);//添加或删除组件后,更新窗口
        JScrollBar jsb = jScrollPane.getVerticalScrollBar();//得到垂直滚动条
        jsb.setValue(jsb.getMaximum());//把滚动条位置设置到最下面
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == butMyEval){
            FrmMyEval frmMyEval = new FrmMyEval();
            frmMyEval.setVisible(true);
        }
    }
}


class MyInfoJPane extends JPanel{

    public MyInfoJPane(MenuEval menuEval){
        JLabel imglab = new JLabel(new ImageIcon("com/guoyue/kitchenhelper/img/logo.png"));
        imglab.setBounds(0,0,10,10);
        JLabel menuEvalLable = new JLabel(menuEval.getEvalCont());
        int userId = menuEval.getUserId();
        UserInfo user = KitchenHelperUtil.userManager.findUserById(userId);
        JLabel menuEvalUserLable = new JLabel(user.getUserName() + ":" + "\n\n\n\n\n\n");
        menuEvalUserLable.setForeground(Color.red);
        add(menuEvalUserLable);
        add(imglab);
        add(menuEvalLable);
    }
}
