package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.domain.menu.MenuFood;
import com.guoyue.kitchenhelper.domain.user.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmMenuFood extends JFrame implements ActionListener {

    JButton butEval = new JButton("评论");
    JButton butColl = new JButton("收藏");
    JButton butFoodList = new JButton("生成购物清单");

    JLabel foodLab = new JLabel("食材" + "\n\n\n\n\n\n");
    JLabel numberLab = new JLabel("数量");

    JPanel toolBarBut = new JPanel();
    JPanel name = new JPanel();
    JPanel workPaneFood;

    JScrollPane jScrollPane2;

    List<MenuFood> menuFoods;

    public FrmMenuFood(){
        this.setVisible(true);

        this.setSize(300,250);

        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        toolBarBut.add(butEval);
        toolBarBut.add(butColl);
        toolBarBut.add(butFoodList);

        name.add(foodLab);
        name.add(numberLab);

        workPaneFood = new JPanel();

        workPaneFood.setLayout(new BoxLayout(workPaneFood,  BoxLayout.Y_AXIS));

        jScrollPane2 = new JScrollPane(workPaneFood,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(jScrollPane2);

        this.add(toolBarBut, BorderLayout.SOUTH);
        this.add(name, BorderLayout.NORTH);

        butColl.addActionListener(this);
        butEval.addActionListener(this);
        butFoodList.addActionListener(this);

        menuFoods = KitchenHelperUtil.menuManager.findMenuFoodByMenuId(FrmMenuMain.curMenu.getMenuId());
        reloadFood();
    }

    public void reloadFood(){
        for(MenuFood menuFood : menuFoods){
            System.out.println("进入食材");
            workPaneFood.add(new MyFoodJPane(menuFood));
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
            this.reloadFood();
        }else if(e.getSource() == butColl){
            KitchenHelperUtil.menuManager.updateMenuEvalColl(FrmMenuMain.curMenu.getMenuId(), UserInfo.currentLoginUser.getUserId());
            if(butColl.getText().equals("已收藏"))
                butColl.setText("收藏");
            else
                butColl.setText("已收藏");
        }else if(e.getSource() == butFoodList){
            FrmFoodList frmFoodList = new FrmFoodList();
            frmFoodList.setVisible(true);
        }
    }
}

class MyFoodJPane extends JPanel{

    public MyFoodJPane(MenuFood menuFood){
        JLabel menuFoodNameLab = new JLabel(menuFood.getFoodName() + "\n\n\n");
        JLabel menuFoodNumberLab = new JLabel(String.valueOf(menuFood.getFoodNumber()));
        menuFoodNameLab.setForeground(Color.red);
        add(menuFoodNameLab);
        add(menuFoodNumberLab);
    }
}