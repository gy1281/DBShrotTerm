package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.domain.menu.MenuFood;
import com.guoyue.kitchenhelper.domain.menu.MenuStep;
import com.guoyue.kitchenhelper.domain.user.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmMenuStep extends JFrame implements ActionListener {

    JButton butEval = new JButton("评论");
    JButton butColl = new JButton("收藏");

    JLabel stepOrderLab = new JLabel("步骤序号" + "\n\n\n\n\n\n");
    JLabel stepDescLab = new JLabel("步骤描述");

    JPanel name = new JPanel();
    JPanel toolBarBut = new JPanel();

    JPanel workPaneStep;

    JScrollPane jScrollPane2;

    List<MenuStep> menuSteps;

    public FrmMenuStep(){
        this.setVisible(true);

        this.setSize(300,450);

        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        Boolean flag = KitchenHelperUtil.menuManager.isMenuEvalColled(FrmMenuMain.curMenu.getMenuId(), UserInfo.currentLoginUser.getUserId());

        if(flag == false){
            butColl.setText("已收藏");
        }
        else if(flag == true){
            butColl.setText("收藏");
        }


        toolBarBut.add(butEval);
        toolBarBut.add(butColl);

        name.add(stepOrderLab);
        name.add(stepDescLab);

        workPaneStep = new JPanel();

        workPaneStep.setLayout(new BoxLayout(workPaneStep,  BoxLayout.Y_AXIS));

        jScrollPane2 = new JScrollPane(workPaneStep,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        this.add(jScrollPane2);

        this.add(toolBarBut, BorderLayout.SOUTH);
        this.add(name, BorderLayout.NORTH);

        butColl.addActionListener(this);
        butEval.addActionListener(this);

        menuSteps = KitchenHelperUtil.menuManager.findMenuStepByMenuId(FrmMenuMain.curMenu.getMenuId());
        System.out.println("menuestep"+menuSteps);
        reloadStep();
    }

    public void reloadStep(){
        for(MenuStep menuStep : menuSteps){
            System.out.println("进入步骤");
            workPaneStep.add(new MyStepJPane(menuStep));
            myUpdateUI();
        }
    }

    private void myUpdateUI() {
        SwingUtilities.updateComponentTreeUI(this);//添加或删除组件后,更新窗口
        JScrollBar jsb = jScrollPane2.getVerticalScrollBar();//得到垂直滚动条
        jsb.setValue(jsb.getMaximum());//把滚动条位置设置到最下面
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == butEval){
            FrmMenuEval frmMenuEval = new FrmMenuEval();
            frmMenuEval.setVisible(true);

        }else if(e.getSource() == butColl){
            System.out.println("传递"+FrmMenuMain.curMenu);
            KitchenHelperUtil.menuManager.updateMenuEvalColl(FrmMenuMain.curMenu.getMenuId(), UserInfo.currentLoginUser.getUserId());
            if(butColl.getText().equals("已收藏"))
                butColl.setText("收藏");
            else
                butColl.setText("已收藏");
        }
    }
}


class MyStepJPane extends JPanel{

    public MyStepJPane(MenuStep menuStep){
        JLabel menuStepDesLab = new JLabel(menuStep.getStepDesc());
        JLabel menuStepOrdLab = new JLabel("步骤" + String.valueOf(menuStep.getStepOrder())+ "\n\n\n\n\n\n");
        menuStepOrdLab.setForeground(Color.red);
        add(menuStepOrdLab);
        add(menuStepDesLab);
    }
}