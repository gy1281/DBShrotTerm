package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.domain.menu.MenuInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FrmMenuMain extends JFrame implements ActionListener {

    JPanel toolBar = new JPanel();

    JButton stepBut = new JButton("查询菜谱步骤");
    JButton foodBut = new JButton("查询菜谱食材");
    JButton allMenuBut = new JButton("所有菜谱");
    JButton searchMenuBut = new JButton("查询菜谱");
    JButton myMenuBut = new JButton("我的菜谱");
    JButton firstPage = new JButton("首页");

    JTextField searchMenuText = new JTextField(25);
    JComboBox searchWayCMB = new JComboBox();

    private Object tblMenuTitle[]= MenuInfo.tblMenutitle;
    private Object tblMenuData[][];
    DefaultTableModel tabMenuModel=new DefaultTableModel();
    private JTable dataTableMenu=new JTable(tabMenuModel);

    List<MenuInfo> menuInfos = null;
    public static MenuInfo curMenu;
    int menuRow;

    public FrmMenuMain(){

        this.setSize(1200, 700);

        //居中定位
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.setTitle("菜谱主界面");

        searchWayCMB.addItem("按菜名查询");
        searchWayCMB.addItem("按标签查询");

        toolBar.add(firstPage);
        toolBar.add(allMenuBut);
        toolBar.add(stepBut);
        toolBar.add(foodBut);
        toolBar.add(searchWayCMB);
        toolBar.add(searchMenuText);
        toolBar.add(searchMenuBut);
        toolBar.add(myMenuBut);

        firstPage.addActionListener(this);
        searchMenuBut.addActionListener(this);
        stepBut.addActionListener(this);
        foodBut.addActionListener(this);
        searchWayCMB.addActionListener(this);
        allMenuBut.addActionListener(this);
        myMenuBut.addActionListener(this);

        this.getContentPane().add(toolBar, BorderLayout.NORTH);
        this.getContentPane().add(new JScrollPane(this.dataTableMenu), BorderLayout.CENTER);

        menuInfos = KitchenHelperUtil.menuManager.findAllMenu();

        this.dataTableMenu.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("选中菜谱123");
                menuRow = FrmMenuMain.this.dataTableMenu.getSelectedRow();
                if(menuRow < 0) {
                    return;
                }
                curMenu = menuInfos.get(menuRow);
                System.out.println("当前菜谱"+ curMenu);
            }
        });

        this.reloadMenuTable();

        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        this.setVisible(true);

    }

    private void reloadMenuTable(){

        tblMenuData = new Object[menuInfos.size()][MenuInfo.tblMenutitle.length];

        for(int i = 0;i < menuInfos.size(); i++){
            for(int j = 0; j < MenuInfo.tblMenutitle.length; j++)
                tblMenuData[i][j] = menuInfos.get(i).getCell(j);
        }

        tabMenuModel.setDataVector(tblMenuData,tblMenuTitle);

        this.dataTableMenu.validate();
        this.dataTableMenu.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        String searchInfo = searchMenuText.getText();
        if(e.getSource() == stepBut){
            FrmMenuStep frmMenuStep = new FrmMenuStep();
            frmMenuStep.setVisible(true);
            KitchenHelperUtil.menuManager.updateMenuCollNumber(FrmMenuMain.curMenu);
            KitchenHelperUtil.menuManager.updateMenuBrowNumber(FrmMenuMain.curMenu);
            KitchenHelperUtil.menuManager.updateMenuComScore(FrmMenuMain.curMenu);
            reloadMenuTable();
        }else if(e.getSource() == searchMenuBut){
            if(searchWayCMB.getSelectedItem().equals("按菜名查询")){
                menuInfos = KitchenHelperUtil.menuManager.findMenuByName(searchInfo);
            }else if(searchWayCMB.getSelectedItem().equals("按标签查询")){
                menuInfos = KitchenHelperUtil.menuManager.findMenuByDes(searchInfo);
            }
            this.reloadMenuTable();
        }else if(e.getSource() == allMenuBut){
            menuInfos = KitchenHelperUtil.menuManager.findAllMenu();
            this.reloadMenuTable();
        }else if(e.getSource() == myMenuBut){
            FrmMyMenu dlg = new FrmMyMenu();
            dlg.setVisible(true);
            this.reloadMenuTable();
        }else if(e.getSource() == firstPage){
            FrmItem frmItem = new FrmItem();
            frmItem.setVisible(true);
            this.setVisible(false);
        }else if(e.getSource() == foodBut){
            FrmMenuFood frmMenuFood = new FrmMenuFood();
            frmMenuFood.setVisible(true);
            KitchenHelperUtil.menuManager.updateMenuCollNumber(FrmMenuMain.curMenu);
            KitchenHelperUtil.menuManager.updateMenuBrowNumber(FrmMenuMain.curMenu);
            KitchenHelperUtil.menuManager.updateMenuComScore(FrmMenuMain.curMenu);
            this.reloadMenuTable();
        }
    }
}
