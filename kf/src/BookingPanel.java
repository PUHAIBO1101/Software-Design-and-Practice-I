import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingPanel extends JPanel implements ActionListener {
    JButton reservation ;		//预约订房按钮
    JButton walkIn ;			//现场订房按钮
    JButton back ;				//返回页面按钮
    JTextArea roomMessage ;		//显示房间信息区域
    Display display ;			//信息类
    ReserveFill fill ;			//预定订房类


    BookingPanel() {
        setLayout(null) ;
        setBounds(0, 0, 800, 800) ;
        setVisible(true) ;

        reservation = new JButton("远程预约");
        reservation.addActionListener(this) ;
        reservation.setBounds(200, 80, 350, 100);
        walkIn = new JButton("请选择状态为空闲的房间进行预约") ;
        walkIn.setBackground(Color.ORANGE);
        walkIn.addActionListener(this) ;
        walkIn.setBounds(250, 220, 250, 50) ;
        roomMessage = new JTextArea(12, 20) ;
        roomMessage.setBounds(200, 300, 350, 200) ;
        back = new JButton("返回") ;
        back.setBounds(20, 20, 100, 50) ;
        back.addActionListener(this) ;
        back.setVisible(false) ;

        add(back) ;
        add(walkIn) ;
        add(reservation) ;
        add(roomMessage) ;
        setInformation() ;
    }
    void setInformation() {				//显示出所有诊室信息
        display = new Display() ;
        display.setRoom() ;				//从数据库中读取诊室信息
        roomMessage.append("  诊室              房间号               房间状态\n") ;
        for(int i=0; i<display.getRoomLength(); i++) {
            Room roomInf = display.getRoom(i) ;
            roomMessage.append(roomInf.getType()+"                  "+roomInf.getRoomNum()+"                       "
                    +roomInf.getState()) ;
            roomMessage.append("\n") ;
        }
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == reservation) {
            walkIn.setVisible(false) ;
            remove(walkIn) ;
            reservation.setVisible(false) ;		//移除功能按钮
            remove(reservation) ;
            fill = new ReserveFill();
            add(fill) ;							//添加填写预约信息面板
            back.setVisible(true) ;

        }

        if(e.getSource() == back) {				//返回功能页面

            walkIn.setVisible(true) ;
            reservation.setVisible(true) ;
            add(reservation) ;					//重新添加功能按钮
            add(walkIn) ;
            back.setVisible(false) ;
        }
    }
}
