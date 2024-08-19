import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ReserveFill extends Order implements ActionListener{		//记录预定订单
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button) {
            try {	Class.forName("com.mysql.cj.jdbc.Driver") ;	}
            catch(Exception ee) {}//
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","puhaibo123") ;
                Statement sql = con.createStatement() ;
                ResultSet rs = sql.executeQuery("SELECT * FROM roommessage WHERE 诊室号="+roomType.getText()+"");//首先从房间信息表中找符合条件的房间
                System.out.println(1);
                boolean flag = true ;			//记录是否预定成功
                while(rs.next()) {
                    System.out.println(2);
                    String type = rs.getString(1) ;
                    String number = rs.getString(2) ;
                    String state = rs.getString(3) ;
                    if(state.equals("空闲")) {
                        System.out.println(3);//想预定的房间类型有空房就预定
                        sql.executeUpdate("update roommessage set 状态='预约' where 诊室号='"+number+"'") ;
                        System.out.println(4);//更新房间状态
                        sql.executeUpdate("insert bookingmessage values ( '"+name.getText()+"','"+number+"','"+phone.getText()+"','"+checkInTime.getText()+"','"+leaveTime.getText()+"')") ;
                        System.out.println(5);//添加预定信息到数据库中
                        JOptionPane.showMessageDialog(this, "预定成功！房间号："+number+" ",null, JOptionPane.INFORMATION_MESSAGE);
                        flag = false ;
                        break ;
                    }
                }
                if(flag)
                    JOptionPane.showMessageDialog(this, "预定失败！",null, JOptionPane.INFORMATION_MESSAGE);
                con.close() ;
            }
            catch(Exception ee) {}
        }
    }
}
