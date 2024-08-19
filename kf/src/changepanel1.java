import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class changepanel1 extends JPanel implements ActionListener {
    JLabel label ;
    JTextField phone ;
    JButton arrive ;				//记录到达
    JButton cancel ;				//删除预约
    JButton transfer ;				//更换诊室
    JTextArea customerMessage ;		//显示就诊信息区域
    JTextArea roomMessage ;			//显示诊室信息区域
    Display display ;				//信息类
    display1 display1;
    changepanel1() {
        setLayout(null) ;
        setBounds(0, 0, 800, 800) ;
        setVisible(true) ;
        label = new JLabel("请输入电话号码") ;
        label.setBounds(100, 60, 150, 50) ;
        phone = new JTextField(30) ;
        phone.setBounds(210, 70, 400, 30) ;
        arrive = new JButton("记录到达") ;
        arrive.setBounds(200, 120, 100, 45) ;
        arrive.addActionListener(this) ;
        cancel = new JButton("取消预约") ;
        cancel.setBounds(350, 120, 100, 45) ;
        cancel.addActionListener(this) ;
        transfer = new JButton("更换诊室") ;
        transfer.setBounds(500, 120, 100, 45) ;
        transfer.addActionListener(this) ;
        customerMessage  = new JTextArea(12, 20) ;
        customerMessage.setBounds(150, 200, 500, 200) ;
        roomMessage = new JTextArea(12, 20) ;
        roomMessage.setBounds(150, 450, 500, 200) ;
        add(label);
        add(phone) ;
        add(arrive) ;
        add(cancel) ;
        add(transfer) ;
        add(customerMessage) ;
        add(roomMessage) ;
        //setCustomerInf() ;
        setRoomInf() ;
    }  void setRoomInf() {				//显示出所有诊室信息
        display = new Display() ;
        display.setRoom() ;				//从数据库中读取诊室信息
        roomMessage.append("  诊室             诊室号               房间状态\n") ;
        for(int i=0; i<display.getRoomLength(); i++) {
            Room roomInf = display.getRoom(i) ;
            roomMessage.append("   "+roomInf.getType()+"                 "+roomInf.getRoomNum()+"                        "+roomInf.getState()) ;
            roomMessage.append("\n") ;
        }
    }
    void setCustomerInf() {				//显示预约信息
        display1 = new display1() ;
        display1.setCustomer(phone.getText()) ;				//从数据库中读取病人预约信息
        customerMessage.append("姓名               诊室号            电话号码             开始时间                离开时间\n") ;
        Patient customerInf=display1.get();
        customerMessage.append(customerInf.getName()+"              " +customerInf.getRoomNum()+"                      "
                    +customerInf.getPhone()+"                    "+customerInf.getCheckInTime()+"                     "
                    +customerInf.getLeaveTime()) ;

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == arrive) {			//预约者记录到达
            SignIn signIn = new SignIn(phone.getText()) ;
            customerMessage.setText("");		//更新信息
            roomMessage.setText("") ;
            setCustomerInf() ;
            setRoomInf() ;
        }
        if(e.getSource() == cancel) {			//删除预约
            Cancel cancel = new Cancel(phone.getText()) ;
            customerMessage.setText("");		//更新信息
            roomMessage.setText("") ;
            setCustomerInf() ;
            setRoomInf() ;
        }
        if(e.getSource() == transfer) {			//更换诊室
            int newRoom = Integer.parseInt(JOptionPane.showInputDialog(this,
                    "请输入要更换的房号", null, JOptionPane.INFORMATION_MESSAGE)) ;

            Change change = new Change(phone.getText(), newRoom) ;
            customerMessage.setText("");		//更新信息
            roomMessage.setText("") ;
            setCustomerInf() ;
            setRoomInf() ;
        }
    }
}