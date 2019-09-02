package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.domain.user.UserFood;
import com.guoyue.kitchenhelper.domain.user.UserInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FrmMyFood extends JFrame implements ActionListener {

    private Object tblTitle[]= UserFood.tblTitle;//计划UI
    private Object tblData[][];
    DefaultTableModel tabModel=new DefaultTableModel();
    private JTable dataTable=new JTable(tabModel);

    private JPanel toolBar = new JPanel();

    private JPanel price_Conf = new JPanel();

    private JButton bdelete = new JButton("删除食材");
    private JButton breduce = new JButton("减少食材数量");
    private JButton confForm = new JButton("确认订单");

    private JLabel totlePrice;

    private UserFood curUserFood = null;

    Integer tblRow = 0;

    List<UserFood> userFoods = null;

    public void reloadTable(){
        tblData =  new Object[userFoods.size()][UserFood.tblTitle.length];
        for(int i=0;i<userFoods.size();i++){
            for(int j=0;j<UserFood.tblTitle.length;j++)
                tblData[i][j]=userFoods.get(i).getCell(j);
        }

        tabModel.setDataVector(tblData,tblTitle);

        this.dataTable.validate();
        this.dataTable.repaint();
    }

    public FrmMyFood() {
        this.setSize(600, 350);

        //居中定位
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.setTitle("我的食材");

        toolBar.add(bdelete);
        toolBar.add(breduce);

        totlePrice = new JLabel("总金额" + this.totalPrice());

        price_Conf.add(totlePrice);
        price_Conf.add(confForm);

        bdelete.addActionListener(this);
        breduce.addActionListener(this);
        confForm.addActionListener(this);

        this.add(toolBar, BorderLayout.NORTH);

        this.add(price_Conf,BorderLayout.SOUTH);

        this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);

        this.dataTable.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("用户所有食材");
                tblRow = FrmMyFood.this.dataTable.getSelectedRow();
                if(tblRow < 0) {
                    return;
                }
            }
        });
        this.reloadTable();
        FrmMyFood.this.setVisible(true);
    }

    public float totalPrice(){
        float totle = 0;
        float price;
        try{
            userFoods = KitchenHelperUtil.userManager.findAllUserFoodByUserId(UserInfo.currentLoginUser.getUserId());
        }catch(Exception e){
            throw new RuntimeException();
        }
        for(UserFood userFood : userFoods){
            price = KitchenHelperUtil.foodManager.findFoodById(userFood.getFoodId()).getFoodPrice();
            totle = totle + price * userFood.getFoodNumber();
        }
        return totle;
    }


    public void actionPerformed(ActionEvent e) {
        curUserFood = userFoods.get(tblRow);
        if(e.getSource() == bdelete){
            JOptionPane.showMessageDialog(null, "确认删除食材", "错误",JOptionPane.ERROR_MESSAGE);
            KitchenHelperUtil.userManager.deleteUserFood(curUserFood);
        }else if(e.getSource() == breduce){
            if(curUserFood.getFoodNumber() > 1)
                KitchenHelperUtil.userManager.updataUserFoodReduce(curUserFood);
            else if(curUserFood.getFoodNumber() == 1)
                KitchenHelperUtil.userManager.deleteUserFood(curUserFood);
        }else if(e.getSource() == confForm){
            this.setVisible(false);
            ConfForm dgl = new ConfForm();
            dgl.setVisible(true);
        }
        totlePrice.setText("总金额" + this.totalPrice());
        this.reloadTable();
    }
}
