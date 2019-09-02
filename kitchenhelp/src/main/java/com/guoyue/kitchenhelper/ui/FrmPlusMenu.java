package com.guoyue.kitchenhelper.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmPlusMenu extends JFrame implements ActionListener {

    JPanel toolBar;
    JPanel workPane;

    JScrollPane jScrollPane;

    JButton plusStepBut  = new JButton("添加步骤");
    JButton removeStepBut = new JButton("删除步骤");
    JButton resetStepBut = new JButton("重置");
    JButton confBut = new JButton("确认");

//    JButton plus

    JPanel infoPane = new JPanel();
    JLabel menuNameLab = new JLabel("");
    JTextField foodNametx = new JTextField();
    JLabel foodDes = new JLabel();


    int index = 1;

    public FrmPlusMenu(){
        this.setVisible(true);
        this.setSize(600, 350);

        //居中定位
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.setTitle("添加菜谱");

        workPane = new JPanel();
        workPane.setLayout(new BoxLayout(workPane,  BoxLayout.Y_AXIS));

        jScrollPane = new JScrollPane(workPane,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(jScrollPane);

        toolBar = new JPanel();

        toolBar.add(plusStepBut);
        toolBar.add(removeStepBut);
        toolBar.add(resetStepBut);
        toolBar.add(confBut);

        plusStepBut.addActionListener(this);
        removeStepBut.addActionListener(this);
        resetStepBut.addActionListener(this);
        confBut.addActionListener(this);

        this.add(toolBar, BorderLayout.SOUTH);

    }

    private void myUpdateUI() {
        SwingUtilities.updateComponentTreeUI(this);//添加或删除组件后,更新窗口
        JScrollBar jsb = jScrollPane.getVerticalScrollBar();//得到垂直滚动条
        jsb.setValue(jsb.getMaximum());//把滚动条位置设置到最下面
    }

    public void actionPerformed(ActionEvent e) {
        JButton curBut = (JButton) e.getSource();
        if(curBut == plusStepBut){
            workPane.add(new MyStepJPanel(index));
            index++;
            myUpdateUI();
        }else if(curBut == removeStepBut){
            if(workPane.getComponentCount()>0) {
                workPane.remove(workPane.getComponentCount()-1);//删除末尾的一个组件 ,
                index-=1;
                myUpdateUI();
            }
        }else if(curBut == resetStepBut){
            for (int i = 0; i < workPane.getComponentCount(); i++) {
                MyStepJPanel mjp = (MyStepJPanel) workPane.getComponent(i);
                //也就是说取值,可以根据文本框所在的位置来取
                System.out.println("第"+(i+1)+"个文本框的值是"+mjp.getJTFValue());
                mjp.setJTFValue("");//清空,重置
                System.out.println("第"+(i+1)+"个文本框的值已清空重置");
            }
        }else if(curBut == confBut){

        }
    }

}

//自定义一个JPanle类
class MyStepJPanel extends JPanel{
    public JTextField jtf;
    public MyStepJPanel(int index) {
        JLabel jl = new JLabel("步骤"+index);
        jtf = new JTextField(25);
        add(jl);
        add(jtf);
    }
    //获取文本框的值
    public String getJTFValue() {
        return jtf.getText();
    }
    //设置文本框的值
    public void setJTFValue(String value) {
        jtf.setText(value);
    }
}

class MyFoodJPanel extends JPanel{
    public JTextField foodNameTx;
    public JTextField foodNumberTx;

    public MyFoodJPanel(int index){
        JLabel foodNameLab = new JLabel("食材名称" + index);
        JLabel foodNumebrLab = new JLabel("单位食材");
        foodNameTx = new JTextField(10);
        foodNumberTx = new JTextField(5);
        add(foodNameLab);
        add(foodNumebrLab);
    }


}
