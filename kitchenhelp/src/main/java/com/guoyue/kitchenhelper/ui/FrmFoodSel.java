package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.domain.food.FoodInfo;
import com.guoyue.kitchenhelper.domain.food.FoodType;
import com.guoyue.kitchenhelper.domain.user.UserFood;
import com.guoyue.kitchenhelper.domain.user.UserInfo;
import com.guoyue.kitchenhelper.util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FrmFoodSel extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JMenuBar menubar=new JMenuBar();
    private JMenu menu_type=new JMenu("食材管理");
    private JMenu menu_more=new JMenu("更多");

    private JMenuItem  menuItem_addFood=new JMenuItem("添加到我的食材");
    private JMenuItem  menuItem_myFood=new JMenuItem("我的食材");

    private JMenuItem menuItem_exit = new JMenuItem("退出");
    private JMenuItem menuItem_FirstPage = new JMenuItem("首页");

    private Object tblTypeTitle[]= FoodType.tblTypeTitle;//计划UI
    private Object tblTypeData[][];
    DefaultTableModel tabTypeModel=new DefaultTableModel();
    private JTable dataTableType=new JTable(tabTypeModel);


    private Object tblFoodTitle[]= FoodInfo.tblFoodTitle;
    private Object tblFoodData[][];
    DefaultTableModel tabFoodModel=new DefaultTableModel();
    private JTable dataTableFood=new JTable(tabFoodModel);

    private FoodType curType=null;
    private FoodInfo curFood = null;
    List<FoodType> types=null;
    List<FoodInfo> foods=null;

    Integer foodRow = null;
    int typeRow;
    private void reloadTypeTable(){
        try {
            types= KitchenHelperUtil.foodManager.findAllFoodType();
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblTypeData =  new Object[types.size()][FoodType.tblTypeTitle.length];
        for(int i=0;i<types.size();i++){
            for(int j=0;j<FoodType.tblTypeTitle.length;j++)
                tblTypeData[i][j]=types.get(i).getCell(j);
        }
        tabTypeModel.setDataVector(tblTypeData,tblTypeTitle);
        this.dataTableType.validate();
        this.dataTableType.repaint();
    }
    private void reloadFoodTabel(int typeIdx){
        if(typeIdx<0) return; //当前食材类别编号
        curType=types.get(typeIdx);
        try {
            foods=KitchenHelperUtil.foodManager.findFoodByTypeId(curType.getTypeId());
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblFoodData =new Object[foods.size()][FoodInfo.tblFoodTitle.length];
        for(int i=0;i<foods.size();i++){
            for(int j=0;j<FoodInfo.tblFoodTitle.length;j++)
                tblFoodData[i][j]=foods.get(i).getCell(j);
        }

        tabFoodModel.setDataVector(tblFoodData,tblFoodTitle);
        this.dataTableFood.validate();
        this.dataTableFood.repaint();
    }



    public FrmFoodSel(){
        this.setSize(1200, 700);

        //居中定位
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.setTitle("厨房小助手");
        this.setVisible(true);
        this.menu_type.add(this.menuItem_addFood); this.menuItem_addFood.addActionListener(this);
        this.menu_type.add(this.menuItem_myFood); this.menuItem_myFood.addActionListener(this);
        this.menu_more.add(this.menuItem_exit); this.menuItem_exit.addActionListener(this);
        this.menu_more.add(this.menuItem_FirstPage); this.menuItem_FirstPage.addActionListener(this);

        menubar.add(menu_type);
        menubar.add(menu_more);
        this.setJMenuBar(menubar);

        this.getContentPane().add(new JScrollPane(this.dataTableType), BorderLayout.WEST);

        this.dataTableType.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("选中食材种类");
                typeRow=FrmFoodSel.this.dataTableType.getSelectedRow();
                if(typeRow<0) {
                    return;
                }
                FrmFoodSel.this.reloadFoodTabel(typeRow);
            }

        });

        this.dataTableFood.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("选中食材");
                foodRow =FrmFoodSel.this.dataTableFood.getSelectedRow();
                if(foodRow < 0) {
                    return;
                }
            }

        });
        this.getContentPane().add(new JScrollPane(this.dataTableFood), BorderLayout.CENTER);

        this.reloadTypeTable();


        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.menuItem_FirstPage){
            System.out.println("返回首页");
            FrmFoodSel.this.setVisible(false);
            new FrmItem();
        }else if(e.getSource() == this.menuItem_exit){
            System.exit(0);
        }else if(e.getSource() == this.menuItem_addFood){
            if(foodRow < 0){
                return;
            }
            curFood = foods.get(foodRow);
            List<UserFood> userFoods = KitchenHelperUtil.userManager.findAllUserFoodByFoodIdAndUserId(curFood.getFoodId(), UserInfo.currentLoginUser.getUserId());
            if(userFoods.size() != 0){
                KitchenHelperUtil.userManager.updataUserFoodPlus(userFoods.get(0));
            }
            else{
                KitchenHelperUtil.userManager.saveUserFood(curFood, UserInfo.currentLoginUser.getUserId());
            }
            FrmMyFood myFood = new FrmMyFood();
            myFood.reloadTable();
            this.reloadFoodTabel(typeRow);
        }else if(e.getSource() == this.menuItem_myFood){
            new FrmMyFood();
        }
    }
}
