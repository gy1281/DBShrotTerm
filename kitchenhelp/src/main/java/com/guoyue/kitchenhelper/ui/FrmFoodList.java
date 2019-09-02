package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.domain.menu.MenuFood;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmFoodList extends JFrame implements ActionListener {

    JPanel toolBar = new JPanel();

    JLabel price;

    JButton printForm = new JButton("一键生成订单");

    List<MenuFood> menuFoods = null;

    private Object tblTitle[]= MenuFood.tbltitle;
    private Object tblData[][];
    DefaultTableModel tabModel=new DefaultTableModel();
    private JTable dataTable=new JTable(tabModel);

    public FrmFoodList(){

        this.setVisible(true);

        this.setSize(300,250);

        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        price = new JLabel();

        menuFoods = KitchenHelperUtil.menuManager.findMenuFoodByMenuId(FrmMenuMain.curMenu.getMenuId());
        price.setText("总价：" + totlePrice());

        toolBar.add(price);
        toolBar.add(printForm);

        printForm.addActionListener(this);

        this.add(toolBar, BorderLayout.SOUTH);
        this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);

        this.reloadTable();

        this.setVisible(true);
    }

    private void reloadTable(){

        tblData =new Object[menuFoods.size()][MenuFood.tbltitle.length];
        for(int i=0;i<menuFoods.size();i++){
            for(int j=0;j<MenuFood.tbltitle.length;j++)
                tblData[i][j]=menuFoods.get(i).getCell2(j);
        }

        tabModel.setDataVector(tblData,tblTitle);
        this.dataTable.validate();
        this.dataTable.repaint();
    }

    public float totlePrice(){
        float tolPrice = 0;
        for(MenuFood menuFood : menuFoods){
            tolPrice = tolPrice + menuFood.getFoodPrice() * menuFood.getFoodNumber();
        }
        return tolPrice;
    }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == printForm){
            FrmSendBookForm frmSendBookForm = new FrmSendBookForm();
            frmSendBookForm.setVisible(true);
        }
    }
}
