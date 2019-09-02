package com.guoyue.kitchenhelper.ui;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.domain.user.UserInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FrmAdminUserManager extends JFrame implements ActionListener {

    JPanel toolBar = new JPanel(new FlowLayout(FlowLayout.LEFT));

    private JButton btDeleteUser = new JButton("删除用户");
    private JButton btAddUser = new JButton("添加用户");
    private JButton btResetPas=new JButton("重置密码");
    private JButton search = new JButton("查询");

    JLabel labSearchUser = new JLabel("查询用户");
    JTextField searchText = new JTextField(15);

    private Object tblTitle[]= UserInfo.tblTitle;
    private Object tblData[][];
    DefaultTableModel tabModel=new DefaultTableModel();
    private JTable dataTable=new JTable(tabModel);

    List<UserInfo> users=null;

    Boolean flag = false;

    int userRow;
    private void reloadTable(){
        try {
            if(flag == false){
                users= KitchenHelperUtil.adminManager.findAllUser();
            }
            else {
                users = KitchenHelperUtil.adminManager.findUserByName(searchText.getText());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblData =  new Object[users.size()][UserInfo.tblTitle.length];
        for(int i=0;i<users.size();i++){
            for(int j=0;j<UserInfo.tblTitle.length;j++)
                tblData[i][j]=users.get(i).getCell(j);
        }
        tabModel.setDataVector(tblData,tblTitle);
        this.dataTable.validate();
        this.dataTable.repaint();
    }
    public FrmAdminUserManager() {
        this.setSize(1200, 700);

        //居中定位
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.setTitle("用户管理界面");

        btDeleteUser.addActionListener(this);
        btAddUser.addActionListener(this);
        btResetPas.addActionListener(this);
        search.addActionListener(this);

        toolBar.add(btAddUser);
        toolBar.add(btDeleteUser);
        toolBar.add(btResetPas);
        toolBar.add(labSearchUser);
        toolBar.add(searchText);
        toolBar.add(search);

        this.add(toolBar, BorderLayout.NORTH);

        btDeleteUser.addActionListener(this);
        btAddUser.addActionListener(this);
        btAddUser.addActionListener(this);
        search.addActionListener(this);

        this.add(toolBar, BorderLayout.NORTH);
        this.dataTable.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("选中食材");
                userRow =FrmAdminUserManager.this.dataTable.getSelectedRow();
                if(userRow < 0) {
                    return;
                }
            }

        });

        this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);

        this.reloadTable();
        this.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == search){
            flag = true;
            reloadTable();
            flag = false;
        }else if(e.getSource() == btAddUser){
            FrmAddUser frmAddUser = new FrmAddUser();
            frmAddUser.setVisible(true);
            reloadTable();
        }else if(e.getSource() == btDeleteUser){
            KitchenHelperUtil.adminManager.deleteUser(users.get(userRow));
            reloadTable();
        }else if(e.getSource() == btResetPas){
            KitchenHelperUtil.adminManager.resetPas(users.get(userRow));
            reloadTable();
        }
    }
}
