package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.domain.food.FoodInfo;
import com.guoyue.kitchenhelper.domain.food.FoodType;
import com.guoyue.kitchenhelper.util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FrmAdmainFoodManager extends JFrame implements ActionListener {

    JPanel toolBar = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private JButton btAddFoodType = new JButton("添加食材种类");
    private JButton btReduceFoodType = new JButton("删除食材种类");
    private JButton btAddFood=new JButton("添加食材");
    private JButton btReduceFood=new JButton("删除食材");

    JButton search = new JButton("查询");

    JComboBox searchWay = new JComboBox();

    JTextField searchText = new JTextField(25);

    private Object tblTypeTitle[]= FoodType.tblTypeTitle;//计划UI
    private Object tblTypeData[][];
    DefaultTableModel tabTypeModel=new DefaultTableModel();
    private JTable dataTableType=new JTable(tabTypeModel);


    private Object tblFoodTitle[]= FoodInfo.tblFoodTitle;
    private Object tblFoodData[][];
    DefaultTableModel tabFoodModel=new DefaultTableModel();
    private JTable dataTableFood=new JTable(tabFoodModel);

    public static FoodType curType=null;
    private FoodInfo curFood = null;
    List<FoodType> types=null;
    List<FoodInfo> foods=null;

    Integer foodRow = null;
    int typeRow;

    Boolean flag = false;
    Boolean flag2 = false;
    private void reloadTypeTable(){
        try {
            if(flag2 == false){
                types= KitchenHelperUtil.foodManager.findAllFoodType();
            }
            else {
                types = KitchenHelperUtil.adminManager.findFoodTypeByName(searchText.getText());
            }
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
            if(flag == false){
                foods=KitchenHelperUtil.foodManager.findFoodByTypeId(curType.getTypeId());
            }
            else{
                foods = KitchenHelperUtil.adminManager.findFoodByName(searchText.getText());
            }
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

    public FrmAdmainFoodManager(){
        this.setSize(1200, 700);

        //居中定位
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.setTitle("食材管理界面");
        this.setVisible(true);

        searchWay.addItem("查找食材种类");
        searchWay.addItem("查找食材");

        btAddFood.addActionListener(this);
        btAddFoodType.addActionListener(this);
        btReduceFood.addActionListener(this);
        btReduceFoodType.addActionListener(this);
        search.addActionListener(this);

        toolBar.add(btAddFood);
        toolBar.add(btReduceFood);
        toolBar.add(btAddFoodType);
        toolBar.add(btReduceFoodType);
        toolBar.add(searchWay);
        toolBar.add(searchText);
        toolBar.add(search);

        this.add(toolBar, BorderLayout.NORTH);

        this.getContentPane().add(new JScrollPane(this.dataTableType), BorderLayout.WEST);

        this.dataTableType.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("选中食材种类");
                typeRow = FrmAdmainFoodManager.this.dataTableType.getSelectedRow();
                if(typeRow<0) {
                    return;
                }
                FrmAdmainFoodManager.this.reloadFoodTabel(typeRow);
            }

        });

        this.dataTableFood.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("选中食材");
                foodRow =FrmAdmainFoodManager.this.dataTableFood.getSelectedRow();
                if(foodRow < 0) {
                    return;
                }
            }

        });
        this.getContentPane().add(new JScrollPane(this.dataTableFood), BorderLayout.CENTER);

        this.reloadTypeTable();


//        this.addWindowListener(new WindowAdapter(){
//            public void windowClosing(WindowEvent e){
//                System.exit(0);
//            }
//        });
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btAddFood){
            FrmAddFood frmAddFood = new FrmAddFood();
            frmAddFood.setVisible(true);
            FrmAdmainFoodManager.this.reloadFoodTabel(typeRow);
        }else if(e.getSource() == btReduceFood){
            try{
                KitchenHelperUtil.adminManager.deleteFood(foods.get(foodRow));
            }catch(Exception e1){
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.reloadFoodTabel(typeRow);
        }else if(e.getSource() == btAddFoodType){
            FrmAddType frmAddType = new FrmAddType();
            frmAddType.setVisible(true);
            FrmAdmainFoodManager.this.reloadTypeTable();
        }else if(e.getSource() == btReduceFoodType){
            try{
                KitchenHelperUtil.adminManager.deleteFoodFype(curType);
            }catch(Exception e1){
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmAdmainFoodManager.this.reloadTypeTable();
        }else if(e.getSource() == search){
            if(searchWay.getSelectedItem().equals("查找食材")){
                flag = true;
                this.reloadFoodTabel(typeRow);
                flag = false;
            }
            else if(searchWay.getSelectedItem().equals("查找食材种类")){
                flag2 = true;
                this.reloadTypeTable();
                this.reloadFoodTabel(0);
                flag2 = false;
            }
        }
    }
}
