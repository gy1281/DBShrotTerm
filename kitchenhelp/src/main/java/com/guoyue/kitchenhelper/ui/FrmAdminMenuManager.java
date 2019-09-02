package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.domain.menu.MenuFood;
import com.guoyue.kitchenhelper.domain.menu.MenuInfo;
import com.guoyue.kitchenhelper.domain.menu.MenuStep;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FrmAdminMenuManager extends JFrame implements ActionListener {

    private JMenuBar menubar=new JMenuBar();

    private JMenu menu_manager = new JMenu("菜谱管理");
    private JMenu step_manager = new JMenu("步骤管理");
    private JMenu food_manager = new JMenu("食材管理");

    private JMenuItem  menuItem_addstpe=new JMenuItem("添加步骤");
    private JMenuItem  menuItem_reducestep=new JMenuItem("删除步骤");

    private JMenuItem  menuItem_addmenu=new JMenuItem("添加菜谱");
    private JMenuItem  menuItem_reducemenu=new JMenuItem("删除菜谱");

    private JMenuItem menuItem_addfood = new JMenuItem("添加食材");
    private JMenuItem menuItem_reducefood = new JMenuItem("删除食材");

    private Object tblMenuTitle[]= MenuInfo.tblMenutitle2;
    private Object tblMenuData[][];
    DefaultTableModel tabMenuModel=new DefaultTableModel();
    private JTable dataTableMenu=new JTable(tabMenuModel);


    private Object tblStepTitle[]= MenuStep.tblSteptitle;
    private Object tblStepData[][];
    DefaultTableModel tabStepModel=new DefaultTableModel();
    private JTable dataTableStep=new JTable(tabStepModel);


    private Object tblFoodTitle[]= MenuFood.tblFoodtitle;
    private Object tblFoodData[][];
    DefaultTableModel tabFoodModel=new DefaultTableModel();
    private JTable dataTableFood=new JTable(tabFoodModel);

    public static MenuInfo curMenu=null;
    private MenuStep curStep = null;
    private MenuFood curFood = null;

    List<MenuInfo> menuInfos = null;
    List<MenuStep> menuSteps = null;
    List<MenuFood> menuFoods = null;

    int stepRow;
    int menuRow;
    int foodRow;

    private void reloadMenuTable(){

        menuInfos = KitchenHelperUtil.menuManager.findAllMenu();

        tblMenuData = new Object[menuInfos.size()][MenuInfo.tblMenutitle2.length];

        for(int i = 0;i < menuInfos.size(); i++){
            for(int j = 0; j < MenuInfo.tblMenutitle2.length; j++)
                tblMenuData[i][j] = menuInfos.get(i).getCell2(j);
        }

        tabMenuModel.setDataVector(tblMenuData,tblMenuTitle);
        this.dataTableMenu.validate();
        this.dataTableMenu.repaint();
    }

    private void reloadStepTabel(int menuIdx){
        if(menuIdx<0) return; //当前食材类别编号
        curMenu=menuInfos.get(menuIdx);

        menuSteps = KitchenHelperUtil.menuManager.findMenuStepByMenuId(curMenu.getMenuId());

        tblStepData =new Object[menuSteps.size()][MenuStep.tblSteptitle.length];
        for(int i=0;i<menuSteps.size();i++){
            for(int j=0;j<MenuStep.tblSteptitle.length;j++)
                tblStepData[i][j]=menuSteps.get(i).getCell(j);
        }

        tabStepModel.setDataVector(tblStepData,tblStepTitle);
        this.dataTableStep.validate();
        this.dataTableStep.repaint();
    }

    private void reloadFoodTable(int menuIdx){
        if(menuIdx<0) return; //当前食材类别编号
        curMenu=menuInfos.get(menuIdx);

        menuFoods = KitchenHelperUtil.menuManager.findMenuFoodByMenuId(curMenu.getMenuId());

        tblFoodData =new Object[menuFoods.size()][MenuFood.tblFoodtitle.length];
        for(int i=0;i<menuFoods.size();i++){
            for(int j=0;j<MenuFood.tblFoodtitle.length;j++)
                tblFoodData[i][j]=menuFoods.get(i).getCell(j);
        }

        tabFoodModel.setDataVector(tblFoodData,tblFoodTitle);
        this.dataTableFood.validate();
        this.dataTableFood.repaint();
    }


    public FrmAdminMenuManager(){
        this.setSize(1200, 700);

        //居中定位
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.setTitle("我的菜谱管理界面");

        menu_manager.add(menuItem_addmenu);
        menu_manager.add(menuItem_reducemenu);

        step_manager.add(menuItem_addstpe);
        step_manager.add(menuItem_reducestep);

        food_manager.add(menuItem_addfood);
        food_manager.add(menuItem_reducefood);

        menuItem_addmenu.addActionListener(this);
        menuItem_reducemenu.addActionListener(this);
        menuItem_addstpe.addActionListener(this);
        menuItem_reducestep.addActionListener(this);
        menuItem_addfood.addActionListener(this);
        menuItem_reducefood.addActionListener(this);

        menubar.add(menu_manager);
        menubar.add(step_manager);
        menubar.add(food_manager);

        this.setJMenuBar(menubar);
        this.getContentPane().add(new JScrollPane(this.dataTableMenu), BorderLayout.CENTER);

        this.dataTableMenu.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("选中菜谱");
                menuRow = FrmAdminMenuManager.this.dataTableMenu.getSelectedRow();
                if(menuRow<0) {
                    return;
                }
                FrmAdminMenuManager.this.reloadStepTabel(menuRow);
                FrmAdminMenuManager.this.reloadFoodTable(menuRow);
            }

        });

        this.dataTableStep.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                stepRow =FrmAdminMenuManager.this.dataTableStep.getSelectedRow();
                System.out.println("选中菜谱步骤"+stepRow);
                if(stepRow < 0) {
                    return;
                }
            }

        });

        this.dataTableFood.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {

                foodRow =FrmAdminMenuManager.this.dataTableFood.getSelectedRow();
                System.out.println("选中菜谱食材"+foodRow);
                if(stepRow < 0) {
                    return;
                }
            }

        });

        this.getContentPane().add(new JScrollPane(this.dataTableStep), BorderLayout.EAST);
        this.getContentPane().add(new JScrollPane(this.dataTableFood), BorderLayout.WEST);

        this.reloadMenuTable();
        this.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menuItem_addmenu){
            FrmAddMenu frmAddMenu = new FrmAddMenu();
            this.reloadMenuTable();
            frmAddMenu.setVisible(true);
        }else if(e.getSource() == menuItem_addstpe){
            FrmAddMenuStep frmAddMenuStep = new FrmAddMenuStep();
            this.reloadStepTabel(menuRow);
            frmAddMenuStep.setVisible(true);
        }else if(e.getSource() == menuItem_addfood){
            FrmAddMenuFood frmAddMenuFood = new FrmAddMenuFood();
            this.reloadFoodTable(menuRow);
            frmAddMenuFood.setVisible(true);
        }else if(e.getSource() == menuItem_reducemenu){
            try{
                KitchenHelperUtil.menuManager.deleteMenu(curMenu);
            }catch (Exception e1){
                throw new RuntimeException();
            }
            this.reloadMenuTable();
        }else if(e.getSource() == menuItem_reducefood){
            KitchenHelperUtil.menuManager.deleteFood(menuFoods.get(foodRow));
            this.reloadFoodTable(menuRow);
        }else if(e.getSource() == menuItem_reducestep){
            KitchenHelperUtil.menuManager.deleteStep(menuSteps.get(stepRow));
            this.reloadStepTabel(menuRow);
        }


    }
}
