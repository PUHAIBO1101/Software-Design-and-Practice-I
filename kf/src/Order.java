import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Order extends JPanel implements ActionListener {			//记录预约类
    JTextField name, phone, checkInTime, leaveTime, roomType ;	//填写预约信息
    JButton button ;					//提交订单信息按钮
    Box baseBox, box1, box2 ;

    Order() {
        // 设置背景
        box1 = Box.createVerticalBox() ;			//左边标签栏
        box1.add(new JLabel("预约人姓名")) ;
        box1.add(Box.createVerticalStrut(10)) ;
        box1.add(new JLabel("电话号码")) ;
        box1.add(Box.createVerticalStrut(10)) ;
        box1.add(new JLabel("预约开始时间")) ;
        box1.add(Box.createVerticalStrut(10)) ;
        box1.add(new JLabel("预约结束时间")) ;
        box1.add(Box.createVerticalStrut(20)) ;
        box1.add(new JLabel("诊室号")) ;
        box2 = Box.createVerticalBox() ;			//右边输入栏
        box2.add(Box.createVerticalStrut(20)) ;
        box2.add(name = new JTextField(30)) ;
        box2.add(Box.createVerticalStrut(10)) ;
        box2.add(phone = new JTextField(30)) ;
        box2.add(Box.createVerticalStrut(10)) ;
        box2.add(checkInTime = new JTextField(30)) ;
        box2.add(Box.createVerticalStrut(10)) ;
        box2.add(leaveTime = new JTextField(30)) ;
        box2.add(Box.createVerticalStrut(10)) ;
        box2.add(roomType = new JTextField(30)) ;
        box2.add(Box.createVerticalStrut(10)) ;
        baseBox = Box.createHorizontalBox() ;
        baseBox.add(box1) ;
        baseBox.add(Box.createHorizontalStrut(10)) ;
        baseBox.add(box2) ;
        button = new JButton("确认") ;
        button.addActionListener(this) ;
        add(baseBox) ;
        add(button) ;
        setBounds(0, 30, 800, 300) ;
        setVisible(true) ;
    }
    @Override
    public void actionPerformed(ActionEvent e) {//处理事件

    }
}
