import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class XJFrame extends JFrame implements ActionListener {
    JButton bookingFunction ;		//预约功能
    JButton arriveFunction ;		//签到功能
    JButton changeFunction ;		//修改功能
    JButton back ;					//返回页面按钮
    JPanel home ;					//主页面
    BookingPanel bookingPanel ;		//预约面板
    ChangePanel changePanel ;		//到达面板
    int flag = 0 ;					//记录使用了哪一种功能
    XJFrame() {

        setLayout(null) ;
        setBounds(300, 100, 800, 800) ;
        setVisible(true) ;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel wel=new JLabel("欢迎使用口腔诊所预约系统");
        Font font = new Font("黑体", Font.PLAIN, 40);
        wel.setFont(font);
        wel.setForeground(Color.gray);
        wel.setBounds(150,150,500,50);
        bookingFunction = new JButton("记录预约");
        bookingFunction.addActionListener(this);
        bookingFunction.setBounds(200, 300, 150, 100);
        arriveFunction = new JButton("修改信息");
        arriveFunction.addActionListener(this);
        arriveFunction.setBounds(450, 300, 150, 100);
        back = new JButton("返回") ;
        back.setBounds(20, 20, 60, 30) ;
        back.addActionListener(this) ;
        back.setVisible(false) ;
        home = new JPanel() ;
        home.setLayout(null) ;
        home.setBounds(0, 0, 800, 800) ;
        home.add(bookingFunction) ;
        home.add(arriveFunction) ;
        home.add(wel);
        add(back) ;
        add(home) ;
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bookingFunction) {		//点击“预约”进入预约功能
            bookingPanel = new BookingPanel() ;
            home.setVisible(false) ;
            remove(home) ;
            add(bookingPanel) ;
            back.setVisible(true) ;
            flag = 1 ;
        }
        if(e.getSource() == arriveFunction) {		//点击“修改信息”进入修改功能
            changePanel = new ChangePanel() ;
            home.setVisible(false) ;
            remove(home) ;
            add(changePanel) ;
            back.setVisible(true) ;
            flag = 2 ;
        }
        if(e.getSource() == back) {				//返回功能页面
            if(flag == 1) {
                bookingPanel.setVisible(false) ;
                remove(bookingPanel) ;				//移除填写预约面板
            }
            if(flag == 2) {
                changePanel.setVisible(false) ;		//移除到达面板
                remove(changePanel) ;
            }
            home.setVisible(true) ;
            add(home) ;							//重新添加功能按钮
            back.setVisible(false) ;
        }
    }
}