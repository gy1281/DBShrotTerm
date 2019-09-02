package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.domain.food.FoodInfo;
import com.guoyue.kitchenhelper.domain.food.FoodType;
import com.guoyue.kitchenhelper.domain.user.BookformInfo;
import com.guoyue.kitchenhelper.domain.user.FoodBookform;
import com.guoyue.kitchenhelper.domain.user.UserFood;
import com.guoyue.kitchenhelper.domain.user.UserInfo;
import com.guoyue.kitchenhelper.util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FrmBuyCar extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JMenuBar menubar=new JMenuBar();
    private JMenu menu_more=new JMenu("更多");

    private JMenuItem menuItem_exit = new JMenuItem("退出");
    private JMenuItem menuItem_FirstPage = new JMenuItem("首页");
    private JMenuItem menuItem_delete = new JMenuItem("退款");

    private Object tblFormTitle[]= FoodBookform.tblFormTitle;//计划UI
    private Object tblFormData[][];
    DefaultTableModel tabFormModel=new DefaultTableModel();
    private JTable dataTableForm=new JTable(tabFormModel);


    private Object tblInfoTitle[]= BookformInfo.tblInfoTitle;
    private Object tblInfoData[][];
    DefaultTableModel tabInfoModel=new DefaultTableModel();
    private JTable dataTableInfo=new JTable(tabInfoModel);

    private FoodBookform curForm = null;

    List<FoodBookform> foodBookforms = null;
    List<BookformInfo> bookformInfos = null;

    private void reloadFormTable(){
        try {
            foodBookforms = KitchenHelperUtil.bookFormManager.findFoodBookFormByUserId(UserInfo.currentLoginUser.getUserId());
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblFormData =  new Object[foodBookforms.size()][FoodBookform.tblFormTitle.length];
        for(int i=0;i<foodBookforms.size();i++){
            for(int j=0;j<FoodBookform.tblFormTitle.length;j++)
                tblFormData[i][j]=foodBookforms.get(i).getCell(j);
        }
        tabFormModel.setDataVector(tblFormData,tblFormTitle);
        this.dataTableForm.validate();
        this.dataTableForm.repaint();
    }

    private void reloadInfoTabel(int typeIdx){
        if(typeIdx<0) return; //当前食材类别编号
        curForm = foodBookforms.get(typeIdx);
        try {
            bookformInfos=KitchenHelperUtil.bookFormManager.findBookFormInfoByBfId(curForm.getBfId());
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblInfoData =new Object[bookformInfos.size()][BookformInfo.tblInfoTitle.length];
        for(int i=0;i<bookformInfos.size();i++){
            for(int j=0;j<BookformInfo.tblInfoTitle.length;j++)
                tblInfoData[i][j]=bookformInfos.get(i).getCell(j);
        }

        tabInfoModel.setDataVector(tblInfoData,tblInfoTitle);
        this.dataTableInfo.validate();
        this.dataTableInfo.repaint();
    }



    public FrmBuyCar(){
        this.setSize(1200, 700);

        //居中定位
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.setTitle("厨房小助手");
        this.setVisible(true);

        this.menu_more.add(this.menuItem_exit); this.menuItem_exit.addActionListener(this);
        this.menu_more.add(this.menuItem_FirstPage); this.menuItem_FirstPage.addActionListener(this);
        this.menu_more.add(this.menuItem_delete); this.menuItem_delete.addActionListener(this);

        menubar.add(menu_more);
        this.setJMenuBar(menubar);

        this.getContentPane().add(new JScrollPane(this.dataTableForm), BorderLayout.WEST);

        this.dataTableForm.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("选中订单");
                int i=FrmBuyCar.this.dataTableForm.getSelectedRow();
                if(i<0) {
                    return;
                }
                FrmBuyCar.this.reloadInfoTabel(i);
            }

        });


        this.getContentPane().add(new JScrollPane(this.dataTableInfo), BorderLayout.CENTER);

        this.reloadFormTable();


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
            FrmBuyCar.this.setVisible(false);
            new FrmItem();
        }else if(e.getSource() == this.menuItem_exit){
            System.exit(0);
        }else if(e.getSource() == this.menuItem_delete){
            JOptionPane.showMessageDialog(null, "确认退款", "错误",JOptionPane.ERROR_MESSAGE);
            KitchenHelperUtil.bookFormManager.deleteBookFormById(curForm);
            this.reloadFormTable();
            this.reloadInfoTabel(0);
        }
    }
}
